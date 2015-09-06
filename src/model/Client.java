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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

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
            // timeout should be increased for slow connection
            int timeout = 15000;
            client = new Socket();            
            client.connect(new InetSocketAddress(this.serverName, port), timeout);
            //this.client = new Socket(this.serverName, this.port);
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
