<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>编辑文章</title>
        <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrap.min.css ">
        <style type="text/css">
        </style>
    </head>
    <body>
        <#include "nav.ftl">
        <input id="title" type="text"/>
        <div id="article">
        </div>
        <button type="button" onclick="getarticle()"></button>
        <#include "foot.ftl">
        <script type="text/javascript" src="/static/js/jquery.min.js"></script>
        <script type="text/javascript" src="/static/js/wangEditor.min.js"></script>
        <script type="text/javascript">
            function getarticle() {
                $.ajax({
                    type: "GET",
                    url: "/article",
                    async: 'false',
                    success: function (data) {
                        $('#article').html(data);
                    },
                });
            }
        </script>
    </body>
</html>