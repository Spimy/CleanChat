package dev.spimy.cleanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }
    
	public void onEnable() {
		getLogger().info("CleanChat Enabled");
		instance = this;
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new OnCurse(), this);
		pm.registerEvents(new OnAdvertise(), this);
		pm.registerEvents(new OnSpam(), this);
		getCommand("cleanchat").setExecutor(new CCComands());
		if (getConfig().getBoolean("check-updates") == true)
	    {
	      Updater updater = new Updater(this, 52281);
	      try
	      {
	        if (updater.checkForUpdates()) {
	          Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAn update was found! New version: &6" + updater.getLatestVersion() + "&c! Download Update: " + updater.getResourceURL()));
	        } else {
	          Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are running the latest version: " + getDescription().getVersion() + "!"));
	        }
	      }
	      catch (Exception e)
	      {
	        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCould not check for updates! Error (stacktrace):"));
	        e.printStackTrace();
	      }
	    }
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}

	public void onDisable() {
		getLogger().info("CleanChat Disabled");
	}
	
}
