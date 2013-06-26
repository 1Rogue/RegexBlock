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
import com.rogue.regexblock.regex.BlockRegex;
import org.bukkit.command.CommandSender;
import static com.rogue.regexblock.RegexBlock._;

/**
 *
 * @since 1.0
 * @author 1Rogue
 * @version 1.0
 */
public class RemoveCommand implements SubCommand {

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 2) {
            if ((RegexBlock.getPlugin().getManager().getRegexes().size() - RegexBlock.getPlugin().getManager().remRegex(args[1]).size()) == 1) {
                sender.sendMessage(_("&aRegex removed"));
            } else {
                sender.sendMessage(_("&eNo regex found by '&9" + args[1] + "&e'. Current regexes:"));
                StringBuilder sb = new StringBuilder();
                for (BlockRegex rb : RegexBlock.getPlugin().getManager().getRegexes().values()) {
                    sb.append(rb.getName()).append("&e, &9");
                }
                sender.sendMessage(_("&9" + sb.substring(0, sb.length() - 4)));
            }
            return true;
        }
        return false;
    }

    public String getName() {
        return "remove";
    }

    public String[] getHelp() {
        return new String[] {
            "/regexblock remove <regex-name>",
            "Removes a regex of the provided name from being checked"
        };
    }
}
