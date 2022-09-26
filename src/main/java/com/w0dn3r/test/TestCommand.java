package com.w0dn3r.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("testplugin.user")) {
                p.sendMessage("§cYou have permission to use this!");
            } else {
                p.sendMessage("§cYou don't have permission to use this!");
            }


            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("hello")) {
                    ((Player) sender).sendMessage("Hello my friend!");
                }
            }
        }

        return false;
    }
}
