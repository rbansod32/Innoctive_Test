<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html>
    <head>
        <title>Struts 2 hello world example</title>
        <s:head/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
 
    <body>
        <h1><s:text name="welcome" /></h1>
        <s:if test="hasActionErrors()">
            <div id="fieldErrors">
                <s:actionerror/>
            </div>
        </s:if>
        
        <%-- <s:form action="testAction" namespace="/" method="post" name="myForm" theme="xhtml">
            <s:textfield name="name" size="40" maxlength="40"/>
            <s:textfield name="name" size="40" maxlength="40"/>
            
            <s:submit key="submit" />
        </s:form> --%>
        
        <table>
        	<tr>
        		<td>First Name</td>
        		<td><input type="text" id='firstName'/></td>
        		
        		<td>Middle Name</td>
        		<td><input type="text" id='middleName'/></td>
        		
        		<td>Last Name</td>
        		<td><input type="text" id='lastName'/></td>
        		
        		<td>Gender</td>
        		<td>
        			<select id="gender">
        				<option value="M">Male</option>
        				<option value="F">Female</option>
        			</select>
        		</td>
        	</tr>
        	
        	<tr>
        		<td>DOB</td>
        		<td><input type="text" id='dob'/></td>
        		
        		<td>Mobile No</td>
        		<td><input type="text" id='mobileNo'/></td>
        		
        		<td>Alt Mobile No</td>
        		<td><input type="text" id='altMobileNo'/></td>
        		
        		<td>Email Id</td>
        		<td><input type="text" id='emailId'/></td>
        	</tr>
        	
        	<tr>
        		<td>Marital Status</td>
        		<td>
        			<select id="maritalStatus">
        				<option value="S">Single</option>
        				<option value="M">Married</option>
        			</select>
        		</td>
        		
        		<td>Blood Group</td>
        		<td>
        			<select id="bloodGroup">
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
        			<button id="saveEmpData">Save Data</button>
        		</td>
        		<td>
        			<button id="showEmpData">Show Employee</button>
        		</td>
        	</tr>
        	
        </table>
        
        <table id="employeeData">
        
        </table>
        
    </body>
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		
    		$("#employeeData").hide();	
    		
    	  $("#saveEmpData").click(function(){
    	    
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
    			type : "GET",
    			url : "SaveAction",
    			data:{
    				firstName:firstName,
    				middleName:middleName,
    				lastName:lastName,
    				gender:gender,
    				dob:dob,
    				mobileNo:mobileNo,
    				altMobileNo:altMobileNo,
    				emailId:emailId,
    				maritalStatus:maritalStatus,
    				bloodGroup:bloodGroup
    			},
    			success : function(itr) {
    				alert(itr.l_returnMsg);
    			},
    			error : function(itr) {
    				alert("No values found..!!");
    			}
    		});
    	    
    	    
    	  });
    	  
    	  $("#showEmpData").click(function(){
    		  $.ajax({
      			type : "GET",
      			url : "ShowAction",
      			success : function(itr) {
      				
      				$("#employeeData").show();
      				
      				alert(itr.l_returnMsg);
      			},
      			error : function(itr) {
      				alert("No values found..!!");
      			}
      		});
    	  
    	  });
    	  
    	});
    </script>
    
</html> 










