<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="医生账号" prop="yishengzhanghao">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.yishengzhanghao?true:false"
								v-model="form.yishengzhanghao" 
								placeholder="请选择医生账号"
								@change="yishengzhanghaoChange">
								<el-option v-for="(item,index) in yishengzhanghaoLists" :label="item" :value="item">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="医生姓名" prop="yishengxingming">
							<el-input class="list_inp" v-model="form.yishengxingming" placeholder="医生姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.yishengxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="班次" prop="banci">
							<el-input class="list_inp" v-model="form.banci" placeholder="班次"
                                type="text"
								:readonly="!isAdd||disabledForm.banci?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="上班时间" prop="shangbanshijian">
							<el-date-picker
								class="list_date"
								v-model="form.shangbanshijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:disabled-date="shangbanshijianDisabledDate"
								:readonly="!isAdd||disabledForm.shangbanshijian?true:false"
								placeholder="请选择上班时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="上班时长\小时" prop="shangbanshizhang">
							<el-input class="list_inp" v-model.number="form.shangbanshizhang" placeholder="上班时长\小时"
                                type="text"
								:readonly="!isAdd||disabledForm.shangbanshizhang?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="休假时间" prop="xiujiashijian">
							<el-date-picker
								class="list_date"
								v-model="form.xiujiashijian"
								format="YYYY 年 MM 月 DD 日"
								value-format="YYYY-MM-DD"
								type="datetime"
								:disabled-date="xiujiashijianDisabledDate"
								:readonly="!isAdd||disabledForm.xiujiashijian?true:false"
								placeholder="请选择休假时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="发布时间" prop="fabushijian">
							<el-date-picker
								class="list_date"
								v-model="form.fabushijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.fabushijian?true:false"
								placeholder="请选择发布时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="备注" prop="beizhu">
							<editor :value="form.beizhu" placeholder="请输入备注" :readonly="!isAdd||disabledForm.beizhu?true:false"
								class="list_editor" @change="(e)=>editorChange(e,'beizhu')"></editor>
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
	const tableName = 'paibanxinxi'
	const formName = '排班信息'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        yishengzhanghao : false,
        yishengxingming : false,
        banci : false,
        shangbanshijian : false,
        shangbanshizhang : false,
        xiujiashijian : false,
        fabushijian : false,
        beizhu : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		yishengzhanghao: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		yishengxingming: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		banci: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		shangbanshijian: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		shangbanshizhang: [
			{required: true,message: '请输入',trigger: 'blur'}, 
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		xiujiashijian: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		fabushijian: [
		],
		beizhu: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//医生账号列表
	const yishengzhanghaoLists = ref([])
	// 限制上班时间只能选择今天以后的日期
	const shangbanshijianDisabledDate = (date) => {
		return date.getTime() < Date.now();
	}
	// 限制休假时间只能选择今天以后的日期
	const xiujiashijianDisabledDate = (date) => {
		return date.getTime() < Date.now();
	}

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			yishengzhanghao: '',
			yishengxingming: '',
			banci: '',
			shangbanshijian: '',
			shangbanshizhang: '',
			xiujiashijian: '',
			fabushijian: '',
			beizhu: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			res.data.data.beizhu = res.data.data.beizhu?(res.data.data.beizhu.replace(reg,'../../../cl515882190/file')):'';
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
			form.value.fabushijian = context?.$toolUtil.getCurDateTime()
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
				if(x=='yishengzhanghao'){
					form.value.yishengzhanghao = row[x];
					disabledForm.value.yishengzhanghao = true;
					continue;
				}
				if(x=='yishengxingming'){
					form.value.yishengxingming = row[x];
					disabledForm.value.yishengxingming = true;
					continue;
				}
				if(x=='banci'){
					form.value.banci = row[x];
					disabledForm.value.banci = true;
					continue;
				}
				if(x=='shangbanshijian'){
					form.value.shangbanshijian = row[x];
					disabledForm.value.shangbanshijian = true;
					continue;
				}
				if(x=='shangbanshizhang'){
					form.value.shangbanshizhang = row[x];
					disabledForm.value.shangbanshizhang = true;
					continue;
				}
				if(x=='xiujiashijian'){
					form.value.xiujiashijian = row[x];
					disabledForm.value.xiujiashijian = true;
					continue;
				}
				if(x=='fabushijian'){
					form.value.fabushijian = row[x];
					disabledForm.value.fabushijian = true;
					continue;
				}
				if(x=='beizhu'){
					form.value.beizhu = row[x];
					disabledForm.value.beizhu = true;
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
			url: `option/yisheng/yishengzhanghao`,
			method: 'get'
		}).then(res=>{
			yishengzhanghaoLists.value = res.data.data
		})
		//由上级字段带出不可改
		disabledForm.value.yishengxingming = true;
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
	const yishengzhanghaoChange=()=>{
		context?.$http({
			url: `follow/yisheng/yishengzhanghao?columnValue=` + form.value.yishengzhanghao,
			method: 'get'
		}).then(res=>{
			if(res.data.data.yishengxingming){
				form.value.yishengxingming = res.data.data.yishengxingming
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
				// 富文本
				.list_editor {
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
