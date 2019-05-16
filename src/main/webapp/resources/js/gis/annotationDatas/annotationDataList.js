$(function() {
    //1.列表:将每项的ID作为连接
    getList();
    function getList() {
        var referrer = document.referrer;
        var brAnnotationData = JSON.parse(localStorage.getItem("brAnnotationData"));
        localStorage.clear();
        if(referrer != ""){
            var relUrl = getUrlRelativePath(referrer);
            if(relUrl == "/annotationdataadmin/delete"&&brAnnotationData != null){
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "标注数据 \""
                    + brAnnotationData.name + " 已被成功删除."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
            if(relUrl == "/annotationdataadmin/add"&&brAnnotationData != null){
                var changeUrl = '/annotationdataadmin/change?id=' + brAnnotationData.id;
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "标注数据 \""
                    + "<a href='" + changeUrl + "'>" + brAnnotationData.name + "</a>"
                    + "\" 已被成功添加."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
            if(relUrl == "/annotationdataadmin/change"&&brAnnotationData != null){
                var changeUrl = '/annotationdataadmin/change?id=' + brAnnotationData.id;
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "标注数据 \""
                    + "<a href='" + changeUrl + "'>" + brAnnotationData.name + "</a>"
                    + "\" 已被成功修改."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
            }
        }

        var listUrl = "/annotationdatamanage/getlist";
        $.getJSON(listUrl, function (data) {
            var annotationDataList = data.result;
            var html = "";
            var href = "";
            $("#result_list tbody").empty();
            for(var i = 0; i < annotationDataList.length; i++){
                var item = annotationDataList[i];
                href = "/annotationdataadmin/change?id=" + item.id ;
                if(i % 2 == 0){
                    html =
                        '<tr class="row2">'
                            + '<td class="action-checkbox">'
                                + '<input type="checkbox" name="_selected_action" value="' + item.id + '" class="action-select">'
                            + '</td>'
                            + '<th class="field-__str__">'
                                + '<a href="' + href + '">' + item.name +'</a>'
                            + '</th>'
                        + '</tr>';
                }else {
                    html =
                        '<tr class="row1">'
                            + '<td class="action-checkbox">'
                                + '<input type="checkbox" name="_selected_action" value="' + item.id + '" class="action-select">'
                            + '</td>'
                            + '<th class="field-__str__">'
                                + '<a href="' + href + '">' + item.name +'</a>'
                            + '</th>'
                        + '</tr>';
                }

                $("#result_list tbody").append(html);
            }
        })
    }
    $(".addlink").click(function (e) {
        var newUrl = "/annotationdataadmin/add"
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
            var brAnnotationDatas = new Array();
            for(var i = 0; i < temps.length; i++){
                var brAnnotationDataItem = {};
                var temp = temps[i];
                var text = $(temp.parentNode.parentNode)[0];
                // var text = temp.parent.parent.find("th a").text;
                brAnnotationDataItem.id = temp.value;
                brAnnotationDataItem.name = text.textContent;
                brAnnotationDatas[i] = brAnnotationDataItem;
            }
            localStorage.setItem("brAnnotationDatas", JSON.stringify(brAnnotationDatas));
            var newUrl = "/annotationdataadmin/delete";
            window.location.href = newUrl;
        }
        window.event.returnValue = false;
    });
})