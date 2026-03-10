<template>
	<div class="top_view">
		<div class="top_left_view">
			<div class="fold_view" @click="toggleClick" :class="{'is_collapse':collapse}">
				<el-icon class="icons">
					<Fold v-if="!collapse" />
					<Expand v-else />
				</el-icon>
			</div>
		</div>

		<div class="projectTitle">基于JAVA的医院管理系统的设计与实现</div>
		<div class="top_right_view">
			<el-dropdown class="avatar-container" trigger="hover">
				<div class="avatar-wrapper">
					<div class="nickname">欢迎 {{$toolUtil.storageGet('adminName')}}</div>
					<img class="user-avatar" :src="store.getters['user/avatar']">
					<el-icon class="el-icon-arrow-down">
						<arrow-down />
					</el-icon>
				</div>
				<template #dropdown>
					<el-dropdown-menu class="user-dropDown" slot="dropdown">
						<el-dropdown-item class="center" @click="centerClick" >
							个人中心
						</el-dropdown-item>
						<el-dropdown-item class="chatRecord" v-if="changeHasChat()">
							<span style="display:block;" @click="chatRecordClick">聊天记录</span>
						</el-dropdown-item>
						<el-dropdown-item class="password" @click="updatepasswordClick">
							修改密码
						</el-dropdown-item>
						<el-dropdown-item class="front">
							<span style="display:block;" @click="frontClick">系统前台</span>
						</el-dropdown-item>
						<el-dropdown-item class="loginOut">
							<span style="display:block;" @click="onLogout">退出登录</span>
						</el-dropdown-item>
					</el-dropdown-menu>
				</template>
			</el-dropdown>
		</div>
		<el-dialog v-model="recordVisible" :title="'聊天记录'" :append-to-body="true">
			<div class="z-box" :style='{"width":"70%","padding":"20px","margin":"0 auto"}'>
				<div class="section-content" v-for="item in recordList" :key="item.id" @click="chatClick(item)">
					<img :src="item.picture?$config.url + item.picture:require('@/assets/img/avatar.png')" style="width: 60px;border-radius: 50%;"
						 alt="">
					<div style="margin: 0 0 0 10px;display: flex;flex-direction: column;align-items: flex-start;">
						<div :style='{"fontSize":"14px","color":"#000","flex":"1","fontWeight":"bold"}'
							 class="item-style">{{item.name}}</div>
						<div :style='{"color":"#666","flex":"1","fontSize":"14px","lineHeight":"1.5","display":"flex","alignItems":"center"}'
							 class="item-style">
							<div v-if="item.notreadnum" style="padding: 0 5px;height: 16px;border-radius: 50%;text-align: center;line-height: 16px;font-size: 12px;background: #f00;color:#fff;width: auto;margin: 0 2px 0 0">{{item.notreadnum}}</div>
							{{item.content.split('/').length&&item.content.split('/')[0]=='upload'?'[图片]':item.content}}
						</div>
					</div>
				</div>
				<div class="noList" v-if="!recordList.length">
					暂无聊天记录
				</div>
			</div>
		</el-dialog>
		<el-dialog v-model="chatVisible" @close="clearChat" :title="nowname" :append-to-body="true">
			<div class="chat-content" id="chat-content">
				<div v-bind:key="item.id" v-for="(item,index) in chatList">
                    <div v-if="index>1&&moment(chatList[index-1].addtime).date()!=moment(item.addtime).date()||index==0"
                         style="font-size: 12px;text-align: center;margin: 4px 0;">
                        {{moment(item.addtime).format("MM-DD HH:mm")}}
                    </div>
					<div v-if="item.uid==user.id" class="right-content">
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
								  type="warning"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
						<el-image v-else fit="cover" :src="item.content?$config.url + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
						<img :src="avatar?$config.url + avatar:require('@/assets/img/avatar.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 0 0 10px;flex-shrink: 0;object-fit: cover;" />
					</div>
					<div v-else class="left-content">
						<img :src="nowfpic?$config.url + nowfpic:require('@/assets/img/avatar.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 10px 0 0;flex-shrink: 0;object-fit: cover;" />
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
								  type="success"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
						<el-image v-else fit="cover" :src="item.content?$config.url + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
					</div>
					<div class="clear-float"></div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer" style="display: flex;align-items: center;">
				<el-input @keydown.enter.native="addChat" v-model="chatForm.content" placeholder="请输入内容" style="width: calc(100% - 180px);float: left;border: 1px solid #ccc;border-radius: 4px;padding: 0 10px;margin-right: 4px;">
				</el-input>
				<el-button :disabled="chatForm.content?false:true" type="primary" @click="addChat">发送</el-button>
				<el-upload style="display: inline-block;margin: 0 0 0 6px;" class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess"
						   :show-file-list="false" accept="image/*,.mp4">
					<el-button type="success">上传文件</el-button>
				</el-upload>
			</div>
		</el-dialog>
	</div>
    <div class="breadcrumb-view">
        <el-breadcrumb separator="-">
            <el-breadcrumb-item v-for="(item,index) in breadcrumbList" :key="item.path">
                <i class="iconfont icon-zhuye2" v-if="!index"></i>
                <span @click="router.push(item.path)">{{item.name}}</span>
            </el-breadcrumb-item>
        </el-breadcrumb>
    </div>
