package com.rupnilcodes.magicaltools;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HaresBlessingEffect extends MobEffect {
    public HaresBlessingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x88FF88);
    }


    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.fallDistance = 0.0F;
        }

        return true;
    }

}