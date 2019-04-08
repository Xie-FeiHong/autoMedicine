package cn.nexuslink.controller;

import cn.nexuslink.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by xfh on 2018/12/20.
 */

@Slf4j
@Controller
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    /**
     * 获取药物介绍
     * @return
     */
    @RequestMapping("/getMedicines")
    @ResponseBody
    public Map<String, Object> getMedicines() {

        Map<String, Object> resultMap = medicineService.getMedicines();
        return resultMap;
    }

    public void testLog(){
        log.info("输出信息");
        log.error("错误信息");
    }
}
