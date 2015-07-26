
package model;

import java.util.ArrayList;

public class PacketQueue {
    
    private ArrayList<Packet> al;

    public PacketQueue() {
        this.al = new ArrayList<>();
    }
    
    public void add (Packet p) {
        al.add(p);
    }
    
    public void addAll (PacketQueue pq) {
        for (Packet p : pq.getAll()) {
            al.add(p);
        }
    }
    
    public Packet get () {
        return al.get(0);
    }
    
    public Packet remove () {
        return al.remove(0);
    }
    
    public ArrayList<Packet> getAll () {
        return al;
    }
    
    public int size() {
        return al.size();
    }
    
    public void clear () {
        al.clear();
    }
}
