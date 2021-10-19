interface UserPO {
  id: number | null;
  name: string | null;
  password: string | null;
  gender: string | null;
  avatar: string | null;
  level: number | null;
  title: string | null;
  phoneNumber: string | null;
  email: string | null;
}
interface UserVO {
  id: number | null;
  name: string | null;
  avatar: string | null;
  level: number | null;
  title: string | null;
}
export {UserPO,UserVO}
