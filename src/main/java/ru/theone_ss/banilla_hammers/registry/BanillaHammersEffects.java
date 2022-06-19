package ru.theone_ss.banilla_hammers.registry;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.theone_ss.banilla_hammers.BanillaHammers;
import ru.theone_ss.banilla_hammers.effect.StunEffect;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class BanillaHammersEffects {

    public static final Map<Identifier, StatusEffect> EFFECTS = new LinkedHashMap<>();

    public static final StatusEffect STUN = add("stun", new StunEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, UUID.randomUUID().toString(), -100d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, UUID.randomUUID().toString(), -100d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, UUID.randomUUID().toString(), +100d, EntityAttributeModifier.Operation.ADDITION));

    private static StatusEffect add(String name, StatusEffect effect) {
        EFFECTS.put(BanillaHammers.id(name), effect);
        return effect;
    }

    public static void init() {
        EFFECTS.forEach((id, effect) -> Registry.register(Registry.STATUS_EFFECT, id, effect));
    }

}
