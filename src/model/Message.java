
package model;

import java.io.Serializable;
import java.util.HashSet;

public class Message implements Serializable {
    
    private String information;
    private String sender;
    private HashSet<String> receivers;

    public Message(String sender, HashSet<String> receivers, String information) {        
        this.sender = sender;
        this.receivers = receivers;
        this.information = information;
    }    

    public String getSender() {
        return this.sender;
    }
    
    public HashSet<String> getReceivers () {
        return this.receivers;
    }
    
    public String getInformation() {
        return this.information;
    }
}
