import request from '@/utils/request'

export function getTopicById(id){
    return request({
        url:'/topics/'+id,
        method:'get',
    })
}
export function getAllTopics(){
    return request({
        url:'/topics',
        method:'get',
    })
}