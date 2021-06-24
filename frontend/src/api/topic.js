import request from '@/utils/request'

export function getTopic(id){
    return request({
        url:'/topics/'+id,
        method:'get',
    })
}