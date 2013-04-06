
package com.mike724.email;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author _TurboCraft_
 * 
 * This can be use to set the languaje for the plugin, we will add:
 * -Spanish
 * -English
 * -French
 * 
 * This will be with yml, we gonna got the [languaje] in the config file
 * and then we're gonna use it .
 */


public class LanguajeManager {
    
    private String Languaje;
    private JavaPlugin plugin;

    public LanguajeManager(JavaPlugin plugin) {
        this.plugin=plugin;
        
        FileConfiguration fileConfiguration_Languaje = YamlConfiguration.loadConfiguration(new File("Email/config.yml"));
        
        String out_Languaje=fileConfiguration_Languaje.getString("email.Languaje");
        //If they put another Languaje like "asdasda" , it'll be English by default. :D
        if(out_Languaje.equalsIgnoreCase("Spanish") || out_Languaje.equalsIgnoreCase("English") || out_Languaje.equalsIgnoreCase("Spanish")){
        this.Languaje = out_Languaje;
        }else this.Languaje="English";
        
        
        
    }
    
    
    public String search(String which){
   
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(new File(getClass().getResource("Languajes/"+Languaje+".yml").getFile()));
        
        return fileConfiguration.getString(which);
         
     
    }
    

}
