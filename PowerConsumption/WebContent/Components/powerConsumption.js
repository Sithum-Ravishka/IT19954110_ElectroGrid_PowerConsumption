$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePCDataForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidPCIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PowerConsumptionAPI", 
 type : type, 
 data : $("#formData").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPCDataSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onPCDataSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divPCDataGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidPCIDSave").val(""); 
$("#formData")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidPCIDSave").val($(this).data("pcid")); 
		 $("#c_ID").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#c_commercial").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#c_agriculture").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#c_residential").val($(this).closest("tr").find('td:eq(3)').text()); 
	     $("#c_date").val($(this).closest("tr").find('td:eq(4)').text()); 
		});


// CLIENT-MODEL================================================================
function validatePCDataForm()
{
	// Power Consumption ID
	if ($("#c_ID").val().trim() == "")
	{
	return "Insert Power Consumption ID.";
	}
	
	// Commercial Unit
	if ($("#c_commercial").val().trim() == "")
	{
	return "Insert Commercial Unit.";
	}

	// Agriculture Unit
	if ($("#c_agriculture").val().trim() == "")
	{
	return "Insert Agriculture Unit.";
	}
	
	// Residential Unit
   	if ($("#c_residential").val().trim() == "")
	{
	return "Insert Residential Unit.";
	}

	// Power Consumption Date
	if ($("#c_date").val().trim() == ""){
	
	return "Insert Power Consumption Date.";
	}
	return true;
}

// DELETE-FUNCTION================================================================
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "PowerConsumptionAPI", 
		 type : "DELETE", 
		 data : "pc_ID=" + $(this).data("pcid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onPCDataDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onPCDataDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divPCDataGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


/**
 * 
 */