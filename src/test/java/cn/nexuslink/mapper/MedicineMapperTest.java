package cn.nexuslink.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xfh on 2019/2/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineMapperTest {

    @Autowired
    private MedicineMapper medicineMapper;

    @Test
    public void getId() throws Exception {
        Long result = medicineMapper.getId("麻黄w");
        if (result != null) {
            System.out.println("result = " + result);
        } else {
            System.out.println("结果为空");
        }
    }

}