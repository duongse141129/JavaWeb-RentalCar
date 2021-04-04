<%-- 
    Document   : viewHistory
    Created on : Mar 4, 2021, 11:34:08 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VIEW HISTORY JSP Page</title>
    </head>
    <body>
        
         <h1>WELCOME ${sessionScope.LOGIN_USER.fullName}</h1>
         <a href="SearchController" >shopping page</a></br>
         <form action="SearchHistoryController">
             <input type="text" name="txtSearchHistory" >
             <input type="date" name="dateSearch" value="${requestScope.ORDER_DATE}">
             <input type="submit" value="search">
         </form>
         <c:if test="${requestScope.MESSAGE !=null && not empty requestScope.MESSAGE}">
            <h5>${requestScope.MESSAGE}</h5>
        </c:if>
         <table border="1">
             <thead>
                 <tr>
                     <th>Car Name</th>
                     <th>Amount</th>
                     <th>Price</th>
                     <th>Rent Date</th>
                     <th>Return Date</th>
                     <th>Cancel</th>
                     <th>Point</th>
                     <th>Rate</th>
                 </tr>
             </thead>
             <c:if test="${requestScope.LIST_HISTORY!=null}">
                 <c:forEach var="detailDto" varStatus="counter" items="${requestScope.LIST_HISTORY}">
                     
                         <tbody>
                             <tr>
                         <form action="RateController" method="POST">
                             <input type="hidden" name="detailID" value="${detailDto.detailID}">
                             <input type="hidden" name="orderID" value="${detailDto.orderID}">
                             <input type="hidden" name="carID" value="${detailDto.carID}">
                             <td>${detailDto.carName}</td>
                             <td>${detailDto.amount}</td>
                             <td>${detailDto.price}</td>
                             <td>${detailDto.rentDate}</td>
                             <td>${detailDto.returnDate}</td>
                             <td>
                                 <c:if test="${detailDto.statusCancel=='true'}">
                                     <a href="CancelController?txtDetailID=${detailDto.detailID}&orderID=${detailDto.orderID}" onclick="return confirm('Are you sure you want to delete this detail?');">Cancel</a>
                                 </c:if>
                             </td>
                             <c:if test="${detailDto.statusRate=='true'}">
                                 <td>
                                     <input type="number" name="txtRate" value="${detailDto.rate}" step="1" min="1" max="10">
                                 </td>
                                 <td>
                                     <input type="submit" value="Rate">
                                 </td>
                             </c:if>
                         </form>
                             </tr>
                         </tbody>
                     
                 </c:forEach>
             </c:if>
         </table>

    </body>
</html>
