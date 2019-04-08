package cn.nexuslink.service.impl;

import cn.nexuslink.pojo.UserSignDTO;
import cn.nexuslink.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by xfh on 2018/12/28.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {



    @Autowired
    private UserService userService;

    @Test
    public void getPrescriptionHistory() throws Exception {
        Map<String, Object> resultMap = userService.getPrescriptionHistory("15023876950");
        resultMap.forEach((k,v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void userSignTest() throws Exception {
        UserSignDTO userSignDTO = new UserSignDTO();
        userSignDTO.setTele("15023876950");
        userSignDTO.setPassword("123456");
        userSignDTO.setName("谢飞宏");
        userSignDTO.setCode("689477");


        Map<String, Object> resultMap = userService.userSign(userSignDTO);
        resultMap.forEach((k,v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void userLoginTest() throws Exception {
        Map<String, Object> resultMap = userService.userLogin("15023876950", "111111");
        resultMap.forEach((k,v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void uploadPicTest() throws Exception {
        File file = new File("D:\\图片\\Solar\\6232\\k19.ico");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", fileInputStream);

        Map<String, Object> resultMap = userService.uploadPic("15023876950", multipartFile);
        resultMap.forEach((k,v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void getUserPicTest() throws Exception {

        Map<String, Object> resultMap = userService.getUserPic("15023876950");
        resultMap.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void updatePasswordTest() throws Exception {
        Map<String, Object> resultMap = userService.updatePassword("15023876950","111111");
        resultMap.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void changeUserNameTest() throws Exception {
        Map<String, Object> resultMap = userService.changeUserName("15023876950","xiefehong");
        resultMap.forEach((k, v)->{
            System.out.println(k + ":" + v);
        });
        Set<Map.Entry<String, Object>> set = resultMap.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getKey() == null){

            }

        }
    }



}