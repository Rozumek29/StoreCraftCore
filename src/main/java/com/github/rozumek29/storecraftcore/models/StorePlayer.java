package com.github.rozumek29.storecraftcore.models;

import com.github.rozumek29.storecraftcore.database.DataSource;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Getter @ToString
public class StorePlayer {

    private UUID uuid;
    private String name;
    private int kills;
    private int deaths;
    private int blockPlaced;
    private int blockBreak;
    private PreparedStatement ps;
    private ResultSet rs;

    public StorePlayer(UUID uuid){
        this.uuid = uuid;
        this.name = Bukkit.getOfflinePlayer(uuid).getName();
        try{
            ps = DataSource.getConnection().prepareStatement("SELECT * FROM 'players' WHERE UUID LIKE '" + uuid + "';");
            rs = ps.executeQuery();

            if (!rs.next()){
                ps = DataSource.getConnection().prepareStatement("INSERT INTO 'players' (UUID, Name, Kills, Deaths, BlockPlaced, BlockBreak) VALUES ('"+uuid+"','"+this.name +"', 0, 0, 0, 0);");
                ps.execute();
                this.kills = 0;
                this.deaths = 0;
                this.blockPlaced = 0;
                this.blockBreak = 0;
            }else {
                this.name = rs.getString(2);
                this.kills = rs.getInt(3);
                this.deaths = rs.getInt(4);
                this.blockPlaced = rs.getInt(5);
                this.blockBreak = rs.getInt(6);
            }

//            System.out.println("Name: "+this.name);
//            System.out.println("Kills: "+this.kills);
//            System.out.println("Deaths: "+this.deaths);
//            System.out.println("blockPlaced: "+this.blockPlaced);
//            System.out.println("blockBreak: "+this.blockBreak);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void savePlayer(){
        try {
            ps = DataSource.getConnection().prepareStatement("UPDATE 'players' SET Kills="+this.kills+", Deaths=+"+this.deaths+", BlockPlaced="+this.blockPlaced+", BlockBreak="+this.blockBreak+" WHERE UUID LIKE '"+uuid+"';");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseKills(){
        this.kills++;
        try {
            ps = DataSource.getConnection().prepareStatement("UPDATE 'players' SET Kills="+this.kills+" WHERE UUID LIKE '"+this.uuid+"';");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseDeaths(){
        this.deaths++;
        try {
            ps = DataSource.getConnection().prepareStatement("UPDATE 'players' SET Deaths="+this.deaths+" WHERE UUID LIKE '"+this.uuid+"';");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseBlocksPlaced(){
        this.blockPlaced++;
        try {
            ps = DataSource.getConnection().prepareStatement("UPDATE 'players' SET BlockPlaced="+this.blockPlaced+" WHERE UUID LIKE '"+this.uuid+"';");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseBlocksBreak(){
        this.blockBreak++;
        try {
            ps = DataSource.getConnection().prepareStatement("UPDATE 'players' SET BlockBreak="+this.blockBreak+" WHERE UUID LIKE '"+this.uuid+"';");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
