import { axios } from '@/utils/request'

// export function selectCustomer(parameter) {
//     return axios({
//         url: '/zhCustomer/selectCustomer',
//         method: 'post',
//         data: parameter
//     })
// }
export function selectCustomer() {
    return axios({
        url: '/zhCustomer/selectCustomer',
        method: 'get'
       // data: parameter
    })
}

export function insertCustomer(parameter) {
    return axios({
        url: '/zhCustomer/insertCustomer',
        method: 'post',
        data: parameter
    })
}

export function selectCompany(parameter) {
    return axios({
        url: '/estate/selectCompany',
        method: 'post',
        data: parameter
    })
}

export function selectCustomerByColumnAndValue(parameter) {
    return axios({
        url: '/zhCustomer/selectCustomerByColumnAndValue',
        method: 'post',
        data: parameter
    })
}

export function selectByCustomerByCustomerType(parameter) {
    return axios({
        url: '/zhCustomer/selectByCustomerByCustomerType',
        method: 'post',
        data: parameter
    })
}

export function UpdateCustomerStatusByCustomerCode(parameter) {
    return axios({
        url: '/zhCustomer/UpdateCustomerStatusByCustomerCode',
        method: 'post',
        data: parameter
    })
}

export function insertCustomerOrEstate(parameter) {
    return axios({
        url: '/zhCustomer/insertCustomerOrEstate',
        method: 'post',
        data: parameter
    })
}
