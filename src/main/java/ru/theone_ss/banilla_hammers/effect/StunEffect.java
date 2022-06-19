package ru.theone_ss.banilla_hammers.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersParticles;

public class StunEffect extends BaseStatusEffect {

    public StunEffect() {
        super(StatusEffectCategory.HARMFUL, 0x828282);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
       if(entity.getWorld() instanceof ServerWorld serverWorld) {
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() + 0.3, entity.getY() + 2.0, entity.getZ() + 0.2, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() + 0.2, entity.getY() + 2.0, entity.getZ() + 0.4, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() + 0.3, entity.getY() + 2.0, entity.getZ() + 0.1, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() - 0.1, entity.getY() + 2.0, entity.getZ() - 0.3, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() - 0.35, entity.getY() + 2.0, entity.getZ() - 0.25, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() + 0.35, entity.getY() + 2.0, entity.getZ() - 0.25, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() - 0.3, entity.getY() + 2.0, entity.getZ() + 0.2, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() - 0.15, entity.getY() + 2.0, entity.getZ() + 0.25, 1, 0.0, 0.0, 0, 0);
           serverWorld.spawnParticles(BanillaHammersParticles.STAR, entity.getX() + 0.2, entity.getY() + 2.0, entity.getZ() - 0.2, 1, 0.0, 0.0, 0, 0);
       }
        super.applyUpdateEffect(entity, amplifier);
    }
}
