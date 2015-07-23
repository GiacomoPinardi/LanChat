
package model;

import java.io.Serializable;

public class Message implements Serializable {
    
    private String information;
    private String sender;
    private String receiver;

    public Message(String sender, String receiver, String information) {        
        this.sender = sender;
        this.receiver = receiver;
        this.information = information;
    }    

    public String getSender() {
        return this.sender;
    }
    
    public String getReceiver () {
        return this.receiver;
    }
    
    public String getInformation() {
        return this.information;
    }
}
