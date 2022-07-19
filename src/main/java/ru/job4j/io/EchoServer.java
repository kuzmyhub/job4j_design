package ru.job4j.io;

import javax.crypto.MacSpi;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String inMsg = in.readLine();
                    if (inMsg.contains("?msg=Hello")) {
                        out.write("Hello.".getBytes());
                    } else if (inMsg.contains("?msg=Exit")) {
                        server.close();
                    } else if (inMsg.contains("?msg=")
                            && !in.readLine().contains("?msg=Hello")
                            && !in.readLine().contains("?msg=Exit")) {
                        out.write("What.".getBytes());
                    }
                    for (String str = in.readLine(); str != null
                            && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}
