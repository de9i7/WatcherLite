package com.tools.watcher.framework.action;

import com.tools.watcher.framework.action.annotation.ActionHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 *
 */
public class ActionFinder {

    /**
     *
     * @param packageName
     * @return
     */
    public static Map<Class<?>, Object> loadActions(String packageName) {
        Map<Class<?>, Object> classes = null;
        try {
            classes = getClasses("com.tools.watcher.application.controller");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }
    /**
     *
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    protected static Map<Class<?>, Object> getClasses(
        String packageName
    ) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();

        // Convert from resource to file representation
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        // Search classes inside the directory
        Map<Class<?>, Object> classes = new HashMap<Class<?>, Object>();
        for (File dir : dirs) {
            classes.putAll(findClasses(dir, packageName));
        }
        return classes;
    }

    /**
     *
     * @param dir
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    private static Map<Class<?>, Object> findClasses(File dir, String packageName) throws ClassNotFoundException {
        Map<Class<?>, Object> classes = new HashMap<Class<?>, Object>();
        if (!dir.exists()) {
            return classes;
        }

        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                classes.putAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                Class<?> clazz = Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6));
                if (clazz.getAnnotation(ActionHandler.class) != null) {
                    System.out.println("Handler : " + clazz.getName());
                    classes.put(clazz, null);
                }
            }
        }
        return classes;
    }

    public static void main(String[] args) {
        new ActionFinder().run();
    }

    private void run() {
//        String currentPackage = ActionFinder.class.getPackage().getName();
        String currentPackage = "com.tools.watcher.application";

        System.out.println("Package: " + currentPackage);
        try {
            Map<Class<?>, Object> classes = getClasses(currentPackage);
            System.out.println("Classses: " + classes);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }

    }
}
