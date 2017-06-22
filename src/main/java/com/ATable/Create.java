package com.ATable;



import java.sql.*;  


public class Create
{

    public static void main(String args[]) 
    {
        try 
        {
           //step1 Register the driver  
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //step2 Get the connection from db 
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");

            //step3 create the statement object  
            Statement stmt = con.createStatement();
            
            //step4 Excecute the query
            stmt.executeUpdate("create table user_info(id  number(5)  primary key, name varchar2(20), salary varchar2(20))");
                    
            System.out.println("Success");

            //step5 close the connection object  
            con.close();

        } 
        catch (Exception e) 
        {
            System.out.println(e.toString());
            
        }

    }
}



//C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib   --location of jdbc jar file 


/*

3 statement objects are available

1- Statement
2- prepared statement 
3- callable statement


prepared statement is much faster than other statements and secure too.

*/
