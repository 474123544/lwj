<template>
	<div>
		<div class="home_box">
			<!-- 医生首页展示 -->
			<div class="homeList_view">
				<div class="homeList_title">
                    <span>医生展示</span>
                </div>
				<div class="homeList">
					<div class="item" v-for="(item,index) in yishengHomeList" :key="index" @click="detailClick('yisheng',item.id)">
						<div class="img-box">
							<img v-if="isHttp(item.touxiang)" :src="item.touxiang.split(',')[0]" alt="">
							<img v-else :src="item.touxiang?$config.url + item.touxiang.split(',')[0]:''" alt="">
						</div>
						<div class="content-box">
							<div class="title">
								医生账号：{{item.yishengzhanghao}}
							</div>
							<div class="title">
								医生姓名：{{item.yishengxingming}}
							</div>
						</div>
					</div>
				</div>
				<div class="homeList_more_view" @click="moreClick('yisheng')">
					<span class="homeList_more_text">查看更多 +</span>
				</div>
			</div>
			<!-- 健康知识 -->
			<div class="newsList_view">
				<div class="ntitle"> <div class="n2">NEWS INFORMATION</div> <div class="n1">健康知识</div> </div>
				
				    <div class="nlist">
				         <ul> 
				        <li v-for="(item,index) in newsList" :key="index" @click="newsDetailClick(item)">
				          
				         <div class="tim"><div class="t1">{{moment(item.addtime).format('DD')}}</div><div class="t2">{{moment(item.addtime).format('YYYY-MM-DD')}}</div></div>
				          
				          <div class="infobox">
				            <div class="nam">{{item.title}}</div>
				            <div class="info">{{item.introduction}}</div>
				          </div>
				          
				          <div v-if="index==0" class="imgbox">
				                <img :src="item.imgUrl" >
				            </div>
				          
				        </li>
				         </ul> 
				    </div>
				
				<div class="nmore" @click="moreClick('news')" style="cursor: pointer">查看更多 +</div>
			</div>

			<!--友情链接-->
			<div class="friendLink" v-if="friendLinkList && friendLinkList.length">
				<div class="title-wrapper">
					<div>友情链接</div>
				</div>
				<div class="list">
					<div class="item" v-for="(item,index) in friendLinkList" :key="index" @click="friendlinkClick(item.linkUrl)">
						<img class="img" :src="item.linkImage?($config.url + item.linkImage.split(',')[0]): ''">
						<div class="name">{{item.linkName}}</div>
					</div>
				</div>
			</div>
			<div class="appendBox1"></div>
		</div>
		<formModel ref="newsFormModelRef"></formModel>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import moment from 'moment'
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	//医生首页展示
	const yishengHomeList = ref([])
	const getyishengHomeList = () => {
		let params = {
			page: 1,
			limit: 4,
            sort:'id',
            order:'desc',
		}
		context?.$http({
			url: 'yisheng/list',
			method: 'get',
			params: params
		}).then(res => {
			yishengHomeList.value = res.data.data.list
			yishengHomeList.value.forEach(item=>{
				if(!isHttp(item.touxiang)){
					item.imgUrls = item.touxiang.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	//健康知识弹窗
	import formModel from './news/formModel'
	const newsFormModelRef = ref(null)
	//健康知识
	const newsList = ref([])
	const getNewsList = () => {
		context?.$http({
			url: 'news/list',
			method: 'get',
			params:{
				page:1,
				limit: 4,
                sort:'id',
                order:'desc',
			}
		}).then(res=>{
			newsList.value = res.data.data.list
			newsList.value.forEach(item=>{
				if(!isHttp(item.picture)){
					item.imgUrl=context.$config.url+item.picture.split(',')[0]
				}
			})
		})
	}
	const newsDetailClick = (item) => {
		if (item && item.id){
			newsFormModelRef.value.init(item.id)
		}
	}
	// 友情链
	const friendLinkList = ref([])
	const getFriendLinkList = ()=>{
		context.$http.get('friendshiplink/list', {
            params:{
                page: 1,
                limit: 6,
                sort:'id',
                order:'desc',
            }
        }).then(res => {
			if(res.data.code == 0) {
				friendLinkList.value = res.data.data.list
			}
		})
	}
	const friendlinkClick = (url)=>{
		if(url){
			window.open(url)
		}
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//医生首页展示
		getyishengHomeList()
		//健康知识
		getNewsList()
		getFriendLinkList()
	}
	init()
</script>

<style lang="scss">
	.home_box {
	}

	// 推荐
	.recomList_view {
		.recomList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1.05) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.recommend_more_view {
			.recommend_more_text {
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {

		.newsList_title {
		}
		// list
		.news_list_one {
			display: flex;
			flex-wrap: wrap;
			.news_item {
				box-shadow: 0 4px 6px rgba(0,0,0,.3);
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(33% - 20px);
				align-items: center;
				.news_img_box {
					width: 40%;
					font-size: 0;
					.news_img {
						object-fit: cover;
						width: 100%;
						height: 200px;
					}
				}
				.news_content {
					margin: 0 0 0 20px;
					width: calc(60% - 20px);
					.news_title {
						font-weight: bold;
						font-size: 20px;
					}
					.news_text {
						font-size: 14px;
						line-height: 1.5;
					}
					.news_time {
						color: #999;
						width: 100%;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.news_more_view {
			.news_more_text {
			}
			.el-icon {
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {

		.homeList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.homeList_more_view {
			cursor: pointer;
			.homeList_more_text {
			}
		}
	}
	// 首页展示
</style>
<style>
.home_box{
    width: 100%;
    margin: 0px auto;
    padding:0 calc((100% - 1200px)/2);
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.home_box .appendBox1{
    width: 100%;
    height:0px;
    background:#f00;
    order:5;
}
/* 总盒子 */
.homeList_view{
    width: 100%;
    margin:20px auto;
    padding: 80px 20px 50px;
    border:1px solid #eee;
    background: #fff;
    position:relative;
    order: 6;
    display:flex;
    flex-wrap:wrap;
    justify-content:space-between;
    align-items: flex-start;
    align-content: flex-start;
}

/* 标题 */
.homeList_view .homeList_title {
	position:absolute;
	top:-6px;
	left:calc((100% - 1200px)/2 + 30px);
}
.homeList_view .homeList_title:after {
	position:absolute;
	content:"";
	display:block;
	width:0;
	height:0;
	border-left:48px solid transparent;
	border-right:100px solid transparent;
	border-top:20px solid var(--theme);
}
.homeList_view .homeList_title span {
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
.homeList_view .homeList_title span:before,.homeList_view .homeList_title span:after {
	position:absolute;
	content:"";
	display:block;
}
.homeList_view .homeList_title span:before {
	background:var(--theme);
	height:6px;
	width:6px;
	left:-6px;
	top:0;
}
.homeList_view .homeList_title span:after {
	background:var(--theme2);
	height:6px;
	width:8px;
	border-radius:4px 4px 0 0;
	left:-8px;
	top:0;
}

/* 分类 */
.homeList_view .categoryList{
    width: 100%;
    background: #fff;
    display: flex;
    flex-wrap: wrap;
    margin:20px auto;
    order:0;
    border:0;
}
.homeList_view .categoryList .item{
    background: none;
    line-height:40px;
    color:#333;
    text-align: center;
    margin:0 20px 10px 0;
    cursor:pointer;
    font-size: 15px;
    padding:0 10px;
    border: 1px solid #eee;
    float:left;
}
.homeList_view .categoryList .item:hover{
    color:var(--theme);
    border: 1px solid var(--theme);
}
.homeList_view .categoryList .item.active{
    color:var(--theme);
    border: 1px solid var(--theme);
}

/* 样式七 总盒子 */
.homeList_view .homeList{
    width: calc(100% - 240px);
    margin: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content:flex-start;
    order:2;
    flex:1;
}
.homeList_view .homeList .item{
    width: calc(50% - 20px);
    margin: 0px 20px 20px 0px;
    padding:10px;
    background: none;
    cursor: pointer;
    overflow:hidden;
    position:relative;
    display:flex;
    flex-wrap:wrap;
    justify-content:space-between;
    border:1px solid #ddd;
}
.homeList_view .homeList .item:nth-child(2n){
    margin-right:0px;
} 

.homeList_view .homeList .item .img-box{
    width: 200px;
    height: 168px;
    overflow: hidden;
    margin: 0px;
    text-align:center;
}
.homeList_view .homeList .item .img-box img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.homeList_view .homeList .item .content-box{
    width: calc(100% - 220px);
}

.homeList_view .homeList .item .content-box .title{
    width: 100%;
    line-height: 24px;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
}
.homeList_view .homeList .item:hover .content-box .title{
    color: #000;
}
.homeList_view .homeList .item .content-box .price{
    width: 100%;
    text-align:left;
    color: #f00;
    font-size:16px;
}
.homeList_view .homeList .item:hover .content-box .price{
}
.homeList_view .homeList .item .content-box .statistic{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    margin:10px 0 0;
}
.homeList_view .homeList .item:hover .content-box .statistic{
    color: #000;
}
.homeList_view .homeList .item .content-box .statistic .iconfont{
    color: inherit;
    margin: 0px 4px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .num{
    color: inherit;
}
.homeList_view .homeList .item .content-box .statistic .like{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .collect{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .clickNum{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}

/* 更多 */
.homeList_view .homeList_more_view{
    position: absolute;
    top: 15px;
    right: 20px;
    cursor: pointer;
}
.homeList_view .homeList_more_view span{
}
.homeList_view .homeList_more_view .el-icon-d-arrow-right{
}
/* 总盒子 */
.newsList_view {
    width: 100%;
    margin:20px auto;
    padding: 80px 20px 50px;
    border:1px solid #eee;
    background: #fff;
    position:relative;
    order: 1;
    display:block;
}
/* 自定义 start*/
/* 标题 */
.newsList_view .ntitle {
	position:absolute;
	top:-6px;
	left:calc((100% - 1200px)/2 + 30px);
}
.newsList_view .ntitle:after {
	position:absolute;
	content:"";
	display:block;
	width:0;
	height:0;
	border-left:48px solid transparent;
	border-right:100px solid transparent;
	border-top:20px solid var(--theme);
}
.newsList_view .ntitle .n1 {
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
.newsList_view .ntitle .n1:before,.newsList_view .ntitle .n1:after {
	position:absolute;
	content:"";
	display:block;
}
.newsList_view .ntitle .n1:before {
	background:var(--theme);
	height:6px;
	width:6px;
	left:-6px;
	top:0;
}
.newsList_view .ntitle .n1:after {
	background:var(--theme2);
	height:6px;
	width:8px;
	border-radius:4px 4px 0 0;
	left:-8px;
	top:0;
}

.newsList_view .ntitle .n2{
    display:none;
    font-size: 18px;
    line-height:30px;
    color:#888;
    margin-left:5px;
    order:2;
}

.newsList_view .nlist{
    width: 100%;
    margin:30px auto 0;
    padding: 0 0 0 500px;
    position:relative;
}
.newsList_view .nlist ul{
    margin:0;
    padding:0;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
}
.newsList_view .nlist ul li{
    width: 100%;
    text-align: left;
    background: none;
    border: 0px dashed #aaa;
    cursor:pointer;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
    margin:0 0 20px;
    border-bottom: 1px dashed #dbd4cd;
}
.newsList_view .nlist ul li:last-child{
    border-bottom: none;
}
.newsList_view .nlist ul li .tim {
    display: flex;
    flex-wrap: wrap;
    width: 80px;
    order:2;
    color:#999;
}
.newsList_view .nlist ul li .tim .t1 {
    width: 100%;
    text-align: center;
    font-size: 32px;
    line-height: 1;
}
.newsList_view .nlist ul li:hover .tim .t1 {
}
.newsList_view .nlist ul li .tim .t2 {
    width: 100%;
    text-align: center;
    line-height: 30px;
}
.newsList_view .nlist ul li:hover .tim .t2 {
}
.newsList_view .nlist ul li .infobox {
    width: calc(100% - 110px);
    flex: 1;
    padding:0 20px 20px;
}
.newsList_view .nlist ul li:hover .infobox {
}
.newsList_view .nlist ul li .infobox .nam {
    font-size: 16px;
    font-weight: 600;
}
.newsList_view .nlist ul li:hover .infobox .nam{
    color:var(--theme);
}
.newsList_view .nlist ul li .infobox .info {
    font-size: 15px;
    color: #888;
    line-height: 24px;
    height:48px;
    overflow:hidden;
    margin-top: 5px;
}

.newsList_view .nlist ul li .imgbox{
    position:absolute;
    left:0;
    top:0;
    width:480px;
    height:450px;
}
.newsList_view .nlist ul li .imgbox img{
    width:100%;
    height:100%;
    object-fit: cover;
}

.newsList_view .nmore{
    position: absolute;
    top: 16px;
    right: calc((100% - 1160px) / 2);
    cursor: pointer;
}
/* 自定义 end*/

.friendLink {
    width: 100%;
    margin:20px auto;
    padding: 80px 20px 50px;
    border:1px solid #eee;
    background: #fff;
    position:relative;
    order: 22;
}
/* 标题 */
.friendLink .title-wrapper{
	position:absolute;
	top:-6px;
	left:calc((100% - 1200px)/2 + 30px);
}
.friendLink .title-wrapper:after{
	position:absolute;
	content:"";
	display:block;
	width:0;
	height:0;
	border-left:48px solid transparent;
	border-right:100px solid transparent;
	border-top:20px solid var(--theme);
}
.friendLink .title-wrapper div{
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
.friendLink .title-wrapper div:before,.friendLink .title-wrapper div:after{
	position:absolute;
	content:"";
	display:block;
}
.friendLink .title-wrapper div:before {
	background:var(--theme);
	height:6px;
	width:6px;
	left:-6px;
	top:0;
}
.friendLink .title-wrapper div:after {
	background:var(--theme2);
	height:6px;
	width:8px;
	border-radius:4px 4px 0 0;
	left:-8px;
	top:0;
}

.friendLink .list {
    width: 100%;
    display: inline-block;
    column-gap: 0px;
    text-align: left;
    flex:1;
    height:44px;
    line-height:44px;
    padding: 0 10px;

}
.friendLink .list .item{
    width: calc(16.6% - 20px);
    display: inline-block;
    cursor:pointer;
    margin:0 20px 0px 0;
}
.friendLink .list .item img {
    width: 100%;
    height:90px;
    object-fit:contain;
    border:1px solid #eee;
}
.friendLink .list .item .name{
    width:100%;
    font-size: 14px;
    line-height: 30px;
    color:#888;
    text-align: center;
    white-space:nowrap; overflow:hidden; text-overflow:ellipsis;
}

</style>