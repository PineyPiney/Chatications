package com.pineypiney.chatications.mixin_interfaces;

public interface IGameOptions {

    // This interface is used to access GameOptions functions and variables from other classes

    boolean getNotifyChat();
    void setNotifyChat(boolean playSound);
    void toggleNotifyChat();

    void writeOptions();
}
