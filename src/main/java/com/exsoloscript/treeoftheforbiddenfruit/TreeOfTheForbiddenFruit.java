package com.exsoloscript.treeoftheforbiddenfruit;

import com.exsoloscript.treeoftheforbiddenfruit.cmd.TreeCommand;
import com.exsoloscript.treeoftheforbiddenfruit.data.PluginData;
import com.exsoloscript.treeoftheforbiddenfruit.event.BlockBreakListener;
import com.exsoloscript.treeoftheforbiddenfruit.event.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.File;

/**
 * Created by Stefan on 05.12.2014.
 */

public class TreeOfTheForbiddenFruit extends JavaPlugin {

    private PluginData data;

    public void onEnable() {
        this.data = new PluginData(this);

        // Plugin wont work without we
        if (Bukkit.getPluginManager().getPlugin("WorldEdit") == null) {
            Bukkit.broadcastMessage(prefix() + "You need WorldEdit to run this plugin! Plugin is shutting down...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Saves config given as resource
        this.saveDefaultConfig();
        loadConfig();

        // Copy schematic from resource folder to data folder
        if (!new File(getDataFolder(), "tree.schematic").exists())
            saveResource("tree.schematic", false);

        register();
    }

    public void onDisable() {
        
    }

    public void loadConfig() {
        Vector v = this.getConfig().getVector("config.center.data");
        World w = Bukkit.getWorld(this.getConfig().getString("config.center.world"));
        if (v != null) {
            if (w != null) {
                this.getData().setCenter(v.toLocation(w));
            } else {
                this.getData().setCenter(v.toLocation(Bukkit.getWorlds().get(0)));
            }
        } else {
            this.getData().setCenter(Bukkit.getWorlds().get(0).getSpawnLocation());
        }

        this.getData().setMapRadius(this.getConfig().getInt("config.center.radius"));

        this.getData().setTimeBeforeFirstTree(this.getConfig().getInt("config.timer.min-until-first-tree") * 1200);
        this.getData().setTimeAfterTreeBreaks(this.getConfig().getInt("config.timer.min-after-tree-breaks") * 1200);
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(this), this);

        this.getCommand("luctree").setExecutor(new TreeCommand(this));
    }

    public String prefix() {
        return ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Lucrorium" + ChatColor.DARK_GREEN + "] " + ChatColor.RESET;
    }

    public PluginData getData() {
        return this.data;
    }
}
