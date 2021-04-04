<%-- 
    Document   : login
    Created on : Oct 17, 2020, 3:55:38 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   
    </head>
    <body>       

            <h1>Login </h1>
            <form action="LoginController" method="POST" >
                Email:  <input type="email" name="txtEmail" /></br>
                Password: <input type="password" name="txtPassword" /></br>
                <div class="g-recaptcha"
                data-sitekey="6LelZAsTAAAAAAv1ADYDnq8AzbmPmbMvjh-xhfgB"></div></br>
                <input type="submit" name="btnAction" value="Login" />
            </form>
            <c:if test="${sessionScope.MESSAGE !=null && not empty sessionScope.MESSAGE}">
                <h5>${sessionScope.MESSAGE}</h5>
            </c:if>
            <a href="RegistrationController" >Register</a></br>  
            <a href="SearchController" >shopping page</a></br>
    </body>
</html>
