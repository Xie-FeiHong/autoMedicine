package cn.nexuslink.constant;

import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xfh on 2018/12/28.
 */
public class Constant {

     // 日志输出的位置
     //  "/usr/local/tomcat/apache-tomcat-8.5.23/autoMedicineLog/info.log";

    // 云服务器存图片的位置地址
    public static final String  UPLOAD_PIC_ADDRESS = "/usr/local/enrollPic/";

    // 云服务器访问图片的地址
    public static final String GET_PIC_URL = "http://120.78.51.133:8080/enrollPic/";

    public static final String PIC_URK = "k7.ico";


    // 本地存图片的的位置地址
    // public static final String  UPLOAD_PIC_ADDRESS = "F:\\JAVAProject\\NMID\\autoMedicine\\pic\\";

    // 本地服务器访问图片的地址 （本地测试图片比较麻烦，但其实也可以）
//    public static final String  GET_PIC_URL = "http://localhost:8080/enrollPic/";

    // 保存Socket对象
    public static final Map<Integer, Socket> SOCKET_MAP = new HashMap<>();
    public static final Map<Integer, SelectionKey> SelectionKey_MAP = new HashMap<>();

}
