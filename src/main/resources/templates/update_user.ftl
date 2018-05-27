<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li><a href="/engineoils">Пользователи</a></li>
            <li class="active">${status} пользователя</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <@spring.bind "userForm"/>
                <form class="" method="post" action="">
                    <div class="form-group">
                        <label>Имя</label>
                        <@spring.formInput "userForm.name" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Логин</label>
                        <@spring.formInput "userForm.login" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <@spring.formInput "userForm.email" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Роль</label>
                        <@spring.bind "roles" />
                        <@spring.formSingleSelect "userForm.role", roles, "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <button type="submit" class="btn primary-btn btn-lg">${status}</button>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>

</#macro>

<@base_template />