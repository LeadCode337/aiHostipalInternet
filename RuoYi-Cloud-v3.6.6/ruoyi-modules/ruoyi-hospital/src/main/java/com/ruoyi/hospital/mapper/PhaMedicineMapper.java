package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.PhaMedicine;

/**
 * 药品库存Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
public interface PhaMedicineMapper 
{
    /**
     * 查询药品库存
     * 
     * @param id 药品库存主键
     * @return 药品库存
     */
    public PhaMedicine selectPhaMedicineById(Long id);

    /**
     * 查询药品库存列表
     * 
     * @param phaMedicine 药品库存
     * @return 药品库存集合
     */
    public List<PhaMedicine> selectPhaMedicineList(PhaMedicine phaMedicine);

    /**
     * 新增药品库存
     * 
     * @param phaMedicine 药品库存
     * @return 结果
     */
    public int insertPhaMedicine(PhaMedicine phaMedicine);

    /**
     * 修改药品库存
     * 
     * @param phaMedicine 药品库存
     * @return 结果
     */
    public int updatePhaMedicine(PhaMedicine phaMedicine);

    /**
     * 删除药品库存
     * 
     * @param id 药品库存主键
     * @return 结果
     */
    public int deletePhaMedicineById(Long id);

    /**
     * 批量删除药品库存
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePhaMedicineByIds(Long[] ids);
}
