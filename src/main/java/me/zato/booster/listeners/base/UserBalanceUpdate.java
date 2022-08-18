package me.zato.booster.listeners.base;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.zato.booster.Booster;
import me.zato.booster.boosts.Boost;
import me.zato.booster.boosts.Boosts;
import net.ess3.api.MaxMoneyException;
import net.ess3.api.events.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.math.BigDecimal;

public class UserBalanceUpdate implements Listener {
    public UserBalanceUpdate(Booster plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onUserBalanceUpdate(UserBalanceUpdateEvent e) throws MaxMoneyException, UserDoesNotExistException, NoLoanPermittedException {
        if(!e.getCause().equals(UserBalanceUpdateEvent.Cause.COMMAND_SELL))
            return;

        Boost boost = Boosts.getMarketBoost();
        if(boost == null)
            return;

        Player player = e.getPlayer();
        if(player == null)
            return;

        //get multiplier
        BigDecimal multiplier = new BigDecimal(boost.getMarketBoost().getMultiplier());
        BigDecimal amount = e.getNewBalance().subtract(e.getOldBalance());
        player.sendMessage("Profit: " + amount);
        amount = amount.multiply(multiplier).subtract(amount);
        player.sendMessage("Profit due to multiplier of " + boost.getMarketBoost().getMultiplier() + ": " + amount);

        Economy.add(player.getUniqueId(), amount);
        return;
    }
}
