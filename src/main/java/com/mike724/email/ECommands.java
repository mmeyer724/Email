package com.mike724.email;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ECommands implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Email plugin;

	public ECommands(Email plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("email")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA+"Use '/email help' for help");
				return true;
			}
			boolean isPlayer   = sender instanceof Player;
			String msgUseHelp  = ChatColor.RED+"Invalid command usage, use '/email help' for help.";
			String opt = args[0];
			if(opt.equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.GREEN+"~~~ Start Email help ~~~");
				if(sender.hasPermission("Email.set")) {
					sender.sendMessage(ChatColor.AQUA+"To set your email (players only): ");
					sender.sendMessage(ChatColor.YELLOW+"/email set youremail@website.com");
				}
				if(sender.hasPermission("Email.set.other")) {
					sender.sendMessage(ChatColor.AQUA+"To set a player's email: ");
					sender.sendMessage(ChatColor.YELLOW+"/email set <player name> youremail@website.com");
				}
				if(sender.hasPermission("Email.remove")) {
					sender.sendMessage(ChatColor.AQUA+"To remove your email (players only): ");
					sender.sendMessage(ChatColor.YELLOW+"/email remove");
				}
				if(sender.hasPermission("Email.remove.other")) {
					sender.sendMessage(ChatColor.AQUA+"To remove a player's email: ");
					sender.sendMessage(ChatColor.YELLOW+"/email remove <player name>");
				}
				if(sender.hasPermission("Email.view")) {
					sender.sendMessage(ChatColor.AQUA+"To view your email (players only): ");
					sender.sendMessage("/email view");
				}
				if(sender.hasPermission("Email.view.other")) {
					sender.sendMessage(ChatColor.AQUA+"To view a player's email: ");
					sender.sendMessage("/email view <player name>");
				}
				if(sender.hasPermission("Email.export")) {
					sender.sendMessage(ChatColor.AQUA+"To export emails to a text file: ");
					sender.sendMessage(ChatColor.YELLOW+"/email export <type, either 1 or 2>");
					sender.sendMessage("Type 1 will output names and emails, type 2 will only export emails.");
				}
				sender.sendMessage(ChatColor.GREEN+"~~~ End Email help ~~~");
				return true;
			} else if(opt.equalsIgnoreCase("set")) {
				if(args.length == 2 && isPlayer && sender.hasPermission("Email.set")) {
					sender.sendMessage("TODO: Set player's email to "+args[1]);
					return true;
				} else if(args.length == 3 && sender.hasPermission("Email.set.other")) {
					sender.sendMessage("TODO: Set "+args[1]+"'s email to "+args[2]);
					return true;
				} else {
					sender.sendMessage(msgUseHelp);
					return true;
				}
			} else if(opt.equalsIgnoreCase("remove")) {
				if(args.length == 1 && isPlayer && sender.hasPermission("Email.remove")) {
					sender.sendMessage("TODO: Remove player's email");
					return true;
				} else if(args.length == 2 && sender.hasPermission("Email.remove.other")) {
					sender.sendMessage("TODO: Remove "+args[1]+"'s email");
					return true;
				} else {
					sender.sendMessage(msgUseHelp);
					return true;
				}
			} else if(opt.equalsIgnoreCase("view")) {
				if(args.length == 1 && isPlayer && sender.hasPermission("Email.view")) {
					sender.sendMessage("TODO: Display player's email");
					return true;
				} else if(args.length == 2 && sender.hasPermission("Email.view.other")) {
					sender.sendMessage("TODO: Display "+args[1]+"'s email");
					return true;
				} else {
					sender.sendMessage(msgUseHelp);
					return true;
				}
			}
			sender.sendMessage(msgUseHelp);
			return true;
		}
		return false;
	}

}
