package com.ruoyi.hospital.service.impl;

import java.util.List;

import com.ruoyi.hospital.service.ISysDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.SysDoctorMapper;
import com.ruoyi.hospital.domain.SysDoctor;

/**
 * 医生档案Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@Service
public class SysDoctorServiceImpl implements ISysDoctorService
{
    @Autowired
    private SysDoctorMapper sysDoctorMapper;

    /**
     * 查询医生档案
     * 
     * @param id 医生档案主键
     * @return 医生档案
     */
    @Override
    public SysDoctor selectSysDoctorById(Long id)
    {
        return sysDoctorMapper.selectSysDoctorById(id);
    }

    /**
     * 查询医生档案列表
     * 
     * @param sysDoctor 医生档案
     * @return 医生档案
     */
    @Override
    public List<SysDoctor> selectSysDoctorList(SysDoctor sysDoctor)
    {
        return sysDoctorMapper.selectSysDoctorList(sysDoctor);
    }

    /**
     * 新增医生档案
     * 
     * @param sysDoctor 医生档案
     * @return 结果
     */
    @Override
    public int insertSysDoctor(SysDoctor sysDoctor)
    {
        return sysDoctorMapper.insertSysDoctor(sysDoctor);
    }

    /**
     * 修改医生档案
     * 
     * @param sysDoctor 医生档案
     * @return 结果
     */
    @Override
    public int updateSysDoctor(SysDoctor sysDoctor)
    {
        return sysDoctorMapper.updateSysDoctor(sysDoctor);
    }

    /**
     * 批量删除医生档案
     * 
     * @param ids 需要删除的医生档案主键
     * @return 结果
     */
    @Override
    public int deleteSysDoctorByIds(Long[] ids)
    {
        return sysDoctorMapper.deleteSysDoctorByIds(ids);
    }

    /**
     * 删除医生档案信息
     * 
     * @param id 医生档案主键
     * @return 结果
     */
    @Override
    public int deleteSysDoctorById(Long id)
    {
        return sysDoctorMapper.deleteSysDoctorById(id);
    }
}
