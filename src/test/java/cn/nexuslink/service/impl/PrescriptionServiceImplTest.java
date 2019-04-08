package cn.nexuslink.service.impl;

import cn.nexuslink.pojo.GetPrescriptionSelfDTO;
import cn.nexuslink.pojo.PrescriptionDO;
import cn.nexuslink.pojo.PrescriptionDTO;
import cn.nexuslink.service.PrescriptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xfh on 2019/2/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PrescriptionServiceImplTest {
    @Autowired
    private PrescriptionServiceImpl prescriptionServiceImpl;

    @Test
    public void getPrescriptionBeSure() throws Exception {
        Map<String, Object> map = prescriptionServiceImpl.getPrescriptionBeSure("15023876950",1,16);
        System.out.println("结果是");
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void testGetPrescriptionByDisease() throws Exception {
        Map<String, Object> map = prescriptionServiceImpl.getPrescriptionAUTOByDisease("辛温解表0");
        System.out.println("结果是");
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    @Test
    public void getPrescriptionBySelf() throws Exception {

        GetPrescriptionSelfDTO getPrescriptionSelfDTO = new GetPrescriptionSelfDTO();
        getPrescriptionSelfDTO.setFlag(false);
        getPrescriptionSelfDTO.setLabel("辛温解表2");
        ArrayList<PrescriptionDTO> prescriptionDTOArrayList = new ArrayList<>();
        PrescriptionDTO prescriptionDTO1 = new PrescriptionDTO();
        prescriptionDTO1.setMedicineName("麻黄");
        prescriptionDTO1.setWeight(2);
        prescriptionDTOArrayList.add(prescriptionDTO1);
        PrescriptionDTO prescriptionDTO2 = new PrescriptionDTO();
        prescriptionDTO2.setMedicineName("桂枝");
        prescriptionDTO2.setWeight(3);
        prescriptionDTOArrayList.add(prescriptionDTO2);
        PrescriptionDTO prescriptionDTO3 = new PrescriptionDTO();
        prescriptionDTO3.setMedicineName("紫苏");
        prescriptionDTO3.setWeight(5);
        prescriptionDTOArrayList.add(prescriptionDTO3);


        getPrescriptionSelfDTO.setPrescription(prescriptionDTOArrayList);


        Map<String, Object> resultMap = prescriptionServiceImpl.getPrescriptionBySelf(getPrescriptionSelfDTO);
        System.out.println("结果如下");
        resultMap.forEach((k, v) -> {
            System.out.println(k + ':' + v);
        });
    }

//    @Test
//    public void getPrescriptionId() throws Exception {
//        PrescriptionDO prescriptionDO = new PrescriptionDO();
//        prescriptionDO.setIsUserDefined((short) 1);
//        prescriptionDO.setDisease("辛温解表");
//        Integer id = prescriptionServiceImpl.getPrescriptionId(prescriptionDO);
//        if (id != null) {
//            System.out.println("id = " + id);
//        }
//    }

}