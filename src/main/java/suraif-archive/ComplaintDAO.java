//package suraif;
//
////public class ComplaintDAO
////{
////    private static Connection dbConnect = null;
////
////    public static boolean addComplaint(String user_id, String title, String description, String complaint_no) {
////        boolean isSuccess = false;
////
////        try {
////            dbConnect = DBConnect.getConnection();
////            Statement stmt = dbConnect.createStatement();
////            String sql = "insert into complaint values ('" + user_id + "', '" + title + "', '" + description + "', '" + complaint_no + "')";
////            int rs = stmt.executeUpdate(sql); //returns 1 if insertion is successful(since only 1 row is affected)
////
////            if (rs > 0) {
////                isSuccess = true;
////            } else {
////                isSuccess = false;
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        return isSuccess;
////    }
////
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    private static Connection dbConnect = null;
//
//    public static boolean addComplaint(String user_id, String title, String description, String complaint_no) {
//        boolean isSuccess = false;
//
//        try {
//            dbConnect = DBConnect.getConnection();
//            Statement stmt = dbConnect.createStatement();
//            String sql = "insert into complaint values ('" + user_id + "', '" + title + "', '" + description + "', )";
//            int rs = stmt.executeUpdate(sql); //returns 1 if insertion is successful(since only 1 row is affected)
//
//            if (rs > 0) {
//                isSuccess = true;
//            } else {
//                isSuccess = false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return isSuccess;
//    }
//
////    public static List<Complaint> viewComplaintByID(String user_id) {
////        ArrayList<Complaint> complaintInformation = new ArrayList<>();
////
////        try {
//////          int convertedUser_id = Integer.parseInt(user_id);
////            dbConnect = DBConnect.getConnection();
////            Statement stmt = dbConnect.createStatement();
////            String sql = "Select * from complaint where user_id = '" + user_id + "'";
////            ResultSet rs = stmt.executeQuery(sql);
////
////            //next() returns a boolean value of true if the query is false
////            while (rs.next()) {
////                String user_idU = rs.getString(1);
////                String title = rs.getString(2);
////                String description = rs.getString(3);
////                String complaint_no = rs.getString(4);
////
////                Complaint complaintInfo = new Complaint(user_idU, title, description, complaint_no);
////                complaintInformation.add(complaintInfo);
////
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        return complaintInformation;
////
////    }
//
//
//    public static List<Complaint> viewComplaint() {
//        ArrayList<Complaint> complaintInformation = new ArrayList<>();
//
//        try {
////          int convertedUser_id = Integer.parseInt(user_id);
//            dbConnect = DBConnect.getConnection();
//            Statement statement = dbConnect.createStatement();
//            String sql = "Select * from complaint";
//            ResultSet rs = statement.executeQuery(sql);
//
//            //next() returns a boolean value of true if the query is false
//            while (rs.next()) {
//                String user_idU = rs.getString(1);
//                String title = rs.getString(2);
//                String description = rs.getString(3);
//                String complaint_no = rs.getString(4);
//
//                Complaint complaintInfo = new Complaint(user_idU, title, description, complaint_no);
//                complaintInformation.add(complaintInfo);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return complaintInformation;
//    }
//
//    public static List<Complaint> viewComplaintByID(String user_id) {
//        ArrayList<Complaint> complaintInformationByID = new ArrayList<>();
//
//        try {
////          int convertedUser_id = Integer.parseInt(user_id);
//            dbConnect = DBConnect.getConnection();
//            Statement stmt = dbConnect.createStatement();
//            String sql = "Select * from complaint where user_id ='"+user_id+"'";
//            ResultSet rs
//
//                    = stmt.executeQuery(sql);
//
//            //next() returns a boolean value of true if the query is false
//            while (rs.next()) {
//                String user_idU = rs.getString(1);
//                String title = rs.getString(2);
//                String description = rs.getString(3);
//                String complaint_no = rs.getString(4);
//
//                Complaint complaintInfo = new Complaint(user_idU, title, description, complaint_no);
//                complaintInformationByID.add(complaintInfo);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return complaintInformationByID;
//    }
//}
//
