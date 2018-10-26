<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/custom/css/article.css"/>
    <link rel="stylesheet" href="/static/open-iconic/font/css/open-iconic-bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.preview.min.css"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/custom/css/like.css"/>
    <link rel="stylesheet" href="/static/custom/css/comment.css"/>
    <script src="/static/jquery/jquery.min.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/editor.md/lib/marked.min.js"></script>
    <script src="/static/editor.md/lib/prettify.min.js"></script>
    <script src="/static/editor.md/editormd.min.js"></script>
    <title>溢栈</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="left">
                <img src="${user.headUrl}" id="head" class="rounded-circle">
                <div class="nickname" id="nickname">${user.nickname}</div>
                <hr/>
                <div class="signature" id="signature">${user.signature}</div>
                <div class="menu">
                    <a class="item select btn" href="/">
                        首页
                    </a>
                    <a class="item btn" href="/category">
                        分类
                    </a>
                    <a class="item btn" href="http://wpa.qq.com/msgrd?v=3&uin=363408268&site=qq&menu=yes">
                        与我聊聊
                    </a>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="middle">
                <div class="article">
                    <div class="title">${article.title}</div>
                    <div class="info">
                        <div>
                            <span class="oi oi-calendar" aria-hidden="true"></span>
                            <span id="date">${article.createDateString}</span>
                        </div>
                        <div>
                            <span class="oi oi-person" aria-hidden="true"></span>
                            <span id="author">${article.nickname}</span>
                        </div>
                        <div>
                            <span class="oi oi-tags" aria-hidden="true"></span>
                            <span id="category">${article.categoryName}</span>
                        </div>
                        <div>
                            <span class="oi oi-eye" aria-hidden="true"></span>
                            <span id="hits">${article.hits}</span>
                        </div>
                        <div>
                            <span class="oi oi-thumb-up" aria-hidden="true"></span>
                            <span id="likes">${article.likes}</span>
                        </div>
                    </div>
                    <div id="editormd-view" class="content">
                        <textarea style="display:none;">
${article.articleMd}
                        </textarea>
                    </div>
                </div>
                <div class="like">
                    <div class="heart" <#if (isLike == true)>style="background-position:right"
                         <#else>style="background-position:left"</#if> rel="${isLike?string("true","false")}"></div>
                </div>
                <div class="comment">
                    <div class="comment-title">
                        所有评论:
                    </div>
                    <div class="comment-list">
                        <div class="comment-item row">
                            <div class="comment-item-left">
                                <img src="/static/custom/image/default.jpeg" class="rounded-circle img-fluid">
                            </div>
                            <div class="comment-item-right">
                                <div class="comment-item-info">
                                    <span>
                                        <a href="javascript:;" class="name">凉衫薄:</a>
                                    </span>
                                    <span class="time">2018-10-25 11:56:07</span>
                                    <span>
                                        <a href="javascript:;" class="reply">回复</a>
                                    </span>
                                </div>
                                <hr>
                                <div class="comment-content">
                                    <a href="#">@凉衫薄</a>测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字
                                </div>
                            </div>
                        </div>
                        <div class="comment-item row">
                            <div class="comment-item-left">
                                <img src="/static/custom/image/default.jpeg" class="rounded-circle img-fluid">
                            </div>
                            <div class="comment-item-right">
                                <div class="comment-item-info">
                                    <span>
                                        <a href="javascript:;" class="name">凉衫薄:</a>
                                    </span>
                                    <span class="time">2018-10-25 11:56:07</span>
                                    <span>
                                        <a href="javascript:;" class="reply">回复</a>
                                    </span>
                                </div>
                                <hr>
                                <div class="comment-content">
                                    测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="comment-reply">
                        <span>
                            <a href="javascript:;" class="reply-to">
                                回复 凉衫薄:
                            </a>
                        </span>
                        <span>
                            <a href="javascript:;" class="cancel">
                                取消
                            </a>
                        </span>
                    </div>
                    <div class="comment-input">
                        <textarea class="comment-text form-control" placeholder="请开始你的表演..."></textarea>
                    </div>
                    <div class="comment-info">
                        <div class="row">
                            <div class="col-sm-3">
                                <input class="form-control" placeholder="昵称">
                            </div>
                            <div class="col-sm-3">
                                <input class="form-control" placeholder="邮箱">
                            </div>
                            <div class="col-sm-3">
                                <input class="form-control" placeholder="个人网址">
                            </div>
                            <div class="col-sm-3">
                                <input type="button" class="btn btn-info" value="评论">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="padding">
            </div>
        </div>
    </div>
</div>
<footer>copyright &copy; 2018 by 溢栈</footer>
<script src="/static/custom/js/article.js"></script>
</body>
</html>