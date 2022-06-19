package ru.theone_ss.banilla_hammers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import ru.theone_ss.banilla_hammers.compat.WinterlyIntegration;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersEffects;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersEntities;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersItems;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersParticles;

public class BanillaHammers implements ModInitializer {
	public static final String MOD_ID = "banilla_hammers";

	@Override
	public void onInitialize() {
		BanillaHammersItems.init();
		BanillaHammersEntities.init();
		BanillaHammersEffects.init();
		BanillaHammersParticles.init();
		if(FabricLoader.getInstance().isModLoaded("winterly")) {
			WinterlyIntegration.init();
		}
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
