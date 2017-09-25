<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
<%--    <form method="post" action="users">
        <spring:message code="app.login"/>: <select name="userId">
    <option value="100000" selected>User</option>
    <option value="100001">Admin</option>
</select>
        <button type="submit"><spring:message code="common.select"/></button>
</form>--%>
<ul>
    <li><a href="rest/admin/users">get users: rest/admin/users</a></li>
    <li><a href="rest/admin/restaurants">get restaurants: rest/admin/restaurants</a></li>
    <li><a href="rest/admin/dishes">get dishes: rest/admin/dishes</a></li>
    <li><a href="meals">get meals</a></li>
</ul>
</section>
</body>
</html>