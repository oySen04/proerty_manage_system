<template>
  <div>
    <a-table :rowSelection="rowSelection" :columns="columns" :dataSource="data">
      <a slot="name" slot-scope="text">{{ text }}</a>
    </a-table>
  </div>
</template>

<script>
// const columns = [
//     {
//         title: '房间编码',
//         dataIndex: 'roomcode',
//         scopedSlots: { customRender: 'roomcode' }
//     },
//     {
//         title: '房间名称',
//         dataIndex: 'roomname'
//     },
//     {
//         title: '规格',
//         dataIndex: 'format'
//     },
//     {
//         title: '朝向',
//         dataIndex: 'face'
//     },
//     {
//         title: '功能',
//         dataIndex: 'function'
//     },
//     {
//         title: '装修',
//         dataIndex: 'decoration'
//     },
//     {
//         title: '类型',
//         dataIndex: 'type'
//     },
//     {
//         title: '建筑面积',
//         dataIndex: 'constructionarea'
//     },
//     {
//         title: '使用面积',
//         dataIndex: 'usagearea'
//     },
//     {
//         title: '已售',
//         dataIndex: 'sold'
//     },
//     {
//         title: '已租',
//         dataIndex: 'rented'
//     }
// ]
// const data = [
//     {
//         key: '1',
//         roomcode: '房间编码',
//         roomname: '房间名称',
//         format: '规格',
//         face: '朝向',
//         function: '功能',
//         decoration: '装修',
//         type: '类型',
//         constructionarea: '建筑面积',
//         usagearea: '使用面积',
//         sold: '已售',
//         rented: '已租'
//     }
// ]
import { selectCell } from '@/api/estate'
const QS = require('qs')
export default {
    name: 'Roominformation',
    props: {
        activeKey: {
            type: Object,
            default: () => {
                return {
                    defaultKey: '1'
                }
            }
        }
    },
    data() {
        return {
            data: [],
            // 这里是显示的字段，需要根据接口返回的字段进行调整
            columns: [
                {
                    title: '房间编码',
                    dataIndex: 'cellCode',
                    scopedSlots: { customRender: 'cellCode' }
                },
                {
                    title: '房间名称',
                    dataIndex: 'cellName'
                },
                {
                    title: '功能',
                    dataIndex: 'cellFunction'
                },
                {
                    title: '建筑面积',
                    dataIndex: 'cellBuildArea'
                },
                {
                    title: '使用面积',
                    dataIndex: 'cellUsedArea'
                },
                {
                    title: '已售',
                    dataIndex: 'isSale'
                },
                {
                    title: '已租',
                    dataIndex: 'isRent'
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
        getData() {
            const sendData = {
                // 获取在第3个页面存储在vuex中的 unitCode
                unitCode: this.$store.state.oneEstate.unitCode
            }
            const parameter = QS.stringify(sendData)
            selectCell(parameter).then(res => {
                this.data = res.result
            })
        }
    }
}
</script>
