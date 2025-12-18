package com.ruoyi.hospital.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 药品库存对象 pha_medicine
 *
 * @author ruoyi
 * @date 2025-12-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhaMedicine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private Long id;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String name;

    /** 药品类型 */
    @Excel(name = "药品类型")
    private String type;

    /** 关联药品标签表 */
    @Excel(name = "关联药品标签表")
    private Long medicineTagId;

    /** 药品售价 */
    @Excel(name = "药品售价")
    private BigDecimal price;

    /** 药品当前库存数量 */
    @Excel(name = "药品当前库存数量")
    private Long stock;

    /** 库存预警阀值 */
    @Excel(name = "库存预警阀值")
    private Long threshold;

    /** 药品生产厂商 */
    @Excel(name = "药品生产厂商")
    private String manufacturer;

    /** 药品状态 */
    @Excel(name = "药品状态")
    private String status;

    /** 图片 */
    @Excel(name = "图片")
    private String imageUrl;

    // 标签表的标签内容
    private String content;

}