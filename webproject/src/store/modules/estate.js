const oneEstate = {
    state: {
        estateName: '',
        estateCode: '',
        buildingCode: '',
        unitCode: ''
    },
    mutations: {
        // 第一个页面存储的字段
        SET_TITLE(state, payload) {
            console.log('payload' + payload.estateCode)
            state.estateName = payload.estateName
            state.estateCode = payload.estateCode
            state.buildingCode = payload.buildingCode
        },
        // 第二个页面存储的字段
        SET_TITLE2(state, payload) {
            state.buildingCode = payload.buildingCode
        },
        // 第三个页面存储的字段
        SET_TITLE3(state, payload) {
            state.unitCode = payload.unitCode
        }
    },
    actions: {

    },
    getters: {

    }
}
export default oneEstate
