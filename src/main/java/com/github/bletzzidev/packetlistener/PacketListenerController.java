package com.github.bletzzidev.packetlistener;

import com.github.bletzzidev.packetlistener.objects.PacketData;
import com.github.bletzzidev.packetlistener.objects.PacketListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PacketListenerController implements Listener {
    private final HashMap<Class<?>, List<PacketListener>> registeredTypedPackets = new HashMap<>();
    public PacketListenerController(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    public void addPacketListener(Class<?> packetClass, PacketListener packetListener) {
        List<PacketListener> listeners = registeredTypedPackets.getOrDefault(packetClass, new ArrayList<>());
        listeners.add(packetListener);
        registeredTypedPackets.put(packetClass, listeners);
    }
    private void injectPacketListener(Player player) {
        ChannelDuplexHandler channelHandler = new ChannelDuplexHandler(){
            @Override
            public void channelRead(ChannelHandlerContext context, Object rawPacket) throws Exception {
                List<PacketListener> listeners = registeredTypedPackets.getOrDefault(rawPacket.getClass(), new ArrayList<>());
                boolean isCancelled = false;
                for (PacketListener listener : listeners) {
                    PacketData packetData = new PacketData(rawPacket, player, false);
                    listener.run(packetData);
                    isCancelled = packetData.isCancelled();
                }
                if(!isCancelled) super.channelRead(context, rawPacket);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelHandler);
    }
    private void removePacketListener(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        injectPacketListener(e.getPlayer());
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        removePacketListener(e.getPlayer());
    }
}