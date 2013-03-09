package com.mike724.email;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;

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
		if(!this.matcher.matches()) {
			return false;
		}
		config.set(root+name, email);
		configA.saveConfig();
		return true;
	}
	
	public String getPlayerEmail(String name) {
		return config.getString(root+name);
	}
	
	public void removePlayerEmail(String name) {
		config.set(root+name, null);
		configA.saveConfig();
	}
	
	public void export(int type) {
		if(!(type == 1 || type == 2)) {
			return;
		}
		String fName = "export-type1.txt";
		if(type == 2) {
			fName = "export-type2.txt";
		}
		File file = new File(plugin.getDataFolder(), fName);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			Set<String> keys = config.getConfigurationSection("emails").getKeys(false);
			for(String key : keys) {
				String line = "";
				if(type == 1) {
					line = key+","+config.getString(root+key);
				} else if(type == 2) {
					line = config.getString(root+key);
				}
				pw.println(line);
			}
			pw.close();
			plugin.getLogger().info("Export file created at "+file.getPath());
		} catch (Exception e) {
			plugin.getLogger().severe("Could not export emails");
			e.printStackTrace();
			return;
		}
	}
	
}
