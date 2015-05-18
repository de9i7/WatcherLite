/*
 * @(#)FileSecurityHelper
 *
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage.  Release of this
 * file requires prior approval from Luxoft Management.
 *
 * Copyright (c) 2013 The Boeing Company All rights reserved.
 */
package com.sandbox.common.fileresolver;

import java.io.File;

/**
 * Security helper that validates files path.
 *
 * @author dkachurovskiy
 * @version 1.0 06.28.13
 */
public class FileSecurityHelper {

    /**
     * This method checks file name that contains user input data. The file name should not contain any special
     * symbols as "../".
     * Also this method compares expected extension with an actual one.
     *
     * @param folder folder path
     * @param filename filename
     * @param extension expected extension of the file
     * @return File instance
     * @throws SecurityException
     */
    public static File resolveFile(
        final String folder,
        final String filename,
        final String extension
    ) throws SecurityException {

        return compareExtension(resolveFile(folder, filename), extension);
    }

    /**
     * This method checks if an absolute path to the given file is equals to
     * a canonical path to the file. Such checking prevents from creation files
     * with tricky paths (e.g. "../../../security/passwords.txt").
     * Also this method compares expected extension with an actual one.
     *
     * @param filepath a path to check
     * @param extension expected extension of the file
     * @return File instance
     * @throws SecurityException
     */
    public static File resolveFilePathWithExtension(
            final String filepath,
            final String extension
    ) throws SecurityException {

           return compareExtension(resolveFile(filepath), extension);
    }

    /**
     * Compares the extension of the target file with desired
     * extension specified by a caller.
     * @param file target file with extension that has to be checked
     * @param extension desired extension
     */
    private static File compareExtension(final File file, final String extension) throws SecurityException{
        final String filename = file.getName();
        final int extIndex = filename.lastIndexOf(".");
        String expectedExtension = "";

        try {
            if (extIndex <= 0
                    || !(expectedExtension = filename.substring(extIndex + 1)).equalsIgnoreCase(extension)
                    ) {
                final String message = (
                        "File name '" + filename + "' is not correct. "
                                + "File extension '" + expectedExtension
                                + "'. Expected extension: '" + extension + "'"
                );
                throw new SecurityException(message);
            }
        } catch (Exception e) {
            final String message = generateSecurityExceptionMessage(filename, e);
            throw new SecurityException(message);
        }
        return file;
    }

    /**
     * This method checks file name that contains user input data. The file name should not contain any special
     * symbols as "../".
     * See also http://cwe.mitre.org/data/definitions/73.html
     * <br/>
     *
     * In case of safe file name this method returns result file. Otherwise the method throws
     * the <code>SecurityException</code>.<br/>
     *
     * @param folder the name of folder that contained file.
     * @param fileName the name of file
     * @return result file.
     * @throws SecurityException the security exception.
     */
    public static File resolveFile(final String folder, final String fileName) throws SecurityException {
        return resolveFile(new File(folder), fileName);
    }

    /**
     * See {@link FileSecurityHelper#resolveFile(String, String) resolveFile} for details.
     *
     * @param folder the folder.
     * @param fileName the file name.
     * @return the result file.
     * @throws SecurityException the security exception.
     */
    public static File resolveFile(final File folder, final String fileName) throws SecurityException {
        try {
            final String canonicalDir = folder.getCanonicalPath();
            final File result = new File(folder, fileName);
            if (
                !result.getAbsolutePath().equalsIgnoreCase(result.getCanonicalPath())
            ) {
                final String message = (
                        "File name '" + fileName + "' is not safe. "
                                + "File canonical path '" + result.getCanonicalPath()
                                + "'. Folder canonical path: '" + canonicalDir + "'"
                );
//                LOG.info("The SecurityException will be throw: " + message);
                throw new SecurityException(message);
            }
            return result;
        } catch (Exception e) {
//            LOG.error(e.toString(), e);
            final String message = generateSecurityExceptionMessage(fileName, e);
//            LOG.info("The SecurityException will be throw: " + message);
            throw new SecurityException(message);
        }
    }

    /**
     * This method checks if an absolute path to the given file is equals to
     * a canonical path to the file. Such checking prevents from creation files
     * with tricky paths (e.g. "../../../security/passwords.txt").
     * The method throws an unchecked {@code SecurityException} if the
     * paths are distinct.
     *
     * @param filePath a path to check
     * @return {@code File} object if checking was successfully passed.
     * @throws SecurityException the security exception.
     */
    public static File resolveFile(final String filePath) throws SecurityException {
        try {
            final File result = new File(filePath);

            if (!result.getAbsolutePath().equalsIgnoreCase(result.getCanonicalPath())) {

                final String message = (
                    "A path to a file is not safe. The full given path to the file is: '"
                        + result.getAbsolutePath() + "'. The canonical path is: '"
                        + result.getCanonicalPath() + "'."
                );
//                LOG.info("The SecurityException will be throw: " + message);
                throw new SecurityException(message);
            }

            return result;
        } catch (Exception e) {
//            LOG.error(e.toString(), e);
            final String message = generateSecurityExceptionMessage(filePath, e);
//            LOG.info("The SecurityException will be throw: " + message);
            throw new SecurityException(message);
        }
    }

    /**
     * Generates the Security Exception Message
     * @param filePath the file path
     * @param e the exception
     * @return
     */
    private static String generateSecurityExceptionMessage(final String filePath, final Exception e) {
        return (
            "The file validation failed because of an exception for file \""
                + filePath
                + "\". See details: "
                + e.toString()
        );
    }

    /**
     * The private constructor.
     */
    private FileSecurityHelper() {
        super();
    }
}
