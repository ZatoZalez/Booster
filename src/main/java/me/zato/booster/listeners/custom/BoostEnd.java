package me.zato.booster.listeners.custom;

import me.pulsi_.bankplus.values.Values;
import me.zato.booster.Booster;
import me.zato.booster.boosts.Boost;
import me.zato.booster.boosts.Boosts;
import me.zato.booster.events.BoosterEndEvent;
import me.zato.booster.events.BoosterStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BoostEnd implements Listener {
    public BoostEnd(Booster plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBoostEnd(BoosterEndEvent e) {
        switch(e.getBoost().getType()){
            case INTEREST_BOOSTER:
                onInterestBoostEnd(e.getBoost());
                break;
            case MARKET_BOOSTER:
                onMarketBoostStart(e.getBoost());
                break;
            case MOB_DROP_BOOSTER:
                onMobDropBoostStart(e.getBoost());
                break;
        }

        Boosts.removeBoost(e.getBoost());
    }

    private static void onInterestBoostEnd(Boost boost) {
        //check if boost is valid
        if(!boost.isInterestBoost()){
            //not valid
            return;
        }

        //check if bankplus has config
        if(me.pulsi_.bankplus.BankPlus.getInstance().getConfig() == null){
            //no config available
            return;
        }

        //set default interest duration of 10 min;
        me.pulsi_.bankplus.BankPlus.getInstance().getConfig().set("Interest.Delay", boost.getInterestBoost().getDefaultInterestDelay());

        //set default interest amount of 10%;
        me.pulsi_.bankplus.BankPlus.getInstance().getConfig().set("Interest.Money-Given", boost.getInterestBoost().getDefaultInterestAmount());

        //reload information
        me.pulsi_.bankplus.BankPlus.getInstance().saveConfig();
        me.pulsi_.bankplus.BankPlus.getInstance().reloadConfigs();
        Values.CONFIG.setupValues();

        //debug
        Bukkit.broadcastMessage("Default interest delay: " + Values.CONFIG.getInterestDelay());
        Bukkit.broadcastMessage("Default interest: " + Values.CONFIG.getInterestMoneyGiven());

        Bukkit.broadcastMessage("Interest Booster Stopped");
    }

    private static void onMarketBoostStart(Boost boost){
        //check if boost is valid
        if(!boost.isMarketBoost()){
            //not valid
            return;
        }

        Bukkit.broadcastMessage("Market Booster Stopped");
    }

    private static void onMobDropBoostStart(Boost boost){
        //check if boost is valid
        if(!boost.isMobDropBoost()){
            //not valid
            return;
        }

        Bukkit.broadcastMessage("MobDrop Booster Stopped");
    }
}
