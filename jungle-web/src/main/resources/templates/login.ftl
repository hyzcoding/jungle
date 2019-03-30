<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>登录</title>
        <script src="/static/js/jquery.min.js"></script>
        <script type="text/javascript" src="/static/bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/static/css/commons.css ">
        <script type="application/javascript">
        </script>
    </head>
    <body>
        <form action="/login" method="post">
            邮箱： <input type="text" name="userEml"/>
            密码： <input type="password" name="userPwd">
            <input type="submit" value="提交">
        </form>
    </body>
</html>