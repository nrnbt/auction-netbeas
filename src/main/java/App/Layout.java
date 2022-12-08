package App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import Login.Login;
import Register.Registration;
import client.GetAllAuctionRequest;
import client.GetAllAuctionResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import client.GetAllAuctionRequest;
import client.GetAllAuctionResponse;

/**
 *
 * @author asus
 */
public class Layout extends javax.swing.JFrame {

    /**
     * Creates new form Layout
     */
    public Layout() {
        initComponents();
        try {
            showData("accepted");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(AuctionList != null && !AuctionList.auctionList.isEmpty()){
            emptyLabel.setVisible(false);
            int left = 15;
            int top = 10;
            int panelCount = 0;
            for(int i=0; i < AuctionList.auctionList.size(); i++){
//                AuctionComponent auction = new AuctionComponent();
//                auction.setVisible(true);
//                auction.setSize(150, 190);
//                auction.setPreferredSize(new Dimension(150,190));
//                javax.swing.GroupLayout auctionComponentLayout = new javax.swing.GroupLayout(auction);
//                auction.setLayout(auctionComponentLayout);
//                auctionComponentLayout.setHorizontalGroup(
//                    auctionComponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGap(0, 130, Short.MAX_VALUE)
//                );
//                auctionComponentLayout.setVerticalGroup(
//                    auctionComponentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGap(0, 150, Short.MAX_VALUE)
//                );
//                
//                auctionsPanel.add(auction, new org.netbeans.lib.awtextra.AbsoluteConstraints(left, top, 130, 150));
       
                javax.swing.JPanel auctionCardPanel = new javax.swing.JPanel();
                javax.swing.JPanel pricePanel = new javax.swing.JPanel();
                javax.swing.JPanel titlePanel = new javax.swing.JPanel();
                javax.swing.JLabel startPriceLabel = new javax.swing.JLabel();
                javax.swing.JLabel createdByLabel = new javax.swing.JLabel();   
                javax.swing.JLabel titleLabel = new javax.swing.JLabel(); 
                javax.swing.JLabel auctionImg = new javax.swing.JLabel();
                
                auctionCardPanel.setBackground(new java.awt.Color(204, 204, 204));
                auctionCardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                pricePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                startPriceLabel.setText("Starting Price:");
                pricePanel.add(startPriceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 30));

                auctionCardPanel.add(pricePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 160, 30));

                titlePanel.setBackground(new java.awt.Color(255, 255, 255));
                titlePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                createdByLabel.setText("Created by:");
                titlePanel.add(createdByLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 20));

                titleLabel.setText("Title:");
                titlePanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 20));

                auctionCardPanel.add(titlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 160, 50));
                auctionCardPanel.add(auctionImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

                scrollablePanel.add(auctionCardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(left, top, 150, 220));
                left = left + 200;
                panelCount = panelCount + 1;
                if((panelCount % 5) == 0){
                    top = top + 240;
                    left = 15;
                }
            }
        } else {
           emptyLabel.setVisible(true); 
        }
    }
    public static GetAllAuctionResponse AuctionList;
    
    public void showData(String stausFilter) throws ClassNotFoundException {
        ArrayList<GetAllAuctionResponse> auctionList = new ArrayList<>();
        try (Socket socket = new Socket("192.168.10.3", 1234)) {

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            GetAllAuctionRequest request = new GetAllAuctionRequest("auctions", stausFilter);
            oos.writeObject(request);
            
            Object obj = ois.readObject();
            GetAllAuctionResponse dataList;
            if (obj.getClass().getName().equals("client.GetAllAuctionResponse")
		&& (dataList = (GetAllAuctionResponse) obj) != null) {
                AuctionList = dataList;
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

        navbar = new com.k33ptoo.components.KGradientPanel();
        AppTitle = new javax.swing.JLabel();
        logOutButton = new com.k33ptoo.components.KButton();
        aboutUSButton = new com.k33ptoo.components.KButton();
        auctionsButton = new com.k33ptoo.components.KButton();
        createAuctionButton = new com.k33ptoo.components.KButton();
        myAuctionsButton = new com.k33ptoo.components.KButton();
        myBidsButton = new com.k33ptoo.components.KButton();
        tabs = new javax.swing.JTabbedPane();
        auctionsPanel = new keeptoo.KGradientPanel();
        header = new keeptoo.KGradientPanel();
        headerLabel = new javax.swing.JLabel();
        auctionsScroll = new javax.swing.JScrollPane();
        scrollablePanel = new keeptoo.KGradientPanel();
        emptyLabel = new javax.swing.JLabel();
        createAuctionPanel = new com.k33ptoo.components.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        myAuctionPanel = new keeptoo.KGradientPanel();
        myBidsPanel = new keeptoo.KGradientPanel();
        abousUsPanel = new keeptoo.KGradientPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navbar.setkBorderRadius(0);
        navbar.setkEndColor(new java.awt.Color(0, 255, 204));
        navbar.setkStartColor(new java.awt.Color(153, 0, 153));
        navbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AppTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        AppTitle.setForeground(new java.awt.Color(255, 255, 255));
        AppTitle.setText("ABEY AUCTION");
        navbar.add(AppTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 61));

        logOutButton.setText("Log Out");
        logOutButton.setkBorderRadius(30);
        logOutButton.setkEndColor(new java.awt.Color(51, 51, 255));
        logOutButton.setkHoverEndColor(new java.awt.Color(0, 204, 204));
        logOutButton.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        logOutButton.setkHoverStartColor(new java.awt.Color(0, 153, 153));
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });
        navbar.add(logOutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 130, 30));

        aboutUSButton.setText("About US");
        aboutUSButton.setkBorderRadius(30);
        aboutUSButton.setkEndColor(new java.awt.Color(0, 204, 204));
        aboutUSButton.setkHoverEndColor(new java.awt.Color(0, 255, 204));
        aboutUSButton.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        aboutUSButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutUSButtonActionPerformed(evt);
            }
        });
        navbar.add(aboutUSButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, 30));

        auctionsButton.setText("Auctions");
        auctionsButton.setkBorderRadius(30);
        auctionsButton.setkEndColor(new java.awt.Color(0, 204, 204));
        auctionsButton.setkHoverEndColor(new java.awt.Color(0, 255, 204));
        auctionsButton.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        auctionsButton.setkHoverStartColor(new java.awt.Color(153, 0, 153));
        auctionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auctionsButtonActionPerformed(evt);
            }
        });
        navbar.add(auctionsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 30));

        createAuctionButton.setText("Create Auction");
        createAuctionButton.setkBorderRadius(30);
        createAuctionButton.setkEndColor(new java.awt.Color(0, 204, 204));
        createAuctionButton.setkHoverEndColor(new java.awt.Color(0, 255, 204));
        createAuctionButton.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        createAuctionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAuctionButtonActionPerformed(evt);
            }
        });
        navbar.add(createAuctionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 30));

        myAuctionsButton.setText("My Auctions");
        myAuctionsButton.setkBorderRadius(30);
        myAuctionsButton.setkEndColor(new java.awt.Color(0, 204, 204));
        myAuctionsButton.setkHoverEndColor(new java.awt.Color(0, 255, 204));
        myAuctionsButton.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        myAuctionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myAuctionsButtonActionPerformed(evt);
            }
        });
        navbar.add(myAuctionsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 30));

        myBidsButton.setText("My Bids");
        myBidsButton.setkBorderRadius(30);
        myBidsButton.setkEndColor(new java.awt.Color(0, 204, 204));
        myBidsButton.setkHoverEndColor(new java.awt.Color(0, 255, 204));
        myBidsButton.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        myBidsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myBidsButtonActionPerformed(evt);
            }
        });
        navbar.add(myBidsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 30));

        getContentPane().add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        tabs.setPreferredSize(new java.awt.Dimension(1000, 500));

        auctionsPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        auctionsPanel.setkStartColor(new java.awt.Color(255, 255, 255));
        auctionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerLabel.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setText("Auctions");
        header.add(headerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 120, -1));

        auctionsPanel.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        scrollablePanel.setkEndColor(new java.awt.Color(255, 255, 255));
        scrollablePanel.setkStartColor(new java.awt.Color(255, 255, 255));
        scrollablePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emptyLabel.setFont(new java.awt.Font("SansSerif", 2, 24)); // NOI18N
        emptyLabel.setIcon(new javax.swing.ImageIcon("/home/nrnbt/NetBeansProjects/master/src/main/java/images/empty.png")); // NOI18N
        emptyLabel.setText("Sorry, No Active Auctions");
        scrollablePanel.add(emptyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 530, 190));

        auctionsScroll.setViewportView(scrollablePanel);

        auctionsPanel.add(auctionsScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1000, 440));

        tabs.addTab("Auctions", auctionsPanel);

        createAuctionPanel.setkEndColor(new java.awt.Color(153, 204, 255));
        createAuctionPanel.setkStartColor(new java.awt.Color(255, 204, 204));
        createAuctionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("create Auction");
        createAuctionPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 24, 150, 40));

        jTextField1.setText("Title");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        createAuctionPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 225, -1));

        jTextField2.setText("Desciption");
        createAuctionPanel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 225, 136));

        jTextField3.setText("Starting price");
        createAuctionPanel.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 106, -1));

        jButton7.setText("Add Image");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        createAuctionPanel.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        jTextField4.setText("IMG");
        createAuctionPanel.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 86, -1));

        jLabel3.setText("jLabel3");
        createAuctionPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 224, 189));

        tabs.addTab("createAcution", createAuctionPanel);

        myAuctionPanel.setkStartColor(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout myAuctionPanelLayout = new javax.swing.GroupLayout(myAuctionPanel);
        myAuctionPanel.setLayout(myAuctionPanelLayout);
        myAuctionPanelLayout.setHorizontalGroup(
            myAuctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        myAuctionPanelLayout.setVerticalGroup(
            myAuctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        tabs.addTab("myAuction", myAuctionPanel);

        myBidsPanel.setkStartColor(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout myBidsPanelLayout = new javax.swing.GroupLayout(myBidsPanel);
        myBidsPanel.setLayout(myBidsPanelLayout);
        myBidsPanelLayout.setHorizontalGroup(
            myBidsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        myBidsPanelLayout.setVerticalGroup(
            myBidsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        tabs.addTab("myBids", myBidsPanel);

        abousUsPanel.setkStartColor(new java.awt.Color(204, 255, 204));
        abousUsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tabs.addTab("aboutUs", abousUsPanel);

        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, -38, 1000, 540));
        tabs.getAccessibleContext().setAccessibleName("Home");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
       this.hide();
       new Login().setVisible(true);
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void auctionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auctionsButtonActionPerformed
        tabs.setSelectedIndex(0);
        try {
            showData("accepted");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
//        auctionsButton.setkStartColor(new Color(153,0,153));
//        auctionsButton.setkEndColor(new Color(0,255,204));
//        createAuctionButton.setkStartColor(new Color(0,153,153));
//        createAuctionButton.setkEndColor(new Color(0,204,204));
//        myAuctionsButton.setkStartColor(new Color(0,153,153));
//        myAuctionsButton.setkEndColor(new Color(0,204,204));
//        myBidsButton.setkStartColor(new Color(0,153,153));
//        myBidsButton.setkEndColor(new Color(0,204,204));
//        aboutUSButton.setkStartColor(new Color(0,153,153));
//        aboutUSButton.setkEndColor(new Color(0,204,204));
    }//GEN-LAST:event_auctionsButtonActionPerformed

    private void createAuctionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAuctionButtonActionPerformed
        tabs.setSelectedIndex(1);
//        createAuctionButton.setkStartColor(new Color(153,0,153));
//        createAuctionButton.setkEndColor(new Color(0,255,204));
//        auctionsButton.setkStartColor(new Color(0,153,153));
//        auctionsButton.setkEndColor(new Color(0,204,204));
//        myAuctionsButton.setkStartColor(new Color(0,153,153));
//        myAuctionsButton.setkEndColor(new Color(0,204,204));
//        myBidsButton.setkStartColor(new Color(0,153,153));
//        myBidsButton.setkEndColor(new Color(0,204,204));
//        aboutUSButton.setkStartColor(new Color(0,153,153));
//        aboutUSButton.setkEndColor(new Color(0,204,204));
    }//GEN-LAST:event_createAuctionButtonActionPerformed

    private void myAuctionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myAuctionsButtonActionPerformed
        tabs.setSelectedIndex(2);
//        myAuctionsButton.setkStartColor(new Color(153,0,153));
//        myAuctionsButton.setkEndColor(new Color(0,255,204));
//        auctionsButton.setkStartColor(new Color(0,153,153));
//        auctionsButton.setkEndColor(new Color(0,204,204));
//        createAuctionButton.setkStartColor(new Color(0,153,153));
//        createAuctionButton.setkEndColor(new Color(0,204,204));
//        myBidsButton.setkStartColor(new Color(0,153,153));
//        myBidsButton.setkEndColor(new Color(0,204,204));
//        aboutUSButton.setkStartColor(new Color(0,153,153));
//        aboutUSButton.setkEndColor(new Color(0,204,204));
    }//GEN-LAST:event_myAuctionsButtonActionPerformed

    private void myBidsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myBidsButtonActionPerformed
        tabs.setSelectedIndex(3);
//        myBidsButton.setkStartColor(new Color(153,0,153));
//        myBidsButton.setkEndColor(new Color(0,255,204));
//        auctionsButton.setkStartColor(new Color(0,153,153));
//        auctionsButton.setkEndColor(new Color(0,204,204));
//        createAuctionButton.setkStartColor(new Color(0,153,153));
//        createAuctionButton.setkEndColor(new Color(0,204,204));
//        myAuctionsButton.setkStartColor(new Color(0,153,153));
//        myAuctionsButton.setkEndColor(new Color(0,204,204));
//        aboutUSButton.setkStartColor(new Color(0,153,153));
//        aboutUSButton.setkEndColor(new Color(0,204,204));
    }//GEN-LAST:event_myBidsButtonActionPerformed

    private void aboutUSButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutUSButtonActionPerformed
        tabs.setSelectedIndex(4);
//        aboutUSButton.setkStartColor(new Color(153,0,153));
//        aboutUSButton.setkEndColor(new Color(0,255,204));
//        auctionsButton.setkStartColor(new Color(0,153,153));
//        auctionsButton.setkEndColor(new Color(0,204,204));
//        createAuctionButton.setkStartColor(new Color(0,153,153));
//        createAuctionButton.setkEndColor(new Color(0,204,204));
//        myAuctionsButton.setkStartColor(new Color(0,153,153));
//        myAuctionsButton.setkEndColor(new Color(0,204,204));
//        myBidsButton.setkStartColor(new Color(0,153,153));
//        myBidsButton.setkEndColor(new Color(0,204,204));
    }//GEN-LAST:event_aboutUSButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
           UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Layout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AppTitle;
    private keeptoo.KGradientPanel abousUsPanel;
    private com.k33ptoo.components.KButton aboutUSButton;
    private com.k33ptoo.components.KButton auctionsButton;
    private keeptoo.KGradientPanel auctionsPanel;
    private javax.swing.JScrollPane auctionsScroll;
    private com.k33ptoo.components.KButton createAuctionButton;
    private com.k33ptoo.components.KGradientPanel createAuctionPanel;
    private javax.swing.JLabel emptyLabel;
    private keeptoo.KGradientPanel header;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private com.k33ptoo.components.KButton logOutButton;
    private keeptoo.KGradientPanel myAuctionPanel;
    private com.k33ptoo.components.KButton myAuctionsButton;
    private com.k33ptoo.components.KButton myBidsButton;
    private keeptoo.KGradientPanel myBidsPanel;
    private com.k33ptoo.components.KGradientPanel navbar;
    private keeptoo.KGradientPanel scrollablePanel;
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
}
