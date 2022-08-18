package me.zato.booster.commands;

import me.zato.booster.boosts.Boost;
import me.zato.booster.boosts.Boosts;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String [] args) {
        //variables
        Player player = (Player)sender;
        if (player == null)
            return true;

        //do something
        for(Boost boost : Boosts.getAllBoosts()){
            player.sendMessage("Currently Running boost: " + boost.getType());
        }
        return true;
    }
}
