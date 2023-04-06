<template>
  <div>
    <a-table :rowSelection="rowSelection" :columns="columns" :dataSource="data">
      <a slot="name" slot-scope="text">{{ text }}</a>
      <template slot="operation" slot-scope="text, record">
        <a href="javascript:;" @click="seemqintain(text, record)">查看单元</a>
      </template>
    </a-table>
  </div>
</template>

<script>
// const columns = [
//     {
//         title: '楼宇编码',
//         dataIndex: 'buildingcode',
//         scopedSlots: { customRender: 'buildingcode' }
//     },
//     {
//         title: '楼宇名称',
//         dataIndex: 'buildingname'
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
//         title: '单元数量',
//         dataIndex: 'unitcount'
//     },
//     {
//         title: '预售许可证号',
//         dataIndex: 'licensenumber'
//     },
//     {
//         title: '竣工日期',
//         dataIndex: 'completiondate'
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
//         buildingcode: 'B-1',
//         buildingname: '楼宇名称1',
//         constructionarea: '建筑面积',
//         usagearea: '使用面积',
//         unitcount: '单元数量',
//         licensenumber: '预售许可证号',
//         completiondate: '竣工日期'
//     },
//     {
//         key: '2',
//         buildingcode: 'B-2',
//         buildingname: '楼宇名称2',
//         constructionarea: '建筑面积',
//         usagearea: '使用面积',
//         unitcount: '单元数量',
//         licensenumber: '预售许可证号',
//         completiondate: '竣工日期'
//     },
//     {
//         key: '3',
//         buildingcode: 'B-3',
//         buildingname: '楼宇名称3',
//         constructionarea: '建筑面积',
//         usagearea: '使用面积',
//         unitcount: '单元数量',
//         licensenumber: '预售许可证号',
//         completiondate: '竣工日期'
//     }
// ]
import { selectBuildingByEstateCode } from '@/api/estate'
const QS = require('qs')
export default {
    namd: 'buildinginformation',
    props: {
        activeKey: {
            type: Object,
            default: () => {
                return {}
            }
            // default: () => {
            //     return {
            //         defaultKey: '1'
            //     }
            // }
        }
    },
    data() {
        return {
            data: [],
            columns: [
                {
                    title: '楼宇编码',
                    dataIndex: 'buildingCode',
                    scopedSlots: { customRender: 'buildingCode' }
                },
                {
                    title: '楼宇名称',
                    dataIndex: 'buildingName'
                },
                {
                    title: '建筑面积',
                    dataIndex: 'buildArea'
                },
                {
                    title: '使用面积',
                    dataIndex: 'usedArea'
                },
                {
                    title: '单元数量',
                    dataIndex: 'unitCount'
                },
                {
                    title: '预售许可证号',
                    dataIndex: 'salePermissionId'
                },
                {
                    title: '竣工日期',
                    dataIndex: 'finishDate'
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
            this.activeKey.defaultKey = '3'
            // 这个‘ SET_TITLE2 ’名字要和 @store/modules/estate.js 文件中存放的方法名保持一致
            this.$store.commit('SET_TITLE2', {
                buildingCode: record.buildingCode
            })
            this.$emit('set-buildinginformation', record.buildingname)
        },
        getData() {
            console.log(this.$store.state)
            const sendData = {
                estateCode: this.$store.state.oneEstate.estateCode
            }
            const parameter = QS.stringify(sendData)
            selectBuildingByEstateCode(parameter).then(res => {
                this.data = res.result
                this.data.map(item => {
                    item.finishDate = item.finishDate.substr(0, 10)
                })
            })
        }
    }
}
</script>
