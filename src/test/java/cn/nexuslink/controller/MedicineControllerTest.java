package cn.nexuslink.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xfh on 2018/12/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineControllerTest {

    @Autowired
    private  MedicineController medicineController;


    @Test
    public void testLog() throws Exception {
        medicineController.testLog();

    }

}