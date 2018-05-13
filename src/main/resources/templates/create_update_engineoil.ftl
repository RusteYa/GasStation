<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li><a href="/engineoils">Моторные масла</a></li>
            <li class="active">${status}</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <@spring.bind "engineOilForm"/>
                <form class="" method="post" action="" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Название</label>
                        <@spring.formInput "engineOilForm.name" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Компания-производитель</label>
                        <@spring.formInput "engineOilForm.manafacturer" "class='form-control form-control-lg'"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label for="price">Цена</label>
                        <input id="price" type="number" class="form-control form-control-lg" name="price" value="${engineOilForm.price}"/>
                        <@spring.showErrors  '<br>', "error"/>
                    </div>
                    <div class="form-group">
                        <label>Фото</label>
                        <input id="fileDatas" type="file" class="form-control-file" name="fileDatas" multiple/>
                    </div>
                    <button type="submit" class="btn primary-btn btn-lg">Добавить</button>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>

</#macro>

<@base_template />