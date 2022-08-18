package me.zato.booster;

import me.zato.booster.commands.*;
import me.zato.booster.config.Utility;
import me.zato.booster.listeners.base.MobDeath;
import me.zato.booster.listeners.base.UserBalanceUpdate;
import me.zato.booster.listeners.custom.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Booster extends JavaPlugin {

    private static Booster plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        initializePlugin();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Utility.Save();
    }

    private void initializePlugin() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage(getDescription().getFullName() + " by " +
                ChatColor.RED + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));

        new BoostEnd(this);
        new BoostStart(this);
        new UserBalanceUpdate(this);
        new MobDeath(this);
        getCommand("tip").setExecutor(new TipCommand());
        getCommand("tip").setTabCompleter(new TipTab());
        getCommand("boost").setExecutor(new BoostCommand());
        getCommand("boost").setTabCompleter(new BoostTab());

        //debug
        getCommand("debug").setExecutor(new Debug());
        Utility.Read();
    }

    public static Booster getPlugin(){
        return plugin;
    }
}
