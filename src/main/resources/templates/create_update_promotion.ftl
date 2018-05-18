<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li><a href="/promotions">Акции</a></li>
            <li class="active">${status} акцию</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <@spring.bind "promotionForm"/>
                <form class="" method="post" action="" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Заголовок</label>
                        <@spring.formInput "promotionForm.header" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Новость</label>
                        <@spring.formTextarea "promotionForm.body" "class='form-control form-control-lg' rows=7"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Фото</label>
                        <input id="fileDatas" type="file" class="form-control-file" name="fileDatas" multiple/>
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