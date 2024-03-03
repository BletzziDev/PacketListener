package com.github.bletzzidev.packetlistener.objects;

import org.bukkit.entity.Player;

public class PacketData {
    private Object packet;
    private Player player;
    private boolean cancelled;

    public PacketData(Object packet, Player player, boolean cancelled) {
        this.packet = packet;
        this.player = player;
        this.cancelled = cancelled;
    }
    public Object getPacket() {
        return this.packet;
    }
    public Player getPlayer() {
        return this.player;
    }
    public boolean isCancelled() {
        return this.cancelled;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}