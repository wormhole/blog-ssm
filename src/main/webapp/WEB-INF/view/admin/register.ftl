<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/custom/admin/css/register.css">
    <script src="/static/layui/layui.js"></script>
    <meta name="keywords" content="${Application.setting.keywords}"/>
    <meta name="description" content="${Application.setting.description}"/>
    <link rel="icon" href="${Application.setting.head}" />
    <title>${Application.setting.title}</title>
</head>
<body>
<div class="register">
    <h3>用户注册</h3>
    <div class="register-wrap">
        <blockquote class="layui-elem-quote hidden"></blockquote>
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
                <input type="password" name="checked-password" id="checked-password" required placeholder="确认密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <input type="text" name="vcode" id="vcode" required placeholder="验证码" class="layui-input">
                </div>
                <div class="layui-inline">
                    <img src="/api/vcode" class="verify-img" id="verify-img"/>
                </div>
            </div>
        </form>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-normal" id="register-btn">注册</button>
        </div>
    </div>
</div>
<footer>${Application.setting.copyright}</footer>
<script type="text/javascript" src="/static/custom/admin/js/register.js"></script>
</body>
</html>