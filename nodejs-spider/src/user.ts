import FormData from 'form-data';
import { UserVO } from './models/user';
import { request } from './util';
export function getUserVOById(id: number):Promise<UserVO|null> {
  type user_infoType = {
    id: number;
    user_name: string;
    avatar_url: string;
    cover_url: string;
    level: number;
    user_title: string;
    verify_type: number;
    verify_info: unknown;
    user_remark: unknown;
    level_type: number;
    medal_list: unknown[];
    _max_can_equip_count: number;
  };
  const formData = new FormData();
  formData.append('u', String(id));
  formData.append('username', '');
  return request({
    method: 'post',
    url: '/other/user.jhtml',
    headers: {
      ...formData.getHeaders(),
    },
    data: formData,
  }).then(res => {
    const { data, retcode }: { data: any; retcode: number } = res.data as any;
    if (200 !== retcode||id!==data.user_info.id) {
      return null;
    }
    const {
      user_name: name,
      avatar_url: avatar,
      level,
      user_title: title,
    } = data.user_info as user_infoType;
    return {
      id,
      name,
      avatar,
      level,
      title,
    };
  });
}
export function getUserIdByName(name:string):Promise<number|null>{
  const formData = new FormData();
  formData.append('username', name);
  return request({
    method: 'post',
    url: '/other/user.jhtml',
    headers: {
      ...formData.getHeaders(),
    },
    data: formData,
  }).then(res => {
    const { data, retcode }: { data: any; retcode: number } = res.data as any;
    if (200 !== retcode||0===data.user_info.id) {
      return null;
    }
    return data.user_info.id
  });
}