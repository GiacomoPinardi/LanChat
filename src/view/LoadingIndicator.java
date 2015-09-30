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

package view;

import java.io.IOException;
import static java.lang.ClassLoader.getSystemResource;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadingIndicator extends Thread {
    
    private JLabel jl = new JLabel();
    private boolean stop;
    private ImageIcon icons[];
    
    public LoadingIndicator (JLabel jl) {
        this.jl = jl;
        this.stop = false;
        icons = new ImageIcon[8];
        this.createIcons(icons);
    }
    
    @Override
    public void run () {
        int i = 0;
        while (!stop) {
            jl.setIcon(icons[i]);
            i ++;
            if (i == 8) {
                i = 0;
            }
            try {
                Thread.sleep(150);
            }
            catch (InterruptedException IE) {
                //
            }
        }
        jl.setIcon(null);
    }
    
    public void stopLoading () {
        this.stop = true;
    }
    
    private void createIcons (ImageIcon[] icons) {
        try {
            for (int i = 0; i < icons.length; i++) {                
                // icons are loaded
                icons[i] = new ImageIcon(ImageIO.read(getSystemResource("img/loading" + String.valueOf(i) + ".png")));
            }
        }
        catch (IOException IOE) {
            //
        }
    }
    
}
