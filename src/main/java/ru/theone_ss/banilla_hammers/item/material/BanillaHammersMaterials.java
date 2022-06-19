package ru.theone_ss.banilla_hammers.item.material;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import net.minecraft.tag.ItemTags;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;
@SuppressWarnings("deprecation")
public enum BanillaHammersMaterials implements ToolMaterial {
    WOOD(0, 89, 2.0F, 0.0F, 8, () -> Ingredient.fromTag(ItemTags.PLANKS)),
    STONE(0, 191, 4.0F, 0.0F, 3, () -> Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)),
    COPPER(0, 241, 2.0F, 0.0F, 4, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    IRON(0, 391, 6.0F, 0.0F, 7, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    DIAMOND(0, 1761, 8.0F, 0.0F, 5, () -> Ingredient.ofItems(Items.DIAMOND)),
    GOLD(0, 93, 12.0F, 0.0F, 26, () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    NETHERITE(0, 2231, 9.0F, 0.0F, 8, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    BanillaHammersMaterials(int miningLevel, int durability, float miningSpeed, float damage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = damage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
