package ru.theone_ss.banilla_hammers.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import ru.theone_ss.banilla_hammers.item.HammerItem;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersItems;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersParticles;

public class BanillaHammersClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BanillaHammersItems.ITEMS.forEach((identifier, item) -> {
            if (item instanceof HammerItem) TwoModelsItemRegistry.register(item);
        });

        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> TwoModelsItemRegistry.ENTRIES.forEach((id, item) ->
                out.accept(new ModelIdentifier(new Identifier(id + "_in_hand"), "inventory"))
        ));
        /*
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((((atlasTexture, registry) -> {
            registry.register(new Identifier("banilla_hammers", "particle/star"));
        })));
        */
        ParticleFactoryRegistry.getInstance().register(BanillaHammersParticles.STAR, FlameParticle.Factory::new);
    }
}

