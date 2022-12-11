/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nrnbt
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public String ipAddress;
    
    public Home(String ipAddress) {
        this.ipAddress = ipAddress;
        initComponents();
    }
    
    public static FetchAuctionResponse AuctionList;
    
    public void showData(String stausFilter) throws ClassNotFoundException {
        ArrayList<FetchAuctionResponse> auctionList = new ArrayList<>();
        try (Socket socket = new Socket(ipAddress, 1234)) {

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            FetchAuctionRequest request = new FetchAuctionRequest("auctions", stausFilter);
            oos.writeObject(request);
   
            Object obj = ois.readObject();
            FetchAuctionResponse adminList;
            
            if (obj.getClass().getName().equals("admin.FetchAuctionResponse")
		&& (adminList = (FetchAuctionResponse) obj) != null) {
                
                if(adminList.auctionList.isEmpty()){
                    JOptionPane.showMessageDialog(Background, "Nothing to show");
                } else {
                    AuctionList = adminList;
                    DefaultTableModel model = (DefaultTableModel) auctionsTable.getModel();
                    Object[] row = new Object[6];
                    for (int i = 0; i < adminList.auctionList.size(); i++) {
                        row[0] = i + 1;
                        row[1] = adminList.auctionList.get(i).title;
                        row[2] = adminList.auctionList.get(i).user;
                        row[3] = adminList.auctionList.get(i).startPrice;
                        row[4] = adminList.auctionList.get(i).status;
                        model.addRow(row);
                    }
                }
            }
            
            oos.close();
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new keeptoo.KGradientPanel();
        tableScroll = new javax.swing.JScrollPane();
        auctionsTable = new javax.swing.JTable();
        showAllButton = new javax.swing.JButton();
        CloseButton = new javax.swing.JLabel();
        singleAuction = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        showPendingButton = new javax.swing.JButton();
        showAcceptedButton = new javax.swing.JButton();
        showFinishedButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        auctionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Title", "User", "Start Price", "Status"
            }
        ));
        tableScroll.setViewportView(auctionsTable);
        if (auctionsTable.getColumnModel().getColumnCount() > 0) {
            auctionsTable.getColumnModel().getColumn(0).setMinWidth(30);
            auctionsTable.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        Background.add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1040, 400));

        showAllButton.setText("Show All");
        showAllButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showAllButtonMouseClicked(evt);
            }
        });
        Background.add(showAllButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 98, -1));

        CloseButton.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        CloseButton.setForeground(new java.awt.Color(255, 255, 255));
        CloseButton.setText("X");
        CloseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseButtonMouseClicked(evt);
            }
        });
        Background.add(CloseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, 20, 20));

        singleAuction.setText("See Info");
        singleAuction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singleAuctionActionPerformed(evt);
            }
        });
        Background.add(singleAuction, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        updateButton.setText("Update");
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });
        Background.add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 90, -1));

        showPendingButton.setText("Show Pending");
        showPendingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPendingButtonMouseClicked(evt);
            }
        });
        Background.add(showPendingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, -1, -1));

        showAcceptedButton.setText("Show Accepted");
        showAcceptedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showAcceptedButtonMouseClicked(evt);
            }
        });
        Background.add(showAcceptedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, -1));

        showFinishedButton1.setText("Show Finished");
        showFinishedButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showFinishedButtonMouseClicked(evt);
            }
        });
        Background.add(showFinishedButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, -1, -1));

        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, 0, 1070, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
        try {
            DefaultTableModel dm = (DefaultTableModel) auctionsTable.getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            showData("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showButtonActionPerformed

    private void CloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseClicked
         System.exit(0);
    }//GEN-LAST:event_CloseButtonMouseClicked

    private void singleAuctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singleAuctionActionPerformed
        if(auctionsTable.getSelectedRowCount() > 0){
            try (Socket socket = new Socket(ipAddress, 1234)) {

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                GetImageRequest imgRequest = new GetImageRequest(AuctionList.auctionList.get(auctionsTable.getSelectedRow()).img);
                oos.writeObject(imgRequest);

                Object obj = ois.readObject();
                Image img;

                if (obj.getClass().getName().equals("admin.Image")
                    && (img = (Image) obj) != null) {
                    if (img.size > 0) {
                        String description = AuctionList.auctionList.get(auctionsTable.getSelectedRow()).description;
                        String html = "<html><body style='width: %1spx'>%1s";
                        Object[] options = {"OK"};
                        JOptionPane.showOptionDialog(
                        Background, 
                                String.format(html, 300, description),
                        auctionsTable.getValueAt(auctionsTable.getSelectedRow(), 1).toString(), 
                        JOptionPane.HEIGHT, 
                        JOptionPane.OK_OPTION,
                        new ImageIcon(img.imageByte),
                        options,
                        options[0]);
                    }
                }

                oos.close();
                ois.close();

                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if(auctionsTable.getRowCount() == 0){
                JOptionPane.showMessageDialog(Background, "Nothing to show");
            } else {
                JOptionPane.showMessageDialog(Background, "Select one or more rows");
            }
        }
    }//GEN-LAST:event_singleAuctionActionPerformed

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
        if(auctionsTable.getSelectedRowCount() > 0){
            if(AuctionList.auctionList.get(auctionsTable.getSelectedRow()).status.equals("pending")){
                try (Socket socket = new Socket(ipAddress, 1234)) {

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    
                    FetchUserInfoRequest request = new FetchUserInfoRequest(AuctionList.auctionList.get(auctionsTable.getSelectedRow()).userId);
                    oos.writeObject(request);

                    Object obj = ois.readObject();
                    FetchUserInfoResponse res;

                    if (obj.getClass().getName().equals("admin.FetchUserInfoResponse")
                        && (res = (FetchUserInfoResponse) obj) != null) {
                        JOptionPane.showOptionDialog(
                            Background, 
                            new updatePanel(res.userName, res.email, res.phone, res.registerNumber, AuctionList.auctionList.get(auctionsTable.getSelectedRow()).id, ipAddress),
                            "Update auction",
                            JOptionPane.NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new Object[]{},
                            null
                        );
                            
                    }
                    oos.close();
                    ois.close();
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else if (AuctionList.auctionList.get(auctionsTable.getSelectedRow()).status.equals("accepted")){
                try (Socket socket = new Socket(ipAddress, 1234)) {

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    
                    FetchUserInfoRequest request = new FetchUserInfoRequest(AuctionList.auctionList.get(auctionsTable.getSelectedRow()).userId);
                    oos.writeObject(request);

                    Object obj = ois.readObject();
                    FetchUserInfoResponse res;

                    if (obj.getClass().getName().equals("admin.FetchUserInfoResponse")
                        && (res = (FetchUserInfoResponse) obj) != null) {
                        JOptionPane.showOptionDialog(
                            Background, 
                            new updateAcceptedPanel(
                                    res.userName, 
                                    res.email, 
                                    res.phone, 
                                    res.registerNumber, 
                                    AuctionList.auctionList.get(auctionsTable.getSelectedRow()).id,
                                    AuctionList.auctionList.get(auctionsTable.getSelectedRow()).startTime,
                                    AuctionList.auctionList.get(auctionsTable.getSelectedRow()).endTime,
                                    ipAddress
                            ),
                            "Update auction",
                            JOptionPane.NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new Object[]{},
                            null
                        );
                            
                    }
                    oos.close();
                    ois.close();
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else if (AuctionList.auctionList.get(auctionsTable.getSelectedRow()).status.equals("finished")){
                try (Socket socket = new Socket(ipAddress, 1234)) {

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    
                    FetchUserInfoRequest request = new FetchUserInfoRequest(AuctionList.auctionList.get(auctionsTable.getSelectedRow()).userId);
                    oos.writeObject(request);

                    Object obj = ois.readObject();
                    FetchUserInfoResponse res;

                    if (obj.getClass().getName().equals("admin.FetchUserInfoResponse")
                        && (res = (FetchUserInfoResponse) obj) != null) {
                        JOptionPane.showOptionDialog(
                            Background, 
                            new updateWinnerPanel(
                                    res.userName, 
                                    res.email, 
                                    res.phone, 
                                    res.registerNumber, 
                                    AuctionList.auctionList.get(auctionsTable.getSelectedRow()).id,
                                    AuctionList.auctionList.get(auctionsTable.getSelectedRow()).winner,
                                    ipAddress
                            ),
                            "Update auction",
                            JOptionPane.NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new Object[]{},
                            null
                        );
                            
                    }
                    oos.close();
                    ois.close();
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
             if(auctionsTable.getRowCount() == 0){
                JOptionPane.showMessageDialog(Background, "Nothing to show");
            } else {
                JOptionPane.showMessageDialog(Background, "Select one or more rows");
            }  
        }
    }//GEN-LAST:event_updateButtonMouseClicked

    private void showAllButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showAllButtonMouseClicked
        try {
            DefaultTableModel dm = (DefaultTableModel) auctionsTable.getModel();
            int rowCount = dm.getRowCount();
            if(rowCount > 0){
               for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
                } 
            }
            showData("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showAllButtonMouseClicked

    private void showAcceptedButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showAcceptedButtonMouseClicked
        DefaultTableModel dm = (DefaultTableModel) auctionsTable.getModel();
        int rowCount = dm.getRowCount();
        if(rowCount > 0){
           for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
            } 
        }
        try {
            showData("accepted");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showAcceptedButtonMouseClicked

    private void showPendingButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPendingButtonMouseClicked
        DefaultTableModel dm = (DefaultTableModel) auctionsTable.getModel();
        int rowCount = dm.getRowCount();
        if(rowCount > 0){
           for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
            } 
        }
        try {
            showData("pending");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showPendingButtonMouseClicked

    private void showFinishedButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showFinishedButtonMouseClicked
        try {
            DefaultTableModel dm = (DefaultTableModel) auctionsTable.getModel();
            int rowCount = dm.getRowCount();
            if(rowCount > 0){
               for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
                } 
            }
            showData("finished");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showFinishedButtonMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel Background;
    private javax.swing.JLabel CloseButton;
    private javax.swing.JTable auctionsTable;
    private javax.swing.JButton showAcceptedButton;
    private javax.swing.JButton showAllButton;
    private javax.swing.JButton showFinishedButton1;
    private javax.swing.JButton showPendingButton;
    private javax.swing.JButton singleAuction;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}