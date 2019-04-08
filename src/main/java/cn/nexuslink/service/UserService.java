package cn.nexuslink.service;


import cn.nexuslink.pojo.UserDO;
import cn.nexuslink.pojo.UserSignDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by xfh on 2018/12/27.
 */
public interface UserService {

    /**
     * 判断电话码号是否已经注册了
     * @param tele 电话号码
     * @return
     */
    boolean isExist(String tele);

    /**
     * 用户注册
     * @param userSignDTO 用户注册的信息
     * @return
     */
    Map<String, Object> userSign(UserSignDTO userSignDTO);

    /**
     * 用户登录
     * @param tele 电话号码
     * @param password 密码
     * @return
     */
    Map<String, Object> userLogin(String tele, String password);

    /**
     * 上传用户头像
     * @param tele
     * @param userPic
     * @return
     */
    Map<String, Object> uploadPic(String tele, MultipartFile userPic);

    /**
     * 获取用户头像
     * @param tele
     * @return
     */
    Map<String, Object> getUserPic(String tele);

    /**
     * 修改用户密码
     * @param tele
     * @param newPassword
     * @return
     */
    Map<String, Object> updatePassword(String tele, String newPassword);

    /**
     * 修改用户昵称
     * @param tele
     * @param newName
     * @return
     */
    Map<String, Object> changeUserName(String tele, String newName);

    /**
     * 查看药方历史
     * @param tele
     * @return
     */
    Map<String, Object> getPrescriptionHistory(String tele);


}
