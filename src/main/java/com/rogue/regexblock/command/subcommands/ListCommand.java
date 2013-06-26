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
public class ListCommand implements SubCommand {

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 2) {
            BlockRegex br = RegexBlock.getPlugin().getManager().getRegex(args[1]);
            if (br != null) {
                sender.sendMessage(_("&eRegex '&9" + args[1] + "&e':"));
                sender.sendMessage(_("&ePattern: &9" + br.getPattern().toString()));
                sender.sendMessage(_("&eDenial Reason: &9" + br.getReason()));
            } else {
                sender.sendMessage(_("&eNo regex found by '&9" + args[1] + "&e'. Current regexes:"));
                StringBuilder sb = new StringBuilder("&9");
                for (BlockRegex rb : RegexBlock.getPlugin().getManager().getRegexes().values()) {
                    sb.append(rb.getName()).append("&e, &9");
                }
                sender.sendMessage(_(sb.substring(0, sb.length() - 4)));
            }
            return true;
        } else if (args.length == 1) {
            StringBuilder sb = new StringBuilder("&eCurrent regexes: &9");
            for (BlockRegex rb : RegexBlock.getPlugin().getManager().getRegexes().values()) {
                sb.append(rb.getName()).append("&e, &9");
            }
            sender.sendMessage(_(sb.substring(0, sb.length() - 4)));
            return true;
        }
        return false;
    }

    public String getName() {
        return "list";
    }

    public String[] getHelp() {
        return new String[] {
            "/regexblock list [regex-name]",
            "Lists all regexes, or information of a specific regex"
        };
    }
}
