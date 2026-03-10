<template>
	<div class="home">
		<div class="index_top">
			<div class="index_top_title"><span>基于JAVA的医院管理系统的设计与实现</span></div>
			<div class="index_top_right">
				<el-button v-if="!Token" class="login" type="success" icon="User" circle @click="loginClick">
					登录
				</el-button>
				<div class="user" v-if="Token">
					<el-dropdown class="avatar-container" trigger="hover">
						<div class="avatar-wrapper">
							<img class="user-avatar" :src="store.getters['user/avatar']" style="width: 50px">
							<div class="nickname">{{store.getters['user/username']}}</div>
							<el-icon class="el-icon-arrow-down">
								<arrow-down />
							</el-icon>
						</div>
						<template #dropdown>
							<el-dropdown-menu class="user-dropDown" slot="dropdown">
								<el-dropdown-item @click="menuHandler('center')" class="center">
									<span>个人中心</span>
								</el-dropdown-item>
								<el-dropdown-item @click="loginOut" class="loginOut">
									<span>退出登录</span>
								</el-dropdown-item>
							</el-dropdown-menu>
						</template>
					</el-dropdown>
				</div>
			</div>
		</div>
		<el-scrollbar class="contain_view">
            <div class="menu-wrapper">
                <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
                    <el-menu :default-openeds="[]" :unique-opened="true" :default-active="menuIndex"
                         class="menu_view" mode="horizontal" :ellipsis="false" @select="menuChange" :key="menuIndex">
                        <el-menu-item class="first-item" index="/index/home" @click="menuHandler('/')">
                            <template #title>
                                <span>首页</span>
                            </template>
                        </el-menu-item>
                        <template v-for="(menu,index) in menuList" :key="menu.menu">
                            <el-sub-menu v-if="menu.child.length>1" :index="menu.name" class="first-item" :teleported="true">
                                <template #title>
                                    <span>{{ menu.name }}</span>
                                </template>
                                <el-menu-item class="second-item" v-for=" (child,index1) in menu.child" :key="index1"
                                              :index="child.url" @click="menuHandler(child.url)">{{ child.name }}
                                </el-menu-item>
                            </el-sub-menu>
                            <el-menu-item v-else :index="menu.child[0].url" class="first-item" @click="menuHandler(menu.child[0].url)">
                                <template #title>
                                    <span>{{menu.child[0].name}}</span>
                                </template>
                            </el-menu-item>
                        </template>
                    </el-menu>
                </el-scrollbar>
            </div>
			<div class="rotation_view" >
				<mySwiper :type="3" :data="rotationList" :autoHeight="false" :autoplay="true"
					:loop="false" :navigation="false" :pagination="true"
					:paginationType="1" :scrollbar="false" :slidesPerView="1"
					:spaceBetween="20" :centeredSlides="false"
					:freeMode="false" :effectType="1"
					:direction="horizontal">
					<template #default="scope">
						<img :style='{}' :src="scope.row.value?$config.url + scope.row.value:''" @click="carouselClick(scope.row.url)">
					</template>
				</mySwiper>
			</div>
			<router-view :key="routerKey"/>
			<el-backtop :right="100" :bottom="100" />
			<div class="bottom_view">
				<img class="bottom_img" src="http://chy2.gdnxeco.com/20230912/0d5ae5e141c14b06a52a7c4c2f8ea4cf.jpg" alt="">
				<div class="bottom_company"></div>
				<div class="bottom_record"></div>
				<div class="bottom_desc"></div>
			</div>
		</el-scrollbar>
	</div>
