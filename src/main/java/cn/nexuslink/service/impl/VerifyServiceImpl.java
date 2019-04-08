package cn.nexuslink.service.impl;

import cn.nexuslink.mapper.VerifyMapper;
import cn.nexuslink.pojo.VerifyDO;
import cn.nexuslink.service.UserService;
import cn.nexuslink.service.VerifyService;
import cn.nexuslink.util.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by xfh on 2018/12/27.
 */
@Service
@Component
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    private UserService userService;
    @Autowired
    private VerifyMapper verifyMapper;

    /**
     * 发送验证码
     * @param tele 电话号码
     * @return
     */
    public Map<String, Object> sendVerify(String tele) {
        Map<String, Object> resultMap = new HashMap<>();
//        // 先判断该电话号码是否已经注册
//        if (userService.isExist(tele)) {
//            resultMap.put("result", false);
//            resultMap.put("reason", "该号码已经注册");
//            return resultMap;
//        } // 不用判断是否注册，因为忘记密码后需要发送验证码修改密码
        // 生成6位验证码
        Random random = new Random();
        String code = new Integer(random.nextInt(900000) + 100000).toString();
        // 发送验证码
        System.out.println("tele="+ tele + "、code =" + code);
        if (!SendSms.sendSms2(tele, code)) {
            resultMap.put("result", false);
            resultMap.put("reason", "验证码发送失败，请检查你的电话号码");
            return resultMap;
        }
        // 把验证码存取数据库，便于后面查找
        String oldCode = verifyMapper.getVerify(tele);
        VerifyDO verifyDO = new VerifyDO();
        verifyDO.setCode(code);
        verifyDO.setTele(tele);
        verifyDO.setGmtModified(new Timestamp(System.currentTimeMillis()));
        if (oldCode ==null || code.length() < 6) {
            // 之前没有发送过验证码
            // 插入
            verifyDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            verifyMapper.insertVerify(verifyDO);
        } else {
            verifyMapper.updateVerify(verifyDO);
        }
        resultMap.put("result", true);  // 返回成功
        return resultMap;
    }
}
