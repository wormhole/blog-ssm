<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/open-iconic/font/css/open-iconic-bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/custom/css/index.css"/>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
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
                <div class="header">
                    最新文章
                </div>
                <div class="body">
                    <#list articleList as article>
                        <div class="article">
                            <div class="title"><a href="${article.url}">${article.title}</a></div>
                            <div class="content">${article.preview}</div>
                            <div class="footer">
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
                        </div>
                    </#list>
                </div>
            </div>
            <div class="buttom">
                <ul class="pagination justify-content-center">
                    <#if (page > 1)>
                        <li class="page-item"><a class="page-link" href="/?page=${page-1}">上一页</a></li>
                    <#else>
                        <li class="page-item disabled"><a class="page-link"">上一页</a></li>
                    </#if>
                    <#list start..end as i>
                        <li class="page-item  <#if (i == page)>active</#if>"><a class="page-link"
                                                                                href="/?page=${i}">${i}</a></li>
                    </#list>
                    <#if (page < pageCount)>
                        <li class="page-item"><a class="page-link" href="/?page=${page+1}">下一页</a></li>
                    <#else>
                        <li class="page-item disabled"><a class="page-link"">下一页</a></li>
                    </#if>
                </ul>
            </div>
            <div class="padding">
            </div>
        </div>
    </div>
</div>
<footer>copyright &copy; 2018 by 溢栈</footer>
</body>
</html>