</template>
<script setup>
	import menu from '@/utils/menu'
	import axios from 'axios'
    import moment from "moment";
	import {
		ref,
		onBeforeUnmount,
		getCurrentInstance,
		nextTick,
		computed,
		watch,
	} from 'vue';
	import {
		useRouter,
		useRoute
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const router = useRouter()
	const route = useRoute();
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const Token = ref('')
	const interval = ref(null)
    const nowDate = ref(new Date())
	if(localStorage.getItem('frontToken') && !store.getters['user/session'].id){
		store.dispatch('user/getSession')
	}
	onBeforeUnmount(() => {
		clearInterval(interval.value)
	})
	// 获取默认菜单index
	const setMenuIndex = (path)=>{
        if(path){
            menuIndex.value = path.replace('Detail','List')
        }else{
            menuIndex.value = ''
        }
	}
	// 默认菜单index
	const menuIndex = ref('')
    const routerKey = ref(Math.random())
	watch(() => router.currentRoute.value,(value, oldValue) => {
		Token.value = context?.$toolUtil.storageGet('frontToken')
        if(value.path==oldValue?.path){
            //改变路由query时重新加载router-view
            routerKey.value = Math.random()
        }
        if(value.query.centerType){
            setMenuIndex()
        }else{
            setMenuIndex(value.path)
        }
	},{
		immediate:true
	})
	const init = () => {
		// 获取菜单
		getMenu()
		// 赋值token
		Token.value = context?.$toolUtil.storageGet('frontToken')
		// 时间
		interval.value = setInterval(() => {
            nowDate.value = new Date()
		}, 1000)
		// 轮播图
		getRotationList()
		if(Token.value){
			getSession()
		}
	}
	// 切换菜单保存index
	const menuChange = (e)=>{
		if(e=='chat')return
	}
	// 轮播图
	const rotationList = ref([])
	const baseUrl = context?.$config.url
	const getRotationList = () => {
		context?.$http({
			url: 'config/list',
			method: 'get',
			params: {
				page: 1,
				limit: 3
			}
		}).then(res => {
			rotationList.value = res.data.data.list
		})
	}

    // 轮播图跳转
    const carouselClick = (url)=>{
        if (url) {
            if (url.startsWith('http')) {
                window.open(url)
            } else {
                context.$router.push(url)
            }
        }
    }
	const menuList = ref([])
	const role = ref('')
	const getMenu = () => {
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
			context?.$toolUtil.storageSet("menus", res.data.data.list[0].menujson);
		})
		menuList.value = context?.$config.menuList
	}
	const loginClick = () => {
		context?.$toolUtil.storageSet('toPath',window.history.state.current)
		router.push('/login')
	}
	const loginOut = () => {
		context?.$toolUtil.message('退出成功', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
		Token.value = ''
	}
	//菜单跳转
	const menuHandler = (name) => {
		if (name == 'center') {
			name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
			menuChange('center')
		}
		router.push(name)
	}
	// 获取用户信息
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu'){
				context?.$toolUtil.storageSet("frontName", res.data.data.zhanghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yisheng'){
				context?.$toolUtil.storageSet("frontName", res.data.data.yishengzhanghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yisheng'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang)
			}
		})
	}
	init()
