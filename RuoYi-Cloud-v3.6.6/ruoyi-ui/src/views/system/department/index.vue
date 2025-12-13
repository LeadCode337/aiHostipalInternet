<template>
  <div class="h-full flex flex-col bg-white rounded-lg shadow-sm border border-slate-200 overflow-hidden">
    <!-- 头部区域 -->
    <div class="p-6 border-b border-slate-100 bg-slate-50">
      <div class="flex justify-between items-center">
        <div>
          <h2 class="text-2xl font-bold text-slate-800 flex items-center gap-2">
            <i class="el-icon-location text-primary text-lg"></i> 科室管理
          </h2>
          <p class="text-sm text-slate-500 mt-1">维护医院科室基础信息、排班时间及状态</p>
        </div>
        <el-button
          @click="handleAdd"
          type="primary"
          icon="el-icon-plus"
          class="flex items-center gap-2 px-4 py-2 text-sm font-medium rounded-lg hover:bg-sky-600 transition shadow-sm"
        >
          新增科室
        </el-button>
      </div>

      <!-- 搜索筛选区域 -->
      <div class="mt-6 p-4 bg-white rounded-xl border border-slate-200 shadow-sm flex items-center gap-4">
        <div class="relative flex-1 max-w-md">
          <i class="el-icon-search absolute left-3 top-1/2 -translate-y-1/2 text-slate-400 text-base"></i>
          <el-input
            placeholder="请输入科室名称..."
            v-model="searchName"
            class="w-full pl-9 pr-3 py-2 text-sm border border-slate-300 rounded-lg focus:ring-2 focus:ring-primary focus:outline-none"
          ></el-input>
        </div>
      </div>
    </div>

    <!-- 表格内容区域 -->
    <div class="flex-1 overflow-auto p-6 bg-slate-50/50">
      <div class="bg-white rounded-xl border border-slate-200 overflow-hidden shadow-sm">
        <el-table
          :data="currentData"
          border
          stripe
          :header-cell-style="{ background: '#f8f9fa', color: '#334155', fontWeight: 500 }"
          :cell-style="{ padding: '16px' }"
          class="w-full text-left text-sm"
        >
          <!-- 科室名称/ID列 -->
          <el-table-column label="科室名称 / ID" width="180">
            <template slot-scope="scope">
              <div class="font-bold text-slate-800">{{ scope.row.name }}</div>
              <div class="text-xs text-slate-400 font-mono">ID: {{ scope.row.id }}</div>
            </template>
          </el-table-column>

          <!-- 地点列 -->
          <el-table-column label="地点" width="180">
            <template slot-scope="scope">
              <div class="flex items-center gap-1.5">
                <i class="el-icon-location text-slate-400 text-xs"></i> {{ scope.row.location }}
              </div>
            </template>
          </el-table-column>

          <!-- 服务时间列 -->
          <el-table-column label="服务时间" width="180">
            <template slot-scope="scope">
              <div class="flex items-center gap-1.5">
                <i class="el-icon-time text-slate-400 text-xs"></i> {{ scope.row.consultationHours }}
              </div>
            </template>
          </el-table-column>

          <!-- 平均就诊时长列 -->
          <el-table-column label="平均就诊时长" width="140">
            <template slot-scope="scope">
              ~{{ scope.row.avgVisitTime }} 分钟/人
            </template>
          </el-table-column>

          <!-- 状态列 -->
          <el-table-column label="状态" width="160">
            <template slot-scope="scope">
              <span
                :class="`px-2.5 py-1 rounded-full text-xs font-bold inline-flex items-center gap-1
                  ${scope.row.status === 'OPEN' ? 'bg-green-100 text-green-700' : 
                    scope.row.status === 'CLOSED' ? 'bg-slate-100 text-slate-600' : 
                    'bg-amber-100 text-amber-700'}`"
              >
                <span
                  :class="`w-1.5 h-1.5 rounded-full ${scope.row.status === 'OPEN' ? 'bg-green-500' : 
                    scope.row.status === 'CLOSED' ? 'bg-slate-500' : 'bg-amber-500'}`"
                ></span>
                {{ scope.row.status === 'OPEN' ? '正常' : 
                  scope.row.status === 'CLOSED' ? '已关闭' : '维护' }}
              </span>
              <div
                v-if="scope.row.patientCount > 0"
                class="text-xs text-slate-400 mt-1 flex items-center gap-1"
              >
                <i class="el-icon-user text-slate-400 text-xs"></i> 当前 {{ scope.row.patientCount }} 人
              </div>
            </template>
          </el-table-column>

          <!-- 创建时间列 -->
          <el-table-column label="创建时间" width="120">
            <template slot-scope="scope">
              <div class="text-slate-500 font-mono text-xs">{{ scope.row.createdAt }}</div>
            </template>
          </el-table-column>

          <!-- 操作列 -->
          <el-table-column label="操作" width="100" fixed="right">
            <template slot-scope="scope">
              <div class="flex items-center justify-end gap-2">
                <button
                  @click="handleEdit(scope.row)"
                  class="p-1.5 text-slate-400 hover:text-blue-600 hover:bg-blue-50 rounded"
                  title="修改"
                >
                  <i class="el-icon-edit text-base"></i>
                </button>
                <button
                  @click="handleDelete(scope.row)"
                  :class="`p-1.5 rounded transition ${scope.row.patientCount > 0 ? 'text-slate-300 cursor-not-allowed' : 'text-slate-400 hover:text-red-600 hover:bg-red-50'}`"
                  :title="scope.row.patientCount > 0 ? '当前科室有人，无法删除' : '删除'"
                  :disabled="scope.row.patientCount > 0"
                >
                  <i class="el-icon-delete text-base"></i>
                </button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <!-- 分页区域 -->
      <div class="mt-4 flex justify-between items-center text-sm text-slate-500">
        <span>
          显示 {{ currentData.length > 0 ? (page - 1) * PAGE_SIZE + 1 : 0 }} -
          {{ Math.min(page * PAGE_SIZE, filteredDepartments.length) }} 共 {{ filteredDepartments.length }} 条
        </span>
        <div class="flex gap-2">
          <el-button
            @click="page--"
            :disabled="page === 1"
            type="text"
            class="px-3 py-1 border rounded hover:bg-white disabled:opacity-50"
          >
            上一页
          </el-button>
          <el-button
            @click="page++"
            :disabled="page >= totalPages"
            type="text"
            class="px-3 py-1 border rounded hover:bg-white disabled:opacity-50"
          >
            下一页
          </el-button>
        </div>
      </div>
    </div>

    <!-- 新增/编辑弹窗 -->
    <el-dialog
      :title="editingDept ? '修改科室信息' : '新增科室'"
      :visible.sync="showModal"
      width="550px"
      :close-on-click-modal="false"
    >
      <form @submit.prevent="handleSave">
        <div class="grid grid-cols-2 gap-5">
          <div class="col-span-2">
            <label class="block text-sm font-bold text-slate-700 mb-1">
              科室名称 <span class="text-red-500">*</span>
            </label>
            <el-input
              required
              v-model="form.name"
              class="w-full p-2.5 border border-slate-300 rounded-lg focus:ring-2 focus:ring-primary focus:outline-none"
              placeholder="例如：心血管内科"
            ></el-input>
            <p class="text-xs text-slate-400 mt-1">系统将自动检查名称是否重复</p>
          </div>

          <div class="col-span-2">
            <label class="block text-sm font-bold text-slate-700 mb-1">科室地点</label>
            <div class="relative">
              <i class="el-icon-location absolute left-3 top-1/2 -translate-y-1/2 text-slate-400 text-base"></i>
              <el-input
                required
                v-model="form.location"
                class="w-full pl-9 pr-3 py-2.5 border border-slate-300 rounded-lg focus:ring-2 focus:ring-primary focus:outline-none"
                placeholder="例如：门诊楼 305"
              ></el-input>
            </div>
          </div>

          <div>
            <label class="block text-sm font-bold text-slate-700 mb-1">会诊时间</label>
            <div class="relative">
              <i class="el-icon-time absolute left-3 top-1/2 -translate-y-1/2 text-slate-400 text-base"></i>
              <el-input
                required
                v-model="form.consultationHours"
                class="w-full pl-9 pr-3 py-2.5 border border-slate-300 rounded-lg focus:ring-2 focus:ring-primary focus:outline-none"
                placeholder="HH:mm - HH:mm"
              ></el-input>
            </div>
          </div>

          <div>
            <label class="block text-sm font-bold text-slate-700 mb-1">平均就诊时长 (分钟)</label>
            <el-input
              required
              type="number"
              :min="1"
              v-model.number="form.avgVisitTime"
              class="w-full p-2.5 border border-slate-300 rounded-lg focus:ring-2 focus:ring-primary focus:outline-none"
            ></el-input>
          </div>

          <div class="col-span-2">
            <label class="block text-sm font-bold text-slate-700 mb-1">科室状态</label>
            <div class="flex gap-4 mt-2">
              <label class="flex items-center gap-2 cursor-pointer">
                <el-radio
                  v-model="form.status"
                  label="OPEN"
                  class="accent-primary"
                ></el-radio>
                <span class="text-sm">正常</span>
              </label>
              <label class="flex items-center gap-2 cursor-pointer">
                <el-radio
                  v-model="form.status"
                  label="MAINTENANCE"
                  class="accent-amber-500"
                ></el-radio>
                <span class="text-sm">维护</span>
              </label>
            </div>
          </div>
        </div>

        <div
          v-if="editingDept"
          class="bg-amber-50 text-amber-800 text-xs p-3 rounded-lg flex items-start gap-2 mt-4"
        >
          <i class="el-icon-warning text-amber-500 text-base flex-shrink-0 mt-0.5"></i>
          <div>
            <p class="font-bold">注意：</p>
            <p>修改“会诊时间”或“平均就诊时长”可能会影响未来的排班计划，请谨慎操作。</p>
          </div>
        </div>

        <div class="pt-4 flex justify-end gap-3 border-t border-slate-100 mt-4">
          <el-button
            type="text"
            @click="showModal = false"
            class="px-5 py-2.5 border border-slate-300 rounded-lg hover:bg-slate-50 text-slate-700 font-medium"
          >
            取消
          </el-button>
          <el-button
            type="primary"
            @click="handleSave"
            class="px-5 py-2.5 bg-primary text-white rounded-lg hover:bg-sky-600 font-medium shadow-lg shadow-sky-100"
          >
            保存信息
          </el-button>
        </div>
      </form>
    </el-dialog>
  </div>
</template>

<script>
import Vue from 'vue';
import { Message, MessageBox } from 'element-ui';

// 类型定义（Vue 2 不支持 TS 语法糖，用 JSDoc 补充）
/**
 * @typedef {Object} Department
 * @property {string} id
 * @property {string} name
 * @property {string} location
 * @property {string} consultationHours
 * @property {number} avgVisitTime
 * @property {'OPEN' | 'CLOSED' | 'MAINTENANCE'} status
 * @property {string} createdAt
 * @property {string} updatedAt
 * @property {number} patientCount
 * @property {boolean} isDeleted
 */

// 模拟数据
const MOCK_DEPARTMENTS = [
  {
    id: 'd1',
    name: '全科门诊',
    location: '门诊楼 2楼 A区',
    consultationHours: '08:00 - 17:30',
    avgVisitTime: 15,
    status: 'OPEN',
    createdAt: '2023-01-10',
    updatedAt: '2023-09-01',
    patientCount: 5,
    isDeleted: false,
  },
  {
    id: 'd2',
    name: '急诊科',
    location: '急诊楼 1楼',
    consultationHours: '00:00 - 24:00',
    avgVisitTime: 10,
    status: 'OPEN',
    createdAt: '2022-11-05',
    updatedAt: '2023-10-01',
    patientCount: 12,
    isDeleted: false,
  },
  {
    id: 'd3',
    name: '口腔科',
    location: '门诊楼 3楼 C区',
    consultationHours: '09:00 - 17:00',
    avgVisitTime: 30,
    status: 'MAINTENANCE',
    createdAt: '2023-03-15',
    updatedAt: '2023-10-20',
    patientCount: 0,
    isDeleted: false,
  },
  {
    id: 'd4',
    name: '儿科',
    location: '儿科楼 1楼',
    consultationHours: '08:30 - 18:00',
    avgVisitTime: 20,
    status: 'CLOSED',
    createdAt: '2023-05-20',
    updatedAt: '2023-08-15',
    patientCount: 0,
    isDeleted: true, // 已软删除
  },
];

export default Vue.extend({
  name: 'DepartmentManager',
  data() {
    return {
      // 科室数据
      departments: MOCK_DEPARTMENTS,
      // 筛选条件
      searchName: '',
      // 弹窗状态
      showModal: false,
      editingDept: null,
      // 分页
      page: 1,
      PAGE_SIZE: 5,
      // 表单数据
      form: {
        name: '',
        location: '',
        consultationHours: '08:00 - 17:00',
        avgVisitTime: 15,
        status: 'OPEN',
      },
    };
  },
  computed: {
    // 筛选后的科室列表
    filteredDepartments() {
      return this.departments.filter((dept) => {
        if (dept.isDeleted) return false; // 不显示已删除数据
        return dept.name.includes(this.searchName);
      });
    },
    // 总页数
    totalPages() {
      return Math.ceil(this.filteredDepartments.length / this.PAGE_SIZE);
    },
    // 当前页数据
    currentData() {
      const start = (this.page - 1) * this.PAGE_SIZE;
      const end = this.page * this.PAGE_SIZE;
      return this.filteredDepartments.slice(start, end);
    },
  },
  methods: {
    // 新增科室
    handleAdd() {
      this.editingDept = null;
      // 重置表单
      this.form = {
        name: '',
        location: '',
        consultationHours: '08:00 - 17:00',
        avgVisitTime: 15,
        status: 'OPEN',
      };
      this.showModal = true;
    },
    // 编辑科室
    handleEdit(dept) {
      this.editingDept = dept;
      // 填充表单
      this.form = {
        name: dept.name,
        location: dept.location,
        consultationHours: dept.consultationHours,
        avgVisitTime: dept.avgVisitTime,
        status: dept.status,
      };
      this.showModal = true;
    },
    // 保存科室（新增/编辑）
    handleSave() {
      // 检查名称重复
      const isDuplicate = this.departments.some(
        (d) =>
          d.name === this.form.name &&
          !d.isDeleted &&
          d.id !== (this.editingDept?.id || '')
      );

      if (isDuplicate) {
        Message.error(`错误：科室名称 "${this.form.name}" 已存在，请勿重复添加！`);
        return;
      }

      const deptData = {
        name: this.form.name,
        location: this.form.location,
        consultationHours: this.form.consultationHours,
        avgVisitTime: this.form.avgVisitTime,
        status: this.form.status,
        updatedAt: new Date().toISOString().split('T')[0],
      };

      if (this.editingDept) {
        // 编辑模式
        this.departments = this.departments.map((d) =>
          d.id === this.editingDept.id ? { ...d, ...deptData } : d
        );
        Message.success('科室信息修改成功！');
      } else {
        // 新增模式
        const newDept = {
          ...deptData,
          id: Date.now().toString(),
          createdAt: new Date().toISOString().split('T')[0],
          patientCount: 0,
          isDeleted: false,
        };
        this.departments.push(newDept);
        Message.success('新科室添加成功！');
      }

      this.showModal = false;
      this.editingDept = null;
    },
    // 删除科室（软删除）
    handleDelete(dept) {
      // 检查是否有人使用
      if (dept.patientCount > 0) {
        Message.warning(
          `无法删除：该科室当前有 ${dept.patientCount} 人 (患者/医护) 正在使用中。请先清空人员。`
        );
        return;
      }

      MessageBox.confirm(`确定要删除科室【${dept.name}】吗？(软删除)`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        this.departments = this.departments.map((d) =>
          d.id === dept.id ? { ...d, isDeleted: true } : d
        );
        Message.success('删除成功！');
      }).catch(() => {
        Message.info('已取消删除');
      });
    },
  },
});
</script>

<style scoped>
/* 自定义样式补充 */
:root {
  --primary: #1890ff; /* Element UI 主色 */
}

.text-primary {
  color: var(--primary);
}

.bg-primary {
  background-color: var(--primary);
}

.focus\:ring-primary:focus {
  --tw-ring-color: var(--primary);
}

.accent-primary {
  accent-color: var(--primary);
}

.accent-amber-500 {
  accent-color: #f59e0b;
}

/* Element UI 组件样式覆盖 */
.el-input__inner {
  border-radius: 0.5rem !important;
}

.el-button--primary {
  background-color: var(--primary) !important;
  border-color: var(--primary) !important;
  border-radius: 0.5rem !important;
}

.el-button--primary:hover {
  background-color: #40a9ff !important;
  border-color: #40a9ff !important;
}

.el-dialog__header {
  background-color: #f8f9fa !important;
  border-bottom: 1px solid #e5e7eb !important;
  padding: 1rem 1.5rem !important;
}

.el-dialog__body {
  padding: 1.5rem !important;
}

.el-dialog__footer {
  padding: 1rem 1.5rem !important;
  border-top: 1px solid #e5e7eb !important;
}

.el-radio__input.is-checked .el-radio__inner {
  background-color: var(--primary) !important;
  border-color: var(--primary) !important;
}

.el-radio__input.is-checked + .el-radio__label {
  color: var(--primary) !important;
}

.accent-amber-500 .el-radio__input.is-checked .el-radio__inner {
  background-color: #f59e0b !important;
  border-color: #f59e0b !important;
}

.accent-amber-500 .el-radio__input.is-checked + .el-radio__label {
  color: #f59e0b !important;
}
</style>