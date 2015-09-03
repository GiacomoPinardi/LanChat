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

import java.io.Serializable;

public class Message implements Serializable {
    
    private String information;
    private String sender;
    private String receiver;

    public Message(String sender, String receiver, String information) {        
        this.sender = sender;
        this.receiver = receiver;
        this.information = information;
    }    

    public String getSender() {
        return this.sender;
    }
    
    public String getReceiver () {
        return this.receiver;
    }
    
    public String getInformation() {
        return this.information;
    }
}
