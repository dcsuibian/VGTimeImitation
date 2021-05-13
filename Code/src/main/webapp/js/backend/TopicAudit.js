$(document).ready(function () {
    sendJSON('/backend/topic/findForAuditor','{}',function (data) {
        if('success'===data.status){
            data=data.data;
            for(let i=0;i<data.length;i++){
                console.log(data[i])
                let item='.row:nth-child('+(1+Math.floor(i/4))+') .topic_list_item:nth-child('+(1+i%4)+')';
                // $(item+' a').attr('data-toggle','modal');
                // $(item+' a').attr('data-target','#topicModal');
                $(item+' .img_box').html('<img src="'+data[i].coverImage+'">')
                $(item+" .info_box p").html(data[i].id+'&nbsp;&nbsp;&nbsp;&nbsp;'+data[i].title)
                $(item+' a').attr('href','/backend/TopicAuditPreview.jsp?id='+$(item+" .info_box p").html().split('&')[0]);
            }
        }
    });
});