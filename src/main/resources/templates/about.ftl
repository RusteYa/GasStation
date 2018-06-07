<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Домой</a></li>
            <li class="active">Об АЗС</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <h2>ITIS OIL — одна из крупнейших нефтегазовых компаний в КФУ. У нас улчшее качество топлива и самые дешевые цены!</h2>
            <div class="col-md-2"></div>
        </div>
    </div>
</div>

</#macro>

<@base_template />