package io.benlewis.dropin.dropper.configuration;

import io.benlewis.dropin.MockBukkitTest;
import io.benlewis.dropin.configuration.ConfigFactory;
import io.benlewis.dropin.configuration.ConfigType;
import org.bukkit.configuration.file.FileConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigFactoryTests extends MockBukkitTest {

    protected ConfigFactory configFactory;

    @Override
    @BeforeEach
    protected void setUpBukkit(){
        super.setUpBukkit();
        configFactory = new ConfigFactory(plugin);
    }

    @Test
    public void loadFromFile_ConfigIsPresent_CanReadValues(){
        FileConfiguration config = configFactory.getInstance(ConfigType.CONFIG).getFileConfiguration();
        assertEquals(42, config.getInt("test-value"));
    }

}
