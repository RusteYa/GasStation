<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Мои заявки</li>
        </ul>
    </div>
</div>

<div class="container">
    <div class="row" style="margin-bottom: 10px; ">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <a href="/tickets/add" class="btn primary-btn btn-lg">Создать заявку</a>
        </div>
        <div class="col-md-4"></div>
    </div>

    <div class="row">
        <table id="table" class="table" style="table-layout: fixed">
            <caption><h3 style="text-align: center">Мои заявки</h3></caption>
            <thead>
            <tr>
                <th>Заголовок</th>
                <th>Текст запроса</th>
                <th>Текст ответа</th>
                <th>Статус</th>
                <th>Дата создания</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <#list ticketlist as ticket>
                <tr>
                    <td>${ticket.header}</td>
                    <td>${ticket.request}</td>
                    <#if ticket.response??>
                        <td>${ticket.response}</td>
                    <#else>
                        <td>Нет ответа</td>
                    </#if>
                    <td>${ticket.ticketStatus.name}</td>
                    <td>${ticket.date}</td>
                    <#if user.appRole.name == "ROLE_MANAGER">
                        <td>
                            <a href="/manager/ticket/${ticket.id}/" style="width: 120px" class="btn primary-btn btn-sm crud_btn">Рассмотреть</a>
                        </td>
                    </#if>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>

</#macro>

<@base_template />
