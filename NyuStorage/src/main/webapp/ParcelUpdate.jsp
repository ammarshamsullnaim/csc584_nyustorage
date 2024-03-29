<%@ page import="java.util.List" %>
<%@ page import="parcel.Parcel" %>
<%@ page import="parcel.ParcelModel" %>
<%@ page import="shelf.Shelf" %>
<%@ page import="java.util.*" %>
<%@ page import="shelf.*" %>
<%@ page import="login.*" %>
<%@ page import="courier.*" %>

<jsp:useBean id="ParcelModel" scope="application" class="parcel.ParcelModel" />
<jsp:useBean id="ShelfModel" scope="application" class="shelf.ShelfModel" />
<jsp:useBean id="UserModel" scope="application" class="login.UserModel" />
<jsp:useBean id="CourierModel" scope="application" class="courier.CourierModel" />


<jsp:include page="header.jsp"></jsp:include>
<br>

<%
   String tracking_num = request.getParameter("tracking_num");
   Parcel parcel = null;
   String errorMessage = null;

   if (tracking_num != null && !tracking_num.isEmpty()) {
       try {
           parcel = ParcelModel.getParcelByTracking_Num(tracking_num);
       } catch (Exception e) {
           errorMessage = "Error retrieving parcel: " + e.getMessage();
           e.printStackTrace();
       }
   }

   if (parcel == null) {
%>
   <h2>Parcel Not Found</h2>
   <p>The parcel with tracking number <%= tracking_num %> was not found.</p>
<%
   } else {
%>

<section id="updateParcel" class="padding">
   <div class="container">
      <div class="row">
         <div class="col-md-12">
            <h2 class="heading">Update Parcel</h2>
            <div class="updateParcel_wrapper">
               <form action="ParcelUpdate" method="post">
              <input type="hidden" name="tracking_num" value="<%= parcel.getTracking_num() %>">
                  <div class="form-group">
                     <label for="receipient_name">receipient_name:</label>
                    <input type="text" class="form-control" id="receipient_name" name="receipient_name" value="<%= parcel.getReceipient_name() %>" required>
						</div>
						<div class="form-group">
                     <label for="tel_num">Tel Num:</label>
                    <input type="text" class="form-control" id="tel_num" name="tel_num" value="<%= parcel.getTel_num() %>" required>
                  </div>
                  <div class="form-group">
                     <label for="received_date">received_date:</label>
                    <input type="date" class="form-control" id="received_date" name="received_date" value="<%= parcel.getReceived_date() %>" required>
                  </div>
                  <div class="form-group">
                     <label for="collection_date">collection_date:</label>
                    <input type="date" class="form-control" id="collection_date" name="collection_date" value="<%= parcel.getCollection_date() %>" required>
                  </div>
                  <div class="form-group">
                     <label for="weight">weight:</label>
                    <input type="text" class="form-control" id="weight" name="weight" value="<%= parcel.getWeight() %>" required>
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
                       
	                  <button type="submit" class="btn btn-primary">Update</button>
               </form>
            </div>
         </div>
      </div>
   </div>
</section>
<% } %>
<jsp:include page="footer.jsp"></jsp:include>
