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
import com.ruoyi.hospital.domain.HosJobTitle;
import com.ruoyi.hospital.service.IHosJobTitleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 医生职称Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/job")
public class HosJobTitleController extends BaseController
{
    @Autowired
    private IHosJobTitleService hosJobTitleService;

    /**
     * 查询医生职称列表
     */
    @RequiresPermissions("hospital:job:list")
    @GetMapping("/list")
    public TableDataInfo list(HosJobTitle hosJobTitle)
    {
        startPage();
        List<HosJobTitle> list = hosJobTitleService.selectHosJobTitleList(hosJobTitle);
        return getDataTable(list);
    }

    /**
     * 导出医生职称列表
     */
    @RequiresPermissions("hospital:job:export")
    @Log(title = "医生职称", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HosJobTitle hosJobTitle)
    {
        List<HosJobTitle> list = hosJobTitleService.selectHosJobTitleList(hosJobTitle);
        ExcelUtil<HosJobTitle> util = new ExcelUtil<HosJobTitle>(HosJobTitle.class);
        util.exportExcel(response, list, "医生职称数据");
    }

    /**
     * 获取医生职称详细信息
     */
    @RequiresPermissions("hospital:job:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hosJobTitleService.selectHosJobTitleById(id));
    }

    /**
     * 新增医生职称
     */
    @RequiresPermissions("hospital:job:add")
    @Log(title = "医生职称", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HosJobTitle hosJobTitle)
    {
        return toAjax(hosJobTitleService.insertHosJobTitle(hosJobTitle));
    }

    /**
     * 修改医生职称
     */
    @RequiresPermissions("hospital:job:edit")
    @Log(title = "医生职称", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HosJobTitle hosJobTitle)
    {
        return toAjax(hosJobTitleService.updateHosJobTitle(hosJobTitle));
    }

    /**
     * 删除医生职称
     */
    @RequiresPermissions("hospital:job:remove")
    @Log(title = "医生职称", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hosJobTitleService.deleteHosJobTitleByIds(ids));
    }
}
