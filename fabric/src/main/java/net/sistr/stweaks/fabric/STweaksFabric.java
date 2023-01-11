package net.sistr.stweaks.fabric;

import net.fabricmc.api.ModInitializer;
import net.sistr.stweaks.STweaks;

public class STweaksFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        STweaks.init();
    }
}