$(document).ready(function () {
    let pageSize=12;
    let newsCount=0;
    let current=0;
    let currentSearchText=(''===$("#searchText").val()?null:$("#searchText").val());
    let currentStatus=$("input[name=\"status\"]:checked").val();
    let currentOrderBy=$("#orderby").val();
    let currentSearchNewsType=("全部"==$("#SearchNewsType").val()?null:$("#SearchNewsType").val());
    newsquerypart();
    function newsquerypart(){
        let jsond = {searchText:currentSearchText,status:currentStatus,orderBy:currentOrderBy,offset:current,rows:pageSize,searchNewsType:currentSearchNewsType};
        sendJSON('/backend/topic/getListForAuditor',JSON.stringify(jsond),function (data) {
            if('success'===data.status){
                data=data.data;
                newsCount=data.count;
                $(".Content-Main").empty();
                if(0==newsCount){
                    current=0;
                    $("#lastpage").html(1);
                    $("#currentpage").get(0).value=1;
                    return;
                }
                if(current>newsCount-1){
                    current=parseInt((newsCount-1)/pageSize)*pageSize;
                    newsquerypart();
                    return;
                }
                $("#lastpage").html(1+parseInt((newsCount-1)/pageSize));
                $("#currentpage").get(0).value=1+parseInt(current/pageSize);
                data=data.topics;
                $(".Content-Main").empty();
                for(let it=0;it<data.length;it++){
                    $(".Content-Main").append("<div class=\"Content-NewsItem\">\n" +
                        "                <div class=\"Content-NewsItem-Picture\">\n" +
                        "                    <a href=\"/backend/TopicAuditPreview.jsp?id="+data[it].id+"\" target=\"_blank\">\n" +
                        "                        <img class=\"Content-NewsItem-Picture-img\" src=\""+data[it].coverImage+"\">\n" +
                        "                    </a>\n" +
                        "                </div>\n" +
                        "                <div class=\"Content-NewsItem-Title\">\n" +
                        "                    <a href=\"/backend/TopicAuditPreview.jsp?id="+data[it].id+"\" target=\"_blank\" class=\"Content-NewsItem-Title-a\">\n" +
                        "                        <p class=\"Content-NewsItem-Title-p\">\n" +
                        "                            "+data[it].id+"&nbsp;&nbsp;&nbsp;"+data[it].title+"\n" +
                        "                        </p>\n" +
                        "                    </a>\n" +
                        "                </div>\n" +
                        "            </div>");
                }
            }

        },function (e) {
            alert("获取数据失败");
            newsCount=0;
            current=0;
            $(".Content-Main").empty();
            $("#lastpage").html(1);
            $("#currentpage").get(0).value=1;
        });
    }
    $("#searchButton").click(function () {
        newsCount=0;
        current=0;
        currentSearchText=$("#searchText").val();
        currentStatus=$("input[name=\"status\"]:checked").val();
        currentOrderBy=$("#orderby").val();
        currentSearchNewsType=("全部"==$("#SearchNewsType").val()?null:$("#SearchNewsType").val());
        newsquerypart();
    });
    $("#previouspage").click(function () {
        if(current-pageSize<0){
            alert("已经是第一页");
            return;
        }
        current-=pageSize;
        newsquerypart();
    });
    $("#nextpage").click(function () {
        if(current+pageSize>newsCount-1) {
            alert("已经是最后一页");
            return;
        }
        current+=pageSize;
        newsquerypart();
    });
    $("#currentpage").on("keypress",function (event) {
        if(13==event.keyCode){
            let temp=(parseInt($("#currentpage").val())-1)*pageSize;
            if(0<=temp&&temp<=newsCount-1){
                current=temp;
                newsquerypart();
            }
            else{
                $("#currentpage").get(0).value=1+parseInt(current/pageSize);
            }
        }
    })
});