import request from '@/utils/request'
export function getAllHomePageData(){
    return request({
        url:'/homepage',
        method:'get',
    })
}