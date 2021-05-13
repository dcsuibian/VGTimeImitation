$(document).ready(function () {
    // sendJSON('/user/isLogined','{}',function (data) {
    //     console.log(data)
    // });
    // $('#test').click(function () {
    //     sendJSON('/test/SpringMVCTest', '{"data":5}',function (data) {
    //         console.log(data);
    //     });
    // });
    let jsont={
        key:'王彬大sb',
    }
    sendJSON('/test/register',JSON.stringify(jsont));
});