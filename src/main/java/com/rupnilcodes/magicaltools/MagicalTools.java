package com.rupnilcodes.magicaltools;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicalTools implements ModInitializer {

    public static final String MOD_ID = "magicaltools";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
        MobEffects.initialize();
        LOGGER.info(MOD_ID + " Initialized!");
    }
}
