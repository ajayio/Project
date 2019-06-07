package com.ecamp.service.master;

import java.util.ArrayList;

import com.ecamp.bean.master.StudentBean;
import com.ecamp.dao.master.StudentDAO;

public class StudentAdministrator {
public String addStudent(StudentBean studentBean) {
				
		String result="FAILURE";
		if (studentBean==null ||studentBean.getStudentName()==null || studentBean.getStudentName().isEmpty() || studentBean.getBatchBean().getBatchId() == 0 || studentBean.getStatusBean().getStatusId() == 0){
			result = "INVALID";
		}
		else if(new StudentDAO().addStudent(studentBean)){
			result = "SUCCESS";
		}
		return result;
		
	}
public String updateStatus(StudentBean studentBean)  {
	String result = "INVALID";
	if(studentBean != null && studentBean.getStudentName() != null && !studentBean.getStudentName().isEmpty() || studentBean.getBatchBean().getBatchId() > 0 || studentBean.getStatusBean().getStatusId() > 0){
		result = new StudentDAO().updateStatus(studentBean);
	}
	return result;
}
public String updateBatch(StudentBean studentBean)  {
	String result = "INVALID";
	if(studentBean != null && studentBean.getStudentName() != null && !studentBean.getStudentName().isEmpty() || studentBean.getBatchBean().getBatchId() > 0 || studentBean.getStatusBean().getStatusId() > 0){
		result = new StudentDAO().updateBatch(studentBean);
	}
	return result;
}
public StudentBean viewStudentById(int userId) {
	 StudentBean studentBean = null;
	 if (userId > 0) {
		 studentBean = new StudentDAO().viewStudentById(userId);
	 }
	 return studentBean;
}
public ArrayList<StudentBean> viewStudentByName(String studentName){
	ArrayList<StudentBean> studentBean = null;
	 if (studentName != null) {
		 studentBean = new StudentDAO().viewStudentByName(studentName);
	 }
	 return studentBean;
}
public ArrayList<StudentBean> viewStudentByBatch(byte BatchId){
	ArrayList<StudentBean> studentBean = null;
	 if (BatchId > 0) {
		 studentBean = new StudentDAO().viewStudentByBatch(BatchId);
	 }
	 return studentBean;
}
public ArrayList<StudentBean> viewStudentByStatus(byte statusId){
	ArrayList<StudentBean> studentBean = null;
	 if (statusId > 0) {
		 studentBean = new StudentDAO().viewStudentByStatus(statusId);
	 }
	 return studentBean;
}
public ArrayList<StudentBean> viewStudent(){
	return new StudentDAO().viewStudent();
}
}
