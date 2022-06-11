package net.melodicalbuild.maximusgovernment.commands;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gov implements TabExecutor {
    public boolean govSession = false;
    public static boolean voteOn = false;
    public static int voteYes = 0;
    public static int voteNo = 0;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Server server = Bukkit.getServer();
        if (args[0].isEmpty()) {
            throw new NullArgumentException("");
        } else if(args[0].equalsIgnoreCase("enable")) {
            if(!govSession) {
                String message = "&1[&bMaximus Government&1]&f: &2The Maximus Government is now in Session, Everyone Report to the Community Hall.";
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                govSession = true;
            } else {
                String message = "&1[&bMaximus Government&1]&f: &4The Government is in Session, You can't start it now.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        } else if(args[0].equalsIgnoreCase("disable")) {
            if(govSession) {
                String message = "&1[&bMaximus Government&1]&f: &2The Maximus Government Session is now Over, You are dismissed.";
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                govSession = false;
            } else {
                String message = "&1[&bMaximus Government&1]&f: &4The Government is not in Session, You can't end it now.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        } else if(args[0].equalsIgnoreCase("vote")) {
            if(args[1].equalsIgnoreCase("start") && !voteOn) {
                voteYes = 0;
                voteNo = 0;
                voteOn = true;
                String message = "&1[&bMaximus Government&1]&f: &2A New Vote has been started, Please use /vote yes | /vote no, to cast your vote.";
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
            } else if(args[1].equalsIgnoreCase("start")) {
                String message = "&1[&bMaximus Government&1]&f: &4A Vote is already started, Please use Stop first.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }

            if(args[1].equalsIgnoreCase("stop") && voteOn) {
                voteOn = false;
                if(voteYes > voteNo) {
                    String message = "&1[&bMaximus Government&1]&f: &aThe Vote has been passed with a vote of " + voteYes + " Votes to Yes and " + voteNo + " Votes to No.";
                    server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                } else if(voteYes < voteNo) {
                    String message = "&1[&bMaximus Government&1]&f: &aThe Vote has been denied with a vote of " + voteYes + " Votes to Yes and " + voteNo + " Votes to No.";
                    server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                } else {
                    String message = "&1[&bMaximus Government&1]&f: &aThe Vote has been voted neutral with a vote of " + voteYes + " Votes to Yes and " + voteNo + " Votes to No.";
                    server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                }
            } else if(args[1].equalsIgnoreCase("stop")) {
                String message = "&1[&bMaximus Government&1]&f: &4There is not active vote. Please use Start first.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }

            if(args[1].equalsIgnoreCase("check") && voteOn) {
                String winningVote = "";
                if(voteYes > voteNo) {
                    winningVote = "&2Yes";
                } else if(voteNo > voteYes) {
                    winningVote = "&4No";
                } else {
                    winningVote = "&6Neutral";
                }
                String message = "&1[&bMaximus Government&1]&f: &aThe Current Voting Results are, " + voteYes + " Votes to Yes and " + voteNo + " Votes to No with " + winningVote + " Winning.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            } else if(args[1].equalsIgnoreCase("check")) {
                String message = "&1[&bMaximus Government&1]&f: &4There is not active vote. Please use Start first.";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
//            String message = "&1[&bMaximus Government&1]&f: &4Vote is Currently not Implemented, Please try again later.";
//            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(args.length == 1) {
            commands.add("enable");
            commands.add("disable");
            commands.add("vote");
            org.bukkit.util.StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if(args.length == 2) {
            if(args[0].equals("vote")) {
                commands.add("start");
                commands.add("stop");
                commands.add("check");
                org.bukkit.util.StringUtil.copyPartialMatches(args[1], commands, completions);
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
