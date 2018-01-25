package dev.spimy.cleanchat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnAdvertise implements Listener {

	@EventHandler
	public boolean onAdvertise(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (Main.getInstance().getConfig().getBoolean("allowAdvertise") == false) {
			if (!p.hasPermission("cc.advertise")) {
				Pattern pattern = Pattern.compile(
						"(?i)(((([a-zA-Z0-9-]+\\.)+(gs|ts|adv|no|uk|us|de|eu|com|net|noip|to|gs|me|info|biz|tv|au))+(\\:[0-9]{2,5})?))");
				Pattern pattern2 = Pattern.compile("(?i)(((([0-9]{1,3}\\.){3}[0-9]{1,3})(\\:[0-9]{2,5})?))");
				Pattern pattern3 = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:[0-9]{1,5})?");
				Pattern pattern4 = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}(:[0-9]{1,5})?");

				Matcher matcher = pattern.matcher(e.getMessage());
				Matcher matcher2 = pattern2.matcher(e.getMessage());
				Matcher matcher3 = pattern3.matcher(e.getMessage());
				Matcher matcher4 = pattern4.matcher(e.getMessage());

				if (matcher.find() || matcher2.find() || matcher3.find() || matcher4.find()) {
					e.setCancelled(true);
					String noAdMsg = Main.getInstance().getConfig().getString("noAdMsg");
					noAdMsg = noAdMsg.replace("{prefix}", Main.getInstance().getConfig().getString("prefix"));
					noAdMsg = noAdMsg.replace("{player}", p.getName());
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', noAdMsg));

				}
			} else {
				e.setCancelled(false);
			}
		}
		return true;

	}

}
