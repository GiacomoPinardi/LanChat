/*
 *
 *  LanChat - Chat through your Local Area Network
 *
 *  Copyright (C) 2015  Giacomo Pinardi
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
            
            // kick people in list
            server.setPeopleToKick(GIS.getKickedPeople());
            
            if (GIS.canUpdate()) {
                // server manager can update the window                
                // contains all name sorted
                SortedSet<String> sorted = new TreeSet<>();
                
                HashMap<String, String> hmIp = server.getIdip();
                HashMap<String, Integer> hmMsg = server.getMsgToSend();
                HashMap<String, Integer> hmSent = server.getMsgSent();
                    
                for (String name : hmIp.keySet()) {
                    sorted.add(name);
                }
                
                // [x][0]: name ; [x][1]: ip ; [x][2]: msg to send/sent
                String[][] data = new String[sorted.size()][3];
                
                int i = 0;
                for (String name : sorted) {
                    data[i][0] = name;
                    data[i][1] = hmIp.get(name);
                    data[i][2] = String.valueOf(hmMsg.get(name)) + "/" + String.valueOf(hmSent.get(name));
                    i++;
                }
                
                GIS.updateWindow(data);
            }
            
            // wait an amount of time
            try {
                Thread.sleep(200);
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
        
        GIS.setTitle("LanChat server: " + this.server.getInfo());
    }
    
}
