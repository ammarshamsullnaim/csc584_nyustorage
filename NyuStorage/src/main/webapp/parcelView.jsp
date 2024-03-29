<%@ page import="java.util.List" %>
<%@ page import="parcel.Parcel" %>
<%@ page import="parcel.ParcelModel" %>

<jsp:useBean id="ParcelModel" scope="application" class="parcel.ParcelModel" />

<%
    List<Parcel> list = ParcelModel.viewParcels();
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

<h1> Parcel List </h1>

<form method="post" action="">
            <div class="mb-3">
                <label for="searchType" class="form-label">Search by:</label>
                <select class="form-control" id="searchType" name="searchType">
                    <option value="tracking_num">Tracking No</option>
                    <option value="receipient_name">Receipient Name</option>
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
                                <th class="text-center">Tracking Num</th>
                                <th class="text-center">Receipient Name</th>
                                <th class="text-center">Tel No</th>
                                <th class="text-center">Received Date</th>
                                <th class="text-center">Collection Date</th>
                                <th class="text-center">weight</th>
                                <th class="text-center">Remark</th>
                                <th class="text-center">User ID</th>
                                <th class="text-center">Shelf ID</th>
                                <th class="text-center">Courier ID</th>
                            
                                <th style="text-align: center;">Actions</th>
                            </tr>
    </thead>
    <tbody>
        <%
            for (Parcel i : list) {
            	if ((searchType == null || searchValue == null) ||
                        (searchType.equals("tracking_num") && String.valueOf(i.getTracking_num()).contains(searchValue)) ||
                        (searchType.equals("receipient_name") && String.valueOf(i.getReceipient_name()).contains(searchValue))){
           
        %>
        <tr>
            <td><%= i.getTracking_num() %></td>
            <td><%= i.getReceipient_name() %></td>
            <td><%= i.getTel_num() %></td>
            <td><%= i.getReceived_date() %></td>
            <td><%= i.getCollection_date() %></td>
            <td><%= i.getWeight() %></td>
            <td><%= i.getRemark() %></td>
            <td><%= i.getUserId() %></td>
            <td><%= i.getShelf_id() %></td>
            <td><%= i.getCourier_id() %></td>
            
            <td style="text-align: center;">
                
                  <div class="btn-group">
                                            <form action="ParcelDelete" method="post">
                                                <input type="hidden" value="<%= i.getTracking_num() %>" name="tracking_num" />
                                                <button onclick="return confirm('Are you sure to delete this record?')" type="submit" class="btn btn-danger mr-2">Delete</button>
                                            </form>
                                            <form action="ParcelUpdate.jsp" method="post">
                                                <input type="hidden" value="<%= i.getTracking_num() %>" name="tracking_num" />
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
    <button onclick="location.href='ParcelAdd.jsp'" type="button" class="btn btn-success">Add New Parcel</button>
</div>
</div>
<br>

<jsp:include page="footer.jsp" />
