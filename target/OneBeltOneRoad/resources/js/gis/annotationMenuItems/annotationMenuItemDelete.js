$(function () {
    //1.取参数
    var brAnnotationMenuItem = JSON.parse(localStorage.getItem("brAnnotationMenuItem"));
    var brAnnotationMenuItems = JSON.parse(localStorage.getItem("brAnnotationMenuItems"));
    var referrer = document.referrer;
    if(referrer != null){
        var relUrl = getUrlRelativePath(referrer);
        if(relUrl == "/annotationmenuitem/list"){
            //从list跳转过来进行多重删除操作
            modifyListContent();
        }else {
            modifyContent();
        }
    }
    function modifyListContent() {
        var html = "<p id = 'sureContent'>你确定删除标注菜单项?下面所有相关项将被删除:</p>";
        if($("#sureContent") != null)
            $("#sureContent").remove();

        $("#sure").after(html);
        console.log(brAnnotationMenuItems.length);
        html = "<ul id = 'ObjectsContent'>";
        for(var i = 0; i < brAnnotationMenuItems.length; i++){
            var item = brAnnotationMenuItems[i];
            html += "<li>标注菜单项:"
                + "<a href='/annotationmenuitem/change?id=" + item.id + "'>标注菜单项 " + item.caption + "</a>"
                + "</li>";
        }
        html += "</ul>";
        // html = "<ul id = 'ObjectsContent'>"
        //     + "<li>标注菜单项:"
        //     + "<a href='/annotationmenuitem/change?id=" + brAnnotationMenuItem.id + "'>标注菜单项 " + brAnnotationMenuItem.caption + "</a>"
        //     + "</li>"
        //     + "</ul>";
        if($("#ObjectsContent") != null)
            $("#ObjectsContent").remove();
        $("#Objects").after(html);
        $("#summary").html("数量:" + brAnnotationMenuItems.length);
    }
    function modifyContent() {
        var html = "<p id = 'sureContent'>你确定删除 &quot;标注菜单项 " + brAnnotationMenuItem.caption + "&quot; ?下面所有相关项将被删除:</p>";
        if($("#sureContent") != null)
            $("#sureContent").remove();

        $("#sure").after(html);
        html = "<ul id = 'ObjectsContent'>"
            + "<li>标注菜单项:"
            + "<a href='/annotationmenuitem/change?id=" + brAnnotationMenuItem.id + "'>标注菜单项 " + brAnnotationMenuItem.caption + "</a>"
            + "</li>"
            + "</ul>";
        if($("#ObjectsContent") != null)
            $("#ObjectsContent").remove();
        $("#Objects").after(html);
    }
    $("[type='submit']").click(function (e) {
        var formData = new FormData();
        if(brAnnotationMenuItem != null){
            var deleteUrl = "/annotationmenuitemmanage/delete";
            formData.append("brAnnotationMenuItemStr", JSON.stringify(brAnnotationMenuItem));
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
        if(brAnnotationMenuItems != null){
            var deleteUrl = "/annotationmenuitemmanage/deletebatch";
            formData.append("brAnnotationMenuItemsStr", JSON.stringify(brAnnotationMenuItems));
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
        var listUrl = "/annotationmenuitem/list"
        window.location.href = listUrl;
        window.event.returnValue = false;
    })
    $(".cancel-link").click(function e() {
        window.location.href = document.referrer;
        window.event.returnValue = false;
    })
})