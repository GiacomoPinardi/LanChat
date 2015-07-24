
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class Packet implements Serializable {
    
    private String sender;
    private String receiver;
    
    // data == null --> Client ask Server new messages
    private ArrayList<Message> data;
    
    TreeSet<String> onlinePeople;
    
    // action == -1 --> unknown error
    // action == 0 --> normal packet
    // action == 1 --> sender Client connect to Server
    // action == 2 --> sender Client disconnect from Server
    // action == 3 --> sender Client joined the server correctly
    // action == 4 --> sender Client hasn't joined the server correctly: duplicate name of clients
    // action == 5 --> sender-name doesen't match with ip
    // action == 6 --> sender Client succesfully leaved Server
    // action == 7 --> client ask server onlinePeople
    private int action;
    
    public Packet (String sender, String receiver, ArrayList<Message> data, TreeSet<String> onlinePeople, int action) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
        this.onlinePeople = onlinePeople;
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
    
    public TreeSet<String> getOnlinePeople () {
        return onlinePeople;
    }
}
