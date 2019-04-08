package cn.nexuslink.controller;

import cn.nexuslink.constant.Constant;
import cn.nexuslink.pojo.GetPrescriptionSelfDTO;
import cn.nexuslink.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by xfh on 2018/12/30.
 */
@Slf4j
@Controller
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * 用户自己输入药材和药材中重量
     * @param prescriptionSelfDTO
     * @return
     */
    @RequestMapping("/getPrescriptionSelf")
    @ResponseBody
    public Map<String, Object> getPrescriptionSelf(GetPrescriptionSelfDTO prescriptionSelfDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        if (prescriptionSelfDTO == null || prescriptionSelfDTO.getLabel() == null || prescriptionSelfDTO.getLabel().length() <= 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "参数label不能为空");
            return resultMap;
        }
        if (prescriptionSelfDTO.getPrescription() == null || prescriptionSelfDTO.getPrescription().size() <= 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "prescription参数不能为空");
            return resultMap;
        }
//        System.out.println("接受参数的结果：");
//        System.out.println("prescriptionSelfDTO = " + prescriptionSelfDTO);
        return prescriptionService.getPrescriptionBySelf(prescriptionSelfDTO);
    }

    /**
     * 系统推荐药方
     * @param disease
     * @return
     */
    @RequestMapping("/getPrescriptionAUTO")
    @ResponseBody
    public Map<String, Object> getPrescriptionAUTO(@RequestParam(value = "disease") String disease) {

        Map<String, Object> resultMap = new HashMap<>();
        if (disease == null || disease.length() == 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "参数disease不能为空");
            return resultMap;
        }
        return prescriptionService.getPrescriptionAUTOByDisease(disease);
    }

    /**
     * 确定抓药
     * @return
     */
    @RequestMapping("/getPrescriptionSure")
    @ResponseBody
    public Map<String, Object> getPrescriptionSure(@RequestParam(value = "tele") String tele,
                                                   @RequestParam(value = "addressId")Integer addressId,
                                                   @RequestParam(value = "prescriptionId") Integer prescriptionId
                                                   ) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() == 0 || addressId == null || prescriptionId == null) {
            resultMap.put("result", false);
            resultMap.put("reason", "请检查参数是不是为空");
            return resultMap;
        }
        return prescriptionService.getPrescriptionBeSure(tele, addressId, prescriptionId);
    }


    @RequestMapping("/testSocket")
    @ResponseBody
    public Map<String, Object> testSocket(@RequestParam(value = "label") Integer label) {
        Map<String, Object> resultMap = new HashMap<>();
        if (label == null) {
            return  resultMap;
        }
        Set<Map.Entry<Integer, Socket>> set = Constant.SOCKET_MAP.entrySet();
        Iterator<Map.Entry<Integer, Socket>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Socket> entry = it.next();
            if (label.equals(entry.getKey())) {
                if (entry.getValue() == null) {
                    break;
                }
                try {
                    BufferedWriter bufout =
                            new BufferedWriter(new OutputStreamWriter(entry.getValue().getOutputStream(),"gbk"));
                    bufout.write("药名1-23;药名2-34;药名3-20");
                    bufout.newLine();
                    bufout.flush();
                    resultMap.put("已经发送", "ok");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }




//        // resultMap.put("全部", Constant.SelectionKey_MAP);
//        Set<Map.Entry<Integer, SelectionKey>> set = Constant.SelectionKey_MAP.entrySet();
//        Iterator<Map.Entry<Integer, SelectionKey>> it = set.iterator();
//        while (it.hasNext()) {
//            Map.Entry<Integer, SelectionKey> entry = it.next();
//            if (label.equals(entry.getKey())) {
//                try {
//                    send(entry.getValue(), "");
//                    resultMap.put("已经发送", "ok");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
        return resultMap;
    }


    /**
     *抓药
     * @param selectionKey
     * @throws IOException
     */
    public void send(SelectionKey selectionKey, String responseMsg) throws IOException {
        if (selectionKey == null || !selectionKey.isValid() ) {
            return;
        }
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        //发送数据缓冲区
        ByteBuffer sBuffer = ByteBuffer.allocate(1024);
        //选择器（叫监听器更准确些吧应该）
        Selector selector = Selector.open();
        int count = 0;
        if (selectionKey.isAcceptable()) {
            //每有客户端连接，即注册通信信道为可读
            serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel) selectionKey.channel();
            responseMsg = "药名1-23;药名2-34;药名3-20";
            System.out.println(responseMsg);
            //返回数据
            sBuffer = ByteBuffer.allocate(responseMsg.getBytes("UTF-8").length);
            sBuffer.put(responseMsg.getBytes("UTF-8"));
            sBuffer.flip();
            socketChannel.write(sBuffer);
        }
    }
}
