package cn.nexuslink.mapper;

import cn.nexuslink.pojo.PrescriptionUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xfh on 2019/2/25.
 */
@Mapper
@Component
public interface PrescriptionUserMapper {
    void insertPrescriptionUser(PrescriptionUserDO prescriptionUserDO);

    List<PrescriptionUserDO> getPrescriptionIds(String tele);

}
