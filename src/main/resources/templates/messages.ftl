<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Мои сообщения</li>
        </ul>
    </div>
</div>

<div class="container">
    <div class="row" style="margin-bottom: 10px; ">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <a href="/messages/send" class="btn primary-btn btn-lg">Отправить сообщение</a>
        </div>
        <div class="col-md-4"></div>
    </div>

    <div class="row">
        <table id="table" class="table" style="table-layout: fixed">
            <caption><h3 style="text-align: center">Полученные сообщения</h3></caption>
            <thead>
            <tr>
                <th>Имя автора</th>
                <th>Логин автора</th>
                <th>Заголовок сообщения</th>
                <th>Сообщение</th>
            </tr>
            </thead>
            <tbody>
                <#list user.receivedMessages as message>
                <tr>
                    <#if user.appRole.level < message.messageSender.appRole.level>
                        <td>***</td>
                    <#else>
                        <td>${message.messageSender.name}</td>
                    </#if>
                    <td>${message.messageSender.login}</td>
                    <td>${message.header}</td>
                    <td style="word-wrap: break-word">${message.body}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <div class="row">
        <table id="table" class="table" style="table-layout: fixed">
            <caption><h3 style="text-align: center">Отправленные сообщения</h3></caption>
            <thead>
            <tr>
                <th>Имя получателя</th>
                <th>Логин получателя</th>
                <th>Заголовок сообщения</th>
                <th>Сообщение</th>
            </tr>
            </thead>
            <tbody>
                <#list user.sentMessages as message>
                <tr>
                    <#if user.appRole.level < message.messageReceiver.appRole.level>
                        <td>***</td>
                    <#else>
                        <td>${message.messageReceiver.name}</td>
                    </#if>
                    <td>${message.messageReceiver.login}</td>
                    <td>${message.header}</td>
                    <td style="word-wrap: break-word">${message.body}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>

</#macro>

<@base_template />
