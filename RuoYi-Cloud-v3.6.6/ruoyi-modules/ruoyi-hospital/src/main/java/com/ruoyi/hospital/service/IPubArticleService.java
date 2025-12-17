package com.ruoyi.hospital.service;

import java.util.List;
import com.ruoyi.hospital.domain.PubArticle;

/**
 * 宣传中心Service接口
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public interface IPubArticleService 
{
    /**
     * 查询宣传中心
     * 
     * @param id 宣传中心主键
     * @return 宣传中心
     */
    public PubArticle selectPubArticleById(Long id);

    /**
     * 查询宣传中心列表
     * 
     * @param pubArticle 宣传中心
     * @return 宣传中心集合
     */
    public List<PubArticle> selectPubArticleList(PubArticle pubArticle);

    /**
     * 新增宣传中心
     * 
     * @param pubArticle 宣传中心
     * @return 结果
     */
    public int insertPubArticle(PubArticle pubArticle);

    /**
     * 修改宣传中心
     * 
     * @param pubArticle 宣传中心
     * @return 结果
     */
    public int updatePubArticle(PubArticle pubArticle);

    /**
     * 批量删除宣传中心
     * 
     * @param ids 需要删除的宣传中心主键集合
     * @return 结果
     */
    public int deletePubArticleByIds(Long[] ids);

    /**
     * 删除宣传中心信息
     * 
     * @param id 宣传中心主键
     * @return 结果
     */
    public int deletePubArticleById(Long id);
}
