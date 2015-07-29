
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
import model.Conversation;
import model.Message;
import model.Packet;
import model.PacketQueue;

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
    
    public static PacketQueue packetOptimizer (PacketQueue pq, String clientName) {
        // optimize the packets making them easier to send
        // every message is put in a hashmap containing sender and messages
        
        HashMap<String, ArrayList<Message>> container = new HashMap<>();
        TreeSet<String> onlinePeople = new TreeSet<>();
        
        PacketQueue result = new PacketQueue();
        
        for (Packet p : pq.getAll()) {
            if (p.getOnlinePeople() != null) {
                onlinePeople.addAll(p.getOnlinePeople());
            }            
            
            // if the packet is normal
            if (p.getAction() == 0 && p.getData() != null) {
                for (Message m : p.getData()) {
                    ArrayList<Message> tmp;
                    
                    if (container.containsKey(m.getSender())) {
                        tmp = container.get(m.getSender());
                    }
                    else {
                        tmp = new ArrayList<>();
                    }
                    tmp.add(m);
                    container.put(m.getSender(), tmp);
                }
            }
            else {
                result.add(p);
            }
        }
        
        boolean first = true;
        
        // for every chat is created a new packet
        for (String name : container.keySet()) {
            if (!first) {
                onlinePeople = null;
            }
            else {
                first = false;
            }
            // only in the first packet will be send the online people
            Packet tmp = new Packet(name, clientName, container.get(name), onlinePeople, 0);
            
            result.add(tmp);
        }
        
        return result;
    }
        
}
