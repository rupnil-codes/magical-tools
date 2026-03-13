package com.rupnilcodes.magicaltools;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MobEffects {
    public static Holder<MobEffect> HARES_BLESSING;

    public static void initialize() {
        HARES_BLESSING = register("hares_blessing",
                new HaresBlessingEffect()
                        .addAttributeModifier(
                                Attributes.MOVEMENT_SPEED,
                                Identifier.withDefaultNamespace("effect.speed"),
                                0.4F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                        .addAttributeModifier(
                                Attributes.JUMP_STRENGTH,
                                Identifier.withDefaultNamespace("effect.jump_boost"),
                                (double)0.6F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
        );
    }

    private static Holder<MobEffect> register(String string, MobEffect mobEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(MagicalTools.MOD_ID, string), mobEffect);
    }

}
