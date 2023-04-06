<template>
  <div>
    <a-row>
      <a-form-model :model="form" layout="inline">
        <a-form-model-item label="房间编码">
          <a-input placeholder="请输入房间编码" v-model="form.housecode" />
        </a-form-model-item>
        <a-form-model-item label="快速检索">
          <a-select v-model="form.region" placeholder="业主编码" class="select">
            <a-select-option value="customer_code">业主编码</a-select-option>
            <a-select-option value="customer_name">业主姓名</a-select-option>
            <a-select-option value="certificate_number">证件号码</a-select-option>
            <a-select-option value="phone_number">手机号码</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item>
          <a-input placeholder="请输入具体信息" v-model="form.retrievingcontent"/>
          <a-icon type="search" class="retrievingcontent-icon" @click="changeData"/>
        </a-form-model-item>
        <a-form-model-item label="快速检索">
          <a-select v-model="form.ownerType" placeholder="全部业主" class="select" @change="selectType">
            <a-select-option value="">全部业主</a-select-option>
            <a-select-option value="正式业主">正式业主</a-select-option>
            <a-select-option value="临时业主">临时业主</a-select-option>
          </a-select>
        </a-form-model-item>
      </a-form-model>
    </a-row>
    <a-row class="setting">
      <a-button type="primary" icon="plus-circle" @click="showAddModal">新增</a-button>&nbsp;
      <a-button type="primary" icon="caret-right" @click="handle('1')">启用</a-button>&nbsp;
      <a-button type="primary" icon="pause" @click="handle('0')">禁用</a-button>&nbsp;
      <a-button type="primary" icon="file" @click="showImportExcelModal">导入</a-button>
    </a-row>
    <a-row>
      <a-table
        :columns="columns"
        :dataSource="data"
        @change="paginationChange"
        position="bottom"
        :pagination="pagination"
        :rowSelection="rowSelection"
      >
        <a slot="operating">
          <!-- slot-scope="text, record" -->
          <a>家庭成员(2)</a>
          <a-divider type="vertical" />
          <a>房产登记(2)</a>
        </a>
      </a-table>
    </a-row>
    <add :visibleObj="visibleObj"></add>
    <importexcel :visibleObj="visibleObj" :companyList="companyList"></importexcel>
  </div>
</template>

<script>
import add from './information-dialog/add'
import importexcel from './information-dialog/importexcel'
import { selectCustomer, selectCompany, selectCustomerByColumnAndValue, selectByCustomerByCustomerType, UpdateCustomerStatusByCustomerCode } from '@/api/owner'
const QS = require('qs')
// import _ from 'lodash'
// import mockjs2 from 'mockjs2'
const columns = [
    {
        align: 'center',
        title: '编号',
        dataIndex: 'id',
        defaultSortOrder: 'descend',
        sorter: (a, b) => a.key - b.key
    },
    {
        align: 'center',
        title: '所属公司',
        dataIndex: 'company'
    },
    {
        align: 'center',
        title: '业主编码',
        dataIndex: 'customerCode',
        defaultSortOrder: 'descend',
        sorter: (a, b) => a.ownercode - b.ownercode
    },
    {
        align: 'center',
        title: '姓名',
        dataIndex: 'customerName'
    },
    {
        align: 'center',
        title: '性别',
        dataIndex: 'customerGender'
    },
    {
        align: 'center',
        title: '出生日期',
        dataIndex: 'customerBirthday'
    },
    {
        align: 'center',
        title: '证件号码',
        dataIndex: 'certificateNumber'
    },
    {
        align: 'center',
        title: '手机号码',
        dataIndex: 'phoneNumber'
    },
    {
        align: 'center',
        title: '银行代扣',
        dataIndex: 'isBankWithhold'
    },
    {
        align: 'center',
        title: '状态',
        dataIndex: 'customerStatus'
    },
    {
        align: 'center',
        title: '操作',
        dataIndex: 'operating',
        scopedSlots: { customRender: 'operating' }
    }
]

// const data = []
// for (let i = 0; i < 200; i++) {
//     data.push({
//         key: i,
//         affiliatedcompany: '海淀子公司',
//         ownercode: _.random(100000, 999999),
//         name: mockjs2.Random.cname(),
//         sex: '男',
//         dateofbirth: mockjs2.Random.date('yyyy-MM-dd'),
//         idnumber: mockjs2.Random.id(),
//         phonenumber: '13811223344',
//         bankwithholding: mockjs2.Random.boolean() === true ? '是' : '否',
//         status: mockjs2.Random.boolean() === true ? '正常' : '停用',
//         operating: 'New York No. 1 Lake Park'
//     })
// }

