<%-- 
    Document   : viewOrder
    Created on : Mar 2, 2021, 1:00:41 PM
    Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VIEW ORDER JSP Page</title>
    </head>
    <body>
        <h1>WELCOME ${sessionScope.LOGIN_USER.fullName}</h1>
        <h2>View Order</h2>
        <a href="SearchController" >Add more car</a></br>
        <c:if test="${requestScope.MESSAGE !=null && not empty requestScope.MESSAGE}">
            <h5>${requestScope.MESSAGE}</h5>
        </c:if>
        Date order:<input type="date" value="${sessionScope.CURRENT_DATE}" readonly="true">
        <c:if test="${sessionScope.CART!=null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Car Name</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Date Rent</th>
                        <th>Date Return</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="carDto" varStatus="counter" items="${sessionScope.CART_DETAIL}">
                    <form action="UpdateBillController" method="POST">
                        <tr>
                            <input type="hidden" name="txtCarID" value="${carDto.carID}">
                            <input type="hidden" name="dateRent" value="${carDto.rentDate}">
                            <input type="hidden" name="dateReturn" value="${carDto.returnDate}">
                            <input type="hidden" name="txtCarName" value="${carDto.carName}">
                            <td>${counter.count}</td>
                            <td>${carDto.carName}</td>
                            <td><input type="number" name="txtAmount" value="${carDto.quantity}" min="1" step="1"></td>
                            <td>${carDto.price}</td>
                            <td><input type="date" name="txtDateRent" value="${carDto.rentDate}" min="${requestScope.CURRENT_DATE}"></td>
                            <td><input type="date" name="txtDateReturn" value="${carDto.returnDate}" min="${requestScope.CURRENT_DATE}"></td>
                            <td><input type="submit" value="Update bill"></td>
                            <td><a href="DeleteBillController?txtCarID=${carDto.carID}&txtDateRent=${carDto.rentDate}&txtDateReturn=${carDto.returnDate}" onclick="return confirm('Are you sure you want to delete this item?');">Delete bill</a></td>

                        </tr>
                    </form>
                   </c:forEach>
                </tbody>
            </table>

        </c:if>
        <form action="CheckDiscountController" method="POST">
            <input type="text" name="txtDiscount" value="">
            <input type="submit" value="check discount code"></br>
        </form>
        <h4>Total:${sessionScope.TOTAL_MONEY}</h4>
        <form action="BillController" method="POST">
            <input type="submit" value="BILL">
        </form>
    </body>
</html>
