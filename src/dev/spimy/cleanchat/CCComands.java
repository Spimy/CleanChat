package dev.spimy.cleanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class CCComands implements CommandExecutor {

	PluginDescriptionFile pdf = Main.getInstance().getDescription();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 0) {
				if (p.hasPermission("cc.help")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r &r &7------- &aCleanChat &7-------"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Version: &a" + pdf.getVersion()));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Author: &a" + pdf.getAuthors()));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&6/cleanchat reload: &aReloads the config file"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r &r &7-----------------------"));
				} else {
					String noperm = Main.getInstance().getConfig().getString("noPerm");
					noperm = noperm.replace("{prefix}", Main.getInstance().getConfig().getString("prefix"));
					noperm = noperm.replace("{player}", p.getName());
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
				}
			} else {
				switch (args[0]) {
				case "reload":
					if (p.hasPermission("cc.reload")) {
						Main.getInstance().reloadConfig();
						String reloadmsg = Main.getInstance().getConfig().getString("reloadMsg");
						reloadmsg = reloadmsg.replace("{player}", p.getName());
						reloadmsg = reloadmsg.replace("{prefix}", Main.getInstance().getConfig().getString("prefix"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadmsg));
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', reloadmsg));
					} else {
						String noperm = Main.getInstance().getConfig().getString("noPerm");
						noperm = noperm.replace("{prefix}", Main.getInstance().getConfig().getString("prefix"));
						noperm = noperm.replace("{player}", p.getName());
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
					}
					return true;
				default:
					if (p.hasPermission("cc.help")) {
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&r &r &7------- &aCleanChat &7-------"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Version: &a" + pdf.getVersion()));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Author: &a" + pdf.getAuthors()));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&6/cleanchat reload: &aReloads the config file"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r &r &7-----------------------"));
						return true;
					} else {
						String noperm = Main.getInstance().getConfig().getString("noPerm");
						noperm = noperm.replace("{prefix}", Main.getInstance().getConfig().getString("prefix"));
						noperm = noperm.replace("{player}", p.getName());
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
					}
				}
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&f[&aCleanChat&f] &7» &cOnly a Player can run this command!"));
		}

		return true;
	}

}
