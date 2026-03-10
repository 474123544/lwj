<template>
	<div class="edit_view" :style='{}'>
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="Ξ" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>
		<el-form ref="formRef" :model="form" class="add_form" label-width="120px" :rules="rules">
			<el-row>
				<el-col :span="12">
					<el-form-item label="药品名称" prop="yaopinmingcheng">
						<el-select
							class="list_sel"
							:disabled="!isAdd||disabledForm.yaopinmingcheng?true:false"
							v-model="form.yaopinmingcheng" 
							placeholder="请选择药品名称"
							style="width:100%;"
							@change="yaopinmingchengChange">
							<el-option v-for="(item,index) in yaopinmingchengLists" :label="item" :value="item">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="单价" prop="danjia">
						<el-input class="list_inp" v-model.number="form.danjia" placeholder="单价"
							 type="number" 							:readonly="!isAdd||disabledForm.danjia?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="开方数量" prop="kaifangshuliang">
						<el-input class="list_inp" v-model.number="form.kaifangshuliang" placeholder="开方数量"
							 type="text" 							:readonly="!isAdd||disabledForm.kaifangshuliang?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="服用方法" prop="fuyongfangfa">
						<el-input class="list_inp" v-model="form.fuyongfangfa" placeholder="服用方法"
							 type="text" 							:readonly="!isAdd||disabledForm.fuyongfangfa?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="其他费用" prop="qitafeiyong">
						<el-input class="list_inp" v-model.number="form.qitafeiyong" placeholder="其他费用"
							 type="number" 							:readonly="!isAdd||disabledForm.qitafeiyong?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="需缴金额" prop="xujiaojine">
						<el-input class="list_inp" v-model="xujiaojine" placeholder="需缴金额" readonly></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="账号" prop="zhanghao">
						<el-input class="list_inp" v-model="form.zhanghao" placeholder="账号"
							 type="text" 							:readonly="!isAdd||disabledForm.zhanghao?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="手机" prop="shouji">
						<el-input class="list_inp" v-model="form.shouji" placeholder="手机"
							 type="text" 							:readonly="!isAdd||disabledForm.shouji?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="开方时间" prop="kaifangshijian">
						<el-date-picker
							class="list_date"
							v-model="form.kaifangshijian"
							format="YYYY-MM-DD HH:mm:ss"
							value-format="YYYY-MM-DD HH:mm:ss"
							type="datetime"
							style="width:100%;"
							:readonly="!isAdd||disabledForm.kaifangshijian?true:false"
							placeholder="请选择开方时间" />
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="医生账号" prop="yishengzhanghao">
						<el-input class="list_inp" v-model="form.yishengzhanghao" placeholder="医生账号"
							 type="text" 							:readonly="!isAdd||disabledForm.yishengzhanghao?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="电话" prop="dianhua">
						<el-input class="list_inp" v-model="form.dianhua" placeholder="电话"
							 type="text" 							:readonly="!isAdd||disabledForm.dianhua?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="其他" prop="qita">
						<el-input class="list_inp" v-model="form.qita" placeholder="其他"
							 type="text" 							:readonly="!isAdd||disabledForm.qita?true:false" />
					</el-form-item>
				</el-col>

			</el-row>
			<div class="formModel_btn_box">
				<el-button class="formModel_cancel" @click="backClick">取消</el-button>
				<el-button class="formModel_confirm"
                           @click="save"
                           type="success"
				>
                    提交
				</el-button>
                <payForm ref="pay" @close="payClose"></payForm>
			</div>
		</el-form>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import moment from "moment";
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'chufangxinxi'
	const formName = '处方信息'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//form表单
	const form = ref({
		yaopinmingcheng: '',
		danjia: 0,
		kaifangshuliang: 0,
		fuyongfangfa: '',
		qitafeiyong: 0,
		xujiaojine: 0,
		zhanghao: '',
		shouji: '',
		kaifangshijian: '',
		yishengzhanghao: '',
		dianhua: '',
		qita: '',
	})
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	const disabledForm = ref({
		yaopinmingcheng : false,
		danjia : false,
		kaifangshuliang : false,
		fuyongfangfa : false,
		qitafeiyong : false,
		xujiaojine : false,
		zhanghao : false,
		shouji : false,
		kaifangshijian : false,
		yishengzhanghao : false,
		dianhua : false,
		qita : false,
		ispay : false,
	})
	const isAdd = ref(false)
	//表单验证
	const rules = ref({
		yaopinmingcheng: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		danjia: [
			{required: true,message: '请输入',trigger: 'blur'}, 
			{ validator: context.$toolUtil.validator.number, trigger: 'blur' },
		],
		kaifangshuliang: [
			{required: true,message: '请输入',trigger: 'blur'}, 
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		fuyongfangfa: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		qitafeiyong: [
			{ validator: context.$toolUtil.validator.number, trigger: 'blur' },
		],
		xujiaojine: [
			{ validator: context.$toolUtil.validator.number, trigger: 'blur' },
		],
		zhanghao: [
		],
		shouji: [
		],
		kaifangshijian: [
		],
		yishengzhanghao: [
		],
		dianhua: [
		],
		qita: [
		],
		ispay: [
		],
	})
	//药品名称列表
	const yaopinmingchengLists = ref([])
	const xujiaojine =computed(()=>{
		let c = form.value
        let a = Number(c.danjia)*Number(c.kaifangshuliang)+Number(c.qitafeiyong)
        form.value.xujiaojine = Number(a)?Number(parseFloat(a).toFixed(2)) : 0
        return Number(a)?Number(parseFloat(a).toFixed(2)) : 0
	})
	//methods

	//methods
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init = (formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null) => {
			form.value.kaifangshijian = context?.$toolUtil.getCurDateTime()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			// getInfo()
			for(let x in row){
				if(x=='yaopinmingcheng'){
					form.value.yaopinmingcheng = row[x];
					disabledForm.value.yaopinmingcheng = true;
					continue;
				}
				if(x=='danjia'){
					form.value.danjia = row[x];
					disabledForm.value.danjia = true;
					continue;
				}
				if(x=='kaifangshuliang'){
					form.value.kaifangshuliang = row[x];
					disabledForm.value.kaifangshuliang = true;
					continue;
				}
				if(x=='fuyongfangfa'){
					form.value.fuyongfangfa = row[x];
					disabledForm.value.fuyongfangfa = true;
					continue;
				}
				if(x=='qitafeiyong'){
					form.value.qitafeiyong = row[x];
					disabledForm.value.qitafeiyong = true;
					continue;
				}
				if(x=='xujiaojine'){
					form.value.xujiaojine = row[x];
					disabledForm.value.xujiaojine = true;
					continue;
				}
				if(x=='zhanghao'){
					form.value.zhanghao = row[x];
					disabledForm.value.zhanghao = true;
					continue;
				}
				if(x=='shouji'){
					form.value.shouji = row[x];
					disabledForm.value.shouji = true;
					continue;
				}
				if(x=='kaifangshijian'){
					form.value.kaifangshijian = row[x];
					disabledForm.value.kaifangshijian = true;
					continue;
				}
				if(x=='yishengzhanghao'){
					form.value.yishengzhanghao = row[x];
					disabledForm.value.yishengzhanghao = true;
					continue;
				}
				if(x=='dianhua'){
					form.value.dianhua = row[x];
					disabledForm.value.dianhua = true;
					continue;
				}
				if(x=='qita'){
					form.value.qita = row[x];
					disabledForm.value.qita = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
            if (localStorage.getItem('autoSave')) {
                localStorage.removeItem('autoSave')
                save()
            }
		})
		context?.$http({
			url: `option/yaopinxinxi/yaopinmingcheng`,
			method: 'get'
		}).then(res=>{
			yaopinmingchengLists.value = res.data.data
		})
		//由上级字段带出不可改
		disabledForm.value.danjia = true;
	}
	//初始化
	//取消
	const backClick = () => {
		history.back()
	}
	//药品名称回调
	const yaopinmingchengChange=()=>{
		context?.$http({
			url: `follow/yaopinxinxi/yaopinmingcheng?columnValue=` + form.value.yaopinmingcheng,
			method: 'get'
		}).then(res=>{
			//带出单价字段
			if(res.data.data.danjia){
				form.value.danjia = res.data.data.danjia
			}
		})
	}
	//提交
	const save=()=>{
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		if(type.value == 'cross'){
			if(crossColumnName.value!=''){
				if(!crossColumnName.value.startsWith('[')){
					for(let o in objcross){
						if(o == crossColumnName.value){
							objcross[o] = crossColumnValue.value
						}
					}
					//修改跨表数据
					changeCrossData(objcross)
				}else{
					crossUserId = context?.$toolUtil.storageGet('userid')
					crossRefId = objcross['id']
					crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
				}
			}
		}
		formRef.value.validate((valid)=>{
			if(valid){
				if(crossUserId&&crossRefId){
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(res=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(res=>{
                                if(context.$toolUtil.isBackAuth('chufangxinxi','支付')){
                                    context.$http.get(`${tableName}/page?sort=id&order=desc`).then(res1=>{
                                        pay.value.payClick(tableName,res1.data.data.list[0])
                                    })
                                    return
                                }
                                context?.$toolUtil.message(`操作成功`,'success')
                                history.back()
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(res=>{
                        if(context.$toolUtil.isBackAuth('chufangxinxi','支付')){
                            context.$http.get(`${tableName}/page?sort=id&order=desc`).then(res1=>{
                                pay.value.payClick(tableName,res1.data.data.list[0])
                            })
                            return
                        }
                        context?.$toolUtil.message(`操作成功`,'success')
                        history.back()
					})
				}
			}
		})
	}
    import payForm from '@/components/payForm'
    const pay = ref(null)
    const payClose = (e)=>{
        history.back()
    }
	//修改跨表数据
	const changeCrossData=(row)=>{
        if(type.value == 'cross'){
            context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
	onMounted(()=>{
		type.value = route.query.type?route.query.type:'add'
		let row = null
		let table = null
		let statusColumnName = null
		let tips = null
		let statusColumnValue = null
		if(type.value == 'cross'){
			row = context?.$toolUtil.storageGet('crossObj')?JSON.parse(context?.$toolUtil.storageGet('crossObj')):{}
			table = context?.$toolUtil.storageGet('crossTable')
			statusColumnName = context?.$toolUtil.storageGet('crossStatusColumnName')
			tips = context?.$toolUtil.storageGet('crossTips')
			statusColumnValue = context?.$toolUtil.storageGet('crossStatusColumnValue')
		}
		init(route.query.id?route.query.id:null, type.value,'', row, table, statusColumnName, tips, statusColumnValue)
	})
    onUnmounted(()=>{
        Object.keys(localStorage).map(item=>{
            if(item.startsWith('cross')){
                localStorage.removeItem(item)
            }
        })
    })
</script>
<style lang="scss" scoped>
	// 面包屑盒子
	.bread_view {
		:deep(.breadcrumb) {
			.el-breadcrumb__separator {
			}
			.first_breadcrumb {
				.el-breadcrumb__inner {
				}
			}
			.second_breadcrumb {
				.el-breadcrumb__inner {
				}
			}
		}
	}
	// 表单
	.add_form{
		// form item
		:deep(.el-form-item) {
			//label
			.el-form-item__label {
			}
			// 内容盒子
			.el-form-item__content {
				// 输入框
				.list_inp {

				}
				//日期选择器
				.list_date {
				}
				// 下拉框
				.list_sel {
					//去掉默认样式
					.select-trigger{
						height: 100%;
						.el-input{
							height: 100%;
						}
					}
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
.edit_view {
    width: 1200px;
    margin: 20px auto;
    padding: 20px 20px 20px ;
    background: #fff;
    overflow: hidden;
    border: 0px solid #eee;
    font-size: 16px;
    color:#666;
}
.edit_view .add_form{
    width: 100%;
    padding: 30px;
    border:0px solid #eee
}
.edit_view .add_form .el-form-item{
    margin: 0px 0px 20px;
    display: flex;
}
.edit_view .add_form .el-form-item .el-form-item__label{
    width: 150px;
    background: none;
    text-align: right;
    display: block;
    font-size: 16px;
    color: rgb(51, 51, 51);
    font-weight: 500;
}
.edit_view .add_form .el-form-item .el-form-item__content{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    width: calc(100% - 120px);
}
.edit_view .add_form .el-form-item .el-form-item__content .list_inp{
    height: 36px;
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    padding: 0px 10px;
    width: 100%;
    box-sizing: border-box;
    background: rgb(255, 255, 255);
    font-size: 16px;
}

.edit_view .add_form .el-form-item .el-form-item__content .list_sel{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: calc(100% - 0px);
    padding: 0px 10px;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}

.edit_view .add_form .el-form-item .el-form-item__content .list_date{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: 100%;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}








</style>