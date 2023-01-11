package net.sistr.stweaks.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sistr.stweaks.STweaks;

@Mod(STweaks.MOD_ID)
public class STweaksForge {
    public STweaksForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(STweaks.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        STweaks.init();
    }
}