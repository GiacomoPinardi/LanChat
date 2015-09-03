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

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Conversation implements Serializable {
    
    private ArrayList<Message> messages;
    private String id;
    private JTextArea textArea;

    public Conversation(String id) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setRows(22);
        textArea.invalidate();
    }
    
    public void addMsgs (ArrayList<Message> ms) {
        for (Message m : ms) {
            textArea.append(m.getSender() + ": " + m.getInformation() + "\n");
        }
        this.messages.addAll(ms);
    }
    
    public void addMsg (Message m) {
        textArea.append(m.getSender() + ": " + m.getInformation() + "\n");
        this.messages.add(m);
    }
    
    public void addMsgsOnTop (ArrayList<Message> ms) {        
        String total = "";
        for (Message m : ms) {
            total = total.concat(m.getSender() + ": " + m.getInformation() + "\n");
        }
        
        textArea.setText(total + textArea.getText());
        this.messages.addAll(0, ms);
    }
    
    public Message getMsg (int index) {
        return this.messages.get(index);
    }
    
    public ArrayList<Message> getAllMsgs () {
        return this.messages;
    }
    
    public void clearAllMsgs () {
        this.messages.clear();
    }
        
    public String getId () {
        return this.id;
    }
    
    public JTextArea getTextArea () {
        return this.textArea;
    }
    
    public int size () {
        return messages.size();
    }
    
}
