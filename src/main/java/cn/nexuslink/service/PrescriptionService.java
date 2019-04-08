package cn.nexuslink.service;

import cn.nexuslink.pojo.GetPrescriptionSelfDTO;

import java.util.Map;

/**
 * Created by xfh on 2019/2/23.
 */
public interface PrescriptionService {
    /**
     * 用户自定义药方
     * @param getPrescriptionSelfDTO
     * @return
     */
    Map<String, Object> getPrescriptionBySelf(GetPrescriptionSelfDTO getPrescriptionSelfDTO);

    /**
     * 系统推荐药方
     * @param disease
     * @return
     */
    Map<String, Object> getPrescriptionAUTOByDisease(String disease);

    Map<String, Object> getPrescriptionBeSure(String tele, Integer addressId, Integer prescriptionId);
}
