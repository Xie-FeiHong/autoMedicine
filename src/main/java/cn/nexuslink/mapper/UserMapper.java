package cn.nexuslink.mapper;

import cn.nexuslink.pojo.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by xfh on 2018/12/22.
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 通过电话号码获取单个用户对象
     * @param tele 电耗号码
     * @return
     */
    UserDO getUser (String tele);

    /**
     * 用户注册
     * @param userDO 需要存入数据的信息
     */
    void insertUser(UserDO userDO);

    /**
     * 更新用户头像
     *
     * @param tele   电话号码
     * @param picUrl 新的头像url地址
     */
    void updateUserPic(@Param(value = "picUrl") String picUrl, @Param(value = "tele") String tele);


    String getUserPic(String tele);

    void updatePassword(@Param(value = "tele") String tele, @Param(value = "newPassword")String newPassword);


    void updateUserName(@Param(value = "tele") String tele, @Param(value = "newName")String newName);


}
