<template>
  <div>
    <a-modal title="新增" width="70%" v-model="visibleObj.addvisible">
      <a-form-model
        :label-col="labelCol"
        ref="ruleForm"
        :model="form"
        :rules="rules"
        :wrapper-col="wrapperCol"
      >
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="所属公司" prop="affiliatedcompany">
              <a-select v-model="form.affiliatedcompany">
                <a-select-option v-for="(item, index) in form.companyList" :key="index" :value="item.id">{{ item.companyFullName }}</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="业主类型">
              <a-select placeholder="please select your zone" v-model="form.customerType">
                <a-select-option value="正式业主">正式业主</a-select-option>
                <a-select-option value="临时业主">临时业主</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="业主编码" prop="ownercode">
              <a-input v-model="form.ownercode"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="业主姓名" prop="ownername">
              <a-input v-model="form.ownername"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="性别">
              <a-radio-group v-model="form.customerGender">
                <a-radio value="1">男</a-radio>
                <a-radio value="0">女</a-radio>
              </a-radio-group>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="出生日期">
              <a-date-picker v-model="form.customerBirthday" type="date" placeholder="Pick a date" style="width: 100%;" />
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="民族">
              <a-input v-model="form.nation"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="学历">
              <a-select placeholder="please select your zone" v-model="form.education">
                <a-select-option value="专科及专科以下">专科及专科以下</a-select-option>
                <a-select-option value="本科及本科以上">本科及本科以上</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="国籍">
              <a-input v-model="form.nationality"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="籍贯">
              <a-input v-model="form.nativePlace"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="证件类型">
              <a-select placeholder="please select your zone" v-model="form.certificateType">
                <a-select-option value="身份证">身份证</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="证件号码">
              <a-input v-model="form.certificateNumber"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="工作单位">
              <a-input v-model="form.workPlace"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="职务">
              <a-input v-model="form.customerDuty"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="手机号码">
              <a-input v-model="form.phoneNumber"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="邮编">
              <a-input v-model="form.postCode"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="联系地址" :labelCol="{span: 3}" :wrapperCol="{span: 21}">
              <a-input v-model="form.address"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="紧急联系人">
              <a-input v-model="form.urgencyUserName"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="紧急联系人电话">
              <a-input v-model="form.urgencyUserPhone"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="紧急联系人地址" :labelCol="{span: 3}" :wrapperCol="{span: 21}">
              <a-input v-model="form.urgencyUserAddress"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="银行代扣">
              <a-radio-group v-model="form.isBankWithhold">
                <a-radio value="1">是</a-radio>
                <a-radio value="0">否</a-radio>
              </a-radio-group>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="开户行">
              <a-input v-model="form.openBank"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="银行账号">
              <a-input v-model="form.bankAccount"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="所在派出所">
              <a-input v-model="form.police"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="状态">
              <a-select placeholder="please select your zone" v-model="form.customerStatus">
                <a-select-option value="1">启用</a-select-option>
                <a-select-option value="0">禁用</a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="备注">
              <a-input type="textarea" v-model="form.remark"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
      <template slot="footer">
        <a-button type="primary" icon="save" @click="saveandnew">保存并新建</a-button>&nbsp;
        <a-button type="primary" icon="issues-close" @click="saveandclose">保存并关闭</a-button>&nbsp;
        <a-button type="primary" icon="close-square" @click="handleCancel">关闭</a-button>
      </template>
    </a-modal>
  </div>
</template>

