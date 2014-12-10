package com.exsoloscript.treeoftheforbiddenfruit.timer;

import com.exsoloscript.treeoftheforbiddenfruit.TreeOfTheForbiddenFruit;
import com.exsoloscript.treeoftheforbiddenfruit.data.Tree;
import com.exsoloscript.treeoftheforbiddenfruit.util.LocationUtils;
import com.sk89q.worldedit.EmptyClipboardException;
import com.sk89q.worldedit.FilenameException;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.data.DataException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * Created by Stefan on 05.12.2014.
 */
public class NextTreeTimer implements Runnable {

    private TreeOfTheForbiddenFruit plugin;
    private int time;
    private int timerID;

    private Player handle;

    public NextTreeTimer(TreeOfTheForbiddenFruit plugin, int time, Player handle) {
        this.plugin = plugin;
        this.handle = handle;
        this.time = time;
        this.timerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, this, 0L, 20L);
    }

    public void run() {
        if (this.plugin.getData().isStarted()) {
            if (time > 0) {
                time--;
                System.out.println(time);
            } else if (time == 0) {
                int mapRadius = this.plugin.getData().getMapRadius();
                int placementDepth = this.plugin.getData().getPlacementDepth();
                Location center = LocationUtils.randomLocationRelativeTo(this.plugin.getData().getCenter(), mapRadius);

                try {
                    Tree.getInstance(this.plugin).paste(center, placementDepth);
                } catch (Exception e) {
                    Bukkit.broadcastMessage(this.plugin.prefix() + "WorldEdit can't place the tree, command canceled");
                    this.plugin.getData().setStarted(false);
                    Bukkit.getScheduler().cancelTask(timerID);
                    return;
                }

                Bukkit.broadcastMessage(this.plugin.prefix() + "A new tree was spawned at " + LocationUtils.toString(center));

                Bukkit.getScheduler().cancelTask(timerID);
            }
        } else {
            Bukkit.getScheduler().cancelTask(timerID);
        }
    }
}
