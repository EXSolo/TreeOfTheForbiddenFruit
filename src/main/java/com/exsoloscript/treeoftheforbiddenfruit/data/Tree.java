package com.exsoloscript.treeoftheforbiddenfruit.data;

import com.exsoloscript.treeoftheforbiddenfruit.TreeOfTheForbiddenFruit;
import com.exsoloscript.treeoftheforbiddenfruit.util.TerrainManager;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Stefan on 05.12.2014.
 */
public class Tree {

    private static Tree instance;

    private Region pastedRegion;

    private TerrainManager terrainManager;

    private WorldEditPlugin worldEdit;
    private TreeOfTheForbiddenFruit plugin;

    public static Tree getInstance(TreeOfTheForbiddenFruit t) {

        if (instance == null)
            instance = new Tree(t);

        return instance;
    }

    private Tree(TreeOfTheForbiddenFruit t) {
        this.plugin = t;
        this.worldEdit = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        this.terrainManager = new TerrainManager(this.worldEdit, this.plugin.getData().getPlayerHandle());
    }

    public void paste(Location where, int howDeep) throws MaxChangedBlocksException, IOException, DataException, EmptyClipboardException, FilenameException {
        Player handle = this.plugin.getData().getPlayerHandle();
        Location handleLocation = handle.getLocation();

        LocalSession ls = this.worldEdit.getSession(handle);

        where.setY(where.getY() - howDeep);
        handle.teleport(where);

        Vector down = new BlockVector(handleLocation.getX() + 40, handleLocation.getY(), handleLocation.getZ() + 40);
        Vector up = new BlockVector(handleLocation.getX() - 40, handleLocation.getY() + 120, handleLocation.getZ() - 40);

        this.pastedRegion = new CuboidRegion(ls.getSelectionWorld(), down, up);

        handle.teleport(where);

        this.terrainManager.loadSchematic(new File(this.plugin.getDataFolder(), "tree"), where);
        where.setY(250);
        handle.teleport(where);
    }

    public Region getRegion() {
        return pastedRegion;
    }
}
