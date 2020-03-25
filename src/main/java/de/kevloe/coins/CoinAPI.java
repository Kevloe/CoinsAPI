package de.kevloe.coins;

import de.kevloe.coins.command.Coins_Command;
import de.kevloe.coins.mysql.Config;
import de.kevloe.coins.mysql.MySQL;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Kevloe on 20.03.2020.
 */

public class CoinAPI extends JavaPlugin {

    @Getter
    private static CoinAPI instance;

    @Getter
    private String prefix = "§eCoins §7| ";

    @Getter
    private Config mysqlconfig;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        this.mysqlconfig = new Config();
        this.mysqlconfig.createFile();

        MySQL.connect();

        getCommand("coins").setExecutor(new Coins_Command());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MySQL.disconnect();
    }
}
