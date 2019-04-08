package cn.nexuslink.socket;

import cn.nexuslink.constant.Constant;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


@Slf4j
public class TomcatServer implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
//    final boolean exit=true;
        Thread thread =new Thread(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                try {
                    ServerSocket server=new ServerSocket(8090);
                    while(true){
                        Socket serverSocket =server.accept();//用新线程来开启是为了防止Tomcat监听器启动时被堵塞。
                        BufferedReader bufIn =
                                new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                        String label = bufIn.readLine();
                        log.info("获取的编号为: " + label);
                        Constant.SOCKET_MAP.put(Integer.valueOf(label), serverSocket);
                        OutputStream OS = serverSocket.getOutputStream();
                        PrintWriter pw = new PrintWriter(OS);
                        pw.write("收到客户端消息");
                        pw.flush();
                        serverSocket = null;
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
