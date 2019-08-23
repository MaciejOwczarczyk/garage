<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp"%>

<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>Solutions</h2>

<table>
    <tr>
        <th>Id</th>
        <th>Excersise_id</th>
        <th>User_Id</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Description</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${solutions}" var="item" varStatus="loop">
        <tr>
            <td>${item.id}</td>
            <td>${item.excersise_id}</td>
            <td>${item.user_id}</td>
            <td>${item.created}</td>
            <td>${item.updated}</td>
            <td>${item.description}</td>
            <td><a href="/details?id=${item.id}"  class="btn btn-info rounded-0 text-light m-1">Details</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

<%@ include file="/WEB-INF/footer.jsp"%>