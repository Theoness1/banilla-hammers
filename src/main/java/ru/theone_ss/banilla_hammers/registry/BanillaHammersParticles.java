package ru.theone_ss.banilla_hammers.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BanillaHammersParticles {

    public static final DefaultParticleType STAR = FabricParticleTypes.simple();

    public static void init(){
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("banilla_hammers", "star"), STAR);
    }

}
