<%@ page import="com.cops.ntsf.model.Complaint" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Suraif
  Date: 12/21/2022
  Time: 8:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Complaints</title>
</head>
<body>
    <table>
        <tr>
            <th>User_id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Complaint_no</th>

        </tr>
        <% List<Complaint> complaintList = (List<Complaint>) request.getAttribute("complaintDetails"); %>
           <% for (int i =0; i<complaintList.size(); i++)
           {%>
            <tr>
                <td>"$<%= complaintList.get(i).getUser_id()%>"</td>
                <td>"$<%= complaintList.get(i).getTitle()%>"</td>
                <td>"$<%= complaintList.get(i).getDescription()%>"</td>
                <td>"$<%= complaintList.get(i).getComplaint_no()%>"</td>
            </tr>
        <% } %>
    </table>

        <a href="http://127.0.0.1:5500/user/driver/addComplaint/addComplaint.html">
    <button class="addcomplaint-btn">Add Complaint</button>
</a>

</body>
</html>
