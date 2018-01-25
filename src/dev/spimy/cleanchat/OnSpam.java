package dev.spimy.cleanchat;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnSpam implements Listener {

	private HashMap<Player, Integer> cooldownTime = new HashMap<>();
	private HashMap<Player, BukkitRunnable> cooldownTask = new HashMap<>();

	@EventHandler
	public boolean onSpam(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		int cooldownSeconds = Main.getInstance().getConfig().getInt("cooldownSeconds");
		
		if (Main.getInstance().getConfig().getBoolean("allowSpamming") == false) {
			if (!p.hasPermission("cc.spam")) {
				if (cooldownTime.containsKey(p)) {
					e.setCancelled(true);
					String noSpamMsg = Main.getInstance().getConfig().getString("noSpamMsg");
					noSpamMsg = noSpamMsg.replace("{prefix}", Main.getInstance().getConfig().getString("prefix"));
					noSpamMsg = noSpamMsg.replace("{player}", p.getName());
					noSpamMsg = noSpamMsg.replace("{cooldown}", cooldownTime.get(p).toString());
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', noSpamMsg));
				    return true;
				}

                cooldownTime.put(p, cooldownSeconds);
                cooldownTask.put(p, new BukkitRunnable() {
                        public void run() {
                                cooldownTime.put(p, cooldownTime.get(p) - 1);
                                if (cooldownTime.get(p) == 0) {
                                        cooldownTime.remove(p);
                                        cooldownTask.remove(p);
                                        cancel();
                                }
                        }
                });
               
                cooldownTask.get(p).runTaskTimer(Main.getInstance(), 20, 20);
                
                return true;

			} else {
				e.setCancelled(false);
			}
		}
		return true;
	}

}
