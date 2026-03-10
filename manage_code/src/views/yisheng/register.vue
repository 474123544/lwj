<template>
	<div>
		<div class="register_view">
			<el-form :model="registerForm" class="register_form">
				<div class="title_view">{{projectName}}注册</div>
				<div class="list_item">
					<div class="list_label">医生账号：</div>
					<el-input class="list_inp"
						 v-model="registerForm.yishengzhanghao" 
						 placeholder="请输入医生账号"
						 type="text"
						/>
				</div>
				<div class="list_item">
					<div class="list_label">密码：</div>
					<el-input class="list_inp"
						 v-model="registerForm.mima" 
						 placeholder="请输入密码"
						 type="password"
						 />
				</div>
				<div class="list_item">
					<div class="list_label">确认密码：</div>
					<el-input class="list_inp" v-model="registerForm.mima2" type="password" placeholder="请输入确认密码" />
				</div>
				<div class="list_item">
					<div class="list_label">医生姓名：</div>
					<el-input class="list_inp"
						 v-model="registerForm.yishengxingming" 
						 placeholder="请输入医生姓名"
						 type="text"
						/>
				</div>
				<div class="list_item">
					<div class="list_label">头像：</div>
					<div :style='{}' class="list_file_list">
						<uploads
							action="file/upload" 
							tip="请上传头像"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''" 
							@change="touxiangUploadSuccess">
						</uploads>
					</div>
				</div>
				<div class="list_item">
					<div class="list_label">性别：</div>
					<el-select 
						class="list_sel"
						v-model="registerForm.xingbie" 
						placeholder="请选择性别"
						>
						<el-option v-for="item in yishengxingbieLists" :label="item" :value="item"></el-option>
					</el-select>
				</div>

				<div class="list_item">
					<div class="list_label">电话：</div>
					<el-input class="list_inp"
						 v-model="registerForm.dianhua" 
						 placeholder="请输入电话"
						 type="text"
						/>
				</div>
				<div class="list_btn">
					<el-button class="register" type="success" @click="handleRegister">注册</el-button>
					<div class="r-login" @click="close">已有账号，直接登录</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context?.$project.projectName
	//获取注册类型
	import { useRoute } from 'vue-router';
	const route = useRoute()
	const tableName = ref('yisheng')
	
	//公共方法
	const getUUID=()=> {
		return new Date().getTime();
	}
	const registerForm = ref({
        xingbie: '',
	})
	const yishengxingbieLists = ref([])
	const init=()=>{
		yishengxingbieLists.value = "男,女".split(',')
	}
    const touxiangUploadSuccess=(fileUrls)=> {
        registerForm.value.touxiang = fileUrls;
    }
	// 多级联动参数
	//注册按钮
	const handleRegister = () => {
		let url = tableName.value +"/register";
		if(registerForm.value.mima!=registerForm.value.mima2){
			context?.$toolUtil.message('两次密码输入不一致','error')
			return false
		}
		if(registerForm.value.touxiang!=null){
			registerForm.value.touxiang = registerForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(registerForm.value.dianhua&&(!context?.$toolUtil.isMobile(registerForm.value.dianhua))){
			context?.$toolUtil.message(`电话应输入手机格式`,'error')
			return false
		}
		context?.$http({
			url:url,
			method:'post',
			data:registerForm.value
		}).then(res=>{
			context?.$toolUtil.message('注册成功','success', obj=>{
				context?.$router.push({
					path: "/login"
				});
			})
		})
	}
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
	init()
	onMounted(()=>{

	})
</script>
<style lang="scss" scoped>
	
	.register_view {
        background-image: url("http://clfile.zggen.cn/20250225/0bea2f3d8ee049589ca7adf4985ab5fd.jpg")!important;
		// 表单盒子
		.register_form {
		}
		// 标题样式
		.title_view {
		}
		// item盒子
		.list_item {
			// label
			.list_label {
			}
			// 输入框
			:deep(.list_inp) {
			}
		}
		//下拉框样式
		:deep(.list_sel) {
			//去掉默认样式
			.select-trigger{
				height: 100%;
				.el-input{
					height: 100%;
				}
			}
		}
		//按钮盒子
		.list_btn {
			//注册按钮
			.register {
			}
			//注册按钮悬浮样式
			.register:hover {
			}
			//已有账号
			.r-login {
			}
		}
		//图片上传样式
		.list_file_list  {
			//提示语
			:deep(.el-upload__tip){
			}
			//外部盒子
			:deep(.el-upload--picture-card){
				//图标
				.el-icon{
				}
			}
			:deep(.el-upload-list__item) {
			}
		}
	}
</style>
<style>
.register_view {
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


.register_form{ width:750px; margin:0 45% 0 0; padding:30px 50px 40px 50px; background:#fff; font-size:16px; border-radius:0px; transform:scale(1); -webkit-transform:scale(1); -ms-transform:scale(1); z-index:5;  box-shadow: 0px 10px 13px -7px #000000, 5px 5px 15px 5px rgba(0,0,0,0);border: 1px solid #ddd;  border-radius:10px;
 }

.register_form:before { content: ""; width: 100%; height: 0px; border-radius: 10px;  position: absolute;  top: -10px; background: rgba(255, 255, 255, .6);  left: 0; transform: scale(.95); -webkit-transform: scale(.95);  -ms-transform: scale(.95);  z-index: -1; }


.register_form .title_view{ width:calc(100% + 0px); float:left; line-height:46px; font-size:22px; font-weight:600; letter-spacing:0.5px; background:none;  color:#333; position:relative; margin-bottom:20px; margin-left:0px; padding-left:0px; text-align:center;  }
.register_form .title_view:before { content: "";  width: 0px; height: 100%;  position: absolute;  top: 0; left: -50px; background: #8b5c7e; }


.register_form .list_item {  width: 100%; padding:0 10px; display: flex; align-items: center; justify-content: flex-start;  margin: 0 0 20px; border-bottom:1px solid #ddd; }
.register_form .list_item .list_label{ display:block; margin-right:10px; white-space:nowrap; font-size:16px;  }
.register_form .list_item .list_label i {  font-size:24px; color:#999;  }
.register_form .list_item .el-input{ border:none; }
.register_form .list_item .el-input .el-input__inner{ width:100%; border:none; border-bottom:0px solid #ddd; height:40px; line-height:40px; border-radius:0; font-size:16px; color:#999;   }
.register_form .list_item .el-select { width:100%; }
.register_form .list_type{ margin-bottom:20px;  }
.register_form .list_code{  width: 100%; padding:0 0 0 10px; display: flex; align-items: center;   justify-content: flex-start;  margin: 0 0 20px;  border-bottom:1px solid #ddd;  }
.register_form .list_code .el-input .el-input__inner{ width:100%; border:none; border:0; height:40px; line-height:40px; border-radius:0; font-size:16px; color:#999;   }


.register_form .list_item .list_file_list{ width: 100%;  margin:10px 20px 10px 0px; flex:1; }

.register_form .list_item .list_file_list .el-upload{ background-color: rgb(255, 255, 255);  width: 100px;  height: 60px; line-height: 68px;  text-align: center;  border: 1px solid rgb(221, 221, 221);  border-radius: 0px; cursor: pointer; }

.register_form .list_item .list_file_list .el-upload .el-icon{ font-size: 26px; color: rgb(187, 187, 187); }

.register_form .list_item .list_file_list .img-uploader .el-upload__tip{ font-size: 16px;  color: rgb(102, 102, 102);margin: 7px 0px 0px; display: flex; align-items: center;  justify-content: flex-start; }


.register_form .list_item .el-upload-dragger{ background: rgb(250, 250, 250); border: 0px solid rgb(221, 221, 221);  border-radius: 0px;  box-sizing: border-box;  width: 100%; height: auto; text-align: center; cursor: pointer; overflow: hidden; padding: 4px 20px 10px;  margin:10px 20px 10px 0px; }

.register_form .list_item .el-upload-dragger .el-icon--upload{ font-size: 48px; color: var(--theme); line-height: 40px;  margin: 0px; }

.register_form .list_item .upload-demo .el-upload__tip{ font-size: 16px; color: rgb(102, 102, 102); margin: 7px 0px 10px; }

.register_form .list_item .el-upload-dragger .el-upload__text{  font-size: 16px;   }
.register_form .list_item .el-upload-dragger .el-upload__text em{ color: var(--theme); }


.register_form .list_item .list_radio{ display: flex; width: 80%; align-items: center; flex-wrap: wrap; background: none; height: 40px; flex: 1; }

.register_form .list_item .list_radio .el-radio{ width: auto; margin: 0px 20px 0px 0px; display: flex; justify-content: center; align-items: center; }

.register_form .list_item .list_radio .el-radio .el-radio__label { font-size: 16px; color: rgb(153, 153, 153);   }

.register_form .list_item .list_radio .el-radio.is-checked .el-radio__label { color:var(--theme);   }

.register_form .list_item .list_radio .el-radio .el-radio__input .el-radio__inner{ border-color: rgb(153, 153, 153); background: rgb(255, 255, 255); }

.register_form .list_item .list_radio .el-radio .el-radio__input.is-checked .el-radio__inner{ border-color: var(--theme); background: var(--theme);  }


.register_form .list_item .list_checkbox { display: flex; width: 80%;  flex-wrap: wrap; align-items: center; background: none; height: 40px; flex:1; }

.register_form .list_item .list_checkbox .el-checkbox{ width: auto; margin: 0px 20px 0px 0px; display: flex;  justify-content: center;  align-items: center; }

.register_form .list_item .list_checkbox .el-checkbox .el-checkbox__label { color: rgb(153, 153, 153);  font-size: 16px; }

.register_form .list_item .list_checkbox .el-checkbox.is-checked .el-checkbox__label { color: var(--theme); }

.register_form .list_item .list_checkbox .el-checkbox .el-checkbox__input .el-checkbox__inner{ border-color: rgb(153, 153, 153);  background: rgb(255, 255, 255); }

.register_form .list_item .list_checkbox .el-checkbox.is-checked .el-checkbox__input .el-checkbox__inner{ border-color: var(--theme); background: var(--theme); }


.register_form .list_code{ display: flex; align-items: center; width: 100%; margin: 10px auto; }
.register_form .list_code .list_code_label{ font-size:16px; }

.register_form .list_code .list_code_item{ width: calc(100% - 130px); display: flex; align-items: center; flex: 1; }

.register_form .list_code .list_code_item .list_code_inp{  font-size:16px; color:#999; flex: 1; border:0;  }

.register_form .list_code .list_code_item .list_code_btn{ width: 120px; height: 40px;  line-height: 40px; padding: 0px;  border: 0px solid rgb(73, 189, 227); background:var(--theme); border-radius: 0px; color: rgb(255, 255, 255); font-size: 15px; }


.register_form .list_btn{  margin-top:20px;   }
.register_form .list_btn .register{ width: auto; height: 42px; line-height: 42px; background: var(--theme); border: 0px solid #ccc; font-weight: 500; font-size: 18px; color: #fff; margin-bottom:20px; padding:0 20px; border-radius:0px; min-width:120px;  float:left;  } 
.register_form .list_btn .register:hover { } 
.register_form .list_btn .r-login{  font-size: 16px; color: #999; height: 42px; line-height: 42px; float:right;  } 
.register_form .list_btn .r-login:hover{ cursor:pointer; color: #1043a9;  } 
.register_form .el-select,.el-input {
  border: none;
}

</style>