package com.ecamp.service.master;
import com.ecamp.bean.master.CourseBean;
import com.ecamp.bean.master.DegreeBean;
import com.ecamp.dao.master.CourseDAO; 
import com.ecamp.dao.master.DegreeDAO;
import java.util.ArrayList;
public class CourseAdministrator {
    
    public String addDegree(CourseBean courseBean) {
        String result="FAILURE";
        if (courseBean==null || courseBean.getCourseName()==null || courseBean.getCourseName().isEmpty()){    
            result = "INVALID";
        }
        else if(new CourseDAO().addCourse(courseBean)){
            result = "SUCCESS";
        } 
        return result;
    }
    public String updateCourse(CourseBean courseBean) {
            
        String result = "INVALID";           
                
        if(courseBean != null && courseBean.getCourseName() != null && !courseBean.getCourseName().isEmpty()){  
            result = new CourseDAO().updateCourse(courseBean);
        }
        return result;            
    }
    public CourseBean viewDegree(byte courseId) {
 
        CourseBean courseBean = null; 
        if (courseId > 0) {    
            courseBean = new CourseDAO().viewCourse(courseId); 
        }
        return courseBean;
    }    
    public CourseBean viewCourse(String courseName) {                 
 	CourseBean courseBean = null;
 	if (courseName != null) {
            courseBean = new CourseDAO().viewCourse(courseName); 
 
        }
 	return courseBean;
    }         
    public CourseBean viewFullName(String fullName) {                 
 	CourseBean courseBean = null;
 	if (fullName != null) {
            courseBean = new CourseDAO().viewCourse(fullName); 
 
        }
 	return courseBean;
    }
    
    public ArrayList<CourseBean> viewCourseDegree(){
        return new CourseDAO().viewCourseDegree((byte)0);
    }
    
    
          public ArrayList<CourseBean> viewCourseAll(){
	 return new CourseDAO().viewCourseAll();
    }     

    public static void main(String[] args) {
                CourseBean courseBean=new CourseBean();
            courseBean.setCourseId((byte)3);
            courseBean.setCourseName("BSc");       
            courseBean.setFullName("Bachelor of Science");
        DegreeBean degreeBean = new DegreeBean();
            degreeBean.setDegreeId((byte)1);
            courseBean.setDegreeBean(degreeBean);
          //System.out.println(new CourseDAO().addCourse(courseBean));
        //System.out.println(new CourseDAO().updateCourse(courseBean));
        //System.out.println(new CourseDAO().viewCourseDegree((byte)1));
        System.out.println(new CourseDAO().viewCourseAll());
    }           
}
