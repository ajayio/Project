
package com.ecamp.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {    

    
     public static Connection getDBConnection() throws ClassNotFoundException{         
         Connection connection = null;
         Class.forName("com.mysql.jdbc.Driver");
         try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/circular", "root", "");
         }         
         catch(SQLException e){
             System.out.println("ERROR");
         }        
         return connection;         
    }
     
     
    public static void closeDBConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet , String errorMessage){
        try{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }            
        }
        catch(SQLException e){
            System.out.println("");
        }
    }
    
    public static void closeDBConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }            
        }
        catch(SQLException e){
            System.out.println("");
        }
    }
    
    public static void main(String[] args) {
        
        try{
            Connection conn = new DBUtil().getDBConnection();
        }        
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
        
    }
    
}
