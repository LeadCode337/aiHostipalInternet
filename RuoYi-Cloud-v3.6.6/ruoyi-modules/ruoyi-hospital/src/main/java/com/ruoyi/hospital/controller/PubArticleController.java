package com.ruoyi.hospital.controller;

import java.util.List;
import java.io.IOException;
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
import com.ruoyi.hospital.domain.PubArticle;
import com.ruoyi.hospital.service.IPubArticleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 宣传中心Controller
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/article")
public class PubArticleController extends BaseController
{
    @Autowired
    private IPubArticleService pubArticleService;

    /**
     * 查询宣传中心列表
     */
    @RequiresPermissions("hospital:article:list")
    @GetMapping("/list")
    public TableDataInfo list(PubArticle pubArticle)
    {
        startPage();
        List<PubArticle> list = pubArticleService.selectPubArticleList(pubArticle);
        return getDataTable(list);
    }

    /**
     * 导出宣传中心列表
     */
    @RequiresPermissions("hospital:article:export")
    @Log(title = "宣传中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PubArticle pubArticle)
    {
        List<PubArticle> list = pubArticleService.selectPubArticleList(pubArticle);
        ExcelUtil<PubArticle> util = new ExcelUtil<PubArticle>(PubArticle.class);
        util.exportExcel(response, list, "宣传中心数据");
    }

    /**
     * 获取宣传中心详细信息
     */
    @RequiresPermissions("hospital:article:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pubArticleService.selectPubArticleById(id));
    }

    /**
     * 新增宣传中心
     */
    @RequiresPermissions("hospital:article:add")
    @Log(title = "宣传中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PubArticle pubArticle)
    {
        return toAjax(pubArticleService.insertPubArticle(pubArticle));
    }

    /**
     * 修改宣传中心
     */
    @RequiresPermissions("hospital:article:edit")
    @Log(title = "宣传中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PubArticle pubArticle)
    {
        return toAjax(pubArticleService.updatePubArticle(pubArticle));
    }

    /**
     * 删除宣传中心
     */
    @RequiresPermissions("hospital:article:remove")
    @Log(title = "宣传中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pubArticleService.deletePubArticleByIds(ids));
    }
}
