<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Управление персоналом</li>
        </ul>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <label for="personnel_find_input">Фильтрация по логину пользователя</label>
            <input id="personnel_find_input" class="input search-input" type="text"
        </div>
    </div>
    <div class="row">
        <table id="table" class="table">
            <caption><h3 style="text-align: center">Таблица пользователей</h3></caption>
            <thead>
            <tr>
                <th>Логин</th>
                <th>Имя</th>
                <th>Емайл</th>
                <th>Роль</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody id="personnel_table_body">
                <#list userlist as user>
                <tr>
                    <td>${user.login}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.appRole.name}</td>
                    <td>
                        <a href="/manager/user/${user.id}/" class="btn primary-btn btn-sm crud_btn">Изменить</a>
                    </td>
                    <td>
                        <form action="/manager/user/${user.id}/delete" method="post">
                            <input type="submit" class="btn primary-btn btn-sm crud_btn" value="Удалить"/>
                        </form>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>

</#macro>

<@base_template />

