package com.ruoyi.hospital.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 科室对象 sys_department
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
public class SysDepartment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 科室名称/ID */
    @Excel(name = "科室名称/ID")
    private Long id;

    /** 科室名称，如“内科”“外科”等 */
    @Excel(name = "科室名称，如“内科”“外科”等")
    private String name;

    /** 地点 */
    @Excel(name = "地点")
    private String location;

    /** 会诊时间 */
    @Excel(name = "会诊时间")
    private String consultationHours;

    /** 平均就诊时长 */
    @Excel(name = "平均就诊时长")
    private Long avgVisitTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 软删除标识，0 为未删除，1 为已删除 */
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

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    public void setConsultationHours(String consultationHours) 
    {
        this.consultationHours = consultationHours;
    }

    public String getConsultationHours() 
    {
        return consultationHours;
    }

    public void setAvgVisitTime(Long avgVisitTime) 
    {
        this.avgVisitTime = avgVisitTime;
    }

    public Long getAvgVisitTime() 
    {
        return avgVisitTime;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("location", getLocation())
            .append("consultationHours", getConsultationHours())
            .append("avgVisitTime", getAvgVisitTime())
            .append("status", getStatus())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
