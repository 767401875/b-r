$(function () {
    var loginUrl = "/usermanage/login";
    $("#submit").click(function () {
        var user = {};
        user.loginName = $("#username").val();
        user.password = $("#password").val();
        var formData = new FormData();
        formData.append("user", JSON.stringify(user));
        $.ajaxSetup ({
            cache:false
        });
        $.ajax({
            url:loginUrl,
            type:"POST",
            data:formData,
            contentType:false,
            processData:false,
            success:function (data) {
                if(data.success){
                    alert("提交成功");
                    window.location.href = '/useradmin/useroperation';
                    // window.location.href = '/usermanage/getuserbyid?userId=3';
                }
                else {
                    alert("提交失败!" + data.errMsg);
                }
            }
        })
    })
})