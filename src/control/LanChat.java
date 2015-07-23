
package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import model.Message;
import model.Packet;
import view.LanChatManager;

public class LanChat {

    public static void main(String[] args) {
        
        LanChatManager LCM = new LanChatManager();
        
        LCM.setVisible(true);
        
        /*
        try {
            Server server = new Server(9888);
            
            server.start();
            
            Client client = new Client("Jack", "127.0.0.1", 9888);
            
            // connecting
            if (client.connect()) {
                System.out.println(client.getClientName() + " succesful connected to " + client.getServerName());
                
                System.out.println("Joining...");                
                       
                int r = client.join();
                
                if (r == 0) {
                    System.out.println("Succesful joined server!");
                    
                    ArrayList<Message> msgs = new ArrayList<>();
                    HashSet<String> receivers = new HashSet<>();
                    
                    receivers.add("Mark");
                    
                    client.connect();
                    
                    msgs.add(new Message(client.getClientName(), "Matt", "Ciao!"));
                    
                    Packet res = client.sendReceive(new Packet(client.getClientName(), "Mark", msgs, null, 0));
                    
                    System.out.println(res.getAction());                    
                }
                else if (r == 1) {
                    System.out.println("Duplicate name!");
                }              
                else {
                    System.out.println("Unknown error!");
                }
            }
            else {
                System.out.println(client.getClientName() + " non connesso!");
            }
        }
        catch (IOException IOE) {
            IOE.printStackTrace();
        }
        */
    }
    
}
