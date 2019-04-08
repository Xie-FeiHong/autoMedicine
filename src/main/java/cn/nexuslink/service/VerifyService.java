package cn.nexuslink.service;

import java.util.Map;

/**
 * Created by xfh on 2018/12/27.
 */
public interface VerifyService {
    /**
     * 发送验证码
     * @param tele 电话号码
     * @return
     */
    Map<String, Object> sendVerify(String tele);
}
