package com.rupnilcodes.magicaltools;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class Teleporter extends Item {

    public static final int DURABILITY = 10;
    public static final int DISTANCE = 16;
//    public static final int COOLDOWN = 6 * 20;
    public static final int COOLDOWN = 0;

    public Teleporter(Item.Properties properties) {
        super(properties);
    }

    public static BlockPos findSafeLocation (Vec3 look_vec, Player player, Level level, BlockPos pos, int depth) {

        BlockPos one_up = pos.above();

        if (depth >= DISTANCE) {
            return BlockPos.containing(player.position());
        }

        if (level.isEmptyBlock(pos) && level.isEmptyBlock(one_up)) {
            return pos;
        }
        else {
            BlockPos new_pos = BlockPos.containing(player.position().add(look_vec.scale(DISTANCE - depth)));
            BlockPos new_pos_up = new_pos.above();

            if (level.isEmptyBlock(new_pos) && level.isEmptyBlock(new_pos_up)) {
                return new_pos;
            }
            else {
                return findSafeLocation(look_vec, player, level, new_pos, depth + 1);
            }
        }
    }

    @Override
    public @NonNull InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            Vec3 look_vector = player.getLookAngle();
            Vec3 target_position = player.position().add(look_vector.scale(DISTANCE));

            BlockPos target_block_position = BlockPos.containing(target_position);

            MagicalTools.LOGGER.info(target_position.toString());

            BlockPos final_teleport_location = findSafeLocation(look_vector, player, level, target_block_position, 1);

            player.teleportTo(final_teleport_location.getX(), final_teleport_location.getY(), final_teleport_location.getZ());
            level.playSound(null, player.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0f, 1.0f);

            player.getCooldowns().addCooldown(stack, COOLDOWN);
            stack.hurtAndBreak(1, player, hand);
        }

        return InteractionResult.SUCCESS;
    }
}
