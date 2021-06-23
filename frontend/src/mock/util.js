const responseBody = {
  data: null,
  message: "",
  code: 0,
  timestamp: -1,
};

export const builder = (data, message, code) => {
  responseBody.data = data
  responseBody.message=responseBody?message:''
  responseBody.code=code&&0!==code?code:0;
  responseBody.timestamp = new Date().getTime();
  return responseBody;
};