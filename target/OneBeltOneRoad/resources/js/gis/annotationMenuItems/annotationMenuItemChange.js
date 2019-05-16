$(function () {
    getAnnotationData();
    //1.Save：新增
    $("[name='_save']").click(
        function () {
            var editUrl = "/annotationmenuitemmanage/edit";
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
                url:editUrl,
                type:"POST",
                data:formData,
                contentType:false,
                processData:false,
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
    //2.Delete:删除 还没有完成
    $(".deletelink").click(function () {
        var brAnnotationMenuItem = {};
        var newUrl = "/annotationmenuitem/delete";
        var idStr = getQueryString("id");
        brAnnotationMenuItem.id = idStr;
        brAnnotationMenuItem.caption = $("[name='caption']").val();
        localStorage.setItem("brAnnotationMenuItem", JSON.stringify(brAnnotationMenuItem));
        window.location.href = newUrl;
        window.event.returnValue=false;

    });
    function getAnnotationData() {
        var idStr = getQueryString("id");
        var listUrl = "/annotationmenuitemmanage/menuitemdetail";
        var referrer = document.referrer;
        if(referrer != null){
            var relUrl = getUrlRelativePath(referrer);
            if(relUrl == "/annotationmenuitem/change"){
                //add跳转时,从add页面获得参数
                var brAnnotationMenuItem = JSON.parse(localStorage.getItem("brAnnotationMenuItem"));
                if(brAnnotationMenuItem != null){
                    var changeUrl = '/annotationmenuitem/change?id=' + idStr;
                    // var html = "<ul class= 'messagelist'>"
                    //     + "<li class='success'>"
                    //     + "The annotation menu items \"AnnotationMenuItem  "
                    //     + "<a href='" + changeUrl + "'>" + brAnnotationMenuItem.caption + "</a>"
                    //     + "\" was changed successfully. You may edit it again below."
                    //     + "</li>"
                    //     + "</ul>";
                    var html = "<ul class= 'messagelist'>"
                        + "<li class='success'>"
                        + "标注菜单项 \""
                        + "<a href='" + changeUrl + "'>" + brAnnotationMenuItem.caption + "</a>"
                        + "\" 已被成功修改. 您能够继续编辑."
                        + "</li>"
                        + "</ul>";
                    if($(".messagelist") != null){
                        $(".messagelist").remove();
                    }
                    $(".breadcrumbs").after(html);
                }
            }
            if(relUrl == "/annotationmenuitem/add"){
                //add跳转时,从add页面获得参数
                var brAnnotationMenuItem = JSON.parse(localStorage.getItem("brAnnotationMenuItem"));
                if(brAnnotationMenuItem != null){
                    var changeUrl = '/annotationmenuitem/change?id=' + idStr;
                    var html = "<ul class= 'messagelist'>"
                        + "<li class='success'>"
                        + "标注菜单项 \""
                        + "<a href='" + changeUrl + "'>" + brAnnotationMenuItem.caption + "</a>"
                        + "\" 已被成功修改. 您能够继续编辑."
                        + "</li>"
                        + "</ul>";
                    if($(".messagelist") != null){
                        $(".messagelist").remove();
                    }
                    $(".breadcrumbs").after(html);
                }
            }
            localStorage.clear();
        }
        $.getJSON(listUrl, function (data) {
            if(data.success){
                var result = data.result;
                var html = '<option value>---------</option>';
                var parentId = null;
                var annotationRefId = null;
                var timeRelated = null;
                var showUp = null;
                for(var item in result){
                    var temp = result[item];
                    if(temp.id == idStr){
                        parentId = temp.parentId;
                        annotationRefId = temp.annotationRefId;
                        timeRelated = temp.timeRelated;
                        showUp = temp.showUp;
                        if(temp.caption != null)
                            $("[name='caption']").val(temp.caption);
                        if(temp.description != null)
                            $("[name='description']").val(temp.description);

                    }
                }
                if(parentId == null)
                     html = '<option value selected>---------</option>';
                //Parent
                for(var item in result){
                    var temp = result[item];
                    if(temp.id === parentId){
                        html += '<option value="' + temp.id + '" selected>标注菜单项 ' + temp.caption + '</option>';
                    }
                    else
                        html += '<option value="' + temp.id + '">标注菜单项 ' + temp.caption + '</option>';
                }
                $("#id_parent").empty();
                $("#id_parent").append(html);
                // AnnotationRef
                // annotationRefId和name相对应
                html = '<option value>---------</option>';
                if(annotationRefId == null)
                    html = '<option value selected>---------</option>';
                for(var item in result){
                    var temp = result[item];
                    if(temp.annotationRefId == null) continue;
                    if(temp.annotationRefId === annotationRefId){
                        html += '<option value="' + temp.annotationRefId + '" selected>标注菜单项 ' + temp.name + '</option>';
                    }
                    else

                        html += '<option value="' + temp.annotationRefId + '">标注菜单项 ' + temp.name + '</option>';
                }
                $("#id_annotationRef").empty();
                $("#id_annotationRef").append(html);
                //TimeRelated
                //ShowUp

            }
            else {
                console.log("error!");
            }
        })

    }
    //4.Save and add another
    $("[name='_addanother']").on("click", function (e) {
            var editUrl = "/annotationmenuitemmanage/edit";
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
            localStorage.setItem("brAnnotationMenuItem", JSON.stringify(brAnnotationMenuItem));
            var newUrl = '/annotationmenuitem/add';
            window.location.href = newUrl;
            window.event.returnValue=false;
        }
    );
    //5.Save and continue editing
    $("[name='_continue']").on("click", function (e) {
        var editUrl = "/annotationmenuitemmanage/edit";
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
        localStorage.setItem("brAnnotationMenuItem", JSON.stringify(brAnnotationMenuItem));
    })
})