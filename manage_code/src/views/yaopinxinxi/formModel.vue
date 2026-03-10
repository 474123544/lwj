<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="药品名称" prop="yaopinmingcheng">
							<el-input class="list_inp" v-model="form.yaopinmingcheng" placeholder="药品名称"
                                type="text"
								:readonly="!isAdd||disabledForm.yaopinmingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="规格" prop="guige">
							<el-input class="list_inp" v-model="form.guige" placeholder="规格"
                                type="text"
								:readonly="!isAdd||disabledForm.guige?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="生产日期" prop="shengchanriqi">
							<el-date-picker
								class="list_date"
								v-model="form.shengchanriqi"
								format="YYYY 年 MM 月 DD 日"
								value-format="YYYY-MM-DD"
								type="datetime"
								:readonly="!isAdd||disabledForm.shengchanriqi?true:false"
								placeholder="请选择生产日期" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="有效期" prop="youxiaoqi">
							<el-input class="list_inp" v-model="form.youxiaoqi" placeholder="有效期"
                                type="text"
								:readonly="!isAdd||disabledForm.youxiaoqi?true:false" />
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
						<el-form-item label="库存" prop="kucun">
							<el-input class="list_inp" v-model.number="form.kucun" placeholder="库存"
                                type="text"
								:readonly="!isAdd||disabledForm.kucun?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="新增时间" prop="xinzengshijian">
							<el-date-picker
								class="list_date"
								v-model="form.xinzengshijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.xinzengshijian?true:false"
								placeholder="请选择新增时间" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="备注" prop="beizhu">
							<el-input class="list_inp" v-model="form.beizhu" placeholder="备注"
                                type="text"
								:readonly="!isAdd||disabledForm.beizhu?true:false" />
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
	const tableName = 'yaopinxinxi'
	const formName = '药品信息'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        yaopinmingcheng : false,
        guige : false,
        shengchanriqi : false,
        youxiaoqi : false,
        danjia : false,
        kucun : false,
        xinzengshijian : false,
        beizhu : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		yaopinmingcheng: [
		],
		guige: [
		],
		shengchanriqi: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		youxiaoqi: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		danjia: [
			{ validator: context.$toolUtil.validator.number, trigger: 'blur' },
		],
		kucun: [
			{required: true,message: '请输入',trigger: 'blur'}, 
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		xinzengshijian: [
		],
		beizhu: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			yaopinmingcheng: '',
			guige: '',
			shengchanriqi: '',
			youxiaoqi: '',
			danjia: '',
			kucun: '',
			xinzengshijian: '',
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
			form.value.xinzengshijian = context?.$toolUtil.getCurDateTime()
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
				if(x=='guige'){
					form.value.guige = row[x];
					disabledForm.value.guige = true;
					continue;
				}
				if(x=='shengchanriqi'){
					form.value.shengchanriqi = row[x];
					disabledForm.value.shengchanriqi = true;
					continue;
				}
				if(x=='youxiaoqi'){
					form.value.youxiaoqi = row[x];
					disabledForm.value.youxiaoqi = true;
					continue;
				}
				if(x=='danjia'){
					form.value.danjia = row[x];
					disabledForm.value.danjia = true;
					continue;
				}
				if(x=='kucun'){
					form.value.kucun = row[x];
					disabledForm.value.kucun = true;
					continue;
				}
				if(x=='xinzengshijian'){
					form.value.xinzengshijian = row[x];
					disabledForm.value.xinzengshijian = true;
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
