<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>编辑文章</title>
        <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrap.min.css ">
        <link rel="stylesheet" type="text/css" href="/static/css/commons.css ">
        <style type="text/css">
            .wangEditor {
                border: 1px solid #ccc;
                width: 100%;
            }
            .toolbar {
                border: 1px solid #ccc;
            }

            .text {
                border: 1px solid #ccc;
                height: 600px;
            }
        </style>
    </head>
    <body>
        <#include "nav.ftl">
        <div class="contenter">
            <div class="col-lg-2">

            </div>
            <div class="col-lg-8 ">
                <div>
                    标题：
                    <input id="title" type="text"/>
                </div>
                <div class="wangEditor">
                    <div id="div1" class="toolbar">
                    </div>
                    <div id="div2" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
                        <p>请输入内容</p>
                    </div>
                </div>
                <button type="button" class="btn btn-default btn-danger center-block"><span
                            onclick="upload()"  class="glyphicon glyphicon-pencil">&nbsp;提交</span></button>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <script type="text/javascript" src="/static/js/jquery.min.js"></script>
        <script type="text/javascript" src="/static/js/wangEditor.min.js"></script>
        <script type="text/javascript">
            var E = window.wangEditor;
            var editor = new E('#div1', '#div2'); // 两个参数也可以传入 elem 对象，class 选择器

            // 自定义菜单配置
            // editor.customConfig.menus = [
            //     'head',
            //     'bold',
            //     'italic',
            //     'underline',
            //     'image'
            // ]
            editor.customConfig.showLinkImg = false;
            editor.customConfig.uploadImgShowBase64 = true;
            editor.customConfig.zIndex = 100;
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
        <script type="text/javascript" src="/static/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>