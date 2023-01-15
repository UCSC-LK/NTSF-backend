<%@ page import="com.cops.ntsf.model.PoliceStation" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 1/15/2023
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Police Station</title>
</head>
<body>
<table>
    <tr>
        <th>Police Station Name</th>


    </tr>
    <% List<PoliceStation> stationsList = (List<PoliceStation>) request.getAttribute("PoliceStationDetails"); %>
    <%--<% for (int i =0; i<complaintList.size(); i++)
    {%>--%>
    <tr>
        <td><%= stationsList.getname()%></td>
    </tr>
    <%--<% } %>--%>
</table>
</body>
</html>
