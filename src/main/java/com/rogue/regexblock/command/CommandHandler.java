/*
 * Copyright (C) 2013 AE97
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.rogue.regexblock.command;

import com.rogue.regexblock.command.subcommands.*;
import java.util.HashMap;
import java.util.Map;
import com.rogue.regexblock.RegexBlock;
import com.rogue.regexblock.regex.RegexBuild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import static com.rogue.regexblock.RegexBlock._;

/**
 * Adapted from TotalPermissions
 * http://dev.bukkit.org/server-mods/totalpermissions
 * 
 * @version 1.0
 * @author Lord_Ralex
 * @since 1.0
 */
public final class CommandHandler implements CommandExecutor {
    
    protected Map<String, RegexBuild> tempregexes = new HashMap<String, RegexBuild>();
    protected final Map<String, SubCommand> commands = new HashMap<String, SubCommand>();
    protected final RegexBlock plugin;

    public CommandHandler(RegexBlock p) {
        plugin = p;

        AddCommand add = new AddCommand();
        commands.put(add.getName().toLowerCase().trim(), add);
        RemoveCommand remove = new RemoveCommand();
        commands.put(remove.getName().toLowerCase().trim(), remove);
        HelpCommand help = new HelpCommand();
        commands.put(help.getName().toLowerCase().trim(), help);
        ListCommand list = new ListCommand();
        commands.put(list.getName().toLowerCase().trim(), list);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        String subCommand;
        if (args.length < 1) {
            args = new String[]{"help"};
        }
        subCommand = args[0];
        SubCommand executor = commands.get(subCommand.toLowerCase());
        if (executor == null) {
            sender.sendMessage(_("&eNo command found, use &9/regexblock help &efor command list"));
            return true;
        }
        if ((args.length > 1) && (args[1].equalsIgnoreCase("help"))) {
            sender.sendMessage(_("&cUsage: &e" + executor.getHelp()[0]));
            sender.sendMessage(_("&e" + executor.getHelp()[1]));
            return true;
        }
        int length = args.length - 1;
        if (length < 0) {
            length = 0;
        }
        String[] newArgs = new String[length];
        for (int i = 0; i < newArgs.length; i++) {
            newArgs[i] = args[i + 1];
        }
        if (sender.hasPermission("regexblock.cmd" + executor.getName())) {
            boolean success = executor.execute(sender, args);
            if (!success) {
                sender.sendMessage(executor.getHelp()[0]);
                sender.sendMessage(executor.getHelp()[1]);
            }
            return true;
        } else {
            sender.sendMessage(_("&cYou cannot use that command"));
        }
        return true;
    }

    /**
     * Gets the registered commands that may be used
     *
     * @return Map of registered sub commands
     *
     * @since 1.0
     */
    public Map<String, SubCommand> getCommandList() {
        return commands;
    }

    public Map<String,RegexBuild> getBuildMap() {
        return tempregexes;
    }
}
