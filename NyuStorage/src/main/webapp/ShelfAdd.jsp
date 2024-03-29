<jsp:include page="header.jsp"></jsp:include>
<br>

<section id="faq" class="padding">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
       <h2 class="heading">Add Shelf</h2>
        <hr class="heading_space">
        <div class="faq_wrapper">
			<form action="ShelfAdd" method="post">
			  <div class="form-group">
			    <label for="shelf_id">Shelf ID:</label>
			    <input type="text" name="shelf_id" class="form-control" id="shelf_id" placeholder="Enter shelf ID">
			  </div>
			  <div class="form-group">
			    <label for="shelf_row">Shelf Row:</label>
			    <input type="text" name="shelf_row" class="form-control" id="shelf_row" placeholder="Enter Shelf Row (A, B, C, ...)" required>
			  </div>
			  <div class="form-group">
			    <label for="shelf_column">Shelf Column:</label>
			    <input type="text" name="shelf_column" class="form-control" id="shelf_column" placeholder="Enter Column (1, 2, 3, ...)" required>
			  </div>

			  <button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
      </div>
    </div>
  </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>