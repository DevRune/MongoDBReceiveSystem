package com.rune.thunderrewards.listener;

import com.rune.thunderrewards.Database;
import com.rune.thunderrewards.ThunderRewards;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryListener implements Listener {

    ThunderRewards plugin = JavaPlugin.getPlugin(ThunderRewards.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws SQLException {

        if(!event.getView().getTitle().contains("Rewards - ")) return;

        NBTItem nbtItem = new NBTItem(event.getCurrentItem());
        UUID id = nbtItem.getUUID("reward-id");
        plugin.getDatabase().deleteReward(id);
        event.getWhoClicked().closeInventory();
        event.getWhoClicked().sendMessage("§cRemoved " + event.getCurrentItem().getItemMeta().getDisplayName());


    }

}
