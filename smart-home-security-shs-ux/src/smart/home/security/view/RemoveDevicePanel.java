/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.home.security.view;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import smart.home.security.model.Device;
import smart.home.security.model.Devices;
import smart.home.security.utilities.DeviceSocketManager;
import smart.home.security.utilities.DeviceTableModel;

/**
 *
 * @author chana
 */
public class RemoveDevicePanel extends javax.swing.JPanel {

    public RemoveDevicePanel() {
        initComponents();

        List<Device> devices = Devices.getInstance().getDevices();
        DefaultTableModel model = DeviceTableModel.defaultTableModel(devices);

        removeDeviceTable.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<String>();
        removeButton = new javax.swing.JButton();
        cancelRemoveButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        removeDeviceTable = new javax.swing.JTable();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remove Device", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N
        setPreferredSize(new java.awt.Dimension(408, 309));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        removeButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        removeButton.setText("REMOVE");
        removeButton.setToolTipText("Are you sure");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        add(removeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 90, -1));

        cancelRemoveButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        cancelRemoveButton.setText("CANCEL");
        cancelRemoveButton.setToolTipText("");
        cancelRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelRemoveButtonActionPerformed(evt);
            }
        });
        add(cancelRemoveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 90, -1));

        removeDeviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DEVICE NAME", "DEVICE ID"
            }
        ));
        jScrollPane3.setViewportView(removeDeviceTable);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 380, 240));
    }// </editor-fold>//GEN-END:initComponents
    private SmartHomeSecurityFrame getSmartHomeSecurityFrame() {
        return (SmartHomeSecurityFrame) SwingUtilities.getWindowAncestor(this);
    }
    private void cancelRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelRemoveButtonActionPerformed
        // TODO add your handling code here:
        getSmartHomeSecurityFrame().replaceFramePanel(new MainPanel());
    }//GEN-LAST:event_cancelRemoveButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
   int selectedIndex = removeDeviceTable.getSelectedRow();
        if (selectedIndex >= 0) {

            String str = evt.getActionCommand();
            if (str.equals("REMOVE")) {
          SwingUtilities.invokeLater(() -> 
        {
           // JDialog.setDefaultLookAndFeelDecorated(true);
            JOptionPane optionPane = new JOptionPane("Are you sure you want to remove this device",
                                                     JOptionPane.QUESTION_MESSAGE,
                                                     JOptionPane.YES_NO_OPTION);


            JDialog dialog = optionPane.createDialog("");
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

            Timer timer = new Timer(5000, e -> dialog.setVisible(false));
            timer.setRepeats(false);
            timer.start();

            dialog.setVisible(true);

            if (optionPane.getValue() instanceof Integer) {
                int option = (Integer) optionPane.getValue();

                if (option == JOptionPane.NO_OPTION) 
                {
                    getSmartHomeSecurityFrame().replaceFramePanel(new MainPanel());
                    System.out.println("No button clicked");
                }
                else if (option == JOptionPane.YES_OPTION) 
                {
                    Device device = Devices.getInstance().getDevices().get(selectedIndex);
                    Devices.getInstance().removeDevice(device);
                    DeviceSocketManager.getInstance().disconnectDevice(device);
                    JOptionPane.showMessageDialog(this, "The Device Has Been Sucessfully Removed");
                    System.out.println("Yes button clicked");
                    
                }
            }
            else {
                System.out.println("dialog was closed by timer");
            }

//            System.out.println("Outside code.");
        });

 
     
//                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this device", "Roseindia.net", JOptionPane.YES_NO_OPTION) == 0) {
//                    Device device = Devices.getInstance().getDevices().get(selectedIndex);
//                    Devices.getInstance().removeDevice(device);
//                    DeviceSocketManager.getInstance().disconnectDevice(device);
//                    JOptionPane.showMessageDialog(this, "The Device Has Been Sucessfully Removed");
////                    JOptionPane.showMessageDialog(null, "You clicked on \"Ok\" button", "Roseindia.net", 1);
//                    getSmartHomeSecurityFrame().replaceFramePanel(new MainPanel());
//                } else {
//                    getSmartHomeSecurityFrame().replaceFramePanel(new MainPanel());
////                    JOptionPane.showMessageDialog(null, "You clicked on \"Cancel\" button", "Roseindia.net", 1);
//                }
//            } else if (str.equals("Show \"Yes/No/Cancel\" dialog box")) {
//                JOptionPane.showConfirmDialog(null, "This is the \"Yes/No/Cancel\" message dialog box.");
//            }

//            JOptionPane.showMessageDialog(this, "Device removed");
//            getSmartHomeSecurityFrame().replaceFramePanel(new MainPanel());
            }
        }
    }//GEN-LAST:event_removeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelRemoveButton;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton removeButton;
    private javax.swing.JTable removeDeviceTable;
    // End of variables declaration//GEN-END:variables
}