package App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import Login.Login;
import Register.Registration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import client.GetAllAuctionRequest;
import client.GetAllAuctionResponse;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.Timer;
/**
 *
 * @author asus
 */
public class Layout extends javax.swing.JFrame {

    /**
     * Creates new form Layout
     */
    
    public int auctionId;
    public int userId;
    public String ipAddress;
    
    public Layout(int userId_, String ipAddress) throws ParseException {
        initComponents();
        userId = userId_;
        this.ipAddress = ipAddress;
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
       
                javax.swing.JPanel auctionPanel = new javax.swing.JPanel();
                javax.swing.JPanel timerPanel = new javax.swing.JPanel();
                javax.swing.JLabel timerLabel = new javax.swing.JLabel();
                javax.swing.JPanel pricePanel = new javax.swing.JPanel();
                javax.swing.JLabel priceLabel = new javax.swing.JLabel();
                javax.swing.JPanel titlePanel = new javax.swing.JPanel();
                javax.swing.JLabel createdByLabel = new javax.swing.JLabel();
                javax.swing.JLabel titleLabel = new javax.swing.JLabel();
                javax.swing.JLabel imgLabel = new javax.swing.JLabel();

                auctionPanel.setMinimumSize(new java.awt.Dimension(200, 260));
                auctionPanel.setPreferredSize(new java.awt.Dimension(200, 260));
                auctionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                timerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                timerLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                timerPanel.add(timerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 30));
                timerPanel.setBackground(new Color(255,255,255));
                timerPanel.setBorder(new javax.swing.border.MatteBorder(0, 2, 2, 2, new java.awt.Color(0, 0, 0)));
                auctionPanel.add(timerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 190, 30));

                pricePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                priceLabel.setText("Start Price: " + AuctionList.auctionList.get(i).startPrice);
                priceLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                pricePanel.add(priceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 30));

                pricePanel.setBackground(new Color(255,255,255));
                pricePanel.setBorder(new javax.swing.border.MatteBorder(0, 2, 0, 2, new java.awt.Color(0, 0, 0)));
                auctionPanel.add(pricePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 190, 30));

                titlePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                createdByLabel.setText("Created by: " + AuctionList.auctionList.get(i).user);
                createdByLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                titlePanel.add(createdByLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 180, 25));

                titleLabel.setText("Title: " + AuctionList.auctionList.get(i).title);
                titleLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                titlePanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 25));
                titlePanel.setBackground(new Color(255,255,255));
                titlePanel.setBorder(new javax.swing.border.MatteBorder(0, 2, 0, 2, new java.awt.Color(0, 0, 0)));

                ImageIcon imageIcon = new ImageIcon(AuctionList.auctionList.get(i).img); 
                Image image = imageIcon.getImage(); 
                Image newimg = image.getScaledInstance(190, 190,  java.awt.Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(newimg));
                imgLabel.setBorder(new javax.swing.border.MatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

                auctionPanel.add(titlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 190, 50));
                auctionPanel.add(imgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 150));
                int id = AuctionList.auctionList.get(i).id;
                auctionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        auctionId = id;
                        auctionPanelMouseClicked(evt);
                    }
                });

                auctionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        auctionPanelMouseEntered(evt);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        auctionPanelMouseExited(evt);
                    }
                });
            

                scrollablePanel.add(auctionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
                Timer startCountTimer;
                Timer endCountTimer;
                if(AuctionList != null && AuctionList.auctionList != null && AuctionList.auctionList.get(i).startTime != null && AuctionList.auctionList.get(i).endTime != null){
                    DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date startDate = (Date)formatter1.parse(AuctionList.auctionList.get(i).startTime);
                    Date endDate = (Date)formatter1.parse(AuctionList.auctionList.get(i).endTime);
                    
                    endCountTimer = new Timer(1000, null);
                      endCountTimer.addActionListener((ActionEvent evt) -> {
                          long start = (endDate.getTime()  - new Date().getTime()) / 1000;
                          long second = start % 60;
                          start = start / 60;
                          long minut = start % 60;
                          start = start / 60;
                          long hour = start % 24;
                          start = start / 24;
                          long day = start % 7;
                          start = start / 7;
                          long week = start;
                          if((endDate.getTime() - new Date().getTime()) / 1000 % 60 >= 0){
                            timerLabel.setText("End Time: " +week+":"+ day+":"+ hour+":"+minut+":"+second);
                          } else {
                            auctionPanel.hide();
                          }
                    });

                    startCountTimer = new Timer(1000, null);
                      startCountTimer.addActionListener((ActionEvent evt) -> {
                          long start = (startDate.getTime() - new Date().getTime()) / 1000;
                          long second = start % 60;
                          start = start / 60;
                          long minut = start % 60;
                          start = start / 60;
                          long hour = start % 24;
                          start = start / 24;
                          long day = start % 7;
                          start = start / 7;
                          long week = start;
                          if((startDate.getTime() - new Date().getTime()) / 1000 % 60 >= 0){
                            timerLabel.setText("Start Time: " +week+":"+ day+":"+ hour+":"+minut+":"+second);
                          } else {
                              endCountTimer.start();
                          }
                    });
                      
                    if((startDate.getTime() - new Date().getTime()) / 1000 % 60 >= 0){
                        startCountTimer.start();
                    } else if ((endDate.getTime()  - new Date().getTime()) / 1000 % 60 >= 0){
                        endCountTimer.start();
                    }
                }
                
                scrollablePanel.add(auctionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(left, top, 190, 260));
                left = left + 200;
                panelCount = panelCount + 1;
                if((panelCount % 5) == 0){
                    top = top + 280;
                    left = 15;
                }
            }
        } else {
           emptyLabel.setVisible(true); 
        }
    }
    public static GetAllAuctionResponse AuctionList;
    
    public void showData(String stausFilter) throws ClassNotFoundException {
        try (Socket socket = new Socket(ipAddress, 1234)) {

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

        auctionsPanel.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 70));

        scrollablePanel.setkEndColor(new java.awt.Color(255, 255, 255));
        scrollablePanel.setkStartColor(new java.awt.Color(255, 255, 255));
        scrollablePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emptyLabel.setFont(new java.awt.Font("SansSerif", 2, 24)); // NOI18N
        emptyLabel.setIcon(new javax.swing.ImageIcon("/home/nrnbt/NetBeansProjects/master/src/main/java/images/empty.png")); // NOI18N
        emptyLabel.setText("Sorry, No Active Auctions");
        scrollablePanel.add(emptyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 530, 190));

        auctionsScroll.setViewportView(scrollablePanel);

        auctionsPanel.add(auctionsScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1040, 440));

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
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        myAuctionPanelLayout.setVerticalGroup(
            myAuctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        tabs.addTab("myAuction", myAuctionPanel);

        myBidsPanel.setkStartColor(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout myBidsPanelLayout = new javax.swing.GroupLayout(myBidsPanel);
        myBidsPanel.setLayout(myBidsPanelLayout);
        myBidsPanelLayout.setHorizontalGroup(
            myBidsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        myBidsPanelLayout.setVerticalGroup(
            myBidsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        tabs.addTab("myBids", myBidsPanel);

        abousUsPanel.setkStartColor(new java.awt.Color(204, 255, 204));
        abousUsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tabs.addTab("aboutUs", abousUsPanel);

        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, -38, 1040, 540));
        tabs.getAccessibleContext().setAccessibleName("Home");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
       this.hide();
       new Login(ipAddress).setVisible(true);
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void auctionPanelMouseEntered(java.awt.event.MouseEvent evt) {                                    
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }  
    
    private void auctionPanelMouseExited(java.awt.event.MouseEvent evt) {                                    
        evt.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }  
    
    private void auctionPanelMouseClicked(java.awt.event.MouseEvent evt) {                                               
        this.hide();
        new AuctionById(auctionId, userId, ipAddress).setVisible(true);
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
