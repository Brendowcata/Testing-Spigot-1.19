package com.w0dn3r.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Events implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        e.setCancelled(true);
        e.getPlayer().sendMessage("§cStop moving! You are frozen.");

    }

    @EventHandler
    public void onPlayerEggThrow(PlayerEggThrowEvent e) {

        e.getPlayer().sendMessage("You just threw an egg!");
    }
}
