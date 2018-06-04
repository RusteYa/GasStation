<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li><a href="/tickets">Мои заявки</a></li>
            <li class="active">Рассматрение заявки</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <@spring.bind "ticketResponseForm"/>
                <form method="post" action="">
                    <div class="form-group">
                        <label>Статус</label>
                        <@spring.bind "statuses" />
                        <@spring.formSingleSelect "ticketResponseForm.status", statuses, "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Ответ на заявку</label>
                        <@spring.formTextarea "ticketResponseForm.response" "class='form-control form-control-lg' rows=7"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <button type="submit" class="btn primary-btn btn-lg">Подтвердить</button>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>

</#macro>

<@base_template />