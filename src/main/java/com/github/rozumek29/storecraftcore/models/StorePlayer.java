package com.github.rozumek29.storecraftcore.models;

import com.github.rozumek29.storecraftcore.database.DataSource;
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
        try(Connection connection = DataSource.getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM players WHERE UUID LIKE ?;")){
            ps.setString(1, uuid.toString());
            rs = ps.executeQuery();

            if (!rs.next()){
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO players (UUID, Name, Kills, Deaths, BlockPlaced, BlockBreak) VALUES (?, ? , 0, 0, 0, 0)");
                this.kills = 0;
                this.deaths = 0;
                this.blockPlaced = 0;
                this.blockBreak = 0;
                ps2.setString(1, uuid.toString());
                ps2.setString(2, name);
                ps2.execute();
                ps2.close();
            }else {
                this.name = rs.getString(2);
                this.kills = rs.getInt(3);
                this.deaths = rs.getInt(4);
                this.blockPlaced = rs.getInt(5);
                this.blockBreak = rs.getInt(6);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void savePlayer(){
        try (Connection connection = DataSource.getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE players SET Kills=?, Deaths=?, BlockPlaced=?, BlockBreak=? WHERE UUID LIKE ?;")){
            ps.setInt(1, this.getKills());
            ps.setInt(2, this.getDeaths());
            ps.setInt(3, this.blockPlaced);
            ps.setString(4, this.getUuid().toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseKills(){
        this.kills++;
        try (Connection connection = DataSource.getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE players SET Kills= ? WHERE UUID LIKE ?;")){
            ps.setInt(1, this.kills);
            ps.setString(2, this.uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseDeaths(){
        this.deaths++;
        try (Connection connection = DataSource.getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE players SET Deaths = ? WHERE UUID LIKE ?;")){
            ps.setInt(1, this.deaths);
            ps.setString(2, this.uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseBlocksPlaced(){
        this.blockPlaced++;
        try (Connection connection = DataSource.getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE players SET BlockPlaced = ? WHERE UUID LIKE ?;")){
            ps.setInt(1, this.blockPlaced);
            ps.setString(2, this.uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseBlocksBreak(){
        this.blockBreak++;
        try (Connection connection = DataSource.getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE players SET BlockBreak = ? WHERE UUID LIKE ?;")){
            ps.setInt(1, this.blockBreak);
            ps.setString(2, this.uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
