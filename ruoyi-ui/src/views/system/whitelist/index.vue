<template>
<div class="app-container"> 
    <!-- 导入按钮 -->
<el-button
  type="info"
  icon="upload"
  size="mini"
  @click="handleImport"
>
  导入
</el-button>
<!-- 查看列表 -->
  <el-table :data="list" style="margin-top: 20px;" border>
    <el-table-column prop="studentId" label="学号" width="150"></el-table-column>
    <el-table-column prop="deptId" label="学院ID" width="100"></el-table-column>
    <el-table-column prop="major" label="专业" width="200"></el-table-column>
    <el-table-column prop="importBatch" label="导入批次" width="180"></el-table-column>
  </el-table>
<!-- 导入对话框（可选） -->
<el-dialog :visible.sync="upload.visible" title="导入白名单" width="400px">
  <el-upload
    ref="upload"
    :auto-upload="false"
    :on-change="handleChange"
    :file-list="upload.fileList"
    accept=".xlsx, .xls"
    drag
  >
    <i class="el-icon-upload"></i>
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div slot="tip" class="el-upload__tip">仅支持 .xls / .xlsx 格式</div>
  </el-upload>
  <div slot="footer" class="dialog-footer">
    <el-button type="primary" @click="submitImport">确 定</el-button>
    <el-button @click="upload.visible = false">取 消</el-button>
  </div>
</el-dialog>
</div>
</template>
<script>
// 引入 API
import { importWhitelist,downloadTemplate,getWhitelistList } from "@/api/system/whitelist";

export default {
  data() {
    return {
      list: [], // 存储表格数据
      upload: {
        visible: false,
        fileList: []
      }
    };
  },
  created() {
    this.getList(); // 页面加载时自动获取数据
  },
  methods: {
// 获取白名单列表
  getList() {
  getWhitelistList().then(response => {
    this.list = response.data;
  });
},
    // 点击“导入”按钮
    handleImport() {
      this.upload.fileList = [];
      this.upload.visible = true;
    },

    // 文件选择变化
    handleChange(file, fileList) {
      this.upload.fileList = fileList.slice(-1); // 只保留最后一个文件
    },

    // 提交导入
   submitImport() {
  const file = this.upload.fileList[0];
  if (!file) {
    this.$modal.msgError("请选择文件");
    return;
  }

  const formData = new FormData();
  formData.append("file", file.raw);

  importWhitelist(formData)
    .then(response => {
      this.$modal.msgSuccess(response.msg); // 弹“导入完成！共处理 X 条”
      this.upload.visible = false;           // 关闭对话框
      this.getList(); // 刷新表格
    })
    .catch(error => {
      console.error("导入失败", error);
      this.$modal.msgError("导入失败：" + (error.response?.data?.msg || "请检查文件格式"));
    });
},
    downloadTemplate() {
    downloadTemplate().then(response => {
      const blob = new Blob([response]);
      const link = document.createElement('a');
      link.href = URL.createObjectURL(blob);
      link.download = '学生白名单导入模板.xlsx';
      link.click();
      URL.revokeObjectURL(link.href);
    });
  }
  }
};

</script>
