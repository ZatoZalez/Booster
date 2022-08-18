package me.zato.booster.listeners.custom;

import me.zato.booster.Booster;
import me.zato.booster.boosts.Boost;
import me.zato.booster.events.BoosterStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class BoostStart implements Listener {
    public BoostStart(Booster plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBoostStart(BoosterStartEvent e) {
        switch(e.getBoost().getType()){
            case INTEREST_BOOSTER:
                onInterestBoostStart(e.getBoost());
                break;
            case MARKET_BOOSTER:
                onMarketBoostStart(e.getBoost());
                break;
            case MOB_DROP_BOOSTER:
                onMobDropBoostStart(e.getBoost());
                break;
        }
    }

    private static void onInterestBoostStart(Boost boost){
        //check if boost is valid
        if(!boost.isInterestBoost()){
            //not valid
            return;
        }

        //check if event is enabled
        if(!me.zato.booster.config.Values.isInterestBoosterEnabled()){
            //event is disabled
            return;
        }

        //check if bankplus has config
        if(me.pulsi_.bankplus.BankPlus.getInstance().getConfig() == null){
            //no config available
            return;
        }

        //set new interest duration
        me.pulsi_.bankplus.BankPlus.getInstance().getConfig().set("Interest.Delay", boost.getInterestBoost().getInterestDelay());

        //set new interest amount
        me.pulsi_.bankplus.BankPlus.getInstance().getConfig().set("Interest.Money-Given", boost.getInterestBoost().getInterestAmount());

        //reload information
        me.pulsi_.bankplus.BankPlus.getInstance().saveConfig();
        me.pulsi_.bankplus.BankPlus.getInstance().reloadConfigs();
        me.pulsi_.bankplus.values.Values.CONFIG.setupValues();

        //debug
        Bukkit.broadcastMessage("Interest Booster Started");
        boost.Start();
    }

    private static void onMarketBoostStart(Boost boost){
        //check if boost is valid
        if(!boost.isMarketBoost()){
            //not valid
            return;
        }

        //check if event is enabled
        if(!me.zato.booster.config.Values.isMarketBoosterEnabled()){
            //event is disabled
            return;
        }

        Bukkit.broadcastMessage("Market Booster Started");
        boost.Start();
    }

    private static void onMobDropBoostStart(Boost boost){
        //check if boost is valid
        if(!boost.isMobDropBoost()){
            //not valid
            return;
        }

        //check if event is enabled
        if(!me.zato.booster.config.Values.isMobDropBoosterEnabled()){
            //event is disabled
            return;
        }

        Bukkit.broadcastMessage("MobDrop Booster Started");
        boost.Start();
    }
}
