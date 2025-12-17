<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工号" prop="employeeId">
        <el-input v-model="queryParams.employeeId" placeholder="请输入医生工号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入医生姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['hospital:doctor:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['hospital:doctor:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['hospital:doctor:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['hospital:doctor:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="doctorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="工号" align="center" prop="employeeId" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="头像" align="center" prop="avatar" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.avatar" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="职称id" align="center" prop="jobTitleId" />
      <el-table-column label="入职日期" align="center" prop="joinDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.joinDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="在职状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.hos_doctor_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:doctor:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:doctor:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改医生档案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- 若依自带的图片上传 -->
        <el-form-item label="身份证图片" prop="idCardUrl">
          <image-upload :limit="1" v-model="form.idCardUrl" />
        </el-form-item>
        
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入医生姓名" />
        </el-form-item>
        <el-form-item label="头像" prop="avatar">
          <image-upload :limit="1" v-model="form.avatar" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入医生联系电话" />
        </el-form-item>
        <el-form-item label="科室id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入关联科室id,绑定所属科室" />
        </el-form-item>
        <el-form-item label="资格证书" prop="licenseUrl">
          <image-upload v-model="form.licenseUrl" />
        </el-form-item>
        <el-form-item label="在职状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.hos_doctor_status" :key="dict.value"
              :label="dict.value">{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入医生身份证号" />
        </el-form-item>

        <el-form-item label="擅长领域" prop="specialty">
          <el-input v-model="form.specialty" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="个人简介或寄语" prop="introduction">
          <el-input v-model="form.introduction" type="textarea" placeholder="请输入内容" />
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
import { listDoctor, getDoctor, delDoctor, addDoctor, updateDoctor } from "@/api/hospital/doctor"

export default {
  name: "Doctor",
  dicts: ['hos_doctor_status', 'hos_doctor_deleted'],
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
      // 医生档案表格数据
      doctorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        employeeId: null,
        name: null,
        jobTitleId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "医生姓名不能为空", trigger: "blur" }
        ],
        avatar: [
          { required: true, message: "医生头像不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "医生联系电话不能为空", trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "关联科室id,绑定所属科室不能为空", trigger: "blur" }
        ],
        jobTitleId: [
          { required: true, message: "关联职称id,绑定医生职称不能为空", trigger: "change" }
        ],
        licenseUrl: [
          { required: true, message: "医生资格证书材料不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "医生在职状态,不能为空", trigger: "change" }
        ],
        idCard: [
          { required: true, message: "医生身份证号不能为空", trigger: "blur" }
        ],
        idCardUrl: [
          { required: true, message: "身份证的图片,不能为空", trigger: "blur" }
        ],
        specialty: [
          { required: true, message: "医生擅长领域,不能为空", trigger: "blur" }
        ],
        introduction: [
          { required: true, message: "医生个人简介或寄语不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询医生档案列表 */
    getList() {
      this.loading = true
      listDoctor(this.queryParams).then(response => {
        this.doctorList = response.rows
        this.total = response.total
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
        employeeId: null,
        name: null,
        password: null,
        avatar: null,
        phone: null,
        deptId: null,
        jobTitleId: null,
        licenseUrl: null,
        joinDate: null,
        status: null,
        isDeleted: null,
        idCard: null,
        idCardUrl: null,
        specialty: null,
        introduction: null,
        rating: null,
        consultCount: null,
        salary: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
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
      this.title = "添加医生档案"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getDoctor(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改医生档案"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDoctor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addDoctor(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除医生档案编号为"' + ids + '"的数据项？').then(function () {
        return delDoctor(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => { })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/doctor/export', {
        ...this.queryParams
      }, `doctor_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
