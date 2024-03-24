package com.rune.thunderrewards.commands;

import com.rune.thunderrewards.ThunderRewards;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AddRewardCommand implements CommandExecutor {

    ThunderRewards plugin = JavaPlugin.getPlugin(ThunderRewards.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("thundercube.command.addreward")){
            sender.sendMessage("§cNo permissions");
            return true;
        }

        if(args.length < 2){
            sender.sendMessage("§4Gebruik: §c/addreward (uuid) (reward)");
            return true;
        }

        try {
            plugin.getDatabase().addRewardToPlayer(UUID.fromString(args[0]), args[1]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
