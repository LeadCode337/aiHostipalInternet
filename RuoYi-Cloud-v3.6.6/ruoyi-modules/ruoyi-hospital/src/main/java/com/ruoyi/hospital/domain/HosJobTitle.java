package com.ruoyi.hospital.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 医生职称对象 hos_job_title
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public class HosJobTitle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 职称id */
    private Long id;

    /** 职称名称 */
    @Excel(name = "职称名称")
    private String name;

    /** 薪资 */
    @Excel(name = "薪资")
    private BigDecimal registrationFee;

    /** 工作天数 */
    @Excel(name = "工作天数")
    private String workDays;

    /** 状态 */
    @Excel(name = "状态")
    private Integer isDeleted;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setRegistrationFee(BigDecimal registrationFee)
    {
        this.registrationFee = registrationFee;
    }

    public BigDecimal getRegistrationFee()
    {
        return registrationFee;
    }

    public void setWorkDays(String workDays)
    {
        this.workDays = workDays;
    }

    public String getWorkDays()
    {
        return workDays;
    }

    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted()
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("registrationFee", getRegistrationFee())
                .append("workDays", getWorkDays())
                .append("isDeleted", getIsDeleted())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
