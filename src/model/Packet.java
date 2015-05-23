
package model;

import java.util.ArrayList;

public class Packet {
    
    private String sender;
    private String receiver;
    private ArrayList<String> data;
    
    // lastMessageIndex == -1 --> sender Client just connected to Server
    // lastmessageIndex == -2 --> sender Client just disconnected from Server
    private int lastMessageIndex;
    
    public Packet (String sender, String receiver, ArrayList<String> data, int lastMessageIndex) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
        this.lastMessageIndex = lastMessageIndex;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public int getLastMessageIndex() {
        return lastMessageIndex;
    }
}
