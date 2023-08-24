<template>
  <div>
    <div style="margin-bottom: 5px">

      <div class="sphere">
        <div>
          <el-input v-model="name" maxlength="5" placeholder="请输入姓名" suffix-icon="el-icon-search" style="width: 110px"
                    @keyup.enter.native="loadPost">
          </el-input>
          <el-select v-model="sex" placeholder="请选择性别" style="width:120px ;margin-left: 5px ">
            <el-option v-for="item in sexs" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
          <!-- :disabled="this.sels.length === 0" 如果没有数据让删除按钮失效 -->
          <el-button type="primary" style=" margin-left: 5px" @click="loadPost">查询</el-button>
          <el-button type="success" @click="resetParam">重置</el-button>
          <el-button type="primary" style=" margin-left: 5px" @click="add">新增</el-button>
          <el-button type="danger" @click="batchDelect" :disabled="this.sels.length === 0">批量删除</el-button>

        </div>
        <div class="TableList">
          <el-table :data="tableData" :header-cell-style="{ background: '#f2f5fc', color: '#555' }" border
                    @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="no" label="账号" width="180">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="80">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="80">
              <template slot-scope="scope">
                <el-tag :type="scope.row.sex === 1 ? 'primary' : 'success'" disable-transitions>{{ scope.row.sex === 1 ?
                    '男' : '女' }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="roleId" label="角色" width="120">
              <template slot-scope="scope">
                <el-tag :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
                        disable-transitions>{{ scope.row.roleId === 0 ? '超级管理员' :
                    (scope.row.roleId === 1 ? '管理员' : '普通用户') }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="phone" label="电话" width="180">
            </el-table-column>

            <el-table-column prop="operate" label="操作">
              <template slot-scope="scope">
                <el-button type="success" size=small style="margin-left:60px" @click="mod(scope.row)">编辑</el-button>
                <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)" style="margin-left: 5px">
                  <el-button slot="reference" type="danger" size="small" style="margin-left: 30px">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>

          </el-table>
          <!--分页-->
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
                         :page-sizes="[6, 12]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
          </el-pagination>

          <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
            <!--        :before-close="handleClose">-->

            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
              <el-form-item label="账号" prop="no">
                <el-col :span="20">
                  <el-input v-model="form.no" maxlength="10"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item label="新密码" prop="pwd_new1">
                <el-col :span="20">
                  <el-input v-model="form.pwd_new1" maxlength="10"></el-input>
                </el-col>
              </el-form-item>
            </el-form>
            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
              <el-form-item label="确认新密码" prop="pwd_new2">
                <el-col :span="20">
                  <el-input v-model="form.pwd_new2" maxlength="10"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item label="姓名" prop="name">
                <el-col :span="20">
                  <el-input v-model="form.name" maxlength="10"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item label="年龄" prop="age">
                <el-col :span="20">
                  <el-input v-model="form.age" maxlength="10"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item label="性别">
                <el-radio-group v-model="form.sex">
                  <el-radio label="1">男</el-radio>
                  <el-radio label="0">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="电话" prop="phone">
                <el-col :span="20">
                  <el-input v-model="form.phone" maxlength="11"></el-input>
                </el-col>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </span>
          </el-dialog>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { forEach } from "core-js/stable/dom-collections";

export default {
  name: "AdminManage",
  /*------------------------数据--------------------------*/
  data() {
    // 检查账号是否重复
    let checkDuplicate = (rule, value, callback) => {
      if (this.form.id) {
        return callback();
      }
      this.$axios.get(this.$httpUrl + "/user/findByNo?no=" + this.form.no).then(res => res.data).then(res => {
        if (res.code != 200) {
          callback()
        } else {
          callback(new Error('账号已经存在'));
        }
      })
    };
    let checkAge = (rule, value, callback) => {
      if (value > 150) {
        callback(new Error('年龄输⼊过⼤'));
      } else {
        callback();
      } 0
    };
    return {
      sels: [], //当前选框选中的值
      // 默认数值
      tableData: [],
      pageSize: 12,
      pageNum: 1,
      total: 0,
      name: '',
      sex: '',
      sexs: [
        {
          value: '1',
          label: '男'
        }, {
          value: '0',
          label: '女'
        }
      ],
      dialogVisible: false,
      form: {
        id: '',
        name: '',
        no: '',
        password: '',
        age: '',
        phone: '',
        sex: '0',
        roleId: '1',
        pwd_new1: '',
        pwd_new2: ''
      },
      rules: {
        no: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur' },
          { validator: checkDuplicate, trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输⼊年龄', trigger: 'blur' },
          { min: 1, max: 3, message: '⻓度在 1 到 3 个位', trigger: 'blur' },
          { pattern: /^([1-9][0-9]*){1,3}$/, message: '年龄必须为正整数字', trigger: "blur" },
          { validator: checkAge, trigger: 'blur' }
        ],
        phone: [
          { required: true, message: "⼿机号不能为空", trigger: "blur" },
          { pattern: /^1[2|3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输⼊正确的⼿机号码", trigger: "blur" }
        ],
        pwd_new1: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur' }
        ]
        , pwd_new2: [
          { required: true, message: '请确认新密码', trigger: 'blur' },
          { min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur' }
        ]
      }
    }
  },

  /*------------------------方法--------------------------*/
  methods: {
    //获取选中的值
    handleSelectionChange(sels) {
      this.sels = sels;
      console.log("选中的值", sels.map((item) => item.id));
    },
    //批量删除执行操作
    batchDelect() {
      // 删除前的提示
      this.$confirm("确认删除记录吗?", "提示", {
        type: "warning",
      }).then(() => {
        let ids = this.sels.map((item) => item.id);
        // 根据后台想要的参数格式选择
        console.log(ids.join(",")); //1,2,3,4
        console.log(ids); //[1,2,3,4]
        for (const id of ids) {
          console.log("id=" + id)
          this.del(id)
        }
      });
    },
    // 验证信息
    resetForm() {
      this.$refs.form.resetFields();
    },
    /*删除*/
    del(id) {
      console.log(id)
      this.$axios.get(this.$httpUrl + '/user/del?id=' + id).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.loadPost()
        } else {
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }
      })

    },
    /*编辑*/
    mod(row) {
      console.log(row)
      // 展示窗口
      this.dialogVisible = true
      this.$nextTick(() => {
        // 附值到表单
        this.form.id = row.id
        this.form.no = row.no
        this.form.name = row.name
        this.form.password = ''
        this.form.age = row.age + ''
        this.form.sex = row.sex + ''
        this.form.phone = row.phone
        this.form.roleId = row.roleId
      })
    },
    // 新增
    add() {
      this.dialogVisible = true
      this.$nextTick(() => {
        // 异步
        this.resetForm();
      })
    },
    doSave() {
      this.$axios.post(this.$httpUrl + '/user/save', this.form).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.$message({
            message: '添加成功',
            type: 'success'
          });
          this.dialogVisible = false
          // 重新加载数据
          this.loadPost()
          this.resetForm()
        } else {
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }
      })
    },
    doMod() {
      if (this.form.pwd_new1 === this.form.pwd_new2) {
        this.form.password = this.form.pwd_new2
        this.$axios.post(this.$httpUrl + '/user/update', this.form).then(res => res.data).then(res => {
          console.log(res)
          if (res.code == 200) {
            this.$message({
              message: '修改成功',
              type: 'success'
            });
            this.dialogVisible = false
            // 重新加载数据
            this.loadPost()
            this.resetForm()
          }
        })
      } else {
        this.$message({
          message: '输入的两次密码不一致',
          type: 'error'
        });
      }
    },
    // 保存并刷新
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            this.doMod();
          } else {
            this.doSave();
          }
        } else {
          console.log('提交信息有误');
          return false;
        }
      });

    },
    // 重置
    resetParam() {
      this.name = ''
      this.sex = ''
    },
    loadGet() {
      /*调取地址，然后回调*/
      this.$axios.get(this.$httpUrl + '/user/list').then(res => res.data).then(res => {
        console.log(res)
      })
    },

    loadPost() {
      /*调取地址，然后回调*/
      this.$axios.post(this.$httpUrl + '/user/listPageC1', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          sex: this.sex,
          roleId: '1'
        }
      }).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.tableData = res.data
          this.total = res.total
        } else {
          alert('获取数据失败')
        }
      })
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1
      this.pageSize = val
      this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      this.loadPost()
    }
  }
  ,
  beforeMount() {
    this.loadGet();
    this.loadPost()
  }
}
</script>

<style scoped></style>