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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>ITIS oil</title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="css/slick.css">
    <link type="text/css" rel="stylesheet" href="css/slick-theme.css">

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="css/nouislider.min.css">

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="css/style.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
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
<!-- HEADER -->
<header>
    <!-- header -->
    <div id="header">
        <div class="container">
            <div class="pull-left">
                <!-- Logo -->
                <div class="header-logo">
                    <a class="logo" href="/">ITIS OIL</a>
                </div>
                <!-- /Logo -->
                <!-- Search -->
                <div class="header-search">
                    <form>
                        <input class="input search-input" type="text" placeholder="Enter your keyword">
                        <select class="input search-categories">
                            <option value="0">All Categories</option>
                            <option value="1">Category 01</option>
                            <option value="1">Category 02</option>
                        </select>
                        <button class="search-btn"><i class="fa fa-search"></i></button>
                    </form>
                </div>
                <!-- /Search -->
            </div>
            <div class="pull-right">
                <ul class="header-btns">
                    <!-- Account -->
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
                            <li><a href="#"><i class="fa fa-comment-o"></i>Мои сообщения</a></li>
                            <li><a href="#"><i class="fa  fa-question-circle-o"></i>Мои запросы</a></li>
                        </ul>
                    </li>
                    <!-- /Account -->

                    <!-- Mobile nav toggle-->
                    <li class="nav-toggle">
                        <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                    </li>
                    <!-- / Mobile nav toggle -->
                </ul>
            </div>
        </div>
        <!-- header -->
    </div>
    <!-- container -->
</header>
<!-- /HEADER -->

<!-- NAVIGATION -->
<div id="navigation">
    <!-- container -->
    <div class="container">
        <div id="responsive-nav">
            <!-- category nav -->
            <div class="category-nav show-on-click">
                <span class="category-header">Служебная панель<i class="fa fa-list"></i></span>
                <ul class="category-list">
                    <li><a href="#">Касса</a></li>
                    <li><a href="#">Редактирование новостей</a></li>
                    <li><a href="#">Редактирование акций</a></li>
                    <li><a href="#">Редактирование продуктов</a></li>
                    <li><a href="#">Управление персоналом</a></li>
                </ul>
            </div>
            <!-- /category nav -->
            <!-- menu nav -->
            <div class="menu-nav">
                <span class="menu-header">Меню<i class="fa fa-bars"></i></span>
                <ul class="menu-list">
                    <li><a href="/products">Акции</a></li>
                    <li><a href="/tickets">Новости</a></li>
                    <li><a href="/messages">Продукты</a></li>
                    <li><a href="/about">Об АЗС</a></li>
                </ul>
            </div>
            <!-- menu nav -->
        </div>
    </div>
    <!-- /container -->
</div>
<!-- /NAVIGATION -->

<@body />

<footer id="footer" class="section section-grey">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- footer widget -->
            <div class="col-md-12">
                <div class="footer">
                    <!-- footer logo -->
                    <div class="header-logo">
                        <a class="logo" href="/">ITIS OIL</a>
                    </div>
                    <!-- /footer logo -->
                    <!-- footer social -->
                    <ul class="footer-social">
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                    </ul>
                    <!-- /footer social -->
                </div>
            </div>
        </div>
        <!-- /row -->
        <hr>
        <!-- row -->
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <!-- footer copyright -->
                <div class="footer-copyright">
                    <h3>©2018 ITIS OIL</h3>
                </div>
                <!-- /footer copyright -->
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</footer>

<!-- jQuery Plugins -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>


</body>
</html>

</#macro>