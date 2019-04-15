$(function() {
    //1.列表:将每项的ID作为连接
    getList();
    function getList() {
        var referrer = document.referrer;
        var brAnnotationData = JSON.parse(localStorage.getItem("brAnnotationData"));
        if(referrer != ""){
            var relUrl = getUrlRelativePath(referrer);
            if(relUrl == "/annotationdataadmin/delete"&&brAnnotationData != null){
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "The annotation data \"AnnotationData "
                    + brAnnotationData.name + " was deleted successfully."
                    + "</li>"
                    + "</ul>";
                if($(".messagelist") != null){
                    $(".messagelist").remove();
                }
                $(".breadcrumbs").after(html);
                localStorage.clear();
            }
            if(relUrl == "/annotationdataadmin/add"&&brAnnotationData != null){
                var changeUrl = '/annotationdataadmin/change?id=' + brAnnotationData.id;
                var html = "<ul class= 'messagelist'>"
                    + "<li class='success'>"
                    + "The annotation data \"AnnotationData "
                    + "<a href='" + changeUrl + "'>" + brAnnotationData.name + "</a>"
                    + "\" was added successfully."
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
                    + "The annotation data \"AnnotationData "
                    + "<a href='" + changeUrl + "'>" + brAnnotationData.name + "</a>"
                    + "\" was changed successfully."
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

})