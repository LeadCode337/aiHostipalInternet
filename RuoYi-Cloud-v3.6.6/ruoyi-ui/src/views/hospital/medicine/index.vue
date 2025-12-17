<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="药品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入药品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="药品生产厂商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入药品生产厂商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hospital:medicine:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hospital:medicine:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hospital:medicine:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:medicine:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="medicineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="药品名称" align="center" prop="name" />
      <el-table-column label="药品类型" align="center" prop="type" />
      <el-table-column label="关联药品标签表" align="center" prop="medicineTagId" />
      <el-table-column label="药品售价" align="center" prop="price" />
      <el-table-column label="药品当前库存数量" align="center" prop="stock" />
      <el-table-column label="库存预警阀值" align="center" prop="threshold" />
      <el-table-column label="药品生产厂商" align="center" prop="manufacturer" />
      <el-table-column label="药品状态" align="center" prop="status" />
      <el-table-column label="图片" align="center" prop="imageUrl">
        <template slot-scope="scope">
          <el-image 
            v-if="scope.row.imageUrl" 
            :src="scope.row.imageUrl" 
            style="width: 50px; height: 50px" 
            fit="cover"
            preview-src-list="[scope.row.imageUrl]"
          />
          <span v-else>无图片</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:medicine:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:medicine:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改药品库存对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="药品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入药品名称" />
        </el-form-item>
        <el-form-item label="药品类型" prop="type">
          <el-input v-model="form.type" placeholder="请输入药品类型" />
        </el-form-item>
        <el-form-item label="关联药品标签表" prop="medicineTagId">
          <el-input v-model="form.medicineTagId" placeholder="请输入关联药品标签表ID" />
        </el-form-item>
        <el-form-item label="药品售价" prop="price">
          <el-input v-model="form.price" placeholder="请输入药品售价" type="number" />
        </el-form-item>
        <el-form-item label="药品当前库存数量" prop="stock">
          <el-input v-model="form.stock" placeholder="请输入药品当前库存数量" type="number" disabled />
        </el-form-item>
        <el-form-item label="库存预警阀值" prop="threshold">
          <el-input v-model="form.threshold" placeholder="请输入库存预警阀值" type="number" />
        </el-form-item>
        <el-form-item label="药品生产厂商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入药品生产厂商" />
        </el-form-item>
        <el-form-item label="药品状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择药品状态">
            <el-option label="正常" value="0" />
            <el-option label="下架" value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="图片">
          <el-input v-model="form.imageUrl" placeholder="请输入图片URL" />
          <!-- 若需富文本编辑器，需确保editor组件已全局注册，否则替换为普通输入框 -->
          <!-- <editor v-model="form.imageUrl" :min-height="192"/> -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMedicine, getMedicine, delMedicine, addMedicine, updateMedicine } from "@/api/hospital/medicine"
import { download } from '@/utils/request' // 补充导出所需的download方法

export default {
  name: "Medicine",
  components: {
    // 若right-toolbar、pagination为全局组件可省略，否则需引入注册
    // RightToolbar: () => import('@/components/RightToolbar'),
    // Pagination: () => import('@/components/Pagination')
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 药品库存表格数据
      medicineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        type: null,
        medicineTagId: null,
        manufacturer: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "药品名称不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "药品类型不能为空", trigger: "blur" }
        ],
        medicineTagId: [
          { required: true, message: "关联药品标签表不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "药品售价不能为空", trigger: "blur" },
          { type: 'number', message: '药品售价必须为数字', trigger: 'blur' }
        ],
        threshold: [
          { required: true, message: "库存预警阀值不能为空", trigger: "blur" },
          { type: 'number', message: '库存预警阀值必须为数字', trigger: 'blur' }
        ],
        manufacturer: [
          { required: true, message: "药品生产厂商不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "药品状态不能为空", trigger: "change" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询药品库存列表 */
    getList() {
      this.loading = true
      listMedicine(this.queryParams).then(response => {
        this.medicineList = response.rows || []
        this.total = response.total || 0
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        type: null,
        medicineTagId: null,
        price: null,
        stock: null,
        threshold: null,
        manufacturer: null,
        status: null,
        imageUrl: null
      }
      this.$refs.form && this.$refs.form.resetFields()
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.$refs.queryForm.resetFields()
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        name: null,
        type: null,
        medicineTagId: null,
        manufacturer: null,
        status: null,
      }
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加药品库存"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row?.id || this.ids[0]
      if (!id) {
        this.$modal.msgWarning("请选择需要修改的药品库存")
        return
      }
      getMedicine(id).then(response => {
        this.form = response.data || {}
        this.open = true
        this.title = "修改药品库存"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const promise = this.form.id ? updateMedicine(this.form) : addMedicine(this.form)
          promise.then(() => {
            this.$modal.msgSuccess(this.form.id ? "修改成功" : "新增成功")
            this.open = false
            this.getList()
          }).catch(() => {
            this.$modal.msgError(this.form.id ? "修改失败" : "新增失败")
          })
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row?.id || this.ids
      if (!ids || (Array.isArray(ids) && ids.length === 0)) {
        this.$modal.msgWarning("请选择需要删除的药品库存")
        return
      }
      this.$modal.confirm(`是否确认删除药品库存编号为"${ids}"的数据项？`).then(() => {
        return delMedicine(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {
        this.$modal.msgError("删除失败")
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      download('hospital/medicine/export', {
        ...this.queryParams
      }, `medicine_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>