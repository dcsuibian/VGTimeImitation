$(document).ready(function () {
    function getTopic() {
        let topic = {
            id: parseInt(getQueryString('id')),
            authorName: $('#author_name').val(),
            content: $('#content').val(),
            type: $('#type').val(),
            title: $('#title').val(),
            resume: $('#resume').val(),
            coverImage: $('#cover_image').val(),
        };
        return topic;
    }

    $('#btn-save').click(function () {
        $.ajax({
            url: '/topic/save',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(getTopic()),
            dataType: 'json',
            // xhrFields: {withCredentials: true},
            type: 'post',
            success(data) {
                console.log(data)
                if ('success' === data.status) {
                    alert("保存成功");
                } else if ('error' === data.status) {
                    alert(data.error_message);
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
    $('#btn-submit').click(function () {
        $.ajax({
            url: '/topic/submit',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(getTopic()),
            dataType: 'json',
            // xhrFields: {withCredentials: true},
            type: 'post',
            success(data) {
                console.log(data)
                if ('success' === data.status) {
                    alert("提交成功");
                } else if ('error' === data.status) {
                    alert(data.error_message);
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
    $('#btn-clear').click(function () {
        $('#author_name').val('');
        $('#content').val('');
        $('#type').val('');
        $('#title').val('');
        $('#resume').val('');
        $('#cover_image').val('');
    });
});