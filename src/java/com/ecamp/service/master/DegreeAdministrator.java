package com.ecamp.service.master;
import com.ecamp.bean.master.DegreeBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.dao.master.DegreeDAO; 
import java.util.ArrayList;
public class DegreeAdministrator {
    
    public String addDegree(DegreeBean degreeBean) {
        String result="FAILURE";
        if (degreeBean==null || degreeBean.getDegreeName()==null || degreeBean.getDegreeName().isEmpty()){    
            result = "INVALID";
        }
        else if(new DegreeDAO().addDegree(degreeBean)){
            result = "SUCCESS";
        } 
        return result;
    }
    public String updateDegree(DegreeBean degreeBean) {
            
        String result = "INVALID";           
                
        if(degreeBean != null && degreeBean.getDegreeName() != null && !degreeBean.getDegreeName().isEmpty()){  
            result = new DegreeDAO().updateDegree(degreeBean);
        }
        return result;            
    }
    public DegreeBean viewDegree(byte degreeId) {
 
        DegreeBean degreeBean = null; 
        if (degreeId > 0) {    
            degreeBean = new DegreeDAO().viewDegree(degreeId); 
        }
        return degreeBean;
    }    
    public DegreeBean viewDegreeName(String degreeName) {                 
 	DegreeBean degreeBean = null;
 	if (degreeName != null) {
            degreeBean = new DegreeDAO().viewDegreeName(degreeName); 
 
        }
 	return degreeBean;
    }         
    public DegreeBean viewDegreeFullName(String fullName) {                 
 	DegreeBean degreeBean = null;
 	if (fullName != null) {
            degreeBean = new DegreeDAO().viewDegreeName(fullName); 
 
        }
 	return degreeBean;
    }
    public ArrayList<DegreeBean> viewDegree(){
 	 return new DegreeDAO().viewDegree();
    }     
    public static void main(String[] args) {
         DegreeBean degreeBean=new DegreeBean();
        //degreeBean.setDegreeId((byte)2);
        //degreeBean.setDegreeName("pg");
        //degreeBean.setFullName("pnder");
        //System.out.println(new DegreeDAO().addDegree(degreeBean));
        //System.out.println(new DegreeDAO().updateDegree(degreeBean));
        //degreeBean.setFullName("Below");
      //  degreeBean.setDegreeId((byte)2);
      //  degreeBean.setFullName("ost");
       // System.out.println(new DegreeDAO().updateDegree(degreeBean));
        //System.out.println(new DegreeDAO().viewDegree("2"));
        //System.out.println(new DegreeDAO().viewDegree());
        System.out.println(new DegreeDAO().viewDegree());
    }           
}
