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

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

import java.io.IOException;
import java.util.logging.Logger;

public class Email extends JavaPlugin {

    public EmailManager emails;
    public EmailTransfer mailman;

    @Override
    public void onDisable() {
        this.getLogger().info("Disabled successfully");
    }

    @Override
    public void onEnable() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        this.saveConfig();

        Logger log = this.getLogger();

        boolean enableEmailSending = config.getBoolean("email.enable");
        if (enableEmailSending) {
            String typeString = config.getString("email.type");
            EmailProvider type = EmailProvider.valueOf(typeString.toUpperCase());
            if (type == null) {
                log.severe("Unknown email provider! (We are working on adding more)");
                this.getServer().getPluginManager().disablePlugin(this);
                return;
            }
            String user = config.getString("email.user");
            String pass = config.getString("email.password");
            if (user == null || pass == null) {
                log.severe("Issue with email configuration section, please fill out everything.");
                this.getServer().getPluginManager().disablePlugin(this);
                return;
            }
            mailman = new EmailTransfer(this, type, user, pass);
        } else {
            mailman = null;
        }

        emails = new EmailManager(this);

        //Enable plugin metrics
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getCommand("email").setExecutor(new EmailCommands(this));
        this.getLogger().info("Enabled successfully");
    }
}
