<template>
	<div class="message_view" :style='{}'>
		<div class="back_view" v-if="centerType">
			<el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
		</div>
		<div class="section_title">
            <span>{{formName}}</span>
		</div>
		<div class="messages_list">
			<div class="add_view">
				<el-button class="add_btn" @click="addClick" v-if="btnAuth('messages','新增')">发表</el-button>
			</div>
			<div class="messages" v-for="(item,index) in list" :key="index" @mouseenter="messageEnter(index)"
				@mouseleave="messageLeave">
				<div class="messages_user">
					<img v-if="item.avatarurl" class="messages_user_avatar"
						:src="$config.url + item.avatarurl.split(',')[0]" alt="">
					<img v-else class="messages_user_avatar" src="@/assets/avatar.png" alt="">
					<span class="messages_user_name">用户：{{item.username}}</span>
				</div>
				<div class="messages_content" v-html="item.content"></div>
				<div class="messages_reply_view" v-if="item.reply">
					<el-divider content-position="left">回复</el-divider>
					<div class="meaages_reply" v-html="item.reply"></div>
				</div>
				<div class="messages_del_view">
					<div class="messages_del" @click="delClick(item.id)"
						v-if="messageShowIndex==index&&item.userid==userid">删除</div>
				</div>
				<el-divider v-if="index!=list.length - 1" />
			</div>
		</div>
		<el-pagination
			background 
			:layout="layouts.join(',')"
			:total="total" 
			:page-size="listQuery.limit"
            v-model:current-page="listQuery.page"
			prev-text="上一页"
			next-text="下一页"
			:hide-on-single-page="false"
			:style='{}'
			@size-change="sizeChange"
			@current-change="currentChange"/>
		<el-dialog v-model="formVisible" :title="'发表'" width="50%" destroy-on-close>
			<el-form class="add_form" :model="form" label-width="80px">
				<el-row>
					<el-col :span="24">
						<el-form-item prop="content" label="内容">
							<editor :value="form.content"
								placeholder="请输入内容" class="editor" @change="contentChange"></editor>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer>
				<span class="formModel_btn_box">
					<el-button class="formModel_cancel" @click="formVisible=false">关闭</el-button>
					<el-button class="formModel_confirm" type="primary" @click="save">发表</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script setup>
	import {
		ref,
		nextTick,
		getCurrentInstance
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//基础信息
	const tableName = 'messages'
	const formName = '反馈建议'
	const router = useRouter()
	const route = useRoute()
	//基础信息
	//权限验证
	const btnAuth = (e, a) => {
		return context?.$toolUtil.isAuth(e, a)
	}
	const list = ref([])
	const listLoading = ref(false)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'addtime',
		order: 'desc'
	})
	const searchQuery = ref({})
	const total = ref(0)
	const userid = ref(0)
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		context?.$http({
			url: `messages/list`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = res.data.data.total
		})
	}
	//分页
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	const form = ref({
		content: '',
		userid: '',
		username: '',
		avatarurl: ''
	})
	const formVisible = ref(false)
	const resetForm = () => {
		form.value = {
			content: '',
			userid: context?.$toolUtil.storageGet('userid'),
			username: context?.$toolUtil.storageGet('frontName'),
			avatarurl: context?.$toolUtil.storageGet('headportrait') ? context?.$toolUtil.storageGet('headportrait') : ''
		}
	}
	//发表
	const addClick = () => {
		resetForm()
		formVisible.value = true
	}
	//富文本回调
	const contentChange = (e) => {
		form.value.content = e
	}
	const save = () => {
		if (form.value.content == '') {
			context?.$toolUtil.message('请输入内容', 'error')
			return false
		}
		let sensitiveWords = "";
		let sensitiveWordsArr = [];
		if(sensitiveWords) {
			sensitiveWordsArr = sensitiveWords.split(",");
		}
		for(var i=0; i<sensitiveWordsArr.length; i++){
			//全局替换
			var reg = new RegExp(sensitiveWordsArr[i],"g");
			//判断内容中是否包括敏感词
			if (form.value.content.indexOf(sensitiveWordsArr[i]) > -1) {
				// 将敏感词替换为 **
				form.value.content = form.value.content.replace(reg,"**");
			}
		}
		context?.$http({
			url: 'messages/add',
			method: 'post',
			data: form.value
		}).then(res => {
			context?.$toolUtil.message(`发表成功`, 'success', () => {
				getList()
				formVisible.value = false
			})
		})
	}
	//删除
	const delClick = (id = null) => {
		if (id) {
			ElMessageBox.confirm(`是否删除选中留言`, '提示', {
				confirmButtonText: '是',
				cancelButtonText: '否',
				type: 'warning',
			}).then(() => {
				context?.$http({
					url: `messages/delete`,
					method: 'post',
					data: [id]
				}).then(res => {
					context?.$toolUtil.message('删除成功', 'success', () => {
						getList()
					})
				})
			}).catch(_ => {})
		}
	}
	//控制删除显示
	const messageShowIndex = ref(-1)
	const messageEnter = (e) => {
		messageShowIndex.value = e
	}
	const meeageLeave = () => {
		messageShowIndex.value = -1
	}
	const init = () => {
		if (route.query.centerType) {
			centerType.value = true
		}
		userid.value = context?.$toolUtil.storageGet('userid')
		getList()
	}
	init()
</script>

