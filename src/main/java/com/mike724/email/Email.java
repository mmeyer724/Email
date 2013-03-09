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

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

public class Email extends JavaPlugin {
	
	public EmailManager emails;

	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
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
