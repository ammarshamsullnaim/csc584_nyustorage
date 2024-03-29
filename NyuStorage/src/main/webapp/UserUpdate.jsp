<%@ page import="login.User" %>
<%@ page import="java.util.*" %>
<%@ page import="login.*" %>

<jsp:useBean id="UserModel" scope="application" class="login.UserModel" />

<jsp:include page="header.jsp"></jsp:include>
<br>

<%
   String userID = request.getParameter("userID");
   User user = UserModel.getUserByID(userID);
%>

<section id="updateUser" class="padding">
   <div class="container">
      <div class="row">
         <div class="col-md-12">
            <h2 class="heading">Update User</h2>
            <div class="updateUser_wrapper">
               <form action="UserUpdate" method="post">
              <input type="hidden" name="userID" value="<%= (user != null) ? user.getUserID() : "" %>">

                  <div class="form-group">
                     <label for="userPassword">User Password:</label>
                    <input type="password" class="form-control" id="userPassword" name="userPassword" value="<%= (user != null) ? user.getUserPassword() : "" %>" required>

                  </div>
                 
                  <div class="form-group">
                     <label for="userName">User Name:</label>
                    <input type="text" class="form-control" id="userName" name="userName" value="<%= (user != null) ? user.getUserName() : "" %>" required>

                  </div>

                   <div class="form-group">
  					  <label for=userPhoneNo>Phone No. :</label>
						<input type="text" name="userPhoneNo" class="form-control" id="userPhoneNo" value="<%= (user != null) ? user.getUserPhoneNo() : "" %>" required>
				</div>
                 <div class="form-group">
                     <label for="userAddress">Address :</label>
                    <input type="text" class="form-control" id="userAddress" name="userAddress" value="<%= (user != null) ? user.getUserAddress() : "" %>" required>

                  </div>
                  <div class="form-group">
				    <label for="userIC">IC:</label>
						<input type="text" name="userIC" class="form-control" id="userIC" value="<%= (user != null) ? user.getUserIC() : "" %>" required>
					</div>
	                  <button type="submit" class="btn btn-primary">Update</button>
               </form>
            </div>
         </div>
      </div>
   </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
