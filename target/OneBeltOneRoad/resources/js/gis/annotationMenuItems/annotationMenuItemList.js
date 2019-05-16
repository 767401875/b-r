$(function () {
    getList();
    function getList() {
        var referrer = document.referrer;
        var brAnnotationMenuItem = JSON.parse(localStorage.getItem("brAnnotationMenuItem"));
        localStorage.clear();
        if(referrer != ""){
            var relUrl = getUrlRelativePath(referrer);
            if(relUrl == "/annotationmenuitem/delete"&&brAnnotationMenuItem != null){
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "标注菜单项 \""
                    + brAnnotationMenuItem.caption + "\" 已被成功删除."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
            if(relUrl == "/annotationmenuitem/add"&&brAnnotationMenuItem != null){
                var changeUrl = '/annotationmenuitem/change?id=' + brAnnotationMenuItem.id;
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "标注菜单项 \""
                    + "<a href='" + changeUrl + "'>" + brAnnotationMenuItem.caption + "</a>"
                    + "\" 已被成功添加."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
            if(relUrl == "/annotationmenuitem/change"&&brAnnotationMenuItem != null){
                var changeUrl = '/annotationmenuitem/change?id=' + brAnnotationMenuItem.id;
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "标注菜单项 \""
                    + "<a href='" + changeUrl + "'>" + brAnnotationMenuItem.caption + "</a>"
                    + "\" 已被成功修改."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
        }
        var listUrl = "/annotationmenuitemmanage/getlist";
        $.getJSON(listUrl, function (data) {
            var brAnnotationMenuItemList = data.result;
            var html = "";
            var href = "";
            $("#result_list tbody").empty();
            for(var i = 0; i < brAnnotationMenuItemList.length; i++){
                var item = brAnnotationMenuItemList[i];
                href = "/annotationmenuitem/change?id=" + item.id ;
                if(i % 2 == 0){
                    html =
                        '<tr class="row2">'
                        + '<td class="action-checkbox">'
                        + '<input type="checkbox" name="_selected_action" value="' + item.id + '" class="action-select">'
                        + '</td>'
                        + '<th class="field-__str__">'
                        + '<a href="' + href + '">' + item.caption +'</a>'
                        + '</th>'
                        + '</tr>';
                }else {
                    html =
                        '<tr class="row1">'
                        + '<td class="action-checkbox">'
                        + '<input type="checkbox" name="_selected_action" value="' + item.id + '" class="action-select">'
                        + '</td>'
                        + '<th class="field-__str__">'
                        + '<a href="' + href + '">' + item.caption +'</a>'
                        + '</th>'
                        + '</tr>';
                }

                $("#result_list tbody").append(html);

            }
            $(".paginator").text(data.size + "个 标注菜单项");
        })
    }
    $(".addlink").click(function (e) {
        var newUrl = "/annotationmenuitem/add"
        window.location.href = newUrl;
        window.event.returnValue = false;
    })
    $("#action-toggle").click(function (e) {
        if(this.checked)
            $('[type="checkbox"]').attr("checked", true);
        else
            $('[type="checkbox"]').attr("checked", false);
    })
    $("#submit").click(function (e) {
       var selValue = $('[name="action"]').find("option:selected").val();
        //选中删除功能
        if(selValue === "delete_selected"){
            var temps = $('tbody input[type=checkbox]:checked');
            var brAnnotationMenuItems = new Array();
            for(var i = 0; i < temps.length; i++){
                var brAnnotationMenuItem = {};
                var temp = temps[i];
                var text = $(temp.parentNode.parentNode)[0];
                // var text = temp.parent.parent.find("th a").text;
                brAnnotationMenuItem.id = temp.value;
                brAnnotationMenuItem.caption = text.textContent;
                console.log(text.textContent);
                console.log(temp.value);
                brAnnotationMenuItems[i] = brAnnotationMenuItem;
            }
            localStorage.setItem("brAnnotationMenuItems", JSON.stringify(brAnnotationMenuItems));
            var newUrl = "/annotationmenuitem/delete";
            window.location.href = newUrl;
        }
        window.event.returnValue = false;
    });
})