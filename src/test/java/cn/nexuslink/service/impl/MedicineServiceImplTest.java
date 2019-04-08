package cn.nexuslink.service.impl;

import cn.nexuslink.service.MedicineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xfh on 2019/2/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineServiceImplTest {

    @Autowired
    private MedicineService medicineService;

    @Test
    public void getMedicines() throws Exception {
        Map<String, Object> map = medicineService.getMedicines();
        map.forEach((k, v) -> {
            System.out.println(k + ": " + v);

        });

    }

}