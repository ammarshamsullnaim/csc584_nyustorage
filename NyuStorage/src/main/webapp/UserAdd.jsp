<jsp:include page="header.jsp"></jsp:include>
<br>

<section id="faq" class="padding">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
       <h2 class="heading">Add User</h2>
        <hr class="heading_space">
        <div class="faq_wrapper">
			<form action="UserAdd" method="post">
			  <div class="form-group">
			    <label for="userID">User ID:</label>
			    <input type="text" name="userID" class="form-control" id="userID" placeholder="Enter user ID">
			  </div>
			  <div class="form-group">
			    <label for="userPassword">User Password:</label>
			    <input type="password" name="userPassword" class="form-control" id="userPassword" placeholder="Enter password" required>
			  </div>
			  <div class="form-group">
			    <label for="userName">User Name:</label>
			    <input type="text" name="userName" class="form-control" id="userName" placeholder="Enter Name" required>
			  </div>
			   <div class="form-group">
				    <label for="userIC">IC:</label>
				    <input type="number" name="userIC" class="form-control" id="userIC"  placeholder="Enter IC" required>
				</div>
			  <div class="form-group">
			    <label for="userPhoneNo">User Phone No.:</label>
			    <input type="number" name="userPhoneNo" class="form-control" id="userPhoneNo"  placeholder="Enter phone number" required>
				</div>
			<div class="form-group">
			    <label for="userAddress">Address:</label>
			    <input type="text" name="userAddress" class="form-control" id="userAddress"  placeholder="Enter address" required>
			</div>
			
			  <button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
      </div>
    </div>
  </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>