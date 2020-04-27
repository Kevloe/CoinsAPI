package de.kevloe.coins.command;

import de.kevloe.coins.CoinAPI;
import de.kevloe.coins.util.Coins;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class Coins_Command implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      final Player player = (Player) sender;
      if (args.length == 0) {
        CompletableFuture.supplyAsync(() -> Coins.getCoins(player))
            .thenAccept(
                coins ->
                    player.sendMessage(
                        CoinAPI.getInstance().getPrefix() + "Deine Coins: §e" + coins));
      } else if (args.length == 3) {
        if (args[0].equalsIgnoreCase("add")) {
          Player pl = Bukkit.getPlayer(args[1]);
          Integer i = Integer.valueOf(args[2]);
          CompletableFuture.runAsync(() -> Coins.setCoins(pl, i, false));
          player.sendMessage(
              CoinAPI.getInstance().getPrefix()
                  + "Du hast "
                  + pl.getName()
                  + " §e"
                  + i
                  + " §7Coins hinzugefügt!");
        } else if (args[0].equalsIgnoreCase("remove")) {
          Player pl = Bukkit.getPlayer(args[1]);
          Integer i = Integer.valueOf(args[2]);
          CompletableFuture.runAsync(() -> Coins.setCoins(pl, i, true));
          player.sendMessage(
              CoinAPI.getInstance().getPrefix()
                  + "Du hast "
                  + pl.getName()
                  + " §e"
                  + i
                  + " §7Coins entfernt!");
        } else {
          player.sendMessage(CoinAPI.getInstance().getPrefix() + "/coins");
          player.sendMessage(CoinAPI.getInstance().getPrefix() + "/coins (add) (Spieler) (Anzahl)");
          player.sendMessage(
              CoinAPI.getInstance().getPrefix() + "/coins (remove) (Spieler) (Anzahl)");
        }
      } else {
        player.sendMessage(CoinAPI.getInstance().getPrefix() + "/coins");
        player.sendMessage(CoinAPI.getInstance().getPrefix() + "/coins (add) (Spieler) (Anzahl)");
        player.sendMessage(
            CoinAPI.getInstance().getPrefix() + "/coins (remove) (Spieler) (Anzahl)");
      }
    }
    return false;
  }
}
