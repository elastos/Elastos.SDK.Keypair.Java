/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.util;
import java.security.MessageDigest;
import java.security.SecureRandom;
/**
 * clark
 * 2018-09-04
 */
public class HashKit {
    public static final long FNV_OFFSET_BASIS_64 = -3750763034362895579L;
    public static final long FNV_PRIME_64 = 1099511628211L;
    private static final SecureRandom random = new SecureRandom();
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    private static final char[] CHAR_ARRAY = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public HashKit() {
    }

    public static long fnv1a64(String key) {
        long hash = -3750763034362895579L;
        int i = 0;

        for(int size = key.length(); i < size; ++i) {
            hash ^= (long)key.charAt(i);
            hash *= 1099511628211L;
        }

        return hash;
    }

    public static String md5(String srcStr) {
        return hash("MD5", srcStr);
    }

    public static String sha1(String srcStr) {
        return hash("SHA-1", srcStr);
    }

    public static String sha256(String srcStr) {
        return hash("SHA-256", srcStr);
    }

    public static String sha384(String srcStr) {
        return hash("SHA-384", srcStr);
    }

    public static String sha512(String srcStr) {
        return hash("SHA-512", srcStr);
    }

    public static String hash(String algorithm, String srcStr) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);

        for(int i = 0; i < bytes.length; ++i) {
            ret.append(HEX_DIGITS[bytes[i] >> 4 & 15]);
            ret.append(HEX_DIGITS[bytes[i] & 15]);
        }

        return ret.toString();
    }

    public static String generateSalt(int saltLength) {
        StringBuilder salt = new StringBuilder(saltLength);

        for(int i = 0; i < saltLength; ++i) {
            salt.append(CHAR_ARRAY[random.nextInt(CHAR_ARRAY.length)]);
        }

        return salt.toString();
    }

    public static String generateSaltForSha256() {
        return generateSalt(32);
    }

    public static String generateSaltForSha512() {
        return generateSalt(64);
    }

    public static boolean slowEquals(byte[] a, byte[] b) {
        if (a != null && b != null) {
            int diff = a.length ^ b.length;

            for(int i = 0; i < a.length && i < b.length; ++i) {
                diff |= a[i] ^ b[i];
            }

            return diff == 0;
        } else {
            return false;
        }
    }
}
