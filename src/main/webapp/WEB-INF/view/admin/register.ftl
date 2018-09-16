<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/custom/css/register.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/layui/layui.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <title>凉衫薄</title>
</head>
<body>
<div class="register">
    <h3>用户注册</h3>
    <div class="register-wrap">
        <div class="alert alert-success alert-dismissable hidden">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            <a href="/admin/login" class="alert-link">注册成功,点击前往登陆页面</a>
        </div>
        <div class="alert alert-danger alert-dismissable hidden">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            <span></span>
        </div>
        <form class="layui-form">
            <div class="layui-form-item">
                <input type="email" name="email" id="email" required placeholder="邮箱" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="text" name="nickname" id="nickname" required placeholder="昵称" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="password" name="password" id="password" required placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="password" name="checkedPassword" id="checkedPassword" required placeholder="确认密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <input type="text" name="vcode" id="vcode" required placeholder="验证码" class="layui-input">
                </div>
                <div class="layui-inline">
                    <img src="/api/vcode" class="verifyImg" id="verifyImg"/>
                </div>
            </div>
        </form>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-normal btn-block" id="registerBtn">注册</button>
        </div>
    </div>
    <p class="copyright">copyright &copy; 2018 by 凉衫薄</p>
</div>
<script type="text/javascript" src="/static/custom/js/register.js"></script>
</body>
</html>