$(function () {
    var userId = getQueryString("userId");//SystemUser id字段
    var registerUserUrl = "/usermanage/registeruser";
    var getUserUrl = "/usermanage/getuserbyid?userId="+userId;
    var isEdit = userId ? true : false;
    var editUserUrl = "/usermanage/edituser";
    if(isEdit)
        getUserInfo(userId);

    //获取user数据并填充表单
    function getUserInfo(userId){
        $.getJSON(getUserUrl, function (data) {
            var user = data.user;
            $("#name").val(user.name);
            $("#login_name").val(user.loginName);

            //生日
            var birthday = new Date(user.birthday).Format("yyyy-MM-dd");
            $("#birthday").find("input").val(birthday);
            $("#email").val(user.email);
            $("#phone").val(user.phone);
            //性别
            var sexHtml =                                                                                                                                                                      "";
            if(user.sex == "")
                sexHtml = '<option value="">选择...</option><option>男</option><option>女</option>';
            else if(user.sex == "男")
                sexHtml = '<option value="">选择...</option><option selected>男</option><option>女</option>';
            else if(user.sex == "女")
                sexHtml = '<option value="">选择...</option><option>男</option><option selected>女</option>';
            $("#sex").html(sexHtml);
        })
    }

    $("#submit").click(function () {
        var user = {};
        if(isEdit)
            user.id = userId;
        user.name = $("#name").val();
        user.loginName = $("#login_name").val();
        user.password = $("#password").val();
        user.sex = $("#sex option:selected").text();
        user.birthday = $("#birthday").find("input").val();
        user.email = $("#email").val();
        user.phone = $("#phone").val();
        var userPicture = $("#picture")[0].files[0];
        var formData = new FormData();
        formData.append("userPicture", userPicture);
        formData.append("userStr", JSON.stringify(user));
        // $.ajaxSetup ({
        //     cache:false
        // });
        $.ajax({
            url:isEdit ? editUserUrl : registerUserUrl,
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
                    console.log(data.errMsg)
                    alert("提交失败!" + data.errMsg);
                }
            }
        })
        // window.location.reload();
    })
})