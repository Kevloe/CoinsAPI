package de.kevloe.coins.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.kevloe.coins.mysql.MySQL;
import org.bukkit.entity.Player;

public class Coins {

  private static boolean isPlayerExists(String user) {
    try {
      PreparedStatement ps =
          MySQL.getConnection()
              .prepareStatement("SELECT Coins FROM CoinsSystem WHERE Spielername = ?");
      ps.setString(1, user);
      ResultSet rs = ps.executeQuery();
      return rs.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void createTable() {
    try {
      PreparedStatement ps =
          MySQL.getConnection()
              .prepareStatement(
                  "CREATE TABLE IF NOT EXISTS CoinsSystem (Spielername VARCHAR(100),Coins INT(100))");
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void setCoins(Player user, int amount, boolean remove) {
    int coins = getCoins(user);
    int coin;
    if (remove) {
      coin = coins - amount;
    } else {
      coin = coins + amount;
    }

    if (isPlayerExists(user.getName())) {
      try {
        PreparedStatement ps =
            MySQL.getConnection()
                .prepareStatement("UPDATE CoinsSystem SET Coins = ? WHERE Spielername = ?");
        ps.setInt(1, coin);
        ps.setString(2, user.getName());
        ps.executeUpdate();
        ps.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      try {
        PreparedStatement ps =
            MySQL.getConnection()
                .prepareStatement("INSERT INTO CoinsSystem (Spielername,Coins) VALUES (?,?)");
        ps.setString(1, user.getName());
        ps.setInt(2, amount);
        ps.executeUpdate();
        ps.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static Integer getCoins(Player user) {
    try {
      PreparedStatement ps =
          MySQL.getConnection()
              .prepareStatement("SELECT Coins FROM CoinsSystem WHERE Spielername = ?");
      ps.setString(1, user.getName());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        return rs.getInt("Coins");
      }
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
