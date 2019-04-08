package cn.nexuslink.service.impl;

import cn.nexuslink.constant.Constant;
import cn.nexuslink.mapper.*;
import cn.nexuslink.pojo.*;
import cn.nexuslink.service.MedicineService;
import cn.nexuslink.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xfh on 2018/12/27.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerifyMapper verifyMapper;
    @Autowired
    private PrescriptionUserMapper prescriptionUserMapper;
    @Autowired
    private PrescriptionMapper prescriptionMapper;
    @Autowired
    private PrescriptionMedicineMapper prescriptionMedicineMapper;
    @Autowired
    private MedicineService medicineService;


    /**
     * 电话号码是否已经注册
     * @param tele 电话号码
     * @return
     */
    public boolean isExist(String tele) {
        // 查找数据库
        UserDO userDO =  userMapper.getUser(tele);
        if (userDO == null || userDO.getUserName() ==null || userDO.getUserName().length() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 用户注册
     * @param userSignDTO 用户注册的信息
     * @return
     */
    public Map<String, Object> userSign(UserSignDTO userSignDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        // 判断是否已经注册
        if (isExist(userSignDTO.getTele())) {
            resultMap.put("result", false);
            resultMap.put("reason", "该用户已经注册了");
            return resultMap;
        }
        // 判断验证妈的是否正确
        String oldCode = verifyMapper.getVerify(userSignDTO.getTele());
        if (!userSignDTO.getCode().equals(oldCode)) {
            resultMap.put("result", false);
            resultMap.put("reason", "验证码错误");
            return resultMap;
        }
        // 插入
        UserDO userDO = new UserDO();
        userDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        userDO.setGmtModified(new Timestamp(System.currentTimeMillis()));
        userDO.setPwd(userSignDTO.getPassword());
        userDO.setTele(userSignDTO.getTele());
        userDO.setUserName(userSignDTO.getName());
        userDO.setPicUrl(Constant.PIC_URK);
        userMapper.insertUser(userDO);
        resultMap.put("result", true);
        return resultMap;
    }

    /**
     * 用户登录
     * @param tele 电话号码
     * @param pwd 密码
     * @return
     */
    public Map<String, Object> userLogin(String tele, String pwd) {
        Map<String, Object> resultMap = new HashMap<>();
        // 获取用户
        UserDO userDO = userMapper.getUser(tele);
        if (userDO == null || userDO.getPwd() == null || userDO.getPwd().length() <= 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "用户不存在，请先注册");
            return resultMap;
        }
        if (userDO.getPwd().equals(pwd)) {
            resultMap.put("result", true);
            resultMap.put("name",userDO.getUserName());
            return resultMap;
        }
        resultMap.put("result", false);
        resultMap.put("reason", "密码错误");
        return resultMap;
    }

    /**
     * 上传用户头像
     * @param tele
     * @param userPic
     * @return
     */
    public Map<String, Object> uploadPic(String tele, MultipartFile userPic) {
        Map<String, Object> resultMap = new HashMap<>();
        // 判读用户是否存在
        if (!isExist(tele)) {
            resultMap.put("result", false);
            resultMap.put("reason", "用户不存在");
            return resultMap;
        }

        String originFileName = userPic.getOriginalFilename();
        String newPicName = UUID.randomUUID() + originFileName.substring(originFileName.lastIndexOf("."));
        String picPath = Constant.UPLOAD_PIC_ADDRESS + newPicName;
        File newFile = new File(picPath);
        try {
            userPic.transferTo(newFile); // 写入文件
        }catch (Exception e) {
            log.error("上传用户头像失败：\n\r picpath = " + picPath + "; \t tele = " + tele);
            e.printStackTrace();
        }
        // 存入数据库，更新用户的头像
        userMapper.updateUserPic(newPicName, tele);
        resultMap.put("result", true);
        resultMap.put("userPicUrl", Constant.GET_PIC_URL+newPicName);
        return resultMap;
    }

    /**
     * 获取用户头像
     * @param tele
     * @return
     */
    public Map<String, Object> getUserPic(String tele) {
        Map<String, Object> resultMap = new HashMap<>();
        if (!isExist(tele)) {
            resultMap.put("result", false);
            resultMap.put("reason", "该用户不存在");
            return resultMap;
        }

        String picUrl = userMapper.getUserPic(tele);

        resultMap.put("result", true);
        resultMap.put("userPicUrl", Constant.GET_PIC_URL + picUrl);
        return resultMap;
    }

    /**
     * 修改密码
     * @param tele
     * @param newPassword
     * @return
     */
    public Map<String, Object> updatePassword(String tele, String newPassword) {
        Map<String, Object> resultMap = new HashMap<>();
        if (!isExist(tele)) {
            resultMap.put("result", false);
            resultMap.put("reason", "该用户不存在");
            return resultMap;
        }

        userMapper.updatePassword(tele, newPassword);
        resultMap.put("result", true);
        return resultMap;
    }

    /**
     * 修改用户昵称
     * @param tele
     * @param newName
     * @return
     */
    public Map<String, Object> changeUserName(String tele, String newName) {
        Map<String, Object> resultMap = new HashMap<>();
        if (!isExist(tele)){
            resultMap.put("result", false);
            resultMap.put("reason", "该用户不存在");
            return resultMap;
        }
        userMapper.updateUserName(tele, newName);
        resultMap.put("result", true);
        return resultMap;
    }

    @Override
    public Map<String, Object> getPrescriptionHistory(String tele) {
        Map<String, Object> resultMap = new HashMap<>();
        // 判断用户是不是存在
        if (!isExist(tele)) {
            resultMap.put("result", false);
            resultMap.put("reason", "用户不存在，请检查用户电话");
            return resultMap;
        }
        // 获取每个prescriptionId
        List<PrescriptionUserDO> list = prescriptionUserMapper.getPrescriptionIds(tele);
        ArrayList<PrescriptionHistoryDTO> resultList = new ArrayList<>();
        Iterator<PrescriptionUserDO> it = list.iterator();
        while (it.hasNext()) {
            PrescriptionUserDO prescriptionUserDO = it.next();
            PrescriptionHistoryDTO prescriptionHistoryDTO = new PrescriptionHistoryDTO();
            // System.out.println("time" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prescriptionUserDO.getGmtCreate()));
            prescriptionHistoryDTO.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prescriptionUserDO.getGmtCreate()));
            prescriptionHistoryDTO.setPrice(prescriptionMapper.getPrice(prescriptionUserDO.getPrescriptionId()));
            ArrayList<PrescriptionMedicineDO> medicineIdList = prescriptionMedicineMapper.getMedicineIds(prescriptionUserDO.getPrescriptionId());
            prescriptionHistoryDTO.setPrescription(medicineService.getPrescription(medicineIdList));
            resultList.add(prescriptionHistoryDTO);
        }
        resultMap.put("result", true);
        resultMap.put("prescriptionList", resultList);
        return resultMap;
    }

}
