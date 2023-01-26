package io.benlewis.dropin.testutils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationUtils {

    public static ConfigurationSection loadConfigFromString(String data) throws InvalidConfigurationException {
        YamlConfiguration config = new YamlConfiguration();
        config.loadFromString(data);
        return config;
    }

}
