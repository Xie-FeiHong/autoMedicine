package cn.nexuslink.service.impl;

import cn.nexuslink.service.VerifyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xfh on 2018/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyServiceImplTest {


    @Autowired
    private VerifyService verifyService;


    @Test
    public void sendVerifyTest() throws Exception {
        Map<String, Object> resultMap =  verifyService.sendVerify("15023876950");
        resultMap.forEach((k, v)->{
            System.out.println("Item:" + k + "; Count:" + v);
        });
    }

}