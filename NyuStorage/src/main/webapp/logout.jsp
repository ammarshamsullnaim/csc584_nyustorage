<%
	if(session.getAttribute("status") != null){
		session.removeAttribute("status");
		session.removeAttribute("role");
		response.sendRedirect("index.jsp");
	}
%>