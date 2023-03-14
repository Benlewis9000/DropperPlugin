package io.benlewis.dropin;

import io.benlewis.dropin.configuration.ConfigFactory;
import io.benlewis.dropin.dropper.map.DropperMapLoader;
import io.benlewis.dropin.dropper.map.DropperMapManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class DropIn extends JavaPlugin {

    @Override
    public void onEnable(){
        ConfigFactory configFactory = new ConfigFactory(this);

        DropperMapManager dropperMapManager = new DropperMapManager();
        DropperMapLoader dropperMapLoader = new DropperMapLoader(this.getServer(), dropperMapManager, configFactory,
                this.getLogger());
        dropperMapLoader.loadMapsIntoManager();

        this.getLogger().info("Enabled DropIn");
    }

    @Override
    public void onDisable(){
        this.getLogger().info("Disabled DropIn");
    }


    // Main class must have no arg constructor
    public DropIn() {}

    // Required constructor for MockBukkit
    protected DropIn(JavaPluginLoader loader, PluginDescriptionFile descriptionFile, File dataFolder, File file) {
        super(loader, descriptionFile, dataFolder, file);
    }

}
