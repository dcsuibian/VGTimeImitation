$(document).ready(function () {
    $('#createTopic').click(function () {
        $.ajax({
            url: '/topic/create',
            contentType: 'application/json;charset=UTF-8',
            data: '{}',
            dataType: 'json',
            type: 'post',
            success(data) {
                console.log(data)
                if ('success' === data.status) {
                    location.reload();
                } else if ('error' === data.status) {
                    alert(data.error_message);
                }
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
});