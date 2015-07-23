
package control;

import java.util.HashSet;
import java.util.Random;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

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
        
        int lenght = r.nextInt(4) + 4;
        
        for (int i = 0; i < lenght; i++) {
            name += (char) (r.nextInt(26) + 'a');
        }
        
        return name;
    }
    
    public static Vector SetToVector (HashSet<String> hs) {
        Vector<String> v = new Vector<>();
        for (String s : hs) {
            v.add(s);
        }
        return v;
    }
        
}
