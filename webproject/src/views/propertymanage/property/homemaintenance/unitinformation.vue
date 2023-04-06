<template>
  <div>
    <a-table :rowSelection="rowSelection" :columns="columns" :dataSource="data">
      <a slot="name" slot-scope="text">{{ text }}</a>
      <template slot="operation" slot-scope="text, record">
        <a href="javascript:;" @click="seemqintain(text, record)">查看房间</a>
      </template>
    </a-table>
  </div>
</template>

<script>
import { selectUnitByBuildingCode } from '@/api/estate'
const QS = require('qs')
// const columns = [
//     {
//         title: '单元编码',
//         dataIndex: 'unitcode',
//         scopedSlots: { customRender: 'unitcode' }
//     },
//     {
//         title: '单元名称',
//         dataIndex: 'unitname'
//     },
//     {
//         title: '开始楼层',
//         dataIndex: 'startfloor'
//     },
//     {
//         title: '结束楼层',
//         dataIndex: 'endfloor'
//     },
//     {
//         title: '开始房号',
//         dataIndex: 'startroomnumber'
//     },
//     {
//         title: '结束房号',
//         dataIndex: 'endroomnumber'
//     },
//     {
//         align: 'center',
//         title: '编辑',
//         dataIndex: 'operation',
//         scopedSlots: { customRender: 'operation' }
//     }
// ]
// const data = [
//     {
//         key: '1',
//         unitcode: 'B-1',
//         unitname: '单元名称1',
//         startfloor: '开始楼层',
//         endfloor: '结束楼层',
//         startroomnumber: '开始房号',
//         endroomnumber: '结束房号'
//     },
//     {
//         key: '2',
//         unitcode: 'B-2',
//         unitname: '单元名称1',
//         startfloor: '开始楼层',
//         endfloor: '结束楼层',
//         startroomnumber: '开始房号',
//         endroomnumber: '结束房号'
//     },
//     {
//         key: '3',
//         unitcode: 'B-3',
//         unitname: '单元名称3',
//         startfloor: '开始楼层',
//         endfloor: '结束楼层',
//         startroomnumber: '开始房号',
//         endroomnumber: '结束房号'
//     }
// ]

export default {
    name: 'Unitinformation',
    props: {
        activeKey: {
            type: Object,
            default: () => {
                return {}
            }
        }
    },
    data() {
        return {
            data: [],
            // 这里是显示的字段，需要根据接口返回的字段进行调整
            columns: [
                {
                    title: '单元编码',
                    dataIndex: 'unitCode',
                    scopedSlots: { customRender: 'unitCode' }
                },
                {
                    title: '单元名称',
                    dataIndex: 'unitName'
                },
                {
                    title: '开始楼层',
                    dataIndex: 'startFloor'
                },
                {
                    title: '结束楼层',
                    dataIndex: 'stopFloor'
                },
                {
                    title: '开始房号',
                    dataIndex: 'startCellId'
                },
                {
                    title: '结束房号',
                    dataIndex: 'stopCellId'
                },
                {
                    align: 'center',
                    title: '编辑',
                    dataIndex: 'operation',
                    scopedSlots: { customRender: 'operation' }
                }
            ]
        }
    },
    computed: {
        rowSelection() {
            // eslint-disable-next-line no-unused-vars
            const { selectedRowKeys } = this
            return {
                onChange: (selectedRowKeys, selectedRows) => {
                    console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows)
                }
            }
        }
    },
    created() {
        this.getData()
    },
    methods: {
        callback(key) {
            console.log(key)
        },
        seemqintain(text, record) {
            this.activeKey.defaultKey = '4'
            // 这个‘ SET_TITLE2 ’名字要和 @store/modules/estate.js 文件中存放的方法名保持一致
            this.$store.commit('SET_TITLE3', {
                unitCode: record.unitCode
            })
            this.$emit('set-unitinformation', record.unitname)
        },
        getData() {
            const sendData = {
                // 获取在第二个页面存储在vuex中的 buildingCode
                buildingCode: this.$store.state.oneEstate.buildingCode
            }
            const parameter = QS.stringify(sendData)
            selectUnitByBuildingCode(parameter).then(res => {
                this.data = res.result
            })
        }
    }
}
</script>
