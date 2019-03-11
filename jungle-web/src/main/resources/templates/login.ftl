<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>login</title>
        <script type="application/javascript">
            $(':submit').onclick('click',function () {
                $.ajax({
                    url:"http://localhost/user/login",
                    type:"POST",
                    data:JSON.stringify($('form').serializeObject()),
                    contentType:"application/json",  //缺失会出现URL编码，无法转成json对象
                    success:function(){
                        alert("成功");
                    }
                });
            });
        </script>
    </head>
    <body>
        <form action="http://localhost/user/login" con method="post">
            邮箱： <input type="text"/>
            密码： <input type="password">
            <input type="submit" value="提交">
        </form>
    </body>
</html>