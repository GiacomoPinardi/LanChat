
package control;

import model.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends Thread {
    
    private ServerSocket serverSocket;
    HashMap<String, String> idip;
    ArrayList<String> messages;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        
        this.idip = new HashMap<>();
        this.messages = new ArrayList<>();
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
                
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                out.writeObject(r);
                
                server.close();
            }
            catch (IOException e) {
                System.err.println("ERROR: Input/Output Exception");
            }
            catch (ClassNotFoundException ex) {
                System.err.println("ERROR: Class Not Found!");
            }
        }
    }
    
    private Packet packetManager (Packet p, String ip) { 
        // return Packet to p.getReceiver()        
        
        switch (p.getLastMessageIndex()) {
            case -1:
                // p.getSender() join conversation
                this.idip.put(p.getSender(), ip);
                return null;
            case -2:
                // p.getSender() leave conversation
                this.idip.remove(p.getSender());
                return null;
            default:
                if (p.getData() != null) {
                    // p.getSender() send new messages
                    this.messages.addAll(p.getData());
                }
                
                ArrayList<String> oldMessages = new ArrayList<>();                
                for (int i = p.getLastMessageIndex()+1; i < this.messages.size() - p.getData().size(); i++) {
                    oldMessages.add(this.messages.get(i));
                }
                return new Packet(p.getSender(), p.getReceiver(), oldMessages, 0);
        }
    }
}
