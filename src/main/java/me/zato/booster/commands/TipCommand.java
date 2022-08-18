package me.zato.booster.commands;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.zato.booster.boosts.Boost;
import me.zato.booster.boosts.Boosts;
import me.zato.booster.config.Values;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.UUID;

public class TipCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String [] args){
        Player player = (Player)sender;

        //check if a boost is running
        if(Boosts.getAllBoosts().size() < 1){
            //there is no event running
            player.sendMessage("There is no event running.");
            return true;
        }

        //variables
        BigDecimal amount = BigDecimal.valueOf(Values.getDefaultTipAmount());
        Player target = null;
        if(sender instanceof Player)
            target = (Player)sender;

        //argument validation
        if(args.length < 1 || args.length > 2) {
            //invalid command
            player.sendMessage("Invalid arguments.");
            return true;
        }

        //target validation
        target = Bukkit.getPlayer(args[0]);
        if(target == null) {
            //invalid target
            player.sendMessage("Invalid player.");
            return true;
        }
        else{
            boolean isValid = false;
            for(Boost boost : Boosts.getAllBoosts())
                if(boost.hasAuthor())
                    if(boost.getAuthor() == target){
                        isValid = true;
                        break;
                    }
            if(!isValid){
                //invalid target
                player.sendMessage("Player is not hosting this boost.");
                return true;
            }
        }

        //amount validation
        if(args.length > 1)
            amount = BigDecimal.valueOf(Integer.parseInt(args[1]));
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            //invalid amount
            player.sendMessage("Invalid tip amount.");
            return true;
        }

        //check players
        if(!Economy.playerExists(player.getUniqueId())){
            //player doesnt exist
            player.sendMessage("You do not have an Economy.");
            return true;
        }

        //exists in essentials
        if(!Economy.playerExists(target.getUniqueId())){
            //target doesnt exist
            player.sendMessage("Event creator does not have an Economy.");
            return true;
        }

        //check balance player
        try {
            if(Economy.getMoneyExact(player.getUniqueId()).compareTo(BigDecimal.ZERO) < amount.compareTo(BigDecimal.ZERO)){
                //not enough balance
                player.sendMessage("You do not have enough balance.");
                return true;
            }
        } catch (UserDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        //remove balance player
        try {
            Economy.subtract(player.getUniqueId(), amount);
        } catch (NoLoanPermittedException e) {
            throw new RuntimeException(e);
        } catch (UserDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (MaxMoneyException e) {
            throw new RuntimeException(e);
        }

        //give balance target
        try {
            Economy.add(target.getUniqueId(), amount);
        } catch (NoLoanPermittedException e) {
            throw new RuntimeException(e);
        } catch (UserDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (MaxMoneyException e) {
            throw new RuntimeException(e);
        }

        //notify players
        //debug
        player.sendMessage("Tipped to " + target.getName());
        target.sendMessage("Tip received from " + player.getName());
        return true;
    }
}

