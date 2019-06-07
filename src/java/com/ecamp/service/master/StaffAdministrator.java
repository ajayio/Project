package com.ecamp.service.master;

import com.ecamp.bean.master.StaffBean;

import com.ecamp.dao.master.StaffDAO;
import com.ecamp.dao.master.StatusDAO;

import com.ecamp.bean.master.UserBean;
import com.ecamp.bean.master.DepartmentBean;
import com.ecamp.bean.master.StatusBean;

import java.util.ArrayList;

public class StaffAdministrator {
	public String addStaff(StaffBean staffBean) {
		
		
		String result="FAILURE";
		if (staffBean==null ||staffBean.getStaffName()==null || staffBean.getStaffName().isEmpty() || staffBean.getDepartmentBean().getDepartmentId() == 0 || staffBean.getStatusBean().getStatusId() == 0){
			result = "INVALID";
		}
		else if(new StaffDAO().addStaff(staffBean)){
			result = "SUCCESS";
		}
		return result;
		
	}
	public String updateStatus(StaffBean staffBean)  {
		String result = "INVALID";
		if(staffBean != null && staffBean.getStaffName() != null && !staffBean.getStaffName().isEmpty() || staffBean.getDepartmentBean().getDepartmentId() > 0 || staffBean.getStatusBean().getStatusId() > 0){
			result = new StaffDAO().updateStatus(staffBean);
		}
		return result;
	}
	
	public String updateDepartment(StaffBean staffBean)  {
		String result = "INVALID";
		if(staffBean != null && staffBean.getStaffName() != null && !staffBean.getStaffName().isEmpty() || staffBean.getDepartmentBean().getDepartmentId() > 0 || staffBean.getStatusBean().getStatusId() > 0){
			result = new StaffDAO().updateDepartment(staffBean);
		}
		return result;
	}
	
	public StaffBean viewStaffById(int userId) {
		 StaffBean staffBean = null;
		 if (userId > 0) {
			 staffBean = new StaffDAO().viewStaffById(userId);
		 }
		 return staffBean;
	}
	public ArrayList<StaffBean> viewStaffByName(String staffName){
		ArrayList<StaffBean> staffBean = null;
		 if (staffName != null) {
			 staffBean = new StaffDAO().viewStaffByName(staffName);
		 }
		 return staffBean;
	}
	public ArrayList<StaffBean> viewStaffByDepartment(byte departmentId){
		ArrayList<StaffBean> staffBean = null;
		 if (departmentId > 0) {
			 staffBean = new StaffDAO().viewStaffByDepartment(departmentId);
		 }
		 return staffBean;
	}
	
	public ArrayList<StaffBean> viewStaffByStatus(byte statusId){
		ArrayList<StaffBean> staffBean = null;
		 if (statusId > 0) {
			 staffBean = new StaffDAO().viewStaffByStatus(statusId);
		 }
		 return staffBean;
	}
	
	public ArrayList<StaffBean> viewStaff(){
		return new StaffDAO().viewStaff();
	}
	public static void main(String[] args) {
StaffBean staffBean = new StaffBean();
		
		UserBean userBean = new UserBean();
		 userBean.setUserId(1); 
		staffBean.setUserBean(userBean);
		staffBean.setStaffName("ezhilone");
		DepartmentBean departmentBean = new DepartmentBean();
		departmentBean.setDepartmentId((byte)9);
		staffBean.setDepartmentBean(departmentBean);
		StatusBean statusBean = new StatusBean();
		statusBean.setStatusId((byte)122);
		staffBean.setStatusBean(statusBean);
		
		//System.out.println(new StaffAdministrator().addStaff(staffBean));
                //System.out.println(new StaffAdministrator().updateDepartment(staffBean));
                System.out.println(new StaffAdministrator().viewStaff());
	}
}
