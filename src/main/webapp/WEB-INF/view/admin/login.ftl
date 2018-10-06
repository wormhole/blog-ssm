<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/custom/css/login.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/layui/layui.js"></script>
    <script src="/static/popper/umd/popper.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <title>栈溢出</title>
</head>
<body>
<div class="login">
    <h3>用户登陆</h3>
    <div class="login-wrap">
        <#if error??>
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                    &times;
                </button>
                <span>${error}</span>
            </div>
        </#if>
        <form class="layui-form" id="loginForm" action="/admin/login" method="post">
            <div class="layui-form-item">
                <input type="email" name="email" id="email" required placeholder="邮箱" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="password" name="password" id="password" required placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <input type="text" name="vcode" id="vcode" required placeholder="验证码" class="layui-input">
                </div>
                <div class="layui-inline">
                    <img src="/api/vcode" class="verifyImg" id="verifyImg"/>
                </div>
            </div>
            <div class="layui-form-item">
                <input name="rememberMe" lay-skin="primary" title="记住我" type="checkbox" value="true">
            </div>
            <div class="layui-form-item">
                <input type="submit" class="layui-btn layui-btn-normal btn-block" id="loginBtn" value="登陆">
            </div>
        </form>
        <a href="/admin/register">注册用户</a>
    </div>
    <p class="copyright">copyright &copy; 2018 by 凉衫薄</p>
</div>
<script type="text/javascript" src="/static/custom/js/login.js"></script>
</body>
</html>