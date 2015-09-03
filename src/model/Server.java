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

import control.Worker;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Server extends Thread {
    
    private ServerSocket serverSocket;
    
    // key: clientName ; value: clientIp
    private HashMap<String, String> idip;
    
    // Key: clientName ; value: messages to client
    private ConcurrentHashMap<String, ArrayList<Message>> msgToSend;
    
    // Key: clientName ; value: number of messages sent
    private HashMap<String, Integer> msgSent;

    // server cannot run if it is false
    private boolean online;
    
    private ArrayList<String> peopleToKick;
    
    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.online = true;
        this.idip = new HashMap<>();
        this.msgToSend = new ConcurrentHashMap<>();
        this.msgSent = new HashMap<>();
        this.peopleToKick = new ArrayList<>();
    }

    @Override
    public void run() {
        while (this.online) {            
            try {
                Socket socket = serverSocket.accept();
                
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                
                // Packet from sender
                Packet data = (Packet) in.readObject();
                
                Packet r = this.packetManager(data, socket.getInetAddress().getHostAddress());
                
                // Packet to sender
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(r);
                
                socket.close();
                in.close();
                out.close();
            }
            catch (SocketException e) {
                // server exit
            }
            catch (IOException IOE) {
                System.err.println("ERROR: Input/Output Exception");
            }
            catch (ClassNotFoundException ex) {
                System.err.println("ERROR: Class Not Found Exception");
            }
        }
    }
    
    private Packet packetManager (Packet p, String ip) { 
        // return Packet to p.getReceiver()
        
        switch (p.getAction()) {
            case 1:                
                if (this.idip.containsKey(p.getSender())) {
                   // client with the same name already exist
                   return new Packet("SERVER", p.getSender(), null, null, 4);
                }
                else {
                    // p.getSender() join conversation
                    this.idip.put(p.getSender(), ip);
                    this.msgToSend.put(p.getSender(), new ArrayList<Message>());
                    this.msgSent.put(p.getSender(), 0);
                    return new Packet("SERVER", p.getSender(), null, null, 3);
                }
            case 2:
                // p.getSender() leave conversation
                if (this.idip.remove(p.getSender()) != null) {
                    // successfully
                    msgSent.remove(p.getSender());
                    return new Packet("SERVER", p.getSender(), null, null, 6);
                }
                else {
                    // error
                    return new Packet("SERVER", p.getSender(), null, null, -1);
                }
            default:                
                if (this.idip.containsKey(p.getSender()) && this.idip.get(p.getSender()).equals(ip)) {
                    // sender-name match with ip
                                        
                    if (peopleToKick.contains(p.getSender())) {
                        // kick people in the arrayList
                        this.idip.remove(p.getSender());
                        peopleToKick.remove(p.getSender());
                        return new Packet("SERVER", p.getSender(), null, null, 8);
                    }
                    else {
                        if (p.getData() != null) {
                            // p.getSender() send new messages
                            for (Message m : p.getData()) {
                                // msgSent is increased 
                                int totalMessages = msgSent.get(m.getSender());
                                totalMessages ++;
                                msgSent.put(m.getSender(), totalMessages);

                                // new messages are stored in 'msgToSend' and wait 'receiver'                                                           
                                String receiver = m.getReceiver();

                                if (receiver.equals("ALL")) {
                                    // message for 'ALL' chat, the global chat
                                    // the message will reach everyone
                                    for (String name : msgToSend.keySet()) {
                                        if (!m.getSender().equals(name)) {
                                            ArrayList<Message> tmp = this.msgToSend.get(name);                                
                                            tmp.add(m);

                                            this.msgToSend.put(name, tmp);
                                        }
                                    }
                                }

                                if (this.idip.containsKey(receiver)) {
                                    // receiver exists
                                    ArrayList<Message> msgs = this.msgToSend.get(receiver);                                
                                    msgs.add(m);

                                    this.msgToSend.put(receiver, msgs);
                                }
                            }                                            
                        }
                        // send old messages to p.getSender() removing it and adding a new, empty ArrayList
                        ArrayList<Message> oldMessages = this.msgToSend.remove(p.getSender());

                        this.msgToSend.put(p.getSender(), new ArrayList<Message>());

                        TreeSet<String> onlinePeople = null;

                        // client ask for online people
                        if (p.getAction() == 7) {
                            onlinePeople = new TreeSet<>();
                            onlinePeople.addAll(this.idip.keySet());
                        }

                        return new Packet("SERVER", p.getSender(), oldMessages, onlinePeople, 0);
                    }
                }
                else {
                    // sender-name doesen't match with ip
                    return new Packet("SERVER", p.getSender(), null, null, 5);
                }
        }
    }
    
    public HashMap<String, String> getIdip () {
        return this.idip;
    }
    
    public HashMap<String, Integer> getMsgToSend () {
        // Key: clientName ; value: number of messages
        
        HashMap<String, Integer> result = new HashMap<>();
        ConcurrentHashMap<String, ArrayList<Message>> tmp = this.msgToSend;
        
        for (String name : tmp.keySet()) {
            result.put(name, tmp.get(name).size());
        }
        
        return result;
    }
    
    public HashMap<String, Integer> getMsgSent () {
        return this.msgSent;
    }
    
    public void setPeopleToKick (ArrayList<String> kp) {
        this.peopleToKick = kp;
    }
    
    public String getInfo () {        
        return Worker.getLocalIp() + ":" + this.serverSocket.getLocalPort();
    }
    
    public void setOffline () {
        try {
            this.online = false;
            this.serverSocket.close();
        }
        catch (IOException IOE) {
            //
        }
    }
}
