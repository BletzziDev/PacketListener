package com.github.bletzzidev.packetlistener.interfaces;

import com.github.bletzzidev.packetlistener.objects.PacketData;

@FunctionalInterface
public interface PacketListenerCallback {
    void callback(PacketData packetData);
}