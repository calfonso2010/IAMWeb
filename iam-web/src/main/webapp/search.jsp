<%@page import="fr.tbr.iamcore.datamodel.Identity"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
<% Collection<Identity> idList = (Collection<Identity>)session.getAttribute("idList");

  session.removeAttribute("idList");
%>

<head>
<link xmlns="http://www.w3.org/1999/xhtml" rel="stylesheet"
	href="css/bootstrap.min.css" />
</head>

<body>


	<div class="container">
		<h2 class="text-info">Identity Search</h2>
		<a href="#">&lt;&lt; back</a>

		<h3 class="text-info">Search Criteria</h3>
	</div>


	<form class="form-horizontal" role="form" method="GET" action="Search">
		<div class="form-group">
			<label for="displayName" class="col-sm-2 control-label"></label>

			<div class="col-sm-10">
				<input name="displayName" type="text" class="form-control" id="displayName"
					placeholder="Display Name" />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>

			<div class="col-sm-10">
				<input name="email" type="text" class="form-control" id="email"
					placeholder="Email" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10 text-right">
				<button type="submit" class="btn btn-primary">Search</button>
			</div>
		</div>
	</form>
	<div class="container">
		<h3 class="text-info">Search Results</h3>

		<form class="form-horizontal">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Selection</th>
							<th>UID</th>
							<th>Display Name</th>
							<th>Email</th>
						</tr>
					</thead>
					<tbody>
					   <%
					   if (idList == null || idList.isEmpty()){%>
						  <tr>
						      <td colspan="4">No result</td>
						  </tr>
						   
					   <% } else{
					   for (Identity id : idList){ %>
						<tr>
							<td><input name="selection" type="radio" value="<%=id.getUid()%>"/></td>
							<td><%=id.getUid() %></td>
							<td><%=id.getDisplayName() %></td>
							<td><%=id.getEmail()%></td>
						</tr>
                        <%} 
                        }%>

					</tbody>
				</table>
			</div>
			<div class="form-group">
				<div class=" col-sm-offset-2 col-sm-10 text-right">
					<button type="submit" class="btn btn-primary">Modify</button>
					<button type="submit" class="btn btn-primary">Delete</button>
					<button type="submit" class="btn btn-default">Cancel</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
