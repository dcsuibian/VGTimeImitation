type ResponseWrapper<T> = {
  data: T | null;
  message: string;
  code: number;
  timestamp: number;
};

declare namespace API {
  type Topic = {
    id: number;
    abstract: string;
    content: string;
    title: string;
    cover: string;
    time: number;
    author: {
      name: string;
    };
    editor: {
      name: string;
    };
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
  type HomePage = {
    hotNews: {
      id: number;
      abstract: string;
      time: number;
      title: string;
      cover: string;
      author: {
        name: string;
      };
    }[];
    news: {
      id: number;
      abstract: string;
      time: number;
      title: string;
      cover: string;
      author: {
        name: string;
      };
    }[];
    guides: {
      id: number;
      abstract: string;
      time: number;
      title: string;
      cover: string;
      author: {
        name: string;
      };
    }[];
    reviews: {
      id: number;
      abstract: string;
      time: number;
      title: string;
      cover: string;
      author: {
        name: string;
      };
    }[];
    cultures: {
      id: number;
      abstract: string;
      time: number;
      title: string;
      cover: string;
      author: {
        name: string;
      };
    }[];
    cartoons: {
      id: number;
      abstract: string;
      time: number;
      title: string;
      cover: string;
      author: {
        name: string;
      };
    }[];
  };
}
