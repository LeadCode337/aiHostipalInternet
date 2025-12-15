package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.SysJobTitle;
import com.ruoyi.system.service.ISysJobTitleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 职称管理Controller
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
@RestController
@RequestMapping("/title")
public class SysJobTitleController extends BaseController
{
    @Autowired
    private ISysJobTitleService sysJobTitleService;

    /**
     * 查询职称管理列表
     */
    @RequiresPermissions("system:title:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJobTitle sysJobTitle)
    {
        startPage();
        List<SysJobTitle> list = sysJobTitleService.selectSysJobTitleList(sysJobTitle);
        return getDataTable(list);
    }

    /**
     * 导出职称管理列表
     */
    @RequiresPermissions("system:title:export")
    @Log(title = "职称管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJobTitle sysJobTitle)
    {
        List<SysJobTitle> list = sysJobTitleService.selectSysJobTitleList(sysJobTitle);
        ExcelUtil<SysJobTitle> util = new ExcelUtil<SysJobTitle>(SysJobTitle.class);
        util.exportExcel(response, list, "职称管理数据");
    }

    /**
     * 获取职称管理详细信息
     */
    @RequiresPermissions("system:title:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysJobTitleService.selectSysJobTitleById(id));
    }

    /**
     * 新增职称管理
     */
    @RequiresPermissions("system:title:add")
    @Log(title = "职称管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJobTitle sysJobTitle)
    {
        return toAjax(sysJobTitleService.insertSysJobTitle(sysJobTitle));
    }

    /**
     * 修改职称管理
     */
    @RequiresPermissions("system:title:edit")
    @Log(title = "职称管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJobTitle sysJobTitle)
    {
        return toAjax(sysJobTitleService.updateSysJobTitle(sysJobTitle));
    }

    /**
     * 删除职称管理
     */
    @RequiresPermissions("system:title:remove")
    @Log(title = "职称管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysJobTitleService.deleteSysJobTitleByIds(ids));
    }
}
