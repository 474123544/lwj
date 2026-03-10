<template>
	<div>
		<div class="forget_view">
			<el-form :model="forgetForm" class="forget_box">
				<div class="forget_title">基于JAVA的医院管理系统的设计与实现{{pageType==1?'找回密码':pageType==2?'输入密保':'重置密码'}}</div>
				<div class="tab_view">
					<div class="tab" :class="pageType==1?'tab_active':''">找回密码</div>
					<div class="tab" :class="pageType==2?'tab_active tab_active1':''"> 输入密保</div>
					<div class="tab" :class="pageType==3?'tab_active tab_active1 tab_active2':''">重置密码</div>
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
    import { useRoute } from 'vue-router'
    const route = useRoute()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
    onMounted(()=>{
    })
	const pageType = ref(1)
	const forgetForm = ref({})
	const userForm = ref({})
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
</script>

<style lang="scss" scoped>
	.forget_view {
        background-image: url("http://clfile.zggen.cn/20250225/0bea2f3d8ee049589ca7adf4985ab5fd.jpg")!important;
		// 表单盒子
		.forget_box {
			// 标题
			.forget_title {
			}
			// tab
			// 盒子
			.tab_view {
				padding: 0 0 20px;
				display: flex;
				width: 100%;
				align-items: center;
				// 默认样式
				.tab {
					padding: 16px 0;
					width: calc(100% / 3);
					font-size: 16px;
					box-sizing: border-box;
					text-align: center;
				}
				// 选中样式一
				.tab_active {
					clip-path: polygon(00% 0%, 92% 00%, 100% 50%, 92% 100%, 0 100%, 8% 50%);
					color: #fff;
					background: var(--theme50);
				}
				// 选中样式二
				.tab_active1 {
					color: #fff;
					background: var(--theme60);
				}
				// 选中样式三
				.tab_active2 {
					color: #fff;
					background: var(--theme90);
				}
			}
			// item
			.list_item {
				// label
				.item_label {
				}
				// 输入框
				:deep(.list_inp) {
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
			}
			// 按钮盒子
			.list_btn {
				// 获取密保
				:deep(.el-button--success) {
				}
				// 获取密保悬浮
				:deep(.el-button--success:hover) {
				}
				// 确认密保
				:deep(.el-button--primary) {
				}
				// 确认密保悬浮
				:deep(.el-button--primary:hover) {
				}
				// 重置密码
				:deep(.el-button--warning) {
				}
				// 重置密码悬浮
				:deep(.el-button--warning:hover) {
				}
				.r-login {
				}
			}
		}
	}

	
</style>
<style>
.forget_view {
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


.forget_view .forget_box{ width:750px; height:100%; margin:0 45% 0 0;  padding:50px 50px 50px 50px; background:#fff; font-size:16px; border-radius:20px; transform:scale(1); -webkit-transform:scale(1); -ms-transform:scale(1); z-index:5;  box-shadow: 0px 10px 13px -7px #000000, 5px 5px 15px 5px rgba(0,0,0,0);border: 1px solid #ddd;  border-radius:10px;  position: relative;
}
.forget_view .forget_box::after,.forget_view .forget_box::before {
    
    z-index:-1;
    position:absolute;
    top:0;
    right:0;
    bottom:0;
    left:0;
    
    margin:-3%;
    box-shadow:inset 0px 0px 20px #fff;
    border-radius:20px;
}


.forget_view .forget_title{ width:calc(100% + 0px); float:left; line-height:46px; font-size:22px; font-weight:600; letter-spacing:0.5px; background:none;  color:#333; position:relative; margin-bottom:20px; margin-left:0px; padding-left:0px; text-align:center;  }

.forget_view .forget_title:before { content: "";  width: 0px; height: 100%;  position: absolute;  top: 0; left: -50px; background: #8b5c7e; }


.forget_view .list_item{  width: 100%; padding:0 10px; display: flex; align-items: center;   justify-content: flex-start;  margin: 0 0 20px;  border:1px solid #ddd;  }
.forget_view .list_item .item_label { margin-right:10px; white-space: nowrap;  font-size: 16px; }
.forget_view .list_item .item_label i {  font-size:24px; color:#999;  }
.forget_view .list_item .list_inp{ width:100%; border:none; border-bottom:0px solid #ddd; height:40px; line-height:40px; font-size: 16px;  }
.forget_view .list_item .el-select{  width:100%; border-bottom:0px solid #ddd; height:40px; line-height:40px; font-size: 16px;  color:#999;  }
.forget_view .list_item .el-select .el-input__inner{ font-size: 16px;  color:#999;  }

.forget_view .list_type{ margin-bottom:20px;  }


.forget_view .list_btn{ margin-top:20px;  text-align:left;  }

.forget_view .list_btn .get_btn{ width: auto; height: 42px; line-height: 42px; background: var(--theme); border: 0px solid #ccc; font-weight: 500; font-size: 18px; color: #fff; margin-bottom:20px; padding:0 20px; border-radius:0px; min-width:120px;  float:left;  } 
.forget_view .list_btn .get_btn:hover {  } 

.forget_view .list_btn .valid_btn{  width: auto; height: 42px; line-height: 42px; background: var(--theme); border: 0px solid #ccc; font-weight: 500; font-size: 18px; color: #fff; margin-bottom:20px; padding:0 20px; border-radius:0px; min-width:120px;  float:left;  } 
.forget_view .list_btn .valid_btn:hover {  } 

.forget_view .list_btn .update_btn{  width: auto; height: 42px; line-height: 42px; background: var(--theme); border: 0px solid #ccc; font-weight: 500; font-size: 18px; color: #fff; margin-bottom:20px;  padding:0 20px; border-radius:0px; min-width:120px;  float:left;  } 
.forget_view .list_btn .update_btn:hover {  } 

.forget_view .list_btn .r-login{  font-size: 16px; color: #999; height: 42px; line-height: 42px; float:right;  } 
.forget_view .list_btn .r-login:hover{ cursor:pointer; color: var(--theme);  } 
.forget_view .el-select,.forget_view .el-input {
  border: none;
}

</style>