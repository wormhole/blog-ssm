<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/custom/css/admin.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/layui/layui.js"></script>
    <title>栈溢出</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <div class="layui-header">
        <div class="layui-logo">StackOverflow.xyz</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="layui-icon layui-icon-notice"></i>&nbsp;&nbsp;消息</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${Session.user.headurl}" class="layui-nav-img layui-circle">
                ${Session.user.nickname}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" data-url="/admin/user/personal" data-title="个人信息"
                           data-id="personal">个人信息</a></dd>
                    <dd><a href="/admin/logout">注销</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon layui-icon-template-1"></i>&nbsp;&nbsp;文章</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="" data-title="所有文章" data-id="">所有文章</a></dd>
                        <dd><a href="javascript:;" data-url="/admin/article/write" data-title="写文章"
                               data-id="write">写文章</a></dd>
                        <dd><a href="javascript:;" data-url="" data-title="分类" data-id="">分类</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon layui-icon-reply-fill"></i>&nbsp;&nbsp;评论</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="" data-title="所有评论" data-id="">所有评论</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon layui-icon-group"></i>&nbsp;&nbsp;用户</a>
                    <dl class="layui-nav-child">
                        <@shiro.hasPermission name="system:user:view">
                            <dd><a href="javascript:;" data-url="" data-title="所有用户" data-id="">所有用户</a></dd>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="system:user:add">
                            <dd><a href="javascript:;" data-url="" data-title="添加用户" data-id="">添加用户</a></dd>
                        </@shiro.hasPermission>
                        <dd><a href="javascript:;" data-url="/admin/user/personal" data-title="个人中心" data-id="personal">个人信息</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon layui-icon-picture"></i>&nbsp;&nbsp;媒体</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="" data-title="图片管理" data-id="">图片管理</a></dd>
                        <dd><a href="javascript:;" data-url="" data-title="音乐管理" data-id="">音乐管理</a></dd>
                        <dd><a href="javascript:;" data-url="" data-title="视频管理" data-id="">视频管理</a></dd>
                    </dl>
                </li>
                <@shiro.hasPermission name="system:setting">
                    <li class="layui-nav-item">
                        <a href="javascript:;"><i class="layui-icon layui-icon-set-fill"></i>&nbsp;&nbsp;设置</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" data-url="" data-title="常规设置" data-id="">常规设置</a></dd>
                            <dd><a href="javascript:;" data-url="" data-title="SEO设置" data-id="">SEO设置</a></dd>
                        </dl>
                    </li>
                </@shiro.hasPermission>
            </ul>
        </div>
    </div>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div class="layui-tab layui-tab-brief" lay-filter="tabs" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this"><span class="layui-icon layui-icon-home"></span>&nbsp;&nbsp;首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <!--<iframe src="/admin/welcome" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>-->
                </div>
            </div>
        </div>
    </div>

    <!-- 底部固定区域 -->
    <div class="layui-footer">
        <center>copyright &copy; 2018 by 凉衫薄</center>
    </div>
</div>
<script type="text/javascript" src="/static/custom/js/admin.js"></script>
</body>
</html>