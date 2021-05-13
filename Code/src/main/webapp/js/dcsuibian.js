function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

// 依赖jQuery
function sendJSON(url,data,successCallback,errorCallback) {
    $.ajax({
        url:url,
        contentType:'application/json;charset=UTF-8',
        data:data,
        dataType:'json',
        type:'post',
        success(data) {
            if(undefined!==successCallback) {
                successCallback(data);
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            if(undefined!==errorCallback) {
                errorCallback(e);
            }
            else{
                console.log(e.status);
                console.log(e.responseText);
            }
        }
    });
}