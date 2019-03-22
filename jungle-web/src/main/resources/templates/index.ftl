<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>首页</title>
    </head>
    <body>
        <shiro:hasRole name="ADMIN">欢迎
            [<shiro:principal property="userEml"/>]
        </shiro:hasRole>
    </body>
</html>