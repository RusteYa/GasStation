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
            <form name='form' action="/do-login" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='login' value=''></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password'/></td>
                    </tr>
                    <tr>
                        <td>Remember Me?</td>
                        <td><input type="checkbox" name="remember-me"/></td>
                    </tr>
                    <tr>
                        <td><input name="submit" type="submit" value="submit"/></td>
                    </tr>
                </table>
            <#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
            </form>
        </div>
    </div>
</div>

</#macro>

<@base_template />