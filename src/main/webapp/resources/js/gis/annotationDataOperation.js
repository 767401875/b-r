$(function () {
    //1.新增
    var registerUrl = "/annotationdatamanage/register";
    $("[name='_save']").click(
        function () {
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
                url:registerUrl,
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
                success:function (data) {
                    if(data.success){
                        console.log("success");
                        alert("提交成功");
                    }
                    else {
                        console.log(data.errMsg);
                        alert("提交失败!" + data.errMsg);
                    }
                }
            })
        }

    );
    //2.删除
    var deleteUrl = "/annotationdatamanage/delete";
    $(".deletelink").click(function () {
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
            url:deleteUrl,
            type:"POST",
            data:formData,
            contentType:false,
            processData:false,
            success:function (data) {
                if(data.success){
                    console.log("success");
                    alert("提交成功");
                }
                else {
                    console.log(data.errMsg);
                    alert("提交失败!" + data.errMsg);
                }
            }
        })
    });
})