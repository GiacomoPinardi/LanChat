
package model;

import java.util.ArrayList;

public class PacketQueue {
    
    ArrayList<Packet> al;

    public PacketQueue() {
        this.al = new ArrayList<>();
    }
    
    public void add (Packet p) {
        al.add(p);
    }
    
    public Packet get () {
        return al.get(0);
    }
    
    public void clear () {
        al.clear();
    }
}
