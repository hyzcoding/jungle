<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>编辑文章</title>
        <style type="text/css">
            .wangEditor{
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
        <div class="wangEditor">
            <div id="div1" class="toolbar">
            </div>
            <div id="div2" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
                <p>请输入内容</p>
            </div>
            <button onclick="upload()"></button>
        </div>

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
                $.ajax({
                    type: "POST",
                    url: "/article/upload",
                    data: editor.txt.html() ,
                    dataType: "json",
                    success: function(data){
                       alert("success");
                });
            }
        </script>
        <#include "foot.ftl">
    </body>
</html>