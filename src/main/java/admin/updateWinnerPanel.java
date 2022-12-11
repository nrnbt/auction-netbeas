/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package admin;

import java.awt.Window;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 *
 * @author nrnbt_
 */
public class updateWinnerPanel extends javax.swing.JPanel {

    /**
     * Creates new form updatePanel
     */
    public int auctionId;
    public String userName;
    public String userEmail;
    public String userPhone;
    public String userRegisterNumber;
    public String ipAddress;
    public String winner;

    public updateWinnerPanel(String name, String email, String phone, String registerNumber, int id, String winner, String ipAddress) {
        this.ipAddress = ipAddress;
        this.winner = winner;
        auctionId = id;
        userName = name;
        userEmail = email;
        userPhone = phone;
        userRegisterNumber = registerNumber;
        initComponents();
        try {
            getBids();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updateWinnerPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        userRegisterNumberLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        userEmalLabel = new javax.swing.JLabel();
        userPhoneLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        winnerSelect = new javax.swing.JComboBox<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userRegisterNumberLabel.setText("user register number: " + userRegisterNumber);
        add(userRegisterNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));

        userNameLabel.setText("user name: " + userName);
        add(userNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 210, -1));

        userEmalLabel.setText("user email: " + userEmail);
        add(userEmalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 20));

        userPhoneLabel.setText("user phone number: " + userPhone);
        add(userPhoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        okButton.setText("Ok");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });
        add(okButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, -1));

        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });
        add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jLabel3.setText("Winner");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        add(winnerSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 250, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        try (Socket socket = new Socket(ipAddress, 1234)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Object item = winnerSelect.getSelectedItem();
            String value = ((ComboItem)item).getValue();
            UpdateAuctionWinnerRequest request = new UpdateAuctionWinnerRequest(
                    auctionId, 
                    value
                );
            oos.writeObject(request);
            oos.flush();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            if(response.contains("Updated")){
                int okClicked = JOptionPane.showOptionDialog(
                    null, 
                    "Successfully updated",
                    "Update Result",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"Ok"},
                    null
                );
                if(okClicked == 0){
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        if (window instanceof JDialog) {
                            JDialog dialog = (JDialog) window;
                            if (dialog.getContentPane().getComponentCount() == 1
                                && dialog.getContentPane().getComponent(0) instanceof JOptionPane){
                                dialog.dispose();
                                }
                            }
                        }  
                }
            } else {
                JOptionPane.showMessageDialog(null, response, "update auction error", JOptionPane.ERROR_MESSAGE);
            }
           
            oos.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_okButtonMouseClicked


    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.getContentPane().getComponentCount() == 1
                    && dialog.getContentPane().getComponent(0) instanceof JOptionPane){
                    dialog.dispose();
                }
            }
        }
    }//GEN-LAST:event_cancelButtonMouseClicked
    
    public void getBids() throws ClassNotFoundException{
        try (Socket socket = new Socket(ipAddress, 1234)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            GetBidsByAuctionIdRequest req = new GetBidsByAuctionIdRequest(auctionId);
            oos.writeObject(req);
            oos.flush();

            Object obj = ois.readObject();
            GetBidsByAuctionIdResponse response;

            if (obj.getClass().getName().equals("admin.GetBidsByAuctionIdResponse")
                && (response = (GetBidsByAuctionIdResponse) obj) != null) {
                if(response.bidsList.isEmpty()){
                    this.hide();
                    JOptionPane.showMessageDialog(null, "No bids on this auction");
                } else {
                    for(int i = 0; i < response.bidsList.size(); i ++){
                        
                        winnerSelect.addItem(
                                new ComboItem(
                                        response.bidsList.get(i).userName + " " + response.bidsList.get(i).price,
                                        Integer.toString(response.bidsList.get(i).id)
                                )
                        );
                        if(Integer.toString(response.bidsList.get(i).id).equals(winner)){
                            winnerSelect.setSelectedIndex(i);
                        }
                    }
                }
            }
            
            oos.close();
            ois.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel userEmalLabel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel userPhoneLabel;
    private javax.swing.JLabel userRegisterNumberLabel;
    private javax.swing.JComboBox<ComboItem> winnerSelect;
    // End of variables declaration//GEN-END:variables
}
