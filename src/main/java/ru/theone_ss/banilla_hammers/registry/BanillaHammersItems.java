package ru.theone_ss.banilla_hammers.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.theone_ss.banilla_hammers.BanillaHammers;
import ru.theone_ss.banilla_hammers.compat.WinterlyIntegration;
import ru.theone_ss.banilla_hammers.item.HammerItem;
import ru.theone_ss.banilla_hammers.item.material.BanillaHammersMaterials;

import java.util.LinkedHashMap;
import java.util.Map;

public class BanillaHammersItems {

    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    public static final Item NETHERITE_HAMMER = add("netherite_hammer", new HammerItem(BanillaHammersMaterials.NETHERITE, 10, -3.2f, settings()));
    public static final Item DIAMOND_HAMMER = add("diamond_hammer", new HammerItem(BanillaHammersMaterials.DIAMOND, 9, -3.2f, settings()));
    public static final Item IRON_HAMMER = add("iron_hammer", new HammerItem(BanillaHammersMaterials.IRON, 8, -3.3f, settings()));
    public static final Item GOLDEN_HAMMER = add("golden_hammer", new HammerItem(BanillaHammersMaterials.GOLD, 6, -3.2f, settings()));
    public static final Item COPPER_HAMMER = add("copper_hammer", new HammerItem(BanillaHammersMaterials.COPPER, 7, -3.4f, settings()));
    public static final Item STONE_HAMMER = add("stone_hammer", new HammerItem(BanillaHammersMaterials.STONE, 7, -3.4f, settings()));
    public static final Item WOODEN_HAMMER = add("wooden_hammer", new HammerItem(BanillaHammersMaterials.WOOD, 6, -3.4f, settings()));

    public static final Item CRYOMARBLE_HAMMER = add("cryomarble_hammer", new HammerItem(material("winterly"), 10, -3.2f, settings("winterly")));

    private static ToolMaterial material(String modId) {
        if(FabricLoader.getInstance().isModLoaded(modId)) {
            if(modId.equals("winterly")) {
                return WinterlyIntegration.getMaterial();
            }
        }
        return BanillaHammersMaterials.IRON;
    }

    private static Item add(String name, Item item) {
        ITEMS.put(BanillaHammers.id(name), item);
        return item;
    }

    private static FabricItemSettings settings() {
        FabricItemSettings settings = new FabricItemSettings();
        settings.group(ItemGroup.COMBAT);
        return settings;
    }

    private static FabricItemSettings settings(String modId) {
        FabricItemSettings settings = new FabricItemSettings();
        if(FabricLoader.getInstance().isModLoaded(modId)) {
            settings.group(ItemGroup.COMBAT);
        }
        return settings;
    }

    public static void init() {
        ITEMS.forEach((id, item) -> Registry.register(Registry.ITEM, id, item));
    }
}
