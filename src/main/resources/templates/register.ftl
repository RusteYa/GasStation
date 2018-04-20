<#include "base.ftl">


<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Регистрация</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="modal-body">
                <@spring.bind "registrationForm"/>
                <form class="" method="post" action="/register">
                    <div class="form-group">
                        <label>Логин</label>
                        <@spring.formInput "registrationForm.login" />
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <@spring.formInput "registrationForm.email" />
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <@spring.formInput "registrationForm.password" />
                        <@spring.showErrors "<br>", "error"/>
                    </div>
                    <div class="form-group">
                        <label>Подтверждение пароля</label>
                        <@spring.formInput "registrationForm.confirmPassword" />
                        <@spring.showErrors "<br>", "error"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                </form>
            </div>
        </div>
    </div>
</#macro>

<@base_template />