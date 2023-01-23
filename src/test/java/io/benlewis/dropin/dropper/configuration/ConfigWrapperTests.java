package io.benlewis.dropin.dropper.configuration;

import io.benlewis.dropin.MockBukkitTest;
import io.benlewis.dropin.configuration.ConfigType;
import io.benlewis.dropin.configuration.ConfigWrapper;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigWrapperTests extends MockBukkitTest {

    @Test
    public void loadFromFile_LoadThenChangeThenLoadAgain_ShouldHaveInitialValue(){
        ConfigWrapper wrapper = new ConfigWrapper(plugin, ConfigType.CONFIG.getFileName());
        FileConfiguration config = wrapper.getFileConfiguration();
        assertFalse(config.getBoolean("modified"));
        config.set("modified", true);
        assertTrue(config.getBoolean("modified"));
        FileConfiguration reloadedConfig = wrapper.loadFromFile();
        assertFalse(reloadedConfig.getBoolean("modified"));
    }

    @Test
    public void saveToFile_SetValueAndSaveThenLoad_ShouldHaveSavedValue() throws IOException, InvalidConfigurationException {
        Random rng = new Random();
        int random = rng.nextInt();
        ConfigWrapper wrapper = new ConfigWrapper(plugin, ConfigType.CONFIG.getFileName());
        FileConfiguration config = wrapper.getFileConfiguration();
        config.set("random", random);
        wrapper.saveToFile();
        FileConfiguration loaded = new YamlConfiguration();
        loaded.load(new File(ConfigType.CONFIG.getFileName()));
        assertEquals(random,loaded.getInt("random"));
    }


}
