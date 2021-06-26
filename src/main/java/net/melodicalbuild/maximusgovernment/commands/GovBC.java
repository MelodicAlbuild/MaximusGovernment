package net.melodicalbuild.maximusgovernment.commands;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GovBC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            throw new NullArgumentException("");
        }

        Server server = Bukkit.getServer();
        String message = getFinalArg(args, 0);
        String masterMsg = "&1[&bMaximus Government&1]&f: " + message;
        server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', masterMsg));
        return true;
    }

    public static String getFinalArg(final String[] args, final int start) {
        final StringBuilder bldr = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            if (i != start) {
                bldr.append(" ");
            }
            bldr.append(args[i]);
        }
        return bldr.toString();
    }
}
