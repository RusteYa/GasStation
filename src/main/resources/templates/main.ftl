<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>

<div id="home">
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <table class="shopping-cart-table table">
                    <caption><h3>Топливо</h3></caption>
                    <tbody>
                    <#list fuellist as fuel>
                        <tr>
                            <th class="empty" colspan="3"></th>
                            <th>${fuel.name}</th>
                            <th colspan="2" class="total">${fuel.price}₽</th>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

            <div class="col-md-10">
                <div class="home-wrap">
                    <div id="home-slick">
                        <div class="banner banner-1">
                            <img src="./img/banner1.jpg" alt="">
                            <div class="banner-caption text-center">
                            </div>
                        </div>
                        <div class="banner banner-1">
                            <img src="./img/banner2.jpg" alt="">
                            <div class="banner-caption">
                            </div>
                        </div>
                        <div class="banner banner-1">
                            <img src="./img/banner3.jpg" alt="">
                            <div class="banner-caption">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</#macro>

<@base_template />
