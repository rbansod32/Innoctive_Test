package com.rushabh.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.rushabh.dao.TestDaoImpl;

@SuppressWarnings("serial")
public class InitAction extends ActionSupport {

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
	
	private String l_returnMsg;
	
    public String execute() {
        return SUCCESS;
    }
    
    public String SaveAction() {
    	TestDaoImpl testDaoImpl = new TestDaoImpl();
    	l_returnMsg = testDaoImpl.SaveData(firstName, middleName, lastName, gender, dob, mobileNo, altMobileNo, emailId, maritalStatus, bloodGroup);
        System.out.println(l_returnMsg);
    	return SUCCESS;
    }

    public String ShowAction() {
    	TestDaoImpl testDaoImpl = new TestDaoImpl();
    	l_returnMsg = testDaoImpl.ShowData();
    	System.out.println(l_returnMsg);
    	return SUCCESS;
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

	public String getL_returnMsg() {
		return l_returnMsg;
	}

	public void setL_returnMsg(String l_returnMsg) {
		this.l_returnMsg = l_returnMsg;
	}
    
	
	
}
