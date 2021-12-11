package com.pineypiney.chatications.mixins;

import com.pineypiney.chatications.mixin_interfaces.IGameOptions;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin implements IGameOptions {

    // Load function, which calls accept(), is called within the constructor
    // so assigning notifyChat cannot be done at the end of the constructor
    // or it would override the load function
    public boolean notifyChat = true;

    @Shadow
    public abstract void write();

    // At the end of the accept function in GameOptions visit the notifyChat variable as well
    @Inject(at = @At("TAIL"), method = "accept")
    private void accept(GameOptions.Visitor visitor, CallbackInfo ci){
        this.notifyChat = visitor.visitBoolean("notifyChat", this.notifyChat);

    }

    // ---- HOOKS ----
    // All accessed through the IGameOptions interface

    // Hooks to get, set and toggle the variable
    @Override
    public boolean getNotifyChat() {
        return this.notifyChat;
    }

    @Override
    public void setNotifyChat(boolean notifyChat) {
        this.notifyChat = notifyChat;
    }

    @Override
    public void toggleNotifyChat() {
        this.notifyChat = !this.notifyChat;
    }

    // Hook for other classes to access the write function
    @Override
    public void writeOptions() {
        write();
    }
}
