<template>
    <div>
        <div class="center_view" v-if="btnAuth('menu','菜单管理')">
            <div style="text-align: left;width: 100%;margin-bottom: 20px">
                <el-button type="primary" style="width: 100px" @click="save">保存修改</el-button>
            </div>
            <el-tabs type="border-card" v-model="role_index" @tab-change="tabClick">
                <template v-for="(role,r_index) in menus" :key="role.roleName">
                    <el-tab-pane :label="role.roleName" :name="r_index">
                        <div v-if="r_index==role_index">
                            <el-collapse v-model="collapse_default">
                                <el-collapse-item v-for="(table,t_index) in role.backMenu" :name="table.menu">
                                    <template #title>
                                        <div style="width: 100%;display: flex;justify-content: space-between;align-items: center">
                                            <div style="font-weight: 700;font-size: 16px">{{table.menu}}</div>
                                            <div style="padding-right: 30px">
                                                <el-button size="small" @click.stop="moveUp(role.backMenu,t_index)">
                                                    上移
                                                </el-button>
                                                <el-button size="small" type="primary" @click.stop="changeName(table,role.backMenu)">
                                                    修改菜单名
                                                </el-button>
                                            </div>
                                        </div>
                                    </template>
                                    <el-table
                                        :data="table.child"
                                        :show-header="false"
                                        style="width: 100%;background: #edeef0;border: 1px solid #ddd">
                                        <el-table-column
                                            prop="menu"
                                            label="菜单名"
                                            width="180">
                                        </el-table-column>
                                        <el-table-column label="权限">
                                            <template #default="scope">
                                                <el-checkbox-group v-model="scope.row.buttons">
                                                    <el-checkbox v-for="item in scope.row.allButtons" :label="item" :value="item" />
                                                </el-checkbox-group>
                                            </template>
                                        </el-table-column>
                                        <el-table-column label="操作">
                                            <template #default="scope">
                                                <el-button size="small" @click="moveUp(table.child,scope.$index)">
                                                    上移
                                                </el-button>
                                                <el-button size="small" type="primary" @click="changeName(scope.row,table.child)">
                                                    修改菜单名
                                                </el-button>
                                                <el-button size="small"
                                                           type="warning"
                                                           @click="toMove(t_index,scope.$index)">
                                                    修改父级菜单
                                                </el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-collapse-item>
                            </el-collapse>
                        </div>
                    </el-tab-pane>
                </template>
            </el-tabs>
        </div>
        <el-dialog
            title="修改父级菜单"
            v-model="dialogVisible"
            width="30%">
            <div style="display: flex;justify-content: center;align-items: center;margin-bottom: 20px">
                <div>父级菜单：</div>
                <el-select v-model="n_first_index" placeholder="请选择父级菜单" style="width: 200px">
                    <el-option
                        v-for="(item,index) in menus[role_index].backMenu"
                        :key="item.menu"
                        :label="item.menu"
                        :value="index">
                    </el-option>
                </el-select>
            </div>
            <div style="width: 100%;text-align: center">
                <el-button type="primary" @click="moveTo">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script setup>
import {getCurrentInstance,ref} from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'

const context = getCurrentInstance()?.appContext.config.globalProperties;

//权限验证
const btnAuth = (e,a)=>{
    return context?.$toolUtil.isAuth(e,a)
}

const menus = ref([])
const role_index = ref(0)   //当前选中的角色index
const first_index = ref(0) //当前选中的一级菜单index
const collapse_default = ref([])    //展开的collapse
const getMenus = ()=>{
    context.$http.get('menu/lists').then(res=>{
        menus.value = JSON.parse(res.data.data[0].menujson)
        collapse_default.value = menus.value[role_index.value].backMenu.map(item=>item.menu)
    })
}
getMenus()

const tabClick = ()=>{
    collapse_default.value = menus.value[role_index.value].backMenu.map(item=>item.menu)
}

const save = ()=>{
    context.$http.post('menu/update',{
        id:1,
        menujson:JSON.stringify(menus.value)
    }).then(res=>{
        if(res.data.code==0){
            ElMessage.success('保存成功，重新登录后生效')
        }
    })
}

const dialogVisible = ref(false)
const n_first_index = ref(null)     //选中的父级菜单
const o_index = ref(0)      //需要移动的二级菜单的index
const toMove = (t_index,index)=>{
    first_index.value = t_index
    n_first_index.value = t_index
    o_index.value = index
    dialogVisible.value = true
}
const moveTo = ()=>{
    let items = menus.value[role_index.value].backMenu[first_index.value].child.splice(o_index.value,1)
    menus.value[role_index.value].backMenu[n_first_index.value].child.push(...items)
    dialogVisible.value = false
}
const changeName = (row,arr)=>{
    ElMessageBox.prompt('请输入新菜单名','修改菜单名',{
        inputValidator:(value)=>{
            if(!value || !value.trim())return '请输入菜单名'
            if(arr.find(item=>item.menu==value.trim())){
                return '该菜单名已存在'
            }
        },
    }).then(({value})=>{
        row.menu = value.trim()
    }).catch(()=>{})
}
const moveUp = (arr,index)=>{
    if(index==0)return
    arr.splice(index-1,0, ...arr.splice(index,1))
    arr = JSON.parse(JSON.stringify(arr))
}

</script>

<style scoped lang="scss">

</style>