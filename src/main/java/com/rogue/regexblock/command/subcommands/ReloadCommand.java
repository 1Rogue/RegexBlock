/*
 * Copyright (C) 2013 Spencer Alderman
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
package com.rogue.regexblock.command.subcommands;

import com.rogue.regexblock.RegexBlock;
import static com.rogue.regexblock.RegexBlock._;
import com.rogue.regexblock.regex.RegexManager;
import org.bukkit.command.CommandSender;

/**
 *
 * @since
 * @author 1Rogue
 * @version
 */
public class ReloadCommand implements SubCommand {

    public boolean execute(CommandSender sender, String[] args) {
        RegexManager rm = RegexBlock.getPlugin().getManager();
        rm.reloadManager();
        rm.loadFromConfig();
        sender.sendMessage(_("&aRegexBlock v" + RegexBlock.getPlugin().getDescription().getVersion() +  " reloaded."));
        return true;
    }

    public String getName() {
        return "reload";
    }

    public String[] getHelp() {
        return new String[] {
            "/regexblock reload",
            "Reloads RegexBlock and grabs a fresh configuration file"
        };
    }

}
