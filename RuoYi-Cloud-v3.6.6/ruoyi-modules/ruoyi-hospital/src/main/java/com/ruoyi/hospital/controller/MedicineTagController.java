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
import com.ruoyi.hospital.domain.MedicineTag;
import com.ruoyi.hospital.service.IMedicineTagService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 药品标签Controller
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@RestController
@RequestMapping("/medicineTag")
public class MedicineTagController extends BaseController
{
    @Autowired
    private IMedicineTagService medicineTagService;

    /**
     * 查询药品标签列表
     */
    @RequiresPermissions("hospital:medicineTag:list")
    @GetMapping("/list")
    public TableDataInfo list(MedicineTag medicineTag)
    {
        startPage();
        List<MedicineTag> list = medicineTagService.selectMedicineTagList(medicineTag);
        return getDataTable(list);
    }

    /**
     * 导出药品标签列表
     */
    @RequiresPermissions("hospital:medicineTag:export")
    @Log(title = "药品标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MedicineTag medicineTag)
    {
        List<MedicineTag> list = medicineTagService.selectMedicineTagList(medicineTag);
        ExcelUtil<MedicineTag> util = new ExcelUtil<MedicineTag>(MedicineTag.class);
        util.exportExcel(response, list, "药品标签数据");
    }

    /**
     * 获取药品标签详细信息
     */
    @RequiresPermissions("hospital:medicineTag:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(medicineTagService.selectMedicineTagById(id));
    }

    /**
     * 新增药品标签
     */
    @RequiresPermissions("hospital:medicineTag:add")
    @Log(title = "药品标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MedicineTag medicineTag)
    {
        return toAjax(medicineTagService.insertMedicineTag(medicineTag));
    }

    /**
     * 修改药品标签
     */
    @RequiresPermissions("hospital:medicineTag:edit")
    @Log(title = "药品标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MedicineTag medicineTag)
    {
        return toAjax(medicineTagService.updateMedicineTag(medicineTag));
    }

    /**
     * 删除药品标签
     */
    @RequiresPermissions("hospital:medicineTag:remove")
    @Log(title = "药品标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(medicineTagService.deleteMedicineTagByIds(ids));
    }
}
