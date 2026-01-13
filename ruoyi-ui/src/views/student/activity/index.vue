<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入活动ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入活动标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="活动开始时间" prop="startTime">
        <el-date-picker
          clearable
          v-model="queryParams.startTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动结束时间" prop="endTime">
        <el-date-picker
          clearable
          v-model="queryParams.endTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入活动地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大参与人数" prop="maxParticipants">
        <el-input
          v-model="queryParams.maxParticipants"
          placeholder="请输入最大参与人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前已报名人数" prop="currentParticipants">
        <el-input
          v-model="queryParams.currentParticipants"
          placeholder="请输入当前已报名人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参与奖励积分" prop="pointsReward">
        <el-input
          v-model="queryParams.pointsReward"
          placeholder="请输入参与奖励积分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_job_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="组织者ID" prop="organizerId">
        <el-input
          v-model="queryParams.organizerId"
          placeholder="请输入组织者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdAt">
        <el-date-picker clearable
                        v-model="queryParams.createdAt"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择创建时间">
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
          v-hasPermi="['student:activity:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['student:activity:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['student:activity:remove']"
        >删除
        </el-button>
      </el-col>
<el-col :span="1.5">
  <el-button
    type="info"
    plain
    icon="el-icon-remove-outline"
    size="mini"
    :disabled="multiple"
    @click="handleHide"
    v-hasPermi="['student:activity:remove']"
  >下架
  </el-button>
</el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:activity:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="活动ID" align="center" prop="id"/>
      <el-table-column label="活动标题" align="center" prop="title"/>
      <el-table-column label="活动详情描述" align="center" prop="description"/>

      <el-table-column label="活动封面图" align="center" prop="coverImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.coverImg" :width="50" :height="50"/>
        </template>
      </el-table-column>

      <el-table-column label="活动开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动地点" align="center" prop="location"/>
      <el-table-column label="最大参与人数" align="center" prop="maxParticipants"/>
      <el-table-column label="当前已报名人数" align="center" prop="currentParticipants"/>
      <el-table-column label="参与奖励积分" align="center" prop="pointsReward"/>
      <el-table-column label="状态：0=已结束，1=进行中，2=已取消,3=已下架" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_job_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="组织者ID" align="center" prop="organizerId"/>
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:activity:edit']"
          >修改
          </el-button>
    <el-button
    size="mini"
    type="text"
    icon="el-icon-remove-outline"
    @click="handleHide(scope.row)"
    v-hasPermi="['student:activity:remove']"
    v-if="scope.row.status !== 3"
   >下架</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:activity:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改志愿活动/讲座对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入活动标题"/>
        </el-form-item>
        <el-form-item label="活动详情描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容"/>
        </el-form-item>

        <el-form-item label="活动封面图" prop="coverImg">
          <image-upload v-model="form.coverImg"/>
        </el-form-item>
        <el-form-item label="活动开始时间" prop="startTime">
          <el-date-picker
            clearable
            v-model="form.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择开始时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动结束时间" prop="endTime">
          <el-date-picker
            clearable
            v-model="form.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择结束时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入活动地点"/>
        </el-form-item>
        <el-form-item label="最大参与人数" prop="maxParticipants">
          <el-input v-model="form.maxParticipants" placeholder="请输入最大参与人数"/>
        </el-form-item>
        <el-form-item label="参与奖励积分" prop="pointsReward">
          <el-input v-model="form.pointsReward" placeholder="请输入参与奖励积分"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_job_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="组织者ID" prop="organizerId">
          <el-input v-model="form.organizerId" placeholder="请输入组织者ID"/>
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
import {listActivity, getActivity, delActivity, addActivity, updateActivity,hideActivity} from "@/api/student/activity"

export default {
  name: "Activity",
  dicts: ['sys_job_status'],
  data() {
    return {
      uploadUrl: process.env.VUE_APP_BASE_API + '/common/upload', // 修正为标准上传接口
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
      // 志愿活动/讲座表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数

      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        title: null,
        description: null,
        coverImg: null,
        startTime: null,
        endTime: null,
        location: null,
        maxParticipants: null,
        currentParticipants: null,
        pointsReward: null,
        status: null,
        organizerId: null,
        createdAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          {required: true, message: "活动标题不能为空", trigger: "blur"}
        ],
        startTime: [
          {required: true, message: "活动开始时间不能为空", trigger: "blur"}
        ],
        endTime: [
          {required: true, message: "活动结束时间不能为空", trigger: "blur"}
        ],
        status: [
          {required: true, message: "状态", trigger: "change"}
        ],
        organizerId: [
          {required: true, message: "组织者ID不能为空", trigger: "blur"}
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 图片上传成功后回调
    beforeUpload(file) {
      const isValid = ['image/jpeg', 'image/png'].includes(file.type);
      if (!isValid) {
        this.$message.error('只支持 JPG/PNG');
      }
      return isValid;
    },
    // 封面图上传成功

    handleCoverSuccess(response) {
      if (response.code === 200) {
        this.form.coverImg = response.fileName; // 使用 fileName 而不是 data
        this.$message.success("封面图上传成功");
      } else {
        this.$message.error(response.msg || "上传失败");
      }
    },
    /** 查询志愿活动/讲座列表 */
    getList() {
      this.loading = true
      listActivity(this.queryParams).then(response => {
        this.activityList = response.rows
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
        title: null,
        description: null,
        coverImg: null,
        startTime: null,
        endTime: null,
        location: null,
        maxParticipants: null,
        currentParticipants: null,
        pointsReward: null,
        status: null,
        organizerId: null,
        createdAt: null
      };
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
      this.title = "添加志愿活动/讲座"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getActivity(id).then(response => {
        const data = response.data;
        this.form = {
          id: data.id,
          title: data.title,
          description: data.description,
          coverImg: data.coverImg,
          startTime: data.startTime, 
          endTime: data.endTime,       
          location: data.location,
          maxParticipants: data.maxParticipants,
          currentParticipants: data.currentParticipants,
          pointsReward: data.pointsReward,
          status: data.status,
          organizerId: data.organizerId,
          createdAt: data.createdAt
        };

        this.open = true;
        this.title = "修改志愿活动/讲座";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateActivity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addActivity(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 下架按钮操作 */
handleHide(row) {
  const ids = row.id || this.ids;
  if (!ids || (Array.isArray(ids) && ids.length === 0)) {
    this.$modal.msgWarning("请先选择要下架的活动");
    return;
  }

  this.$modal.confirm('是否确认下架选中的活动？下架后用户将无法查看和报名，但数据会保留。')
    .then(() => {
      return hideActivity(ids); // 调用新 API
    })
    .then(() => {
      this.getList();
      this.$modal.msgSuccess("下架成功");
    })
    .catch(() => {});
},
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除志愿活动/讲座编号为"' + ids + '"的数据项？').then(function () {
        return delActivity(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/activity/export', {
        ...this.queryParams
      }, `activity_${new Date().getTime()}.xlsx`)
    },
  }
}
</script>
