/*
    This file is part of Email.

    Email is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Email is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Email.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.mike724.email;

import java.util.ArrayList;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;
import org.bukkit.ChatColor;

public class EmailTransfer {

    private Email plugin;
    private EmailProvider type;
    private String user;
    private String password;
    LanguajeManager LA;

    public EmailTransfer(Email plugin, EmailProvider type, String user, String password) {
        this.plugin = plugin;
        this.type = type;
        this.user = user;
        this.password = password;
        this.LA=new LanguajeManager(plugin);
    }

    public void send(ArrayList<String> to, String subject, String content) {
        Properties p = new Properties();
        for(Map.Entry<String, String> entry : type.getProps().entrySet()) {
            p.put(entry.getKey(), entry.getValue().replace("$PASS", this.password));
        }
        Session s = Session.getInstance(p, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        Message m = new MimeMessage(s);
        
        for(int counter=0;to.size()>=counter;counter++){
        try {
            m.setFrom(new InternetAddress(this.user));
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to.get(counter)));
            m.setSubject(subject);
            m.setText(content);
            Transport.send(m);
            plugin.getLogger().info(ChatColor.GOLD + LA.search("emailTransfer.emailSent1") +ChatColor.BLUE + to.get(counter));}
         catch (MessagingException ex) {
            ex.printStackTrace();
            plugin.getLogger().info(ChatColor.RED + LA.search("emailTransfer.emailSent2") +ChatColor.GREEN + to.get(counter));
        }
        }
    }
}