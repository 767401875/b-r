$(function () {
    //1.取参数
    var brAnnotationData = JSON.parse(localStorage.getItem("brAnnotationData"));
    modifyContent();
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
        var deleteUrl = "/annotationdatamanage/delete";
        var formData = new FormData();
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
        var listUrl = "/annotationdataadmin/list"
        window.location.href = listUrl;
        window.event.returnValue = false;
    })
    $(".cancel-link").click(function e() {
        window.location.href = document.referrer;
        window.event.returnValue = false;
    })
    // localStorage.clear();
})