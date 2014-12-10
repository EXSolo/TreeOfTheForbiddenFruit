package com.exsoloscript.treeoftheforbiddenfruit.cmd;

import com.exsoloscript.treeoftheforbiddenfruit.TreeOfTheForbiddenFruit;
import com.exsoloscript.treeoftheforbiddenfruit.timer.NextTreeTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Stefan on 05.12.2014.
 */
public class TreeCommand implements CommandExecutor {

    private TreeOfTheForbiddenFruit plugin;

    public TreeCommand(TreeOfTheForbiddenFruit plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            if (sender.hasPermission("totff.manage")) {
                Player player = (Player) sender;
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("start")) {
                        if (!this.plugin.getData().isStarted()) {
                            this.plugin.getData().setStarted(true);
                            this.plugin.getData().setPlayerHandle(player.getUniqueId());
                            new NextTreeTimer(this.plugin, this.plugin.getData().getTimeBeforeFirstTree(), player);
                            Bukkit.broadcastMessage(this.plugin.prefix() + "The tree of the forbidden fruit was enabled");
                        } else {
                            sender.sendMessage(this.plugin.prefix() + "The game was started already. You can stop it with /luctree stop");
                        }
                    } else if (args[0].equalsIgnoreCase("stop")) {
                        this.plugin.getData().setStarted(false);
                        Bukkit.broadcastMessage(this.plugin.prefix() + "The tree of the forbidden fruit was disabled");
                    } else if (args[0].equalsIgnoreCase("help")) {
                        sender.sendMessage(
                                this.plugin.prefix() +
                                        "TOTFF Commands: \n" +
                                        " - /luctree start" +
                                        " - /luctree stop" +
                                        " - /luctree help"
                        );
                    }
                }
            }
        }

        return false;
    }
}
