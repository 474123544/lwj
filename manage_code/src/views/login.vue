<template>
	<div>
		<div class="login_view">
			<el-form :model="loginForm" class="login_form">
				<div class="title_view">基于JAVA的医院管理系统的设计与实现登录</div>
				<div class="list_item" v-if="loginType==1">
					<div class="list_label">
						账号：
					</div>
					<input class="list_inp" v-model="loginForm.username" placeholder="请输入账号" name="username" />
				</div>
				<div class="list_item" v-if="loginType==1">
					<div class="list_label">
						密码：
					</div>
					<input class="list_inp" v-model="loginForm.password" type="password" placeholder="请输入密码" @keydown.enter.native="handleLogin"  />
				</div>
				<div class="list_type" v-if="userList.length>1">
				  <el-radio v-for="(item,index) in userList" :key="index" v-model="loginForm.role"
				    :label="item.roleName">{{item.roleName}}</el-radio>
				</div>
				<div class="btn_view">
					<el-button class="login" v-if="loginType==1" type="success" @click="handleLogin">登录</el-button>
					<el-button class="register" type="primary" @click="handleRegister('yisheng')">注册医生</el-button>
				</div>
			</el-form>
		</div>
		<Vcode :show="isShow" @success="success" @close="close" @fail='fail'></Vcode>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
	} from "vue";
	import { useStore } from 'vuex'
	const store = useStore()
	const userList = ref([])
	const menus = ref([])
	const loginForm = ref({
		role: '',
		username: '',
		password: ''
	})
	const tableName = ref('')
	const loginType = ref(1)
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//注册
    const handleRegister = (tableName) => {
    	context?.$router.push(`/${tableName}Register`)
    }
	const handleLogin = () => {
		if (!loginForm.value.username) {
			context?.$toolUtil.message('请输入用户名', 'error')
			
			return;
		}
		if (!loginForm.value.password) {
			context?.$toolUtil.message('请输入密码', 'error')
			return;
		}
		if (userList.value.length > 1) {
			if (!loginForm.value.role) {
				context?.$toolUtil.message('请选择角色', 'error')
				verifySlider.value.reset()
				return;
			}
			for (let i = 0; i < menus.value.length; i++) {
				if (menus.value[i].roleName == loginForm.value.role) {
					tableName.value = menus.value[i].pathName||menus.value[i].tableName;
				}
			}
		} else {
			tableName.value = userList.value[0].pathName||userList.value[0].tableName;
			loginForm.value.role = userList.value[0].roleName;
		}
		login()
	}
	const login = () => {
		context?.$http({
			url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
			method: 'post'
		}).then(res => {
			context?.$toolUtil.storageSet("Token", res.data.token);
			context?.$toolUtil.storageSet("role", loginForm.value.role);
			context?.$toolUtil.storageSet("sessionTable", tableName.value);
			context?.$toolUtil.storageSet("adminName", loginForm.value.username);
			store.dispatch('user/getSession')	//vuex中获取用户信息并保存
			context?.$router.push('/')
		}, err => {
		})
	}
	//获取菜单
	const getMenu=()=> {
      let params = {
        page: 1,
        limit: 1,
        sort: 'id',
      }

      context?.$http({
        url: "menu/list",
        method: "get",
        params: params
      }).then(res => {
          menus.value = JSON.parse(res.data.data.list[0].menujson)
          for (let i = 0; i < menus.value.length; i++) {
            if (menus.value[i].hasBackLogin=='是') {
              userList.value.push(menus.value[i])
            }
          }
			loginForm.value.role = userList.value[0].roleName
          context?.$toolUtil.storageSet("menus", JSON.stringify(menus.value));
      })
    }
	//初始化
	const init = () => {
		getMenu();
	}
	onMounted(()=>{
		init()
		

	})
</script>

<style lang="scss" scoped>
	.login_view {
        background-image: url("http://clfile.zggen.cn/20250225/0bea2f3d8ee049589ca7adf4985ab5fd.jpg")!important;
		// 表单盒子
		.login_form {
		}
		.title_view {
		}
		// item盒子
		.list_item {
			// label
			.list_label {
			}
			// 输入框
			.list_inp {
			}
		}
		// 用户类型样式
		.list_type {
			// 未选中样式
			:deep(.el-radio) {
				// 单选框
				.el-radio__inner {
				}
				// 提示文字
				.el-radio__label{
				}
			}
			// 选中样式
			:deep(.is-checked) {
				// 单选框
				.el-radio__inner {
				}
				// 提示文字
				.el-radio__label{
				}
			}
		}
		// 按钮盒子
		.btn_view {
			// 登录
			.login {
			}
			// 注册
			.register {
			}
		}
	}

