package cn.nexuslink.mapper;


import cn.nexuslink.pojo.VerifyDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by xfh on 2018/12/28.
 */
@Mapper
@Component
public interface VerifyMapper {

    String getVerify(String tele);

    boolean insertVerify(VerifyDO verifyDO);

    boolean updateVerify(VerifyDO verifyDO);

}