</template>

<script setup>
	import axios from 'axios'
    import moment from "moment"
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		toRefs,
		defineEmits,
		getCurrentInstance,
		ref,
		onBeforeUnmount,
		computed,
	} from 'vue';

	import {
		useRouter,
		useRoute
	} from 'vue-router';
	const router = useRouter()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const emit = defineEmits(['collapseChange'])
	const role = context?.$toolUtil.storageGet('sessionTable')
	const roleName = context?.$toolUtil.storageGet('role')
	const route = useRoute()
	let breadcrumbList = computed(()=>{
		let arr = route.matched.map(item=>({
			name:item.name,
			path:item.path
		}))
		return arr[arr.length-1].path=='/'?[arr[0]]:arr
	})

	const props = defineProps({
		collapse: Boolean
	})
	const {collapse} = toRefs(props)

	//获取用户信息
	import { useStore } from 'vuex'
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const avatar = ref(store.state.user.avatar)
	if(!store.state.user.session.id){
		store.dispatch('user/getSession')
	}
	const toggleClick = () => {
		emit('collapseChange')
	}
	// 退出登录
	const onLogout = () => {
		let toolUtil = context?.$toolUtil
		store.dispatch('delAllCachedViews')
		store.dispatch('delAllVisitedViews')
        store.dispatch('user/loginOut')
		toolUtil.storageClear()
		router.replace({
			name: "login"
		});
	}
	// 跳转前台
	const frontClick = () => {
        window.open(`${context.$config.url}client/index.html#/index/home`,'_blank')
	}
	// 个人中心
	const centerClick = () => {
		router.push(`/${role}Center`)
	}
	// 修改密码
	const updatepasswordClick = () => {
		router.push(`/updatepassword`)
	}
	const recordList = ref([])
	const recordVisible = ref(false)
	const chatVisible = ref(false)
	const nowfid = ref(0)
	const nowfpic = ref('')
	const nowname = ref('')
	const chatList = ref([])
	const chatForm = ref({
		content: ''
	})
	const chatTimer = ref(null)
	const uploadUrl = context.$config.url + 'file/upload'
	const hasChatList = [
		'yonghu',
		'yisheng',
	]
	const chatRecordClick = () => {
		getRecordList()
	}
	const changeHasChat = () => {
		let table = context.$toolUtil.storageGet('sessionTable')
		if(hasChatList.includes(table)){
			return true
		}else{
			false
		}
	}
	const getRecordList = () => {
		context.$http.get('chatfriend/page2', {
			params: {
				uid: user.value.id,
				type: 2
			}
		}).then(res => {
			if (res.data && res.data.code == 0) {
				recordList.value = res.data.data.list
				recordVisible.value = true
			}
		})
	}
	const chatClick = (row) => {
		nowfid.value = row.fid
		nowfpic.value = row.picture
		nowname.value = row.name
        scrollFlag.value = true
		getChatList()
		chatVisible.value = true
	}
    const scrollFlag = ref(true)
	const getChatList = () => {
		context.$http.get('chatmessage/mlist', {
			params: {
				page: 1,
				limit: 1000,
				uid: user.value.id,
				fid: nowfid.value
			}
		}).then(res => {
			if (res.data && res.data.code == 0) {
				chatList.value = res.data.data.list
				let div = document.getElementsByClassName('chat-content')[0]
				setTimeout(() => {
                    if (div){
                        if(div.scrollTop+div.clientHeight==div.scrollHeight || scrollFlag.value){
                            div.scrollTop = div.scrollHeight
                            scrollFlag.value = false
                        }
                    }
				}, 0)
				chatTimer.value = setTimeout(() => {
					getChatList()
				}, 5000)
			}
		})
	}
	const clearChat = () => {
		clearTimeout(chatTimer.value)
		chatList.value = []
		getRecordList()
	}
	const uploadSuccess = (res) => {
		if (res.code == 0) {
			clearTimeout(chatTimer.value)
			context.$http.post('chatmessage/add', {
				uid: user.value.id,
				fid: nowfid.value,
				content: 'file/' + res.file,
				format: 2
			}).then(res2 => {
				chatForm.value = {
					content: ''
				}
                scrollFlag.value = true
				getChatList()
			})
		}
	}
	const addChat = () => {
        if(!chatForm.value.content.trim())return context.$message.error("消息内容不能为空")
		clearTimeout(chatTimer.value)
		context.$http.post('chatmessage/add', {
			uid: user.value.id,
			fid: nowfid.value,
			content: chatForm.value.content,
			format: 1
		}).then(res2 => {
			chatForm.value = {
				content: ''
			}
            scrollFlag.value = true
			getChatList()
		})
	}
