
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
        this.GIC = new GraphicInterfaceClient(this.client.getClientName());
        this.forServer = new PacketQueue();
        this.forClient = new PacketQueue();        
    }
    
    @Override
    public void run () {
        int delay = 200;
        int counter = 0;
        
        while (GIC.isOnline()) {        
            // here client will ask server for packet and with 'updateWindow' the new packet will be sent
                            
            // client connect to server
            // REMINDER: if no packets aren't send client MUST disconnect: "client.disconnect();"
            if (this.client.connect()) {
                GIC.setConsoleText(true, "Client connected");                        
                
                // packet for server
                Packet toSend;
                
                // if there is at least one packet to send
                if (forServer.size() != 0) {
                    // first optimization
                    //this.forServer = Worker.packetOptimizer(forServer, client.getClientName());

                    toSend = this.forServer.remove();
                    
                    // every 5 normal packet, 1 is for online people
                    if (counter%5 == 0) {
                        if (toSend.getAction() == 0) {
                            toSend.setAction(7);
                        }
                        else {
                            counter --;
                        }
                    }
                }
                else {
                    // no packet for server, client will ask for online people
                    toSend = new Packet(client.getClientName(), "SERVER", null, null, 7);
                }
                
                // client send packet to server and store response from server
                Packet response = this.client.sendReceive(toSend);                
                
                if (response != null) {
                    this.forClient.add(response);
                    
                    // client manage packet to/from server
                    this.packetManager();
                }
                
                counter ++;
            }
            else {
                GIC.setConsoleText(false, "Connection error!");
            }
            
            try {
                Thread.sleep(delay);
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
                GIC.setTitle("LanChat: " + this.client.getClientName() + " - " + this.client.getServerName() + ":" + this.client.getPort());
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
    
    private void packetManager () {        
        // second optimization
        //this.forClient = Worker.packetOptimizer(forClient, client.getClientName());        
        
        // send packet to GraphicInterfaceClient        
        GIC.updateWindow(forClient);        
        
        forClient.clear();
        
        // get packet from GraphicInterfaceClient
        forServer.addAll(GIC.getPacketForServer());
    }
    
}
