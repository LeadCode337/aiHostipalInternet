package com.ruoyi.hospital.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 药品标签对象 medicine_tag
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private Long id;

    /** 标签内容 */
    @Excel(name = "标签内容")
    private String content;

}
