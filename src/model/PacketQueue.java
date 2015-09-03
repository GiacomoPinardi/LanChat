/*
 *
 *  LanChat - Chat through your Local Area Network
 *
 *  Copyright (C) 2015  Giacomo Pinardi
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
