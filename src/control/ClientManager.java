
package control;

import model.Client;
import javax.swing.JOptionPane;
import model.Packet;
import model.PacketQueue;
import view.GraphicInterfaceClient;

public class ClientManager extends Thread {

    private Client client;
    private GraphicInterfaceClient GIC;
    
    private PacketQueue forServer;
    private PacketQueue forClient;
    
    public ClientManager(Client client) {
        this.client = client;
        this.GIC = new GraphicInterfaceClient();
        this.forServer = new PacketQueue();
        this.forClient = new PacketQueue();        
    }
    
    @Override
    public void run () {
        while (GIC.isOnline()) {
            // here client will ask server for packet and with 'updateWindow' the new packet will be sent
            
            // client connect to server
            if (this.client.connect()) {
                GIC.setConsoleText(true, "Client connected");
            }
            else {
                GIC.setConsoleText(false, "Connection error!");
            }
            
            // client send packet to server and store response from server
            Packet response = this.client.sendReceive(this.forServer.get());
            
            this.forClient.add(response);
            
            // client manage packet to/from server
            this.packetManager();
            
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException IE) {
                
            }
        }
        
        // client leave server
        if (!this.client.connect()) {
            JOptionPane.showMessageDialog(GIC, "Cannot leave server!\nServer connection error.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (!this.client.leave()) {            
                JOptionPane.showMessageDialog(GIC, "Cannot leave server!\nPlease retry.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }
    
    public boolean showClientInterface () {
        if (this.client.connect()) {
            int r = this.client.join();
            if (r == 0) {
                // start client view
                GIC.setTitle("LanChat: " + this.client.getClientName() + " - " + this.client.getServerName() + " [" + this.client.getPort() + "]");
                GIC.setVisible(true);
                return true;
            }
            else if (r == 1) {
                JOptionPane.showMessageDialog(GIC, "Duplicate name!\nChange Client name and retry.", "ERROR", JOptionPane.ERROR_MESSAGE);
                GIC.dispose();
            }
            else {
                JOptionPane.showMessageDialog(GIC, "Unknown error!\nPlease retry.", "ERROR", JOptionPane.ERROR_MESSAGE);
                GIC.dispose();
            }
        }
        else {
            JOptionPane.showMessageDialog(GIC, "Server not found or wrong port number!", "ERROR", JOptionPane.ERROR_MESSAGE);
            GIC.dispose();
        }
        return false;
    }
    
    public void packetManager () {
        // send packet to GraphicInterfaceClient
        // get packet from GraphicInterfaceClient
        
        
    }
    
}
