package com.pineypiney.chatications.mixins;

import com.pineypiney.chatications.mixin_interfaces.MinecraftClientInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin extends ReentrantThreadExecutor<Runnable> implements WindowEventHandler, MinecraftClientInterface {

    private boolean playSound = true;

    public MinecraftClientMixin(String string) {
        super(string);
    }

    @Override
    public boolean getPlaySound() {
        return this.playSound;
    }

    @Override
    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    @Override
    public void togglePLaySound() {
        this.playSound = !playSound;
    }
}
