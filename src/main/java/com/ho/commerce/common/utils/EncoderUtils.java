package com.ho.commerce.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncoderUtils {

    public static String SHA256Decode(String password) {
        StringBuffer sbuf = new StringBuffer();

        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        mDigest.update(password.getBytes());

        byte[] msgStr = mDigest.digest() ;

        for(int i=0; i < msgStr.length; i++){
            byte tmpStrByte = msgStr[i];
            String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);

            sbuf.append(tmpEncTxt) ;
        }

        return sbuf.toString();
    }
}
