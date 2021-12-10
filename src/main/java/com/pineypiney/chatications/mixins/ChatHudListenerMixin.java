package com.pineypiney.chatications.mixins;

import com.pineypiney.chatications.ChaticationsClient;
import com.pineypiney.chatications.mixin_interfaces.MinecraftClientInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ClientChatListener;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ChatHudListener.class)
public abstract class ChatHudListenerMixin implements ClientChatListener {

    public boolean playSound = false;

    @Inject(at = @At("TAIL"), method = "onChatMessage")
    private void onChatMessage(MessageType type, Text text, UUID sender, CallbackInfo ci){
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity p = client.player;
        if(p != null && client instanceof MinecraftClientInterface c) {
            if(type == MessageType.CHAT && !client.isWindowFocused() && c.getPlaySound()){
                p.playSound(ChaticationsClient.NOTIFICATION_SOUND_EVENT, SoundCategory.VOICE, 1, 1);
            }
        }
    }
}
