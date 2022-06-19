package ru.theone_ss.banilla_hammers.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BanillaHammersParticles {

    public static final DefaultParticleType STAR = FabricParticleTypes.simple();

    public static void init(){
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("banilla_hammers", "star"), STAR);
    }

}
