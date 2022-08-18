package me.zato.booster.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class BoostTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("MARKET_BOOSTER");
            arguments.add("MOB_DROP_BOOSTER");
            arguments.add("INTEREST_BOOSTER");

            return arguments;
        }
        return null;
    }
}
