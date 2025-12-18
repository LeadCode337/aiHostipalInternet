package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.MedicineTagMapper;
import com.ruoyi.hospital.domain.MedicineTag;
import com.ruoyi.hospital.service.IMedicineTagService;

/**
 * 药品标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@Service
public class MedicineTagServiceImpl implements IMedicineTagService 
{
    @Autowired
    private MedicineTagMapper medicineTagMapper;

    /**
     * 查询药品标签
     * 
     * @param id 药品标签主键
     * @return 药品标签
     */
    @Override
    public MedicineTag selectMedicineTagById(Long id)
    {
        return medicineTagMapper.selectMedicineTagById(id);
    }

    /**
     * 查询药品标签列表
     * 
     * @param medicineTag 药品标签
     * @return 药品标签
     */
    @Override
    public List<MedicineTag> selectMedicineTagList(MedicineTag medicineTag)
    {
        return medicineTagMapper.selectMedicineTagList(medicineTag);
    }

    /**
     * 新增药品标签
     * 
     * @param medicineTag 药品标签
     * @return 结果
     */
    @Override
    public int insertMedicineTag(MedicineTag medicineTag)
    {
        return medicineTagMapper.insertMedicineTag(medicineTag);
    }

    /**
     * 修改药品标签
     * 
     * @param medicineTag 药品标签
     * @return 结果
     */
    @Override
    public int updateMedicineTag(MedicineTag medicineTag)
    {
        return medicineTagMapper.updateMedicineTag(medicineTag);
    }

    /**
     * 批量删除药品标签
     * 
     * @param ids 需要删除的药品标签主键
     * @return 结果
     */
    @Override
    public int deleteMedicineTagByIds(Long[] ids)
    {
        return medicineTagMapper.deleteMedicineTagByIds(ids);
    }

    /**
     * 删除药品标签信息
     * 
     * @param id 药品标签主键
     * @return 结果
     */
    @Override
    public int deleteMedicineTagById(Long id)
    {
        return medicineTagMapper.deleteMedicineTagById(id);
    }
}
