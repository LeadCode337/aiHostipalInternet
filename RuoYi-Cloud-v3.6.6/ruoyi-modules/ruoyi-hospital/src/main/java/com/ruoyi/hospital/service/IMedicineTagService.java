package com.ruoyi.hospital.service;

import java.util.List;
import com.ruoyi.hospital.domain.MedicineTag;

/**
 * 药品标签Service接口
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
public interface IMedicineTagService 
{
    /**
     * 查询药品标签
     * 
     * @param id 药品标签主键
     * @return 药品标签
     */
    public MedicineTag selectMedicineTagById(Long id);

    /**
     * 查询药品标签列表
     * 
     * @param medicineTag 药品标签
     * @return 药品标签集合
     */
    public List<MedicineTag> selectMedicineTagList(MedicineTag medicineTag);

    /**
     * 新增药品标签
     * 
     * @param medicineTag 药品标签
     * @return 结果
     */
    public int insertMedicineTag(MedicineTag medicineTag);

    /**
     * 修改药品标签
     * 
     * @param medicineTag 药品标签
     * @return 结果
     */
    public int updateMedicineTag(MedicineTag medicineTag);

    /**
     * 批量删除药品标签
     * 
     * @param ids 需要删除的药品标签主键集合
     * @return 结果
     */
    public int deleteMedicineTagByIds(Long[] ids);

    /**
     * 删除药品标签信息
     * 
     * @param id 药品标签主键
     * @return 结果
     */
    public int deleteMedicineTagById(Long id);
}
