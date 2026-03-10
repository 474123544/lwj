<template>
	<div class="menu_wrapper">
		<el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
			<el-menu :default-openeds="[]" :unique-opened="true" default-active="0" class="menu_view"
				:collapse="collapse">
				<el-menu-item class="first-item" :index="0" @click="menuHandler('')">
					<i class="iconfont icon-zhuye2" v-if="collapse?false:true"></i>
					<template #title>
						<span>首页</span>
					</template>
				</el-menu-item>
                <template v-for=" (item,index) in menuList.backMenu">
                    <el-sub-menu   class="first-item" :index="index+2+''">
                        <template #title>
                            <i class="iconfont" :class="item.fontClass" v-if="collapse?false:true"></i>
                            <span>{{ item.menu }}</span>
                        </template>
                        <el-menu-item class="second-item" v-for=" (child,sort) in item.child" :key="sort"
                            :index="(index+2)+'-'+sort"
                            @click="menuHandler(child.classname||child.tableName,child.menuJump)">{{ child.menu }}
                        </el-menu-item>
                    </el-sub-menu>
                </template>
			</el-menu>
		</el-scrollbar>
	</div>
</template>

<script setup>
	import menu from '@/utils/menu'
	import {
		ref,
		toRefs,
		getCurrentInstance,
		nextTick
	} from 'vue';
	import { useStore } from 'vuex'
	const store = useStore()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//props
	const props = defineProps({
		collapse: Boolean
	})
	const {
		collapse
	} = toRefs(props)
	//data
	const menuList = ref([])
	const role = ref('')
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	const init = () => {
		const menus = menu.list()
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('role')

		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				menuList.value = menuList.value[i];
				break;
			}
		}
	}
	const menuHandler = (name,menuJump) => {
        if(name == 'center'){
            context.$router.push(`/${role.value}Center`)
        }else if(name == 'storeup'){
            context.$router.push(`/storeup?type=${menuJump}`)
        }else if(name == 'exampaper' && menuJump == '12'){
            context.$router.push('/exampaperlist')
        }else if(name == 'examrecord' && menuJump == '22'){
            context.$router.push('/examfailrecord')
        }else{
            context.$router.push(`/${name}${menuJump?'?menuJump='+menuJump:''}`)
        }
	}
	init()
</script>

