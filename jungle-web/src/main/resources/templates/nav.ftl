<!-- 导航 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <!-- Login -->
        <form class="navbar-form navbar-left aw-btn-nav" role="search">
            <div class="navbar-header">
                <a class="navbar-brand" href="">JUNGLE论坛</a>
            </div>
        </form>
        <!-- ENDLogin -->

        <!-- 搜索框 -->
        <div class="navbar-left navbar-form hidden-xs hidden-sm">
                <input class="form-control search-query" type="text"
                       placeholder="搜索问题、话题或人" autocomplete="off" name="s" id="aw-search-query">
        </div>
        <!-- END搜索框 -->
        <div class="navbar-form navbar-left">
            <a href="">
                <button type="submit" class="btn btn-default btn-warning"><span
                            class="glyphicon glyphicon-th-list">&nbsp;发现话题</span></button>
            </a>
            <a href="">
                <button type="button" class="btn btn-default btn-danger "><span
                            class="glyphicon glyphicon-pencil">&nbsp;提起新话题</span></button>
            </a>
        </div>

        <!-- 登录注册按钮 -->
        <div class="nav navbar-nav navbar-right">
            <!-- 登陆&注册栏 -->
            <!-- 		<span>
                        <a class="register btn btn-normal btn-success" href="regist.jsp"><span class="glyphicon glyphicon-user">&nbsp;注册</span></a>
                        <a class="login btn btn-normal btn-primary" href="login.jsp"><span class="glyphicon glyphicon-log-in">&nbsp;登录</span></a>
                    </span>


            </div>  -->
            <!-- end 登陆&注册栏 -->
            <form class="navbar-form navbar-right" role="search">
                <div class="btn-group">
                    <#if Session["token"]?exists >
                        <div class="btn-group">
                            <#--<div class="navbar-left"><img alt="" style="width: 40px;height: 35px;"-->
                                                          <#--src=""></div>-->

                            <button type="button" class="btn btn-default btn btn-primary">
                                <span>欢迎&nbsp;</span></button>
                            <button type="button" class="btn btn-default dropdown-toggle btn btn-info"
                                    data-toggle="dropdown">
                                <span class="caret"></span>
                                <span class="sr-only">切换下拉菜单</span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="">个人中心</a></li>
                                <li><a href="">我的消息<span class="badge badge-important" id="message"></span></a>
                                </li>
                                <li><a href="">我的粉丝</a></li>
                                <li class="divider"></li>
                                <li><a href="">退出登录</a></li>
                            </ul>
                        </div>
                    <#else>
                        <a href="/login">
                            <button type="button" class="btn btn-default  btn-primary">
                                <span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
                            </button>
                        </a>
                        <a href="/register">
                            <button type="button" class="register btn btn-normal btn-success">
                                <span class="glyphicon glyphicon-user"></span>&nbsp;注册
                            </button>
                        </a>
                    </#if>
                </div>
            </form>
        </div>
</nav>
<!-- END导航 -->