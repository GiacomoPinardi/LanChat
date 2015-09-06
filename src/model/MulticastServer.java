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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MulticastServer extends Thread {
    
    boolean stop;
    private int delay;
    private int port;

    public MulticastServer() {
        this.stop = false;
        this.delay = 1500;
        this.port = 9117;
    }
    
    @Override
    public void run() { 
        try {
            DatagramSocket socket = new DatagramSocket(port);

            while (!stop) {
                try {                
                    String dString = "Hello Client!";                
                    byte[] buf = dString.getBytes();

                    // send
                    InetAddress group = InetAddress.getByName("230.0.0.4");
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
                    socket.send(packet);

                    // sleep for a while                    
                    Thread.sleep((long)(Math.random() * delay));
                }
                catch (IOException | InterruptedException IOE) {
                    //
                }
            }
            socket.close();
        }
        catch (SocketException SE) {
            //
        }        
    }
    
    public void setOffline() {
        this.stop = true;
        
    }
    
}
