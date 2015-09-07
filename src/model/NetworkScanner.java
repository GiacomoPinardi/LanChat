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
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import view.LoadingIndicator;

public class NetworkScanner extends Thread {
    
    private JRootPane rootPane;
    private JTextField jtx1;
    private JTextField jtx2;
    private JLabel jl;
    
    public NetworkScanner (JRootPane rootPane, JTextField jtx1, JTextField jtx2, JLabel jl) {
        this.rootPane = rootPane;
        this.jtx1 = jtx1;
        this.jtx2 = jtx2;
        this.jl = jl;
    }
    
    @Override
    public void run () {
        // loading action showed in a label
        LoadingIndicator li = new LoadingIndicator(jl);        
        li.start();
        
        try {
            // new multicast socket, 5 sec timeout
            MulticastSocket socket = new MulticastSocket(9117);
            socket.setSoTimeout(5000);
            // address for broadcasting
            InetAddress address = InetAddress.getByName("230.0.0.4");
            
            socket.joinGroup(address);
            
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            // socket received data are stored in 'packet'
            socket.receive(packet);
            
            // packet data are stored in 'received'
            String received = new String(packet.getData(), 0, packet.getLength());            
            // if 'received' equals with server's message
            String data[] = received.split("_");
            if (data[0].equals("Hello Client!")) {                
                jtx1.setText(packet.getAddress().toString().replace("/", ""));
                jtx2.setText(data[1]);
            }

            socket.leaveGroup(address);
            socket.close();            
        }
        catch (BindException BE) {
            int r = JOptionPane.showConfirmDialog(rootPane, "Are the server running on this machine?", "Server online?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (r == 0) {
                jtx1.setText("127.0.0.1");
            }
        }
        catch (SocketTimeoutException STE) {
            JOptionPane.showMessageDialog(rootPane, "No servers found.", "Nothing found", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException SE) {
            JOptionPane.showMessageDialog(rootPane, "Cannot scan network.\nPlease retry.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        li.stopLoading();
    }
    
}
