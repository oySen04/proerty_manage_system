import { axios } from '@/utils/request'

export function insertEstate(parameter) {
  return axios({
    url: '/estate/insertEstate',
    method: 'post',
    data: parameter
  })
}
