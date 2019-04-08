package cn.nexuslink.mapper;

import cn.nexuslink.pojo.PrescriptionMedicineDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by xfh on 2019/2/24.
 */
@Mapper
@Component
public interface PrescriptionMedicineMapper {

    void insertPrescriptionMedicines(ArrayList<PrescriptionMedicineDO> prescriptionMedicineDOArrayList);

    ArrayList<PrescriptionMedicineDO> getMedicineIds(Long prescriptionId);
}
