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
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class NetworkScanner extends Thread {
    
    JRootPane rootPane = new JRootPane();
    JTextField jtx = new JTextField();
    
    public NetworkScanner (JRootPane rootPane, JTextField jtx) {
        this.rootPane = rootPane;
        this.jtx = jtx;
    }
    
    @Override
    public void run () {
        try {
            MulticastSocket socket = new MulticastSocket(9117);
            socket.setSoTimeout(5000);
            InetAddress address = InetAddress.getByName("230.0.0.4");
            
            socket.joinGroup(address);
            
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            
            String received = new String(packet.getData(), 0, packet.getLength());            
            if (received.equals("Hello Client!")) {                
                jtx.setText(packet.getAddress().toString().replace("/", ""));
            }

            socket.leaveGroup(address);
            socket.close();            
        }
        catch (BindException BE) {
            int r = JOptionPane.showConfirmDialog(rootPane, "Are the server running on this machine?", "Server online?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (r == 0) {
                //jTextField2.setText("127.0.0.1");
            }
        }
        catch (SocketTimeoutException STE) {
            JOptionPane.showMessageDialog(rootPane, "No servers found.", "Nothing found", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException SE) {
            JOptionPane.showMessageDialog(rootPane, "Cannot scan netword.\nPlease retry.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
