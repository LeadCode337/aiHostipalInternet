package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.PubBanner;

/**
 * 首页轮播图Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public interface PubBannerMapper 
{
    /**
     * 查询首页轮播图
     * 
     * @param id 首页轮播图主键
     * @return 首页轮播图
     */
    public PubBanner selectPubBannerById(Long id);

    /**
     * 查询首页轮播图列表
     * 
     * @param pubBanner 首页轮播图
     * @return 首页轮播图集合
     */
    public List<PubBanner> selectPubBannerList(PubBanner pubBanner);

    /**
     * 新增首页轮播图
     * 
     * @param pubBanner 首页轮播图
     * @return 结果
     */
    public int insertPubBanner(PubBanner pubBanner);

    /**
     * 修改首页轮播图
     * 
     * @param pubBanner 首页轮播图
     * @return 结果
     */
    public int updatePubBanner(PubBanner pubBanner);

    /**
     * 删除首页轮播图
     * 
     * @param id 首页轮播图主键
     * @return 结果
     */
    public int deletePubBannerById(Long id);

    /**
     * 批量删除首页轮播图
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePubBannerByIds(Long[] ids);
}
