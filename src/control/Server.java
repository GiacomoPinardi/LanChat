
package control;

import model.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import model.Message;

public class Server extends Thread {
    
    private ServerSocket serverSocket;
    
    // key: clientName ; value: clientIp
    private HashMap<String, String> idip;
    
    // Key: clientName ; value: message to client
    private HashMap<String, ArrayList<Message>> msgToSend;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        
        this.idip = new HashMap<>();
        this.msgToSend = new HashMap<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                
                ObjectInputStream in = new ObjectInputStream(server.getInputStream());
                
                // Packet from sender
                Packet data = (Packet) in.readObject();                              
                
                Packet r = this.packetManager(data, server.getInetAddress().toString());
                
                // Packet to sender
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                out.writeObject(r);                               
                
                server.close();
                in.close();
                out.close();
            }
            catch (IOException e) {
                System.err.println("ERROR: Input/Output Exception");
                e.printStackTrace();
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
                   return new Packet("SERVER", p.getSender(), null, 4);
                }
                else {
                    // p.getSender() join conversation
                    this.idip.put(p.getSender(), ip);
                    return new Packet("SERVER", p.getSender(), null, 3);
                }
            case 2:
                // p.getSender() leave conversation
                this.idip.remove(p.getSender());
                return null;
            default:                
                if (this.idip.get(p.getSender()).equals(ip)) {
                    // sender-name match with ip
                    if (p.getData() != null) {
                        // p.getSender() send new messages
                        for (Message m : p.getData()) {
                            // new messages are stored in 'msgToSend' and wait 'receiver'
                            for (String receiver : m.getReceivers()) {                                
                                if (this.idip.containsKey(receiver)) {
                                    // receiver exists
                                    ArrayList<Message> msgs = this.msgToSend.get(receiver);
                                    msgs.add(m);

                                    this.msgToSend.put(receiver, msgs);
                                }                                
                            }
                        }                                            
                    }
                    // send old messages to p.getSender()
                    ArrayList<Message> oldMessages = this.msgToSend.get(p.getSender());
                    
                    return new Packet("SERVER", p.getSender(), oldMessages, 0);
                }
                else {
                    // sender-name doesen't match with ip
                    return new Packet("SERVER", p.getSender(), null, 5);
                }
        }
    }
}
