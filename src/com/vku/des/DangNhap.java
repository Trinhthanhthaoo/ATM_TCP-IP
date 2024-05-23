
package com.vku.des;

import com.vku.common.DefineNumberSystem;
import com.vku.model.Account;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

public class DangNhap extends javax.swing.JFrame {

    public DangNhap() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(86, 84, 162));
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 380, 50));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(86, 84, 162));
        jLabel2.setText("TÊN ĐĂNG NHẬP");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 210, 40));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(86, 84, 162));
        jLabel3.setText("MẬT KHẨU");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 140, 40));

        btnDangNhap.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(86, 84, 162));
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        getContentPane().add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, 170, 60));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 32)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(86, 84, 162));
        jLabel4.setText("ĐĂNG NHẬP");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 200, 60));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 380, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vku/img/Online Banking.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
     Socket clientSK = null;
    DataOutputStream outClient = null;
    try {
        // Thiết lập kết nối tới máy chủ
        clientSK = new Socket(InetAddress.getByName("localhost"), 8300);
        // Đọc giá trị từ các ô nhập liệu
        String accountID = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
    //    String moneyStr = txtNhapsotien.getText().trim();
        // Gửi dữ liệu qua socket
        outClient = new DataOutputStream(clientSK.getOutputStream());
        
        
        outClient.writeByte(DefineNumberSystem.NUM_01);
        outClient.flush();
        
        outClient.writeUTF(accountID);  // Gửi ID tài khoản
        outClient.flush();
        
        outClient.writeUTF(password);  // Gửi mật khẩu
        outClient.flush();
        
  //      outClient.writeDouble(amount);  // Gửi số tiền cần nạp

        // Nhận phản hồi từ máy chủ
        DataInputStream inClient = new DataInputStream(clientSK.getInputStream());
        
        boolean done = false;
        
        while(!done) {
          byte messageType = inClient.readByte();

          switch(messageType)
          {
            case 1:
              boolean auth = inClient.readBoolean();

              if (auth) {
                  Loading l = new Loading();
                  l.show();
                  this.hide();
              } else {
                  JOptionPane.showMessageDialog(null, "Authentication failed", "Error", JOptionPane.ERROR_MESSAGE);
                  done = true;
              }
              
              break;
            default:
              done = true;
          }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error during recharge: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Đảm bảo đóng các kết nối
        try {
            if (outClient != null) outClient.close();
            if (clientSK != null) clientSK.close();
        } catch (Exception ex) {
            System.err.println("Error closing connection: " + ex.getMessage());
            Loading l = new Loading();
            l.show();
            this.hide();
        }
    }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
