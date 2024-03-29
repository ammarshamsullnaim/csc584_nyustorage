<%@ page import="shelf.Shelf" %>
<%@ page import="java.util.*" %>
<%@ page import="shelf.*" %>

<jsp:useBean id="ShelfModel" scope="application" class="shelf.ShelfModel" />

<jsp:include page="header.jsp"></jsp:include>
<br>

<%
	String shelf_id = request.getParameter("shelf_id");
   	Shelf shelf = ShelfModel.shelfReadById(shelf_id);
%>

<section id="shelfUpdate" class="padding">
   <div class="container">
      <div class="row">
         <div class="col-md-12">
            <h2 class="heading">Update Shelf</h2>
            <div class="updateShelf_wrapper">
               <form action="ShelfUpdate" method="post">
              
              <input type="hidden" name="shelf_id" value="<%= (shelf != null) ? shelf.getShelf_id() : "" %>">
				<div class="form-group">
                    <label for="shelf_row">Shelf Row:</label>
                    <input type="text" class="form-control" id="shelf_row" name="shelf_row" value="<%= (shelf != null) ? shelf.getShelf_row() : "" %>" required>
				</div>
				<div class="form-group">
                    <label for="shelf_column">Shelf Column:</label>
                    <input type="text" class="form-control" id="shelf_column" name="shelf_column" value="<%= (shelf != null) ? shelf.getShelf_column() : "" %>" required>
				</div>
	            <button type="submit" class="btn btn-primary">Update</button>
               </form>
            </div>
         </div>
      </div>
   </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
