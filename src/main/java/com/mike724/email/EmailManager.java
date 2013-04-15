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

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailManager {

    private ConfigAccessor configA;
    private FileConfiguration config;
    private String root = "emails.";
    private Email plugin;

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailManager(Email plugin) {
        this.plugin = plugin;
        pattern = Pattern.compile(EMAIL_PATTERN);
        configA = new ConfigAccessor(plugin, "emails.yml");
        config = configA.getConfig();
    }

    public boolean setPlayerEmail(String name, String email) {
        this.matcher = this.pattern.matcher(email);
        if (!this.matcher.matches()) {
            return false;
        }
        config.set(root + name, email);
        configA.saveConfig();
        return true;
    }

    public String getPlayerEmail(String name) {
        return config.getString(root + name);
    }

    public String[] getAllPlayerEmails() {
        ConfigurationSection sect = config.getConfigurationSection(root);
        Set<String> keys = sect.getKeys(false);
        String[] emails = new String[keys.size()];
        int id = 0;
        for (String key : keys) {
            emails[id] = sect.getString(key);
            id++;
        }
        return emails;
    }

    public void removePlayerEmail(String name) {
        config.set(root + name, null);
        configA.saveConfig();
    }

    public void export(int type) {
        if (!(type == 1 || type == 2)) {
            return;
        }
        String fName = "export-type1.txt";
        if (type == 2) {
            fName = "export-type2.txt";
        }
        File file = new File(plugin.getDataFolder(), fName);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            Set<String> keys = config.getConfigurationSection("emails").getKeys(false);
            for (String key : keys) {
                String line;
                if (type == 1) {
                    line = key + "," + config.getString(root + key);
                } else {
                    line = config.getString(root + key);
                }
                pw.println(line);
            }
            pw.close();
            plugin.getLogger().info("Export file created at " + file.getPath());
        } catch (Exception e) {
            plugin.getLogger().severe("Could not export emails");
            e.printStackTrace();
            return;
        }
    }

}
