package me.zato.booster.commands;

import me.zato.booster.boosts.*;
import me.zato.booster.config.Values;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoostCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String [] args){
        //variables
        Boost.Type type = Boost.Type.NONE;
        Boost.Cause cause = Boost.Cause.NONE;
        int duration = Values.getDefaultEventDuration();
        Player player = null;
        if(sender instanceof Player)
            player = (Player)sender;

        //argument validation
        if(args.length < 1 || args.length > 2) {
            //missing boosttype or invalid command
            if(player != null)
                player.sendMessage("Invalid arguments.");
            return true;
        }

        //type validation
        type = Boost.Type.valueOf(args[0]);
        if(type.equals(Boost.Type.NONE)) {
            //invalid boost type command
            if(player != null)
                player.sendMessage("Invalid boost type.");
            return true;
        }

        //boost dubble validation
        if((type.equals(Boost.Type.INTEREST_BOOSTER) && Boosts.containsInterestBoost())
                || (type.equals(Boost.Type.MARKET_BOOSTER) && Boosts.containsMarketBoost())
                || (type.equals(Boost.Type.MOB_DROP_BOOSTER) && Boosts.containsMobDropBoost())) {
            //invalid boost type command
            if(player != null)
                player.sendMessage("Boost is already running.");
            return true;
        }

        //duration validation
        if(args.length > 1)
            duration = Integer.parseInt(args[1]);
        if(duration <= 0){
            //invalid duration
            if(player != null)
                player.sendMessage("Invalid duration.");
            return true;
        }

        //cause validation
        if(player != null)
            cause = Boost.Cause.PLAYER_COMMAND;
        else
            cause = Boost.Cause.CONSOLE_COMMAND;

        //start event
        Boost boost = null;
        switch(type){
            case MARKET_BOOSTER:
                MarketBoost marketBoost = new MarketBoost();
                boost = new Boost(marketBoost, player, duration, type, cause);
                break;
            case INTEREST_BOOSTER:
                InterestBoost interestBoost = new InterestBoost();
                boost = new Boost(interestBoost, player, duration, type, cause);
                break;
            case MOB_DROP_BOOSTER:
                MobDropBoost mobDropBoost = new MobDropBoost();
                boost = new Boost(mobDropBoost, player, duration, type, cause);
                break;
        }

        boost.Initiate();
        return true;
    }
}
