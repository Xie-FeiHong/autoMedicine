package cn.nexuslink.controller;

import cn.nexuslink.constant.Constant;
import cn.nexuslink.pojo.HtmlPerson;
import cn.nexuslink.pojo.UserSignDTO;
import cn.nexuslink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xfh on 2018/12/28.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     *
     * @param tele
     * @param password
     * @param code     验证码
     * @param name     用户昵称
     * @return
     */
    @RequestMapping("/sign")
    @ResponseBody
    public Map<String, Object> sign(@RequestParam(value = "tele") String tele,
                                    @RequestParam(value = "password") String password,
                                    @RequestParam(value = "code") String code,
                                    @RequestParam(value = "name") String name
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "请检查你的电话号码");
            return resultMap;
        }
        if (password == null || password.length() <= 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "密码不能为空");
            return resultMap;
        }
        if (code == null || code.length() <= 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "验证码不能为空");
            return resultMap;
        }
        if (name == null || name.length() <= 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "昵称不能为空");
            return resultMap;
        }
        // 注册
        UserSignDTO userSignDTO = new UserSignDTO();
        userSignDTO.setCode(code);
        userSignDTO.setName(name);
        userSignDTO.setPassword(password);
        userSignDTO.setTele(tele);

        return userService.userSign(userSignDTO);
    }


    /**
     * 登录接口
     *
     * @param tele     电话号码
     * @param password 密码
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam(value = "tele") String tele, @RequestParam(value = "password") String password) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "请检查您的电话号码");
            return resultMap;
        }
        if (password == null || password.length() <= 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "密码不能为空");
            return resultMap;
        }
        return userService.userLogin(tele, password);
    }

    /**
     * 上传用户头像
     *
     * @param tele    电话号码
     * @param userPic 头像文件
     * @return
     */
    @RequestMapping("/uploadUserPic")
    @ResponseBody
    public Map<String, Object> uploadPic(@RequestParam(value = "tele") String tele, @RequestParam(value = "userPic") MultipartFile userPic) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "请检查你的电话号码");
            return resultMap;
        }
        if (userPic == null) {
            resultMap.put("result", false);
            resultMap.put("reason", "不能传空图片");
            return resultMap;
        }
        return userService.uploadPic(tele, userPic);
    }

    /**
     * 获取用户头像
     *
     * @param tele
     * @return
     */
    @RequestMapping("/getUserPic")
    @ResponseBody
    public Map<String, Object> getUserPic(@RequestParam(value = "tele") String tele) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "请输出正确的电话号码");
            return resultMap;
        }
        return userService.getUserPic(tele);
    }

    /**
     * 修改密码
     * @param tele
     * @param newPassword
     * @return
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam(value = "tele") String tele, @RequestParam(value = "newPassword") String newPassword) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "请输出正确的电话号码");
            return resultMap;
        }
        return userService.updatePassword(tele, newPassword);
    }


    /**
     * 修改用户名
     * @param tele
     * @param newName
     * @return
     */
    @RequestMapping("/changeName")
    @ResponseBody
    public Map<String, Object> changeName(@RequestParam(value = "tele") String tele, @RequestParam(value = "newName") String newName) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "请输出正确的电话号码");
            return resultMap;
        }
        return userService.changeUserName(tele, newName);
    }

    /**
     * 查看药方历史
     * @param tele
     * @return
     */
    @RequestMapping("/getPrescriptionHistory")
    @ResponseBody
    public Map<String, Object> getPrescriptionHistory(@RequestParam(value = "tele") String tele) {
        Map<String, Object> resultMap = new HashMap<>();
        if (tele == null || tele.length() != 11 || tele.charAt(0) != '1') {
            resultMap.put("result", false);
            resultMap.put("reason", "请输出正确的电话号码");
            return resultMap;
        }
        return userService.getPrescriptionHistory(tele);
    }

    @RequestMapping("/html5/testForm")
    @ResponseBody
    public Map<String, Object> testHtml5(HtmlPerson htmlPerson) {
        Map<String, Object> map = new HashMap<>();
        map.put("姓名", htmlPerson.getName());
        map.put("学号", htmlPerson.getCode());
        map.put("密码", htmlPerson.getPwd());
        map.put("性别", htmlPerson.getSex());
        map.put("学校", htmlPerson.getSchool());
        map.put("学院", htmlPerson.getXueYuan());
        map.put("专业", htmlPerson.getZhaunye());
        map.put("爱好", htmlPerson.getHobby());
        map.put("提交时间", htmlPerson.getDate());

        MultipartFile userPic = htmlPerson.getPhoto();
        String originFileName = userPic.getOriginalFilename();
        String newPicName = UUID.randomUUID() + originFileName.substring(originFileName.lastIndexOf("."));
        String picPath = Constant.UPLOAD_PIC_ADDRESS + newPicName;
        File newFile = new File(picPath);
        try {
            userPic.transferTo(newFile); // 写入文件
        }catch (Exception e) {
            // log.error("上传用户头像失败：\n\r picpath = " + picPath + "; \t tele = " + tele);
            e.printStackTrace();
        }
        map.put("照片", Constant.GET_PIC_URL+newPicName);
        return map;
    }

}