</style>
<style>
.login_view {
    min-height: 100vh;
    position: relative;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-end;
    background-image: url(http://clfile.zggen.cn/20250225/0bea2f3d8ee049589ca7adf4985ab5fd.jpg);
    background-position: center center;
    background-size: 100% 100% !important;
    background-repeat: no-repeat;
    background-attachment: initial;
    background-origin: initial;
    background-clip: initial;
    background-color: initial;
}

/*表单盒子*/
.login_form{ width:750px; margin:0 45% 0 0; padding:50px 50px 50px 50px; background:#fff; font-size:16px; transform:scale(1); -webkit-transform:scale(1); -ms-transform:scale(1); z-index:5; display:flex; flex-wrap:wrap; box-shadow: 0px 10px 13px -7px #000000, 5px 5px 15px 5px rgba(0,0,0,0);border: 1px solid #ddd;  border-radius:10px;
}

.login_form:before{ content:""; width:100%; height:0px; border-radius:10px; position:absolute; top:-10px; background:rgba(255,255,255,.6); left:0; transform:scale(.95); -webkit-transform:scale(.95); -ms-transform:scale(.95); z-index:-1; }

/*标题*/
.login_form .title_view{ width:calc(100% + 0px); float:left; line-height:46px; font-size:22px; font-weight:600; letter-spacing:0.5px; background:none;  color:#333; position:relative; margin-bottom:20px; margin-left:0px; padding-left:0px; text-align:center; }
.login_form .title_view:before{ content:""; width:0px; height:100%; position:absolute; top:0; left:-50px; background:#8b5c7e; }

/*list_item*/
.login_form .list_item{ width: 100%; padding:0 10px; display: flex; align-items: center;   justify-content: flex-start;  margin: 0 0 20px;  border-bottom:1px solid #ddd; }
.login_form .list_item .list_label { margin-right:10px; font-size:16px; white-space:nowrap;  }
.login_form .list_item .list_label i {  font-size:24px; color:#999;  }
.login_form .list_item .list_inp{ width:100%;background:none; border:none; height:40px; line-height:40px; font-size:inherit;  }
.login_form .list_item .el-select{  }

/*验证码*/
.login_form .listCode_view{ width: 100%; padding:0 0 0 10px; margin: 0 0 10px; display: flex; align-items: center;   justify-content: flex-start;   border-bottom:1px solid #ddd;   }
.login_form .listCode_view .listCode_label{ margin-right:10px; font-size:16px; white-space:nowrap;  }
.login_form .listCode_view .listCode_inp{ width:100%;background:none; border:none;  height:40px; line-height:40px; font-size:inherit; flex:1 }
.login_form .listCode_view .listCode_btn{ width:110px; background:var(--theme30); height:40px; line-height:40px; text-align:center; }

/*用户类型*/
.login_form .list_type{ display: flex; align-items: center; width: 100%; padding: 10px 0px;margin-bottom:10px;  order:6; }
.login_form .list_type .el-radio{ display: flex; align-items: center; }
.login_form .list_type .el-radio .el-radio__label{ color: #666;  font-size: 16px; }
.login_form .list_type .el-radio.is-checked{ }
.login_form .list_type .el-radio.is-checked .el-radio__label{ color: var(--theme);  font-size: 16px; }
.login_form .list_type .el-radio__input{ }
.login_form .list_type .el-radio__input.is-checked{ }
.login_form .list_type .el-radio__input .el-radio__inner{ background: rgb(255, 255, 255); border: 1px solid rgb(220, 223, 230); }
.login_form .list_type .el-radio__input.is-checked  .el-radio__inner{ background-color: var(--theme); border-color: var(--theme); }

/*记住密码*/
.login_form .remember_view{  margin-bottom:20px;  }
.login_form .remember_view .el-checkbox{ width: 20%; margin: 0px;  display: flex;  justify-content: center; align-items: center; }
.login_form .remember_view .el-checkbox .el-checkbox__label{ background: rgb(255, 255, 255); }
.login_form .remember_view .el-checkbox .el-checkbox__inner{ color: rgb(153, 153, 153); }
.login_form .remember_view .el-checkbox.is-checked .el-checkbox__label{ color:#339933; font-size: 15px;  }
.login_form .remember_view .el-checkbox.is-checked .el-checkbox__inner{  background: #339933;  border-color: #339933; font-size: 16px; }

/* 按钮 */
.login_form .btn_view{  width: 100%;  display: flex; flex-wrap:wrap; order:11;  }
.login_form .btn_view .login{ width:auto; height: 42px; line-height: 42px; background: var(--theme); border: 0px solid #ccc; font-weight: 500; font-size: 16px; color: #fff; margin:0 10px 10px 0; padding:0 20px; border-radius:0px; min-width:116px; } 
.login_form .btn_view .login:hover {  } 
.login_form .btn_view .register{ height: 42px; line-height: 42px;  background: #fff; border: 0px solid #B8B8B8; font-size:16px; color: #666; margin:0 10px 10px 0; padding:0 10px;  } 
.login_form .btn_view .register:hover {   } 
.login_form .btn_view .forget{ height: 42px; line-height: 42px; padding:0 10px; margin: 0 0 10px; background: #fff; border:0px solid #ccc; font-size:16px; color: #666;  width: auto;  } 
.login_form .btn_view .forget:hover {   }

.login_form .face{ width: auto; height: 42px; line-height: 42px; background: none; border: 1px solid #ccc; font-weight: 500; font-size: 16px; color: #333; margin:20px 0 10px; padding:0 10px; border-radius:0px;  text-align:center;  order:13;  } 
.login_form .face:hover {  } 


</style>