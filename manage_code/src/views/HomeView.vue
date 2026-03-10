<template>
	<div class="home_view">
		<div class="projectTitle">欢迎使用 {{projectName}}</div>
		<div class="count_list">
			<el-collapse-transition v-if="btnAuth('yaopinxinxi','首页总数')">
				<el-card v-show="countTypeList.closeyaopinxinxiCountType" class="card_view count-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								药品信息
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenyaopinxinxiCountType')" class="showIcons"
									:class="countTypeList.hiddenyaopinxinxiCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closeyaopinxinxiCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenyaopinxinxiCountType">
							<div class="count_title">药品信息总数</div>
							<div class="count_num">{{yaopinxinxiCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('yishengyuyue','首页总数')">
				<el-card v-show="countTypeList.closeyishengyuyueCountType" class="card_view count-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								医生预约
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenyishengyuyueCountType')" class="showIcons"
									:class="countTypeList.hiddenyishengyuyueCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closeyishengyuyueCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenyishengyuyueCountType">
							<div class="count_title">医生预约总数</div>
							<div class="count_num">{{yishengyuyueCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('chufangxinxi','首页总数')">
				<el-card v-show="countTypeList.closechufangxinxiCountType" class="card_view count-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								处方信息
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenchufangxinxiCountType')" class="showIcons"
									:class="countTypeList.hiddenchufangxinxiCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closechufangxinxiCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenchufangxinxiCountType">
							<div class="count_title">处方信息总数</div>
							<div class="count_num">{{chufangxinxiCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
		<div class="card_list">
			<el-collapse-transition v-if="btnAuth('yaopinxinxi','首页统计')">
				<el-card v-show="cardTypeList.closeyaopinxinxiChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								药品信息
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenyaopinxinxiChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenyaopinxinxiChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closeyaopinxinxiChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenyaopinxinxiChartType1">
							<div id="yaopinxinxikucunEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('yishengyuyue','首页统计')">
				<el-card v-show="cardTypeList.closeyishengyuyueChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								医生预约
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenyishengyuyueChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenyishengyuyueChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closeyishengyuyueChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenyishengyuyueChartType1">
							<div id="yishengyuyueyishengzhanghaoEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('chufangxinxi','首页统计')">
				<el-card v-show="cardTypeList.closechufangxinxiChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								处方信息
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenchufangxinxiChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenchufangxinxiChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closechufangxinxiChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenchufangxinxiChartType1">
							<div id="chufangxinxixujiaojineEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
	</div>
</template>

<script setup>
	import {
		inject,
		nextTick,
		ref,
		getCurrentInstance
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	const countTypeList = ref({
	})
	const getCountList=()=>{
		countTypeList.value.closeyaopinxinxiCountType = true
		countTypeList.value.hiddenyaopinxinxiCountType = true
		if(btnAuth('yaopinxinxi','首页总数')){
			getyaopinxinxiCount()
		}
		countTypeList.value.closeyishengyuyueCountType = true
		countTypeList.value.hiddenyishengyuyueCountType = true
		if(btnAuth('yishengyuyue','首页总数')){
			getyishengyuyueCount()
		}
		countTypeList.value.closechufangxinxiCountType = true
		countTypeList.value.hiddenchufangxinxiCountType = true
		if(btnAuth('chufangxinxi','首页总数')){
			getchufangxinxiCount()
		}
	}
	const yaopinxinxiCount = ref(0)
	const getyaopinxinxiCount = () => {
		context?.$http({
			url:'yaopinxinxi/count',
			method: 'get'
		}).then(res=>{
			yaopinxinxiCount.value = res.data.data
		})
	}
	const yishengyuyueCount = ref(0)
	const getyishengyuyueCount = () => {
		context?.$http({
			url:'yishengyuyue/count',
			method: 'get'
		}).then(res=>{
			yishengyuyueCount.value = res.data.data
		})
	}
	const chufangxinxiCount = ref(0)
	const getchufangxinxiCount = () => {
		context?.$http({
			url:'chufangxinxi/count',
			method: 'get'
		}).then(res=>{
			chufangxinxiCount.value = res.data.data
		})
	}
	const countTypeClick = (e) => {
		countTypeList.value[e] = !countTypeList.value[e]
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	let echarts = inject("echarts")
	const cardTypeClick = (e) =>{
		cardTypeList.value[e] = !cardTypeList.value[e]
		setTimeout(()=>{
			getCardList()
		},1000)
	}
	const cardTypeList = ref({
		closeyaopinxinxiChartType1: true,
		hiddenyaopinxinxiChartType1: true,
		closeyishengyuyueChartType1: true,
		hiddenyishengyuyueChartType1: true,
		closechufangxinxiChartType1: true,
		hiddenchufangxinxiChartType1: true,
	})
	const getCardList = () => {
		if(btnAuth('yaopinxinxi','首页统计')){
			getyaopinxinxiChart1()
		}
		if(btnAuth('yishengyuyue','首页统计')){
			getyishengyuyueChart1()
		}
		if(btnAuth('chufangxinxi','首页统计')){
			getchufangxinxiChart1()
		}
	}
	import '@/assets/js/echarts-theme'
	const getyaopinxinxiChart1 = () => {
		nextTick(()=>{
			var kucunEchart1 = echarts.init(document.getElementById("yaopinxinxikucunEchart1"),'theme');
			context?.$http({
				url: `yaopinxinxi/value/yaopinmingcheng/kucun?order=desc`,
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].yaopinmingcheng);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].yaopinmingcheng
				    })
				}
				var option = {};
				option = {
    title: {
        text: '药品库存统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar',
        barWidth:'20px',
        itemStyle: {
            borderRadius:[4, 4, 4, 4]
        }
      
    }
}

				kucunEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				kucunEchart1.setOption(option);
				//根据窗口的大小变动图表
				kucunEchart1.resize();
			})
		})
	}
	const getyishengyuyueChart1 = () => {
		nextTick(()=>{
			var yishengzhanghaoEchart1 = echarts.init(document.getElementById("yishengyuyueyishengzhanghaoEchart1"),'theme');
			context?.$http({
				url: "yishengyuyue/group/yishengzhanghao",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].yishengzhanghao);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].yishengzhanghao
				    })
				}
				var option = {};
				option = {
    title: {
        text: '医生预约统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar',
        barWidth:'20px',
        itemStyle: {
            borderRadius:[4, 4, 4, 4]
        }
      
    }
}
                var middle = option.xAxis
                option.xAxis = option.yAxis
                option.yAxis = middle

				yishengzhanghaoEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				yishengzhanghaoEchart1.setOption(option);
				//根据窗口的大小变动图表
				yishengzhanghaoEchart1.resize();
			})
		})
	}
	const getchufangxinxiChart1 = () => {
		nextTick(()=>{
			var xujiaojineEchart1 = echarts.init(document.getElementById("chufangxinxixujiaojineEchart1"),'theme');
			context?.$http({
				url: `chufangxinxi/value/zhanghao/xujiaojine?order=desc`,
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].zhanghao);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].zhanghao
				    })
				}
				var option = {};
				option = {
    title: {
        text: '缴费统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'line'
    }
}
				xujiaojineEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				xujiaojineEchart1.setOption(option);
				//根据窗口的大小变动图表
				xujiaojineEchart1.resize();
			})
		})
	}

	const init=()=>{
		getCountList()
		getCardList()
	}
	init()
