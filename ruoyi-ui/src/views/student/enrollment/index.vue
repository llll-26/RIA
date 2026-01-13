<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="报名ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入报名ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入报名用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动ID" prop="activityId">
        <el-input
          v-model="queryParams.activityId"
          placeholder="请输入活动ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名时间" prop="enrolledAt">
        <el-date-picker clearable
          v-model="queryParams.enrolledAt"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择报名时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="完成时间" prop="completedAt">
        <el-date-picker clearable
          v-model="queryParams.completedAt"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择完成时间">
        </el-date-picker>
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
          v-hasPermi="['student:enrollment:add']"
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
          v-hasPermi="['student:enrollment:edit']"
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
          v-hasPermi="['student:enrollment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:enrollment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enrollmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报名ID" align="center" prop="id" />
      <el-table-column label="报名用户ID" align="center" prop="userId" />
      <el-table-column label="活动ID" align="center" prop="activityId" />
      <el-table-column label="报名状态：0=已报名，1=已取消，2=已完成" align="center" prop="status" />
      <el-table-column label="报名时间" align="center" prop="enrolledAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrolledAt, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="completedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completedAt, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
  <template slot-scope="scope">
    <el-button
      size="mini"
      type="text"
      icon="el-icon-edit"
      @click="handleUpdate(scope.row)"
      v-hasPermi="['student:enrollment:edit']"
    >修改</el-button>
    
    <el-button
      size="mini"
      type="text"
      icon="el-icon-delete"
      @click="handleDelete(scope.row)"
      v-hasPermi="['student:enrollment:remove']"
    >删除</el-button>

    <!-- 管理员完成按钮（仅当未完成时显示） -->
    <el-button
      v-if="scope.row.status !== 2"
      size="mini"
      type="text"
      style="color: #E6A23C"
      @click="handleAdminComplete(scope.row)"
    >完成活动</el-button>
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

    <!-- 添加或修改用户活动报名记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- 完成时间（仅当状态为“已完成”时才需要） -->
        <el-form-item label="报名状态" prop="status">
  <el-select v-model="form.status" placeholder="请选择状态">
    <el-option :value="0" label="已报名"></el-option>
    <el-option :value="1" label="已取消"></el-option>
    <!-- 不再提供 “已完成” 选项 -->
  </el-select>
</el-form-item>
    <el-form-item label="完成时间" prop="completedAt">
      <el-date-picker
        v-model="form.completedAt"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择完成时间">
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
import { listEnrollment, getEnrollment, delEnrollment, addEnrollment, updateEnrollment,adminCompleteEnrollment} from "@/api/student/enrollment"
import axios from 'axios'
export default {
  name: "Enrollment",
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
      // 用户活动报名记录表格数据
      enrollmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        userId: null,
        activityId: null,
        status: null,
        enrolledAt: null,
        completedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        status: [
          { required: true, message: "报名状态：0=已报名，1=已取消，2=已完成不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询用户活动报名记录列表 */
    getList() {
      this.loading = true
      listEnrollment(this.queryParams).then(response => {
        this.enrollmentList = response.rows
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
        activityId: null,
        status: null,
        enrolledAt: null,
        completedAt: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加用户活动报名记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getEnrollment(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改用户活动报名记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEnrollment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addEnrollment(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户活动报名记录编号为"' + ids + '"的数据项？').then(function() {
        return delEnrollment(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/enrollment/export', {
        ...this.queryParams
      }, `enrollment_${new Date().getTime()}.xlsx`)
    },
                // 管理员完成活动
// 在 methods 中
handleAdminComplete(row) {
  this.$confirm(`确认完成该活动？完成后将自动发放积分。`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    adminCompleteEnrollment(row.id).then(response => {
      this.$message.success(response.msg);
      this.getList(); // 刷新列表
    }).catch(error => {
      console.error('请求失败:', error);
      const msg = error.response?.data?.msg || error.message || '操作失败';
      this.$message.error(msg);
    });
  });
}
               
  }
}
</script>
