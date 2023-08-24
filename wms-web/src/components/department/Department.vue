<template>
  <div>
    <el-card>
      <tree-table ref="multipleTable" :border="true" :show-index="true" :expand-type="false" index-text="ID"
        :selection-type="false" :data="cateList" :columns="columns" :show-row-hover="true">
      </tree-table>
    </el-card>
  </div>
</template>

<!-- 分类表格 :data(设置数据源) :columns(设置表格中列配置信息) :selection-type(是否有复选框)
:expand-type(是否展开数据) show-index(是否设置索引列) index-text(设置索引列头) border(是否添加纵向边框)
 :show-row-hover(是否鼠标悬停高亮) -->

<script>
export default {
  name: "Department",
  data() {
    return {
      // tableData:[],
      // 商品分类的数据列表
      cateList: [],
      // 为table指定列的定义
      defaultProps: {
        children: 'children',
        label: 'name',
        parent: 'parent'
      },
      columns: [
        {
          label: "部门",
          prop: "department",
          width: "400px",
        },
        {
          label: "角色",
          prop: "role",
          minWidth: "50px",
        },
        {
          label: "姓名",
          prop: "name",
          minWidth: "50px",
        },
      ],
    };
  },
  computed: {
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.user = JSON.parse(sessionStorage.getItem('CurUser'))
    },
    mod(row) {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        //赋值到表单
        this.form.id = row.id
        this.form.name = row.name
        this.form.role = row.role
        this.form.pid = row.pid
      })
    },
    loadPost() {
      this.$axios.get(this.$httpUrl + '/tree', {
        param: {
          name: this.name
        }
      }).then(res => res.data).then(res => {
        console.log(res),
          this.cateList = res
        res.forEach((node) => {
          if (!node.parent) {
            node.role = null;
            node.department = node.name;
            node.name = null;
          }
        });
      })
    }
  },
  beforeMount() {
    this.loadPost()
  }
};
</script>

