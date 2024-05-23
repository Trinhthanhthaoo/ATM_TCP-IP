package com.vku.server;

import com.vku.common.Database;
import com.vku.common.DefineNumberSystem;
import com.vku.des.History;
import com.vku.model.Account;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ATMserver {
    
    private static byte NUM_01 = 1; // login
    
    //dang nhap
    public static void startServer() {
        try (ServerSocket serverSK = new ServerSocket(8300)) {
            System.out.println("Server is running on port 8300");

            while (true) {
                try (Socket clientSK = serverSK.accept();
                     DataInputStream input = new DataInputStream(clientSK.getInputStream());
                     DataOutputStream output = new DataOutputStream(clientSK.getOutputStream())) {
                    
                    System.out.println("Connection successful");
                    
                    byte messageType = input.readByte();
                    String username = "";
                    String password = "";
                    double amount = 0;
                    String recipientAccount = "";
                    System.out.println("messageType: " + messageType);
                    
                    // Login
                    // Variables
                    if (messageType == DefineNumberSystem.NUM_01) {
                        username = input.readUTF();
                        password = input.readUTF();
                    } else if (messageType == DefineNumberSystem.NUM_02) {
                        username = input.readUTF();
                        password = input.readUTF();
                        amount = input.readDouble();
                    } else if (messageType == DefineNumberSystem.NUM_03) {
                        username = input.readUTF();
                        password = input.readUTF();
                        recipientAccount = input.readUTF();
                        amount = input.readDouble();
                    }else if (messageType == DefineNumberSystem.NUM_04) {
                        username = input.readUTF();
                        password = input.readUTF();
                        amount = input.readDouble();
                    }else if (messageType == DefineNumberSystem.NUM_05) {
                        username = input.readUTF();
                    }else if (messageType == DefineNumberSystem.NUM_06) {
                        username = input.readUTF();
                        password = input.readUTF();
                        recipientAccount = input.readUTF();
                        amount = input.readDouble();
                    }
                    
                    System.out.println("username: " + username);
                    System.out.println("password: " + password);
                    System.out.println("recipientAccount: " + recipientAccount);
                    System.out.println("amount: " + amount);



                    // Process commons
                    // Thực hiện xác thực
                    boolean isAuthenticated = authenticate(username, password);
                    Date date = new Date();  // Thời gian hiện tại
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                    String updateDate = formatter.format(date);  // Định dạng thời gian

                    
                    // Login
                    if (messageType == DefineNumberSystem.NUM_01) {
                        output.writeByte(1); // Response with 1
                        output.flush();
                            
                        output.writeBoolean(isAuthenticated);
                        output.flush();
                            
                        output.writeByte(-1);
                        output.flush();
                    } else if (messageType == DefineNumberSystem.NUM_02) {
                        output.writeByte(1); // Response with 1
                        output.flush();
                            
                        if (isAuthenticated) {
                            updateBalance(username, amount);
                            output.writeBoolean(true);
                            output.flush();
                        } else {
                            output.writeBoolean(false);
                            output.flush();
                        }
                            
                        output.writeByte(-1);
                        output.flush();
                    }else if (messageType == DefineNumberSystem.NUM_03) { //chuyentien
                        output.writeByte(1); // Response with 3
                        output.flush();
                            
                        if (isAuthenticated) {
                            transferMoney(username,recipientAccount, amount);                     
                        // Tạo chi tiết lịch sử giao dịch
                                String transactionId = java.util.UUID.randomUUID().toString();
                                String details = "chuyen tien toi " + recipientAccount + " tien: " + amount; 
                                
                                // Lưu lịch sử giao dịch
                                saveTransactionHistory(transactionId, username, details, updateDate);
                              
                                
                            output.writeBoolean(true);
                            output.flush();
                        } else {
                            output.writeBoolean(false);
                            output.flush();
                        }
                            
                        output.writeByte(-1);
                        output.flush();
//                        if (messageType == DefineNumberSystem.NUM_03) { // Chuyển tiền
//                            output.writeByte(1); // Phản hồi với mã 3
//                            output.flush();
//
//                            if (authenticate(username, password)) {
//                                // Thực hiện chuyển tiền
//                                transferMoney(username, recipientAccount, amount);
//
//                                // Tạo chi tiết lịch sử giao dịch
////                                String transactionId = java.util.UUID.randomUUID().toString();
////                                String details = "chuyen tien toi " + recipientAccount + " tien: " + amount; 
//                                
//
//                                // Lưu lịch sử giao dịch
////                                saveTransactionHistory(transactionId, username, details, updateDate);
//
//                                output.writeBoolean(true);  // Phản hồi thành công
//                            } else {
//                                output.writeBoolean(false);  // Phản hồi thất bại
//                            }
//
//                            output.writeByte(-1);  // Kết thúc phản hồi
//                            output.flush();
//                        }
//
//
//           
                    }else if (messageType == DefineNumberSystem.NUM_04) {
                        output.writeByte(1); // Response with 1
                        output.flush();
                            
                        if (isAuthenticated) {
                             withdrawAmount(username, amount);
                            output.writeBoolean(true);
                            output.flush();
                        } else {
                            output.writeBoolean(false);
                            output.flush();
                        }
                            
                        output.writeByte(-1);
                        output.flush();
                    } else if (messageType == DefineNumberSystem.NUM_05) {
                        Account account = getAccount(username);
                         output.writeByte(1); // Response with 1
                        output.flush();
                         if (isAuthenticated) {
                             getAccount(username);
                            output.writeBoolean(true);
                            output.flush();
                        } else {
                            output.writeBoolean(false);
                            output.flush();
                        }
                            
                        output.writeByte(-1);
                        output.flush();
                    }else if (messageType == DefineNumberSystem.NUM_06) {
                        output.writeByte(1); // Response with 6
                        output.flush();
                            
                        if (isAuthenticated) {
//                            String details = input.readUTF(); 
                          //  String updateDate = input.readUTF();  
//                            saveTransactionHistory(username, password, details, updateDate);  
                            output.writeBoolean(true);
                            output.flush();
                        } else {
                            output.writeBoolean(false);
                            output.flush();
                        }
                    }
                    
                    
                    
                    
                    
                    
                    
                    
//                    if ("DN".equals(key)) {
//                        output.writeBoolean(isAuthenticated);
//                    }

                    
//                    switch(key) {
//                        case "NT": // nạp tiền
//                            if (isAuthenticated) {
//                                System.out.println("PXH: Đang thực hiện nạp tiền");
//                                // Nếu xác thực thành công, cập nhật số dư
////                                updateBalance(username, amount);
//                                // Gửi phản hồi thành công
//                                output.writeBoolean(true);
//                            } else {
//                                // Gửi phản hồi thất bại nếu không xác thực được
//                                output.writeBoolean(false);
//                            }
//                            break;
//                        case "RT":
//                             if (isAuthenticated) {
//                                System.out.println("PXH: Đang thực hiện rút tiền");
//                                // Nếu xác thực thành công, cập nhật số dư
////                                withdrawAmount(username, amount);
//                                // Gửi phản hồi thành công
////                                output.writeBoolean(true);
//                            } else {
//                                // Gửi phản hồi thất bại nếu không xác thực được
////                                output.writeBoolean(false);
//                            }
//                            break;
//                        case "DN":
////                             if (isAuthenticated) {
////                                System.out.println("PXH: Đang thực hiện đăng nhập");                                
////                             //   authenticate(username, password);
////                                // Gửi phản hồi thành công
////                                output.writeBoolean(isAuthenticated);
////                            } else {
////                                // Gửi phản hồi thất bại nếu không xác thực được
////                                output.writeBoolean(false);
////                            }
//                            break;
////                        case "IS":
////                             if (isAuthenticated) {
////                                System.out.println("PXH: Đang thực hiện rút tiền");
////                               insertAcc(username,password,AccountNo,AccountType,Gender,Address,DateofBirth,Nationality,Mobile, amount);
////                                // Gửi phản hồi thành công
////                                output.writeBoolean(true);
////                            } else {
////                                // Gửi phản hồi thất bại nếu không xác thực được
////                                output.writeBoolean(false);
////                            }
////                            break;
////                        case "CT":
////                             if (isAuthenticated) {
////                                System.out.println("PXH: Đang thực hiện rút tiền");
////                                // Nếu xác thực thành công, cập nhật số dư
////                                transferMoney(username,recipientAccountNo, amount);
////                                // Gửi phản hồi thành công
////                                output.writeBoolean(true);
////                            } else {
////                                // Gửi phản hồi thất bại nếu không xác thực được
////                                output.writeBoolean(false);
////                            }
////                            break;
//                    }
                    
                    
                    
                    // Gửi kết quả về máy khách
//                    output.writeBoolean(isAuthenticated);
                } catch (IOException | SQLException ex) {
                    System.out.println("Error processing client data: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ATMserver.class.getName()).log(Level.SEVERE, "Server error", ex);
        }
    }

    private static boolean authenticate(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        PreparedStatement ps = Database.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        
        return rs.next(); // Trả về true nếu có tài khoản trùng khớp
    }
    

 public static void recharge() {
    try (ServerSocket serverSK = new ServerSocket(8300)) {
        System.out.println("Server is running on port 8300");
        System.out.println("TTTT recharge");
        while (true) {
            try (Socket clientSK = serverSK.accept();
                 DataInputStream input = new DataInputStream(clientSK.getInputStream());
                 DataOutputStream output = new DataOutputStream(clientSK.getOutputStream())) {

                System.out.println("Connection successful");

                // Đọc dữ liệu từ máy khách
                String username = input.readUTF();  // ID tài khoản
                String password = input.readUTF();  // Mật khẩu
                double amountToAdd = input.readDouble();  // Số tiền cần nạp

                // Xác thực người dùng
                boolean isAuthenticated = authenticateRecharge(username, password);

                if (isAuthenticated) {
                    // Nếu xác thực thành công, cập nhật số dư
                    updateBalance(username, amountToAdd);

                    // Gửi phản hồi thành công
                    output.writeBoolean(true);
                } else {
                    // Gửi phản hồi thất bại nếu không xác thực được
                    output.writeBoolean(false);
                }

            } catch (IOException | SQLException ex) {
                System.out.println("Error processing client data: " + ex.getMessage());
            }
        }
    } catch (IOException ex) {
            Logger.getLogger(ATMserver.class.getName()).log(Level.SEVERE, "Server error", ex);
    }
}

private static boolean authenticateRecharge(String username, String password) throws SQLException {
    String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
    PreparedStatement ps = Database.getConnection().prepareStatement(sql);
    ps.setString(1, username);
    ps.setString(2, password);
    ResultSet rs = ps.executeQuery();

    return rs.next();  // Trả về true nếu có tài khoản trùng khớp
}

private static void updateBalance(String username, double amountToAdd) throws SQLException {
    String sql = "UPDATE Account SET Balance = Balance + ? WHERE Username = ?";
    PreparedStatement ps = Database.getConnection().prepareStatement(sql);
    ps.setDouble(1, amountToAdd);
    ps.setString(2, username);
    ps.executeUpdate();  // Thực hiện cập nhật
}

public static void withdraw() {
    try (ServerSocket serverSK = new ServerSocket(8300)) {
        System.out.println("Server is running on port 8300");

        while (true) {
            try (Socket clientSK = serverSK.accept();
                 DataInputStream input = new DataInputStream(clientSK.getInputStream());
                 DataOutputStream output = new DataOutputStream(clientSK.getOutputStream())) {

                System.out.println("Connection successful");

                // Đọc dữ liệu từ máy khách
                String username = input.readUTF();
                String password = input.readUTF();
                double amountToWithdraw = input.readDouble();  // Số tiền cần rút

                // Xác thực người dùng
                boolean isAuthenticated = authenticateRecharge(username, password);

                if (isAuthenticated) {
                    // Kiểm tra số dư trước khi rút
                    if (isBalanceSufficient(username, amountToWithdraw)) {
                        // Thực hiện rút tiền
                        withdrawAmount(username, amountToWithdraw);

                        // Phản hồi thành công
                        output.writeBoolean(true);
                    } else {
                        // Số dư không đủ
                        output.writeBoolean(false);
                        System.out.println("Insufficient balance to withdraw");
                    }
                } else {
                    // Xác thực không thành công
                    output.writeBoolean(false);
                    System.out.println("Authentication failed");
                }

            } catch (IOException | SQLException ex) {
                System.out.println("Error processing client data: " + ex.getMessage());
            }
        }
    } catch (IOException ex) {
        Logger.getLogger(ATMserver.class.getName()).log(Level.SEVERE, "Server error", ex);
    }
}

private static boolean isBalanceSufficient(String username, double amountToWithdraw) throws SQLException {
    // Lấy số dư và kiểm tra xem nó có đủ để rút tiền không
    String sql = "SELECT Balance FROM Account WHERE Username = ?";
    PreparedStatement ps = Database.getConnection().prepareStatement(sql);
    ps.setString(1, username);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        double balance = rs.getDouble("Balance");
        return balance >= amountToWithdraw;  // Kiểm tra xem số dư đủ để rút không
    } else {
        throw new SQLException("Account not found");
    }
}

private static void withdrawAmount(String username, double amountToWithdraw) throws SQLException {
    // Trừ số tiền cần rút khỏi số dư
    String sql = "UPDATE Account SET Balance = Balance - ? WHERE Username = ? AND Balance >= ?";
    PreparedStatement ps = Database.getConnection().prepareStatement(sql);
    ps.setDouble(1, amountToWithdraw);
    ps.setString(2, username);
    ps.setDouble(3, amountToWithdraw);  // Đảm bảo số dư đủ
    ps.executeUpdate();
}



//Chuyển tiền
public static void transfer() {
    try (ServerSocket serverSK = new ServerSocket(8300)) {
        System.out.println("Server is running on port 8300");

        while (true) {
            try (Socket clientSK = serverSK.accept();
                 DataInputStream input = new DataInputStream(clientSK.getInputStream());
                 DataOutputStream output = new DataOutputStream(clientSK.getOutputStream())) {

                System.out.println("Connection successful");

                // Đọc dữ liệu từ máy khách
                String senderUsername = input.readUTF();  // Tên người gửi
                String senderPassword = input.readUTF();  // Mật khẩu người gửi
                String recipientAccountNo = input.readUTF();  // Số tài khoản người nhận
                double amountToTransfer = input.readDouble();  // Số tiền cần chuyển

                // Xác thực người gửi
                boolean isAuthenticated = authenticateRecharge(senderUsername, senderPassword);

                if (isAuthenticated) {
                    // Kiểm tra số dư của người gửi
                    if (isBalanceSufficient(senderUsername, amountToTransfer)) {
                        // Thực hiện chuyển tiền
                        transferMoney(senderUsername, recipientAccountNo, amountToTransfer);

                        // Gửi phản hồi thành công
                        output.writeBoolean(true);
                    } else {
                        // Số dư không đủ để chuyển
                        output.writeBoolean(false);
                        System.out.println("Insufficient balance to transfer");
                    }
                } else {
                    // Xác thực không thành công
                    output.writeBoolean(false);
                    System.out.println("Authentication failed");
                }

            } catch (IOException | SQLException ex) {
                System.out.println("Error processing client data: " + ex.getMessage());
            }
        }
    } catch (IOException ex) {
        Logger.getLogger(ATMserver.class.getName()).log(Level.SEVERE, "Server error", ex);
    }
}

private static void transferMoney(String senderUsername, String recipientAccountNo, double amountToTransfer) throws SQLException {
    // Thực hiện chuyển tiền từ người gửi sang người nhận
    Connection conn = Database.getConnection();

    // Sử dụng giao dịch (transaction) để đảm bảo tính toàn vẹn dữ liệu
    try {
        conn.setAutoCommit(false);  // Bắt đầu giao dịch

        // Trừ số tiền từ tài khoản người gửi
        String sql1 = "UPDATE Account SET Balance = Balance - ? WHERE Username = ? AND Balance >= ?";
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        ps1.setDouble(1, amountToTransfer);
        ps1.setString(2, senderUsername);
        ps1.setDouble(3, amountToTransfer);  // Đảm bảo số dư đủ
        int rowsAffected1 = ps1.executeUpdate();

        if (rowsAffected1 == 0) {
            throw new SQLException("Insufficient balance or account not found");
        }

        // Thêm số tiền vào tài khoản người nhận
        String sql2 = "UPDATE Account SET Balance = Balance + ? WHERE Username = ?";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.setDouble(1, amountToTransfer);
        ps2.setString(2, recipientAccountNo);
        int rowsAffected2 = ps2.executeUpdate();

        if (rowsAffected2 == 0) {
            throw new SQLException("Recipient account not found");
        }

        conn.commit();  // Hoàn thành giao dịch

    } catch (SQLException ex) {
        conn.rollback();  // Hoàn tác nếu có lỗi
        throw ex;  // Truyền lỗi lên trên
    } finally {
        conn.setAutoCommit(true);  // Khôi phục chế độ tự động commit
    }
}


//Thông tin cá nhân
  public static Account getAccount(String username) throws SQLException {
    Account account = null;

    // SQL để lấy thông tin tài khoản dựa trên tên người dùng
    String sql = "SELECT * FROM Account WHERE Username = ?";
    PreparedStatement ps = Database.getConnection().prepareStatement(sql);
    ps.setString(1, username);  // Thiết lập tham số cho PreparedStatement

    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        // Lấy thông tin từ ResultSet và tạo đối tượng Account
        String password = rs.getString("Password");
        String accountNo = rs.getString("AccountNo");
        String accountType = rs.getString("AccountType");
        String gender = rs.getString("Gender");
        String address = rs.getString("Address");
        String dateOfBirth = rs.getString("DateofBirth");
        String nationality = rs.getString("Nationalty");
        String mobile = rs.getString("Mobile");
        double balance = rs.getDouble("Balance");

        account = new Account(username, password, accountNo, accountType, gender, address, dateOfBirth, nationality, mobile, balance);
    }

    return account;  // Trả về đối tượng Account hoặc null nếu không tìm thấy
}

  
  
  
  //khoa Account
   public static void updateAccountStatus(String username, boolean lock) throws SQLException {
    String sql = "UPDATE Account SET deleted = ? WHERE Username = ?";
    PreparedStatement ps = Database.getConnection().prepareStatement(sql);
    ps.setInt(1, lock ? 1 : 0);  // Khóa nếu lock là true, mở nếu là false
    ps.setString(2, username);
    ps.executeUpdate();  // Thực hiện cập nhật
}
   public static void Acc() {
    try (ServerSocket serverSK = new ServerSocket(8300)) {
        System.out.println("Server is running on port 8300");

        while (true) {
            try (Socket clientSK = serverSK.accept();
                 DataInputStream input = new DataInputStream(clientSK.getInputStream());
                 DataOutputStream output = new DataOutputStream(clientSK.getOutputStream())) {

                System.out.println("Connection successful");

                // Đọc loại yêu cầu từ máy khách
                String requestType = input.readUTF();

                if ("UPDATE_ACCOUNT_STATUS".equals(requestType)) {
                    // Đọc thông tin tài khoản cần cập nhật
                    String username = input.readUTF();
                    boolean lock = input.readBoolean();

                    try {
                        // Khóa hoặc mở tài khoản dựa trên yêu cầu
                        updateAccountStatus(username, lock);

                        output.writeBoolean(true);  // Phản hồi thành công

                    } catch (SQLException ex) {
                        output.writeBoolean(false);  // Phản hồi thất bại
                        System.out.println("Error updating account status: " + ex.getMessage());
                    }
                }

            } catch (IOException ex) {
                System.out.println("Error processing client data: " + ex.getMessage());
            }
        }
    } catch (IOException ex) {
        System.err.println("Server error: " + ex.getMessage());
    }
}

   
   //thêm Account
   public static void insertAcc(String username, String password, String AccountNo, String AccountType, String Gender, String Address, String DateofBirth, String Nationality, String Mobile, double Balance) throws SQLException {
    String sql = "INSERT INTO Account (Username, Password, AccountNo, AccountType, Gender, Address, DateofBirth, Nationality, Mobile, Balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, AccountNo);
        ps.setString(4, AccountType);
        ps.setString(5, Gender);
        ps.setString(6, Address);
        ps.setString(7, DateofBirth);
        ps.setString(8, Nationality);
        ps.setString(9, Mobile);
        ps.setDouble(10, Balance);

        ps.executeUpdate();  // Thực hiện chèn dữ liệu
    }
}
    public static void InsertAccount() {
        try (ServerSocket serverSK = new ServerSocket(8300)) {
            System.out.println("Server is running on port 8300");

            while (true) {
                try (Socket clientSK = serverSK.accept();
                        DataInputStream input = new DataInputStream(clientSK.getInputStream());
                        DataOutputStream output = new DataOutputStream(clientSK.getOutputStream())) {

                    System.out.println("Connection successful");

                    // Đọc dữ liệu từ máy khách
                    String username = input.readUTF();
                    String password = input.readUTF();
                    String AccountNo = input.readUTF();
                    String AccountType = input.readUTF();
                    String Gender = input.readUTF();
                    String Address = input.readUTF();
                    String DateofBirth = input.readUTF();
                    String Nationality = input.readUTF();
                    String Mobile = input.readUTF();                 
                    double amountToWithdraw = input.readDouble();  // Số tiền cần rút

                    // Xác thực người dùng
                    boolean isAuthenticated = authenticateRecharge(username, password);

                    if (isAuthenticated) {
                        // Kiểm tra số dư trước khi rút
                        if (isBalanceSufficient(username, amountToWithdraw)) {
                            // Thực hiện thêm Account
                            insertAcc(username,password,AccountNo,AccountType,Gender,Address,DateofBirth,Nationality,Mobile, amountToWithdraw);

                            // Phản hồi thành công
                            output.writeBoolean(true);
                        } else {
                            // Số dư không đủ
                            output.writeBoolean(false);
                            System.out.println("Insufficient balance to withdraw");
                        }
                    } else {
                        // Xác thực không thành công
                        output.writeBoolean(false);
                        System.out.println("Authentication failed");
                    }

                } catch (IOException | SQLException ex) {
                    System.out.println("Error processing client data: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ATMserver.class.getName()).log(Level.SEVERE, "Server error", ex);
        }
    }
   public static void saveTransactionHistory(String ID, String username, String details, String updateDate) throws SQLException {
    String sql = "INSERT INTO History (ID, Username, Details, UpdateDate) VALUES (?, ?, ?, ?)";

    try (PreparedStatement ps = Database.getConnection().prepareStatement(sql)) {
        ps.setString(1, ID);
        ps.setString(2, username);
        ps.setString(3, details);  
        ps.setString(4, updateDate);  

        ps.executeUpdate();
    }
}


    

}
