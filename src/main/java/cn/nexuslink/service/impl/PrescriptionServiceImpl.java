package cn.nexuslink.service.impl;

import cn.nexuslink.constant.Constant;
import cn.nexuslink.mapper.MedicineMapper;
import cn.nexuslink.mapper.PrescriptionMapper;
import cn.nexuslink.mapper.PrescriptionMedicineMapper;
import cn.nexuslink.mapper.PrescriptionUserMapper;
import cn.nexuslink.pojo.*;
import cn.nexuslink.service.MedicineService;
import cn.nexuslink.service.PrescriptionService;
import cn.nexuslink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by xfh on 2019/2/23.
 */
@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private MedicineMapper medicineMapper;
    @Autowired
    private PrescriptionMapper prescriptionMapper;
    @Autowired
    private PrescriptionMedicineMapper prescriptionMedicineMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PrescriptionUserMapper prescriptionUserMapper;
    @Autowired
    private MedicineService medicineService;

    @Override
    public Map<String, Object> getPrescriptionBySelf(GetPrescriptionSelfDTO getPrescriptionSelfDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        // 先创建一个药方，并获取ID
        PrescriptionDO prescriptionDO = new PrescriptionDO();
        prescriptionDO.setDisease(getPrescriptionSelfDTO.getLabel());
        prescriptionDO.setIsUserDefined((short)1); // 1表示用户自己定义
        Long prescriptionId = getPrescriptionId(prescriptionDO);

        // 判断药材是不是存在, 并获取药材的id
        ArrayList<PrescriptionDTO> prescriptionList = getPrescriptionSelfDTO.getPrescription();
        ArrayList<PrescriptionMedicineDO> prescriptionMedicineDOList = new ArrayList<>();
        Iterator<PrescriptionDTO> it = prescriptionList.iterator();
        Boolean resultFlag = true;
        String reason = "";
        double price = 0;
        while (it.hasNext()) {
            PrescriptionDTO prescriptionDTO = it.next();

            // Long medicineId = medicineMapper.getId(prescriptionDTO.getMedicineName());
            if(prescriptionDTO.getMedicineName() == null) {
                continue;
            }
            MedicineDO medicineDO = medicineMapper.getMedicineByName(prescriptionDTO.getMedicineName());

            /// 以后这里还要判断 中药的量是否够
            if (medicineDO == null || medicineDO.getId() == null) {
                if (resultFlag) {
                    reason = reason + prescriptionDTO.getMedicineName();
                    resultFlag = false;
                } else {
                    reason = reason + "、" + prescriptionDTO.getMedicineName();
                }
                continue;
            }
            if (!resultFlag) {
                continue;
            }
            PrescriptionMedicineDO prescriptionMedicineDO = new PrescriptionMedicineDO();
            prescriptionMedicineDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            prescriptionMedicineDO.setGmtModified(new Timestamp(System.currentTimeMillis()));
            prescriptionMedicineDO.setMedicineId(medicineDO.getId());
            prescriptionMedicineDO.setPrescriptionId(prescriptionId);
            prescriptionMedicineDO.setWeight(prescriptionDTO.getWeight());
            price = price + (double)(medicineDO.getPrice() * prescriptionDTO.getWeight())/100;
            prescriptionMedicineDOList.add(prescriptionMedicineDO);
        }
        if (!resultFlag) {
            resultMap.put("result", resultFlag);
            resultMap.put("reason", reason + ", 药材不存在");
            // 删除已经创建的药方
            return resultMap;
        } else {

            if (!getPrescriptionSelfDTO.isFlag()) {
                // 需要判断该药方是不是有毒，获取其他问题
            }
            // 将药方存到数据库里
            prescriptionMedicineMapper.insertPrescriptionMedicines(prescriptionMedicineDOList);
            // 将价格存在数据中
            prescriptionMapper.updatePrice(prescriptionId, price);
            resultMap.put("result", true);
            resultMap.put("prescriptionId", prescriptionId);
            resultMap.put("price", price);
            String intor = "请饭后吃";
            resultMap.put("infor", intor);
            return resultMap;
        }
    }

    public Map<String, Object> getPrescriptionAUTOByDisease(String disease) {
        Map<String, Object> resultMap = new HashMap<>();
        ArrayList<Long> idList = prescriptionMapper.getPrescriptionIdByDisease(disease);
        if (idList == null || idList.size() == 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "系统中没有该病症对应的药方");
            return resultMap;
        }

        ArrayList<PrescriptionMedicineDO> medicineIdList = prescriptionMedicineMapper.getMedicineIds(idList.get(0));
        if (medicineIdList == null || medicineIdList.size() == 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "系统中没有改病症对应的药方");
            return resultMap;
        }

        ArrayList<PrescriptionDTO> prescription = medicineService.getPrescription(medicineIdList);
