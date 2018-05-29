<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li><a href="/messages">Мои сообщения</a></li>
            <li class="active">Отправить сообщение</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <@spring.bind "messageForm"/>
                <form method="post" action="">
                    <div class="form-group">
                        <label>Логин получателя</label>
                        <@spring.formInput "messageForm.recipientLogin" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Заголовок</label>
                        <@spring.formInput "messageForm.header" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Сообщение</label>
                        <@spring.formTextarea "messageForm.body" "class='form-control form-control-lg' rows=7"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <button type="submit" class="btn primary-btn btn-lg">Отправить</button>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>

</#macro>

<@base_template />