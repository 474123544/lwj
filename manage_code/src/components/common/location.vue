<template>
    <div>
        <el-dialog v-model="mapVisible" :title="'选择地点'" width="70%" destroy-on-close>
            <div class="aMapMain">
                <div class="aMap">
                    <el-amap ref="mapRef" :min-zoom="8" :max-zoom="22" :center="center" :zoom="zoom"
                             @click="clickMap" @init="initMap">
                        <el-amap-search-box @select="selectPoi" @choose="selectPoi"/>
                        <el-amap-marker :position="componentMarker.position" :content="componentMarker.content" />
                        <el-amap-control-geolocation @complete="getLocation" :extensions="extensions" />
                    </el-amap>
                </div>
                <div class="aMapAddress">
                    <p>
                        <span>坐标：</span>
                        <span>{{ thePosition.lng }}</span>，
                        <span>{{ thePosition.lat }}</span>
                    </p>
                    <p>
                        <span>地址：</span>
                        <span>{{ thePosition.fulladdress }}</span>
                    </p>
                </div>
            </div>
            <template #footer>
				<span class="formModel_btn_box">
					<el-button class="formModel_confirm" @click="chooseMapClick">确定位置</el-button>
				</span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import {
    ref,
    defineProps,
    defineEmits
} from 'vue'
import {
    ElMessage,
} from 'element-plus'
const props = defineProps({
    position: {     // 父组件传进来的默认坐标
        type: Object,
        default: {
            longitude:113.887902,
            latitude:22.554732,
            fulladdress:''
        }
    },
})
const mapVisible = ref(false)
const mapShow = () => {
    initMapInfo()
    mapVisible.value = true
}
//声明父级调用
defineExpose({
    mapShow
})
const emit = defineEmits(['mapData'])
const zoom = ref(16)
const center = ref([])
const componentMarker = ref({
    position: [props.position.longitude, props.position.latitude],
    visible: true,
    draggable: false
})
const thePosition = ref({})

/** 接受父组件传值进行赋值 初始化地图数据*/
const initMapInfo = () => {
    //高德组件用的是缩写，所以需要重新建个对象赋值
    thePosition.value = props.position
    thePosition.value.lng = props.position.longitude||113.887902
    thePosition.value.lat = props.position.latitude||22.554732
    //视图中心定位在坐标位置
    center.value = [thePosition.value.lng,thePosition.value.lat]
    //标记点定位在坐标位置
    componentMarker.value.position = center.value
}

/** 地图初始化完成钩子*/
const initMap = (e) => {}
//获取当前位置
const getLocation = (e) => {
    let position = [e.position.lng, e.position.lat]
    componentMarker.value.position = position
    center.value = position
    getAddress(position)
}
//点击描点
const clickMap = (e) => {
    let position = [e.lnglat.lng, e.lnglat.lat]
    center.value = position
    componentMarker.value.position = position
    getAddress(position)
}
/** 选取搜索结果 */
const selectPoi = (e) => {
    let poi = e.poi
    if (poi.address.length > 0 && poi.location != undefined &&
        poi.location != null && poi.location != '') {
        let position = [e.poi.location.lng, e.poi.location.lat]
        center.value = position
        componentMarker.value.position = position
        getAddress(position)
    }else{
        ElMessage.warning("未获取到该地点定位信息")
    }
}
//获取详细地址
const getAddress = (position) => {
    var geocoder = new AMap.Geocoder({
        radius: 1000,
        extensions: 'all'
    })
    geocoder.getAddress(position, function(status, result) {
        thePosition.value = {
            lng: position[0], //经度
            lat: position[1], //纬度
            fulladdress: '' //详细地址
        }
        let reg = /.+?(省|市|自治区|自治州|盟|旗|县|区)/g // 截取地图地址
        let detailedAddress = result.regeocode.formattedAddress
        let districtList = detailedAddress.match(reg)
        if (districtList.length < 3) {
            thePosition.value.city = districtList[0]
            thePosition.value.zone = districtList[1]
        } else {
            thePosition.value.city = districtList[1]
            thePosition.value.zone = districtList[2]
        }
        thePosition.value.province = districtList[0]
        thePosition.value.fulladdress = detailedAddress
    })
}
const chooseMapClick=()=>{
    emit('mapData', thePosition.value) // 传值到父组件
    mapVisible.value = false
}

</script>

<style lang="scss">
.amap-sug-result {
    z-index: 2099;
}

.amap-copyright {
    height: 24px;
}

.el-vue-search-box-container,
.el-vue-search-box-container input {
    height: 29px;
    border-radius: 8px;
}

.aMapMain {
    width: 100%;

    .aMap {
        width: 100%;
        height: 500px;
    }

    .aMapAddress {
        text-align: left;

        p {
            margin-top: 5px;
            margin-bottom: 0;
        }
    }
}

.el-vue-search-box-container {
    border: 1px solid #e2e2e2;
    height: 36px;
    box-shadow: none;
}
</style>