</script>
<style lang="scss" scoped>
	// 首页盒子
	.home_view {
        background-image: url("http://clfile.zggen.cn/20240915/2ba09c686491481982bda72452d5ca8f.webp")!important;
	}
	.home_view .projectTitle{
	}

	.showIcons {
		transition: transform 0.3s;
		margin-right: 10px;
	}

	.showIcons1 {
		transform: rotate(-180deg);
	}
	
	// 总数盒子
	.count_list{
		// 总数card
		.card_view {
			// card头部
			.el-card__header {
				// 头部盒子
				.index_card_head {
					// 标题
					.card_head_title {
					}
					// 按钮盒子
					.card_head_right {
						// 按钮
						.el-icon {
						}
					}
				}
			}
			// body
			.el-card__body {
				// body盒子
				.count_item{
					// 总数标题
					.count_title{
					}
					// 总数数字
					.count_num{
					}
				}
			}
		}
	}

	// 统计图盒子
	.card_list {
		// 统计图card
		.card_view {
			// 头部
			.el-card__header {
				// 头部盒子
				.index_card_head {
					// 标题
					.card_head_title {
					}
					// 按钮盒子
					.card_head_right {
						// 按钮
						.el-icon{
						}
					}
				}
			}
			// body
			.el-card__body {
				// body盒子
				.card_item{
				}
			}
		}
	}
</style>
<style>
/*总盒子*/
.home_view{
    padding: 20px 30px 20px 40px;
    margin: 0px;
    height: auto;
    min-height: 100vh;
    background: url() no-repeat center top / cover !important;
    width: 100%;
    border-radius: 0px;
    display:flex;
    flex-wrap:wrap;
    align-items:flex-start;
}
.home_view .projectTitle{
    width: 100%;
    font-size: 26px;
    font-weight: bold;
    padding: 40px 0px 20px;
    height: auto;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0px 0px 20px;
    color: rgb(55, 55, 55);
}

/*总数*/
/*总盒子*/
.home_view .count_list {
    display:flex;
    flex-wrap:wrap;
    justify-content:center;
    padding:0px 0px 20px;
    width:100%;
    align-items:flex-start;
}
/*卡片盒子*/
.home_view .count_list .card_view {
    width:23%;
    height:auto;
    margin:0px 1% 20px;
    box-sizing:border-box;
    background:var(--theme10);
    border:1px solid var(--theme30);
    border-radius:20px;
    flex: 1;
    padding:0;
    box-shadow: none;
}

