package me.hephaestus.breakcancel;

import me.hephaestus.breakcancel.Listener.BreakBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BreakCancel extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic

        //makes the class act like a config access point
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Register the event listener
        getServer().getPluginManager().registerEvents(new BreakBlockEvent(), this);
    }

}

