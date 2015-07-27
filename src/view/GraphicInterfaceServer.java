
package view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Vector;
import javax.swing.JOptionPane;

public class GraphicInterfaceServer extends javax.swing.JFrame {
    
    // true if GUI is on, false otherwise
    private boolean online;
    
    // true if ServerManager can update window, false otherwise
    private boolean canUpdate;
    
    // manage clipboard
    Clipboard clipboard;
    StringSelection selection;
    
    public GraphicInterfaceServer() {
        initComponents();        
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.online = true;
        this.canUpdate = true;
        
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Ip:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Messages:");

        jToggleButton1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jToggleButton1.setText("Copy Name");
        jToggleButton1.setFocusable(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jToggleButton2.setText("Copy Ip");
        jToggleButton2.setFocusable(false);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("Action");

        jMenuItem1.setText("Quit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        jMenuItem2.setText("Stop refresh");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToggleButton1)
                        .addGap(37, 37, 37)
                        .addComponent(jToggleButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int r;
        
        if (jList1.getModel().getSize() != 0) {
            r = JOptionPane.showConfirmDialog(rootPane, "Are you sure?\nOther people cannot chat if you quit.", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
        else {
            r = 0;
        }        
                
        if (r == 0) {
            this.online = false;
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (jMenuItem2.getText().equalsIgnoreCase("Stop refresh")) {
            this.canUpdate = false;
            jMenuItem2.setText("Start refresh");
        }
        else {
            this.canUpdate = true;
            jMenuItem2.setText("Stop refresh");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            // toggle button pressed
            
            jToggleButton2.setEnabled(false);
            this.setEnabledComponent(false);
            this.canUpdate = false;
        }
        else {
            // toggle button released
            if (jList1.getSelectedIndex() != -1) {
                if (jList1.getMaxSelectionIndex() - jList1.getMinSelectionIndex() == 0) {                
                    String s = (String) jList1.getSelectedValue();
                    String data[] = s.replace(" ", "").split(String.valueOf((char) 007));
                    
                    selection = new StringSelection(data[0]);
                    clipboard.setContents(selection, null);
                }
                else {
                    // two or more selected
                    JOptionPane.showMessageDialog(rootPane, "Please select ONE item!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                // no one selected
                JOptionPane.showMessageDialog(rootPane, "Please select one item from the list!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            jToggleButton2.setEnabled(true);
            this.setEnabledComponent(true);
            this.canUpdate = true;
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        if (jToggleButton2.isSelected()) {
            // toggle button pressed
            
            jToggleButton1.setEnabled(false);
            this.setEnabledComponent(false);
            this.canUpdate = false;
        }
        else {
            // toggle button released
            if (jList1.getSelectedIndex() != -1) {
                if (jList1.getMaxSelectionIndex() - jList1.getMinSelectionIndex() == 0) {                
                    String s = (String) jList1.getSelectedValue();
                    String data[] = s.replace(" ", "").split(String.valueOf((char) 007));
                    
                    selection = new StringSelection(data[1]);
                    clipboard.setContents(selection, null);
                }
                else {
                    // two or more selected
                    JOptionPane.showMessageDialog(rootPane, "Please select ONE item!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                // no one selected
                JOptionPane.showMessageDialog(rootPane, "Please select one item from the list!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            jToggleButton1.setEnabled(true);
            this.setEnabledComponent(true);
            this.canUpdate = true;
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed
    
    private void setEnabledComponent (boolean b) {
        jMenu1.setEnabled(b);
        jMenu2.setEnabled(b);
        jMenuItem1.setEnabled(b);
        jMenuItem2.setEnabled(b);
    }
    
    public void updateWindow (String[][] data) {
        // [x][0]: name ; [x][1]: ip ; [x][2]: msg to send
        // update jList with the new stats and info 
        
        Vector<String> v = new Vector<>();
        
        for (int i = 0; i < data.length; i++) {
            String s1 = String.format("%-18s%s", data[i][0], ((char) 007) + data[i][1]);
            v.add(String.format("%-40s%s", s1, ((char) 007) + data[i][2]));
        }
        
        jList1.setListData(v);
    }
    
    public boolean isOnline () {
        return this.online;
    }
    
    public boolean canUpdate () {
        return this.canUpdate;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
