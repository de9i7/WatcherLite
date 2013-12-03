package com.sandbox.xsd_checksum_updater;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Checksum.
 *
 * @author sbeldekov
 * @version 1.0
 */
public final class Checksum {

    private static final char[] HEX_CHAR_TABLE =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final int HEX_MAX_DIGIT = 0x0F;

    private Checksum() {
    }

    private static String getHexString(byte[] bytes) {
//        System.out.println("Step0: bytes.length : " + Integer.toBinaryString(bytes.length));
//        System.out.println("Step1: bytes.length << 1 : " + Integer.toBinaryString(bytes.length << 1));
        char[] hex = new char[bytes.length << 1];
        int index = 0;
        System.out.println("HEX: " + hex);
        for (byte b : bytes) {
            hex[index++] = HEX_CHAR_TABLE[(b >> 4) & HEX_MAX_DIGIT];
//            System.out.println("index " + index + ": " + hex[--index]);
            hex[index++] = HEX_CHAR_TABLE[b & HEX_MAX_DIGIT];
//            System.out.println("index " + index + ": " + hex[--index]);
        }
        return new String(hex);
    }

    /**
     * Returns checksum.
     *
     * @param bytes input bytes.
     * @return checksum.
     */
    public static String getChecksumMD5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return getHexString(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns checksum.
     *
     * @param file input file.
     * @return checksum.
     * @throws java.io.IOException if I/O error occurs.
     */
    public static String getChecksumMd5(File file) throws IOException {
        return getChecksumMD5(FileUtils.readFileToByteArray(file));
    }

    /**
     * Returns checksum.
     *
     * @param bytes input bytes.
     * @return checksum.
     */
    public static String getChecksumSha512(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            return getHexString(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns checksum.
     *
     * @param file input file.
     * @return checksum.
     * @throws java.io.IOException if I/O error occurs.
     */
    public static String getChecksumSha256(File file) throws IOException {
        return getChecksumSha512(FileUtils.readFileToByteArray(file));
    }

}
