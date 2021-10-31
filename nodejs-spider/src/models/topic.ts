export interface TopicVO {
  id: number | null;
  title: string | null;
  abstract: string | null;
  author: null | {
    id: number | null;
    name: string;
  };
  editor: null | {
    id: number | null;
    name: string;
  };
  time: number | null;
  content: string | null;
  cover: string | null;
}
