package com.exsoloscript.treeoftheforbiddenfruit.event;

import com.exsoloscript.treeoftheforbiddenfruit.TreeOfTheForbiddenFruit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Stefan on 07.12.2014.
 */
public class PlayerMoveListener implements Listener {

    private TreeOfTheForbiddenFruit plugin;

    public PlayerMoveListener(TreeOfTheForbiddenFruit plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (this.plugin.getData().isStarted()) {
            if (player.getUniqueId().equals(this.plugin.getData().getPlayerHandle())) {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    player.teleport(event.getFrom());
                    player.setFlying(true);
                    event.setCancelled(true);
                }
            }
        }
    }
}
