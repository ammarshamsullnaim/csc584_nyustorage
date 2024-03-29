<%@ page import="login.*" %>

<jsp:include page="header.jsp"></jsp:include>
<br>

<section id="faq" class="padding">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
       <h2 class="heading">Login</h2>
        <hr class="heading_space">
        <div class="faq_wrapper">
			<form method="post" action="LoginController">
			    </div>
			    <div class="form-group">
			        <label for="userID">User ID</label>
			        <input type="text" name="userID" class="form-control" id="userID" aria-describedby="userID" placeholder="Enter user ID">
			    </div>
			    <div class="form-group">
			        <label for="userPassword">User Password</label>
			        <input type="password" name="userPassword" class="form-control" id="userPassword" placeholder="Enter password">
			    </div>
			    <div class="form-group">
			        <button type="submit" class="btn btn-primary">Login</button>
			        <a href="UserAdd.jsp" class="btn btn-success">Sign Up</a>
			    </div>
			</form>
		</div>
      </div>
    </div>
  </div>
</section>

<br>
<jsp:include page="footer.jsp"></jsp:include>