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
             //stmt.executeUpdate(" CREATE SEQUENCE user_id_seq");
             stmt.executeUpdate("create table Value_per_hour(id serial primary key, Semester_id integer, Batch_id integer, Value integer, Created_at timestamp, Updated_at timestamp)");
             //    stmt.executeUpdate("ALTER SEQUENCE user_id_seq owned by User_info.id;");
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




