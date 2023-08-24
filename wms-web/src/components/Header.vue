<template slot-scope="scope">
  <div style="display: flex;line-height:60px">
    <div style="margin-top: 4px;cursor: pointer" >
      <i :class="icon" style="font-size: 20px" @click="collapse"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px">
      <span>欢迎来到后台管理系统</span>
    </div>
        <!--cursor:pointer是鼠标走到处变按钮  -->
    <el-dropdown style="cursor: pointer">
      <span>{{user.name}}</span>
      <i class="el-icon-s-custom" style="margin-left:5px"></i>
      <el-dropdown-menu slot="dropdown" >
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="toChangePwd">修改密码</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%" >
      <el-form ref="form" :rules="rules" :model="form" label-width="100px">
<!--        <el-form-item label="原密码" prop="password">-->
<!--          <el-col :span="20">-->
<!--            <el-input v-model="form.password" maxlength="10"></el-input>-->
<!--          </el-col>-->
<!--        </el-form-item>-->
      </el-form>
      <el-form ref="form" :rules="rules" :model="form" label-width="100px">
        <el-form-item label="新密码" prop="pwd_new1">
          <el-col :span="20">
            <el-input v-model="form.pwd_new1" maxlength="10"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <el-form ref="form" :rules="rules" :model="form" label-width="100px">
        <el-form-item label="确认新密码" prop="pwd_new2" >
          <el-col :span="20">
            <el-input v-model="form.pwd_new2" maxlength="10"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "Header",
  data(){
    return{
      user:{},
      tableData:[],
      dialogVisible: false,
      form:{
        id:'',
        password:'',
        pwd_new1: '',
        pwd_new2: ''
      },
      rules:{
        password: [
          {required: true, message: '请输入原密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        pwd_new1: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ]
        ,pwd_new2: [
          {required: true, message: '请确认新密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ]
      },
    }
  },
  props: {
    icon:String
  },
  methods: {
    init(){
      this.user = JSON.parse(sessionStorage.getItem('CurUser'))
    },
    // 验证信息
    resetForm() {
      this.$refs.form.resetFields();
    },
    toChangePwd(){
      // 展示窗口
      this.dialogVisible = true
      this.$nextTick(()=>{
        // 附值到表单
        this.form.id = this.user.id;
        this.form.password = '';
      })
    },
    doSave(){
      this.$axios.post(this.$httpUrl+'/user/save',this.form).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code ==200){
          this.$message({
            message: '添加成功',
            type: 'success'
          });
          this.dialogVisible = false
          // 重新加载数据
          this.loadPost()
          this.resetForm()
        }else {
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }
      })
    },
    doMod(){
      if (this.form.pwd_new1 === this.form.pwd_new2){
        this.form.password = this.form.pwd_new2
        this.$axios.post(this.$httpUrl+'/user/update',this.form).then(res=>res.data).then(res=>{
        console.log(res)
            if (res.code ==200){
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
      }else {
        this.$message({
          message: '输入的两次密码不一致',
          type: 'error'
        });
      }
    },
    loadGet(){
      /*调取地址，然后回调*/
      this.$axios.get(this.$httpUrl+'/user/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    },
    loadPost(){
      /*调取地址，然后回调*/
      this.$axios.post(this.$httpUrl+'/user/listPageC1',{
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          sex: this.sex,
          roleId:'2'
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code ==200){
          this.tableData = res.data
        }else {
          alert('获取数据失败')
        }
      })
    },
    save(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id){
            this.doMod();
          }else {
            this.doSave();
          }
        } else {
          console.log('提交信息有误');
          return false;
        }
      });

    },
    toUser(){
      console.log('to_user')
      this.$router.push("/Home")
    },
    logout(){
      console.log('logout')
      this.$confirm("请您确认是否要退出登录","提示",{
        confirmButtonText:"确定",   // 确认按钮显示
        type: "warning",
        center: true    // 文字居中显示
      })
          .then(()=>{
            this.$message({
              type:"success",
              message: "成功退出登录"
            })
            this.$router.push("/")
            sessionStorage.clear()
          })
          .catch(()=>{
            this.$message({
              type:"info",
              message:"已取消退出登录"
            })
          })

    },
    collapse(){
      this.$emit('doCollapse')
    }
  },
  created() {
    this.init()
    this.$router.push("/Calendar")
  },
  beforeMount() {
    this.loadGet();
    this.loadPost()
  }
}
</script>

<style scoped>

</style>