package cn.nexuslink.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xfh on 2018/12/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendSmsTest {


    @Autowired
    private SendSms sendSms;


    @Test
    public void sendSms2() throws Exception {
       SendSms.sendSms2("15023876950","123456");
    }

}