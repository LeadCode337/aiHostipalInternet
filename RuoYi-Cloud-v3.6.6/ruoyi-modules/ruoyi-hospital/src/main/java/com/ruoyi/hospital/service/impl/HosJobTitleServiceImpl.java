package com.ruoyi.hospital.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.HosJobTitleMapper;
import com.ruoyi.hospital.domain.HosJobTitle;
import com.ruoyi.hospital.service.IHosJobTitleService;

/**
 * 医生职称Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class HosJobTitleServiceImpl implements IHosJobTitleService
{
    @Autowired
    private HosJobTitleMapper hosJobTitleMapper;

    /**
     * 查询医生职称
     *
     * @param id 医生职称主键
     * @return 医生职称
     */
    @Override
    public HosJobTitle selectHosJobTitleById(Long id)
    {
        return hosJobTitleMapper.selectHosJobTitleById(id);
    }

    /**
     * 查询医生职称列表
     *
     * @param hosJobTitle 医生职称
     * @return 医生职称
     */
    @Override
    public List<HosJobTitle> selectHosJobTitleList(HosJobTitle hosJobTitle)
    {
        return hosJobTitleMapper.selectHosJobTitleList(hosJobTitle);
    }

    /**
     * 新增医生职称
     *
     * @param hosJobTitle 医生职称
     * @return 结果
     */
    @Override
    public int insertHosJobTitle(HosJobTitle hosJobTitle)
    {
        hosJobTitle.setCreateTime(DateUtils.getNowDate());
        return hosJobTitleMapper.insertHosJobTitle(hosJobTitle);
    }

    /**
     * 修改医生职称
     *
     * @param hosJobTitle 医生职称
     * @return 结果
     */
    @Override
    public int updateHosJobTitle(HosJobTitle hosJobTitle)
    {
        hosJobTitle.setUpdateTime(DateUtils.getNowDate());
        return hosJobTitleMapper.updateHosJobTitle(hosJobTitle);
    }

    /**
     * 批量删除医生职称
     *
     * @param ids 需要删除的医生职称主键
     * @return 结果
     */
    @Override
    public int deleteHosJobTitleByIds(Long[] ids)
    {
        return hosJobTitleMapper.deleteHosJobTitleByIds(ids);
    }

    /**
     * 删除医生职称信息
     *
     * @param id 医生职称主键
     * @return 结果
     */
    @Override
    public int deleteHosJobTitleById(Long id)
    {
        return hosJobTitleMapper.deleteHosJobTitleById(id);
    }
}
