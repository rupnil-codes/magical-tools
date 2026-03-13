package com.rupnilcodes.magicaltools;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class RabbitShoes extends Item {
    public RabbitShoes(Properties properties) {
        super(properties);
    }
    public static final int BASE_DURABILITY = 8;

    public static final ResourceKey<EquipmentAsset> ARMOR_MATERIAL_KEY =
            ResourceKey.create(
                    EquipmentAssets.ROOT_ID,
                    Identifier.fromNamespaceAndPath(
                            MagicalTools.MOD_ID, "rabbit"
                    )
            );

    public static final TagKey<Item> REPAIRS_ARMOR =
            TagKey.create(
                    BuiltInRegistries.ITEM.key(),
                    Identifier.fromNamespaceAndPath(
                            MagicalTools.MOD_ID, "repairs_rabbit_armor"
                    )
            );

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.BOOTS, 2
            ),
            5,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            REPAIRS_ARMOR,
            ARMOR_MATERIAL_KEY
    );

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        if (entity instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET) == itemStack) {

            var instance = new MobEffectInstance(MobEffects.HARES_BLESSING, 20, 0, true, false, true);
            player.addEffect(instance);
        }
    }
}