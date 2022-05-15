<%@page import="com.PowerConsumption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Consumption</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/powerConsumption.js"></script>
</head>
<body style="background-color: #66b3ff">
	<div class="container p-3 mb-2 mx-autos"
		style="background-color: #66b3ff">
		<div class="row  mx-auto">
			<div class="col-5  mx-auto">
				<h1 class="text-white"
					style="width: 550px; margin-left: -50px; margin-bottom: 20px;">Power
					Consumption Management</h1>

				<div class="p-3 mb-2 bg-white text-black mx-auto"
					style="border-radius: 10px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
					<form id="formData" name="formData" method="post"
						action="powerConsumption.jsp">

						Power Consumption ID: <input id="c_ID" name="c_ID" type="text"
							placeholder="Enter Power Consumption ID"
							class="form-control form-control-sm"> <br>
						Commercial Unit: <input id="c_commercial" name="c_commercial"
							placeholder="Enter Commercial Unit" type="text"
							class="form-control form-control-sm"> <br>
						Agriculture Unit: <input id="c_agriculture" name="c_agriculture"
							placeholder="Enter Agriculture Unit" type="text"
							class="form-control form-control-sm"> <br>
						Residential Unit: <input id="c_residential" name="c_residential"
							placeholder="Enter Residential Unit" type="text"
							class="form-control form-control-sm"> <br> Power
						Consumption Date: <input id="c_date" name="c_date" type="date"
							class="form-control form-control-sm"> <br>

						<div class="mx-auto" style="width: 50px">
							<input id="btnSave" name="btnSave" type="button" value="Save"
								class="btn btn-success"> <input type="hidden"
								id="hidPCIDSave" name="hidPCIDSave" value="">
						</div>
					</form>
				</div>
				<br>
				<div id="alertSuccess" class="alert alert-success"
					style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"></div>
				<div id="alertError" class="alert alert-danger"
					style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"></div>
				<br>
			</div>
		</div>
		<div id="divPCDataGrid" class="col-9 p-3 mb-2 bg-white mx-auto"
			style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
			<%
				PowerConsumption PCDataObj = new PowerConsumption();
			out.print(PCDataObj.readPCData());
			%>

		</div>
	</div>
</body>
</html>