//        Iterator<PrescriptionMedicineDO> it = medicineIdList.iterator();
//        while (it.hasNext()) {
//            PrescriptionMedicineDO prescriptionMedicineDO = it.next();
//            // System.out.println("prescriptionMedicineDO =" + prescriptionMedicineDO);
//            String medicineName = medicineMapper.getName(prescriptionMedicineDO.getMedicineId());
//            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
//            prescriptionDTO.setMedicineName(medicineName);
//            prescriptionDTO.setWeight(prescriptionMedicineDO.getWeight());
//            prescription.add(prescriptionDTO);
//        }
        resultMap.put("result", true);
        resultMap.put("prescription", prescription);
        resultMap.put("prescriptionId", idList.get(0));
        resultMap.put("price", prescriptionMapper.getPrice(idList.get(0))); // 获取价格
        return resultMap;
    }

    public Map<String, Object> getPrescriptionBeSure(String tele, Integer addressId, Integer prescriptionId){
        Map<String, Object> resultMap = new HashMap<>();
        // 判断电话号码是不是存在
        if (!userService.isExist(tele)) {
            resultMap.put("result", false);
            resultMap.put("reason", "用户不存在");
            return resultMap;
        }
        // 判断药材是不是存在
        ArrayList<PrescriptionMedicineDO> medicineIdList = prescriptionMedicineMapper.getMedicineIds(new Long(prescriptionId));
        if (medicineIdList == null || medicineIdList.size() == 0) {
            resultMap.put("result", false);
            resultMap.put("reason", "请检查药方Id");
            return resultMap;
        }

        Iterator<PrescriptionMedicineDO> it = medicineIdList.iterator();
        Map<String, Integer> prescriptionMap = new HashMap<>();
        while (it.hasNext()) {
            PrescriptionMedicineDO prescriptionMedicineDO = it.next();
            String medicineName = medicineMapper.getName(prescriptionMedicineDO.getMedicineId());
            prescriptionMap.put(medicineName, prescriptionMedicineDO.getWeight());
        }

        // System.out.println("结果药方是" + prescriptionMap.toString());
        // 然后将数据发给机器
        Set<Map.Entry<Integer, Socket>> set = Constant.SOCKET_MAP.entrySet();
        Iterator<Map.Entry<Integer, Socket>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Socket> entry = iterator.next();
            if (addressId.equals(entry.getKey())) {
                if (entry.getValue() == null) {
                    break;
                }
                try {
                    BufferedWriter bufout =
                            new BufferedWriter(new OutputStreamWriter(entry.getValue().getOutputStream(),"gbk"));
                    bufout.write(prescriptionMap.toString());
                    bufout.newLine();
                    bufout.flush();
                    resultMap.put("已经发送", "ok");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        // 存到数据里面
        PrescriptionUserDO prescriptionUserDO = new PrescriptionUserDO();
        prescriptionUserDO.setPrescriptionId(new Long(prescriptionId));
        prescriptionUserDO.setTele(tele);
        prescriptionUserDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        prescriptionUserDO.setGmtModified(new Timestamp(System.currentTimeMillis()));
        prescriptionUserMapper.insertPrescriptionUser(prescriptionUserDO);
        resultMap.put("result", true);
        return resultMap;

    }

    /**
     * 创建一个药方并获取ID
     * @param prescriptionDO
     * @return
     */
    private Long getPrescriptionId(PrescriptionDO prescriptionDO) {
        prescriptionDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        prescriptionDO.setGmtModified(new Timestamp(System.currentTimeMillis()));
        prescriptionMapper.addPrescription(prescriptionDO);
        return prescriptionMapper.getPrescriptionId(prescriptionDO);
    }

}
