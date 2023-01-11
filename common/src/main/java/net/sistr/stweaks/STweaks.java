package net.sistr.stweaks;

import net.sistr.stweaks.config.STweaksConfig;

public class STweaks {
    public static final String MOD_ID = "stweaks";
    private static final STweaksConfig CONFIG_INSTANCE = new STweaksConfig();

    public static STweaksConfig config() {
        return CONFIG_INSTANCE;
    }

    public static void init() {

    }
}