package cn.nexuslink.controller;


import cn.nexuslink.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xfh on 2018/12/27.
 */
@Controller
public class VerifyController {

    @Autowired
    private VerifyService verifyService;

    @RequestMapping("/verify")
    @ResponseBody
    public Map<String, Object> verify(@RequestParam(value = "tele") String tele) {

        // 判断tele是否正确
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "电话号码格式不正确");
            return resultMap;
        }
        // 发送验证短信
        return verifyService.sendVerify(tele);
    }

}