<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 4px;
		padding: 10px 0px;
		margin: 10px auto;
		background: none;
		width: 100%;
		text-align: right;
		// 返回按钮
		.back_btn {
			border: 1px solid var(--theme);
			cursor: pointer;
			border-radius: 0px;
			padding: 0 24px;
			color: #fff;
			background: var(--theme);
			width: auto;
			font-size: 14px;
			height: 34px;
		}
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
	// 列表盒子
	.messages_list {
		// 新增盒子
		.add_view {
			// 新增按钮
			.add_btn {
			}
			// 新增按钮-悬浮
			.add_btn:hover {
			}
		}
		// item
		.messages {
			// 用户盒子
			.messages_user {
				// 用户头像
				.messages_user_avatar {
				}
				// 用户名
				.messages_user_name {
				}
			}
			// 内容
			.messages_content {
			}
			// 回复盒子
			.messages_reply_view {
				// 分割线样式
				:deep(.el-divider) {
					// 分割线文字样式
					.el-divider__text {
					}
				}
				// 回复内容
				.meaages_reply {
				}
			}
			// 操作按钮盒子
			.messages_del_view {
				// 删除按钮
				.messages_del {
				}
				// 删除按钮-悬浮
				.messages_del:hover {
				}
			}
		}
	}
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
		}
		// 上一页
		:deep(.btn-prev) {
		}
		// 下一页
		:deep(.btn-next) {
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
		}
		// 页码
		:deep(.el-pager) {
			// 数字
			.number {
			}
			// 数字悬浮
			.number:hover {
			}
			// 选中
			.number.is-active {
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			.el-select {
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;

					}
				}
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			// 输入框
			.el-input {
			}
		}
	}
	// 表单
	.add_form {
		// form item
		:deep(.el-form-item) {
			//label
			.el-form-item__label {
			}
			// 内容盒子
			.el-form-item__content {
				// 富文本
				.list_editor {
				}
			}
		}
	}
	// 按钮盒子
	.formModel_btn_box {
		.formModel_cancel {
		}
		.formModel_cancel:hover {
		}
		
		.formModel_confirm {
		}
		.formModel_confirm:hover {
		}
	}
</style>
<style>
/*总盒子*/
.message_view {
    width: 1200px;
    margin: 20px auto;
    background: #fff;
    padding:60px 20px 10px;
    border:1px solid #eee;
    position:relative;
}
/* 标题 */
.message_view .section_title{
	position:absolute;
	top:-6px;
	left:0px);
}
.message_view .section_title:after {
	position:absolute;
	content:"";
	display:block;
	width:0;
	height:0;
	border-left:48px solid transparent;
	border-right:100px solid transparent;
	border-top:20px solid var(--theme);
}
.message_view .section_title span {
	position:relative;
	display:inline-block;
	text-align:center;
	background:var(--theme);
	font-size:18px;
	color:#fff;
	line-height:1;
	padding:15px 8px 10px;
	border-top-right-radius:8px;
	min-width:150px;
}
.message_view .section_title span:before,.message_view .section_title span:after{
	position:absolute;
	content:"";
	display:block;
}
.message_view .section_title span:before {
	background:var(--theme);
	height:6px;
	width:6px;
	left:-6px;
	top:0;
}
.message_view .section_title span:after {
	background:var(--theme2);
	height:6px;
	width:8px;
	border-radius:4px 4px 0 0;
	left:-8px;
	top:0;
}

/*列表盒子*/
.message_view .messages_list{
    width: calc(100% - 0px);
    margin: 20px auto;
    border-radius: 0px;
    padding: 0px;
    box-sizing: border-box;
}
/*新增盒子*/
.message_view .messages_list .add_view{
    width: 100%;
    text-align: right;
    margin: 0px 0px 30px;
}
/*新增按钮*/
.message_view .messages_list .add_view .add_btn{
    margin: 0px 10px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 34px;
    font-size: 16px;
    color: rgb(255, 255, 255);
    border-radius: 2px;
    border: 0px;
    background: var(--theme);
    cursor: pointer;
}
/*item*/
.message_view .messages_list .messages{
    width: 100%;
    border-bottom:1px dashed #ddd;
    margin: 0px 0px 20px;
}
/*用户盒子*/
.message_view .messages_list .messages .messages_user{
    display: flex;
    align-items: center;
    background: none;
    padding: 5px 10px;
}
/*头像*/
.message_view .messages_list .messages .messages_user .messages_user_avatar{
    width: 100px;
    height: 100px;
    border-radius:4px;
    margin: 0px 10px 0px 0px;
}
/*用户名*/
.message_view .messages_list .messages .messages_user .messages_user_name{
    font-size: 16px;
    color: #333;
}
/*内容*/
.message_view .messages_list .messages .messages_content{
    width: 100%;
    padding: 20px 0px 0px 60px;
    text-align: left;
}
/*回复盒子*/
.message_view .messages_list .messages .messages_reply_view{
    width: 100%;
    margin: 0px;
    text-align: left;
    padding: 0px 60px;
}
/*线条样式*/
.message_view .messages_list .messages .messages_reply_view .el-divider--horizontal{
    border: none;
    background: rgb(220, 223, 230);
}
/*文字*/
.message_view .messages_list .messages .messages_reply_view .el-divider--horizontal .el-divider__text{
    font-size: 16px;
}
/*回复内容*/
.message_view .messages_list .messages .messages_reply_view .meaages_reply{
    color: rgb(153, 153, 153);
}

/*按钮盒子*/
.message_view .messages_list .messages .messages_del_view{
    width: 100%;
    height: auto;
    padding: 0px 10px 0px 0px;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    margin: 0px 0px 20px;
}
/*删除按钮*/
.message_view .messages_list .messages .messages_del_view .messages_del{
    font-size: 16px;
    color: rgb(255, 255, 255);
    cursor: pointer;
    background: rgba(245, 110, 95,1);
    padding: 5px 20px;
    border-radius: 4px;
}
/*删除按钮-悬浮*/
.message_view .messages_list .messages .messages_del_view .messages_del:hover{
    background: rgba(245, 110, 95,.8);
}

</style>