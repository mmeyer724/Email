package com.mike724.email;

import org.bukkit.configuration.file.FileConfiguration;

public class EmailManager {
	
	private ConfigAccessor configA;
	private FileConfiguration config;
	private String root = "emails.";

	public EmailManager(Email plugin) {
		configA = new ConfigAccessor(plugin, "emails.yml");
		config = configA.getConfig();
	}
	
	public void setPlayerEmail(String name, String email) {
		config.set(root+name, email);
		configA.saveConfig();
	}
	
	public String getPlayerEmail(String name) {
		return config.getString(root+name);
	}
	
	public void removePlayerEmail(String name) {
		config.set(root+name, null);
		configA.saveConfig();
	}
	
}
