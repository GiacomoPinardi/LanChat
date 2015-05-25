
package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import model.Packet;

public class Client {
    
    private String clientName;
    private String serverName;
    int port;
    
    private Socket client = null;
    
    public Client (String clientName, String serverName, int port) {
        this.clientName = clientName;
        this.serverName = serverName;
        this.port = port;     
    }
    
    public boolean connect () {
        try {
            this.client = new Socket(this.serverName, this.port);
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    public Packet sendReceive (Packet packet) {        
        try {
            // to server:
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outToServer);

            out.writeObject(packet);

            out.close();
            
            // from server:
            InputStream inFromServer = client.getInputStream();            
            ObjectInputStream in = new ObjectInputStream(inFromServer);
            
            Packet p = (Packet) in.readObject();
            
            in.close();
            
            return p;
            //client.close(); 
        }
        catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
    
    public boolean disconnect () {
        try {
            this.client.close();
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
    
}
