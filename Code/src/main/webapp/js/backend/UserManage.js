let modalUser;
$(document).ready(function () {
    $('a').click(function () {
        let id=parseInt($(this).html());
        sendJSON('/user/findById','{"id":'+id+'}',function (data) {
            console.log(data)
            if('success'===data.status){
                data=data.data;
                modalUser=data;
                $('.modal-body>p').html(data.name);
                $('select').val(data.role);
            }
        });
    });
    $('#btn_change').click(function () {
        modalUser.role=$('select').val();
        console.log(modalUser)
        sendJSON('/user/update',JSON.stringify(modalUser),function (data) {
            if('success'===data.status){
                console.log(data.data);
                location.reload();
            }
        });
    });
});