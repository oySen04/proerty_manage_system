<template>
  <div class="search-house">
    <a-row>
      <a-col :span="4">
        <a-form :form="form" :label-col="labelCol" :wrapper-col="wrapperCol">
          <a-form-item label="请选择住宅:">
            <a-select placeholder="please select your zone" v-model="estateName" @change="changeBuilding">
              <a-select-option v-for="(item, index) in zoneList" :key="index" :value="item.estateCode">{{ item.estateName }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-form>
      </a-col>
      <a-col :span="20">
        <a-row>
          <a-row class="title" type="flex" justify="start">
            <a-form-item label="查看楼宇信息">
              <a-select placeholder="please select your zone" v-model="buildingName" @change="onChange" style="width:200px">
                <a-select-option v-for="(item, index) in options" :key="index" :value="item.buildingCode">{{ item.buildingName }}</a-select-option>
              </a-select>
            </a-form-item>
            <!-- <h1 class="housinginformation-header">查看 第1栋 楼宇信息</h1>
            <a-cascader
              class="cascader"
              :options="options"
              @change="onChange"
              placeholder="Please select"
            /> -->
          </a-row>
          <buildinginformation :buildList="buildList"></buildinginformation>
        </a-row>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import housinginformation from './searchComp/housinginformation'
import buildinginformation from './searchComp/buildinginformation'
import unitinformation from './searchComp/unitinformation'
import { selectAllEstate, selectBuildingByEstate, selectBuildingByEstateCodeAndBuildingCode } from '@/api/estate'
const QS = require('qs')
export default {
    data() {
        return {
            labelCol: { span: 10 },
            wrapperCol: { span: 10 },
            form: this.$form.createForm(this),
            zoneList: [],
            estateName: '',
            buildingName: '',
            options: [],
            buildingCode: '',
            buildList: []
        }
    },
    created() {
        this.getData()
    },
    methods: {
        onChange(selectedKeys, info) {
            console.log(selectedKeys, info)
            selectBuildingByEstateCodeAndBuildingCode(QS.stringify({ buildingCode: this.buildingName, estateCode: this.estateName })).then(res => {
                this.buildList = res.result
            })
        },
        changeBuilding() {
            selectBuildingByEstate(QS.stringify({ estateCode: this.estateName })).then(res => {
                this.options = res.result
            })
        },
        getData() {
            selectAllEstate().then(res => {
                this.zoneList = res.result
            })
        }
    },
    components: {
        housinginformation,
        buildinginformation,
        unitinformation
    }
}
</script>

<style scoped lang="less">
.search-house {
    background-color: #fff;
    padding: 20px 0;
    min-height: 500px;
}
.title {
    margin-bottom: 20px;
    .housinginformation-header {
        margin-bottom: 0;
        line-height: 32px;
        margin-left: 40px;
    }
    .cascader {
        margin-left: 20px;
        width: 330px;
    }
}
</style>
