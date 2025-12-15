package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 职称管理对象 sys_job_title
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
public class SysJobTitle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 职称名称，如“主任医师”“主治医师”等 */
    @Excel(name = "职称名称，如“主任医师”“主治医师”等")
    private String name;

    /** 薪资 */
    @Excel(name = "薪资")
    private BigDecimal registrationFee;

    /** 工作天数 */
    @Excel(name = "工作天数")
    private String workDays;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("registrationFee", getRegistrationFee())
            .append("workDays", getWorkDays())
            .toString();
    }
}
