package com.ecamp.dao.master;
import com.ecamp.bean.master.DegreeBean;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ecamp.util.DBUtil; 
import java.sql.SQLException; 
import java.util.ArrayList;
public class DegreeDAO {  
    Connection connection=null;    
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    DegreeBean degreeBean=null;
    String errorMessage=null;
    
    public boolean addDegree(DegreeBean degreeBean){                 
        int count=0;         
        boolean result=false;
      //add
      
        try {
         connection=DBUtil.getDBConnection();       
        preparedStatement = connection.prepareStatement("insert into ecamp_tbl_degree(degreeName,fullName)values(?,?)");                    
        preparedStatement.setString(1, degreeBean.getDegreeName());  
         preparedStatement.setString(2, degreeBean.getFullName());
        count=preparedStatement.executeUpdate();
        if(count>0){
            result=true;
        }            
 	} catch (Exception e) {
            System.out.println("Error in DegreeDAO | Add");
 	}    
        finally{
            errorMessage="Error in DegreeDAO | Add | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, null, errorMessage);  
            return result;
        }    
    } 
    //update
    
    public String updateDegree(DegreeBean degreeBean){        
        String result="FAILURE";
        
                      	try { 
                  connection=DBUtil.getDBConnection(); 
               preparedStatement = connection.prepareStatement("update ecamp_tbl_degree set fullName=? where degreeId=?");                
                preparedStatement.setString(1,degreeBean.getFullName()); 
                preparedStatement.setByte(2,degreeBean.getDegreeId()); 
               if(preparedStatement.executeUpdate()>0){  
                   result="SUCCESS";
            }        
 	} catch (Exception e) {  
            System.out.println("Error in DegreeDAO | Update");
 	}    
        finally{
            errorMessage="Error in DegreeDAO | Update | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, null, errorMessage);
            return result;
        } 
    }
    //view by id
               public DegreeBean viewDegree(byte degreeId){         
                 degreeBean=null;
        try {             
            connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("select degreeName,fullName from ecamp_tbl_degree where degreeId=?");
            preparedStatement.setByte(1, degreeId);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                degreeBean=new DegreeBean();
 	        degreeBean.setDegreeId(degreeId);
                degreeBean.setDegreeId(resultSet.getByte("degreeId"));
                degreeBean.setDegreeName(resultSet.getString("DegreeName"));
                String fullName = resultSet.getString("full name"); 
            }
        } catch (Exception e) {
            System.out.println("Error in DegreeDAO | View | Id");
        }
        finally{
            errorMessage="Error in DegreeDAO | View | degreeId | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);             
            return degreeBean;
        }  
               }
               //view by name
               
               
           public DegreeBean viewDegreeName(String degreeName){
               
 	         degreeBean=null;
        try {
            connection=DBUtil.getDBConnection();
 	    preparedStatement=connection.prepareStatement ("select degreeId,fullName from ecamp_tbl_degree where degreeName=?"); 
            preparedStatement.setString(1, degreeName);
             resultSet=preparedStatement.executeQuery();
 	 	if(resultSet.next()){                 
                    degreeBean=new DegreeBean();
 	 	      degreeBean.setDegreeId(resultSet.getByte("degreeId"));	 	 
                     degreeBean.setDegreeName(resultSet.getString("degreeName"));
                     degreeBean.setFullName(resultSet.getString("fullName"));
 	 	}                
 	} catch (Exception e) {
            System.out.println("Error in DegreeDAO | View | Name");
 	}
        finally{
            errorMessage="Error in DegreeDAO | View | DegreeName | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            return degreeBean;
        }
    }    
           
           //view by fullName
            public DegreeBean viewFullName(String fullName){
               
 	         degreeBean=null;
        try {
            connection=DBUtil.getDBConnection();
 	 preparedStatement=connection.prepareStatement ("select degreeId, fullName from ecamp_tbl_degree where degreeName=?"); 
          preparedStatement.setString(1, fullName);
           resultSet=preparedStatement.executeQuery();
 	 	if(resultSet.next()){                 
                    degreeBean=new DegreeBean();
 	 	      degreeBean.setDegreeId(resultSet.getByte("degreeId"));	 	 
                     String description = resultSet.getString("degreeName");
                     degreeBean.setFullName(resultSet.getString("fullName"));
 	 	}                
 	} catch (Exception e) {
            System.out.println("Error in DegreeDAO | View | fullName");
 	}
        finally{
            errorMessage="Error in DegreeDAO | View | fullName | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            return degreeBean;
        }
    }    
            //view all
    public ArrayList<DegreeBean> viewDegree(){
        degreeBean=null;
 	ArrayList<DegreeBean> degreeList=new ArrayList<DegreeBean>();        
        try{            
        connection=DBUtil.getDBConnection();    
        preparedStatement=connection.prepareStatement ("select degreeId, degreeName, fullName from ecamp_tbl_degree");            
        resultSet=preparedStatement.executeQuery();   
            while(resultSet.next()){                 
                degreeBean=new DegreeBean();
               degreeBean.setDegreeId(resultSet.getByte("degreeId"));
               degreeBean.setDegreeName(resultSet.getString("degreeName"));
               degreeBean.setfullName(resultSet.getString("fullName"));
            }         }
        catch(Exception e){
            System.out.println("Error in DegreeDAO | View | All");
        }         finally{
            errorMessage="Error in DegreeDAO | View | All | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);             
            return degreeList;
        } 
    }    
    public static void main(String[] args) {
        //DegreeBean degreeBean=new DegreeBean();
        //degreeBean.setDegreeId((byte)2);
        //degreeBean.setDegreeName("pg");
        //degreeBean.setFullName("pnder");
        //System.out.println(new DegreeDAO().addDegree(degreeBean));
        //System.out.println(new DegreeDAO().updateDegree(degreeBean));
        //degreeBean.setFullName("Below");
     //   degreeBean.setDegreeId((byte)3);
       // degreeBean.setFullName("bost");
        //System.out.println(new DegreeDAO().updateDegree(degreeBean));
        //System.out.println(new DegreeDAO().viewDegree("2"));
        //System.out.println(new DegreeDAO().viewDegree());
       // System.out.println(new DegreeDAO().viewDegreeName("pg"));
        //System.out.println(new DegreeDAO().viewDegree());
    }           
}

    
