package com.fourmc.events;

import com.fourmc.AnimationTestClient;
import dev.kosmx.playerAnim.api.layered.IActualAnimation;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static final String KEY_CATEGORY_ANIMATIONTEST = "key.fourmc.animation-test";
    public static final String KEY_WAVE = "key.animation-test.wave";
    public static final String KEY_ABILITY1 = "key.animation-test.ability1";

    public static KeyBinding waveKey;
    public static KeyBinding ability1Key;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (waveKey.wasPressed()) {
                ModifierLayer<IAnimation> layer = AnimationTestClient.getAnimLayer(client.player);
                if (layer != null) {
                    IActualAnimation<?> rawAnim = PlayerAnimationRegistry.getAnimation(Identifier.of("animation-test", "waving")).playAnimation();
                    layer.setAnimation(rawAnim); // Or load from JSON, etc.
                }
            }
            if (ability1Key.wasPressed()) {
                ModifierLayer<IAnimation> layer = AnimationTestClient.getAnimLayer(client.player);
                if (layer != null) {
                    IActualAnimation<?> rawAnim = PlayerAnimationRegistry.getAnimation(Identifier.of("animation-test", "stretch")).playAnimation();
                    layer.setAnimation(rawAnim); // Or load from JSON, etc.
                }
            }
        });
    }

    public static void register() {
        waveKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_WAVE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_ANIMATIONTEST
        ));

        ability1Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ABILITY1,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                KEY_CATEGORY_ANIMATIONTEST
        ));

        registerKeyInputs();
    }
}
