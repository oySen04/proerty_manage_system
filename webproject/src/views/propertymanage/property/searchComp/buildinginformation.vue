<template>
  <div>
    <a-form-model>
      <a-row>
        <a-col :span="12">
          <a-form-model-item label="楼宇编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.buildingCode"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="楼宇名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.buildingName"/>
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-model-item label="单元数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.unitCount"/>
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-model-item label="使用面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.usedArea"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="建筑面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.buildArea"/>
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-model-item label="建筑许可证" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.buildPermissionId"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="预售许可证" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.salePermissionId"/>
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row>
        <a-col :span="12">
          <a-form-model-item label="竣工日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.finishDate"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="封顶日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input disabled v-model="buildList.overRoofDate"/>
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row>
        <a-form-model-item label="备注" :labelCol="{span: 2}" :wrapperCol="{span: 21}">
          <a-input type="textarea" disabled v-model="buildList.remark"/>
        </a-form-model-item>
      </a-row>
    </a-form-model>
  </div>
</template>

<script>
export default {
  props: [ 'buildList' ],
  data() {
      return {
          labelCol: { lg: { span: 4 }, sm: { span: 4 } },
          wrapperCol: { lg: { span: 18 }, sm: { span: 18 } }
      }
  }
}
</script>

<style scoped>
.ant-form-item {
    margin-bottom: 8px;
}
</style>
