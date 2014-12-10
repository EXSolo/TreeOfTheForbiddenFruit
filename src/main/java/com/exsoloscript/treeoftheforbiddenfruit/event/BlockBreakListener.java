package com.exsoloscript.treeoftheforbiddenfruit.event;

import com.exsoloscript.treeoftheforbiddenfruit.TreeOfTheForbiddenFruit;
import com.exsoloscript.treeoftheforbiddenfruit.data.Tree;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Stefan on 07.12.2014.
 */
public class BlockBreakListener implements Listener {

    private TreeOfTheForbiddenFruit plugin;
    private WorldEditPlugin worldEdit;

    public BlockBreakListener(TreeOfTheForbiddenFruit plugin) {
        this.plugin = plugin;
        this.worldEdit = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (this.plugin.getData().isStarted()) {
            Region r = Tree.getInstance(this.plugin).getRegion();
            Location l = event.getBlock().getLocation();
            if (r != null) {
                if (r.contains(new Vector(l.getX(), l.getY(), l.getZ()))) {
                    EditSession es = this.worldEdit.createEditSession(event.getPlayer());
                    Set<BaseBlock> toReplace = new HashSet<BaseBlock>();
                    BaseBlock replaceWith = new BaseBlock(0);
                    System.out.println("Match!");
                    /*try {
                        es.replaceBlocks(r, toReplace, replaceWith);
                    } catch (MaxChangedBlocksException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }
    }
}
