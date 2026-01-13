<template>
  <div class="app-container">
    <el-card>
      <div slot="header" class="clearfix">
        <span>技能分类积分规则配置</span>
      </div>

      <!-- 表格：每行一条规则 -->
      <el-table :data="rules" border style="width: 100%">
        <el-table-column label="技能分类" prop="categoryName" width="150" />
        <el-table-column label="角色类型" width="120">
          <template #default="{ row }">
            <span>{{ row.roleType === 1 ? '服务提供者' : '学习者' }}</span>
          </template>
        </el-table-column>
        <el-table-column label="积分值" width="150">
          <template #default="{ row }">
            <el-input
              v-model.number="row.pointValue"
              size="mini"
              type="number"
              :min="0"
            />
          </template>
        </el-table-column>
        <el-table-column label="启用" width="80">
          <template #default="{ row }">
            <el-switch v-model="row.isActive" />
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="{ row }">
            <el-button size="mini" type="danger" @click="deleteRule(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin-top: 10px; text-align: right;">
        <el-button type="primary" @click="addProviderRule">新增提供者规则</el-button>
        <el-button type="primary" @click="addLearnerRule">新增学习者规则</el-button>
        <el-button type="success" @click="saveRules">保存</el-button>
      </div>

      <!-- 新增规则对话框 -->
      <el-dialog
        :title="newRuleForm.roleType === 1 ? '新增服务提供者规则' : '新增学习者规则'"
        :visible.sync="dialogVisible"
        width="400px"
      >
        <el-form :model="newRuleForm" label-width="80px">
          <el-form-item label="技能分类">
            <el-select v-model="newRuleForm.categoryId" @change="handleCategoryChange">
              <el-option
                v-for="cat in availableCategories"
                :key="cat.id"
                :label="cat.name"
                :value="cat.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="积分值">
            <el-input-number
              v-model="newRuleForm.pointValue"
              :min="0"
              controls-position="right"
            />
          </el-form-item>
          <el-form-item label="启用">
            <el-switch v-model="newRuleForm.isActive" />
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="confirmAddRule">确定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
import { listCategory } from '@/api/student/category'
import { getAllPointRules, savePointRules } from '@/api/student/pointRule'

export default {
  name: 'PointRuleConfig',
  data() {
    return {
      rules: [],
      categories: [],
      dialogVisible: false,
      newRuleForm: {
        categoryId: null,
        categoryName: '',
        roleType: 1, // 1=提供者, 2=学习者
        pointValue: 0,
        isActive: true
      },
      availableCategories: []
    }
  },
  async created() {
    this.initData()
  },
  methods: {
    async loadCategories() {
      const res = await listCategory({ pageNum: 1, pageSize: 1000 });
      this.categories = res.rows || []; 
    },
    async initData() {
      await this.loadCategories();
      await this.loadRules();
    },
    async loadRules() {
      const res = await getAllPointRules();
      this.rules = res.data || [];
    },
  deleteRule(row) {
  this.$confirm('确定删除该规则？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(()=>{ 
    this.rules = this.rules.filter(r => r !== row);
  });
},
  async saveRules() {
  try {
    const payload = this.rules.map(r => ({
      id: r.id,
      categoryId: r.categoryId,
      roleType: r.roleType,
      pointValue: r.pointValue,
      isActive: r.isActive
    }));

    await savePointRules(payload);
    this.$message.success('保存成功');
  } catch (error) {
    console.error('保存失败:', error);
    this.$message.error('保存失败，请重试');
  }
},
    addProviderRule() {
      this.openAddDialog(1);
    },
    addLearnerRule() {
      this.openAddDialog(2);
    },
    openAddDialog(roleType) {
      const configuredCategoryIds = this.rules
        .filter(r => r.roleType === roleType)
        .map(r => r.categoryId);

      this.availableCategories = this.categories.filter(
        cat => !configuredCategoryIds.includes(cat.id)
      );

      if (this.availableCategories.length === 0) {
        this.$message.warning('所有技能分类已配置');
        return;
      }

      this.newRuleForm = {
        categoryId: this.availableCategories[0].id,
        categoryName: this.availableCategories[0].name,
        roleType: roleType,
        pointValue: 0,
        isActive: true
      };
      this.dialogVisible = true;
    },
    handleCategoryChange(categoryId) {
      const category = this.availableCategories.find(cat => cat.id === categoryId);
      if (category) {
        this.newRuleForm.categoryName = category.name;
      }
    },
    confirmAddRule() {
      const form = this.newRuleForm;
      if (!form.categoryId) {
        this.$message.warning('请选择技能分类');
        return;
      }

      this.rules.push({
        id: null,
        categoryId: form.categoryId,
        categoryName: form.categoryName,
        roleType: form.roleType,
        pointValue: form.pointValue,
        isActive: form.isActive
      });

      this.dialogVisible = false;
      this.$message.success('规则已添加，请点击【保存】提交');
    }
  }
}
</script>