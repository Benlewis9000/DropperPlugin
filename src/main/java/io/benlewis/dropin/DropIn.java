package io.benlewis.dropin;

import org.bukkit.plugin.java.JavaPlugin;

public class DropIn extends JavaPlugin {

    @Override
    public void onEnable(){
        this.getLogger().info("Enabled DropIn");
    }

    @Override
    public void onDisable(){
        this.getLogger().info("Disabled DropIn");
    }

}
