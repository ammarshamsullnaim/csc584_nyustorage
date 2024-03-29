<%@ page import="java.util.List" %>
<%@ page import="login.User" %>
<%@ page import="login.UserModel" %>

<jsp:useBean id="UserModel" scope="application" class="login.UserModel" />

<%
    List<User> list = UserModel.viewUsers();
String searchType = request.getParameter("searchType");
String searchValue = request.getParameter("searchValue");
%>
<style>
    body {
        padding-top: 0px;
        font-family: Arial, sans-serif;
    }

    .form-container {
        width: 100%;
        margin: 0 auto;
        background-color: #f9f9f9;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .scroll-box {
        width: 100%;
        height: 400px;
        overflow: auto;
        border: 1px solid #ccc;
        padding: 10px;
    }

    .table {
        width: 100%;
        border-collapse: collapse;
    }

    .table th,
    .table td {
        border: 1px solid #ccc;
        padding: 10px;
        text-align: center;
    }

    .table th {
        background-color: #f2f2f2;
    }

    .table td {
        background-color: #ffffff;
    }

    .table td:last-child {
        white-space: nowrap;
    }

    .table td button {
        margin-right: 5px;
    }

    .btn-group {
        margin-bottom: 10px;
    }

    .center-button {
        text-align: center;
    }

    .btn-success {
        background-color: #28a745;
        color: #ffffff;
    }

    .btn-primary {
        background-color: #007bff;
        color: #ffffff;
    }

    .btn-danger {
        background-color: #dc3545;
        color: #ffffff;
    }

    .btn-outline-primary {
        border-color: #007bff;
        color: #007bff;
    }
</style>

<jsp:include page="header.jsp" />
<div class="form-container">

<h1> User List </h1>

<form method="post" action="">
            <div class="mb-3">
                <label for="searchType" class="form-label">Search by:</label>
                <select class="form-control" id="searchType" name="searchType">
                    <option value="UserID">UserID</option>
                    <option value="UserName">UserName</option>
                </select>
            </div>
            <div class="mb-3 d-flex">
    <input type="text" class="form-control me-2" id="searchValue" name="searchValue" placeholder="Search Value">
    <button type="submit" class="btn btn-outline-primary">Search</button>
</div>
</form>

<br>
<div class="scroll-box">



<table class="table table-striped">
    <thead>
          <tr>
                                <th class="text-center">User ID</th>
                                <th class="text-center">User Password</th>
                                <th class="text-center">User Name</th>
                                <th class="text-center">User Phone No.</th>
                                <th class="text-center">User Address</th>
                                <th class="text-center">User IC</th>
                                <th style="text-align: center;">Actions</th>
                            </tr>
    </thead>
    <tbody>
        <%
            for (User i : list) {
            	if ((searchType == null || searchValue == null) ||
                        (searchType.equals("UserID") && String.valueOf(i.getUserID()).contains(searchValue)) ||
                        (searchType.equals("UserName") && i.getUserName().contains(searchValue))) {
           
        %>
        <tr>
            <td><%= i.getUserID() %></td>
            <td><%= i.getUserPassword() %></td>
            <td><%= i.getUserName() %></td>
            <td><%= i.getUserIC() %></td>
            <td><%= i.getUserPhoneNo() %></td>
            <td><%= i.getUserAddress() %></td>
         
            
            <td style="text-align: center;">
                
                  <div class="btn-group">
                                            <form action="UserDelete" method="post">
                                                <input type="hidden" value="<%= i.getUserID() %>" name="userID" />
                                                <button onclick="return confirm('Are you sure to delete this record?')" type="submit" class="btn btn-danger mr-2">Delete</button>
                                            </form>
                                            <form action="UserUpdate.jsp" method="post">
                                                <input type="hidden" value="<%= i.getUserID() %>" name="userID" />
                                                <button type="submit" class="btn btn-primary mr-2">Update</button>
                                            </form>
                                        </div>
                    
                  </div>
                </td>
              </tr>
        <%
            } }
        %>
    </tbody>
</table>
<br>
</div>
<br>
<div class="text-center">
    <button onclick="location.href='UserAdd.jsp'" type="button" class="btn btn-success">Add New User</button>
</div>
</div>
<br>

<jsp:include page="footer.jsp" />

