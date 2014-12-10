package com.exsoloscript.treeoftheforbiddenfruit.data;

import com.exsoloscript.treeoftheforbiddenfruit.TreeOfTheForbiddenFruit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Stefan on 05.12.2014.
 */
public class PluginData {

    private TreeOfTheForbiddenFruit plugin;

    private boolean started;

    private Location center;
    private int mapRadius;

    private int placementDepth;

    private int timeBeforeFirstTree;
    private int timeAfterTreeBreaks;

    private UUID playerHandle;

    public PluginData(TreeOfTheForbiddenFruit t) {
        this.plugin = t;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Location getCenter() {
        return center;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public int getMapRadius() {
        return mapRadius;
    }

    public void setMapRadius(int mapRadius) {
        this.mapRadius = mapRadius;
    }

    public int getPlacementDepth() {
        return placementDepth;
    }

    public void setPlacementDepth(int placementDepth) {
        this.placementDepth = placementDepth;
    }

    public int getTimeAfterTreeBreaks() {
        return timeAfterTreeBreaks;
    }

    public void setTimeAfterTreeBreaks(int timeAfterTreeBreaks) {
        this.timeAfterTreeBreaks = timeAfterTreeBreaks;
    }

    public Player getPlayerHandle() {
        return Bukkit.getPlayer(this.playerHandle);
    }

    public void setPlayerHandle(UUID playerHandle) {
        this.playerHandle = playerHandle;
    }

    public int getTimeBeforeFirstTree() {
        return timeBeforeFirstTree;
    }

    public void setTimeBeforeFirstTree(int timeBeforeFirstTree) {
        this.timeBeforeFirstTree = timeBeforeFirstTree;
    }
}
