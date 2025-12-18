package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.HosJobTitle;

/**
 * 医生职称Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public interface HosJobTitleMapper
{
    /**
     * 查询医生职称
     *
     * @param id 医生职称主键
     * @return 医生职称
     */
    public HosJobTitle selectHosJobTitleById(Long id);

    /**
     * 查询医生职称列表
     *
     * @param hosJobTitle 医生职称
     * @return 医生职称集合
     */
    public List<HosJobTitle> selectHosJobTitleList(HosJobTitle hosJobTitle);

    /**
     * 新增医生职称
     *
     * @param hosJobTitle 医生职称
     * @return 结果
     */
    public int insertHosJobTitle(HosJobTitle hosJobTitle);

    /**
     * 修改医生职称
     *
     * @param hosJobTitle 医生职称
     * @return 结果
     */
    public int updateHosJobTitle(HosJobTitle hosJobTitle);

    /**
     * 删除医生职称
     *
     * @param id 医生职称主键
     * @return 结果
     */
    public int deleteHosJobTitleById(Long id);

    /**
     * 批量删除医生职称
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHosJobTitleByIds(Long[] ids);
}