</script>
<style lang="scss" scoped>
	/* 头部 */
	.index_top {
		.index_top_title {
		}
		.index_top_right {
			line-height: 1;
			.weather_time_view {
				display: flex;
				align-items: center;
				.weather {
					padding: 0 10px;
					flex-direction: row;
					display: none;
					font-size: 16px;
					line-height: 1;
					justify-content: center;
					align-items: center;
					.city {
						padding: 0;
						margin: 0 10px 0 0;
					}
					.wea {
					}
				}
				.time {
					padding: 0 20px;
					flex-direction: row;
					display: flex;
					font-size: 16px;
					line-height: 1;
					justify-content: center;
					align-items: center;
					.date {
						padding: 0;
						margin: 0 10px 0 0;
					}
					.timer {
						padding: 0;
						margin: 0;
					}
				}
			}
		}
		// 登录按钮
		:deep(.el-button--success) {
			border-radius: 4px;
			padding: 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: none;
			font-size: 16px;
			border-color: transparent;
		}
		:deep(.el-button--success:hover) {
		}
		// 登出按钮
		:deep(.el-button--danger) {
			border-radius: 4px;
			padding: 10px;
			color: #fff;
			background: none;
			font-size: 16px;
			border-color: transparent;
		}
		:deep(.el-button--danger:hover) {
		}
	}
	// 轮播盒子
	.rotation_view {
	}
	// 底部
	.bottom_view {
		// 图片
		.bottom_img {
			border-radius: 100%;
			object-fit: cover;
			display: none;
			width: 44px;
			height: 44px;
		}
		// 公司
		.bottom_company {
			margin: 10px 0 0;
		}
		// 备案号
		.bottom_record {
			margin: 10px 0 0;
		}
		// 联系方式
		.bottom_desc {
			margin: 10px 0 0;
		}
	}
	
	
	

	.contain_view {
		margin: 0;
		background: url(http://clfile.zggen.cn/20250118/93a2e61371a947e89d2228083f5fd8c9.gif);
		position: relative;
		height: calc(100% - 60px);
	}
	.el-aside {
		transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-moz-transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-o-transition: width 0.15s;
	}
	.el-sub-menu__hide-arrow{
		display: block !important;
	}
	.menu_scrollbar {
	}
	// 总盒子
	.menu_view {
		// 一级菜单
		:deep(.first-item) {

			// 图标
		
			// 标题
			.el-sub-menu__title,
			span {
			}
		
			//箭头
			.el-sub-menu__icon-arrow {
			}
		}
		// 选中
		:deep(.is-active) {
		}
		
		// 悬浮
		:deep(.first-item:hover) {
		}
	}
</style>
<style lang="scss">
	// 二级
	.menu_popper {
		border:none !important;
		background: none !important;

		// 二级总盒子
		.el-menu--popup {
		}

		// 二级菜单
		.second-item {
		}
		
		// 选中
		.is-active {
		}
		
		// 悬浮
		.second-item:hover {
		}
	}
</style>
<style>

.index_top {
    box-sizing: border-box;
    width: 100%;
    display: flex;
    align-items: center;
    height: 44px;
    background: #eceff1;
    color: #333;
    justify-content: space-between;
    padding: 0px calc((100% - 1200px) / 2);
    z-index: 1002;
    position: relative;
    border-bottom: 1px solid #cfd8dc;
}

.index_top .index_top_title{
    font-size: 20px;
    color: #333;
    font-weight: bold;
    margin-left:20px;
}

.index_top .index_top_right{
    display: flex;
    align-items: center;
    margin-right:20px;
    color: inherit;
}

.index_top .notice-btn{
    background: none;
    border: 0px solid #dcdfe6;
    color: #333;
    margin: 0;
    padding: 10px;
    font-size: 16px;
    border-radius: 0px;
}
.index_top .notice-btn:hover{
    color: #ccc;
}

.index_top .notice-btn .iconfont{
    font-size: 16px;
}


.index_top .index_top_right .chat{
    margin:0 10px;
}
.index_top .index_top_right .chat:hover{
    color: #ccc;
}
.index_top .index_top_right .chat .iconfont{
    font-size: 16px;
}
.index_top .index_top_right .chat span{
    font-size: 16px;
}

.index_top .index_top_right .cart{
    margin-right:10px;
    font-size: 16px;
}
.index_top .index_top_right .cart:hover{
    color: #ccc;
}
.index_top .index_top_right .cart .iconfont{
    font-size: 16px;
    margin-right:5px;
}

.index_top .index_top_right .toBack{
    margin-right:15px;
}
.index_top .index_top_right .toBack .el-button{
    background:none;
    border:0;
    padding:0;
    font-size: 16px;
    color: #333;
}
.index_top .index_top_right .toBack .el-button:hover{
    color: #ccc;
    background:none;
}
.index_top .index_top_right .login span{
    color: #333;
}
.index_top .index_top_right .login .el-icon-user{
    color: #333;
}
.index_top .index_top_right .login .el-icon{
    color: #333 !important;
}



.index_top .user {
    margin:0 10px;
}
.index_top .user .avatar-wrapper{
    display: flex;
    align-items: center;
    cursor:pointer;
    color: inherit;
}

.index_top .user .avatar-wrapper .user-avatar{
    width: 36px !important;
    height: 36px;
    border-radius: 100%;
    margin-right:5px;
}

.index_top .user .avatar-wrapper .nickname{
    font-size: 16px;
    margin-right:5px;
    color: #333;
}

.index_top .user .avatar-wrapper .el-icon-arrow-down{
    color: #333;
}


.user-dropDown{
    padding: 10px 0;
    margin: 5px 0;
    background: #fff;
    border: 1px solid #e6ebf5;
    border-radius: 4px;
    -webkit-box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
}
.user-dropDown li{
    padding: 0 20px !important;
    line-height: 36px !important;
    font-size: 14px !important;
    color: #606266 !important;
}
.user-dropDown li:hover{
    color: #0076ca !important;
    background: none !important;
}

.user-dropDown li.loginOut{
    background: none !important;
    border-color: none !important;
    color: #666 !important;
}
.user-dropDown li.loginOut:hover{
    border-radius: 0px !important;
    background: none !important;
    color: #0076ca !important;
}
.bottom_view {
    width: 100%;
    background: #242424;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 20px 0px;
    min-height: 130px;
    border-width: 0px 0px 0px;
    border-style: solid;
    border-color: #d6dee0;
    font-size: 16px;
    color: #fff;
    line-height: 1.8;
    margin-top:0px;
}


.menu_scrollbar {
    width: 100%;
    margin-top:10px;
    background:none;
    border-width: 0px;
    border-style: solid;
    border-color: rgb(239, 239, 239);
}
.menu_scrollbar .el-scrollbar__view {
    padding-bottom: 0px;
}

.menu_scrollbar .menu_view {
    background: transparent;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    border-bottom:none;
    padding:0px 0;
}

.menu_scrollbar .menu_view .el-menu-item,.menu_scrollbar .menu_view .el-sub-menu,.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title{
    height: 100px;
    line-height: 100px;
    border: 0px;
    color: #333;
    font-size: 16px;
    padding: 0px 10px;
    cursor: pointer;
    white-space: nowrap;
    list-style: none;
    background: transparent;
    text-align: center;
    min-width:80px;
    clip-path: polygon(0% 15%, 15% 0%, 85% 0%, 100% 15%, 100% 85%, 85% 100%, 15% 100%, 0% 85%);
}
.menu_scrollbar .menu_view .el-menu-item:hover,.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title:hover{
    background:var(--theme);
    color: #fff !important;
}
.menu_scrollbar .menu_view .el-menu-item.is-active,.menu_scrollbar .menu_view .el-sub-menu.is-active{
    background:var(--theme);
    color: #fff !important;
}


li.el-menu-item.first-item{
}
li.el-menu-item.first-item i {
    vertical-align: middle;
    margin: 0px 3px;
    width: 34px;
    text-align: center;
    font-size: 18px;
    color: inherit;
}
li.el-menu-item.first-item span{
    font-size: 16px;
    vertical-align: middle;
    color: inherit;
}
li.el-menu-item.first-item.is-active {
    background:var(--theme);
    color: #fff !important;
}
li.el-menu-item.first-item:hover {
    background:var(--theme);
    color: #fff !important;
}


.first-item.el-sub-menu .el-sub-menu__title {
    width: 100% !important;
}
.first-item.el-sub-menu.is-opened .el-sub-menu__title {
    width: 100% !important;
    background:var(--theme);
    color: #fff !important;
}
.first-item.el-sub-menu.is-active .el-sub-menu__title {
    width: 100% !important;
    background:var(--theme);
    color: #fff !important;
}


.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-icon-menu,.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .iconfont{
    vertical-align: middle;
    margin: 0px 3px;
    width: 34px;
    text-align: center;
    font-size: 18px;
    color: inherit;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title span{
    font-size: 16px;
    vertical-align: middle;
    color: inherit;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-sub-menu__icon-arrow{
    position: static;
    margin: -3px 0px 0px 8px;
    font-size: 12px;
    vertical-align: middle;
    color: inherit;
}
.el-menu .el-icon {
    flex-shrink: inherit !important;
}


.el-menu--horizontal .el-menu{
    border: none;
}
.el-menu--horizontal .el-menu .el-menu-item{
    color: rgb(102, 102, 102);
    height: 40px;
    line-height: 40px;
    padding: 0px 20px;
    background: rgb(255, 255, 255);
}
.el-menu--horizontal .el-menu .el-menu-item:hover{
    color: var(--theme) !important;
    background: #fff !important;
}
.el-menu--horizontal .el-menu .el-menu-item.is-active{
    color: var(--theme) !important;
    background: #fff !important;
}


.rotation_view{
    width: 100%;
    margin: 10px auto;
    padding:0 calc((100% - 1200px)/2);
}

.rotation_view .swiper{
    width: 100% !important;
    height: 400px;
    border:0px solid #fff;
    border-radius:0px;
}
.rotation_view .swiper .swiper-slide{
}
.rotation_view .swiper .swiper-slide img{
    width: 100%;
    height: 100%;
    object-fit:cover;
    border-radius:0px;
    clip-path: polygon(0% 2%, 2% 0%, 98% 0%, 100% 2%, 100% 98%, 98% 100%, 2% 100%, 0% 98%);
    border-radius:10px;
}

.rotation_view .swiper .swiper-button-prev{
    margin-left:5%;
    color:var(--theme);
}

.rotation_view .swiper .swiper-button-next{
    margin-right:5%;
    color:var(--theme);
}


.bread_view {
    width:100%;
    background: none;
    color: rgb(51, 51, 51);
    text-align:center;
    font-size:20px;
    font-weight:600;
    border:0px solid #eee;
    padding:15px 20px 10px 50px;
    background:var(--theme);
}

.bread_view .el-breadcrumb{
    font-size: 16px;
    line-height: 1;
}

.bread_view .el-breadcrumb .el-breadcrumb__separator{
    margin: 0px 9px;
    font-weight: 500;
    color: #fff;
}

.bread_view .el-breadcrumb .first_breadcrumb span a{
    color: #fff;
    display: inline-block;
}

.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner{
    color: #fff;
    display: inline-block;
}
.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner.is-link{
    color: #fff !important;
}
.el-breadcrumb__inner a,.el-breadcrumb__inner.is-link {
    color:#fff !important;
}

</style>