package ru.theone_ss.banilla_hammers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.theone_ss.banilla_hammers.compat.WinterlyIntegration;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersEffects;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersEntities;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersItems;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersParticles;

public class BanillaHammers implements ModInitializer {
	public static ItemGroup BANILLA_HAMMERS;
	public static final String MOD_ID = "banilla_hammers";

	@Override
	public void onInitialize() {
		BANILLA_HAMMERS = createItemGroup();
		BanillaHammersItems.init();
		BanillaHammersEntities.init();
		BanillaHammersEffects.init();
		BanillaHammersParticles.init();
		if(FabricLoader.getInstance().isModLoaded("winterly")) {
			WinterlyIntegration.init();
		}
	}

	private static ItemGroup createItemGroup() {
		var group = FabricItemGroup.builder().displayName(Text.translatable("Banilla Hammers"))
				.icon(() -> BanillaHammersItems.DIAMOND_HAMMER.asItem().getDefaultStack())
				.entries((displayContext, entries) -> BanillaHammersItems.ITEMS.forEach((id, item) -> entries.add(item.getDefaultStack()))).build();
		Registry.register(Registries.ITEM_GROUP, id("items"), group);
		return group;
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
