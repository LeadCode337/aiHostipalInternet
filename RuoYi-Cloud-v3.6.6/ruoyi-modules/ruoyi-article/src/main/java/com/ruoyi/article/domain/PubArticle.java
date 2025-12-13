package com.ruoyi.article.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 健康宣教文章对象 pub_article
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public class PubArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章唯一标识 */
    private Long id;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 文章详细内容 */
    @Excel(name = "文章详细内容")
    private String content;

    /** 文章分类 */
    @Excel(name = "文章分类")
    private String category;

    /** 文章状态('0'待审核,'1'已发布,'2'已下架) */
    @Excel(name = "文章状态('0'待审核,'1'已发布,'2'已下架)")
    private String status;

    /** 热推顺序,数值越小排序越靠前 */
    @Excel(name = "热推顺序,数值越小排序越靠前")
    private Long hotOrder;

    /** 点赞数量 */
    @Excel(name = "点赞数量")
    private Long likeNum;

    /** 收藏数量 */
    @Excel(name = "收藏数量")
    private Long saveNum;

    /** 文章上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "文章上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 文章下架日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "文章下架日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date removedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setHotOrder(Long hotOrder) 
    {
        this.hotOrder = hotOrder;
    }

    public Long getHotOrder() 
    {
        return hotOrder;
    }

    public void setLikeNum(Long likeNum) 
    {
        this.likeNum = likeNum;
    }

    public Long getLikeNum() 
    {
        return likeNum;
    }

    public void setSaveNum(Long saveNum) 
    {
        this.saveNum = saveNum;
    }

    public Long getSaveNum() 
    {
        return saveNum;
    }

    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    public void setRemovedTime(Date removedTime) 
    {
        this.removedTime = removedTime;
    }

    public Date getRemovedTime() 
    {
        return removedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("category", getCategory())
            .append("status", getStatus())
            .append("hotOrder", getHotOrder())
            .append("likeNum", getLikeNum())
            .append("saveNum", getSaveNum())
            .append("uploadTime", getUploadTime())
            .append("removedTime", getRemovedTime())
            .toString();
    }
}
