<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/custom/css/personal.css">
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/layui/layui.js"></script>
    <title>凉衫薄</title>
</head>
<body>
<div class="layui-card" id="baseConf">
    <div class="layui-card-header">基本信息</div>
    <div class="layui-card-body">
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input name="email" class="layui-input" type="email" required id="email" value="${Session.user.email}">
            </div>
            <div class="layui-form-mid error-color hidden" id="email-error"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-inline">
                <input name="nickname" class="layui-input" type="text" required id="nickname" value="${Session.user.nickname}">
            </div>
            <div class="layui-form-mid error-color hidden" id="nickname-error"></div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" id="saveBaseBtn">保存修改</button>
            </div>
        </div>
    </div>
</div>
<div class="layui-card" id="pwdConf">
    <div class="layui-card-header">修改密码</div>
    <div class="layui-card-body">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">旧密码</label>
                <div class="layui-input-inline">
                    <input name="oldPassword" class="layui-input" type="password" required id="oldPassword">
                </div>
                <div class="layui-form-mid error-color hidden"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                    <input name="newPassword" class="layui-input" type="password" required id="newPassword">
                </div>
                <div class="layui-form-mid error-color hidden"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input name="checkedPassword" class="layui-input" type="password" required id="checkedPassword">
                </div>
                <div class="layui-form-mid error-color hidden"></div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" id="savePwdBtn">保存密码</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-card" id="headConf">
    <div class="layui-card-header">头像设置</div>
    <div class="layui-card-body">
        <div class="layui-row">
            <img src="${Session.user.headurl}" class="layui-circle" id="headImg">
        </div>
        <input type="file" name="headImg" class="hidden">
        <div class="layui-row">
            <button class="layui-btn layui-btn-normal layui-btn-lg layui-show-lg-block" id="saveHeadBtn">
                保存头像
            </button>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="/static/custom/js/personal.js"></script>
</body>
</html>