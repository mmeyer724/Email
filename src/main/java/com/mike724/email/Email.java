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
