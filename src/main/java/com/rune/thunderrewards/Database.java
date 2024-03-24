package com.rune.thunderrewards;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rune.thunderrewards.ThunderRewards;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class Database {

    //Database Details
    String password = "uoL5g2wL6xUIIL9r";
    String username = "databaseuser";
    String connectionString = "mongodb+srv://databaseuser:uoL5g2wL6xUIIL9r@cluster0.n1sgocv.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    MongoDatabase database;
    MongoCollection<Document> collection;

    public void connect() {

        MongoClient mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("friendsnetwork");
        collection = database.getCollection("rewards");
    }

    public HashMap<UUID, String> getRewardsFrom(String uuid) throws SQLException {

        Document playerdoc = new Document("player", uuid);

        HashMap<UUID, String> hashMap = new HashMap<>();

        for(Document document : collection.find(playerdoc)){
            hashMap.put(UUID.fromString(document.getString("id")), document.getString("key"));
        }

        return hashMap;
    }

    public void addRewardToPlayer(UUID uuid, String reward) throws SQLException {

        Document newdoc = new Document("player", uuid.toString());
        newdoc.append("key", reward);
        newdoc.append("id", UUID.randomUUID().toString());
        collection.insertOne(newdoc);

    }

    public void deleteReward(UUID id) throws SQLException {

        Document newdoc = new Document("id", id.toString());

        collection.deleteOne(newdoc);

    }

}
