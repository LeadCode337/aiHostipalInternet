<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态"
          clearable
        >
          <el-option
            v-for="dict in dict.type.hos_banner_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row
      :gutter="10"
      class="mb8"
      style="display: flex; justify-content: flex-end"
    >
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="small"
          @click="handleAdd"
          v-hasPermi="['hospital:banner:add']"
          >上传新图片</el-button
        >
      </el-col>
    </el-row>

    <div v-loading="loading" class="banner-grid">
      <div v-for="item in bannerList" :key="item.id" class="banner-card">
        <div class="card-image">
          <img :src="item.imageUrl" alt="banner" />
          <div
            class="status-badge"
            :class="item.status === '0' ? 'active' : 'inactive'"
          >
            {{ selectDictLabel(dict.type.hos_banner_status, item.status) }}
          </div>
        </div>
        <div class="card-content">
          <div class="card-title">{{ item.title }}</div>
          <div class="card-info">
            <i class="el-icon-date"></i> 上传:
            {{ parseTime(item.uploadTime, "{y}-{m}-{d}") }}
          </div>
          <div class="card-info" v-if="item.lastActiveAt">
            <i class="el-icon-time"></i> 最后活跃:
            {{ parseTime(item.lastActiveAt, "{y}-{m}-{d}") }}
            <span v-if="item.status === '1'" class="inactive-days">
              (已停用 {{ getDaysDiff(item.lastActiveAt) }} 天)
            </span>
          </div>
        </div>
        <div class="card-actions">
          <el-button
            size="mini"
            type="text"
            class="action-btn"
            @click="handleUpdate(item)"
            v-hasPermi="['hospital:banner:edit']"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="text"
            class="action-btn success"
            @click="handleToggleStatus(item)"
            v-if="item.status === '1'"
            >启用</el-button
          >
          <el-button
            size="mini"
            type="text"
            class="action-btn"
            @click="handleToggleStatus(item)"
            v-else
            >禁用</el-button
          >
          <el-button
            size="mini"
            type="text"
            class="action-btn danger"
            @click="handleDelete(item)"
            v-hasPermi="['hospital:banner:remove']"
            >删除</el-button
          >
        </div>
      </div>
    </div>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改首页轮播图对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="图片" prop="imageUrl">
          <image-upload v-model="form.imageUrl" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.hos_banner_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最后活跃日期" prop="lastActiveAt">
          <el-date-picker
            clearable
            v-model="form.lastActiveAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后活跃日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上传日期" prop="uploadTime">
          <el-date-picker
            clearable
            v-model="form.uploadTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上传日期"
          >
          </el-date-picker>
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
import {
  listBanner,
  getBanner,
  delBanner,
  addBanner,
  updateBanner,
} from "@/api/hospital/banner";

export default {
  name: "Banner",
  dicts: ["hos_banner_status"],
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
      // 首页轮播图表格数据
      bannerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询首页轮播图列表 */
    getList() {
      this.loading = true;
      listBanner(this.queryParams).then((response) => {
        this.bannerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        imageUrl: null,
        status: null,
        lastActiveAt: null,
        uploadTime: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加首页轮播图";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getBanner(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改首页轮播图";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateBanner(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBanner(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除首页轮播图编号为"' + ids + '"的数据项？')
        .then(function () {
          return delBanner(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "hospital/banner/export",
        {
          ...this.queryParams,
        },
        `banner_${new Date().getTime()}.xlsx`
      );
    },
    getDaysDiff(date) {
      if (!date) return 0;
      const now = new Date();
      const lastActive = new Date(date);
      const diffTime = Math.abs(now - lastActive);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      return diffDays;
    },
    handleToggleStatus(row) {
      const newStatus = row.status === "0" ? "1" : "0";
      const action = newStatus === "0" ? "启用" : "禁用";
      this.$modal
        .confirm("确认要" + action + "该轮播图吗？")
        .then(() => {
          updateBanner({ ...row, status: newStatus }).then(() => {
            this.$modal.msgSuccess(action + "成功");
            this.getList();
          });
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped lang="scss">
.banner-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  padding: 10px 0;
}

.banner-card {
  border: 1px solid #e6e6e6;
  border-radius: 8px;
  overflow: hidden;
  background: #fff;
  transition: all 0.3s;
  display: flex;
  flex-direction: column;

  &:hover {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    transform: translateY(-2px);
  }

  .card-image {
    position: relative;
    height: 180px;
    background: #f5f7fa;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;

    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .status-badge {
      position: absolute;
      top: 10px;
      right: 10px;
      padding: 4px 12px;
      border-radius: 4px;
      font-size: 12px;
      color: #fff;
      font-weight: 500;

      &.active {
        background-color: #67c23a; // Success green
      }

      &.inactive {
        background-color: #909399; // Info grey
      }
    }
  }

  .card-content {
    padding: 16px;
    flex: 1;

    .card-title {
      font-size: 16px;
      font-weight: bold;
      color: #303133;
      margin-bottom: 12px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .card-info {
      font-size: 13px;
      color: #909399;
      margin-bottom: 8px;
      display: flex;
      align-items: center;

      i {
        margin-right: 6px;
      }

      .inactive-days {
        color: #e6a23c;
        margin-left: 5px;
      }
    }
  }

  .card-actions {
    display: flex;
    border-top: 1px solid #ebeef5;

    .action-btn {
      flex: 1;
      text-align: center;
      padding: 12px 0;
      color: #606266;
      border-radius: 0;

      &:not(:last-child) {
        border-right: 1px solid #ebeef5;
      }

      &:hover {
        background-color: #f5f7fa;
      }

      &.success {
        color: #67c23a;
        &:hover {
          background-color: #f0f9eb;
        }
      }

      &.danger {
        color: #f56c6c;
        &:hover {
          background-color: #fef0f0;
        }
      }
    }
  }
}
</style>
