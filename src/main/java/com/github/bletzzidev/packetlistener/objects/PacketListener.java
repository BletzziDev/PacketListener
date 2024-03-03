package com.github.bletzzidev.packetlistener.objects;

import com.github.bletzzidev.packetlistener.interfaces.PacketListenerCallback;

public class PacketListener {
    private final PacketListenerCallback callback;

    public PacketListener(PacketListenerCallback callback) {
        this.callback = callback;
    }
    public void run(PacketData packetData) {
        callback.callback(packetData);
    }
    public PacketListenerCallback getCallback() {
        return callback;
    }
}