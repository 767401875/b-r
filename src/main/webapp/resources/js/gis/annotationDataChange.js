$(function () {
    getAnnotationData();
    //1.Save：新增
    $("[name='_save']").click(
        function () {
            var editUrl = "/annotationdatamanage/edit";
            var brAnnotationData = {};
            var idStr = getQueryString("id");
            brAnnotationData.id = idStr;
            brAnnotationData.name = $("#id_name").val();
            brAnnotationData.layers = $("#id_layers").val();
            brAnnotationData.layerFeatures = $("#id_layerFeatures").val();
            brAnnotationData.time = $("#id_time").val();
            brAnnotationData.style = $("#id_style").val();
            brAnnotationData.chart = $("#id_chart").val();
            brAnnotationData.keyPoint = $("#id_keyPoint").val();
            var formData = new FormData();
            formData.append("brAnnotationDataStr", JSON.stringify(brAnnotationData));
            $.ajax({
                url:editUrl,
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
                success:function (data) {
                    if(data.success){
                        localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
                        var newUrl = '/annotationdataadmin/list';
                        window.location.href = newUrl;
                        window.event.returnValue=false;
                    }
                    else {
                        console.log(data.errMsg);
                        alert("error!" + data.errMsg);
                    }
                }
            })
            localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
        }

    );
    //2.Delete:删除 还没有完成
    $(".deletelink").click(function () {
        var brAnnotationData = {};
        var newUrl = "/annotationdataadmin/delete";
        var idStr = getQueryString("id");
        brAnnotationData.id = idStr;
        brAnnotationData.name = $("#id_name").val();
        localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
        window.location.href = newUrl;
        window.event.returnValue=false;

    });
    //3.列表
    function getAnnotationData() {
        var idStr = getQueryString("id");
        var listUrl = "/annotationdatamanage/annotationdetail/" + idStr;
        var referrer = document.referrer;
        if(referrer != null){
            var relUrl = getUrlRelativePath(referrer);
            if(relUrl == "/annotationdataadmin/change"){
                //add跳转时,从add页面获得参数
                var brAnnotationData = JSON.parse(localStorage.getItem("brAnnotationData"));
                if(brAnnotationData != null){
                    var changeUrl = '/annotationdataadmin/change?id=' + idStr;
                    var html = "<ul class= 'messagelist'>"
                        + "<li class='success'>"
                        + "The annotation data \"AnnotationData "
                        + "<a href='" + changeUrl + "'>" + brAnnotationData.name + "</a>"
                        + "\" was changed successfully. You may edit it again below."
                        + "</li>"
                        + "</ul>";
                    if($(".messagelist") != null){
                        $(".messagelist").remove();
                    }
                    $(".breadcrumbs").after(html);
                }
            }
            if(relUrl == "/annotationdataadmin/add"){
                //add跳转时,从add页面获得参数
                var brAnnotationData = JSON.parse(localStorage.getItem("brAnnotationData"));
                if(brAnnotationData != null){
                    var changeUrl = '/annotationdataadmin/change?id=' + idStr;
                    var html = "<ul class= 'messagelist'>"
                        + "<li class='success'>"
                        + "The annotation data \"AnnotationData "
                        + "<a href='" + changeUrl + "'>" + brAnnotationData.name + "</a>"
                        + "\" was added successfully. You may edit it again below."
                        + "</li>"
                        + "</ul>";
                    if($(".messagelist") != null){
                        $(".messagelist").remove();
                    }
                    $(".breadcrumbs").after(html);
                }
            }
        }
        $.getJSON(listUrl, function (data) {
            if(data.success){
                var result = data.result;
                if(result != null){
                    if(result.name != null)
                        $("#id_name").val(result.name);
                    if(result.layers != null&&result.layers.value != null)
                        $("#id_layers").val(result.layers.value);
                    if(result.layerFeatures != null&&result.layerFeatures.value != null)
                        $("#id_layerFeatures").val(result.layerFeatures.value);
                    if(result.time != null&&result.time.value != null)
                        $("#id_time").val(result.time.value);
                    if(result.style != null&&result.style.value != null)
                        $("#id_style").val(result.style.value);
                    if(result.chart != null&&result.chart.value != null)
                        $("#id_chart").val(result.chart.value);
                    if(result.keyPoint != null&&result.keyPoint.value != null)
                        $("#id_keyPoint").val(result.keyPoint.value);
                }
            }
            else{
                alert("error!");
            }
        })
    }
    //4.Save and add another
    $("[name='_addanother']").on("click", function (e) {
        var editUrl = "/annotationdatamanage/edit";
        var brAnnotationData = {};
        var idStr = getQueryString("id");
        brAnnotationData.id = idStr;
        brAnnotationData.name = $("#id_name").val();
        brAnnotationData.layers = $("#id_layers").val();
        brAnnotationData.layerFeatures = $("#id_layerFeatures").val();
        brAnnotationData.time = $("#id_time").val();
        brAnnotationData.style = $("#id_style").val();
        brAnnotationData.chart = $("#id_chart").val();
        brAnnotationData.keyPoint = $("#id_keyPoint").val();
        var formData = new FormData();
        formData.append("brAnnotationDataStr", JSON.stringify(brAnnotationData));
        $.ajax({
            url:editUrl,
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
        })
        localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
        var newUrl = '/annotationdataadmin/add';
        window.location.href = newUrl;
        window.event.returnValue=false;
        }
    );
    //5.Save and continue editing
    $("[name='_continue']").on("click", function (e) {
        var editUrl = "/annotationdatamanage/edit";
        var brAnnotationData = {};
        var idStr = getQueryString("id");
        brAnnotationData.id = idStr;
        brAnnotationData.name = $("#id_name").val();
        brAnnotationData.layers = $("#id_layers").val();
        brAnnotationData.layerFeatures = $("#id_layerFeatures").val();
        brAnnotationData.time = $("#id_time").val();
        brAnnotationData.style = $("#id_style").val();
        brAnnotationData.chart = $("#id_chart").val();
        brAnnotationData.keyPoint = $("#id_keyPoint").val();
        var formData = new FormData();
        formData.append("brAnnotationDataStr", JSON.stringify(brAnnotationData));
        $.ajax({
            url:editUrl,
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
        localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
    })
})