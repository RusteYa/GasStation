<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Акции</li>
        </ul>
    </div>
</div>

<div class="container">
    <#if user?? && (user.appRole.name == "ROLE_CONTENTMANAGER" || user.appRole.name == "ROLE_MANAGER")>
        <div class="row" style="padding-bottom: 10px; margin-bottom: 10px; border-bottom: 1px solid #DADADA">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <a href="/contentmanager/promotion/add" class="btn primary-btn btn-lg">Добавить новую акцию</a>
            </div>
            <div class="col-md-4"></div>
        </div>
    </#if>

    <#list promolist as promo>
    <div class="row">
        <div class="promolist clearfix col-md-11">
            <div class="promo_image">
                <a><img src="${promo.photoPath}" height="200" width="260"></a>
            </div>
            <div class="text_content">
                <div class="promo_date">
                    ${promo.date}
                </div>
                <div class="promo_header">
                    <a>
                        ${promo.header}
                    </a>
                </div>
                <div class="promo_body">
                    <p>${promo.body}</p>
                </div>
            </div>
        </div>
        <#if user?? && (user.appRole.name == "ROLE_CONTENTMANAGER" || user.appRole.name == "ROLE_MANAGER")>
            <div class="col-md-1">
                <a href="/contentmanager/promotion/${promo.id}/" class="btn primary-btn btn-sm crud_btn">Изменить</a>
                <form action="/contentmanager/promotion/${promo.id}/delete" method="post">
                    <input type="submit" class="btn primary-btn btn-sm crud_btn" value="Удалить"/>
                </form>
            </div>
        </#if>
    </div>
    </#list>
</div>

</#macro>

<@base_template />
