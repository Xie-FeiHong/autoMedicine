package cn.nexuslink.service;

import cn.nexuslink.pojo.PrescriptionDTO;
import cn.nexuslink.pojo.PrescriptionMedicineDO;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by xfh on 2018/12/20.
 */
public interface MedicineService {

    /**
     * 药材百科
     * @return
     */
    Map<String, Object> getMedicines();

    ArrayList<PrescriptionDTO> getPrescription(ArrayList<PrescriptionMedicineDO> medicineIdList);

}