<style lang="scss" scoped>
	// 总盒子
	:deep(.menu_scrollbar) {

		// 菜单盒子-展开样式
		.menu_view {

			// 无二级菜单
			.el-menu-item {
				.iconfont {
				}
			}

			// 无二级悬浮
			.el-menu-item:hover {
			}

			// 无二级选中
			.el-menu-item.is-active {
			}

			// 有二级盒子
			.el-sub-menu {

				// 有二级item
				.el-sub-menu__title {
					.iconfont {
					}
					.el-sub-menu__icon-arrow{
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
				}
			}
			// 二级盒子
			.el-menu--inline {
				// 二级菜单
				.second-item {
				}
				// 二级悬浮
				.second-item:hover {
				}
				// 二级选中
				.is-active.second-item {
				}
			}
		}
		// 菜单盒子-关闭样式
		.el-menu--collapse {
			padding: 0;
			background: none;
			height: 100%;

			// 无二级菜单
			.el-menu-item {
				border: none;
				padding: 0 10px;
				color: #ccc;
				background: none;
				line-height: 50px;
				height: 50px;
			}

			// 无二级悬浮
			.el-menu-item:hover {
				padding: 0 10px;
				color: #ccc;
				background: none;
				line-height: 50px;
				height: 50px;
			}

			// 无二级选中
			.el-menu-item.is-active {
				padding: 0 10px;
				color: #ccc;
				background: none;
				line-height: 50px;
				height: 50px;
			}

			// 有二级盒子
			.el-sub-menu {
				cursor: pointer;
				padding: 0 0;
				white-space: nowrap;
				background: none;
				position: relative;

				// 有二级item
				.el-sub-menu__title {
					border: none;
					padding: 0 10px;
					color: #ccc;
					background: none;
					line-height: 50px;
					height: 50px;
					.el-sub-menu__icon-arrow{
						margin: -3px 0 0 8px;
						color: inherit;
						vertical-align: middle;
						font-size: 12px;
						position: static;
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
					padding: 0 10px;
					color: #ccc;
					background: none;
					line-height: 50px;
					height: 50px;
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
					padding: 0 10px;
					color: #ccc;
					background: none;
					line-height: 50px;
					height: 50px;
				}
			}
			// 二级盒子
			.el-menu--inline {
				border: none;
				padding: 0px;
				background: none;
				// 二级菜单
				.second-item {
					padding: 0 40px;
					color: #666;
					background: none;
					line-height: 50px;
					height: 50px;
				}
				// 二级悬浮
				.second-item:hover {
					padding: 0 40px;
					color: #f00;
					background: greenyellow;
					line-height: 50px;
					height: 50px;
				}
				// 二级选中
				.is-active.second-item {
					padding: 0 40px;
					color: #000;
					background: none;
					line-height: 50px;
					height: 50px;
				}
			}
		}
	}
</style>
<style lang="scss">
	.el-menu-item .el-menu-tooltip__trigger {
		padding: 0;
	}
	.el-popper{
		.el-menu--popup-container {
			.el-menu--popup{
				border: none;
				padding: 0px;
				background: none;
				// 二级菜单
				.second-item {
					padding: 0 40px;
					color: #666;
					background: none;
					line-height: 50px;
					height: 50px;
				}
				// 二级悬浮
				.second-item:hover {
					padding: 0 40px;
					color: #f00;
					background: greenyellow;
					line-height: 50px;
					height: 50px;
				}
				// 二级选中
				.is-active.second-item {
					padding: 0 40px;
					color: #000;
					background: none;
					line-height: 50px;
					height: 50px;
				}
			}
		}
	}
</style>
<style>
/* 菜单展开样式 */
/* 中间内容总盒子 */
.main-container{
    margin: 0px 0px 0px 250px;
    padding: 110px 0px 0px !important;
    background: #fff;
    overflow: hidden;
}

/* 总盒子 */
.menu_wrapper{
    width: 250px;
    background: #fff;
    height: 100%;
    overflow: hidden;
    position: fixed;
    top: 80px;
    left: 0px;
    border-width: 0 3px 0 0;
    border-style: solid;
    border-color: #8b77d1;
    padding: 20px 10px;
    transition: all 0.3s ease-in-out 0s;
}
/* 菜单盒子 */
.menu_wrapper .menu_view{
    height: 100%;
    background: none;
    color: #333;
    padding: 20px 0px 100px;
    border: 0px;
    transition: all 0s ease 0s;
}

/* 一级菜单 */
/* 盒子 */
.menu_wrapper .menu_view li.el-sub-menu{
    padding: 0px;
    cursor: pointer;
    position: relative;
    white-space: nowrap;
    background: none;
    color: rgb(51, 51, 51);
}

/* item */
.menu_wrapper .menu_view .first-item.el-sub-menu .el-sub-menu__title{
    height: 50px;
    padding: 0px 10px 0px 20px;
    color: #333;
    background:none !important;
    line-height: 50px;
    border-width: 0px 0 0px;
    border-style: solid;
    border-color:  #1d87d1#45adf5 #45adf5;
    font-weight: 600;
    margin: 0 0 10px;
    transition: all 0s ease 0s;
}
/* 悬浮*/
.menu_wrapper .menu_view li.el-sub-menu .el-sub-menu__title:hover{
    background:var(--theme10) !important;
    color: var(--theme);
}
/* 选中 */
.menu_wrapper .menu_view li.el-sub-menu .el-sub-menu__title.is-active{
    background:var(--theme10) !important;
    color: var(--theme);
}
/* 箭头*/
.menu_wrapper .menu_view li.el-sub-menu .el-sub-menu__title .el-sub-menu__icon-arrow{
    position: absolute;
    margin: -6px 0px 0px 8px;
    font-size: 12px;
    vertical-align: middle;
    color: inherit;
    right: 10px;
    display: none;
}
.menu_wrapper .menu_view .el-menu-item{
    height: 50px;
    padding: 0px 10px 0px 20px!important;
    color: #333;
    background: url() no-repeat center top / 100% 100%;
    line-height: 50px;
    border-width: 0px;
    border-style: solid;
    border-color: rgb(255, 156, 5);
    font-weight: 600;
    margin: 0 0 10px;
    transition: all 0s ease 0s;
    align-items: center;
    display: flex;
}
.menu_wrapper .menu_view .el-menu-item:hover{
    background:var(--theme10) !important;
    color: var(--theme);
}
.menu_wrapper .menu_view .el-menu-item.is-active{
    background:var(--theme10) !important;
    color: var(--theme);
}
.menu_wrapper .menu_view .el-sub-menu.is-opened .el-sub-menu__title {
    background:var(--theme10) !important;
    color: var(--theme);
}
.menu_wrapper .menu_view .el-sub-menu.is-active .el-sub-menu__title {
    background:var(--theme10) !important;
    color: var(--theme);
}

.menu_wrapper .first-item .el-menu--inline {
    background: var(--theme);
}
.menu_wrapper .first-item i:first-child {
    font-size: 18px!important;
    margin-right: 8px;
    line-height: 18px!important;
    background: #fff;
    color: var(--theme);
    border-radius: 50%;
    padding: 4px;
    width: auto;
}

.first-item:nth-child(5n+1) i {
    color: #D3623D!important;
}
.first-item:nth-child(5n+2) i {
    color: #36B874!important;
}
.first-item:nth-child(5n+3) i {
    color: #2EA6E7!important;
}
.first-item:nth-child(5n+4) i {
    color: #3965E1!important;
}
.first-item:nth-child(5n+5) i {
    color: #DFB528!important;
}


/* 二级菜单 */
/* 盒子 */
.menu_wrapper .menu_view .el-menu--inline{
    border: none;
    padding: 0px;
    background: none;
    width: auto;
    margin: 0 0 10px;
    box-sizing: border-box;
}
/* item */
.menu_wrapper .menu_view .el-menu--inline .second-item{
    height: 40px;
    line-height: 40px;
    padding: 0px 20px 0px 45px;
    background: none;
    color: #333;
    background: none !important;
    border-width: 0px;
    border-style: solid;
    border-color: rgb(251, 190, 98);
    display: block;
    margin: 0 0 5px;
    font-weight: 600;
    border-radius: 0px;
    min-width:auto;
}
/* 悬浮 */
.menu_wrapper .menu_view .el-menu .second-item:hover{
    color: #333;
    background: linear-gradient(180deg, rgba(255,255,255,0) 0%, rgba(255,255,255,1) 50%, rgba(255,255,255,0) 100%) !important;
    min-width:auto;
}
/* 选中 */
.menu_wrapper .menu_view .el-menu--inline .second-item.is-active{
    color: #333;
    background: linear-gradient(180deg, rgba(255,255,255,0) 0%, rgba(255,255,255,1) 50%, rgba(255,255,255,0) 100%) !important;
    min-width:auto;
}

</style>