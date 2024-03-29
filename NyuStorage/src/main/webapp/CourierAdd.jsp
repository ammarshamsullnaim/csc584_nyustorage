<jsp:include page="header.jsp"></jsp:include>
<br>

<section id="faq" class="padding">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
       <h2 class="heading">Add Courier</h2>
        <hr class="heading_space">
        <div class="faq_wrapper">
			<form action="CourierAdd" method="post">
			  <div class="form-group">
			    <label for="courier_id">courier ID:</label>
			    <input type="text" name="courier_id" class="form-control" id="courier_id" placeholder="Enter courier ID">
			  </div>
			  <div class="form-group">
			    <label for="courier_name">Courier Name:</label>
			    <input type="text" name="courier_name" class="form-control" id="courier_name" placeholder="Enter Courier Name" required>
			  </div>

			  <button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
      </div>
    </div>
  </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>