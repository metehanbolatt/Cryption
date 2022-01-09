package service;

import com.google.crypto.tink.subtle.AesGcmJce;

import java.nio.charset.StandardCharsets;

public class CryptionService {

    public static String encryption(String key128Bit, String plainText, String associatedData){
        String stringEncryption = "";

        try {
            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] byteEncryption = aesGcmJce.encrypt(plainText.getBytes(), associatedData.getBytes());
            stringEncryption = new String(byteEncryption, StandardCharsets.ISO_8859_1);
        } catch (Exception ex){
            System.out.println("Encryption Error: " + ex);
        }

        return stringEncryption;
    }

    public static String decryption(String key128Bit, String cipherText, String associatedData){
        String stringDecryption = "";

        try {
            byte[] convertEncode = cipherText.getBytes(StandardCharsets.ISO_8859_1);
            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] descBytes = aesGcmJce.decrypt(convertEncode, associatedData.getBytes());
            stringDecryption = new String(descBytes);
        } catch (Exception ex){
            System.out.println("Decryption Error: " + ex);
        }
        return stringDecryption;
    }
}
