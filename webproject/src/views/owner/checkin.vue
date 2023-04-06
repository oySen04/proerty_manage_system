<template>
  <div class="search-house">
    <a-form :form="form" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-row>
        <a-col :span="6">
          <a-form-item label="请选择业主:">
            <a-select placeholder="请选择业主" v-model="homeForm.yezhu" @change="yezhuChange">
              <a-select-option v-for="(item, index) in yezhuList" :key="index" :value="item.customerName" @click="chooseYezhu(item)">{{ item.customerName }}</a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="请选择住宅:">
            <a-select placeholder="请选择住宅" v-model="homeForm.home" @change="homeChange">
              <a-select-option :key="index" :value="item.estateCode" v-for="(item, index) in homeList" @click="chooseHome(item)">
                {{ item.estateName }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="请选择楼宇:">
            <a-select placeholder="请选择楼宇" v-model="homeForm.build" @change="buildChange">
              <a-select-option :key="index" :value="item.buildingCode" v-for="(item, index) in buildList" @click="chooseBuild(item)">
                {{ item.buildingName }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="请选择单元:">
            <a-select placeholder="请选择单元" v-model="homeForm.unit" @change="unitChange">
              <a-select-option :key="index" :value="item.unitCode" v-for="(item, index) in unitList" @click="chooseUnit(item)">
                {{ item.unitName }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="请选择房间:">
            <a-select placeholder="请选择房间" v-model="homeForm.cell">
              <a-select-option :key="index" :value="item.cellCode+''" v-for="(item, index) in cellList" @click="chooseCell(item)">
                {{ item.cellName }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <!-- <a-form-item label="房屋查询">
            <a-cascader
              class="cascader"
              :options="options"
              @change="onChange"
              v-decorator="['cascader']"
              placeholder="Please select"
            />
          </a-form-item> -->
        </a-col>
        <a-col :span="18">
          <div>
            <!-- <h1 class="housinginformation-header">查看 第1栋 楼宇信息</h1> -->
            <a-row>
              <a-col :span="8">
                <a-form-item label="入住日期" :labelCol="{span: 6}" :wrapperCol="{span: 18}">
                  <a-date-picker v-model="homeForm.date"/>
                </a-form-item>
              </a-col>
              <a-col :span="8">
                <a-form-item label="使用状态" :labelCol="{span: 6}" :wrapperCol="{span: 18}">
                  <a-select placeholder="please select your zone" v-model="homeForm.status">
                    <a-select-option value="空闲">空闲</a-select-option>
                    <a-select-option value="自主">自主</a-select-option>
                    <a-select-option value="出租">出租</a-select-option>
                    <a-select-option value="转卖">转卖</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
            </a-row>
            <a-row type="flex" justify="center">
              <a-button @click="save">保存</a-button>
            </a-row>
          </div>
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>

<script>
import { selectCustomer, insertCustomerOrEstate } from '@/api/owner'
import $commonFun from '@/utils/common'
import { selectEstate, selectBuildingByEstate, selectUnitByBuildingCode, selectCell } from '@/api/estate'
const QS = require('qs')
export default {
    data() {
        return {
            labelCol: { lg: { span: 6 }, sm: { span: 6 } },
            wrapperCol: { lg: { span: 16 }, sm: { span: 16 } },
            form: this.$form.createForm(this),
            options: [
                {
                    value: '第一栋',
                    label: '第一栋',
                    children: [
                        {
                            value: '一单元',
                            label: '一单元',
                            children: [
                                {
                                    value: '101',
                                    label: '101'
                                },
                                {
                                    value: '102',
                                    label: '102'
                                }
                            ]
                        },
                        {
                            value: '二单元',
                            label: '二单元',
                            children: [
                                {
                                    value: '101',
                                    label: '101'
                                },
                                {
                                    value: '102',
                                    label: '102'
                                }
                            ]
                        }
                    ]
                },
                {
                    value: '第二栋',
                    label: '第二栋',
                    children: [
                        {
                            value: '一单元',
                            label: '一单元',
                            children: [
                                {
                                    value: '101',
                                    label: '101'
                                },
                                {
                                    value: '102',
                                    label: '102'
                                }
                            ]
                        },
                        {
                            value: '二单元',
                            label: '二单元',
                            children: [
                                {
                                    value: '101',
                                    label: '101'
                                },
                                {
                                    value: '102',
                                    label: '102'
                                }
                            ]
                        }
                    ]
                }
            ],
            yezhuList: [],
            homeList: [],
            homeForm: {
              yezhu: undefined,
              yezhuId: '',
              home: undefined,
              build: undefined,
              unit: undefined,
              cell: undefined,
              cellId: '',
              status: undefined,
              date: ''
            },
            buildList: [],
            unitList: [],
            cellList: []
        }
    },
    created() {
      this.getYezhu()
    },
    methods: {
        // onChange(selectedKeys, info) {
        //     console.log(selectedKeys, info)
        // },
        save() {
          const obj = {
            customerId: this.homeForm.yezhuId,
            customerName: this.homeForm.yezhu,
            cellId: this.homeForm.cellId,
            useStatus: this.homeForm.status,
            liveDate: $commonFun.dateFormat(new Date(this.homeForm.date))
          }
          console.log(obj)
          insertCustomerOrEstate(obj).then(res => {
            if (res.result === '1') {
              this.$message.success('添加成功')
            } else if (res.result === '2') {
              this.$message.errr('添加失败')
            }
          })
            // e.preventDefault()
            // this.form.validateFieldsAndScroll((err, values) => {
            //     if (!err) {
            //         this.$emit('nextStep')
            //         console.log('Received values of form: ', values)
            //     }
            // })
        },
        yezhuChange() {
          this.homeForm.home = undefined
          this.homeForm.build = undefined
          this.homeForm.unit = undefined
          this.homeForm.cell = undefined
          this.homeList = []
          this.buildList = []
          this.unitList = []
          this.cellList = []
        },
        homeChange() {
          this.homeForm.build = undefined
          this.homeForm.unit = undefined
          this.homeForm.cell = undefined
          this.buildList = []
          this.unitList = []
          this.cellList = []
        },
        buildChange() {
          this.homeForm.unit = undefined
          this.homeForm.cell = undefined
          this.unitList = []
          this.cellList = []
        },
        unitChange() {
          this.homeForm.cell = undefined
          this.cellList = []
        },
        getYezhu() {
          selectCustomer({
            message: null
          }).then(res => {
            this.yezhuList = res.result
          })
        },
        chooseYezhu(item) {
          console.log(item.customerName, item.customerCode)
          this.homeList.yezhu = item.customerName
          this.homeForm.yezhuId = item.customerCode
          selectEstate(QS.stringify({ company: item.company })).then(res => {
               this.homeList = res.result
           })
        },
        chooseHome(item) {
          selectBuildingByEstate(QS.stringify({ estateCode: item.estateCode })).then(res => {
            this.buildList = res.result
          })
        },
        chooseBuild(item) {
          selectUnitByBuildingCode(QS.stringify({ buildingCode: item.buildingCode })).then(res => {
            this.unitList = res.result
          })
        },
        chooseUnit(item) {
          selectCell(QS.stringify({ unitCode: item.unitCode })).then(res => {
            this.cellList = res.result
          })
        },
        chooseCell(item) {
          this.homeForm.cellId = item.id
        }
    }
}
</script>

<style scoped lang="less">
.search-house {
    background-color: #fff;
    padding: 20px;
    min-height: 500px;
}
</style>
