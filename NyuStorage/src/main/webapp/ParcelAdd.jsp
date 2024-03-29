<jsp:include page="header.jsp"></jsp:include>
<br>

<%@ page import="shelf.Shelf" %>
<%@ page import="java.util.*" %>
<%@ page import="shelf.*" %>
<%@ page import="login.*" %>
<%@ page import="courier.*" %>

<jsp:useBean id="ShelfModel" scope="application" class="shelf.ShelfModel" />
<jsp:useBean id="UserModel" scope="application" class="login.UserModel" />
<jsp:useBean id="CourierModel" scope="application" class="courier.CourierModel" />

<section id="faq" class="padding">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h2 class="heading">Add Parcel</h2>
        <hr class="heading_space">
        <div class="faq_wrapper">
          <form action="ParcelAdd" method="post">
            <div class="form-group">
              <label for="tracking_num">Tracking Number:</label>
              <input type="text" name="tracking_num" class="form-control" id="tracking_num" placeholder="Enter tracking number" required>
            </div>
            <div class="form-group">
              <label for="receipient_name">Recipient Name:</label>
              <input type="text" name="receipient_name" class="form-control" id="receipient_name" placeholder="Enter recipient name" required>
            </div>
            <div class="form-group">
              <label for="tel_num">Telephone Number:</label>
              <input type="text" name="tel_num" class="form-control" id="tel_num" placeholder="Enter telephone number" required>
            </div>
            <div class="form-group">
              <label for="received_date">Received Date:</label>
              <input type="date" name="received_date" class="form-control" id="received_date" placeholder="Enter received date (yyyy-MM-dd)" required>
            </div>
            <div class="form-group">
              <label for="collection_date">Collection Date:</label>
              <input type="date" name="collection_date" class="form-control" id="collection_date" placeholder="Enter collection date (yyyy-MM-dd)" required>
            </div>
            <div class="form-group">
              <label for="weight">Weight:</label>
              <input type="number" name="weight" class="form-control" id="weight" placeholder="Enter weight" required>
            </div>
            <div class="form-group">
			  <label for="remark">Remark:</label>
			  <select name="remark" class="form-control" id="remark">
			    <option value="Received">Received</option>
			    <option value="Collected">Collected</option>
			    <option value="Missing">Missing</option>
			  </select>
			</div>

            <!-- Dropdown for User ID -->
       <label for="userId">User:</label>
        <select name="userId" id="userId" required>
            <option value="">Select User</option>
            <% UserModel userModel = new UserModel();
               List<User> userList = userModel.viewUsers(); // Fetching all users from the database using the viewUsers method.
               for (User user : userList) { %>
                <option value="<%= user.getUserID() %>"><%= user.getUserName() %></option>
            <% } %>
        </select><br>
        
     <!-- Dropdown for Shelf -->
       <label for="shelf_id">Shelf:</label>
        <select name="shelf_id" id="	shelf_id" required>
            <option value="">Select Shelf</option>
            <% ShelfModel shelfModel = new ShelfModel();
               List<Shelf> shelfList = shelfModel.shelfRead(); // Fetching all shelves from the database using the shelfRead method.
               for (Shelf shelf : shelfList) { %>
                <option value="<%= shelf.getShelf_id() %>"><%= shelf.getShelf_row() %> - <%= shelf.getShelf_column() %></option>
            <% } %>
        </select><br>

        <!-- Dropdown for Courier -->
        <label for="courier_id">Courier:</label>
        <select name="courier_id" id="courier_id" required>
            <option value="">Select Courier</option>
            <% CourierModel courierModel = new CourierModel();
               List<Courier> courierList = courierModel.viewCourier(); // Fetching all couriers from the database using the viewCourier method.
               for (Courier courier : courierList) { %>
                <option value="<%= courier.getCourier_ID() %>"><%= courier.getcourier_name() %></option>
            <% } %>
        </select><br>

            <button type="submit" class="btn btn-primary">Add</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
