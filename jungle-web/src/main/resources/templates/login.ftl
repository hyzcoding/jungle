<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>登录</title>
        <script src="/static/jquery.min.js"></script>
        <script type="application/javascript">
        </script>
    </head>
    <body>
        <form action="/web/login" method="post">
            邮箱： <input type="text" name="userEml"/>
            密码： <input type="password" name="userPwd">
            <input type="submit" value="提交">
        </form>
    </body>
</html>