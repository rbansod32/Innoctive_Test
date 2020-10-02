package com.rushabh.Action;

import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.rushabh.dao.TestDaoImpl;

@SuppressWarnings("serial")
public class InitAction extends ActionSupport {

	private String eid;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String dob;
	private String mobileNo;
	private String altMobileNo;
	private String emailId;
	private String maritalStatus;
	private String bloodGroup;
	
	private String returnMsg;
	
    public String execute() {
        return SUCCESS;
    }
        
    public String SaveAction() {
    	TestDaoImpl l_testDaoImpl = new TestDaoImpl();
    	returnMsg = l_testDaoImpl.SaveData(firstName, middleName, lastName, gender, dob, mobileNo, altMobileNo, emailId, maritalStatus, bloodGroup);
    	return SUCCESS;
    }

    public String ShowAction() {
    	TestDaoImpl l_testDaoImpl = new TestDaoImpl();
    	JSONObject l_tableData = new JSONObject();
    	l_tableData = l_testDaoImpl.ShowData();
    	returnMsg = l_tableData.toString();
    	return SUCCESS;
    }
    
    public String ViewDetailsAction() {
    	TestDaoImpl l_testDaoImpl = new TestDaoImpl();
    	JSONObject l_detailsData = new JSONObject();
    	l_detailsData = l_testDaoImpl.ViewDetails(eid);
    	returnMsg = l_detailsData.toString();
    	return SUCCESS;
    }
    
    public String DeleteAction() {
    	TestDaoImpl l_testDaoImpl = new TestDaoImpl();
    	returnMsg = l_testDaoImpl.DeleteData(eid);
    	return SUCCESS;
    }
    
    
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAltMobileNo() {
		return altMobileNo;
	}

	public void setAltMobileNo(String altMobileNo) {
		this.altMobileNo = altMobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
}
