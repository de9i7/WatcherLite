package com.tools.watcher.framework.action.service;

import com.tools.watcher.framework.action.AbstractAction;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ActionFinder {

    /**
     *
     * @param packageName
     * @return
     */
    public static Map<String, AbstractAction> loadActions(String packageName) {
        try {
            Iterable<Class> classes = getClasses(packageName);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     *
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    protected static Iterable<Class> getClasses(
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
        List<Class> classes = new ArrayList<Class>();
        for (File dir : dirs) {
            classes.addAll(findClasses(dir, packageName));
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
    private static List<Class> findClasses(File dir, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!dir.exists()) {
            return classes;
        }

        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static void main(String[] args) {
        new ActionFinder().run();
    }

    private void run() {
        String currentPackage = ActionFinder.class.getPackage().getName();
        System.out.println("Package: " + currentPackage);
        try {
            Iterable<Class> classes = getClasses(currentPackage);
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
