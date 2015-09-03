
package view;

import control.Worker;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
    
    private boolean canUpdateOnlinePeopleList;
    
    private JFileChooser fileChooser;
    
    // true: you have been kicked
    private boolean kicked;
    
    private AboutInterface AI;    
    
    public GraphicInterfaceClient(String clientName) {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.conversations = new HashMap<>();        
        
        this.forServer = new PacketQueue();
        
        this.online = true;
        
        this.clientName = clientName;
        
        this.canUpdateOnlinePeopleList = true;
        
        this.fileChooser = new JFileChooser();
        
        this.kicked = false;
        
        this.AI = new AboutInterface();
        
        // enter key will push 'send' button
        rootPane.setDefaultButton(jButton2);
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
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

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

        jMenuItem2.setText("Close chat");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Quit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Conversation");

        jMenu3.setText("Save");

        jMenuItem8.setText("Save current");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Save all");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenu2.add(jMenu3);

        jMenuItem4.setText("Load");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenu4.setText("Delete");

        jMenuItem6.setText("Delete current");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem10.setText("Delete all");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenu2.add(jMenu4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("About");

        jMenuItem3.setText("About LanChat");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(jTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (!kicked) {
            int r = JOptionPane.showConfirmDialog(rootPane, "Are you sure?\nAll unsaved conversations will be lost!", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            // 'online' is a boolean variable that other class check to ensure that client interface is on        
            if (r == 0) {
                this.online = false;                
                this.dispose();
            }
        }
        else {
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
                        this.openNewChat(jList1.getSelectedValue().toString());
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
        this.send();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jTabbedPane1.remove(jTabbedPane1.getSelectedIndex());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        this.save(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        this.save(false);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.delete(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        this.delete(false);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.load();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        AI.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int currentIndex = jTabbedPane1.getSelectedIndex();            
        if (currentIndex != -1) {
            jTabbedPane1.setTitleAt(currentIndex, jTabbedPane1.getTitleAt(currentIndex).replace("# ", ""));
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked
     
    private void send () {
        if (!jTextField1.getText().equals("") && jTabbedPane1.getSelectedIndex() != -1) {
            // new packet is created
            int indexPane = jTabbedPane1.getSelectedIndex();
            String sender = this.clientName;
            String receiver = jTabbedPane1.getTitleAt(indexPane);
            
            if (receiver.contains("# ")) {
                receiver = receiver.replace("# ", "");
            }
            
            ArrayList<Message> al = new ArrayList<>();
            Message msg = new Message(sender, receiver, jTextField1.getText());
            al.add(msg);
            
            Packet p = new Packet(sender, receiver, al, null, 0);
            
            this.forServer.add(p);            
            
            // conversation is updated with the new message
            Conversation c = conversations.get(receiver);            
            c.addMsg(msg);
            conversations.put(receiver, c);
            
            jTextField1.setText("");
        }
    }
    
    public void updateWindow (PacketQueue pq) {       
        // contains all online people from packets
        TreeSet<String> onlinePeople = new TreeSet<>();
        
        boolean onlineUpdater = false;
        
        for (Packet p : pq.getAll()) {
            // get list of online people at the moment
            if (p.getOnlinePeople() != null) {
                onlinePeople.addAll(p.getOnlinePeople());
                onlineUpdater = true;
            }
            
            if (p.getData() != null) {
                // will contain all the name of the new messages: this names will be shown with #
                ArrayList<String> newChatNames = new ArrayList<>();
                
                // new messages saved in conversations
                for (Message m : p.getData()) {                    
                    String name = m.getSender();
                    Conversation c;
                    
                    // if message are global, it will be put in the chat called 'ALL'
                    if (m.getReceiver().equals("ALL")) {
                        name = "ALL";
                    }
                    
                    newChatNames.add(name);
                    
                    int indexConversation = this.tabbedPaneContains(name);                    
                    
                    if (conversations.containsKey(name)) {
                        // exists conversation
                        c = conversations.get(name);
                        
                        if (indexConversation == -1) {
                            // conversation isn't shown
                            jTabbedPane1.addTab(name, new JScrollPane(c.getTextArea()));
                        }
                    }
                    else {
                        // new conversation
                        c = new Conversation(name);
                        jTabbedPane1.addTab(name, new JScrollPane(c.getTextArea()));
                    }
                    // message is added                     
                    c.addMsg(m);
                    conversations.put(name, c);                    
                }
                
                int indexShowing = jTabbedPane1.getSelectedIndex();
                String currentShowing = "";                
                
                if (indexShowing != -1) {
                    currentShowing = jTabbedPane1.getTitleAt(indexShowing);
                }                
                for (String name : newChatNames) {
                    if (!currentShowing.equals(name)) {
                        int index = this.tabbedPaneContains(name);
                        if (index != -1) {
                            jTabbedPane1.setTitleAt(index,"# " + name);
                        }
                    }
                }
            }
            else if (p.getAction() == 5) {
                if (!kicked) {
                    this.setConsoleText(false, "Client name doesen't match with ip! Try to exit and reconnect.");
                }                
            }
            else if (p.getAction() == 8) {
                kicked = true;
                this.setConsoleText(false, "You have been kicked from server! Try to exit and reconnect.");
                JOptionPane.showMessageDialog(rootPane, "You have been kicked!\nTry to exit and reconnect.", "Kicked!", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if (onlineUpdater) {        
            onlinePeople.remove(this.clientName);
            onlinePeople.add("ALL");
            this.updateOnlinePeopleList(onlinePeople);
        }
        
    }
    
    private void openNewChat (String chatName) {        
        Conversation c;
        
        if (conversations.containsKey(chatName)) {
            // exists conversation
            c = conversations.get(chatName);
        }
        else {
            // new conversation is created
            c = new Conversation(chatName);
        }
        
        conversations.put(chatName, c);
        
        jTabbedPane1.add(chatName, new JScrollPane(c.getTextArea()));
    }
    
    private int tabbedPaneContains (String chatName) {
        // return index of chat if contains it, else return -1
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            if (chatName.equals(jTabbedPane1.getTitleAt(i).replace("# ", ""))) {
                return i;
            }
        }        
        return -1;
    }
    
    private void save (boolean b) {
        // b: true -> current ; b: false -> all
        ArrayList<Conversation> al = new ArrayList<>();
        
        if (b) {
            // current conversation
            int indexConversation = jTabbedPane1.getSelectedIndex();
            if (indexConversation != -1) {
                // conversation selected
                al.add(conversations.get(jTabbedPane1.getTitleAt(indexConversation)));
            }
            else {
                // no conversation selected
                JOptionPane.showMessageDialog(rootPane, "Please click the conversation you want to save!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            // all conversation
            if (!conversations.isEmpty()) {
                for (String name : conversations.keySet()) {
                    al.add(conversations.get(name));
                }
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "There aren't any conversations to save!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if (!al.isEmpty()) {
            // all is good
            fileChooser.showSaveDialog(rootPane);
            File selected = fileChooser.getSelectedFile();
            if (selected != null) {
                if (Worker.serializeConversations(al, selected)) {
                    JOptionPane.showMessageDialog(rootPane, "Conversation/s successful saved.", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(rootPane, "Cannot save conversation/s!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }   
        }        
    }
    
    private void delete (boolean b) {
        // b: true -> current ; b: false -> all
        if (b) {
            // current conversation
            int indexConversation = jTabbedPane1.getSelectedIndex();
            if (indexConversation != -1) {
                // conversation selected
                int r = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete conversation with " + jTabbedPane1.getTitleAt(indexConversation) + "?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (r == 0) {
                    // delete
                    conversations.remove(jTabbedPane1.getTitleAt(indexConversation));
                    jTabbedPane1.remove(indexConversation);
                }
            }
            else {
                // no conversation selected
                JOptionPane.showMessageDialog(rootPane, "Please click the conversation you want to delete!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }            
        }
        else {
            // all conversations
            if (!conversations.isEmpty()) {
                int r = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete all conversations?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (r == 0) {
                    // delete
                    conversations.clear();
                    jTabbedPane1.removeAll();
                }
            }
            else {
                // conversations is empty
                JOptionPane.showMessageDialog(rootPane, "There aren't any conversations!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void load () {
        fileChooser.showOpenDialog(rootPane);
        File selected = fileChooser.getSelectedFile();
        if (selected != null) {
            ArrayList<Conversation> al = Worker.deserializeConversations(selected);
            if (al != null) {
                // conversation are loaded
                for (Conversation c : al) {
                    Conversation tmp;
                    if (conversations.containsKey(c.getId())) {
                        // old conversation have to merge new one
                        tmp = conversations.get(c.getId());
                        tmp.addMsgsOnTop(c.getAllMsgs());                        
                    }
                    else {
                        // new conversation
                        tmp = c;
                    }
                    
                    conversations.put(c.getId(), tmp);                    
                    
                    int indexConversation = this.tabbedPaneContains(c.getId());

                    if (indexConversation != -1) {
                        // chat with the same id is already running
                        jTabbedPane1.remove(indexConversation);
                    }
                    Conversation updatedConversation = conversations.get(c.getId());
                    jTabbedPane1.addTab(c.getId(), new JScrollPane(updatedConversation.getTextArea()));
                }
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "Cannot load conversations!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }
    
    private void setEnabledComponent (boolean b) {
        jButton2.setEnabled(b);
        jMenu1.setEnabled(b);
        jMenu2.setEnabled(b);
        jMenu5.setEnabled(b);
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
        jLabel2.setText("Online: " + (onlinePeople.size()));
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
    
    public boolean isKicked () {
        return this.kicked;
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
