<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/custom/css/article.css"/>
    <link rel="stylesheet" href="/static/open-iconic/font/css/open-iconic-bootstrap.min.css"
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.preview.min.css"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
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
                    </div>
                    <div id="editormd-view" class="content">
                        <textarea style="display:none;">
${article.articleMd}
                        </textarea>
                    </div>
                </div>
            </div>
            <div class="padding">
            </div>
        </div>
    </div>
</div>
<footer>copyright &copy; 2018 by 溢栈</footer>
<script type="text/javascript">
    editormd.markdownToHTML("editormd-view", {});
</script>
</body>
</html>