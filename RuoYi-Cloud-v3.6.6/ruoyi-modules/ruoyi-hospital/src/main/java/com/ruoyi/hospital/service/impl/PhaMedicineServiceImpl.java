package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.PhaMedicineMapper;
import com.ruoyi.hospital.domain.PhaMedicine;
import com.ruoyi.hospital.service.IPhaMedicineService;

/**
 * 药品库存Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@Service
public class PhaMedicineServiceImpl implements IPhaMedicineService 
{
    @Autowired
    private PhaMedicineMapper phaMedicineMapper;

    /**
     * 查询药品库存
     * 
     * @param id 药品库存主键
     * @return 药品库存
     */
    @Override
    public PhaMedicine selectPhaMedicineById(Long id)
    {
        return phaMedicineMapper.selectPhaMedicineById(id);
    }

    /**
     * 查询药品库存列表
     * 
     * @param phaMedicine 药品库存
     * @return 药品库存
     */
    @Override
    public List<PhaMedicine> selectPhaMedicineList(PhaMedicine phaMedicine)
    {
        return phaMedicineMapper.selectPhaMedicineList(phaMedicine);
    }

    /**
     * 新增药品库存
     * 
     * @param phaMedicine 药品库存
     * @return 结果
     */
    @Override
    public int insertPhaMedicine(PhaMedicine phaMedicine)
    {
        return phaMedicineMapper.insertPhaMedicine(phaMedicine);
    }

    /**
     * 修改药品库存
     * 
     * @param phaMedicine 药品库存
     * @return 结果
     */
    @Override
    public int updatePhaMedicine(PhaMedicine phaMedicine)
    {
        return phaMedicineMapper.updatePhaMedicine(phaMedicine);
    }

    /**
     * 批量删除药品库存
     * 
     * @param ids 需要删除的药品库存主键
     * @return 结果
     */
    @Override
    public int deletePhaMedicineByIds(Long[] ids)
    {
        return phaMedicineMapper.deletePhaMedicineByIds(ids);
    }

    /**
     * 删除药品库存信息
     * 
     * @param id 药品库存主键
     * @return 结果
     */
    @Override
    public int deletePhaMedicineById(Long id)
    {
        return phaMedicineMapper.deletePhaMedicineById(id);
    }
}