export default {
    data() {
        return {
            visibleObj: {
                addvisible: false,
                importexcelvisible: false
            },
            form: {
                housecode: '',
                region: undefined,
                retrievingcontent: undefined,
                ownerType: undefined
            },
            pagination: {
                defaultPageSize: 10,
                showTotal: total => `共 ${total} 条数据`,
                showQuickJumper: true,
                showSizeChanger: true,
                pageSizeOptions: ['5', '10', '15', '20'],
                onShowSizeChange: (current, pageSize) => (this.pageSize = pageSize)
            },
            data: [],
            columns,
            selectedRows: [],
            codes: [],
            companyList: []
        }
    },
    computed: {
        rowSelection() {
            return {
                onChange: (selectedRowKeys, selectedRows) => {
                    console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows)
                },
                onSelect: (record, selected, selectedRows) => {
                    console.log(record, selected, selectedRows)
                    // eslint-disable-next-line vue/no-side-effects-in-computed-properties
                    this.selectedRows = selectedRows
                },
                onSelectAll: (selected, selectedRows, changeRows) => {
                    // eslint-disable-next-line vue/no-side-effects-in-computed-properties
                    this.selectedRows = selectedRows
                    console.log(selected, selectedRows, changeRows)
                }
            }
        }
    },
    created() {
        this.getData()
        this.getCompany()
    },
    methods: {
        getCompany() {
            selectCompany().then(res => {
                this.companyList = res.result
                console.log(this.companyList, '------', res.result)
            })
        },
        getData() {
            this.data = []
            // 向后端传递数据，传递的属性如下
            const obj = {
                column: this.form.region || '',
                value: this.form.retrievingcontent || '',
                customerType: this.form.ownerType
            }
            // 把声明的obj填入到接口参数中
            selectCustomer(obj).then(res => {
                console.log(res)
                this.data = res.result
                this.data.map(item => {
                    console.log(item.customerStatus === '1')
                    if (item.customerStatus === '1') {
                         item.customerStatus = '启用'
                     } else if (item.customerStatus === '0') {
                         item.customerStatus = '禁用'
                     }
                     if (item.isBankWithhold === '1') {
                         item.isBankWithhold = '是'
                     } else if (item.isBankWithhold === '0') {
                         item.isBankWithhold = '否'
                     }
                     if (item.customerGender === '1') {
                         item.customerGender = '男'
                     } else if (item.customerGender === '0') {
                         item.customerGender = '女'
                     }
                })
                // 用户在查询之后，清空筛选项内容 注意：要与上方data中的form属性保持值一致
                this.form.region = undefined
                this.form.retrievingcontent = undefined
                this.form.ownerType = undefined
            })
        },
        changeData() {
            this.data = []
            const sendData = {
                column: this.form.region || '',
                value: this.form.retrievingcontent || ''
            }
            const parameter = QS.stringify(sendData)
            selectCustomerByColumnAndValue(parameter).then(res => {
                console.log(res)
                this.data = res.result
                 this.data.map(item => {
                     if (item.customerStatus === '1') {
                         item.customerStatus = '启用'
                     } else if (item.customerStatus === '0') {
                         item.customerStatus = '禁用'
                     }
                     if (item.isBankWithhold === '1') {
                         item.isBankWithhold = '是'
                     } else if (item.isBankWithhold === '0') {
                         item.isBankWithhold = '否'
                     }
                })
            })
        },
        selectType() {
            this.data = []
            const sendData = {
                customerType: this.form.ownerType || ''
            }
            const parameter = QS.stringify(sendData)
            selectByCustomerByCustomerType(parameter).then(res => {
                console.log(res)
                this.data = res.result
            })
        },
        paginationChange(pagination, filters, sorter) {
            console.log('params', pagination, filters, sorter)
        },
        showAddModal() {
            this.visibleObj.addvisible = true
        },
        showImportExcelModal() {
            this.visibleObj.importexcelvisible = true
        },
        start() {
            console.log(this.selectedRows)
            this.$confirm({
                title: '确认对话框',
                content: `确认要处理选中的${this.selectedRows.length}项记录吗？`,
                onOk() {
                    return new Promise((resolve, reject) => {
                        setTimeout(Math.random() > 0.5 ? resolve : reject, 1000)
                    }).catch(() => console.log('Oops errors!'))
                },
                onCancel() {}
            })
        },
        disable() {
            this.$confirm({
                title: '确认对话框',
                content: `确认要处理选中的${this.selectedRows.length}项记录吗？`,
                onOk() {
                    return new Promise((resolve, reject) => {
                        setTimeout(Math.random() > 0.5 ? resolve : reject, 1000)
                    }).catch(() => console.log('Oops errors!'))
                },
                onCancel() {}
            })
        },
        handle(type) {
            console.log(this.selectedRows, '-------')
            this.selectedRows.map(item => {
                console.log(item.customerCode)
                this.codes.push(item.customerCode)
            })
            const sendData = {
                customerCodes: this.codes.join('|').toString(),
                status: type
            }
            console.log(sendData)
            // const parameter = QS.stringify(sendData)
            UpdateCustomerStatusByCustomerCode(sendData).then(res => {
                // if (res.result === 1) {
                this.getData()
                this.$notification.success({
                    message: '提示',
                    duration: 3,
                    description: res.result
                })
                this.codes = []
                this.selectedRows = []
                // }
            })
        }
    },
    components: {
        add,
        importexcel
    }
}
</script>

<style lang="less" scoped>
.select {
    width: 110px;
}
.retrievingcontent-icon {
    position: absolute;
    top: 50%;
    right: 10px;
    transform: translateY(-50%);
}
/deep/ input,
/deep/ .ant-select-selection {
    border-radius: 4px;
}
.setting {
    margin: 10px 0;
}
</style>
