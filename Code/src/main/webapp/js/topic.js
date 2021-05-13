$(document).ready(function () {
    (function markdownCompile() {
        //获取要转换的文字
        let text = document.getElementById('content').innerHTML;
        //创建实例
        let converter = new showdown.Converter();
        //进行转换
        let html = converter.makeHtml(text);
        //展示到对应的地方  result便是id名称
        document.getElementById('content').innerHTML = html;
    })();

    afterLoginArray.push(function () {
        $('#comment_topic_input').removeAttr('disabled');
        $('#comment_topic_input').attr('placeholder','严禁发布敏感内容');
        $('#comment_topic_btn').removeAttr('disabled');
        $('.comment_textarea>img').attr('src',user.profilePicture);
        $('span.reply a').attr('data-toggle','modal');
        $('span.reply a').attr('data-target','#myModal');
    })
    $('#comment_topic_btn').click(function () {
        let jsond={
            topicId:parseInt(getQueryString("id")),
            content:$('#comment_topic_input').val(),
        };
        sendJSON('/topicComment/replyToTopic',JSON.stringify(jsond),function (data) {
            if('success'===data.status){
                location.reload();
            }
        });
    })

    let topicCommentToReply;

    $('span.reply a').click(function () {
        if(undefined===user){
            alert('请登录');
            return;
        }
        let jsond={
            id:$(this).attr('data'),
        };
        sendJSON("/topicComment/getById",JSON.stringify(jsond),function (data) {
            if('success'===data.status) {
                data=data.data;
                topicCommentToReply=data;
                $('#comment_to_comment_input').attr('placeholder', '回复给 '+data.sender.name+' ：');
            }
        })
    })
    $('#comment_to_comment_btn').click(function () {
        let jsond={
            topicCommentId:topicCommentToReply.id,
            content: $('#comment_to_comment_input').val(),
        }
        sendJSON('/topicComment/replyToTopicComment',JSON.stringify(jsond),function (data) {
            if('success'===data.status){
                location.reload();
            }
        })
    })
});