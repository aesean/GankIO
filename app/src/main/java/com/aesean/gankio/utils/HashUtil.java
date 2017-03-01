package com.aesean.gankio.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * HashUtil
 *
 * @author xl
 * @version V1.0
 * @since 26/02/2017
 */
public class HashUtil {
    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA224 = "SHA-224";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";

    private static final String DEFAULT_ALGORITHM = SHA256;

    private HashUtil() {
        throw new RuntimeException("禁止实例化");
    }

    public static String hash(String s) {
        byte[] hash = hash(s.getBytes(), DEFAULT_ALGORITHM);
        return StringUtil.bytesToHex(hash);
    }

    public static String hash(String s, String algorithm) {
        return StringUtil.bytesToHex(hash(s.getBytes(), algorithm));
    }

    public static byte[] hash(byte[] bytes, String algorithm) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(bytes);
        return messageDigest.digest();
    }
}
