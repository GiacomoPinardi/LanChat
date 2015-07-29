
package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Conversation implements Serializable {
    
    private ArrayList<Message> messages;
    private String id;
    private JTextArea textArea;

    public Conversation(String id) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setRows(22);
        textArea.invalidate();
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
    
    public void addMsgsOnTop (ArrayList<Message> ms) {        
        String total = "";
        for (Message m : ms) {
            total = total.concat(m.getSender() + ": " + m.getInformation() + "\n");
        }
        
        textArea.setText(total + textArea.getText());
        this.messages.addAll(0, ms);
    }
    
    public Message getMsg (int index) {
        return this.messages.get(index);
    }
    
    public ArrayList<Message> getAllMsgs () {
        return this.messages;
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
