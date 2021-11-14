import { request } from 'umi';
// export async function getAllTopics() {
//   return request<ResponseWrapper<API.Topic[]>>('/api/topics', {
//     method: 'GET',
//   });
// }
export async function getTopicById(id: number) {
  return request<ResponseWrapper<API.Topic>>(`/api/topics/${id}`, {
    method: 'GET',
  });
}
export async function getHomePage() {
  return request<ResponseWrapper<API.HomePage>>(`/api/home-page`, {
    method: 'GET',
  });
}
