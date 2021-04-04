<%-- 
    Document   : verify
    Created on : Mar 7, 2021, 4:45:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VERIFY JSP Page</title>
    </head>
    <body>
        <a href="LogoutController">login</a>
        <h1>VERIFY</h1>
        <h1>Please check email</h1>
        <c:if test="${requestScope.MESSAGE !=null && not empty requestScope.MESSAGE}">
            <h5>${requestScope.MESSAGE}</h5>
        </c:if>    
        <form action="VerifyController" method="POST">
            <input type="hidden" name="email" value="${sessionScope.EMAIL_REGISTER}">
            <input type="text" name="txtCodeVerify" >
            <input type="submit" name="btnAction" value="sendAgain">
            <input type="submit" name="btnAction" value="verify">
        </form>
    </body>
</html>
