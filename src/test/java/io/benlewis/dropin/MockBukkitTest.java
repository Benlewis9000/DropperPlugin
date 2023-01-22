package io.benlewis.dropin;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MockBukkitTest {

    protected ServerMock server;
    protected DropIn plugin;

    @BeforeEach
    protected void setUpBukkit(){
        server = MockBukkit.mock();
        plugin = MockBukkit.load(DropIn.class);
    }

    @AfterEach
    protected void tearDownBukkit(){
        MockBukkit.unmock();
    }

}
