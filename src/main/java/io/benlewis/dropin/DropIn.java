package io.benlewis.dropin;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class DropIn extends JavaPlugin {

    // Main class must have no arg constructor
    public DropIn() {}

    // Required constructor for MockBukkit
    protected DropIn(JavaPluginLoader loader, PluginDescriptionFile descriptionFile, File dataFolder, File file) {
        super(loader, descriptionFile, dataFolder, file);
    }

    @Override
    public void onEnable(){
        this.getLogger().info("Enabled DropIn");
    }

    @Override
    public void onDisable(){
        this.getLogger().info("Disabled DropIn");
    }

}
