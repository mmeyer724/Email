/*
    This file is part of Email.

    Email is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Email is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Email.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.mike724.email;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.PluginDescriptionFile;

public class EmailCommands implements CommandExecutor {

    private Email plugin;
    private String[] everyEmails;
    LanguageManager LG;

    public EmailCommands(Email plugin) {
        this.plugin = plugin;
        this.LG = new LanguageManager(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("email")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.AQUA + LG.getString("commands.help1") + " ");
                return true;
            }
            boolean isPlayer = sender instanceof Player;
            String msgUseHelp = ChatColor.RED + LG.getString("commands.help2") + " ";
            String opt = args[0];
            if (opt.equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.GREEN + LG.getString("commands.startEmailHelp") + " ");
                sender.sendMessage(ChatColor.AQUA + LG.getString("commands.viewPluginInformation") + " ");
                sender.sendMessage(ChatColor.YELLOW + "/email info");
                if (sender.hasPermission("Email.set")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailSet1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailSet2") + " ");
                }
                if (sender.hasPermission("Email.set.others")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailSetOther1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailSetOther2") + " ");
                }
                if (sender.hasPermission("Email.remove")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailRemove1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailRemove2") + " ");
                }
                if (sender.hasPermission("Email.remove.others")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailRemoveOthers1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailRemoveOthers2") + " ");
                }
                if (sender.hasPermission("Email.view")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailView1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailView2") + " ");
                }
                if (sender.hasPermission("Email.view.others")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailViewOther1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailViewOther2") + " ");
                }
                if (sender.hasPermission("Email.send")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailSend1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailSend2") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailSend3") + " ");
                }
                if (sender.hasPermission("Email.send.all")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailSendAll1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailSendAll2") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailSendAll3") + " ");
                }
                if (sender.hasPermission("Email.export")) {
                    sender.sendMessage(ChatColor.AQUA + LG.getString("commands.emailExport1") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailExport2") + " ");
                    sender.sendMessage(ChatColor.YELLOW + LG.getString("commands.emailExport3") + " ");
                }
                sender.sendMessage(ChatColor.GREEN + LG.getString("commands.endHelp") + " ");
                return true;
            } else if (opt.equalsIgnoreCase("set")) {
                if (args.length == 2 && isPlayer && sender.hasPermission("Email.set")) {
                    boolean result = plugin.emails.setPlayerEmail(sender.getName(), args[1]);
                    sender.sendMessage((result) ? ChatColor.GREEN + LG.getString("answers.emailSet") + " " : ChatColor.RED + LG.getString("answers.invalidEmail") + " ");
                    return true;
                } else if (args.length == 3 && sender.hasPermission("Email.set.others")) {
                    boolean result = plugin.emails.setPlayerEmail(args[1], args[2]);
                    sender.sendMessage((result) ? ChatColor.GREEN + LG.getString("answers.emailSet") + " " : ChatColor.RED + LG.getString("answers.invalidEmail") + " ");
                    return true;
                } else {
                    sender.sendMessage(msgUseHelp);
                    return true;
                }
            } else if (opt.equalsIgnoreCase("remove")) {
                if (args.length == 1 && isPlayer && sender.hasPermission("Email.remove")) {
                    plugin.emails.removePlayerEmail(sender.getName());
                    sender.sendMessage(ChatColor.GREEN + LG.getString("answers.emailRemove") + " ");
                    return true;
                } else if (args.length == 2 && sender.hasPermission("Email.remove.others")) {
                    plugin.emails.removePlayerEmail(args[1]);
                    sender.sendMessage(ChatColor.GREEN + LG.getString("answers.emailRemove") + " ");
                    return true;
                } else {
                    sender.sendMessage(msgUseHelp);
                    return true;
                }
            } else if (opt.equalsIgnoreCase("view")) {
                if (args.length == 1 && isPlayer && sender.hasPermission("Email.view")) {
                    String email = plugin.emails.getPlayerEmail(sender.getName());
                    if (email != null) {
                        sender.sendMessage(ChatColor.GREEN + LG.getString("answers.emailView") + " " + ChatColor.YELLOW + email);
                    } else {
                        sender.sendMessage(ChatColor.RED + LG.getString("answers.noEmailSet") + " ");
                    }
                    return true;
                } else if (args.length == 2 && sender.hasPermission("Email.view.others")) {
                    String email = plugin.emails.getPlayerEmail(args[1]);
                    if (email != null) {
                        sender.sendMessage(ChatColor.GREEN + LG.getString("answers.emailView") + " " + ChatColor.YELLOW + email);
                    } else {
                        sender.sendMessage(ChatColor.RED + LG.getString("answers.noEmailSetOthers") + " ");
                    }
                    return true;
                } else {
                    sender.sendMessage(msgUseHelp);
                    return true;
                }
            } else if (opt.equalsIgnoreCase("send")) {
                if (plugin.mailman == null) {
                    sender.sendMessage(ChatColor.RED + LG.getString("answers.emailSendingDisable") + " ");
                    return true;
                }
                if (!isPlayer) {
                    sender.sendMessage(ChatColor.RED + LG.getString("answers.sorryOnlyPlayers") + " ");
                    return true;
                }
                if (!(args.length == 1 || args.length == 2)) {
                    sender.sendMessage(msgUseHelp);
                    return true;
                }
                boolean allPlayers = args.length == 1;
                if ((allPlayers && sender.hasPermission("Email.send.all")) || (!allPlayers && sender.hasPermission("Email.send"))) {
                    //Get itemstack in hand, quit if it's not a written book
                    Player p = (Player) sender;
                    ItemStack hand = p.getItemInHand();
                    if (hand.getType() != Material.WRITTEN_BOOK) {
                        sender.sendMessage(ChatColor.RED + LG.getString("answers.book") + " ");
                        return true;
                    }

                    //Set appropriate recipient string
                    //If all players, then toEmail will be a CSV string (ex. email1@example.com,email2@example.com)
                    String toEmail = "";
                    if (allPlayers) {
                        String[] emailArray = plugin.emails.getAllPlayerEmails();
                        //adding every Emails to an String[] , this can be use to a list on the server. and for sending each message.
                        this.everyEmails = emailArray;
                        sender.sendMessage(ChatColor.GOLD + String.valueOf(everyEmails.length) + " Emails");
                        for (String email : emailArray) {
                            toEmail += "," + email;

                        }

                        toEmail = toEmail.substring(1);
                    } else {
                        this.everyEmails[0] = plugin.emails.getPlayerEmail(args[1]);
                    }

                    //Can't have that!
                    if (everyEmails.length == 0) {
                        sender.sendMessage(ChatColor.RED + LG.getString("answers.noEmailSetOthers") + " ");
                        return true;
                    }

                    //Get the book's metadata
                    BookMeta data = (BookMeta) hand.getItemMeta();

                    //The email's subject
                    String bookTitle = data.getTitle();

                    //The email's body
                    String bookContent = "";
                    for (String page : data.getPages()) {
                        bookContent += plugin.alter.IN_BETWEEN_PAGES + page;
                    }

                    //Remove the extra
                    bookContent = bookContent.substring(plugin.alter.IN_BETWEEN_PAGES.length());

                    String subject = plugin.alter.replaceVariables(plugin.alter.SUBJECT, p.getName(), bookTitle, bookContent);
                    String content = plugin.alter.replaceVariables(plugin.alter.CONTENT, p.getName(), bookTitle, bookContent);

                    //Send the email! :)
                    Bukkit.getScheduler().runTaskAsynchronously(plugin, new EmailTask(plugin.mailman, everyEmails, subject, content));
                    sender.sendMessage(ChatColor.GREEN + LG.getString("answers.sent") + " ");
                    return true;
                } else {
                    sender.sendMessage(msgUseHelp);
                    return true;
                }
            } else if (opt.equalsIgnoreCase("export")) {
                if (args.length == 2 && sender.hasPermission("Email.export")) {
                    if (args[1].equalsIgnoreCase("1")) {
                        plugin.emails.export(1);
                        sender.sendMessage(ChatColor.GREEN + LG.getString("answers.emailExport1") + " ");
                        return true;
                    } else if (args[1].equalsIgnoreCase("2")) {
                        plugin.emails.export(2);
                        sender.sendMessage(ChatColor.GREEN + LG.getString("answers.emailExport2") + " ");
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED + LG.getString("answers.emailExportException") + " ");
                    }
                } else {
                    sender.sendMessage(msgUseHelp);
                    return true;
                }
            } else if (opt.equalsIgnoreCase("reload")) {
                if (sender.hasPermission("Email.reload")) {
                    plugin.loadConfig();
                    sender.sendMessage(ChatColor.GREEN + LG.getString("answers.reload") + " ");
                    return true;
                } else {
                    sender.sendMessage(msgUseHelp);
                    return false;
                }
            } else if (opt.equalsIgnoreCase("info")) {
                PluginDescriptionFile pdf = plugin.getDescription();
                String name = ChatColor.YELLOW + pdf.getName() + ChatColor.AQUA;
                String version = ChatColor.YELLOW + pdf.getVersion() + ChatColor.AQUA;
                String author = ChatColor.YELLOW + pdf.getAuthors().get(0) + ChatColor.AQUA;
                sender.sendMessage(name + " version " + version + " by " + author + " is " + ChatColor.GREEN + "running.");
                return true;
            }
            sender.sendMessage(msgUseHelp);
            return true;
        }
        return false;
    }

}
