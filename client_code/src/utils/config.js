const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '医生',
					icon: '',
					child:[

						{
							name:'医生',
                            url:'/index/yishengList'

						},
					]
				},
				{
					name: '反馈建议',
					icon: '',
					child:[

						{
							name:'反馈建议',
                            url:'/index/messagesList'

						},
					]
				},
				{
					name: '公告资讯',
					icon: '',
					child:[

						{
							name:'健康知识',
                            url:'/index/newsList'

						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: `基于JAVA的医院管理系统的设计与实现`
        } 
    }
}
export default config
