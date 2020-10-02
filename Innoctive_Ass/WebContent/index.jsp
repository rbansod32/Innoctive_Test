<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html>

<head>
    <title>Employee Management</title>
    <s:head />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
	
    <style type="text/css">
        body{
        	background-color: #EAF0F1;
        }
        
        #appHeading{
        	text-align: center;
        }
        
        .textBold {
            font-weight: bold;
        }
        
    </style>
</head>

<body>
	<div id="appHeading">
		<h2 class="textBold">Single Page Employee Management Application</h2>
	</div>

    <div id="addEmployeeTab">
        <table class="table">
            <tr>
                <td class="textBold">First Name</td>
                <td><input type="text" id='firstName' class="form-control" /></td>

                <td class="textBold">Middle Name</td>
                <td><input type="text" id='middleName' class="form-control" /></td>

                <td class="textBold">Last Name</td>
                <td><input type="text" id='lastName' class="form-control" /></td>

                <td class="textBold">Gender</td>
                <td>
                    <select id="gender" class="form-control">
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td class="textBold">DOB</td>
                <td><input type="text" id='dob' class="form-control" /></td>

                <td class="textBold">Mobile No</td>
                <td><input type="text" id='mobileNo' class="form-control" /></td>

                <td class="textBold">Alternate Mobile No</td>
                <td><input type="text" id='altMobileNo' class="form-control" /></td>

                <td class="textBold">Email Id</td>
                <td><input type="text" id='emailId' class="form-control" /></td>
            </tr>

            <tr>
                <td class="textBold">Marital Status</td>
                <td>
                    <select id="maritalStatus" class="form-control">
                        <option value="S">Single</option>
                        <option value="M">Married</option>
                    </select>
                </td>

                <td class="textBold">Blood Group</td>
                <td>
                    <select id="bloodGroup" class="form-control">
                        <option value="A">A</option>
                        <option value="A+">A+</option>
                        <option value="B">B</option>
                        <option value="B+">B+</option>
                        <option value="AB">AB</option>
                        <option value="AB+">AB+</option>
                        <option value="O">O</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <button id="saveEmpData" class="form-control btn btn-success">Save Data</button>
                </td>
            </tr>

        </table>
    </div>


    <div id="showEmployeeTab">

        <table id="employeeData" class="table">

            <thead>
                <tr>
                    <th>Emp Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Mobile No</th>
                    <th>Email Id</th>
                    <th>View Action</th>
                    <th>Delete Action</th>
                </tr>
            </thead>

            <tbody></tbody>

        </table>

    </div>

  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Employee Details</h4>
        </div>
        <div class="modal-body">
          <table class="table table-bordered">
              <tr>
              	  <td class="textBold">First Name</td><td><p id="popupFirstName"></p></td>
                  <td class="textBold">Middle Name</td><td><p id="popupMiddleName"></p></td>
                  <td class="textBold">Last Name</td><td><p id="popupLastName"></p></td>
              </tr>
              
              <tr>
              	  <td class="textBold">Gender</td><td><p id="popupGender"></p></td>
                  <td class="textBold">Date of Birth</td><td><p id="popupDob"></p></td>
                  <td class="textBold">Marital Status</td><td><p id="popupMaritalStatus"></p></td>
              </tr>
              
              <tr>
				  <td class="textBold">Mobile No</td><td><p id="popupMobileNo"></p></td>              	  
                  <td class="textBold">Alternate MobileNo</td><td><p id="popupAltMobileNo"></p></td>
                  <td class="textBold">Email Id</td><td><p id="popupEmailId"></p></td>
              </tr>
              
              <tr>
				  <td class="textBold">Blood Group</td><td><p id="popupBloodGroup"></p></td>
              </tr>
          </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

</body>

<script type="text/javascript">
    $(document).ready(function() {

    	var date_input=$('#dob'); //our date input has the name "date"
        //var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        var options={
          format: 'dd-M-yyyy',
          todayHighlight: true,
          autoclose: true,
        };
        date_input.datepicker(options);
    	
        $.ajax({
            type: "POST",
            url: "ShowAction",
            success: function(data) {

                var returnMsg = data.returnMsg;

                var jsonObject = $.parseJSON(returnMsg);

                $.each(jsonObject, function(i, obj) {

                    $.each(obj, function(i, row) {
                        var rowData = "<tr><td>" + row.eid + "</td><td>" + row.firstName + "</td><td>" + row.lastName + "</td><td>" + row.mobileNo + "</td>" +
                            "<td>" + row.emailId + "</td><td><button class='form-control btn btn-info btn-sm' onclick='viewEmployee(" + row.eid + ")'>View</button></td>" +
                            "<td><button class='form-control btn btn-danger btn-sm' onclick='deleteEmployee(" + row.eid + ")'>Delete</button></td></tr>";

                        $("#employeeData tbody").append(rowData);
                    });

                });

            },
            error: function(data) {
                alert("error");
            }
        });


        $("#saveEmpData").click(function() {

            var firstName = $("#firstName").val();
            var middleName = $("#middleName").val();
            var lastName = $("#lastName").val();
            var gender = $("#gender").val();
            var dob = $("#dob").val();
            var mobileNo = $("#mobileNo").val();
            var altMobileNo = $("#altMobileNo").val();
            var emailId = $("#emailId").val();
            var maritalStatus = $("#maritalStatus").val();
            var bloodGroup = $("#bloodGroup").val();

            $.ajax({
                type: "POST",
                url: "SaveAction",
                dataType: "json",
                data: {
                    firstName: firstName,
                    middleName: middleName,
                    lastName: lastName,
                    gender: gender,
                    dob: dob,
                    mobileNo: mobileNo,
                    altMobileNo: altMobileNo,
                    emailId: emailId,
                    maritalStatus: maritalStatus,
                    bloodGroup: bloodGroup
                },
                success: function(data) {
                    alert(data.returnMsg);
                    location.reload();
                },
                error: function(data) {
                    alert("error");
                }
            });

        });

    });


    function viewEmployee(eid) {
        $.ajax({
            type: "POST",
            url: "ViewDetailsAction",
            data: {
                eid: eid
            },
            success: function(data) {

                var returnMsg = data.returnMsg;

                var jsonObject = $.parseJSON(returnMsg);

                $.each(jsonObject, function(i, obj) {

                    $.each(obj, function(i, row) {
                        
                        $("#popupFirstName").text(row.firstName);
                        $("#popupMiddleName").text(row.middleName);
                        $("#popupLastName").text(row.lastName);
                        $("#popupGender").text(row.gender);
                        $("#popupDob").text(row.dob);
                        $("#popupMaritalStatus").text(row.maritalStatus);
                        $("#popupMobileNo").text(row.mobileNo);
                        $("#popupAltMobileNo").text(row.altMobileNo);
                        $("#popupEmailId").text(row.emailId);
                        $("#popupBloodGroup").text(row.bloodGroup);
                                                
                    });

                });

                $("#myModal").modal();
            },
            error: function(data) {
                alert("error");
            }
        });
    }


    function deleteEmployee(eid) {
        $.ajax({
            type: "POST",
            url: "DeleteAction",
            dataType: "json",
            data: {
                eid: eid
            },
            success: function(data) {
                alert(data.returnMsg);
                location.reload();
            },
            error: function(data) {
                alert("error");
            }
        });
    }
</script>

</html>