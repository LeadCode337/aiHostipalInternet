package com.ruoyi.hospital.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 医生档案对象 sys_doctor
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
public class SysDoctor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医生唯一标识 */
    @Excel(name = "医生唯一标识")
    private Long id;

    /** 医生工号 */
    @Excel(name = "医生工号")
    private Long employeeId;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String name;

    /** 医生登录密码,默认密码123456 */
    private String password;

    /** 医生头像url地址 */
    @Excel(name = "医生头像url地址")
    private String avatar;

    /** 医生联系电话 */
    private String phone;

    /** 关联科室id,绑定所属科室 */
    private Long deptId;

    /** 关联职称id,绑定医生职称 */
    @Excel(name = "关联职称id,绑定医生职称")
    private Long jobTitleId;

    /** 医生资格证书材料的地址(医生能看到证书的详情) */
    private String licenseUrl;

    /** 医生入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "医生入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinDate;

    /** 医生在职状态,'0'在职,'1'停止,'2'离职 */
    @Excel(name = "医生在职状态,'0'在职,'1'停止,'2'离职")
    private String status;

    /** 软删除标识,'0'为未删除,'1'为已删除 */
    private String isDeleted;

    /** 医生身份证号 */
    private String idCard;

    /** 身份证的图片,路径 */
    private String idCardUrl;

    /** 医生擅长领域,用于前端展示 */
    private String specialty;

    /** 医生个人简介或寄语 */
    private String introduction;

    /** 医生服务评分,保留2位小数,默认5.00分 */
    private BigDecimal rating;

    /** 医生累计接诊量,如13000 */
    private Long consultCount;

    /** 工资表 */
    private Long salary;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setJobTitleId(Long jobTitleId) 
    {
        this.jobTitleId = jobTitleId;
    }

    public Long getJobTitleId() 
    {
        return jobTitleId;
    }

    public void setLicenseUrl(String licenseUrl) 
    {
        this.licenseUrl = licenseUrl;
    }

    public String getLicenseUrl() 
    {
        return licenseUrl;
    }

    public void setJoinDate(Date joinDate) 
    {
        this.joinDate = joinDate;
    }

    public Date getJoinDate() 
    {
        return joinDate;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
    }

    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    public void setIdCardUrl(String idCardUrl) 
    {
        this.idCardUrl = idCardUrl;
    }

    public String getIdCardUrl() 
    {
        return idCardUrl;
    }

    public void setSpecialty(String specialty) 
    {
        this.specialty = specialty;
    }

    public String getSpecialty() 
    {
        return specialty;
    }

    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }

    public void setRating(BigDecimal rating) 
    {
        this.rating = rating;
    }

    public BigDecimal getRating() 
    {
        return rating;
    }

    public void setConsultCount(Long consultCount) 
    {
        this.consultCount = consultCount;
    }

    public Long getConsultCount() 
    {
        return consultCount;
    }

    public void setSalary(Long salary) 
    {
        this.salary = salary;
    }

    public Long getSalary() 
    {
        return salary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("employeeId", getEmployeeId())
            .append("name", getName())
            .append("password", getPassword())
            .append("avatar", getAvatar())
            .append("phone", getPhone())
            .append("deptId", getDeptId())
            .append("jobTitleId", getJobTitleId())
            .append("licenseUrl", getLicenseUrl())
            .append("joinDate", getJoinDate())
            .append("status", getStatus())
            .append("isDeleted", getIsDeleted())
            .append("idCard", getIdCard())
            .append("idCardUrl", getIdCardUrl())
            .append("specialty", getSpecialty())
            .append("introduction", getIntroduction())
            .append("rating", getRating())
            .append("consultCount", getConsultCount())
            .append("salary", getSalary())
            .toString();
    }
}
