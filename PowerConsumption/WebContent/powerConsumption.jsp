<%@page import="com.PowerConsumption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Consumption</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/powerConsumption.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-7">
				<h1>Power Consumption Management</h1>
				<form id="formData" name="formData" method="post"
					action="powerConsumption.jsp">
					Power Consumption ID: <input id="c_ID" name="c_ID" type="text"
						class="form-control form-control-sm"> <br> Commercial
					Unit: <input id="c_commercial" name="c_commercial" type="text"
						class="form-control form-control-sm"> <br>
					Agriculture Unit: <input id="c_agriculture" name="c_agriculture"
						type="text" class="form-control form-control-sm"> <br>
					Residential Unit: <input id="c_residential" name="c_residential"
						type="text" class="form-control form-control-sm"> <br>
					Power Consumption Date: <input id="c_date" name="c_date"
						type="text" class="form-control form-control-sm"> <br>
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidPCIDSave" name="hidPCIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divPCDataGrid">
					<%
					PowerConsumption PCDataObj = new PowerConsumption();
					out.print(PCDataObj.readPCData());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>