package ru.theone_ss.banilla_hammers.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BaseStatusEffect extends StatusEffect {

    protected BaseStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

}
