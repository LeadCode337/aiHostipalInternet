package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysJobTitle;

/**
 * 职称管理Service接口
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
public interface ISysJobTitleService 
{
    /**
     * 查询职称管理
     * 
     * @param id 职称管理主键
     * @return 职称管理
     */
    public SysJobTitle selectSysJobTitleById(Long id);

    /**
     * 查询职称管理列表
     * 
     * @param sysJobTitle 职称管理
     * @return 职称管理集合
     */
    public List<SysJobTitle> selectSysJobTitleList(SysJobTitle sysJobTitle);

    /**
     * 新增职称管理
     * 
     * @param sysJobTitle 职称管理
     * @return 结果
     */
    public int insertSysJobTitle(SysJobTitle sysJobTitle);

    /**
     * 修改职称管理
     * 
     * @param sysJobTitle 职称管理
     * @return 结果
     */
    public int updateSysJobTitle(SysJobTitle sysJobTitle);

    /**
     * 批量删除职称管理
     * 
     * @param ids 需要删除的职称管理主键集合
     * @return 结果
     */
    public int deleteSysJobTitleByIds(Long[] ids);

    /**
     * 删除职称管理信息
     * 
     * @param id 职称管理主键
     * @return 结果
     */
    public int deleteSysJobTitleById(Long id);
}
