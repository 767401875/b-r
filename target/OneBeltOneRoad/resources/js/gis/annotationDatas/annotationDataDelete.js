$(function () {
    //1.取参数
    var brAnnotationData = JSON.parse(localStorage.getItem("brAnnotationData"));
    var brAnnotationDatas = JSON.parse(localStorage.getItem("brAnnotationDatas"));
    var referrer = document.referrer;
    if(referrer != null){
        var relUrl = getUrlRelativePath(referrer);
        if(relUrl == "/annotationdataadmin/list"){
            //从list跳转过来进行多重删除操作
            modifyListContent();
        }else {
            modifyContent();
        }
    }
    function modifyListContent() {
        var html = "<p id = 'sureContent'>你确定删除标注数据项?下面所有相关项将被删除:</p>";
        if($("#sureContent") != null)
            $("#sureContent").remove();

        $("#sure").after(html);
        console.log(brAnnotationDatas.length);
        html = "<ul id = 'ObjectsContent'>";
        for(var i = 0; i < brAnnotationDatas.length; i++){
            var item = brAnnotationDatas[i];
            html += "<li>标注数据项:"
                + "<a href='/annotationdataadmin/change?id=" + item.id + "'>标注数据项 " + item.name + "</a>"
                + "</li>";
        }
        html += "</ul>";
        if($("#ObjectsContent") != null)
            $("#ObjectsContent").remove();
        $("#Objects").after(html);
        $("#summary").html("数量:" + brAnnotationDatas.length);
    }
    function modifyContent() {
        var html = "<p id = 'sureContent'>你确定删除 &quot;标注数据 " + brAnnotationData.name + "&quot;? 下面所有相关项将被删除:</p>";
        if($("#sureContent") != null)
            $("#sureContent").remove();
        $("#sure").after(html);
        html = "<ul id = 'ObjectsContent'>"
                + "<li>标注数据"
                    + "<a href='/annotationdataadmin/change?id=" + brAnnotationData.id + "'>标注数据 " + brAnnotationData.name + "</a>"
                + "</li>"
            + "</ul>";
        if($("#ObjectsContent") != null)
            $("#ObjectsContent").remove();
        $("#Objects").after(html);
    }
    $("#submit").click(function (e) {
        var formData = new FormData();
        if(brAnnotationData != null){
            var deleteUrl = "/annotationdatamanage/delete";
            formData.append("brAnnotationDataStr", JSON.stringify(brAnnotationData));
            $.ajax({
                url:deleteUrl,
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
                async: false,
                success:function (data) {
                    if(data.success){
                        console.log("success");
                    }
                    else {
                        console.log(data.errMsg);
                    }
                }
            });
        }
        if(brAnnotationDatas != null){
            var deleteUrl = "/annotationdatamanage/deletebatch";
            formData.append("brAnnotationDatasStr", JSON.stringify(brAnnotationDatas));
            $.ajax({
                url:deleteUrl,
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
                async: false,
                success:function (data) {
                    if(data.success){
                        console.log("success");
                    }
                    else {
                        console.log(data.errMsg);
                    }
                }
            });
        }
        var listUrl = "/annotationdataadmin/list"
        window.location.href = listUrl;
        window.event.returnValue = false;
    })
    $(".cancel-link").click(function e() {
        window.location.href = document.referrer;
        window.event.returnValue = false;
    })
})