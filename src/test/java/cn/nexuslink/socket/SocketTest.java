package cn.nexuslink.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by xfh on 2019/1/19.
 */
public class SocketTest {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("120.78.51.133", 10009);
            //定义目的，将数据写入到Socket输出流，发给服务端
            BufferedWriter bufout =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //定义一个Socket读取流，读取服务端返回的信息
            BufferedReader bufIn =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufout.write("3");
            // bufout.newLine();

            bufout.flush();
            System.out.println("已经发给服务器了");
            int coutn = 0;
            while (true) {
                String line = null;
                line = bufIn.readLine();
                while (line != null) {
                    System.out.println("返回的结果：" + line);
                    if (coutn < 3) {

//                        if (coutn == 1) {
//                            bufout.write("22");
//                            bufout.write("exit");
//                            bufout.flush();
//                        }
//                        bufout.write("22");
//                        bufout.flush();
                        coutn++;
                    }
                    line = bufIn.readLine();

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
