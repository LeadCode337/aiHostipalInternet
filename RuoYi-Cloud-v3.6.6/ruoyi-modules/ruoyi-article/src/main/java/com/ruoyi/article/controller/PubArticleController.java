package com.ruoyi.article.controller;

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
import com.ruoyi.article.domain.PubArticle;
import com.ruoyi.article.service.IPubArticleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 健康宣教文章Controller
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@RestController
@RequestMapping("/article")
public class PubArticleController extends BaseController
{
    @Autowired
    private IPubArticleService pubArticleService;

    /**
     * 查询健康宣教文章列表
     */
    @RequiresPermissions("article:article:list")
    @GetMapping("/list")
    public TableDataInfo list(PubArticle pubArticle)
    {
        startPage();
        List<PubArticle> list = pubArticleService.selectPubArticleList(pubArticle);
        return getDataTable(list);
    }

    /**
     * 导出健康宣教文章列表
     */
    @RequiresPermissions("article:article:export")
    @Log(title = "健康宣教文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PubArticle pubArticle)
    {
        List<PubArticle> list = pubArticleService.selectPubArticleList(pubArticle);
        ExcelUtil<PubArticle> util = new ExcelUtil<PubArticle>(PubArticle.class);
        util.exportExcel(response, list, "健康宣教文章数据");
    }

    /**
     * 获取健康宣教文章详细信息
     */
    @RequiresPermissions("article:article:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pubArticleService.selectPubArticleById(id));
    }

    /**
     * 新增健康宣教文章
     */
    @RequiresPermissions("article:article:add")
    @Log(title = "健康宣教文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PubArticle pubArticle)
    {
        return toAjax(pubArticleService.insertPubArticle(pubArticle));
    }

    /**
     * 修改健康宣教文章
     */
    @RequiresPermissions("article:article:edit")
    @Log(title = "健康宣教文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PubArticle pubArticle)
    {
        return toAjax(pubArticleService.updatePubArticle(pubArticle));
    }

    /**
     * 删除健康宣教文章
     */
    @RequiresPermissions("article:article:remove")
    @Log(title = "健康宣教文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pubArticleService.deletePubArticleByIds(ids));
    }
}
