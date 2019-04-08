package cn.nexuslink.mapper;

import cn.nexuslink.pojo.PrescriptionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by xfh on 2019/2/23.
 */
@Mapper
@Component
public interface PrescriptionMapper {

    void addPrescription(PrescriptionDO prescriptionDO);

    Long getPrescriptionId(PrescriptionDO prescriptionDO);

    ArrayList<Long> getPrescriptionIdByDisease(String disease);

    void updatePrice(@Param(value = "id") Long id, @Param(value = "price") Double price);

    Double getPrice(Long id);

}