.home_view .count_list .card_view:nth-child(1) {
}
.home_view .count_list .card_view:nth-child(2) {
}
.home_view .count_list .card_view:nth-child(3) {
}
.home_view .count_list .card_view:nth-child(4) {
}
.home_view .count_list .card_view:nth-child(2n+5) {
}
.home_view .count_list .card_view:nth-child(2n+6) {
}
.home_view .count_list .card_view:nth-child(1):hover {
}
.home_view .count_list .card_view:nth-child(2):hover {
}
.home_view .count_list .card_view:nth-child(3):hover {
}
.home_view .count_list .card_view:nth-child(4):hover {
}
.home_view .count_list .card_view:nth-child(2n+5):hover {
}
.home_view .count_list .card_view:nth-child(2n+6):hover {
}

/*head 总盒子*/
.home_view .count_list .card_view .el-card__header{
     width: 100%;
    border: 0px solid rgb(238, 238, 238);
    display: none;
}
/*item*/
.home_view .count_list .card_view .el-card__header .index_card_head{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 0px;
}
/*标题*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_title{
    font-size: 14px;
}
/*按钮盒子*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_right{
    display: flex;
    align-items: center;
}
/*按钮*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_right .showIcons{
    color: rgb(102, 102, 102);
    font-size: 20px;
    cursor: pointer;
}
/*body 总盒子*/
.home_view .count_list .card_view .el-card__body{
    padding: 0px;
    background: none;
}
/*item*/
.home_view .count_list .card_view .el-card__body .count_item{
    padding: 0px;
    text-align: left;
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
    flex-direction: column;
}
/*标题*/
.home_view .count_list .card_view .el-card__body .count_item .count_title{
    font-size: 16px;
    line-height: 2;
    display: inline-block;
    margin: 0px 10px 0px 0px;
    font-weight: 500;
    width: 100%;
    text-align:center;
    color: #333;
    background:var(--theme30);
    padding: 5px;
}
/*数字*/
.home_view .count_list .card_view .el-card__body .count_item .count_num{
    width: 100%;
    font-size: 30px;
    color: var(--theme);
    line-height: 2;
    display: inline-block;
    font-weight: 500;
    text-align:center;
}

/*图表*/
/*总盒子*/
.home_view .card_list{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    padding: 0px 0px 20px;
    align-items: flex-start;
    order:11;
}
/*卡片 总盒子*/
.home_view .card_list .card_view{
    width: 48%;
    height: auto;
    margin: 0px 1% 30px;
    box-sizing: border-box;
    border:1px solid var(--theme30);
    border-radius: 0px;
    padding: 20px 0px 0px;
    box-shadow: 0 0px 0px 0 rgba(0,0,0,.1);
    border-radius:30px 30px 0 0;
}
/*head 总盒子*/
.home_view .card_list .card_view .el-card__header{
    width: 100%;
    border: 0px solid rgb(238, 238, 238);
    background: rgb(255, 255, 255);
    display: none;
}
/*item*/
.home_view .card_list .card_view .el-card__header .index_card_head{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 10px;
}
/*标题*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_title{
    color: rgb(33, 33, 33);
}
/*按钮盒子*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_right{
    display: none;
}
/*按钮*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_right .showIcons{
    color: rgb(238, 238, 238);
    font-size: 20px;
    cursor: pointer;
}

/*body 总盒子*/
.home_view .card_list .card_view .el-card__body{
    padding: 0px;
    background: rgb(255, 255, 255);
}
.home_view .card_list .card_view .el-card__body .card_item{
    padding: 10px;
    text-align: center;
}

.home-calendar{
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px;
  margin: 0px auto 30px;
  border-radius: 0px;
  color: rgb(102, 102, 102);
  background: #fff;
  width: calc(100% - 30px);
  font-size: inherit;
  height: auto;
  padding:0 0 10px;
  order:2;
}
.home-calendar .header{
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  height: auto;
  padding: 17px 40px;
  background:var(--theme10);
  border:1px solid var(--theme30);
  border-radius:30px 30px 0 0;
}
.home-calendar .header .btn{
  cursor: pointer;
  border: 0px solid rgb(153, 153, 153);
  padding: 0px 0px 0px 12px;
  align-items: center;
  color: #333;
  border-radius: 4px;
  display: flex;
  width: auto;
  font-size: 16px;
  justify-content: center;
}
.home-calendar .header .title{
  padding: 0px 10px;
  font-size: 24px;
  align-items: center;
  justify-content: center;
  display: flex;
  color: #333;
}
.home-calendar table{
  width: 100%;
  padding: 0px 0px 20px;
  height: auto;
  border:1px solid var(--theme30);
}
.home-calendar tbody,thead{
  width: 100%;
}
.home-calendar tr{
  width: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
}
.home-calendar th{
  align-items: center;
  flex: 1;
  display: flex;
  line-height: 50px;
  justify-content: center;
}
.home-calendar td{
  cursor: pointer;
  padding: 6px 12px 6px;
  flex: 1;
  display: flex;
  justify-content: center;
  text-align: center;
}
.home-calendar td.today .text{
  width: 80%;
  height: 80%;
  padding:5px 0;
  background: var(--theme50) !important;
  color:var(--theme);
  border-radius:0px;
}
.home-calendar td.festival .text{
  width: 80%;
  height: 80%;
  padding:5px 0;
  background: var(--theme20);
  border-radius:0px;
}

</style>
