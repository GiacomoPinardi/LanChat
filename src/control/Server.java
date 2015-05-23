
package control;

import model.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                
                ObjectInputStream in = new ObjectInputStream(server.getInputStream());
                
                Packet data = (Packet) in.readObject();
                
                
                
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                out.writeObject(/);
                
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
}
