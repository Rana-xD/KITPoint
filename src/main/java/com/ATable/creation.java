package com.ATable;



import java.sql.*;  


public class creation
{

    public static void main(String args[]) 
    {
        try 
        {
           //step1 Register the driver  
        	Class.forName("org.postgresql.Driver");

            //step2 Get the connection from db 
        	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KITPoint","postgres", "admin");

            //step3 create the statement object  
            Statement stmt = con.createStatement();
            
            //step4 Excecute the query
             stmt.executeUpdate("create table User_info(id serial, Name varchar(100), Batch integer, Semester integer, Email varchar(100), Password varchar(100),User_type varchar(100),Created_at timestamp, Updated_at timestamp)");
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

