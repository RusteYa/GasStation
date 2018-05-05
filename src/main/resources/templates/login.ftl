<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Логин</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form name='form' action="/do-login" method='POST'>
                    <div class="form-group">
                        <label for="login1">Логин</label>
                        <input type="text" class="form-control form-control-lg" name='login' id="login1">
                    </div>
                    <div class="form-group">
                        <label for="password1">Пароль</label>
                        <input type="password" class="form-control form-control-lg" name='password' id="password1">
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input form-control-lg" name="remember-me" id="сheck1">
                        <label class="form-check-label" for="сheck1">Запомнить меня</label>
                    </div>
                <#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <button type="submit" class="btn btn-primary btn-lg">Войти</button>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</div>

</#macro>

<@base_template />