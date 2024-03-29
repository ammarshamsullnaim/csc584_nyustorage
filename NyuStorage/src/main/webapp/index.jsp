<%@ page import="parcel.ParcelModel" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="courier.*" %>
<%@ page import="login.UserModel" %>
<%@ page import="shelf.ShelfModel" %>
<%@ page import="parcel.Parcel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        main {
            padding: 20px;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            margin-top: 20px;
        }

        .grid-item {
            border: 1px solid #ccc;
            padding: 20px;
            text-align: center;
            background-color: #f9f9f9;
        }

        footer {
            background-color: #444;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <header>
        <jsp:include page="header.jsp" />
    </header>
    <main>
    <h1>Welcome to the Dashboard</h1>
    <p>
        <% if (session.getAttribute("role") != null) {
            if (session.getAttribute("role").equals("admin")) { %>
                administrator.
        <% 
            } else if (session.getAttribute("role").equals("user")) { %>
                user.
        <% 
            }
        } %>
    </p>

    <%-- Access the static method in a static way --%>
    <%
        int totalCouriers = CourierModel.getTotalCouriers();
    	
	    //ShelfModel shelfModel = new ShelfModel();
	    int totalShelf = ShelfModel.getTotalShelf();
	    
	    int totalParcel = ParcelModel.getTotalParcels();
	    
	    int totalUsers = UserModel.getTotalUsers();
	    
	    int totalParcels = ParcelModel.getTotalParcels();
	    
	    
    %>

    <div class="grid-container">
        <div class="grid-item">
            <h3>Total Courier</h3>
            <p><%= totalCouriers %></p>
        </div>
        <div class="grid-item">
            <h3>Total Shelf</h3>
            <p><%= totalShelf %></p>
        </div>
        <div class="grid-item">
            <h3>Total Parcel</h3>
            <p><%= totalParcel %></p>
        </div>
        <div class="grid-item">
             <h3>Total Users</h3>
             <p><%= totalUsers %></p>
        </div>
        
    </div>
    
   
    
</main>
