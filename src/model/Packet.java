
package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable {
    
    private String sender;
    private String receiver;
    
    // data == null --> Client ask Server new messages
    private ArrayList<Message> data;
    
    // action == 1 --> sender Client connect to Server
    // action == 2 --> sender Client disconnect from Server
    // action == 3 --> sender Client joined the server correctly
    // action == 4 --> sender Client hasn't joined the server correctly: duplicate name of clients
    // action == 5 --> sender Client hasn't joined the server
    private int action;
    
    public Packet (String sender, String receiver, ArrayList<Message> data, int action) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
        this.action = action;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public ArrayList<Message> getData() {
        return data;
    }

    public int getAction() {
        return action;
    }
}