</script>

<style lang="scss" scoped>
	// 总盒子
	.top_view {
		// 左边盒子
		.top_left_view {
			display: none;
			// 折叠按钮盒子
			.fold_view {
				// 图标
				.icons {
				}
			}
		}
		// 标题
		.projectTitle{
		}
		// 右部盒子
		.top_right_view{
			// 头像盒子
			.avatar-container {
				cursor: pointer;
				margin: 0 30px 0 0;
				display: flex;
				align-items: center;
				height: 50px;
				.avatar-wrapper {
					margin: 5px 0 0;
					display: flex;
					position: relative;
					align-items: center;
					// 昵称
					.nickname {
						cursor: pointer;
						margin: 0 5px;
						line-height: 44px;
					}
					// 头像
					.user-avatar {
						cursor: pointer;
						border-radius: 10px;
						width: 40px;
						height: 40px;
					}
					// 图标
					.el-icon-arrow-down {
					}
				}
			}
		}
	}
	// 下拉盒子
	.el-dropdown-menu{
		background: #fff;
		// 下拉盒子itme
		:deep(.el-dropdown-menu__item){
			color: inherit;
			background: #fff;
		}
		// item悬浮
		:deep(.el-dropdown-menu__item:hover){
			color: #fff;
			background: var(--theme);
		}
	}
	.section-content {
		cursor: pointer;
		padding: 20px;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
		margin: 0 0 20px;
		color: #333;
		background: #fff;
		display: flex;
		width: 100%;
		border-color: #efefef;
		border-width: 0;
		align-items: center;
		border-style: solid;
		position: relative;
	}

	.section-content:hover {
		color: #fff;
		background: #DF847F10;
	}
	.chat-content {
		padding-bottom: 20px;
		width: 100%;
		margin-bottom: 10px;
		max-height: 300px;
		height: 300px;
		overflow-y: scroll;
		border: 1px solid #eeeeee;
		background: #fff;

		.left-content {
			float: left;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}

		.right-content {
			float: right;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}
	}

	.clear-float {
		clear: both;
	}
	.noList {
		color: #9e9e9e;
		width: 100%;
		text-align: center;
		padding: 60px 0;
	}
