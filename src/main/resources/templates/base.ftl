<#import "/spring.ftl" as spring />

<#macro body></#macro>
<#macro head></#macro>

<#macro base_template>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>ITIS oil</title>

    <base href="/">

    <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="css/slick.css">
    <link type="text/css" rel="stylesheet" href="css/slick-theme.css">
    <link type="text/css" rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        :root #header + #content > #left > #rlblock_left {
            display: none !important;
        }
    </style>

    <@head />
</head>

<body>
<header>
    <div id="header">
        <div class="container">
            <div class="pull-left">
                <div class="header-logo">
                    <a class="logo" href="/">ITIS OIL</a>
                </div>
            </div>

            <div class="pull-right">
                <ul class="header-btns">
                    <li class="header-account dropdown default-dropdown">
                        <#if user??>
                        <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
                            <div class="header-btns-icon">
                                <img class="avatar-mini" src="./img/avatar.jpg" alt="">
                            </div>
                            <strong class="text-uppercase">${user.login} <i class="fa fa-caret-down"></i></strong>
                        </div>
                        <a href="/logout" class="text-uppercase">Logout</a>
                        <#else>
                        <a href="/login" class="text-uppercase">Login</a> / <a href="/register" class="text-uppercase">Register</a>
                        </#if>

                        <ul class="custom-menu">
                            <li><a href="#"><i class="fa fa-user-o"></i>Мой аккаунт</a></li>
                            <li><a href="/messages"><i class="fa fa-comment-o"></i>Мои сообщения</a></li>
                            <li><a href="/tickets"><i class="fa  fa-question-circle-o"></i>Мои заявки</a></li>
                        </ul>
                    </li>

                    <li class="nav-toggle">
                        <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<div id="navigation">
    <div class="container">
        <div id="responsive-nav">
            <#if user?? && (user.appRole.name == "ROLE_CASHIER" || user.appRole.name == "ROLE_CONTENTMANAGER" || user.appRole.name == "ROLE_MANAGER")>
            <div class="category-nav show-on-click">
                <span class="category-header">Служебная панель<i class="fa fa-list"></i></span>
                <ul class="category-list">
                    <#switch user.appRole.name>
                        <#case "ROLE_MANAGER">
                            <li><a href="/manager/personnel_management">Управление персоналом</a></li>
                            <li><a href="#">Касса</a></li>
                            <li><a href="/contentmanager/news/add">Добавить новость</a></li>
                            <li><a href="/contentmanager/promotion/add">Добавить акцию</a></li>
                            <li><a href="/contentmanager/engineoil/add">Добавить моторное масло</a></li>
                            <#break>
                        <#case "ROLE_CASHIER">
                            <li><a href="#">Касса</a></li>
                            <#break>
                        <#case "ROLE_CONTENTMANAGER">
                            <li><a href="/contentmanager/news/add">Добавить новость</a></li>
                            <li><a href="/contentmanager/promotion/add">Добавить акцию</a></li>
                            <li><a href="/contentmanager/engineoil/add">Добавить моторное масло</a></li>
                            <#break>
                    </#switch>
                </ul>
            </div>
            </#if>

            <div class="menu-nav">
                <span class="menu-header">Меню<i class="fa fa-bars"></i></span>
                <ul class="menu-list">
                    <li><a href="/promotions">Акции</a></li>
                    <li><a href="/news">Новости</a></li>
                    <li><a href="/engineoils">Моторные масла</a></li>
                    <li><a href="/about">Об АЗС</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<@body />

<footer id="footer" class="section section-grey">
    <div class="container">
        <div class="row">
            <div class="col-md-12" style="text-align: center">
                <div class="footer">
                    <div class="header-logo">
                        <a class="logo" style="margin-right: 0" href="/">ITIS OIL</a>
                    </div>
                    <ul class="footer-social">
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <div class="footer-copyright">
                    <h3>©2018 ITIS OIL</h3>
                </div>
            </div>
        </div>
    </div>
</footer>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>


</body>
</html>

</#macro>