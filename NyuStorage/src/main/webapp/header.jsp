<!DOCTYPE html>
<html lang="en">
<head>
<style>
    .dropdown-menu a {
        color: #333;
    }

    .dropdown-menu a:hover {
        background-color: #f2f2f2;
    }
</style>
<title>Invetory System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">NyuStorage</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>

        <% if(session.getAttribute("status") != null) { %>
            
            <li class="nav-item"><a class="nav-link" href="userView.jsp">View User Information</a></li>
            <li class="nav-item"><a class="nav-link" href="courierView.jsp">View Courier</a></li>
            <li class="nav-item"><a class="nav-link" href="shelfView.jsp">View Shelf</a></li>
            <li class="nav-item"><a class="nav-link" href="parcelView.jsp">View Parcel</a></li>
            <li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
        <% } else { %>
            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
        <% } %>
    </ul>
</div>

</nav>

<div class="container">
	
	<%
		if(session.getAttribute("success") != null) {	%>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
			  <%=session.getAttribute("success") %>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
	<%		session.removeAttribute("success");
		}
	%>
	
	<%
		if(session.getAttribute("danger") != null) {	%>
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
			  <%=session.getAttribute("danger") %>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
	<%		session.removeAttribute("danger");
		}
	%>
	
	