package com.pineypiney.chatications;

import com.pineypiney.chatications.mixin_interfaces.IGameOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;

public class ChaticationsClient implements ClientModInitializer {

    public static final Identifier NOTIFICATION_ID = new Identifier("chatications:notification");
    public static SoundEvent NOTIFICATION_SOUND_EVENT = new SoundEvent(NOTIFICATION_ID);

    // This is the keybinding to toggle the notifications
    private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.chatications.notifications",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "category.chatications.chatications"
    ));

    @Override
    public void onInitializeClient() {
        // This is all taken from https://fabricmc.net/wiki/tutorial:keybinds
        Registry.register(Registry.SOUND_EVENT, NOTIFICATION_ID, NOTIFICATION_SOUND_EVENT);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(keyBinding.wasPressed()){
                if(client.options instanceof IGameOptions options){
                    options.toggleNotifyChat();
                    options.writeOptions();
                }
            }
        });
    }
}
