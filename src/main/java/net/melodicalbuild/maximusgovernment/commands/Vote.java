package net.melodicalbuild.maximusgovernment.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vote implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(Gov.voteOn) {
            if(args[0].equalsIgnoreCase("yes")) {
                Gov.voteYes++;
                String message = "&1[&bMaximus Government&1]&f: &aYou have voted Yes.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            } else if(args[0].equalsIgnoreCase("no")) {
                Gov.voteNo++;
                String message = "&1[&bMaximus Government&1]&f: &aYou have voted No.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            } else {
                String message = "&1[&bMaximus Government&1]&f: &4That is an Invalid Vote Choice.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        } else {
            String message = "&1[&bMaximus Government&1]&f: &4There is no active vote.";
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
            commands.add("yes");
            commands.add("no");
            org.bukkit.util.StringUtil.copyPartialMatches(args[0], commands, completions);
        Collections.sort(completions);
        return completions;
    }
}
