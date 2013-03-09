package com.mike724.email;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

public class Email extends JavaPlugin {

	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
		//Enable plugin metrics
		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getLogger().info("Enabled successfully");
	}
}
