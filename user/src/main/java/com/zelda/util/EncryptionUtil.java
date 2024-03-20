package com.zelda.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;

public class EncryptionUtil {
    private static final String AES = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

    //운영환경에서는 추가적으로 Cloud Config server를 구성하고 외부노출을 최소화 해야하는 경우에는
    //AWS에서 제공하는 암호화 관리 기능을 사용하여 key형태만 별도로 관리하는 방법으로도 보안처리필요
    private static final String INIT_VECTOR = "RandomInitVector"; // 16 bytes IV
    public static final String SALT_REG_NO = "saltForRegNo"; // 주민번호용 솔트
    public static final String SALT_CORP_NO = "saltForCorpNo"; // 사업자번호용 솔트

    public static String encrypt(String value, String salt) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
        SecretKeySpec sKeySpec = new SecretKeySpec(generateKey(salt), AES);

        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);

        byte[] encrypted = cipher.doFinal(value.getBytes());

        return Base64.encodeBase64String(encrypted);
    }

    private static byte[] generateKey(String salt) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] key = digest.digest(salt.getBytes("UTF-8"));
        return Arrays.copyOf(key, 16); // AES 키 길이에 맞게 조정
    }
}
