package ru.theone_ss.banilla_hammers.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersEntities;

@Environment(EnvType.CLIENT)
public class RectangleEntityClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(BanillaHammersEntities.RECTANGLE, EmptyEntityRenderer::new);

    }
}
