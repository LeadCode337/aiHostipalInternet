package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.PubBannerMapper;
import com.ruoyi.hospital.domain.PubBanner;
import com.ruoyi.hospital.service.IPubBannerService;

/**
 * 首页轮播图Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@Service
public class PubBannerServiceImpl implements IPubBannerService 
{
    @Autowired
    private PubBannerMapper pubBannerMapper;

    /**
     * 查询首页轮播图
     * 
     * @param id 首页轮播图主键
     * @return 首页轮播图
     */
    @Override
    public PubBanner selectPubBannerById(Long id)
    {
        return pubBannerMapper.selectPubBannerById(id);
    }

    /**
     * 查询首页轮播图列表
     * 
     * @param pubBanner 首页轮播图
     * @return 首页轮播图
     */
    @Override
    public List<PubBanner> selectPubBannerList(PubBanner pubBanner)
    {
        return pubBannerMapper.selectPubBannerList(pubBanner);
    }

    /**
     * 新增首页轮播图
     * 
     * @param pubBanner 首页轮播图
     * @return 结果
     */
    @Override
    public int insertPubBanner(PubBanner pubBanner)
    {
        return pubBannerMapper.insertPubBanner(pubBanner);
    }

    /**
     * 修改首页轮播图
     * 
     * @param pubBanner 首页轮播图
     * @return 结果
     */
    @Override
    public int updatePubBanner(PubBanner pubBanner)
    {
        return pubBannerMapper.updatePubBanner(pubBanner);
    }

    /**
     * 批量删除首页轮播图
     * 
     * @param ids 需要删除的首页轮播图主键
     * @return 结果
     */
    @Override
    public int deletePubBannerByIds(Long[] ids)
    {
        return pubBannerMapper.deletePubBannerByIds(ids);
    }

    /**
     * 删除首页轮播图信息
     * 
     * @param id 首页轮播图主键
     * @return 结果
     */
    @Override
    public int deletePubBannerById(Long id)
    {
        return pubBannerMapper.deletePubBannerById(id);
    }
}
