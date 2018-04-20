<#include "base.ftl">
<#import "/spring.ftl" as spring />

<#macro body>

<div id="home">
    <div class="container">
        <div class="row">
            <div class="home-wrap">
                <div id="home-slick">
                    <div class="banner banner-1">
                        <img src="./img/banner1.jpg" alt="">
                        <div class="banner-caption text-center">
                        <#--<button class="primary-btn"></button>-->
                        </div>
                    </div>
                    <div class="banner banner-1">
                        <img src="./img/banner2.jpg" alt="">
                        <div class="banner-caption">
                        <#--<button class="primary-btn"></button>-->
                        </div>
                    </div>
                    <div class="banner banner-1">
                        <img src="./img/banner3.jpg" alt="">
                        <div class="banner-caption">
                        <#--<button class="primary-btn"></button>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</#macro>

<@base_template />
