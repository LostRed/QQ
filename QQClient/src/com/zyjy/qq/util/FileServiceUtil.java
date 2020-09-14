package com.zyjy.qq.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class FileServiceUtil {
    private static FileServiceUtil FILE_SERVER_UTIL;
    private ServerSocket serverSocket;
    private String host;
    private int port;

    private FileServiceUtil() {
        try {
            this.serverSocket = new ServerSocket(0);
            this.host = InetAddress.getLocalHost().getHostAddress();
            this.port = serverSocket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileServiceUtil getFileServerUtil() {
        if (FILE_SERVER_UTIL == null) {
            synchronized (FileServiceUtil.class) {
                if (FILE_SERVER_UTIL == null) {
                    FILE_SERVER_UTIL = new FileServiceUtil();
                }
            }
        }
        return FILE_SERVER_UTIL;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
