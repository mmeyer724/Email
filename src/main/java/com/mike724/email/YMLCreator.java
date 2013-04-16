
package com.mike724.email;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class YMLCreator {
    private String where;
    private Plugin p;
    byte[] buffer = new byte[1024];
    InputStream old;
    FileOutputStream new_one;

    public YMLCreator(Plugin p) {
        this.p = p;
        this.where = this.p.getDataFolder() + "/Languajes/";
    }

    public void createLanguajes(String Languaje) {

        try {
            this.old = getClass().getResourceAsStream("/" + Languaje + ".yml");


            this.new_one = new FileOutputStream(new File(this.where + Languaje + ".yml"));

            int largo;
            while ((largo = old.read(buffer)) > 0) {
                new_one.write(buffer, 0, largo);
            }
            old.close();
            new_one.close();
        } catch (Exception ex) {
            Logger.getLogger(YMLCreator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
