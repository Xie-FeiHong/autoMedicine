package cn.nexuslink.controller;

import cn.nexuslink.constant.Constant;
import cn.nexuslink.socket.ReceiveSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xfh on 2019/1/20.
 */
@Controller
public class StartSocketController {

    @RequestMapping("/startSocket")
    @ResponseBody
    public Map<String, Object> start() {
        Map<String, Object> map = new HashMap<>();
        new Thread(new ReceiveSocket()).start();
        map.put("ok1", Constant.SOCKET_MAP);
        return  map;
    }

    @RequestMapping("/getMap")
    @ResponseBody
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("内容", Constant.SOCKET_MAP);
        return  map;
    }
}
