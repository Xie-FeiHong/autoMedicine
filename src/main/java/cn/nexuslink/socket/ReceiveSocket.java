package cn.nexuslink.socket;

import cn.nexuslink.constant.Constant;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xfh on 2019/1/19.
 */

@Slf4j
public class ReceiveSocket implements Runnable {
    public void run() {
        ServerSocket serverSocket = null;
        try {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(10009);
            }
            String label = null;
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    // 将Socket存在内存中
                    BufferedReader bufIn =
                            new BufferedReader(new InputStreamReader(socket.getInputStream(), "gbk"));
                    BufferedWriter bufOut =
                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "gbk"));

                    while (true) {
                        char[] buf = new char[1024];
                        int len  = 0;
                        // System.out.println("开始接了");
                        if ((len = bufIn.read(buf)) != -1) {
                            label = new String(buf,0,len);
                        } else {
                            continue;
                        }
                        // System.out.println("label = " + label);
                        if (label.contains("exit")) {
                            socket.close();
                            // System.out.println("退出");
                            break;
                        }
                        log.info("获取的编号为: " + label);
                        bufOut.write("已经保存");
                        bufOut.newLine();
                        bufOut.flush();
                        // 判断是不是存数字？
                        try {
                            Constant.SOCKET_MAP.put(Integer.valueOf(label.trim()), socket);
                        }catch (Exception ee) {
                            log.info("转成成数字失败： " + label);
                        }
                    }
                } catch (Exception e) {
                    log.info("转成成数字失败： " + label);
                }
                // bufIn.close();
            }
        } catch (IOException e) {
            log.error("端口8089被被占用, 监听端口失");
        }
    }

//    public static void main(String[] args) {
//        new Thread(new ReceiveSocket()).start();
//    }
}
