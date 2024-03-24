package com.rune.thunderrewards.commands;

import com.rune.thunderrewards.ThunderRewards;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RewardsCommand implements CommandExecutor {

    ThunderRewards plugin = JavaPlugin.getPlugin(ThunderRewards.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        if(!sender.hasPermission("thundercube.command.rewards")){
            sender.sendMessage("§cNo permissions");
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 1){
            player.sendMessage("§4Gebruik: §c/rewards (player)");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null || !target.isOnline()){
            player.sendMessage("§cPlayer not found");
            return true;
        }

        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "Rewards - " + args[0]);

        HashMap<UUID, String> hashMap;

        try {
            hashMap = plugin.getDatabase().getRewardsFrom(target.getUniqueId().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int i = 0;

        for(UUID id : hashMap.keySet()){
            ItemStack item = new ItemStack(Material.BOOK, 1);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(hashMap.get(id));
            item.setItemMeta(itemMeta);
            NBTItem nbtItem = new NBTItem(item);
            nbtItem.setUUID("reward-id", id);
            inventory.setItem(i, nbtItem.getItem());
            i++;
            if(i >= 6 * 9) break;
        }

        player.openInventory(inventory);

        return true;
    }
}
