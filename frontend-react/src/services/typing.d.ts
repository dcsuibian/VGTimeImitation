type ResponseWrapper<T> = {
  data: T;
  message: string;
  code: number;
  timestamp: number;
};

declare namespace API {
  type Topic = {
    id?: number;
    content?: string;
    title?: string;
    cover?: string;
  };
  type User = {
    id?: number;
    name?: string;
    password?: string;
    gender?: string;
    avatar?: string;
    level?: number;
    title?: string;
    phoneNumber?: string;
    email?: string;
  };
}
