package com.zyjy.qq.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MD5Util {
    public static String getMD5(File file) {
        String MD5 = null;
        try {
            MD5 = DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return MD5;
    }
}
