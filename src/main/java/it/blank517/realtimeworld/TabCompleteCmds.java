package it.blank517.realtimeworld;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class TabCompleteCmds implements TabCompleter {

    private final Main plugin;
    private final HashMap<Integer, ArrayList<String>> HINTS;

    TabCompleteCmds(Main plugin, HashMap<Integer, ArrayList<String>> HINTS) {
        this.plugin = plugin;
        this.HINTS = HINTS;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                      @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("realtimeworld")) {
            final int argsLength = args.length;
            if (argsLength <= 3) {
                if (argsLength == 3) {
                    List<World> worlds = plugin.getServer().getWorlds();
                    ArrayList<String> HINTS_3 = new ArrayList<>();
                    for (World world : worlds) {
                        HINTS_3.add(world.getName());
                    }
                    HINTS.put(3, HINTS_3);
                }
                final List<String> completions = new ArrayList<>();
                //copy matches of first argument from list (ex: if first arg is 'e' will return just 'enable')
                StringUtil.copyPartialMatches(args[argsLength - 1], HINTS.get(args.length), completions);
                Collections.sort(completions);
                return completions;
            }
        }
        return null;
    }
}