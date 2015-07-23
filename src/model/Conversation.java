
package model;

import java.util.ArrayList;

public class Conversation {
    
    private ArrayList<Message> messages;
    private String id;

    public Conversation(String id) {
        this.id = id;
    }
    
    public void addMsgs (ArrayList<Message> ms) {
        this.messages.addAll(ms);
    }
    
    public void addMsg (Message m) {
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
    
}
