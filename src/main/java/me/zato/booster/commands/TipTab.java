package me.zato.booster.commands;

import me.zato.booster.boosts.Boost;
import me.zato.booster.boosts.Boosts;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TipTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            for(Boost boost : Boosts.getAllBoosts())
                if(boost.hasAuthor())
                    arguments.add(boost.getAuthor().getName());
            return arguments;
        }
        return null;
    }
}
