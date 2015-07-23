
package model;

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
    private int port;
    
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
    
    public int join () {
        Packet response = this.sendReceive(new Packet(this.clientName, "SERVER", null, null, 1));
        
        if (response.getAction() == 3) {
            // succesful
            return 0;
        }
        else if (response.getAction() == 4) {
            // duplicate name
            return 1;
        }
        else {
            // unknown error
            return 2;
        }
    }
    
    public Packet sendReceive (Packet packet) {        
        try {
            // to server:
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outToServer);

            out.writeObject(packet);
            
            // from server:
            InputStream inFromServer = client.getInputStream();            
            ObjectInputStream in = new ObjectInputStream(inFromServer);
            
            Packet p = (Packet) in.readObject();
                        
            return p;
        }
        catch (IOException | ClassNotFoundException ex) {
            //ex.printStackTrace();
            return null;
        }
    }
    
    public boolean leave () {
        Packet response = this.sendReceive(new Packet(this.clientName, "SERVER", null, null, 2));
        
        return response.getAction() == 6;
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

    public String getClientName() {
        return clientName;
    }

    public String getServerName() {
        return serverName;
    }

    public int getPort() {
        return port;
    }
    
}
