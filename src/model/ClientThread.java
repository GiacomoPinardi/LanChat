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

package model;

import control.ClientManager;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import view.LoadingIndicator;

public class ClientThread extends Thread {
    
    private JLabel jl;
    private String name;
    private String ip;
    private int port;
    private JRootPane rootPane;
    
    public ClientThread (JLabel jl, String name, String ip, int port, JRootPane rootPane) {
        this.jl = jl;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.rootPane = rootPane;
    }
    
    @Override
    public void run () {
        // loading action showed in a label
        LoadingIndicator li = new LoadingIndicator(jl);
        li.start();

        // all fields are correct, new client is created
        Client c = new Client(name, ip, port);                        

        // ClientManager manage client and send/ask server new messages
        ClientManager CM = new ClientManager(c, rootPane);

        // CM try to start client, if client work correctly CM run a thread that periodically check server
        if (CM.showClientInterface()) {
            CM.start();
        }
        li.stopLoading();
    }
    
}
