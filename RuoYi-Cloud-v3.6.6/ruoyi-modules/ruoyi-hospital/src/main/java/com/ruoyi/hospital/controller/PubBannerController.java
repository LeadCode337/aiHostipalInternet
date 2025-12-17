package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.hospital.domain.PubBanner;
import com.ruoyi.hospital.service.IPubBannerService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 首页轮播图Controller
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@RestController
@RequestMapping("/banner")
public class PubBannerController extends BaseController
{
    @Autowired
    private IPubBannerService pubBannerService;

    /**
     * 查询首页轮播图列表
     */
    @RequiresPermissions("hospital:banner:list")
    @GetMapping("/list")
    public TableDataInfo list(PubBanner pubBanner)
    {
        startPage();
        List<PubBanner> list = pubBannerService.selectPubBannerList(pubBanner);
        return getDataTable(list);
    }

    /**
     * 导出首页轮播图列表
     */
    @RequiresPermissions("hospital:banner:export")
    @Log(title = "首页轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PubBanner pubBanner)
    {
        List<PubBanner> list = pubBannerService.selectPubBannerList(pubBanner);
        ExcelUtil<PubBanner> util = new ExcelUtil<PubBanner>(PubBanner.class);
        util.exportExcel(response, list, "首页轮播图数据");
    }

    /**
     * 获取首页轮播图详细信息
     */
    @RequiresPermissions("hospital:banner:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pubBannerService.selectPubBannerById(id));
    }

    /**
     * 新增首页轮播图
     */
    @RequiresPermissions("hospital:banner:add")
    @Log(title = "首页轮播图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PubBanner pubBanner)
    {
        return toAjax(pubBannerService.insertPubBanner(pubBanner));
    }

    /**
     * 修改首页轮播图
     */
    @RequiresPermissions("hospital:banner:edit")
    @Log(title = "首页轮播图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PubBanner pubBanner)
    {
        return toAjax(pubBannerService.updatePubBanner(pubBanner));
    }

    /**
     * 删除首页轮播图
     */
    @RequiresPermissions("hospital:banner:remove")
    @Log(title = "首页轮播图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pubBannerService.deletePubBannerByIds(ids));
    }
}
