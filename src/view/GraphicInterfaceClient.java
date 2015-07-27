
package view;

import control.Worker;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Conversation;
import model.Message;
import model.Packet;
import model.PacketQueue;

public class GraphicInterfaceClient extends javax.swing.JFrame {
    
    // String: name ; Conversation: conversations
    private HashMap<String, Conversation> conversations;
    // all the packets for server
    private PacketQueue forServer;
    // client is onlie: true
    private boolean online;
    
    private String clientName;
    
    boolean canUpdateOnlinePeopleList;
    
    public GraphicInterfaceClient(String clientName) {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.conversations = new HashMap<>();        
        /*  
        JTextArea jta = new JTextArea();
        jta.setEditable(false);
        jTabbedPane1.addTab("All", jta);
        */
        this.forServer = new PacketQueue();
        
        this.online = true;
        
        this.clientName = clientName;
        
        this.canUpdateOnlinePeopleList = true;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jButton2.setText("Send");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Online: ");

        jToggleButton1.setText("New");
        jToggleButton1.setFocusable(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int r = JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        // 'online' is a boolean variable that other class check to ensure that client interface is on        
        if (r == 0) {
            this.online = false;
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            // toggle button pressed
            
            this.setEnabledComponent(false);
            this.canUpdateOnlinePeopleList = false;
        }
        else {
            // toggle button released
            if (jList1.getSelectedIndex() != -1) {
                if (jList1.getMaxSelectionIndex() - jList1.getMinSelectionIndex() == 0) {                
                    if (this.tabbedPaneContains(jList1.getSelectedValue().toString()) == -1) {
                        this.openNewChat(true, jList1.getSelectedValue().toString(), clientName);
                    }
                    else {
                        // chat is already shown
                        JOptionPane.showMessageDialog(rootPane, "Chat you have selected is already shown!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }                    
                }
                else {
                    // two or more selected
                    JOptionPane.showMessageDialog(rootPane, "Please select ONE person!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                // no one selected
                JOptionPane.showMessageDialog(rootPane, "Please select one person from the list!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            this.setEnabledComponent(true);
            this.canUpdateOnlinePeopleList = true;            
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!jTextField1.getText().equals("") && jTabbedPane1.getSelectedIndex() != -1) {
            // new packet is created
            int indexPane = jTabbedPane1.getSelectedIndex();
            String sender = this.clientName;
            String receiver = jTabbedPane1.getTitleAt(indexPane);
            ArrayList<Message> al = new ArrayList<>();
            Message msg = new Message(sender, receiver, jTextField1.getText());
            al.add(msg);
            
            Packet p = new Packet(sender, receiver, al, null, 0);
            
            this.forServer.add(p);            
            
            // jTabbedPane is updated with new message
            JTextArea jta = (JTextArea) jTabbedPane1.getSelectedComponent();
            jta.append(sender + ": " + jTextField1.getText() + "\n");
            
            jTabbedPane1.insertTab(receiver, null, jta, null, indexPane); 
            
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
     
    public void updateWindow (PacketQueue pq) {       
        // contains all online people from packets
        TreeSet<String> onlinePeople = new TreeSet<>();
        
        for (Packet p : pq.getAll()) {
            // get list of online people at the moment
            if (p.getOnlinePeople() != null) {
                onlinePeople.addAll(p.getOnlinePeople());
                onlinePeople.remove(this.clientName);
            }
            
            if (p.getData() != null) {
                // new messages saved in conversations
                for (Message m : p.getData()) {

                    String sender = m.getSender();
                    Conversation c;

                    int indexConversation = this.tabbedPaneContains(sender);                    
                    
                    if (indexConversation != -1) {                        
                        // conversation with sender is visible in the tabbedPane                        
                        JTextArea jta = (JTextArea) jTabbedPane1.getComponentAt(indexConversation);
                        jta.append(sender + ": " + m.getInformation() + "\n");                        
                        jTabbedPane1.insertTab(sender, null, jta, null, indexConversation);
                    }
                    else {
                        // conversation is not visible                        
                        this.openNewChat(false, sender, m.getInformation());                        
                    }
                    
                    if (conversations.containsKey(sender)) {
                        // exists conversation
                        c = conversations.get(sender);
                    }
                    else {
                        // new conversation
                        c = new Conversation(sender);
                    }

                    c.addMsg(m);
                    conversations.put(sender, c);
                }
            }
        }
        
        //if (!onlinePeople.isEmpty()) {
            this.updateOnlinePeopleList(onlinePeople);
        //}
        
    }
    
    // noMessage: true --> no new message to show
    private void openNewChat (boolean noMessage, String sender, String message) {
        JTextArea jta = new JTextArea();
                        
        jta.setEditable(false);
        jta.setLineWrap(true);

        if (!noMessage) {
            jta.append(sender + ": " + message + "\n");
        }
        
        if (this.conversations.containsKey(sender)) {
            Conversation c = this.conversations.get(sender);
            for (int i = 0; i < c.size(); i++) {
                Message m = c.getMsg(i);
                jta.append(m.getSender() + ": " + m.getInformation() + "\n");
            }
        } 
        
        jTabbedPane1.add(sender, jta);
    }
    
    // TEST - TO DO (usare una copia dell'app!)
    private JTextArea getTextArea (JScrollPane jsp) {
        for (Component c : jsp.getComponents()) {
            if (c.getName() != null) {
                if (c.getName().equals("textArea")) {
                    return (JTextArea) c;
                }
            }
        }
        return null;
    }
    
    private int tabbedPaneContains (String chatName) {
        // return index of chat if contains it, else return -1
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            if (chatName.equals(jTabbedPane1.getTitleAt(i))) {
                return i;
            }
        }        
        return -1;
    }
    
    private void setEnabledComponent (boolean b) {
        jButton2.setEnabled(b);
        jMenu1.setEnabled(b);
        jMenuItem1.setEnabled(b);
    }
    
    public PacketQueue getPacketForServer () {
        PacketQueue tmp = new PacketQueue();
        tmp.addAll(forServer);
        
        forServer.clear();
        
        return tmp;
    }
    
    private void updateOnlinePeopleList (TreeSet<String> onlinePeople) {
        if (this.canUpdateOnlinePeopleList) {
            // all online people are showed
            jList1.setListData(Worker.SetToVector(onlinePeople));
        }        
        jLabel2.setText("Online: " + (onlinePeople.size() + 1));
    }
    
    public void setConsoleText (boolean type, String text) {
        // type: true --> all is good
        // type: false --> error
        jLabel1.setText(text);
        if (type) {
            jLabel1.setForeground(Color.BLACK);
        }
        else {
            jLabel1.setForeground(Color.RED);
        }
    }               
    
    public boolean isOnline () {
        return this.online;
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
