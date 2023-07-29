<template>
  <div style="display: flex;line-height:60px">
    <div style="margin-top: 4px;cursor: pointer" >
      <i :class="icon" style="font-size: 20px"@click="collapse"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px">
      <span>欢迎来到后台管理系统</span>
    </div>
        <!--cursor:pointer是鼠标走到处变按钮  -->
    <el-dropdown style="cursor: pointer">
      <span>{{user.name}}</span>
      <i class="el-icon-s-custom" style="margin-left:5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>

export default {
  name: "Header",
  data(){
    return{
      user: JSON.parse(sessionStorage.getItem("CurUser"))
    }
  },
  props: {
    icon:String
  },
  methods: {
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
    this.$router.push("/Home")
  }
}
</script>

<style scoped>

</style>