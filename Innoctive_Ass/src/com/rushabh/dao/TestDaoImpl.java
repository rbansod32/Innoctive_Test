package com.rushabh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rushabh.database.DatabaseConnection;

public class TestDaoImpl{

	public String SaveData(String firstName, String middleName, String lastName, String gender, String dob, String mobileNo, String altMobileNo, String emailId, String maritalStatus, String bloodGroup) {
		
		DatabaseConnection dbCon = new DatabaseConnection();
		
		Statement stmt = null;
		
		String l_returnMsg = "";
		
		int l_insertCount = 0;
		
		try {
			Connection conn = dbCon.FetchConnection();
			
			firstName = "".equals(firstName.trim()) ? null : firstName;
			middleName = "".equals(middleName.trim()) ? null : middleName;
			lastName = "".equals(lastName.trim()) ? null : lastName;
			gender = "".equals(gender.trim()) ? null : gender;
			dob = "".equals(dob.trim()) ? null : ConvertDateFormat(dob, "dd-MM-yyyy", "yyyy-MM-dd");
			mobileNo = "".equals(mobileNo.trim()) ? null : mobileNo;
			altMobileNo = "".equals(firstName.trim()) ? null : altMobileNo;
			emailId = "".equals(emailId.trim()) ? null : emailId;
			maritalStatus = "".equals(maritalStatus.trim()) ? null : maritalStatus;
			bloodGroup = "".equals(bloodGroup.trim()) ? null : bloodGroup;
			
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO Employee(firstName,middleName,lastName,gender,dob,mobileNo,altMobileNo,emailId,maritalStatus,bloodGroup)"
					+ "VALUES('"+firstName+"','"+middleName+"','"+lastName+"','"+gender+"','"+dob+"',"+mobileNo+","+altMobileNo+",'"+emailId+"','"+maritalStatus+"','"+bloodGroup+"');";
			
			System.out.println("Insert Statement : " + sql);
			
			l_insertCount = stmt.executeUpdate(sql);
			
			if(l_insertCount>0) {
				l_returnMsg = "Employee Added...!!!";
			}else {
				l_returnMsg = "Error while adding employee...!!!";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return l_returnMsg;
	}
	
	public String ConvertDateFormat(String p_date, String p_inputFormat, String p_outputFormat) {
		String l_returnDate = "";

		try {
			Date l_date = new SimpleDateFormat(p_inputFormat).parse(p_date);
			
			DateFormat dateFormat = new SimpleDateFormat(p_outputFormat);  
			l_returnDate = dateFormat.format(l_date);  
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return l_returnDate;
	}

	public String ShowData() {
		DatabaseConnection dbCon = new DatabaseConnection();
		
		Statement stmt = null;
				
		Connection conn = dbCon.FetchConnection();
		
		String firstName="";
		String middleName="";
		String lastName="";
		String gender="";
		String dob="";
		String mobileNo="";
		String altMobileNo="";
		String emailId="";
		String maritalStatus="";
		String bloodGroup="";
		
		String tableRow = "";
		
		try {
			stmt = conn.createStatement();
			
			String sql = "SELECT emp_id,firstName,middleName,lastName,gender,dob,mobileNo,altMobileNo,emailId,maritalStatus,bloodGroup FROM Employee";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				firstName=rs.getString(1);
				middleName=rs.getString(2);
				lastName=rs.getString(3);
				gender=rs.getString(4);
				dob=rs.getString(5);
				mobileNo=rs.getString(6);
				altMobileNo=rs.getString(7);
				emailId=rs.getString(8);
				maritalStatus=rs.getString(9);
				bloodGroup=rs.getString(10);
				
				tableRow += "<tr>"
							+ "<td>"+firstName+"</td>"
							+ "<td>"+middleName+"</td>"
							+ "<td>"+lastName+"</td>"
							+ "<td>"+gender+"</td>"
							+ "<td>"+dob+"</td>"
							+ "<td>"+mobileNo+"</td>"
							+ "<td>"+altMobileNo+"</td>"
							+ "<td>"+emailId+"</td>"
							+ "<td>"+maritalStatus+"</td>"
							+ "<td>"+bloodGroup+"</td>"
						+ "</tr>";
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return tableRow;
	}

}
