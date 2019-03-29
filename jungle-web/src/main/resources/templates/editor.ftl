<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>编辑文章</title>
        <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrap.min.css ">
        <style type="text/css">
            .wangEditor {
                border: 1px solid #ccc;
                width: 400px;
            }
            .toolbar {
                border: 1px solid #ccc;
            }

            .text {
                border: 1px solid #ccc;
                height: 400px;
            }
        </style>
    </head>
    <body>
        <#include "nav.ftl">
        <input id="title" type="text"/>
        <div class="wangEditor">
            <div id="div1" class="toolbar">
            </div>
            <div id="div2" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
                <p>请输入内容</p>
            </div>
        </div>
        <button onclick="upload()">提交</button>
        <script type="text/javascript" src="/static/js/jquery.min.js"></script>
        <script type="text/javascript" src="/static/js/wangEditor.min.js"></script>
        <script type="text/javascript">
            var E = window.wangEditor
            var editor = new E('#div1', '#div2')  // 两个参数也可以传入 elem 对象，class 选择器

            // 自定义菜单配置
            editor.customConfig.menus = [
                'head',
                'bold',
                'italic',
                'underline',
                'image'
            ]
            editor.customConfig.showLinkImg = false
            editor.customConfig.uploadImgShowBase64 = true
            editor.create()

            function upload() {
                var formData = new FormData();
                var file = new Blob([editor.txt.html()]);
                formData.append("file", file);
                $.ajax({
                    headers: {
                        "Authorization": '${Session["token"]!""}'
                    },
                    type: "POST",
                    url: "/article/upload",
                    async: 'false',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        alert(data.toString());
                    },
                });
            }
        </script>
        <#include "foot.ftl">
    </body>
</html>