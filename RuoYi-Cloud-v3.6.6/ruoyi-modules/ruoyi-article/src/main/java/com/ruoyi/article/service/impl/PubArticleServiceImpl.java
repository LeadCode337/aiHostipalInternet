package com.ruoyi.article.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.article.mapper.PubArticleMapper;
import com.ruoyi.article.domain.PubArticle;
import com.ruoyi.article.service.IPubArticleService;

/**
 * 健康宣教文章Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class PubArticleServiceImpl implements IPubArticleService 
{
    @Autowired
    private PubArticleMapper pubArticleMapper;

    /**
     * 查询健康宣教文章
     * 
     * @param id 健康宣教文章主键
     * @return 健康宣教文章
     */
    @Override
    public PubArticle selectPubArticleById(Long id)
    {
        return pubArticleMapper.selectPubArticleById(id);
    }

    /**
     * 查询健康宣教文章列表
     * 
     * @param pubArticle 健康宣教文章
     * @return 健康宣教文章
     */
    @Override
    public List<PubArticle> selectPubArticleList(PubArticle pubArticle)
    {
        return pubArticleMapper.selectPubArticleList(pubArticle);
    }

    /**
     * 新增健康宣教文章
     * 
     * @param pubArticle 健康宣教文章
     * @return 结果
     */
    @Override
    public int insertPubArticle(PubArticle pubArticle)
    {
        return pubArticleMapper.insertPubArticle(pubArticle);
    }

    /**
     * 修改健康宣教文章
     * 
     * @param pubArticle 健康宣教文章
     * @return 结果
     */
    @Override
    public int updatePubArticle(PubArticle pubArticle)
    {
        return pubArticleMapper.updatePubArticle(pubArticle);
    }

    /**
     * 批量删除健康宣教文章
     * 
     * @param ids 需要删除的健康宣教文章主键
     * @return 结果
     */
    @Override
    public int deletePubArticleByIds(Long[] ids)
    {
        return pubArticleMapper.deletePubArticleByIds(ids);
    }

    /**
     * 删除健康宣教文章信息
     * 
     * @param id 健康宣教文章主键
     * @return 结果
     */
    @Override
    public int deletePubArticleById(Long id)
    {
        return pubArticleMapper.deletePubArticleById(id);
    }
}
