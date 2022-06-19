package ru.theone_ss.banilla_hammers.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.theone_ss.banilla_hammers.entity.RectangleEntity;

public class BanillaHammersEntities {

    public static final EntityType<Entity> RECTANGLE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("banilla_hammers", "rectangle"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (type, world) -> new RectangleEntity(type, world)).dimensions(EntityDimensions.fixed(2.5F, 0.25F)).build());

    public static void init() {
    }
}
