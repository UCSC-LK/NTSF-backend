package com.cops.ntsf.dao;
import com.cops.ntsf.controller.Complaint;
import com.cops.ntsf.util.DBConnect;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    private static Connection dbConnect = null;

    public static List<Complaint> viewComplaint(String user_id)
    {
        ArrayList<Complaint> complaintInfo = new ArrayList<>();

        try
        {
            dbConnect = DBConnect.getConnection();
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return complaintInfo;

    }










//    public static Complaint viewComplaint()
//    {
//        Complaint complaint = null;
//        try{
//            dbConnect = DBConnect.getConnection();
//            Statement stmt = dbConnect.createStatement();
//            String sql = "SELECT * FROM complaint";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while(rs.next())
//            {
//                String user_id = rs.getString("user_id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                String complaint_no = rs.getString("complaint_no");
//
//                complaint = new Complaint(user_id, title, description, complaint_no);
//
//                System.out.println(user_id + "\t" + title + "\t" + description + "\t" + complaint_no );
//            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return complaint;
//    }



//
//    private static void getComplaintByID()
//    {
//        boolean isSuccess = false;
//
//        try{
//            dbConnect = DBConnect.getConnection();
//            Statement stmt = dbConnect.createStatement();
//            String sql = "SELECT * FROM complaint WHERE user_id = 2";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            if (rs.next())
//            {
//                String user_id = rs.getString("user_id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                String complaint_id = rs.getString("complaint_id");
//
//                System.out.println(user_id + "\t" + title + "\t" + description + "\t" + complaint_id );
//            }
//            else
//            {
//                System.out.println("Complaint doesnt exist");
//            }
//
//        }
//
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
    public static boolean addComplaint(String user_id, String title, String description, String complaint_no)
    {
        boolean isSuccess = false;

        try{
            dbConnect = DBConnect.getConnection();
            Statement stmt = dbConnect.createStatement();
            String sql = "insert into complaint values ('"+user_id+"', '"+title+"', '"+description+"', '"+complaint_no+"')";
            int rs = stmt.executeUpdate(sql); //returns 1 if insertion is successful(since only 1 row is affected)

            if (rs > 0)
            {
                isSuccess = true;
            }
            else
            {
                isSuccess = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return isSuccess;
    }


//
//
//    public static boolean deleteComplaint(String complaint_id)
//    {
//        boolean isSuccess = false;
//
//        try
//        {
//            dbConnect = DBConnect.getConnection();
//            Statement stmt = dbConnect.createStatement();
//            String sql = "DELETE FROM complaint WHERE user_id='"+complaint_id+"'";
//            int rs = stmt.executeUpdate(sql);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return isSuccess;
//    }
//
//    public static boolean readComplaint()
//    {
//        boolean isSuccess = false;
//
//        try
//        {
//            dbConnect = DBConnect.getConnection();
//            Statement stmt = dbConnect.createStatement();
//            String sql = "";
//            int rs = stmt.executeUpdate(sql);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return isSuccess;
//    }
//
//
}