<script>
import { selectCompany, insertCustomer } from '@/api/owner'
import $commonFun from '@/utils/common'
const QS = require('qs')
export default {
    data() {
        return {
            labelCol: { lg: { span: 6 }, sm: { span: 6 } },
            wrapperCol: { lg: { span: 18 }, sm: { span: 18 } },
            form: {
                companyList: [],
                affiliatedcompany: '',
                ownercode: '',
                ownername: '',
                customerType: '',
                customerGender: '',
                customerBirthday: undefined,
                nation: '',
                education: '',
                nationality: '',
                nativePlace: '',
                certificateType: '',
                certificateNumber: '',
                workPlace: '',
                customerDuty: '',
                phoneNumber: '',
                postCode: '',
                address: '',
                urgencyUserName: '',
                urgencyUserPhone: '',
                urgencyUserAddress: '',
                isBankWithhold: '',
                openBank: '',
                bankAccount: '',
                police: '',
                customerStatus: '',
                remark: ''
            },
            rules: {
                affiliatedcompany: [{ required: true, message: '所属公司必须填写', trigger: 'change' }],
                ownercode: [{ required: true, message: '业主编码必须填写', trigger: 'blur' }],
                ownername: [{ required: true, message: '业主姓名必须填写', trigger: 'blur' }]
            }
        }
    },
    props: {
        visibleObj: {
            type: Object,
            default: () => {
                return {
                    addvisible: false
                }
            }
        }
    },
    created() {
        this.getData()
    },
    methods: {
        getData() {
            selectCompany().then(res => {
                this.form.companyList = res.result
            })
        },
        showModal() {
            this.visible = true
        },
        saveandnew() {
            this.$refs.ruleForm.validate(valid => {
                if (valid) {
                  const sendData = {
                    customerCode: this.form.ownercode,
                    customerName: this.form.ownername,
                    customerGender: this.form.customerGender,
                    customerType: this.form.customerType,
                    customerBirthday: $commonFun.dateFormat(new Date(this.form.customerBirthday)),
                    nation: this.form.nation,
                    education: this.form.education,
                    nationality: this.form.nationality,
                    nativePlace: this.form.nativePlace,
                    certificateType: this.form.certificateType,
                    certificateNumber: this.form.certificateNumber,
                    workPlace: this.form.workPlace,
                    customerDuty: this.form.customerDuty,
                    phoneNumber: this.form.phoneNumber,
                    postCode: this.form.postCode,
                    address: this.form.address,
                    urgencyUserName: this.form.urgencyUserName,
                    urgencyUserPhone: this.form.urgencyUserPhone,
                    urgencyUserAddress: this.form.urgencyUserAddress,
                    isBankWithhold: this.form.isBankWithhold,
                    openBank: this.form.openBank,
                    bankAccount: this.form.bankAccount,
                    police: this.form.police,
                    customerStatus: this.form.customerStatus,
                    remark: this.form.remark,
                    company: this.form.affiliatedcompany
                  }
                  const parameter = QS.stringify(sendData)
                  insertCustomer(parameter).then(res => {
                    this.$notification.success({
                        message: '提示',
                        duration: 3,
                        description: res.result
                    })
                    console.log(this.$parent)
                    this.visibleObj.addvisible = false
                    this.$parent.getData()
                    this.handleCancel()
                })
                } else {
                    console.log('error submit!!')
                    return false
                }
            })
        },
        saveandclose() {
            this.$refs.ruleForm.validate(valid => {
                if (valid) {
                    this.visibleObj.addvisible = false
                } else {
                    console.log('error submit!!')
                    return false
                }
            })
        },
        handleCancel(e) {
            this.visibleObj.addvisible = false
            this.form = {
                companyList: [],
                affiliatedcompany: '',
                ownercode: '',
                ownername: '',
                customerType: '',
                customerGender: '',
                customerBirthday: undefined,
                nation: '',
                education: '',
                nationality: '',
                nativePlace: '',
                certificateType: '',
                certificateNumber: '',
                workPlace: '',
                customerDuty: '',
                phoneNumber: '',
                postCode: '',
                address: '',
                urgencyUserName: '',
                urgencyUserPhone: '',
                urgencyUserAddress: '',
                isBankWithhold: '',
                openBank: '',
                bankAccount: '',
                police: '',
                customerStatus: '',
                remark: ''
            }
        }
    }
}
</script>

<style lang='less' scoped>
.add {
    width: 600px;
    height: 500px;
}
.ant-form-item {
    margin-top: 5px;
    margin-bottom: 0px;
}
</style>
