package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.SysDepartment;

/**
 * 科室Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
public interface SysDepartmentMapper 
{
    /**
     * 查询科室
     * 
     * @param id 科室主键
     * @return 科室
     */
    public SysDepartment selectSysDepartmentById(Long id);

    /**
     * 查询科室列表
     * 
     * @param sysDepartment 科室
     * @return 科室集合
     */
    public List<SysDepartment> selectSysDepartmentList(SysDepartment sysDepartment);

    /**
     * 新增科室
     * 
     * @param sysDepartment 科室
     * @return 结果
     */
    public int insertSysDepartment(SysDepartment sysDepartment);

    /**
     * 修改科室
     * 
     * @param sysDepartment 科室
     * @return 结果
     */
    public int updateSysDepartment(SysDepartment sysDepartment);

    /**
     * 删除科室
     * 
     * @param id 科室主键
     * @return 结果
     */
    public int deleteSysDepartmentById(Long id);

    /**
     * 批量删除科室
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDepartmentByIds(Long[] ids);
}