</style>
<style>
/*总盒子*/
.top_view{
    height: 80px;
    width: calc(100% - 17px);
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    left: 0px;
    top: 0px;
    z-index: 9;
    padding: 0px;
    border-width: 0 0 3px;
    border-style: solid;
    border-color: #c797cf;
    background:#9ed6fc;
    color: #333;
}
/*标题*/
.top_view .projectTitle{
    font-size: 24px;
    margin: 0px;
    padding: 0px 0px 0px 20px;
    line-height: 1;
    font-weight: 600;
    color: inherit;
    order: 1;
    margin-right: auto;
}

/*左边盒子*/
.top_view .top_left_view{
    width: auto;
    display: none;
    height: 100%;
    align-items: center;
}
/*折叠按钮*/
.top_view .top_left_view .fold_view{
    display: flex;
    padding: 0px;
    margin: 0px 0px 0px 222px;
    background: rgb(139, 92, 126);
    border-radius: 50%;
    width: 32px;
    height: 32px;
    align-items: center;
    justify-content: center;
 }
.top_view .top_left_view .fold_view:hover {
    background:#fa9fa4;
 }
/*图标*/
.top_view .top_left_view .fold_view .icons{
    font-size: 24px;
    color: inherit;
}

/*系统公告*/
.top_view .notice-btn{
    padding: 0 16px;
    margin-right:20px;
    line-height: 50px;
    font-size: 14px;
    color: inherit;
    order: 3;
    height: 100%;
    border: none;
    background: none;
    color: inherit;
}
.top_view .notice-btn:hover{
    color: inherit;
}
.top_view .notice-btn .iconfont{
    font-size: 16px;
}

/*右边盒子 横排*/
.top_view .top_right_view{
    width: auto;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    order: 4;
    margin-right: 30px;
    padding-left: 20px;
 }
.top_view .top_right_view .actionBar{ }
.top_view .top_right_view .actionBar .item { float:left; margin-right:20px; margin-bottom:5px; font-size:16px; color:#8b5c7e; font-weight:500;  }
.top_view .top_right_view .actionBar .item:hover {   color: inherit; cursor:pointer;  }
.top_view .top_right_view .actionBar .item i { font-size:18px; }

.top_view .user-info{
    position:absolute;
    left:20px;
    display: flex;
    align-items: center;
}
.top_view .user-info .img-wrapper{
    margin-right:10px;
}
.top_view .user-info .img-wrapper .user-avatar{
    width:50px;
}
.top_view .user-info .nickname{
    font-size:16px;
    color:#333 !important;
}

.top_view .weather {
    order: 2;
    display: flex;
    white-space: nowrap;
    line-height: 80px;
    column-gap: 4px;
    padding-left: 20px;
    color: inherit;
}

.top_view img.user-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
}

.top_view .avatar-wrapper {
    display: flex;
    align-items: center;
}

.top_view .nickname {
    white-space: nowrap;
    margin-right: 6px;
    color:#333 !important;
}

.top_view .nowDate {
    order: 2;
    white-space: nowrap;
    margin: 0 20px;
    padding-left: 20px;
    line-height: 80px;
    color: inherit;
}

.breadcrumb-view {
    display:block;
    background:linear-gradient(180deg, rgba(233,247,255,1) 0%, rgba(139,199,239,1) 50%, rgba(109,188,241,1) 50%, rgba(142,201,240,1) 100%);
    width: calc(100% - 70px) !important;
    height: 38px;
    display: flex;
    align-items: center;
    padding: 0 20px;
    margin-left:40px;
}
.el-breadcrumb {
    display: flex;
    align-items: center;
    height: 38px;
}
/*分割线*/
.breadcrumb-view .el-breadcrumb__separator{
    color: #07487f;
}
.breadcrumb-view .el-breadcrumb__inner{
    color: #07487f !important;
}
.breadcrumb-view .el-breadcrumb__inner:hover{
    color: #07487f;
}

</style>