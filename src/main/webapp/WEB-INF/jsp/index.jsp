<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>!!!!!</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/showWord">

    <label>Повторим?<br>
        <input type="text" name="URI" placeholder="Write URI">
    </label>

    <input type="submit" value="Show word"><br><br>

</form>

<c:forEach items="${words}" var="entry">
    ${entry.key} - ${entry.value}<br>
</c:forEach>


</body>
</html>
