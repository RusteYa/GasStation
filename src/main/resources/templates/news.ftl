<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Новости</li>
        </ul>
    </div>
</div>

<div class="container">
    <#if user?? && (user.appRole.name == "ROLE_CONTENTMANAGER" || user.appRole.name == "ROLE_MANAGER")>
        <div class="row" style="padding-bottom: 10px; margin-bottom: 10px; border-bottom: 1px solid #DADADA">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <a href="/contentmanager/news/add" class="btn primary-btn btn-lg">Добавить новую новость</a>
            </div>
            <div class="col-md-4"></div>
        </div>
    </#if>

    <#list newslist as news>
    <div class="row">
        <div class="newslist clearfix col-md-11">
            <div class="news_image">
                <a><img src="${news.photoPath}" height="200" width="260"></a>
            </div>
            <div class="text_content">
                <div class="news_date">
                    ${news.date}
                </div>
                <div class="news_header">
                    <a>
                        ${news.header}
                    </a>
                </div>
                <div class="news_body">
                    <p>${news.body}</p>
                </div>
            </div>
        </div>
        <#if user?? && (user.appRole.name == "ROLE_CONTENTMANAGER" || user.appRole.name == "ROLE_MANAGER")>
            <div class="col-md-1">
                <a href="/contentmanager/news/${news.id}/" class="btn primary-btn btn-sm crud_btn">Изменить</a>
                <form action="/contentmanager/news/${news.id}/delete" method="post">
                    <input type="submit" class="btn primary-btn btn-sm crud_btn" value="Удалить"/>
                </form>
            </div>
        </#if>
    </div>
    </#list>
</div>

</#macro>

<@base_template />
