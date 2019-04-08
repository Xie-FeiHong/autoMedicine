package cn.nexuslink.mapper;

import cn.nexuslink.pojo.MedicineDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xfh on 2018/12/30.
 */
@Mapper
@Component
public interface MedicineMapper {

    /**
     * 获取所有药材信息
     * @return
     */
    List<MedicineDO> getMedicineList();

    /**
     * 获取单个药材的药材ID，顺便判断该药材是否是否
     * @param name
     * @return
     */
    Long getId(String name);

    MedicineDO getMedicineByName(String medicineName);

    /**
     * 获取药材的名字
     * @param id
     * @return
     */
     String getName(Long id);
}
