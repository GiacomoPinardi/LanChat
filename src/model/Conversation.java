
package model;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class Conversation {
    
    private ArrayList<Message> messages;
    private String id;
    private JTextArea textArea;

    public Conversation(String id) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
    }
    
    public void addMsgs (ArrayList<Message> ms) {
        for (Message m : ms) {
            textArea.append(m.getSender() + ": " + m.getInformation() + "\n");
        }
        this.messages.addAll(ms);
    }
    
    public void addMsg (Message m) {
        textArea.append(m.getSender() + ": " + m.getInformation() + "\n");
        this.messages.add(m);
    }
    
    public Message getMsg (int index) {
        return this.messages.get(index);
    }
    
    public void clearAllMsgs () {
        this.messages.clear();
    }
        
    public String getId () {
        return this.id;
    }
    
    public JTextArea getTextArea () {
        return this.textArea;
    }
    
    public int size () {
        return messages.size();
    }
    
}
