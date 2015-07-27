
package control;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import model.Server;
import view.GraphicInterfaceServer;

public class ServerManager extends Thread {
    
    private Server server;
    private GraphicInterfaceServer GIS;
    
    public ServerManager (Server server) {
        this.server = server;
        this.GIS = new GraphicInterfaceServer();
    }
    
    @Override
    public void run () {
        while (GIS.isOnline()) {
            // update the GIS with new stats and info from server
                 
            if (GIS.canUpdate()) {
                // server manager can update the window                
                // contains all name sorted
                SortedSet<String> sorted = new TreeSet<>();
                
                HashMap<String, String> hmIp = server.getIdip();
                HashMap<String, Integer> hmMsg = server.getMsgToSend();

                for (String name : hmIp.keySet()) {
                    sorted.add(name);
                }
                
                // [x][0]: name ; [x][1]: ip ; [x][2]: msg to send
                String[][] data = new String[sorted.size()][3];
                
                int i = 0;
                for (String name : sorted) {
                    data[i][0] = name;
                    data[i][1] = hmIp.get(name);
                    data[i][2] = String.valueOf(hmMsg.get(name));
                    i++;
                }
                
                GIS.updateWindow(data);
            }
            
            // wait an amount of time
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException IE) {
                System.err.println("Interrupted Exception : " + IE.getLocalizedMessage());
            }
        }
        
        server.setOffline();
    }
    
    public void showServerInterface() {
        GIS.setVisible(true);
        
        this.server.start();
        
        GIS.setTitle("Server on " + this.server.getInfo());
    }
    
}
