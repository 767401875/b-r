$(function () {
    getAnnotation();
    //1.Save：新增
    $("[name='_save']").click(
        function () {
            var addUrl = "/annotationdatamanage/add";
            var brAnnotationData = {};
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
                url:addUrl,
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
                async: false,
                success:function (data) {
                    if(data.success){
                        alert("success");
                    }
                    else {
                        console.log(data.errMsg);
                        alert("error!" + data.errMsg);
                    }
                }
            })
            localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
            var newUrl = '/annotationdataadmin/list';
            window.location.href = newUrl;
            window.event.returnValue=false;
        }

    );
    //2.Save and add another
    $("[name='_addanother']").on("click", function (e) {
            var addUrl = "/annotationdatamanage/add";
            var brAnnotationData = {};
            var id = null;
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
                url:addUrl,
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
                async: false,
                success:function (data) {
                    if(data.success){
                        console.log("success");
                        brAnnotationData.id = data.id;
                        localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
                    }
                    else {
                        console.log(data.errMsg);
                        alert("error!" + data.errMsg);
                    }
                }
            });
            emptyData();
        }
    );
    //3.Save and continue editing
    $("[name='_continue']").on("click", function (e) {
        var addUrl = "/annotationdatamanage/add";
        var id = null;
        var brAnnotationData = {};
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
            url:addUrl,
            type:"POST",
            data:formData,
            contentType:false,
            processData:false,
            async: false,
            success:function (data) {
                if(data.success){
                    console.log("success");
                    id = data.id;
                }
                else {
                    console.log(data.errMsg);
                    alert("error!" + data.errMsg);
                }
            }
        });
        var changeUrl = '/annotationdataadmin/change?id=' + id;
        //对跳转页面change进行传参
        localStorage.setItem("brAnnotationData", JSON.stringify(brAnnotationData));
        window.location.href = changeUrl;
        window.event.returnValue=false;
    })
    function emptyData() {
        $("#id_name").val("");
        $("#id_layers").val(null);
        $("#id_layerFeatures").val(null);
        $("#id_time").val(null);
        $("#id_style").val(null);
        $("#id_chart").val(null);
        $("#id_keyPoint").val(null);
    }
    function getAnnotation() {
        var referrer = document.referrer;
        var brAnnotationData = JSON.parse(localStorage.getItem("brAnnotationData"));
        if(referrer != ""){
            var relUrl = getUrlRelativePath(referrer);
            if(relUrl != "/annotationdataadmin/list"&&brAnnotationData != null){
                var changeUrl = '/annotationdataadmin/change?id=' + brAnnotationData.id;
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "The annotation data \"AnnotationData "
                    + "<a href='" + changeUrl + "'>" + brAnnotationData.name + "</a>"
                    + "\" was added successfully. You may add another annotation data below."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
        }
    }
})