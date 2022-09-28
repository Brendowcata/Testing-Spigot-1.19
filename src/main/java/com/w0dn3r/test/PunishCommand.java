package com.w0dn3r.test;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class PunishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 2) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);

                    switch (args[1].toLowerCase()) {
                        case "kick":
                            target.kickPlayer(ChatColor.RED + "You have been kicked for being a bad player!");
                            break;
                        case "ban":
                            Bukkit.getBanList(BanList.Type.NAME).addBan(
                                    target.getName(),
                                    ChatColor.RED + "Being a bad player!",
                                    null, null);
                            target.kickPlayer(ChatColor.RED + "You have been banned!");
                            break;
                        case "tempban":
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.HOUR, 12);

                            Bukkit.getBanList(BanList.Type.NAME).addBan(
                                    target.getName(),
                                    ChatColor.RED + "Being a bad player!",
                                    cal.getTime(), null);
                            target.kickPlayer(ChatColor.RED + "You have been banned!");
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "You did not specify whether to kick, ban or tempban!");
                            return false;
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "This player is not online!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /punish <player> <kick/ban/tempban>.");
            }
        }

        return false;
    }
}
