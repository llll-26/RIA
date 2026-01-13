<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="80px"
    >
      <el-form-item label="奖品ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入奖品ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="奖品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入奖品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所需积分" prop="pointsRequired">
        <el-input
          v-model="queryParams.pointsRequired"
          placeholder="请输入所需积分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库存" prop="stock">
        <el-input
          v-model="queryParams.stock"
          placeholder="请输入库存"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否上架" prop="isActive">
        <el-select v-model="queryParams.isActive" placeholder="请选择" clearable>
          <el-option :value="true" label="是" />
          <el-option :value="false" label="否" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['student:item:add']"
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
          v-hasPermi="['student:item:edit']"
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
          v-hasPermi="['student:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:item:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 数据表格 -->
    <el-table
      v-loading="loading"
      :data="itemList"
      @selection-change="handleSelectionChange"
      row-key="id"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="奖品ID" align="center" prop="id" />
      <el-table-column label="奖品名称" align="center" prop="name" />
      <el-table-column label="所需积分" align="center" prop="pointsRequired" />
      <el-table-column label="类型" align="center" prop="type" />
      <el-table-column label="库存" align="center" prop="stock" />
      <el-table-column label="是否上架" align="center" prop="isActive">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isActive" type="success">是</el-tag>
          <el-tag v-else type="info">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="详细说明" align="center" prop="description" show-overflow-tooltip />
      <el-table-column label="奖品图片" align="center" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.imageUrl" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:item:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:item:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 新增/编辑对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="奖品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入奖品名称" />
        </el-form-item>
        <el-form-item label="所需积分" prop="pointsRequired">
          <el-input-number
            v-model="form.pointsRequired"
            :min="0"
            controls-position="right"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input-number
            v-model="form.stock"
            :min="0"
            controls-position="right"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="是否上架" prop="isActive">
          <el-switch
  v-model="form.isActive"
  :active-value="1"
  :inactive-value="0"
  active-text="是"
  inactive-text="否"
/>
        </el-form-item>
        <el-form-item label="详细说明" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入说明" />
        </el-form-item>
        <el-form-item label="奖品图片" prop="imageUrl">
          <image-upload v-model="form.imageUrl" />
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
import {listItem, getItem, delItem, addItem, updateItem,hideItem} from "@/api/student/item"

export default {
  name: "Item",
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
      // 兑换奖品项表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        name: null,
        pointsRequired: null,
        type: null,
        stock: null,
        isActive: null,
        description: null,
        imageUrl: null,
      },
      // 表单参数
      // 表单参数
      form: {
        id: null,
        name: null,
        pointsRequired: null,
        type: null,
        stock: null,
        isActive: null,
        description: null,
        imageUrl: null
      },
      // 表单校验
      rules: {
        id: [
          {required: true, message: "奖品ID不能为空", trigger: "blur"}
        ],
        name: [
          {required: true, message: "奖品名称不能为空", trigger: "blur"}
        ],
        pointsRequired: [
          {required: true, message: "所需积分不能为空", trigger: "blur"}
        ],
        type: [
          {required: true, message: "类型不能为空", trigger: "change"}
        ],
        stock: [
          {required: true, message: "库存不能为空", trigger: "blur"}
        ],
        isActive: [
          {required: true, message: "是否上架不能为空", trigger: "blur"}
        ],
        description: [
          {required: true, message: "详细说明不能为空", trigger: "blur"}
        ],
        imageUrl: [
          {required: true, message: "奖品图片URL不能为空", trigger: "blur"}
        ]
  
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleHide(row) {
  const ids = row ? [row.id] : this.ids;
  if (!ids || ids.length === 0) {
    this.$modal.msgWarning("请先选择要下架的奖品");
    return;
  }

  this.$modal.confirm('是否确认下架选中的奖品？下架后用户将无法兑换，但历史记录不受影响。')
    .then(() => {
      return hideItem(ids);
    })
    .then(() => {
      this.getList();
      this.$modal.msgSuccess("下架成功");
    })
    .catch(() => {});
},
    /** 查询兑换奖品项列表 */
 // 假设这是你的 getList 方法
getList() {
  //构造查询参数副本并转换 isActive
  const params = { ...this.queryParams };
  if (params.isActive === true) {
    params.isActive = 1;
  } else if (params.isActive === false) {
    params.isActive = 0;
  } else if (params.isActive == null) {
  
    delete params.isActive;
  }

  this.loading = true;
  listItem(params).then(response => {
    this.itemList = response.rows;
    this.total = response.total;
    this.loading = false;
  });
},
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form.id = null;
      this.form.name = null;
      this.form.pointsRequired = null;
      this.form.type = null;
      this.form.stock = null;
      this.form.isActive = null;
      this.form.description = null;
      this.form.imageUrl = null;
      this.resetForm("form");
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
      this.title = "添加兑换奖品项"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getItem(id).then(response => {

        const data = response.data;
        this.form.id = data.id;
        this.form.name = data.name;
        this.form.pointsRequired = data.pointsRequired;
        this.form.type = data.type;
        this.form.stock = data.stock;
        this.form.isActive = data.isActive;
        this.form.description = data.description;
        this.form.imageUrl = data.imageUrl; // 确保 imageUrl 是响应式字段

        this.open = true;
        this.title = "修改兑换奖品项";
      });
    },
    /** 提交按钮 */
    /** 提交按钮 */
submitForm() {
  this.$refs["form"].validate(valid => {
    if (valid) {
      if (this.form.id != null) {
        updateItem(this.form).then(response => {
          this.$modal.msgSuccess("修改成功");
          this.open = false;
          this.getList();
        });
      } else {
        addItem(this.form).then(response => {
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
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除兑换奖品项编号为"' + ids + '"的数据项？').then(function () {
        return delItem(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/item/export', {
        ...this.queryParams
      }, `item_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
