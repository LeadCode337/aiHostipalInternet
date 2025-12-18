package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.hospital.domain.SysDoctor;
import com.ruoyi.hospital.service.ISysDoctorService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 医生档案Controller
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@RestController
@RequestMapping("/doctor")
public class SysDoctorController extends BaseController
{
    @Autowired
    private ISysDoctorService sysDoctorService;




    /**
     * 查询医生档案列表
     */
    @RequiresPermissions("hospital:doctor:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDoctor sysDoctor)
    {
        startPage();
        List<SysDoctor> list = sysDoctorService.selectSysDoctorList(sysDoctor);
        return getDataTable(list);
    }

    /**
     * 导出医生档案列表
     */
    @RequiresPermissions("hospital:doctor:export")
    @Log(title = "医生档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDoctor sysDoctor)
    {
        List<SysDoctor> list = sysDoctorService.selectSysDoctorList(sysDoctor);
        ExcelUtil<SysDoctor> util = new ExcelUtil<SysDoctor>(SysDoctor.class);
        util.exportExcel(response, list, "医生档案数据");
    }

    /**
     * 获取医生档案详细信息
     */
    @RequiresPermissions("hospital:doctor:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysDoctorService.selectSysDoctorById(id));
    }

    /**
     * 新增医生档案
     */
    @RequiresPermissions("hospital:doctor:add")
    @Log(title = "医生档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDoctor sysDoctor)
    {
        return toAjax(sysDoctorService.insertSysDoctor(sysDoctor));
    }

    /**
     * 修改医生档案
     */
    @RequiresPermissions("hospital:doctor:edit")
    @Log(title = "医生档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDoctor sysDoctor)
    {
        return toAjax(sysDoctorService.updateSysDoctor(sysDoctor));
    }

    /**
     * 删除医生档案
     */
    @RequiresPermissions("hospital:doctor:remove")
    @Log(title = "医生档案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysDoctorService.deleteSysDoctorByIds(ids));
    }



}
