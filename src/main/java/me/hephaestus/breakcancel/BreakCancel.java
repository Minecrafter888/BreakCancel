package me.hephaestus.breakcancel;

import me.hephaestus.breakcancel.Listener.BreakBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BreakCancel extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Register the event listener
        getServer().getPluginManager().registerEvents(new BreakBlockEvent(), this);
    }

}

