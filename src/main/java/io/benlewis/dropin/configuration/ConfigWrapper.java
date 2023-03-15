package io.benlewis.dropin.configuration;


import io.benlewis.dropin.DropIn;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigWrapper {

    private final DropIn plugin;
    private final String filePath;
    private FileConfiguration config;

    public ConfigWrapper(DropIn plugin, String filePath) {
        this.plugin = plugin;
        this.filePath = filePath;
        config = loadFromFile();
    }

    public String getFilePath() {
        return filePath;
    }

    public FileConfiguration getFileConfiguration() {
        return config;
    }

    public FileConfiguration loadFromFile() {
        File file = new File(plugin.getDataFolder(), getFilePath());
        if (!file.exists()) {
            saveTemplateToFile(file);
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        }
        catch (InvalidConfigurationException e){
            plugin.getLogger().warning(("""
                    Invalid configuration for file "%s":
                    %s
                    Using defaults.""").formatted(getFilePath(), e.getMessage()));
        }
        catch (IOException e){
            plugin.getLogger().warning(("""
                    Unable to read configuration file "%s":
                    %s
                    Using defaults.""").formatted(getFilePath(), e.getMessage()));
        }
        return config;
    }

    private void saveTemplateToFile(File file) {
        file.getParentFile().mkdirs();
        try {
            plugin.saveResource(getFilePath(), false);
        } catch(IllegalArgumentException e){
            plugin.getLogger().warning("No template file was found for \"%s\"".formatted(getFilePath()));
        }
    }

    public void saveToFile() {
        try {
            config.save(getFilePath());
            plugin.getLogger().info("Saved configuration \"%s\"".formatted(getFilePath()));
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save configuration to file \"%s\"".formatted(getFilePath()));
            e.printStackTrace();
        }
    }
}
