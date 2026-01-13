<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发布者ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入发布者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技能标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入技能标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="封面图" prop="coverImg">
        <el-input
          v-model="queryParams.coverImg"
          placeholder="请输入封面图"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
     
      <el-form-item label="可预约时间段" prop="availableTime">
        <el-input
          v-model="queryParams.availableTime"
          placeholder="请输入可预约时间段"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="每小时可获积分" prop="pointsPerHour">
        <el-input
          v-model="queryParams.pointsPerHour"
          placeholder="请输入每小时可获积分"
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
          v-hasPermi="['student:service:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['student:service:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:service:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="serviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="技能ID" align="center" prop="id" />
      <el-table-column label="发布者ID" align="center" prop="userId" />
      <el-table-column label="技能标题" align="center" prop="title" />
<el-table-column label="详细描述" align="center" width="200">
  <template slot-scope="scope">
    <div class="description-wrap" :title="scope.row.description">
      {{ scope.row.description }}
    </div>
  </template>
</el-table-column>

      <el-table-column label="封面图" align="center" prop="coverImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.coverImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="标准分类ID" align="center" prop="categoryId" />
      <el-table-column label="可预约时间段" align="center" width="180">
  <template slot-scope="scope">
    <div v-if="scope.row.availableTime !== null && scope.row.availableTime !== undefined">
      <span v-for="(time, index) in parseAvailableTime(scope.row.availableTime)" :key="index">
        {{ time }}
        <span v-if="index < parseAvailableTime(scope.row.availableTime).length - 1">, </span>
      </span>
    </div>
    <span v-else>-</span>
  </template>
</el-table-column>
      <el-table-column label="每小时可获积分" align="center" prop="pointsPerHour" />
      <el-table-column label="审核状态：0=待审，1=通过，2=拒绝" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
  <template slot-scope="scope">
    <!-- 仅当状态为“待审核”(0) 时，才显示审核按钮 -->
    <template v-if="scope.row.status === 0">
      <el-button
        size="mini"
        type="success"
        @click="handleAuditPass(scope.row.id)"
        v-hasPermi="['student:service:audit']"
      >通过</el-button>
      <el-button
        size="mini"
        type="danger"
        @click="handleAuditReject(scope.row.id)"
        v-hasPermi="['student:service:audit']"
      >拒绝</el-button>
    </template>


    <el-button
      size="mini"
      type="text"
      icon="el-icon-delete"
      @click="handleDelete(scope.row)"
      v-hasPermi="['student:service:remove']"
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

  </div>
</template>

<script>
import { listService, delService, addService, auditPass, auditReject } from "@/api/student/service"
import { parseAvailableTime } from '@/utils/html'
export default {
  name: "Service",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 技能服务表格数据
      serviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        title: null,
        description: null,
        coverImg: null,
        categoryId: null,
        availableTime: null,
        pointsPerHour: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "发布者ID不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "技能标题不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "详细描述不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "标准分类ID不能为空", trigger: "change" }
        ],
        pointsPerHour: [
          { required: true, message: "每小时可获积分不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "审核状态：0=待审，1=通过，2=拒绝不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    parseAvailableTime,
    /** 审核通过 */
handleAuditPass(id) {
  this.$modal.confirm('确认通过该技能服务？').then(() => {
    return auditPass(id);
  }).then(() => {
    this.$modal.msgSuccess("审核通过");
    this.getList();
  }).catch(error => {
    console.error('【审核通过失败】', error);
    // 可选：显示错误提示
    this.$modal.msgError("操作失败，请重试");
  });
},

/** 审核拒绝 */
handleAuditReject(id) {
  this.$modal.confirm('确认拒绝该技能服务？').then(() => {
    return auditReject(id);
  }).then(() => {
    this.$modal.msgSuccess("已拒绝");
    this.getList();
  }).catch(error => {
    console.error('【审核拒绝失败】', error);
    this.$modal.msgError("操作失败，请重试");
  });
},
    /** 查询技能服务列表 */
    getList() {
      this.loading = true
      listService(this.queryParams).then(response => {
        this.serviceList = response.rows
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
        userId: null,
        title: null,
        description: null,
        coverImg: null,
        categoryId: null,
        customCategory: null,
        availableTime: null,
        pointsPerHour: null,
        status: null,
        createdAt: null
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

      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加技能服务"
    },
    /** 提交按钮 */
/** 提交按钮（仅新增） */
submitForm() {
  this.$refs["form"].validate(valid => {
    if (valid) {
      addService(this.form).then(response => {
        this.$modal.msgSuccess("新增成功")
        this.open = false
        this.getList()
      })
    }
  })
},
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除技能服务编号为"' + ids + '"的数据项？').then(function() {
        return delService(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/service/export', {
        ...this.queryParams
      }, `service_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
<style>
.description-wrap {
  word-break: break-word;           /* 长单词自动换行 */
  line-height: 1.4;                /* 行高更舒适 */
  max-height: 60px;               /* 限制最大高度 */
  overflow-y: auto;               /* 超出显示滚动条 */
  padding: 4px;
  background-color: #f9f9f9;
  border-radius: 4px;
  font-size: 12px;
  white-space: normal;            /* 允许换行 */
}
</style>