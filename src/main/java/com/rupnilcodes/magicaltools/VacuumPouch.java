package com.rupnilcodes.magicaltools;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class VacuumPouch extends Item {

    public static final int RADIUS = 24;
    public static final double PULL_SPEED = 0.4;

    public VacuumPouch(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        if (entity instanceof Player player && (equipmentSlot == EquipmentSlot.MAINHAND || equipmentSlot == EquipmentSlot.OFFHAND) ) {

            AABB area = player.getBoundingBox().inflate(RADIUS);
            List<ItemEntity> items = serverLevel.getEntitiesOfClass(ItemEntity.class, area);

            for (ItemEntity item : items) {
                Vec3 target_direction = player.position().add(0, 0.5, 0).subtract(item.position()).normalize();

                item.setDeltaMovement(target_direction.scale(PULL_SPEED));
                serverLevel.sendParticles(ParticleTypes.PORTAL, item.getX(), item.getY(), item.getZ(), 1, 0, 0, 0, 0);
            }
        }

        super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot);
    }
}
