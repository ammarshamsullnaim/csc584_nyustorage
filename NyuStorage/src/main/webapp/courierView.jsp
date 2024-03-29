<%@ page import="java.util.List" %>
<%@ page import="courier.Courier" %>
<%@ page import="courier.CourierModel" %>

<jsp:useBean id="CourierModel" scope="application" class="courier.CourierModel" />

<%
    List<Courier> list = CourierModel.viewCourier();
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

<h1> Courier List </h1>

<form method="post" action="">
            <div class="mb-3">
                <label for="searchType" class="form-label">Search by:</label>
                <select class="form-control" id="searchType" name="searchType">
                    <option value="courier_id">courier ID</option>
                    <option value="courier_name">courier Name</option>
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
                                <th class="text-center">Courier ID</th>
                                <th class="text-center">Courier Name</th>
                            
                                <th style="text-align: center;">Actions</th>
                            </tr>
    </thead>
    <tbody>
        <%
            for (Courier i : list) {
            	if ((searchType == null || searchValue == null) ||
                        (searchType.equals("courier_id") && String.valueOf(i.getCourier_ID()).contains(searchValue)) ||
                        (searchType.equals("courier_name") && i.getcourier_name().contains(searchValue))) {
           
        %>
        <tr>
            <td><%= i.getCourier_ID() %></td>
            <td><%= i.getcourier_name() %></td>

            
            <td style="text-align: center;">
                
                  <div class="btn-group">
                                            <form action="CourierDelete" method="post">
                                                <input type="hidden" value="<%= i.getCourier_ID() %>" name="courier_id" />
                                                <button onclick="return confirm('Are you sure to delete this record?')" type="submit" class="btn btn-danger mr-2">Delete</button>
                                            </form>
                                            <form action="courierUpdate.jsp" method="post">
                                                <input type="hidden" value="<%= i.getCourier_ID() %>" name="courier_id" />
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
    <button onclick="location.href='CourierAdd.jsp'" type="button" class="btn btn-success">Add New Courier</button>
</div>
</div>
<br>

<jsp:include page="footer.jsp" />
