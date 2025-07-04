package com.fourmc;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.MirrorModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class AnimationTestClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("animation-test");

    public static final Identifier MOD_LAYER_ID = Identifier.of("animation-test", "waving");

    @Override
    public void onInitializeClient() {

        PlayerAnimationAccess.REGISTER_ANIMATION_EVENT.register((player, animationStack) -> {
            ModifierLayer<IAnimation> layer = new ModifierLayer<>();
            animationStack.addAnimLayer(1000, layer);
            PlayerAnimationAccess.getPlayerAssociatedData(player).set(MOD_LAYER_ID, layer);
        });
    }

    public static ModifierLayer<IAnimation> getAnimLayer(AbstractClientPlayerEntity player) {
        return (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData(player).get(MOD_LAYER_ID);
    }
}
