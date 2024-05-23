/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vku.des;

import com.vku.common.DefineNumberSystem;
import com.vku.server.ATMserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin!
 */
public class ChuyenTien extends javax.swing.JPanel {

    /**
     * Creates new form ChuyenTien
     */
    public ChuyenTien() {
        initComponents();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNhapsotien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        ChuyenTien = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        txtUsername.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("Password");

        txtNhapsotien.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setText("Transfer amount ");

        txtPassword.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 153, 255));
        jLabel4.setText("Beneficiary account ");

        txtAccount.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N

        ChuyenTien.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        ChuyenTien.setText("Transfer");
        ChuyenTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenTienActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("transfer money ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel4)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNhapsotien)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                            .addComponent(txtAccount)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(ChuyenTien, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(txtPassword))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNhapsotien, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(ChuyenTien, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ChuyenTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenTienActionPerformed
       Socket clientSocket = null;
        DataOutputStream outClient = null;

        try {
            // Establish connection to server
            clientSocket = new Socket("localhost", 8300);

            // Read values from input fields
            String senderUsername = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            String recipientAccount = txtAccount.getText().trim();
            String amountStr = txtNhapsotien.getText().trim();

// Xác thực đầu vào
            if (senderUsername.isEmpty() || password.isEmpty() || recipientAccount.isEmpty() || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Các trường không được để trống", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    throw new NumberFormatException("Số tiền phải dương.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Send data to server
            outClient = new DataOutputStream(clientSocket.getOutputStream());
             outClient.writeByte(DefineNumberSystem.NUM_03);
        outClient.flush();
//            outClient.writeUTF("CT");  // CT for money transfer
            outClient.writeUTF(senderUsername);
            outClient.flush();
            outClient.writeUTF(password);
             outClient.flush();
            outClient.writeUTF(recipientAccount); 
             outClient.flush();// Account to transfer to
            outClient.writeDouble(amount);
            outClient.flush();

            // Read server response
            DataInputStream inClient = new DataInputStream(clientSocket.getInputStream());
            
            
            boolean done = false;
        
            while(!done) {
              byte messageType = inClient.readByte();

              switch(messageType)
              {
                case 1:
                  boolean result = inClient.readBoolean();

                  if (result) {
                      JOptionPane.showMessageDialog(this, "Transfer successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                  } else {
                      JOptionPane.showMessageDialog(this, "Transfer failed.", "Error", JOptionPane.ERROR_MESSAGE);
                      done = true;
                  }

                  break;
                default:
                  done = true;
              }
            }
            


        } catch (IOException e) {
//            JOptionPane.showMessageDialog(this, "Error during transfer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Transfer failed.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure connections are properly closed
            try {
                if (outClient != null) {
                    outClient.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_ChuyenTienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChuyenTien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtNhapsotien;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    
}
