package com.ecamp.service.master;

import com.ecamp.bean.master.StatusBean;
import com.ecamp.dao.master.StatusDAO;
import java.util.ArrayList;


public class StatusAdministrator {
	public String addStatus(StatusBean statusBean) {
		String result="FAILURE";
		if (statusBean==null ||statusBean.getStatusName()==null ||statusBean.getStatusName().isEmpty()){
			result = "INVALID";
		}
		else if(new StatusDAO().addStatus(statusBean)){
			result = "SUCCESS";
		}
		return result;
		
	}
	public String updateStatus(StatusBean statusBean) {
		String result = "INVALID";
		if(statusBean != null && statusBean.getStatusName() != null && !statusBean.getStatusName().isEmpty()){
			result = new StatusDAO().updateStatus(statusBean);
		}
		return result;
	}
	public StatusBean viewStatus(byte statusId) {
			 StatusBean statusBean = null;
			 if (statusId > 0) {
				 statusBean = new StatusDAO().viewStatus(statusId);
			 }
			 return statusBean;
	}
	public StatusBean viewStatus(String statusName) {
		StatusBean statusBean = null;
		if (statusName != null) {
			statusBean = new StatusDAO().viewStatus(statusName);
		}
		return statusBean;
	}
	public ArrayList<StatusBean> viewStatus(){
		return new StatusDAO().viewStatus();
	}
	public static void main(String[] args) {
		StatusBean statusBean=new StatusBean();
		//System.out.println(new StatusAdministrator().viewStatus("ACTIVE").ge   tStatusId());
		//statusBean.setStatusId(new StatusAdministrator().viewStatus("ACTIVE").getStatusId());
		statusBean.setStatusName("ACTIVE");
		statusBean.setDescription("ACTIVEACCOUNT ///");
		//System.out.println(new StatusAdministrator().addStatus(statusBean));
		//System.out.println(new StatusAdministrator().updateStatus(statusBean));
		System.out.println(new StatusAdministrator().viewStatus((byte)1));
		System.out.println(new StatusAdministrator().viewStatus((byte)1).getDescription());
		System.out.println(new StatusAdministrator().viewStatus("LIVE").getDescription());
		System.out.println(new StatusAdministrator().viewStatus().iterator().next().getDescription());
		System.out.println(new StatusAdministrator().viewStatus());
	} 
}
