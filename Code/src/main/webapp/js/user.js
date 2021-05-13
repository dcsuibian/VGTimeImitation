//依赖dcsuibian.js
let user;
let afterLoginArray=new Array();
$(document).ready(function () {
    afterLoginArray.push(function (data) {
        user=data;
        $('#loginPanel').hide();
        $('#loginedPanel').show();
    })
    sendJSON('/user/isLogined','{}',function (data) {
        if('success'===data.status){
            for(let i=0;i<afterLoginArray.length;i++){
                afterLoginArray[i](data.data);
            }
        }
    });

    $('#user_main_display').click(function () {
        $('.user_main').toggle();
    });
    $('#register_now').click(function () {
        $('#registerPanel').show();
        $('#loginPanel').hide();
    });
    $('#login_now').click(function () {
        $('#registerPanel').hide();
        $('#loginPanel').show();
    });
    $('#btn_register').click(function () {
        let user={
            phoneNumber:$('#register_phone_number').val(),
            password:$('#register_password').val(),
            realName:''===$('#register_real_name').val().trim()?null:$('#register_real_name').val().trim(),
            identityCard:''===$('#register_identity_card').val().trim()?null:$('#register_identity_card').val().trim(),
        };
        $.ajax({
            url:'/user/register',
            contentType:'application/json;charset=UTF-8',
            data:JSON.stringify(user),
            dataType:'json',
            type:'post',
            success(data){
                console.log(data)
                if('success'===data.status){
                    $('#registerPanel').hide();
                    $('#loginPanel').show();
                }else if('error'===data.status){
                    alert(data.error_message);
                }
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
    $('#btn_login').click(function () {
        let temp={
            phoneNumber:$('#login_phoneNumber').val(),
            password:$('#login_password').val(),
        };
        sendJSON('/user/login',JSON.stringify(temp),function (data) {
            if('success'===data.status){
                for(let i=0;i<afterLoginArray.length;i++){
                    afterLoginArray[i](data.data);
                }
            }else if('error'===data.status){
                alert(data.error_message);
            }
        })
    });
    $('#btn_exit').click(function () {
        sendJSON('/user/exit','{}',function () {
            location.reload();
        })
    });
});