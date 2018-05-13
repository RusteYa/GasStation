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
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <@spring.bind "registrationForm"/>
                <form class="" method="post" action="/register">
                    <div class="form-group">
                        <label>Логин</label>
                        <@spring.formInput "registrationForm.login" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <@spring.formInput "registrationForm.email" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <@spring.formPasswordInput "registrationForm.password" "class='form-control form-control-lg'"/>
                        <@spring.showErrors "<br>", "error"/>
                    </div>
                    <div class="form-group">
                        <label>Подтверждение пароля</label>
                        <@spring.formPasswordInput "registrationForm.confirmPassword" "class='form-control form-control-lg'"/>
                        <@spring.showErrors "<br>", "error"/>
                    </div>
                    <button type="submit" class="btn primary-btn btn-lg">Зарегистрироваться</button>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</div>
</#macro>

<@base_template />