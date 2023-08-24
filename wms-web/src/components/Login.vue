<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">用户登录</h1>
        <el-form :model="loginForm" label-width="100px" :rules="rules" ref="loginForm">
          <el-form-item label="账号" prop="no" style="margin-bottom: 30px;">
            <el-input style="width: 300px" type="text" v-model="loginForm.no" autocomplete="off" size="small"
                      maxlength="16" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password" style="margin-bottom: 30px;">
            <el-input style="width: 300px" type="password" v-model="loginForm.password" show-password autocomplete="off"
                      size="small" @keyup.enter.native="confirm" maxlength="16" placeholder="请输入密码" ></el-input>
          </el-form-item>
          <el-form-item label="验证码" style="margin-bottom: 30px;">
            <div style="display: flex">
              <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 55%;" placeholder="请输入验证码"
                        maxlength="15">
              </el-input>
              <ValidCode @input="createValidCode" />
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirm" :disabled="confirm_disabled"
                       style="margin-top: -20px; margin-left:100px">确
              定</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>


<script>
import ValidCode from "@/components/ValidCode.vue";
export default {
  name: "Login",
  components: { ValidCode },
  data() {
    return {
      form: {},
      // 点完确定后，确定按钮不可继续点击
      confirm_disabled: false,
      loginForm: {
        no: '',
        password: '',
      },
      rules: {
        no: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              if (value && value.length == 16) {
                callback(new Error('账号长度不超过15个字符'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }

        ],
        password: [
          { required: true, message: '请输密码', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              if (value && value.length == 16) {
                callback(new Error('密码长度不超过15个字符'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ],

      },
      validCode: ''
    }
  },
  methods: {
    createValidCode(data) {
      this.validCode = data
    },
    confirm() {
      this.confirm_disabled = true;
      this.$refs.loginForm.validate((valid) => {
        if (valid) { //valid成功为true，失败为false
          if (!this.form.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if (this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }

          this.$axios.post(this.$httpUrl + '/user/login', this.loginForm).then(res => res.data).then(res => {
            if (res.code == 200) {
              //存储
              sessionStorage.setItem("CurUser", JSON.stringify(res.data.user))
              this.$store.commit("setMenu", res.data.menu)
              //跳转到主页
              this.$router.replace('/Index');
            } else {
              this.confirm_disabled = false;
              alert('校验失败，用户名或密码错误！');
              return false;
            }
          });
        } else {
          this.confirm_disabled = false;
          console.log('校验失败');
          return false;
        }
      });

    },
  }
}
</script>

<style  scoped>
.loginBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to bottom right, #18e552, #2cb2c1);
}

.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -250px;
  margin-left: -400px;
  width: 700px;
  height: 500px;
  background: rgba(255, 255, 255, 0.6);
  border-radius: 5%;

}

.login-title {
  margin: 45px 0;
  margin-top: 35px;
  text-align: center;
}

.login-content {
  width: 500px;
  height: 550px;
  position: absolute;
  top: 50px;
  left: 100px;
}
</style>