
package control;

import java.util.HashMap;
import java.util.TreeMap;
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
                TreeMap<String, String[]> tm = new TreeMap<>();

                HashMap<String, String> hm1 = server.getIdip();
                HashMap<String, Integer> hm2 = server.getMsgToSend();

                for (String name : hm1.keySet()) {
                    String[] data = new String[2];
                    data[0] = hm1.get(name);
                    data[1] = String.valueOf(hm2.get(name));

                    tm.put(name, data);
                }

                GIS.updateWindow(tm);
            }
            
            // wait an amount of time
            try {
                Thread.sleep(2000);
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
