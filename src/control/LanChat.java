
package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import model.Message;
import model.Packet;

public class LanChat {

    public static void main(String[] args) {
        
        try {
            Server server = new Server(9888);
            
            server.start();
            
            Client client = new Client("Jack", "127.0.0.1", 9888);
            
            // connecting
            if (client.connect()) {
                System.out.println(client.getClientName() + " succesful connected to " + client.getServerName());
                
                System.out.println("Joining...");
                Packet response = client.sendReceive(new Packet(client.getClientName(), null, null, 1));
                if (response.getAction() == 3) {
                    System.out.println("Succesful joined server!");
                    
                    ArrayList<Message> msgs = new ArrayList<>();
                    HashSet<String> receivers = new HashSet<>();
                    
                    receivers.add("Mark");
                    
                    client.connect();
                    
                    msgs.add(new Message(client.getClientName(), receivers, "Ciao!"));
                    
                    Packet r = client.sendReceive(new Packet(client.getClientName(), "Mark", msgs, 0));
                    
                    System.out.println(r.getAction());                    
                }
                else if (response.getAction() == 4) {
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
        
        
            /*
            Scanner lettore = new Scanner(System.in);
            
            String r = lettore.nextLine();
            
            if (r.equals("a")) {
            try {
            System.out.println("Port: ");
            
            int port = lettore.nextInt();
            Server server = new Server(port);
            server.start();
            
            System.out.println("Server created!");
            }
            catch (IOException IOE) {
            System.err.println("Input/Output Exception");
            }
            }
            
            
            System.out.print("Client name: ");
            String clientName = lettore.next();
            
            String serverName = "127.0.0.1";
            
            if (r.equals("b")) {
            System.out.println("Server ip:");
            serverName = lettore.nextLine();
            }
            
            System.out.print("Port: ");
            int port = lettore.nextInt();
            
            Client client = new Client(clientName, serverName, port);
            if(client.connect()) {
            System.out.println("Succesful connected!");
            }
            else {
            System.err.println("ERROR: Client not connected!");
            }
            
            boolean exit = false;
            
            System.out.println("Joining...");
            Packet join = new Packet(clientName, "", null, -1);
            client.sendReceive(join);
            
            while (!exit) {
            System.out.print("Type your message: [q == quit]");
            
            String message = lettore.next();
               
            if (message.equals("q")) {
            System.out.println("Leaving...");
            Packet leave = new Packet(clientName, "", null, -2);
            client.sendReceive(leave);
            
            if (client.disconnect()) {
            System.out.println("Succesfully disconnected!");
            }
            else {
            System.err.println("ERROR: Client not disconnected!");
            }
            exit = true;
            }
            else {
            ArrayList<String> m = new ArrayList<>();
            m.add(message);
            
            Packet send = new Packet(client.getClientName(), client.getServerName(), m, messages.size());
            
            Packet receive =  client.sendReceive(send);
            
            for (String msg : receive.getData()) {
            System.out.println(msg);
            }
            }
            }
            */
    }
    
}
