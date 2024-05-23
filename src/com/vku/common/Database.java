
package com.vku.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=ATM;integratedSecurity=true;";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        if (Database.getConnection() != null) {
            System.out.println("Y");
        } else {
            System.out.println("N");
        }
    }
    
//     public void excuteDB(String sql, String Username, String Password,String AccountNo, String AccountType, String Gender, String Address, String DateofBirth, String Nationalty,String Mobile,float Balance ) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        try {
//            conn = getConnection(); // Nhận kết nối từ cơ sở dữ liệu
//            if (conn != null) {
//                ps = conn.prepareStatement(sql); // Tạo PreparedStatement với câu lệnh SQL
//                ps.setString(1, Username); // Đặt giá trị cho tham số đầu tiên
//                ps.setString(2, Password); // Đặt giá trị cho tham số thứ hai
//                ps.setString(3, AccountNo);
//                ps.setString(4, AccountType);
//                ps.setString(5, Gender);
//                ps.setString(6, Address);
//                ps.setString(7, DateofBirth);
//                ps.setString(8, Nationalty);
//                ps.setString(9, Mobile);
//                ps.setFloat(10, Balance);
//                ps.executeUpdate(); // Thực thi câu lệnh SQL
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing SQL: " + e.getMessage());
//        } finally {
//            // Đảm bảo đóng tài nguyên
//            try {
//                if (ps != null) {
//                    ps.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println("Error closing resources: " + ex.getMessage());
//            }
//        }
//    }
//
//    public float queryBalance(String queryBalanceSql, String Username, String Password, String AccountNo) {
//    try (Connection conn = getConnection();
//         PreparedStatement ps = conn.prepareStatement(queryBalanceSql)) {
//        ps.setString(1, Username);
//        ps.setString(2, Password);
//        ps.setString(3, AccountNo);
//
//        try (ResultSet rs = ps.executeQuery()) {
//            if (rs.next()) {
//                return rs.getFloat("Balance"); // Assuming the column name is "Balance"
//            }
//        }
//    } catch (SQLException e) {
//        System.out.println("Error querying balance: " + e.getMessage());
//    }
//    return 0; // Return a default value if balance retrieval fails
//}
//
//   public void excuteDB(String updateBalanceSql, float updatedBalance, String Username, String Password, String AccountNo) {
//    try (Connection conn = getConnection();
//         PreparedStatement ps = conn.prepareStatement(updateBalanceSql)) {
//        ps.setFloat(1, updatedBalance);
//        ps.setString(2, Username);
//        ps.setString(3, Password);
//        ps.setString(4, AccountNo);
//        ps.executeUpdate(); // Execute the update query
//    } catch (SQLException e) {
//        System.out.println("Error executing SQL: " + e.getMessage());
//    }
//}

}
