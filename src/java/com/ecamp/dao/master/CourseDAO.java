
package com.ecamp.dao.master;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ecamp.util.DBUtil;
import com.ecamp.bean.master.DegreeBean;
import com.ecamp.bean.master.CourseBean;
public class CourseDAO {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;
    CourseBean courseBean=null;
    String errorMessage=null;
    //add
    
    public boolean addCourse(CourseBean courseBean){
        int count=0;
        boolean result=false;
       
        try{
             connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("insert into ecamp_tbl_course(courseId,courseName,fullName,degreeId) values(?,?,?,?)");
            preparedStatement.setByte(1, courseBean.getCourseId());
            preparedStatement.setString(2, courseBean.getCourseName());
            preparedStatement.setString(3, courseBean.getFullName());
            preparedStatement.setByte(4, courseBean.getDegreeBean().getDegreeId());
            
            count=preparedStatement.executeUpdate();
            if(count>0){
                result=true;
            }       
        }catch (Exception e){
            System.out.println("Error in CourseDAO | Add");              
        }
        finally{
            errorMessage="Error in CourseDAO | Add | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, null, errorMessage);
            return result;
        }
    }
    //Update
    
    public String updateCourse(CourseBean courseBean){
        String result="FAILURE";
        
        try{
            connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("update ecamp_tbl_course set fullName=? where degreeId=?");
            preparedStatement.setString(1, courseBean.getFullName());
            preparedStatement.setByte(2, courseBean.getDegreeBean().getDegreeId());
            if(preparedStatement.executeUpdate()>0){
                result="SUCCESS";
            }
            
        }catch(Exception e){
            System.out.println("Error in CourseDAO | Update");
        }
        finally{
            errorMessage="Error in DegreeDAO | Update | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, null, errorMessage);
            return result;
        }
    }
    //View Id
    
    public CourseBean viewCourse(byte courseId){
        courseBean=null;
        try{
            connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("select courseName,fullName,degreeId from ecamp_tbl_course where courseId=?");
            preparedStatement.setByte(1, courseBean.getCourseId());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                courseBean=new CourseBean();
                courseBean.getCourseName();
                courseBean.getClass();
                courseBean.getDegreeBean().getDegreeId();
                courseBean.setCourseId(courseId);
                courseBean.setCourseName(resultSet.getString("courseName"));
                courseBean.setFullName(resultSet.getString("fullName"));
                DegreeBean degreeBean=new DegreeBean();
                courseBean.getDegreeBean().setDegreeId((byte)0);
            }
        }catch(Exception e){
            System.out.println("Error in CourseDAO | View | Id");
        }
        finally{
            errorMessage ="Error in CourseDAO | View | CourseId | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            return courseBean;
        }
    }
    //view name
     public CourseBean viewCourse(String CourseName){
        courseBean=null;
        try{
            connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("select fullName,degreeId from ecamp_tbl_course where courseName=?");
            preparedStatement.setString(1, courseBean.getCourseName());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                courseBean=new CourseBean();
                courseBean.getCourseId();
                courseBean.getClass();
                courseBean.getDegreeBean().getDegreeId();
                courseBean.setCourseId(resultSet.getByte("courseId"));
                courseBean.setCourseName(CourseName);
                courseBean.setFullName(resultSet.getString("fullName"));
                DegreeBean degreeBean=new DegreeBean();
                courseBean.getDegreeBean().setDegreeId(resultSet.getByte("DegreeId"));
            }
        }catch(Exception e){
            System.out.println("Error in CourseDAO | View | Name");
        }
        finally{
            errorMessage ="Error in CourseDAO | View | CourseId | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            return courseBean;
        }
    }
    //view fullname
     public CourseBean viewFullName(String fullName){
        courseBean=null;
        try{
            connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("select CourseName,degreeId from ecamp_tbl_course where courseName=?");
            preparedStatement.setString(1, courseBean.getCourseName());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                courseBean=new CourseBean();
                courseBean.getCourseId();
                courseBean.getClass();
                courseBean.getDegreeBean().getDegreeId();
                courseBean.setCourseId(resultSet.getByte("courseId"));
                courseBean.setCourseName(fullName);
              courseBean.setCourseName(resultSet.getString("courseName"));
                //object
                DegreeBean degreeBean=new DegreeBean();
                courseBean.getDegreeBean().setDegreeId((byte)0);
                //one error may defined
            }
        }catch(Exception e){
            System.out.println("Error in CourseDAO | View | Name");
        }
        finally{
            errorMessage ="Error in CourseDAO | View | CourseId | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            return courseBean;
        }
    }
     //view by degree id
     public ArrayList<CourseBean> viewCourseDegree(byte degreeId){
        courseBean=null;
 	ArrayList<CourseBean> courseList=new ArrayList<CourseBean>();        
        try{            
        connection=DBUtil.getDBConnection();    
        preparedStatement=connection.prepareStatement ("select courseId, courseName, fullName from ecamp_tbl_course where degreeId =?");           
                    preparedStatement.setByte(1, degreeId);                    
                     resultSet=preparedStatement.executeQuery();   
            while(resultSet.next()){                 
                courseBean=new CourseBean();
               courseBean.setCourseName(resultSet.getString("courseName"));
               courseBean.setFullName(resultSet.getString("fullName"));
               courseList.add(courseBean);
            }         }
        catch(Exception e){
            System.out.println("Error in DegreeDAO | View | All");
        }         finally{
            errorMessage="Error in DegreeDAO | View | All | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);             
            return courseList;
        } 
     }
     
     
              //view all
   public ArrayList<CourseBean> viewCourseAll(){
        courseBean=null;
 	ArrayList<CourseBean> courseList=new ArrayList<CourseBean>();        
        try{            
        connection=DBUtil.getDBConnection();    
        preparedStatement=connection.prepareStatement ("select courseId, courseName, fullName, degreeId from ecamp_tbl_course");                               
        resultSet=preparedStatement.executeQuery();   
            while(resultSet.next()){                 
                courseBean=new CourseBean();
                courseBean.setCourseId(resultSet.getByte("courseId"));
                courseBean.setCourseName(resultSet.getString("courseName"));
                courseBean.setFullName(resultSet.getString("fullName"));
                DegreeBean degreeBean = new DegreeBean();
                    degreeBean.setDegreeId(resultSet.getByte("degreeId"));
                courseBean.setDegreeBean(degreeBean);
                courseList.add(courseBean);                
            }         }
        catch(Exception e){
            System.out.println("Error in DegreeDAO | View | All");
        }         
        finally{
            errorMessage="Error in DegreeDAO | View | All | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                         
        } 
     return courseList;
   
     }
    public static void main(String[]args){
//        CourseBean courseBean=new CourseBean();
//            courseBean.setCourseId((byte)3);
//            courseBean.setCourseName("BSc");       
//            courseBean.setFullName("Bachelor of Science");
//        DegreeBean degreeBean = new DegreeBean();
//            degreeBean.setDegreeId((byte)1);
//            courseBean.setDegreeBean(degreeBean);
//          //System.out.println(new CourseDAO().addCourse(courseBean));
//        //System.out.println(new CourseDAO().updateCourse(courseBean));
//        //System.out.println(new CourseDAO().viewCourseDegree((byte)1));
//        System.out.println(new CourseDAO().viewCourseAll());
//        
    }           
}
