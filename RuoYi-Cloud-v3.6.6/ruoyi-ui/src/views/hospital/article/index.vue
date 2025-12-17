<template>
  <div class="app-container">
    <div class="header-section">
      <div class="header-title">
        <i class="el-icon-s-promotion"></i> 宣传与内容中心
      </div>
      <div class="header-desc">管理健康科普文章与首页轮播图片</div>
      <div class="header-tabs">
        <div
          class="tab-item"
          :class="{ active: activeTab === 'article' }"
          @click="activeTab = 'article'"
        >
          健康文章管理
        </div>
        <div
          class="tab-item"
          :class="{ active: activeTab === 'banner' }"
          @click="activeTab = 'banner'"
        >
          轮播图/Banner管理
        </div>
      </div>
    </div>

    <div v-show="activeTab === 'article'">
      <el-form
        :model="queryParams"
        ref="queryForm"
        size="small"
        :inline="true"
        v-show="showSearch"
        label-width="68px"
      >
        <el-form-item label="文章标题" prop="title">
          <el-input
            v-model="queryParams.title"
            placeholder="请输入文章标题"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="类型" prop="category">
          <el-select
            v-model="queryParams.category"
            placeholder="请选择类型"
            clearable
          >
            <el-option
              v-for="dict in dict.type.hos_article_category"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select
            v-model="queryParams.status"
            placeholder="请选择状态"
            clearable
          >
            <el-option
              v-for="dict in dict.type.hos_article_status"
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

      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['hospital:article:add']"
            >新增</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['hospital:article:edit']"
            >修改</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['hospital:article:remove']"
            >删除</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['hospital:article:export']"
            >导出</el-button
          >
        </el-col>
        <right-toolbar
          :showSearch.sync="showSearch"
          @queryTable="getList"
        ></right-toolbar>
      </el-row>

      <el-table
        v-loading="loading"
        :data="articleList"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="序号" align="center" prop="id" />
        <el-table-column label="文章标题" align="center" prop="title" />
        <el-table-column label="类型" align="center" prop="category">
          <template slot-scope="scope">
            <dict-tag
              :options="dict.type.hos_article_category"
              :value="scope.row.category"
            />
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <dict-tag
              :options="dict.type.hos_article_status"
              :value="scope.row.status"
            />
          </template>
        </el-table-column>
        <el-table-column
          label="文章上传日期"
          align="center"
          prop="uploadTime"
          width="180"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.uploadTime, "{y}-{m}-{d}") }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['hospital:article:edit']"
              >修改</el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['hospital:article:remove']"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

      <!-- 添加或修改宣传中心对话框 -->
      <el-dialog
        :title="title"
        :visible.sync="open"
        width="500px"
        append-to-body
      >
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="文章标题" prop="title">
            <el-input v-model="form.title" placeholder="请输入文章标题" />
          </el-form-item>
          <el-form-item label="文章内容">
            <editor v-model="form.content" :min-height="192" />
          </el-form-item>
          <el-form-item label="类型" prop="category">
            <el-select v-model="form.category" placeholder="请选择类型">
              <el-option
                v-for="dict in dict.type.hos_article_category"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="文章上传日期" prop="uploadTime">
            <el-date-picker
              clearable
              v-model="form.uploadTime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择文章上传日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="文章下架日期" prop="removedTime">
            <el-date-picker
              clearable
              v-model="form.removedTime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择文章下架日期"
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

    <div v-if="activeTab === 'banner'" class="banner-wrapper">
      <Banner />
    </div>
  </div>
</template>

<script>
import {
  listArticle,
  getArticle,
  delArticle,
  addArticle,
  updateArticle,
} from "@/api/hospital/article";
import Banner from "@/views/hospital/banner/index";

export default {
  name: "Article",
  components: { Banner },
  dicts: ["hos_article_category", "hos_article_status"],
  data() {
    return {
      activeTab: "article",
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
      // 宣传中心表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        category: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "文章标题不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询宣传中心列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then((response) => {
        this.articleList = response.rows;
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
        content: null,
        category: null,
        status: null,
        hotOrder: null,
        likeNum: null,
        saveNum: null,
        uploadTime: null,
        removedTime: null,
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
      this.title = "添加宣传中心";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getArticle(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宣传中心";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateArticle(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then((response) => {
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
        .confirm('是否确认删除宣传中心编号为"' + ids + '"的数据项？')
        .then(function () {
          return delArticle(ids);
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
        "hospital/article/export",
        {
          ...this.queryParams,
        },
        `article_${new Date().getTime()}.xlsx`
      );
    },
    /** 跳转到轮播图管理 */
    goToBanner() {
      this.$router.push("/hospital/banner");
    },
  },
};
</script>

<style scoped lang="scss">
.banner-wrapper ::v-deep .app-container {
  padding: 0;
}
.header-section {
  background-color: #f8f8f9;
  padding: 20px 20px 0 20px;
  margin-bottom: 20px;
  border-radius: 4px;

  .header-title {
    font-size: 20px;
    font-weight: bold;
    color: #303133;
    margin-bottom: 10px;
    display: flex;
    align-items: center;

    i {
      color: #1890ff;
      margin-right: 8px;
      font-size: 24px;
    }
  }

  .header-desc {
    font-size: 14px;
    color: #606266;
    margin-bottom: 20px;
    padding-left: 32px;
  }

  .header-tabs {
    display: flex;
    border-bottom: 2px solid #e4e7ed;

    .tab-item {
      padding: 0 20px 10px 20px;
      font-size: 14px;
      cursor: pointer;
      color: #606266;
      position: relative;
      font-weight: 500;

      &.active {
        color: #1890ff;

        &::after {
          content: "";
          position: absolute;
          bottom: -2px;
          left: 0;
          width: 100%;
          height: 2px;
          background-color: #1890ff;
        }
      }

      &:hover {
        color: #1890ff;
      }
    }
  }
}
</style>
