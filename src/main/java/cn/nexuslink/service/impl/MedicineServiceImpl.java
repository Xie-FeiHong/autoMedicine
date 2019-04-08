package cn.nexuslink.service.impl;

import cn.nexuslink.constant.Constant;
import cn.nexuslink.mapper.MedicineMapper;
import cn.nexuslink.pojo.Medicine;
import cn.nexuslink.pojo.MedicineDO;
import cn.nexuslink.pojo.PrescriptionDTO;
import cn.nexuslink.pojo.PrescriptionMedicineDO;
import cn.nexuslink.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xfh on 2018/12/30.
 */
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineMapper medicineMapper;

    public Map<String, Object> getMedicines() {
        Map<String, Object> resultMap = new HashMap<>();
        // 获取药材信息
        List<MedicineDO> resultrList = medicineMapper.getMedicineList();
        //
        if (resultrList == null || resultrList.size() == 0) {
            resultMap.put("result", false);
            return resultMap;
        } else {
            ArrayList<Medicine> list = new ArrayList<>();
            Iterator<MedicineDO> it = resultrList.iterator();
            while(it.hasNext()) {
                MedicineDO medicineDO = it.next();
                Medicine medicine = new Medicine();
                medicine.setIntro(medicineDO.getIntro());
                medicine.setMedicineName(medicineDO.getMedicineName());
                medicine.setNotice(medicineDO.getNotice());
                medicine.setMedicinePic(Constant.GET_PIC_URL + medicineDO.getMedicinePic());
                list.add(medicine);
            }
            resultMap.put("result", true);
            resultMap.put("medicineList", list);
            return resultMap;
        }
    }

    public ArrayList<PrescriptionDTO> getPrescription(ArrayList<PrescriptionMedicineDO> medicineIdList){
        ArrayList<PrescriptionDTO> prescription = new ArrayList<>();
        Iterator<PrescriptionMedicineDO> it = medicineIdList.iterator();
        while (it.hasNext()) {
            PrescriptionMedicineDO prescriptionMedicineDO = it.next();
            // System.out.println("prescriptionMedicineDO =" + prescriptionMedicineDO);
            String medicineName = medicineMapper.getName(prescriptionMedicineDO.getMedicineId());
            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
            prescriptionDTO.setMedicineName(medicineName);
            prescriptionDTO.setWeight(prescriptionMedicineDO.getWeight());
            prescription.add(prescriptionDTO);
        }
        return prescription;
    }
}
