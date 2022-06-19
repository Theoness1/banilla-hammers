package ru.theone_ss.banilla_hammers.compat;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import ru.theone_ss.banilla_hammers.item.material.BaseToolMaterial;
import ru.tlmclub.winterly.registry.WinterlyItems;

public class WinterlyIntegration {

    public static ToolMaterial CRYOMARBLE_MATERIAL = new BaseToolMaterial(0, 1761, 8.0F, 0.0F, 6, () -> Ingredient.ofItems(WinterlyItems.CRYOMARBLE));

    public static ToolMaterial getMaterial() {
        return CRYOMARBLE_MATERIAL;
    }

    public static void init() {

    }

}
