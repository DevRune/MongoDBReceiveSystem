package com.rune.thunderrewards;

import com.rune.thunderrewards.commands.AddRewardCommand;
import com.rune.thunderrewards.commands.AddRewardNameCommand;
import com.rune.thunderrewards.commands.RewardsCommand;
import com.rune.thunderrewards.listener.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class ThunderRewards extends JavaPlugin {

    Database database;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("addreward").setExecutor(new AddRewardCommand());
        getCommand("addrewardname").setExecutor(new AddRewardNameCommand());
        getCommand("rewards").setExecutor(new RewardsCommand());
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        this.database = new Database();
        database.connect();
    }

    public Database getDatabase() {
        return database;
    }
}
