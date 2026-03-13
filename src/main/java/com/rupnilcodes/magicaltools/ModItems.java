package com.rupnilcodes.magicaltools;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Function;

public class ModItems {
    public static final VacuumPouch VACUUM_POUCH =
            register("vacuum_pouch", VacuumPouch::new, new Item.Properties());

    public static final Teleporter TELEPORTER =
            register("teleporter", Teleporter::new, new Item.Properties().durability(Teleporter.DURABILITY));

    public static final RabbitShoes RABBIT_SHOES = register(
            "rabbit_shoes",
            RabbitShoes::new,
            new Item.Properties().humanoidArmor(RabbitShoes.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(RabbitShoes.BASE_DURABILITY))
    );

    public static final GravityApple GRAVITY_APPLE =
            register(
                    "gravity_apple",
                    GravityApple::new,
                    new Item.Properties().food(
                            GravityApple.FOOD_COMPONENT,
                        GravityApple.FOOD_CONSUMABLE_COMPONENT
                    )
            );

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MagicalTools.MOD_ID, name));

        T item = itemFactory.apply(settings.setId(itemKey));

        assert item != null;
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        MagicalTools.LOGGER.info("Initializing ModItems.");
    }

}