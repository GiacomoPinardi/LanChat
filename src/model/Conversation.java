
package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Conversation {
    
    private ArrayList<Message> messages;
    private HashSet<String> partecipants;
    private String id;

    public Conversation(String id, HashSet<String> partecipants) {
        this.id = id;
        this.partecipants = partecipants;
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
    
    public void addPartecipant (String name) {
        this.partecipants.add(name);
    }
    
    public void addPartecipants (ArrayList<String> partecipants) {
        this.partecipants.addAll(partecipants);
    }
    
    public String[] getAllPartecipants () {
        return this.partecipants.toArray(new String[0]);
    }
    
    public String getId () {
        return this.id;
    }
    
}
