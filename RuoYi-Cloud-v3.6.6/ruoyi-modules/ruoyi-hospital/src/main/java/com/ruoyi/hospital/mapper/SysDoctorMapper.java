package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.SysDoctor;

/**
 * 医生档案Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
public interface SysDoctorMapper 
{
    /**
     * 查询医生档案
     * 
     * @param id 医生档案主键
     * @return 医生档案
     */
    public SysDoctor selectSysDoctorById(Long id);

    /**
     * 查询医生档案列表
     * 
     * @param sysDoctor 医生档案
     * @return 医生档案集合
     */
    public List<SysDoctor> selectSysDoctorList(SysDoctor sysDoctor);

    /**
     * 新增医生档案
     * 
     * @param sysDoctor 医生档案
     * @return 结果
     */
    public int insertSysDoctor(SysDoctor sysDoctor);

    /**
     * 修改医生档案
     * 
     * @param sysDoctor 医生档案
     * @return 结果
     */
    public int updateSysDoctor(SysDoctor sysDoctor);

    /**
     * 删除医生档案
     * 
     * @param id 医生档案主键
     * @return 结果
     */
    public int deleteSysDoctorById(Long id);

    /**
     * 批量删除医生档案
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDoctorByIds(Long[] ids);
}
