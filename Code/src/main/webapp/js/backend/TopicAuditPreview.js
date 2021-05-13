$(document).ready(function () {
    function markdownCompile() {
        //获取要转换的文字
        let text = document.getElementById('content').innerHTML;
        //创建实例
        let converter = new showdown.Converter();
        //进行转换
        let html = converter.makeHtml(text);
        //展示到对应的地方  result便是id名称
        document.getElementById('content').innerHTML = html;
    }
    markdownCompile();
    $('#pass').click(function () {
        let json={
            result:'pass',
            id:parseInt(getQueryString('id')),
        }
        sendJSON('/backend/topic/auditResult',JSON.stringify(json));
    });
    $('#reject').click(function () {
        let json={
            result:'fail',
            id:parseInt(getQueryString('id')),
        }
        sendJSON('/backend/topic/auditResult',JSON.stringify(json));
    });
});