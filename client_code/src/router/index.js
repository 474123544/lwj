import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import messagesList from '@/views/pages/messages/list'
import syslogList from '@/views/pages/syslog/list'
import syslogDetail from '@/views/pages/syslog/formModel'
import syslogAdd from '@/views/pages/syslog/formAdd'
import friendshipLinkList from '@/views/pages/friendshipLink/list'
import friendshipLinkDetail from '@/views/pages/friendshipLink/formModel'
import friendshipLinkAdd from '@/views/pages/friendshipLink/formAdd'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import yishengList from '@/views/pages/yisheng/list'
import yishengDetail from '@/views/pages/yisheng/formModel'
import yishengAdd from '@/views/pages/yisheng/formAdd'
import yaopinxinxiList from '@/views/pages/yaopinxinxi/list'
import yaopinxinxiDetail from '@/views/pages/yaopinxinxi/formModel'
import yaopinxinxiAdd from '@/views/pages/yaopinxinxi/formAdd'
import rukujiluList from '@/views/pages/rukujilu/list'
import rukujiluDetail from '@/views/pages/rukujilu/formModel'
import rukujiluAdd from '@/views/pages/rukujilu/formAdd'
import yishengyuyueList from '@/views/pages/yishengyuyue/list'
import yishengyuyueDetail from '@/views/pages/yishengyuyue/formModel'
import yishengyuyueAdd from '@/views/pages/yishengyuyue/formAdd'
import jiuzhentongzhiList from '@/views/pages/jiuzhentongzhi/list'
import jiuzhentongzhiDetail from '@/views/pages/jiuzhentongzhi/formModel'
import jiuzhentongzhiAdd from '@/views/pages/jiuzhentongzhi/formAdd'
import jiuzhenqiandaoList from '@/views/pages/jiuzhenqiandao/list'
import jiuzhenqiandaoDetail from '@/views/pages/jiuzhenqiandao/formModel'
import jiuzhenqiandaoAdd from '@/views/pages/jiuzhenqiandao/formAdd'
import zhenduanbingliList from '@/views/pages/zhenduanbingli/list'
import zhenduanbingliDetail from '@/views/pages/zhenduanbingli/formModel'
import zhenduanbingliAdd from '@/views/pages/zhenduanbingli/formAdd'
import chufangxinxiList from '@/views/pages/chufangxinxi/list'
import chufangxinxiDetail from '@/views/pages/chufangxinxi/formModel'
import chufangxinxiAdd from '@/views/pages/chufangxinxi/formAdd'
import chukujiluList from '@/views/pages/chukujilu/list'
import chukujiluDetail from '@/views/pages/chukujilu/formModel'
import chukujiluAdd from '@/views/pages/chukujilu/formAdd'
import paibanxinxiList from '@/views/pages/paibanxinxi/list'
import paibanxinxiDetail from '@/views/pages/paibanxinxi/formModel'
import paibanxinxiAdd from '@/views/pages/paibanxinxi/formAdd'
import chatMessageList from '@/views/pages/chatMessage/list'
import chatMessageDetail from '@/views/pages/chatMessage/formModel'
import chatMessageAdd from '@/views/pages/chatMessage/formAdd'
import chatFriendList from '@/views/pages/chatFriend/list'
import chatFriendDetail from '@/views/pages/chatFriend/formModel'
import chatFriendAdd from '@/views/pages/chatFriend/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'messagesList',
			component: messagesList
		}
		, {
			path: 'syslogList',
			component: syslogList
		}, {
			path: 'syslogDetail',
			component: syslogDetail
		}, {
			path: 'syslogAdd',
			component: syslogAdd
		}
		, {
			path: 'friendshipLinkList',
			component: friendshipLinkList
		}, {
			path: 'friendshipLinkDetail',
			component: friendshipLinkDetail
		}, {
			path: 'friendshipLinkAdd',
			component: friendshipLinkAdd
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'yishengList',
			component: yishengList
		}, {
			path: 'yishengDetail',
			component: yishengDetail
		}, {
			path: 'yishengAdd',
			component: yishengAdd
		}
		, {
			path: 'yaopinxinxiList',
			component: yaopinxinxiList
		}, {
			path: 'yaopinxinxiDetail',
			component: yaopinxinxiDetail
		}, {
			path: 'yaopinxinxiAdd',
			component: yaopinxinxiAdd
		}
		, {
			path: 'rukujiluList',
			component: rukujiluList
		}, {
			path: 'rukujiluDetail',
			component: rukujiluDetail
		}, {
			path: 'rukujiluAdd',
			component: rukujiluAdd
		}
		, {
			path: 'yishengyuyueList',
			component: yishengyuyueList
		}, {
			path: 'yishengyuyueDetail',
			component: yishengyuyueDetail
		}, {
			path: 'yishengyuyueAdd',
			component: yishengyuyueAdd
		}
		, {
			path: 'jiuzhentongzhiList',
			component: jiuzhentongzhiList
		}, {
			path: 'jiuzhentongzhiDetail',
			component: jiuzhentongzhiDetail
		}, {
			path: 'jiuzhentongzhiAdd',
			component: jiuzhentongzhiAdd
		}
		, {
			path: 'jiuzhenqiandaoList',
			component: jiuzhenqiandaoList
		}, {
			path: 'jiuzhenqiandaoDetail',
			component: jiuzhenqiandaoDetail
		}, {
			path: 'jiuzhenqiandaoAdd',
			component: jiuzhenqiandaoAdd
		}
		, {
			path: 'zhenduanbingliList',
			component: zhenduanbingliList
		}, {
			path: 'zhenduanbingliDetail',
			component: zhenduanbingliDetail
		}, {
			path: 'zhenduanbingliAdd',
			component: zhenduanbingliAdd
		}
		, {
			path: 'chufangxinxiList',
			component: chufangxinxiList
		}, {
			path: 'chufangxinxiDetail',
			component: chufangxinxiDetail
		}, {
			path: 'chufangxinxiAdd',
			component: chufangxinxiAdd
		}
		, {
			path: 'chukujiluList',
			component: chukujiluList
		}, {
			path: 'chukujiluDetail',
			component: chukujiluDetail
		}, {
			path: 'chukujiluAdd',
			component: chukujiluAdd
		}
		, {
			path: 'paibanxinxiList',
			component: paibanxinxiList
		}, {
			path: 'paibanxinxiDetail',
			component: paibanxinxiDetail
		}, {
			path: 'paibanxinxiAdd',
			component: paibanxinxiAdd
		}
		, {
			path: 'chatMessageList',
			component: chatMessageList
		}, {
			path: 'chatMessageDetail',
			component: chatMessageDetail
		}, {
			path: 'chatMessageAdd',
			component: chatMessageAdd
		}
		, {
			path: 'chatFriendList',
			component: chatFriendList
		}, {
			path: 'chatFriendDetail',
			component: chatFriendDetail
		}, {
			path: 'chatFriendAdd',
			component: chatFriendAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
