
package com.mike724.email;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author _TurboCraft_
 *         <p/>
 *         This can be use to set the languaje for the plugin, we will add:
 *         -Spanish
 *         -English
 *         -French
 *         <p/>
 *         This will be with yml, we gonna got the [languaje] in the config file
 *         and then we're gonna use it .
 */


public class LanguajeManager {

    private String Languaje;
    private JavaPlugin plugin;


    public LanguajeManager(JavaPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration fileConfiguration_Languaje = plugin.getConfig();

        String out_Languaje = fileConfiguration_Languaje.getString("email.languaje");
        this.Languaje = out_Languaje;
        plugin.getLogger().info("\u001B[33m" + "Languaje: " + Languaje + "\u001B[0m");
        //If they put another Languaje like "asdasda" , it'll be English by default. :D
        if (out_Languaje.equalsIgnoreCase("Spanish") || out_Languaje.equalsIgnoreCase("English") || out_Languaje.equalsIgnoreCase("French")) {
            this.Languaje = out_Languaje;
        } else this.Languaje = "English";


    }


    public String search(String which) {

        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "/Languajes/" + this.Languaje + ".yml"));
        String out = fileConfiguration.getString(which);

        return out;


    }


}
