	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import chukujilu from '@/views/chukujilu/list'
	import yishengyuyue from '@/views/yishengyuyue/list'
	import zhenduanbingli from '@/views/zhenduanbingli/list'
	import jiuzhentongzhi from '@/views/jiuzhentongzhi/list'
	import yaopinxinxi from '@/views/yaopinxinxi/list'
	import syslog from '@/views/syslog/list'
	import rukujilu from '@/views/rukujilu/list'
	import users from '@/views/users/list'
	import jiuzhenqiandao from '@/views/jiuzhenqiandao/list'
	import paibanxinxi from '@/views/paibanxinxi/list'
	import chufangxinxi from '@/views/chufangxinxi/list'
	import yonghu from '@/views/yonghu/list'
	import friendshipLink from '@/views/friendshipLink/list'
	import messages from '@/views/messages/list'
	import yisheng from '@/views/yisheng/list'
	import config from '@/views/config/list'
	import usersCenter from '@/views/users/center'
	import yishengRegister from '@/views/yisheng/register'
	import yishengCenter from '@/views/yisheng/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/yishengCenter',
			name: '医生个人中心',
			component: yishengCenter
		}
		,{
			path: '/news',
			name: '健康知识',
			component: news
		}
		,{
			path: '/chukujilu',
			name: '出库记录',
			component: chukujilu
		}
		,{
			path: '/yishengyuyue',
			name: '医生预约',
			component: yishengyuyue
		}
		,{
			path: '/zhenduanbingli',
			name: '诊断病历',
			component: zhenduanbingli
		}
		,{
			path: '/jiuzhentongzhi',
			name: '就诊通知',
			component: jiuzhentongzhi
		}
		,{
			path: '/yaopinxinxi',
			name: '药品信息',
			component: yaopinxinxi
		}
		,{
			path: '/syslog',
			name: '操作日志',
			component: syslog
		}
		,{
			path: '/rukujilu',
			name: '入库记录',
			component: rukujilu
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/jiuzhenqiandao',
			name: '就诊签到',
			component: jiuzhenqiandao
		}
		,{
			path: '/paibanxinxi',
			name: '排班信息',
			component: paibanxinxi
		}
		,{
			path: '/chufangxinxi',
			name: '处方信息',
			component: chufangxinxi
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/friendshipLink',
			name: '友情链接',
			component: friendshipLink
		}
		,{
			path: '/messages',
			name: '反馈建议',
			component: messages
		}
		,{
			path: '/yisheng',
			name: '医生',
			component: yisheng
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		]
	},
	{
		path: '/yishengRegister',
		name: '医生注册',
		component: yishengRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
