import request from '@/utils/request'

export function getTopic(id){
    return request({
        url:'/topic/'+id,
        method:'get',
    })
}