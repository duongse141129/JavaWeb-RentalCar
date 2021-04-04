<%-- 
    Document   : shopping
    Created on : Feb 22, 2021, 9:24:33 PM
    Author     : Admin
                        <input type="hidden" name="rd" value="${param.rd}">
                        <input type="hidden" name="txtSearch" value="${param.txtSearch}">
                        <input type="hidden" name="cmbType" value="${param.cmbType}">
                        <input type="hidden" name="txtAmountSearch" min="1" max="10" step="1" value="${param.txtAmountSearch}">
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping JSP Page</title>
        <link rel="stylesheet" href="CSS\style.css">
    </head>
    <body>
        <a href="LogoutController">Logout</a>
        <a href="ViewOrderController">View Order</a>
        <a href="ViewHistoryController">View History</a>
        <h1>WELCOME ${sessionScope.LOGIN_USER.fullName}</h1>
        <form action="SearchController" method="POST">
            <input type="radio" name="rd" value="name" checked="">
            <c:if test="${param.txtSearch ==null}">
                <input type="text" name="txtSearch" value="">
            </c:if>
            <c:if test="${param.txtSearch !=null}">
                <input type="text" name="txtSearch" value="${param.txtSearch}">
            </c:if>  
            
            Type Car:
            <c:if test="${param.rd=='type'}">
                <input type="radio" name="rd" value="type" checked="">
            </c:if>
            <c:if test="${param.rd!='type'}">
                <input type="radio" name="rd" value="type">
            </c:if>     

            <select name="cmbType" >

                <c:if test="${param.cmbType==null}">
                    <c:forEach var="type" varStatus="counter" items="${sessionScope.LIST_TYPE}">
                        <option value="${type.typeID}">${type.typeName}</option>                                   
                    </c:forEach>            
                </c:if>
                <c:if test="${param.cmbType!=null}">
                    <c:forEach var="type" varStatus="counter" items="${sessionScope.LIST_TYPE}">
                        <c:if test="${param.cmbType==type.typeID}">
                            <option value="${type.typeID}" selected="true">${type.typeName}</option>   
                        </c:if>                                   
                        <c:if test="${param.cmbType!=type.typeID}">
                            <option value="${type.typeID}">${type.typeName}</option>   
                        </c:if> 
                    </c:forEach>
                </c:if>         
            </select>
                <input type="number" name="txtAmountSearch" min="1" max="10" step="1" value="1">
                RentDate:<input type="date" name="txtRentDate" value="${requestScope.DATE_RENT}">
                ReturnDate:<input type="date" name="txtReturnDate" value="${requestScope.DATE_RETURN}">
                <input type="submit" value="Search" >
        </form></br>
        <c:if test="${requestScope.MESSAGE !=null && not empty requestScope.MESSAGE}">
            <h5>${requestScope.MESSAGE}</h5>
        </c:if>        
        <c:if test="${requestScope.LIST!=null}">
            <c:forEach var="carDto" varStatus="counter1" items="${requestScope.LIST}">
                <form action="AddController" method="POST" class="nbs-flexisel-item">
                    <div >
                        <img src="${carDto.img}" width="250" height="250"/>
                        <input type="hidden" name="img" value="${carDto.img}" >
                    </div>
                    <h3>${carDto.carName}</h3>
                    <p>
                        ${carDto.color}</br>
                        Quantity:${carDto.quantity}</br>
                        ${carDto.price}/day</br>
                        ${carDto.nsx}</br>                       
                        Rate: ${carDto.rating}</br>
                        ${carDto.type}</br>
                        <input type="hidden" name="txtCarID" value="${carDto.carID}">
                        <input type="hidden" name="txtCarName" value="${carDto.carName}">
                        <input type="hidden" name="txtColor" value="${carDto.color}">
                        <input type="hidden" name="txtQuantity" value="${carDto.quantity}">
                        <input type="hidden" name="txtPrice" value="${carDto.price}">
                        <input type="hidden" name="txtNsx" value="${carDto.nsx}">
                        <input type="hidden" name="txtRating" value="${carDto.rating}">
                        
                        <input type="hidden" name="txtTypeID" value="${carDto.type}">   
                        
                        <input type="hidden" name="txtRentDate" value="${requestScope.DATE_RENT}">
                        <input type="hidden" name="txtReturnDate" value="${requestScope.DATE_RETURN}">
                        

                        
                        <input type="number" name="txtAmount" min="1" max="${carDto.quantity}" step="1" value="1">
                        <c:if test="${sessionScope.LOGIN_USER!=null}">
                             <input type="submit" class="add2cart" value="AddToCart">
                        </c:if>
                    </p>
                </form>              
            </c:forEach>
            <footer >
                            <div class="footer">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${requestScope.TOTAL_PAGE!=null && requestScope.TOTAL_PAGE>1}">
                                        <c:forEach var="count" begin="1" end="${requestScope.TOTAL_PAGE}">
                                            <li class="page-item">
                                                <a class="page-link" href="SearchController?txtCurrentPage=${count}&rd=${param.rd}&cmbType=${param.cmbType}&txtAmountSearch=${param.txtAmountSearch}&txtSearch=${param.txtSearch}&txtRentDate=${requestScope.DATE_RENT}&txtReturnDate=${requestScope.DATE_RETURN}">${count}</a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>   
           </footer>
        </c:if>
           
        
    </body>
</html>
