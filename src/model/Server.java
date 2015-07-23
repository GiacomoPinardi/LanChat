
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Server extends Thread {
    
    private ServerSocket serverSocket;
    
    // key: clientName ; value: clientIp
    private HashMap<String, String> idip;
    
    // Key: clientName ; value: messages to client
    private HashMap<String, ArrayList<Message>> msgToSend;

    // server cannot run if it is false
    private boolean online;
    
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        
        this.online = true;
        this.idip = new HashMap<>();
        this.msgToSend = new HashMap<>();
    }

    @Override
    public void run() {
        while (this.online) {            
            try {
                Socket socket = serverSocket.accept();
                
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                
                // Packet from sender
                Packet data = (Packet) in.readObject();                              
                
                Packet r = this.packetManager(data, socket.getInetAddress().toString());
                
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
                IOE.printStackTrace();
            }
            catch (ClassNotFoundException ex) {
                System.err.println("ERROR: Class Not Found!");
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
                    return new Packet("SERVER", p.getSender(), null, null, 3);
                }
            case 2:
                // p.getSender() leave conversation
                if (this.idip.remove(p.getSender()) != null) {
                    // successfully
                    return new Packet("SERVER", p.getSender(), null, null, 6);
                }
                else {
                    // error
                    return new Packet("SERVER", p.getSender(), null, null, -1);
                }
            default:                
                if (this.idip.get(p.getSender()).equals(ip)) {
                    // sender-name match with ip
                    if (p.getData() != null) {
                        // p.getSender() send new messages
                        for (Message m : p.getData()) {
                            // new messages are stored in 'msgToSend' and wait 'receiver'                                                           
                            String receiver = m.getReceiver();
                            if (this.idip.containsKey(receiver)) {
                                // receiver exists
                                ArrayList<Message> msgs = this.msgToSend.get(receiver);
                                msgs.add(m);

                                this.msgToSend.put(receiver, msgs);
                            }                                
                            
                        }                                            
                    }
                    // send old messages to p.getSender()
                    ArrayList<Message> oldMessages = this.msgToSend.get(p.getSender());
                    
                    HashSet<String> onlinePeople = null;
                    
                    // client ask for online people
                    if (p.getAction() == 7) {
                        onlinePeople.addAll(this.idip.keySet());
                    }
                    
                    return new Packet("SERVER", p.getSender(), oldMessages, onlinePeople, 0);
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
        HashMap<String, ArrayList<Message>> tmp = this.msgToSend;
        
        for (String name : tmp.keySet()) {
            result.put(name, tmp.get(name).size());
        }
        
        return result;
    }
    
    public String getInfo () {
        return serverSocket.getInetAddress().toString() + ":" + serverSocket.getLocalPort();
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