package com.ATable;



import java.sql.*;  


public class Create
{

    public static void main(String args[]) 
    {
        try 
        {
           //step1 Register the driver  
        	Class.forName("org.postgresql.Driver");

            //step2 Get the connection from db 
        	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KITPoint","postgres", "pannrana");

            //step3 create the statement object  
            Statement stmt = con.createStatement();
            
            //step4 Excecute the query
            stmt.executeUpdate("create table batch_master(id serial primary key, Semester_id integer, Name varchar(50),Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table User_info(id serial primary key, Name varchar(100), Batch integer, Semester integer, Email varchar(100), Password varchar(100),User_type varchar(100),Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Value_per_hour(id serial primary key, Semester_id integer, Batch_id integer, Value integer, Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Semester_Master(id serial primary key, Semester varchar(20),Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Project_member(id serial primary key, Project_id integer, User_id integer,Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Project_Category_Master(id serial primary key, Name varchar(150), Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table KIT_Point_Student_Wise(id serial primary key, User_id integer, Project_id integer, KIT_Point varchar(40), Hours integer,Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table KIT_Point(id serial primary key, Value integer, KIT_point varchar(40), Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Budget_Master(id serial primary key, Name varchar(150), Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Budget(id serial primary key,Budget_id integer, Project_id integer, Cost integer, Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Project_Stage_Master(id serial primary key, Stage_name varchar(70), Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Project_Stage(id serial primary key, Project_id integer, Stage_id integer, Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Task_Master(id serial primary key, Project_id integer, Name varchar(150), Assigned_To integer, Description varchar(500),Status varchar(70), Time_spend integer, Deadline date, Start_date date, End_date date,Created_at timestamp, Updated_at timestamp)");
            stmt.executeUpdate("create table Project_Master(id serial primary key, Project_Name varchar(150), Project_Code varchar(40), Skillset varchar(150), Project_type integer, Project_Co integer, Project_leader integer, Project_member varchar(40),Description varchar(500), Status varchar(40),Initially_planned integer, Budget integer, KIT_point varchar(40), Deadline date, Start_date date, End_date date, Created_at timestamp, Updated_at timestamp)");
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
