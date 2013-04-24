package com.mike724.email;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class LanguageManager {

    private String lang;
    private HashMap<String, String> langData;

    public LanguageManager(Email plugin) {
        this.lang = plugin.getConfig().getString("language");

        boolean valid = false;
        for (String option : plugin.VALID_LANGS) {
            if (this.lang.equalsIgnoreCase(option)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            this.lang = "English";
        }

        //Load strings into hashmap
        langData = new HashMap<String, String>();
        ConfigAccessor ca = new ConfigAccessor(plugin, "languages", this.lang + ".yml");
        FileConfiguration config = ca.getConfig();
        for (String type : config.getKeys(false)) {
            for (String msg : config.getConfigurationSection(type).getKeys(false)) {
                String path = type + "." + msg;
                langData.put(path, config.getString(path));
            }
        }

    }

    public String getString(String key) {
        String translation = langData.get(key);
        if (translation == null || translation.isEmpty()) {
            translation = key.toUpperCase();
        }
        return translation;
    }

}
