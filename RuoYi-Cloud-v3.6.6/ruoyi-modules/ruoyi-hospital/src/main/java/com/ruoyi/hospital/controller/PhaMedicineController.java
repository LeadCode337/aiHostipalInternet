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
import com.ruoyi.hospital.domain.PhaMedicine;
import com.ruoyi.hospital.service.IPhaMedicineService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 药品库存Controller
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@RestController
@RequestMapping("/medicine")
public class PhaMedicineController extends BaseController
{
    @Autowired
    private IPhaMedicineService phaMedicineService;

    /**
     * 查询药品库存列表
     */
    @RequiresPermissions("hospital:medicine:list")
    @GetMapping("/list")
    public TableDataInfo list(PhaMedicine phaMedicine)
    {
        startPage();
        List<PhaMedicine> list = phaMedicineService.selectPhaMedicineList(phaMedicine);
        return getDataTable(list);
    }

    /**
     * 导出药品库存列表
     */
    @RequiresPermissions("hospital:medicine:export")
    @Log(title = "药品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PhaMedicine phaMedicine)
    {
        List<PhaMedicine> list = phaMedicineService.selectPhaMedicineList(phaMedicine);
        ExcelUtil<PhaMedicine> util = new ExcelUtil<PhaMedicine>(PhaMedicine.class);
        util.exportExcel(response, list, "药品库存数据");
    }

    /**
     * 获取药品库存详细信息
     */
    @RequiresPermissions("hospital:medicine:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(phaMedicineService.selectPhaMedicineById(id));
    }

    /**
     * 新增药品库存
     */
    @RequiresPermissions("hospital:medicine:add")
    @Log(title = "药品库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PhaMedicine phaMedicine)
    {
        return toAjax(phaMedicineService.insertPhaMedicine(phaMedicine));
    }

    /**
     * 修改药品库存
     */
    @RequiresPermissions("hospital:medicine:edit")
    @Log(title = "药品库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PhaMedicine phaMedicine)
    {
        return toAjax(phaMedicineService.updatePhaMedicine(phaMedicine));
    }

    /**
     * 删除药品库存
     */
    @RequiresPermissions("hospital:medicine:remove")
    @Log(title = "药品库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(phaMedicineService.deletePhaMedicineByIds(ids));
    }
}
