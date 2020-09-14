package com.zyjy.qq.util;


import java.io.*;
import java.net.Socket;

public class IOStreamUtil {
    public static void Transmit(InputStream is, OutputStream os) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] bytes = new byte[1024 * 1024];
        while (true) {
            int rs = bis.read(bytes);
            if (rs < 0) {
                break;
            }
            bos.write(bytes, 0, rs);
            bos.flush();
        }
    }

    public static void release(InputStream is, OutputStream os, Socket socket) {
        try {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
