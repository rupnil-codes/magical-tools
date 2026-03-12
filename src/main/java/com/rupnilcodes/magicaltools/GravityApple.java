package com.rupnilcodes.magicaltools;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class GravityApple extends Item {
    public GravityApple(Properties properties) {
        super(properties);
    }
    public static final FoodProperties FOOD_COMPONENT = new FoodProperties.Builder()
            .alwaysEdible()
            .build();

    public static final Consumable FOOD_CONSUMABLE_COMPONENT = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(
                            MobEffects.SLOW_FALLING, 10 * 20, 1),
                        1.0f
                    )
            )
        .build();

}
