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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
import model.Conversation;

public class Worker {
    
    public static boolean ipChecker (String ip) {
        // ip number syntax checking
        try {
            String parts[] = ip.split("\\.");
            
            if (parts.length == 4) {
                for (int i = 0; i < 4; i++) {
                    int x = Integer.valueOf(parts[i]);
                    
                    if (x < 0 || x > 255) {
                        return false;
                    }
                }
                return true;
            }            
        }
        catch (PatternSyntaxException | NumberFormatException  e) {
            //
        }
        return false;
    }
    
    public static int checkPortNumber (String port) {
        // port number syntax checking
        try {
            int p = Integer.valueOf(port);
            if (p >= 0) {
                return p;
            }
        }
        catch (NumberFormatException NFE) {
            //
        }
        return -1;
    }
    
    public static int checkClientName (String name) {
        if (!name.contains(" ")) {
            if (name.length() <= 14) {
                if (!name.equals("ALL")) {
                    if (name.matches("[a-zA-Z]+")) {
                        if (name.equals("")) {
                            return 5;
                        }
                        return 0;
                    }
                    else {
                        return 4;
                    }
                }
                else {
                    return 3;
                }
            }
            else {
                return 2;
            }
        }
        else {
            return 1;
        }
    }
    
    public static String randomName () {
        // generate new random name
        Random r = new Random();
        
        String name = "";
        
        int lenght = r.nextInt(11) + 4;
        
        for (int i = 0; i < lenght; i++) {
            name += (char) (r.nextInt(26) + 'a');
        }
        
        return name;
    }
    
    public static Vector SetToVector (Set<String> hs) {
        Vector<String> v = new Vector<>();
        for (String s : hs) {
            v.add(s);
        }
        return v;
    }
    
    public static boolean serializeConversations (ArrayList<Conversation> al, File f) {
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fOut);
            oos.writeObject(al);
            oos.close();
            fOut.close();            
            return true;
        }
        catch(IOException IOE) {
            return false;
        }
    }
    
    public static ArrayList<Conversation> deserializeConversations (File f) {
        ArrayList<Conversation> al = null;
        
        try {
            FileInputStream fIn = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fIn);
            al = (ArrayList<Conversation>) in.readObject();
            in.close();
            fIn.close();
        }
        catch(IOException | ClassNotFoundException i) {
            //
        }

        return al;
    }  
    
    public static String getLocalIp () {
        // store the local IPv4 (work also without internet)
        String ip = "x.x.x.x";
        
        try {
            // get all interface from pc network
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                // current element is the next interface
                NetworkInterface currentInterface = interfaces.nextElement();
                if (!currentInterface.isUp() || currentInterface.isLoopback() || currentInterface.isVirtual()) {
                    // skip if the interface isn't 'good'
                    continue;
                }
                // get all addresses from the current interface
                Enumeration<InetAddress> addresses = currentInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress currentAddress = addresses.nextElement();
                    if (currentAddress.isLoopbackAddress()) {
                        // skip
                        continue;
                    }
                    if (currentAddress instanceof Inet6Address) {
                        // skip IPv6
                        continue;
                    }
                    if (currentAddress instanceof Inet4Address)
                        // IPv4 is stored
                        ip = currentAddress.getHostAddress();
                }
            }
        }
        catch (IOException IOE) {
            //
        }
        
        return ip;
    }
}
