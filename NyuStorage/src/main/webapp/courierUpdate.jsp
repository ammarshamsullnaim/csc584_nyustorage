<%@ page import="courier.Courier" %>
<%@ page import="java.util.*" %>
<%@ page import="courier.*" %>

<jsp:useBean id="CourierModel" scope="application" class="courier.CourierModel" />

<jsp:include page="header.jsp"></jsp:include>
<br>

<%
   String courier_id = request.getParameter("courier_id");
   Courier courier = CourierModel.getCourierByID(courier_id);
%>

<section id="deleteCourierByID" class="padding">
   <div class="container">
      <div class="row">
         <div class="col-md-12">
            <h2 class="heading">Update Courier</h2>
            <div class="updateCourier_wrapper">
               <form action="CourierUpdate" method="post">
              <input type="hidden" name="courier_id" value="<%= (courier != null) ? courier.getCourier_ID() : "" %>">
                  <div class="form-group">
                     <label for="courier_name">Courier Name:</label>
                    <input type="text" class="form-control" id="courier_name" name="courier_name" value="<%= (courier != null) ? courier.getcourier_name() : "" %>" required>
						</div>
	                  <button type="submit" class="btn btn-primary">Update</button>
               </form>
            </div>
         </div>
      </div>
   </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
