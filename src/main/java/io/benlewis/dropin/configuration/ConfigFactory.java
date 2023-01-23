package io.benlewis.dropin.configuration;

import io.benlewis.dropin.DropIn;

import java.util.HashMap;

public class ConfigFactory {

    private final DropIn plugin;
    private final HashMap<ConfigType, ConfigWrapper> configurations;

    public ConfigFactory(DropIn plugin) {
        this.plugin = plugin;
        configurations = new HashMap<>();
    }

    public ConfigWrapper getInstance(ConfigType type){
        if (configurations.containsKey(type)) {
            return configurations.get(type);
        }
        ConfigWrapper wrapper = new ConfigWrapper(plugin, type.getFileName());
        configurations.put(type, wrapper);
        return wrapper;
    }

}
