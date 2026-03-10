<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="药品名称" prop="yaopinmingcheng">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.yaopinmingcheng?true:false"
								v-model="form.yaopinmingcheng" 
								placeholder="请选择药品名称"
								@change="yaopinmingchengChange">
								<el-option v-for="(item,index) in yaopinmingchengLists" :label="item" :value="item">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="单价" prop="danjia">
							<el-input class="list_inp" v-model.number="form.danjia" placeholder="单价"
                                type="number"
								:readonly="!isAdd||disabledForm.danjia?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="开方数量" prop="kaifangshuliang">
							<el-input class="list_inp" v-model.number="form.kaifangshuliang" placeholder="开方数量"
                                type="text"
								:readonly="!isAdd||disabledForm.kaifangshuliang?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="服用方法" prop="fuyongfangfa">
							<el-input class="list_inp" v-model="form.fuyongfangfa" placeholder="服用方法"
                                type="text"
								:readonly="!isAdd||disabledForm.fuyongfangfa?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="其他费用" prop="qitafeiyong">
							<el-input class="list_inp" v-model.number="form.qitafeiyong" placeholder="其他费用"
                                type="number"
								:readonly="!isAdd||disabledForm.qitafeiyong?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="需缴金额" prop="xujiaojine">
							<el-input class="list_inp" v-model="xujiaojine" :readonly="true" placeholder="需缴金额" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="账号" prop="zhanghao">
							<el-input class="list_inp" v-model="form.zhanghao" placeholder="账号"
                                type="text"
								:readonly="!isAdd||disabledForm.zhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="手机" prop="shouji">
							<el-input class="list_inp" v-model="form.shouji" placeholder="手机"
                                type="text"
								:readonly="!isAdd||disabledForm.shouji?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="开方时间" prop="kaifangshijian">
							<el-date-picker
								class="list_date"
								v-model="form.kaifangshijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.kaifangshijian?true:false"
								placeholder="请选择开方时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="医生账号" prop="yishengzhanghao">
							<el-input class="list_inp" v-model="form.yishengzhanghao" placeholder="医生账号"
                                type="text"
								:readonly="!isAdd||disabledForm.yishengzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="电话" prop="dianhua">
							<el-input class="list_inp" v-model="form.dianhua" placeholder="电话"
                                type="text"
								:readonly="!isAdd||disabledForm.dianhua?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="其他" prop="qita">
							<el-input class="list_inp" v-model="form.qita" placeholder="其他"
                                type="text"
								:readonly="!isAdd||disabledForm.qita?true:false" />
						</el-form-item>
					</el-col>

				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
	//基础信息
	const tableName = 'chufangxinxi'
	const formName = '处方信息'
	//基础信息
	//form表单
	const form = ref({})
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
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
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
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//药品名称列表
	const yaopinmingchengLists = ref([])
	const xujiaojine =computed(()=>{
		let c = form.value
        let a = Number(c.danjia)*Number(c.kaifangshuliang)+Number(c.qitafeiyong)
        form.value.xujiaojine = Number(a)?Number(parseFloat(a).toFixed(2)) : 0
        return Number(a)?Number(parseFloat(a).toFixed(2)) : 0
	})

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			yaopinmingcheng: '',
			danjia: '',
			kaifangshuliang: '',
			fuyongfangfa: '',
			qitafeiyong: '',
			xujiaojine: '',
			zhanghao: '',
			shouji: '',
			kaifangshijian: '',
			yishengzhanghao: '',
			dianhua: '',
			qita: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
			form.value.kaifangshijian = context?.$toolUtil.getCurDateTime()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
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
			formVisible.value = true
		}

		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
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
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	const yaopinmingchengChange=()=>{
		context?.$http({
			url: `follow/yaopinxinxi/yaopinmingcheng?columnValue=` + form.value.yaopinmingcheng,
			method: 'get'
		}).then(res=>{
			if(res.data.data.danjia){
				form.value.danjia = res.data.data.danjia
			}
		})
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
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
					crossUserId = user.value.id
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
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}else{
                context.$message.error('请完善信息')
            }
		})
	}
	//修改跨表数据
	const changeCrossData = async (row)=>{
        if(type.value == 'cross'){
            await context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
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
		.cancel_btn {
		}
		.cancel_btn:hover {
		}
		
		.confirm_btn {
		}
		.confirm_btn:hover {
		}
	}
</style>
