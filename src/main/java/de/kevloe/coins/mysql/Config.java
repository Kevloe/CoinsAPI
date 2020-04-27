package de.kevloe.coins.mysql;

import java.io.File;
import java.io.IOException;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

  @Getter public File file = new File("plugins/Coins/mysql.yml");
  @Getter public YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

  public void createFile() {
    if (!file.exists()) {
      try {
        file.getParentFile().mkdirs();
        file.createNewFile();
        cfg.set("MySQL.Host", "host");
        cfg.set("MySQL.Port", "3306");
        cfg.set("MySQL.Database", "Database");
        cfg.set("MySQL.Username", "Username");
        cfg.set("MySQL.Password", "Password");
        cfg.save(file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
