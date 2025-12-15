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
import com.ruoyi.hospital.domain.SysDepartment;
import com.ruoyi.hospital.service.ISysDepartmentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 科室Controller
 * 
 * @author ruoyi
 * @date 2025-12-13
 */
@RestController
@RequestMapping("/department")
public class SysDepartmentController extends BaseController
{
    @Autowired
    private ISysDepartmentService sysDepartmentService;

    /**
     * 查询科室列表
     */
    @RequiresPermissions("hospital:department:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDepartment sysDepartment)
    {
        startPage();
        List<SysDepartment> list = sysDepartmentService.selectSysDepartmentList(sysDepartment);
        return getDataTable(list);
    }

    /**
     * 导出科室列表
     */
    @RequiresPermissions("hospital:department:export")
    @Log(title = "科室", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDepartment sysDepartment)
    {
        List<SysDepartment> list = sysDepartmentService.selectSysDepartmentList(sysDepartment);
        ExcelUtil<SysDepartment> util = new ExcelUtil<SysDepartment>(SysDepartment.class);
        util.exportExcel(response, list, "科室数据");
    }

    /**
     * 获取科室详细信息
     */
    @RequiresPermissions("hospital:department:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysDepartmentService.selectSysDepartmentById(id));
    }

    /**
     * 新增科室
     */
    @RequiresPermissions("hospital:department:add")
    @Log(title = "科室", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDepartment sysDepartment)
    {
        return toAjax(sysDepartmentService.insertSysDepartment(sysDepartment));
    }

    /**
     * 修改科室
     */
    @RequiresPermissions("hospital:department:edit")
    @Log(title = "科室", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDepartment sysDepartment)
    {
        return toAjax(sysDepartmentService.updateSysDepartment(sysDepartment));
    }

    /**
     * 删除科室
     */
    @RequiresPermissions("hospital:department:remove")
    @Log(title = "科室", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysDepartmentService.deleteSysDepartmentByIds(ids));
    }
}
