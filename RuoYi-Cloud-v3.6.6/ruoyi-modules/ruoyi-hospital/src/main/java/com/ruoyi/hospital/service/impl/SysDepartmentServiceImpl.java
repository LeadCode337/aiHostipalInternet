package com.ruoyi.hospital.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.SysDepartmentMapper;
import com.ruoyi.hospital.domain.SysDepartment;
import com.ruoyi.hospital.service.ISysDepartmentService;

/**
 * 科室Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
@Service
public class SysDepartmentServiceImpl implements ISysDepartmentService 
{
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    /**
     * 查询科室
     * 
     * @param id 科室主键
     * @return 科室
     */
    @Override
    public SysDepartment selectSysDepartmentById(Long id)
    {
        return sysDepartmentMapper.selectSysDepartmentById(id);
    }

    /**
     * 查询科室列表
     * 
     * @param sysDepartment 科室
     * @return 科室
     */
    @Override
    public List<SysDepartment> selectSysDepartmentList(SysDepartment sysDepartment)
    {
        return sysDepartmentMapper.selectSysDepartmentList(sysDepartment);
    }

    /**
     * 新增科室
     * 
     * @param sysDepartment 科室
     * @return 结果
     */
    @Override
    public int insertSysDepartment(SysDepartment sysDepartment)
    {
        sysDepartment.setCreateTime(DateUtils.getNowDate());
        return sysDepartmentMapper.insertSysDepartment(sysDepartment);
    }

    /**
     * 修改科室
     * 
     * @param sysDepartment 科室
     * @return 结果
     */
    @Override
    public int updateSysDepartment(SysDepartment sysDepartment)
    {
        sysDepartment.setUpdateTime(DateUtils.getNowDate());
        return sysDepartmentMapper.updateSysDepartment(sysDepartment);
    }

    /**
     * 批量删除科室
     * 
     * @param ids 需要删除的科室主键
     * @return 结果
     */
    @Override
    public int deleteSysDepartmentByIds(Long[] ids)
    {
        return sysDepartmentMapper.deleteSysDepartmentByIds(ids);
    }

    /**
     * 删除科室信息
     * 
     * @param id 科室主键
     * @return 结果
     */
    @Override
    public int deleteSysDepartmentById(Long id)
    {
        return sysDepartmentMapper.deleteSysDepartmentById(id);
    }
}
