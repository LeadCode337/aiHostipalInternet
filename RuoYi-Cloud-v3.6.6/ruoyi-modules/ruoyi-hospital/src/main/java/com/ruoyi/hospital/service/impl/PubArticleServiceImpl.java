package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.PubArticleMapper;
import com.ruoyi.hospital.domain.PubArticle;
import com.ruoyi.hospital.service.IPubArticleService;

/**
 * 宣传中心Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
@Service
public class PubArticleServiceImpl implements IPubArticleService 
{
    @Autowired
    private PubArticleMapper pubArticleMapper;

    /**
     * 查询宣传中心
     * 
     * @param id 宣传中心主键
     * @return 宣传中心
     */
    @Override
    public PubArticle selectPubArticleById(Long id)
    {
        return pubArticleMapper.selectPubArticleById(id);
    }

    /**
     * 查询宣传中心列表
     * 
     * @param pubArticle 宣传中心
     * @return 宣传中心
     */
    @Override
    public List<PubArticle> selectPubArticleList(PubArticle pubArticle)
    {
        return pubArticleMapper.selectPubArticleList(pubArticle);
    }

    /**
     * 新增宣传中心
     * 
     * @param pubArticle 宣传中心
     * @return 结果
     */
    @Override
    public int insertPubArticle(PubArticle pubArticle)
    {
        return pubArticleMapper.insertPubArticle(pubArticle);
    }

    /**
     * 修改宣传中心
     * 
     * @param pubArticle 宣传中心
     * @return 结果
     */
    @Override
    public int updatePubArticle(PubArticle pubArticle)
    {
        return pubArticleMapper.updatePubArticle(pubArticle);
    }

    /**
     * 批量删除宣传中心
     * 
     * @param ids 需要删除的宣传中心主键
     * @return 结果
     */
    @Override
    public int deletePubArticleByIds(Long[] ids)
    {
        return pubArticleMapper.deletePubArticleByIds(ids);
    }

    /**
     * 删除宣传中心信息
     * 
     * @param id 宣传中心主键
     * @return 结果
     */
    @Override
    public int deletePubArticleById(Long id)
    {
        return pubArticleMapper.deletePubArticleById(id);
    }
}
