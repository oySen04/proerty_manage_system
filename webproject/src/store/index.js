import Vue from 'vue'
import Vuex from 'vuex'

import app from './modules/app'
import user from './modules/user'
// default router permission control
import permission from './modules/permission'
import oneStep from '@/store/modules/oneStep'
import twoStep from '@/store/modules/twoStep'
import threeStep from '@/store/modules/threeStep'
import oneEstate from '@/store/modules/estate'
// dynamic router permission control (Experimental)
// import permission from './modules/async-router'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    app,
    user,
    permission,
    oneStep,
    twoStep,
    threeStep,
    oneEstate
  },
  state: {

  },
  mutations: {

  },
  actions: {

  },
  getters
})
