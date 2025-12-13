package com.ruoyi.article.service;

import java.util.List;
import com.ruoyi.article.domain.PubArticle;

/**
 * 健康宣教文章Service接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface IPubArticleService 
{
    /**
     * 查询健康宣教文章
     * 
     * @param id 健康宣教文章主键
     * @return 健康宣教文章
     */
    public PubArticle selectPubArticleById(Long id);

    /**
     * 查询健康宣教文章列表
     * 
     * @param pubArticle 健康宣教文章
     * @return 健康宣教文章集合
     */
    public List<PubArticle> selectPubArticleList(PubArticle pubArticle);

    /**
     * 新增健康宣教文章
     * 
     * @param pubArticle 健康宣教文章
     * @return 结果
     */
    public int insertPubArticle(PubArticle pubArticle);

    /**
     * 修改健康宣教文章
     * 
     * @param pubArticle 健康宣教文章
     * @return 结果
     */
    public int updatePubArticle(PubArticle pubArticle);

    /**
     * 批量删除健康宣教文章
     * 
     * @param ids 需要删除的健康宣教文章主键集合
     * @return 结果
     */
    public int deletePubArticleByIds(Long[] ids);

    /**
     * 删除健康宣教文章信息
     * 
     * @param id 健康宣教文章主键
     * @return 结果
     */
    public int deletePubArticleById(Long id);
}
