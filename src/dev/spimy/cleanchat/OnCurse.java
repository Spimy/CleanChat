package dev.spimy.cleanchat;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class OnCurse implements Listener {
	
	public static List<String> wordlist = Main.getInstance().getConfig().getStringList("BannedWords");

	@EventHandler
	public boolean onCurse(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		for (String cursewords : Main.getInstance().getConfig().getStringList("BannedWords")) {
			if (Main.getInstance().getConfig().getBoolean("allowSwearing") == false) {
				if (!p.hasPermission("cc.swear")) {
					if (e.getMessage().toLowerCase().contains(cursewords)) {
						e.setCancelled(true);
						String noCurseMsg = Main.getInstance().getConfig().getString("noCurseMsg");
						noCurseMsg = noCurseMsg.replace("{prefix}", Main.getInstance().getConfig().getString("prefix"));
						noCurseMsg = noCurseMsg.replace("{player}", p.getName());
						noCurseMsg = noCurseMsg.replace("{bannedword}", cursewords);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', noCurseMsg));
					}
				} else {
					e.setCancelled(false);
				}
			}
		}
		return true;
	}

}
