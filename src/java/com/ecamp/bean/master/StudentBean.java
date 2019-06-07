package com.ecamp.bean.master;

public class StudentBean {
	private UserBean userBean;
	private String studentName;
	private BatchBean batchBean;
	private StatusBean statusBean;
	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public BatchBean getBatchBean() {
		return batchBean;
	}
	public void setBatchBean(BatchBean batchBean) {
		this.batchBean = batchBean;
	}
	public StatusBean getStatusBean() {
		return statusBean;
	}
	public void setStatusBean(StatusBean statusBean) {
		this.statusBean = statusBean;
	}
}
