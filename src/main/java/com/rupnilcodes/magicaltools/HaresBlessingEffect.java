package com.rupnilcodes.magicaltools;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HaresBlessingEffect extends MobEffect {

    public static Holder<MobEffect> HARES_BLESSING;

    public HaresBlessingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x88FF88);
    }

    public static void register() {
        HARES_BLESSING = Registry.registerForHolder(
                BuiltInRegistries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(MagicalTools.MOD_ID, "hares_blessing"),
                new HaresBlessingEffect()
        );
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof Player) {
            if (entity.isJumping()) {
                MagicalTools.LOGGER.info("Jumping");
            }
        }

        return super.applyEffectTick(level, entity, amplifier);
    }

}