/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.view;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import smart.home.security.model.Device;
import smart.home.security.model.Devices;
import smart.home.security.model.Notification;
import smart.home.security.model.Notifications;
import smart.home.security.utilities.DevicesMacAddressModel;
import smart.home.security.utilities.NotificationsModel;

/**
 *
 * @author shahi
 */
public class AvailableDevicesPanel extends javax.swing.JPanel {
    Map<String, String> deviceDetails;
     String token1=null;
    private void scanDevices() {
        deviceDetails = new HashMap();
        System.out.println("token=");
        try {
            
            String gg=null;
           
            Scanner s = new Scanner(Runtime.getRuntime().exec("arp -a").getInputStream()).useDelimiter("\n");
           int i=0;
            while (s.hasNext()) 
            {
                String token = s.next();
                System.out.println("token="+token);
                int length = token. length();
                 System.out.println("length="+length);
               //  token1=token;
                 
                 
                // i++;
            gg=token;
//                 //substring(token.length() - 30);//, (token.indexOf("dynamic")));
//                 
            String kk1=null;
            String kk=null;
            if(gg.length()>32 && gg.contains("dynamic"))
            {
              kk = gg.substring(gg.length() - 33, (gg.indexOf("dynamic")));
             
             if(kk.contains(" "))
             {
               int inx1=kk.indexOf(" ");// 
                  
              kk = kk.substring(0,inx1); 
         }
               kk1 = gg.substring(1,17);//gg.length() - 47);
             if(kk1.contains(" "))
             {
               int inx=kk1.indexOf(" ");// 
                  
              kk1 = kk1.substring(0,inx); 
         }
             System.out.println("macc="+kk);
             
                 // String kk1 = gg.substring(2,gg.length() - 47);
              System.out.println("ip="+kk1);
              
//                deviceDetails.put(kk,kk1);

            }
   deviceDetails.put(kk, kk1);
               
                
//               
            }
            
            //         String kk = token1.substring(token1.length - 33, (token1.inndexOf("static")));
             // System.out.println("macc="+kk);
               //      String kk1 = token1.substring(0,token1.length() - 33);
              //System.out.println("ip="+kk1);
              
//                String replace = token.substring(0, token.indexOf(" on")).replace("? (", "").replace(") at ", "-");
//                String[] deviceInfo = replace.split("-");
//                System.out.println("deviceInfo="+deviceInfo);
                 
//               
//              
        } catch (IOException ex) {
            Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void filterDevices() {
        for (Device device : Devices.getInstance().getDevices()) {
            deviceDetails.remove(device.getMacAddress());
        }
    }

    /**
     * Creates new form AvailableDevicePanel
     */
    public AvailableDevicesPanel() {
        initComponents();        
        scanDevices();
        filterDevices();
        DefaultTableModel model = DevicesMacAddressModel.defaultTableModel(deviceDetails.keySet());
        availableMacAddressTable.setModel(model);

//        List<String> ips = findAvailableDevices();
//        
//        for (String ip : ips) {
//            System.out.print("IP: " + ip);
//            try {
//                NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getByName(ip));
//                if (network != null) {
//                    byte[] mac = network.getHardwareAddress();
//                    
//
//                    StringBuilder sb = new StringBuilder();
//                    for (int i = 0; i < mac.length; i++) {        
//                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//                    }
//                    System.out.println(" - MAC: " + sb.toString());
//                }                
//                else {
//                    System.out.println();
//                }
//            } catch (SocketException ex) {
//                Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (UnknownHostException ex) {
//                Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }        
    }
//    
//    private List<String> findAvailableDevices() {
//        List<String> ips = new ArrayList();
//        try {
//            String subnet = getSubnet();
//            ExecutorService executorService = Executors.newFixedThreadPool(100);
//            
//            List<Callable<String>> callableTasks = new ArrayList<>();
//            for (int i = 1; i < 255; i++) {
//                String host = subnet + i;
//                Callable<String> callableTask = () -> {
//                    return InetAddress.getByName(host).isReachable(1000) ? host : null;
//                };
//                callableTasks.add(callableTask);
//            }
//            List<Future<String>> futures = executorService.invokeAll(callableTasks);
//            
//            for (Future future : futures) {
//                String ip = (String) future.get();
//                if (ip != null) {
//                    ips.add(ip);
//                }
//            }
//            executorService.shutdown();
//        } catch (InterruptedException | ExecutionException ex) {
//            Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return ips;
//    }
//    
//    private String getSubnet() {
//        String subnet = null;
//        try {
//            InetAddress ipAddr = InetAddress.getLocalHost();
//            String ip = ipAddr.getHostAddress();
//            subnet = ip.substring(0, ip.lastIndexOf('.') + 1);
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(AvailableDevicesPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return subnet;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private SmartHomeSecurityFrame getSmartHomeSecurityFrame() {
        return (SmartHomeSecurityFrame) SwingUtilities.getWindowAncestor(this);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        availableMacAddressTable = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Available Devices"));
        setPreferredSize(new java.awt.Dimension(397, 311));

        availableMacAddressTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device Mac Address"
            }
        ));
        availableMacAddressTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                availableMacAddressTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(availableMacAddressTable);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        getSmartHomeSecurityFrame().replaceFramePanel(new MainPanel());
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void availableMacAddressTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_availableMacAddressTableMouseClicked
        // TODO add your handling code here:
         int selectedIndex = availableMacAddressTable.getSelectedRow();
        if (selectedIndex >= 0)
        {
            String mac = (String) availableMacAddressTable.getValueAt(selectedIndex, 0);
            String ip = deviceDetails.get(mac);
            
            System.out.println("ip in avalible="+ip);
            getSmartHomeSecurityFrame().replaceFramePanel(new AddDevicePanel(mac, ip));            
        }    
        
        
        
    }//GEN-LAST:event_availableMacAddressTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable availableMacAddressTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
