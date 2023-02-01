package io.benlewis.dropin.dropper.map;

import io.benlewis.dropin.configuration.ConfigFactory;
import io.benlewis.dropin.configuration.ConfigType;
import io.benlewis.dropin.configuration.ConfigWrapper;
import org.bukkit.configuration.ConfigurationSection;

import java.util.logging.Logger;

// TODO needs tests!
public class DropperMapLoader {

    private final DropperMapManager dropperMapManager;
    private final DropperMapFactory dropperMapFactory;
    private final ConfigFactory configFactory;
    private final Logger logger;

    public DropperMapLoader(DropperMapManager dropperMapManager, DropperMapFactory dropperMapFactory,
                            ConfigFactory configFactory, Logger logger){
        this.dropperMapManager = dropperMapManager;
        this.dropperMapFactory = dropperMapFactory;
        this.configFactory = configFactory;
        this.logger = logger;
    }

    public void loadMapsIntoManager(){
        ConfigWrapper mapsConfig = configFactory.getInstance(ConfigType.MAPS);
        ConfigurationSection mapsConfigSection = mapsConfig.getFileConfiguration().getConfigurationSection("maps");
        if (mapsConfigSection == null){
            logger.warning("No dropper maps have been found in %s".formatted(ConfigType.MAPS.getFileName()));
        }
        for (String key : mapsConfigSection.getKeys(false)){
            ConfigurationSection mapSection = mapsConfigSection.getConfigurationSection(key);
            if (mapSection == null) {
                logger.warning("Config section for map %s was not found. This map will not be loaded.");
                continue;
            }

            try {
                DropperMap dropperMap = dropperMapFactory.create(key, mapSection);
                dropperMapManager.put(key, dropperMap);
            } catch(Exception ex) {
                logger.warning("Failed to create map \"%s\", so ignoring: %s".formatted(key, ex.getMessage()));
            }
        }
    }

}
