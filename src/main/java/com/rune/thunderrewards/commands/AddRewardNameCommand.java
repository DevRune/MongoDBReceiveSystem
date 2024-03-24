package com.rune.thunderrewards.commands;

import com.rune.thunderrewards.ThunderRewards;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.UUID;

public class AddRewardNameCommand implements CommandExecutor {

    ThunderRewards plugin = JavaPlugin.getPlugin(ThunderRewards.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("thundercube.command.addreward")){
            sender.sendMessage("§cNo permissions");
            return true;
        }

        if(args.length < 2){
            sender.sendMessage("§4Gebruik: §c/addrewardname (name) (reward)");
            return true;
        }

        try {
            plugin.getDatabase().addRewardToPlayer(Bukkit.getPlayer(args[0]).getUniqueId(), args[1]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
