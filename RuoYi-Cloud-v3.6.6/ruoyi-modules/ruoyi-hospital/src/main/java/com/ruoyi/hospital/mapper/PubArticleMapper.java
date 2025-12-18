package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.PubArticle;

/**
 * 宣传中心Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public interface PubArticleMapper 
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
     * 删除宣传中心
     * 
     * @param id 宣传中心主键
     * @return 结果
     */
    public int deletePubArticleById(Long id);

    /**
     * 批量删除宣传中心
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePubArticleByIds(Long[] ids);
}
