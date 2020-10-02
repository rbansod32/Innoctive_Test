package com.rushabh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rushabh.database.DatabaseConnection;
import com.rushabh.utilities.Utils;

@SuppressWarnings("unchecked")
public class TestDaoImpl {

	public String SaveData(String p_firstName, String p_middleName, String p_lastName, String p_gender, String p_dob, String p_mobileNo, 
			String p_altMobileNo, String p_emailId, String p_maritalStatus, String p_bloodGroup) {

		DatabaseConnection l_dbCon = new DatabaseConnection();

		Statement l_stmt = null;

		String l_returnMsg = "";
		String l_sql = "";
		
		int l_insertCount = 0;

		try {
			Connection l_conn = l_dbCon.FetchConnection();

			p_firstName = "".equals(p_firstName.trim()) ? "" : p_firstName;
			p_middleName = "".equals(p_middleName.trim()) ? "" : p_middleName;
			p_lastName = "".equals(p_lastName.trim()) ? "" : p_lastName;
			p_gender = "".equals(p_gender.trim()) ? "" : p_gender;
			p_dob = "".equals(p_dob.trim()) ? null : Utils.ConvertDateFormat(p_dob, "dd-MMM-yyyy", "yyyy-MM-dd");
			p_mobileNo = "".equals(p_mobileNo.trim()) ? null : p_mobileNo;
			p_altMobileNo = "".equals(p_firstName.trim()) ? null : p_altMobileNo;
			p_emailId = "".equals(p_emailId.trim()) ? null : p_emailId;
			p_maritalStatus = "".equals(p_maritalStatus.trim()) ? null : p_maritalStatus;
			p_bloodGroup = "".equals(p_bloodGroup.trim()) ? null : p_bloodGroup;

			l_stmt = l_conn.createStatement();

			l_sql = "INSERT INTO Employee(firstName,middleName,lastName,gender,dob,mobileNo,altMobileNo,emailId,maritalStatus,bloodGroup)"
					+ "VALUES('" + p_firstName + "','" + p_middleName + "','" + p_lastName + "','" + p_gender + "','" + p_dob
					+ "'," + p_mobileNo + "," + p_altMobileNo + ",'" + p_emailId + "','" + p_maritalStatus + "','" + p_bloodGroup+ "');";

			l_insertCount = l_stmt.executeUpdate(l_sql);

			if (l_insertCount > 0) {
				l_returnMsg = "Employee Added...!!!";
			} else {
				l_returnMsg = "Error while adding employee...!!!";
			}

		} catch (SQLException e) {
			l_returnMsg = "Error while adding employee...!!!";
			e.printStackTrace();
		} catch (Exception e) {
			l_returnMsg = "Error while adding employee...!!!";
			e.printStackTrace();
		}

		return l_returnMsg;
	}

	

