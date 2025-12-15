package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysJobTitleMapper;
import com.ruoyi.system.domain.SysJobTitle;
import com.ruoyi.system.service.ISysJobTitleService;

/**
 * 职称管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
@Service
public class SysJobTitleServiceImpl implements ISysJobTitleService 
{
    @Autowired
    private SysJobTitleMapper sysJobTitleMapper;

    /**
     * 查询职称管理
     * 
     * @param id 职称管理主键
     * @return 职称管理
     */
    @Override
    public SysJobTitle selectSysJobTitleById(Long id)
    {
        return sysJobTitleMapper.selectSysJobTitleById(id);
    }

    /**
     * 查询职称管理列表
     * 
     * @param sysJobTitle 职称管理
     * @return 职称管理
     */
    @Override
    public List<SysJobTitle> selectSysJobTitleList(SysJobTitle sysJobTitle)
    {
        return sysJobTitleMapper.selectSysJobTitleList(sysJobTitle);
    }

    /**
     * 新增职称管理
     * 
     * @param sysJobTitle 职称管理
     * @return 结果
     */
    @Override
    public int insertSysJobTitle(SysJobTitle sysJobTitle)
    {
        return sysJobTitleMapper.insertSysJobTitle(sysJobTitle);
    }

    /**
     * 修改职称管理
     * 
     * @param sysJobTitle 职称管理
     * @return 结果
     */
    @Override
    public int updateSysJobTitle(SysJobTitle sysJobTitle)
    {
        return sysJobTitleMapper.updateSysJobTitle(sysJobTitle);
    }

    /**
     * 批量删除职称管理
     * 
     * @param ids 需要删除的职称管理主键
     * @return 结果
     */
    @Override
    public int deleteSysJobTitleByIds(Long[] ids)
    {
        return sysJobTitleMapper.deleteSysJobTitleByIds(ids);
    }

    /**
     * 删除职称管理信息
     * 
     * @param id 职称管理主键
     * @return 结果
     */
    @Override
    public int deleteSysJobTitleById(Long id)
    {
        return sysJobTitleMapper.deleteSysJobTitleById(id);
    }
}
