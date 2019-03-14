<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>login</title>
        <script src="/static/jquery.min.js"></script>
        <script type="application/javascript">
            $(document).ready(function () {
                $('#submit').click( function () {
                    $.ajax({
                        url: "http://localhost/user/login",
                        type: "POST",
                        data: JSON.stringify($('form').serializeObject()),
                        contentType: "application/json",  //缺失会出现URL编码，无法转成json对象
                        success: function (e) {
                            var last=JSON.stringify(e);
                            alert(last);
                        }
                    });
                });
                /**
                 * 自动将form表单封装成json对象
                 */
                $.fn.serializeObject = function() {
                    var o = {};
                    var a = this.serializeArray();
                    $.each(a, function() {
                        if (o[this.name]) {
                            if (!o[this.name].push) {
                                o[this.name] = [ o[this.name] ];
                            }
                            o[this.name].push(this.value || '');
                        } else {
                            o[this.name] = this.value || '';
                        }
                    });
                    return o;
                };
            })
        </script>
    </head>
    <body>
        <form action="#">
            邮箱： <input type="text" name="userEml"/>
            密码： <input type="password" name="userPwd">
            <button type="button" id="submit">提交</button>
        </form>

        <h1>${result.code}</h1>
        <h1>${result.message}</h1>
    </body>
</html>