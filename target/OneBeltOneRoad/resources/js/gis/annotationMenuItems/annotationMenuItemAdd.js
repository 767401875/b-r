$(function () {
    getAnnotation();
    //1.Save：新增
    $("[name='_save']").click(
        function () {
            var addUrl = "/annotationmenuitemmanage/add";
            var brAnnotationMenuItem = {};
            var idStr = getQueryString("id");
            var parentId = $("#id_parent option:selected").val();
            var annotationRefId = $("#id_annotationRef option:selected").val();
            var annotationRefName = $("#id_annotationRef option:selected").text();
            var timeRelated = $("#id_timeRelated option:selected").val();
            var showUp = $("#id_showUp option:selected").val();
            brAnnotationMenuItem.id = idStr;
            brAnnotationMenuItem.caption =  $("[name='caption']").val();
            brAnnotationMenuItem.description =  $("[name='description']").val();
            switch (timeRelated){
                case "1":
                    brAnnotationMenuItem.timeRelated = null;
                    break;
                case "2":
                    brAnnotationMenuItem.timeRelated = true;
                    break;
                case "3":
                    brAnnotationMenuItem.timeRelated = false;
            }
            switch (showUp){
                case "1":
                    brAnnotationMenuItem.showUp = null;
                    break;
                case "2":
                    brAnnotationMenuItem.showUp = true;
                    break;
                case "3":
                    brAnnotationMenuItem.showUp = false;
            }
            brAnnotationMenuItem.annotationRefId = annotationRefId;
            brAnnotationMenuItem.parentId = parentId;
            var name = annotationRefName.split(" ")[1];
            brAnnotationMenuItem.name = name;
            var formData = new FormData();
            formData.append("brAnnotationMenuItemStr", JSON.stringify(brAnnotationMenuItem));
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
                    }
                    else {
                        console.log(data.errMsg);
                    }
                }
            })
            localStorage.setItem("brAnnotationMenuItem", JSON.stringify(brAnnotationMenuItem));
            var newUrl = '/annotationmenuitem/list';
            window.location.href = newUrl;
            window.event.returnValue=false;
        }

    );
    //2.Save and add another
    $("[name='_addanother']").on("click", function (e) {
            var addUrl = "/annotationmenuitemmanage/add";
            var brAnnotationMenuItem = {};
            var idStr = getQueryString("id");
            var parentId = $("#id_parent option:selected").val();
            var annotationRefId = $("#id_annotationRef option:selected").val();
            var annotationRefName = $("#id_annotationRef option:selected").text();
            var timeRelated = $("#id_timeRelated option:selected").val();
            var showUp = $("#id_showUp option:selected").val();
            brAnnotationMenuItem.id = idStr;
            brAnnotationMenuItem.caption =  $("[name='caption']").val();
            brAnnotationMenuItem.description =  $("[name='description']").val();
            switch (timeRelated){
                case "1":
                    brAnnotationMenuItem.timeRelated = null;
                    break;
                case "2":
                    brAnnotationMenuItem.timeRelated = true;
                    break;
                case "3":
                    brAnnotationMenuItem.timeRelated = false;
            }
            switch (showUp){
                case "1":
                    brAnnotationMenuItem.showUp = null;
                    break;
                case "2":
                    brAnnotationMenuItem.showUp = true;
                    break;
                case "3":
                    brAnnotationMenuItem.showUp = false;
            }
            brAnnotationMenuItem.annotationRefId = annotationRefId;
            brAnnotationMenuItem.parentId = parentId;
            var name = annotationRefName.split(" ")[1];
            brAnnotationMenuItem.name = name;
            var formData = new FormData();
            formData.append("brAnnotationMenuItemStr", JSON.stringify(brAnnotationMenuItem));
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
                        localStorage.setItem("brAnnotationMenuItem", JSON.stringify(brAnnotationMenuItem));
                    }
                    else {
                        console.log(data.errMsg);
                    }
                }
            });
            emptyData();
        }
    );
    //3.Save and continue editing
    $("[name='_continue']").on("click", function (e) {
        var addUrl = "/annotationmenuitemmanage/add";
        var brAnnotationMenuItem = {};
        var idStr = getQueryString("id");
        var parentId = $("#id_parent option:selected").val();
        var annotationRefId = $("#id_annotationRef option:selected").val();
        var annotationRefName = $("#id_annotationRef option:selected").text();
        var timeRelated = $("#id_timeRelated option:selected").val();
        var showUp = $("#id_showUp option:selected").val();
        brAnnotationMenuItem.id = idStr;
        brAnnotationMenuItem.caption =  $("[name='caption']").val();
        brAnnotationMenuItem.description =  $("[name='description']").val();
        switch (timeRelated){
            case "1":
                brAnnotationMenuItem.timeRelated = null;
                break;
            case "2":
                brAnnotationMenuItem.timeRelated = true;
                break;
            case "3":
                brAnnotationMenuItem.timeRelated = false;
        }
        switch (showUp){
            case "1":
                brAnnotationMenuItem.showUp = null;
                break;
            case "2":
                brAnnotationMenuItem.showUp = true;
                break;
            case "3":
                brAnnotationMenuItem.showUp = false;
        }
        brAnnotationMenuItem.annotationRefId = annotationRefId;
        brAnnotationMenuItem.parentId = parentId;
        var name = annotationRefName.split(" ")[1];
        brAnnotationMenuItem.name = name;
        var formData = new FormData();
        formData.append("brAnnotationMenuItemStr", JSON.stringify(brAnnotationMenuItem));
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
                }
            }
        });
        var changeUrl = '/annotationmenuitem/change?id=' + id;
        //对跳转页面change进行传参
        localStorage.setItem("brAnnotationMenuItem", JSON.stringify(brAnnotationMenuItem));
        window.location.href = changeUrl;
        window.event.returnValue=false;
    })
    function emptyData() {
        $("#id_caption").val("");
        $("#id_description").val(null);
    }
    function getAnnotation() {
        var referrer = document.referrer;
        var brAnnotationMenuItem = JSON.parse(localStorage.getItem("brAnnotationMenuItem"));
        var listUrl = "/annotationmenuitemmanage/menuitemdetail";
        localStorage.clear();
        if(referrer != ""){
            var relUrl = getUrlRelativePath(referrer);
            if(relUrl != "/annotationdataadmin/list"&&brAnnotationMenuItem != null){
                var changeUrl = '/annotationdataadmin/change?id=' + brAnnotationMenuItem.id;
                // var html = "<ul class= 'messagelist'>"
                //     + "<li class='success'>"
                //     + "The annotation menu item \"AnnotationMenuItem "
                //     + "<a href='" + changeUrl + "'>" + brAnnotationMenuItem.caption + "</a>"
                //     + "\" was added successfully. You may add another annotation menu item below."
                //     + "</li>"
                //     + "</ul>";
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "标注菜单项 \""
                    + "<a href='" + changeUrl + "'>" + brAnnotationMenuItem.caption + "</a>"
                    + "\" 已被成功添加. 您能够在下面添加另外一个标注菜单项."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
        }
        $.getJSON(listUrl, function (data) {
            if(data.success){
                var result = data.result;
                var html = '<option value selected>---------</option>';
                var parentId = null;
                var annotationRefId = null;
                var timeRelated = null;
                var showUp = null;
                //Parent
                for(var item in result){
                    var temp = result[item];
                    html += '<option value="' + temp.id + '">标注菜单项 ' + temp.caption + '</option>';
                }
                $("#id_parent").empty();
                $("#id_parent").append(html);
                // AnnotationRef
                // annotationRefId和name相对应
                html = '<option value selected>---------</option>';
                for(var item in result){
                    var temp = result[item];
                    if(temp.annotationRefId == null) continue;
                    html += '<option value="' + temp.annotationRefId + '">标注菜单项 ' + temp.name + '</option>';
                }
                $("#id_annotationRef").empty();
                $("#id_annotationRef").append(html);
                //TimeRelated
                //ShowUp

            }
            else {
                console.log("错误!");
            }
        })

    }
})