	public JSONObject ShowData() {
		DatabaseConnection l_dbCon = new DatabaseConnection();

		Statement l_stmt = null;

		Connection l_conn = l_dbCon.FetchConnection();

		String l_sql = "";
		String l_eid = "";
		String l_firstName = "";
		String l_lastName = "";
		String l_emailId = "";

		double l_mobileNo = 0.0;
		
		JSONObject l_tableData = new JSONObject();

		JSONArray l_tableRows = new JSONArray();

		try {
			l_stmt = l_conn.createStatement();

			l_sql = "SELECT emp_id,firstName,lastName,mobileNo,emailId FROM Employee";

			ResultSet l_rs = l_stmt.executeQuery(l_sql);

			while (l_rs.next()) {

				JSONObject l_row = new JSONObject();

				l_eid = l_rs.getString(1);
				l_firstName = l_rs.getString(2);
				l_lastName = l_rs.getString(3);
				l_mobileNo = l_rs.getDouble(4);
				l_emailId = l_rs.getString(5);

				l_row.put("eid", l_eid);
				l_row.put("firstName", l_firstName);
				l_row.put("lastName", l_lastName);
				l_row.put("mobileNo", l_mobileNo);
				l_row.put("emailId", l_emailId);

				l_tableRows.add(l_row);

			}

			l_tableData.put("tableRows", l_tableRows);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return l_tableData;
	}

	public JSONObject ViewDetails(String p_eid) {
		DatabaseConnection l_dbCon = new DatabaseConnection();

		Statement l_stmt = null;

		Connection l_conn = l_dbCon.FetchConnection();

		String l_sql = "";
		String l_firstName = "";
		String l_middleName = "";
		String l_lastName = "";
		String l_gender = "";
		String l_dob = "";
		String l_emailId = "";
		String l_maritalStatus = "";
		String l_bloodGroup = "";

		double l_mobileNo = 0.0;
		double l_altMobileNo = 0.0;
		
		JSONObject l_tableData = new JSONObject();

		JSONArray l_employeeData = new JSONArray();

		try {
			l_stmt = l_conn.createStatement();

			l_sql = "SELECT firstName,middleName,lastName,gender,dob,mobileNo,altMobileNo,emailId,maritalStatus,bloodGroup FROM Employee where emp_id = "+ p_eid;

			ResultSet l_rs = l_stmt.executeQuery(l_sql);

			while (l_rs.next()) {

				JSONObject l_row = new JSONObject();

				l_firstName = l_rs.getString(1);
				l_middleName = l_rs.getString(2);
				l_lastName = l_rs.getString(3);
				l_gender = l_rs.getString(4);
				l_dob = l_rs.getString(5);
				l_mobileNo = l_rs.getDouble(6);
				l_altMobileNo = l_rs.getDouble(7);
				l_emailId = l_rs.getString(8);
				l_maritalStatus = l_rs.getString(9);
				l_bloodGroup = l_rs.getString(10);

				if("M".equals(l_gender)) {
					l_gender = "Male";
				}else if("F".equals(l_gender)) {
					l_gender = "Female";
				}
				
				if("S".equals(l_maritalStatus)) {
					l_maritalStatus = "Single";
				}else if("M".equals(l_maritalStatus)) {
					l_maritalStatus = "Married";
				}
				
				l_dob = Utils.ConvertDateFormat(l_dob, "yyyy-MM-dd", "dd-MMM-yyyy");
				
				l_row.put("firstName", l_firstName);
				l_row.put("middleName", l_middleName);
				l_row.put("lastName", l_lastName);
				l_row.put("gender", l_gender);
				l_row.put("dob", l_dob);
				l_row.put("mobileNo", l_mobileNo);
				l_row.put("altMobileNo", l_altMobileNo);
				l_row.put("emailId", l_emailId);
				l_row.put("maritalStatus", l_maritalStatus);
				l_row.put("bloodGroup", l_bloodGroup);

				l_employeeData.add(l_row);
				
			}

			l_tableData.put("employeeRows", l_employeeData);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return l_tableData;
	}

	public String DeleteData(String p_eid) {

		DatabaseConnection l_dbCon = new DatabaseConnection();

		Statement l_stmt = null;

		String l_sql = "";
		String l_returnMsg = "";

		int l_deleteCount = 0;

		try {
			Connection l_conn = l_dbCon.FetchConnection();

			l_stmt = l_conn.createStatement();

			l_sql = "DELETE FROM Employee where emp_id = " + p_eid;

			l_deleteCount = l_stmt.executeUpdate(l_sql);

			if (l_deleteCount > 0) {
				l_returnMsg = "Employee Deleted...!!!";
			} else {
				l_returnMsg = "Error while deleting employee...!!!";
			}

		} catch (SQLException e) {
			l_returnMsg = "Error while deleting employee...!!!";
			e.printStackTrace();
		} catch (Exception e) {
			l_returnMsg = "Error while deleting employee...!!!";
			e.printStackTrace();
		}

		return l_returnMsg;
	}

}
