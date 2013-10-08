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
import com.rogue.regexblock.regex.RegexBuild;
import static com.rogue.regexblock.RegexBlock._;
import java.util.Map;
import org.bukkit.command.CommandSender;

/**
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class AddCommand implements SubCommand {

    public boolean execute(CommandSender sender, String[] args) {
        Map<String, RegexBuild> rbm = RegexBlock.getPlugin().getCommandHandler().getBuildMap();
        if (rbm.get(sender.getName()) == null) {
            RegexBlock.getPlugin().getCommandHandler().getBuildMap().put(sender.getName(), new RegexBuild());
            sender.sendMessage(_("&aNew Regex instance started."));
        }
        RegexBuild rb = RegexBlock.getPlugin().getCommandHandler().getBuildMap().get(sender.getName());
        switch (args.length) {
            case 1:
                sender.sendMessage(_("&e>> "));
                sender.sendMessage(_("&e>> &7Current Regex Name: &9" + rb.getName()));
                sender.sendMessage(_("&e>> &7Current Pattern: &9" + rb.getPattern()));
                sender.sendMessage(_("&e>> &7Current Denial Reason: &9" + rb.getReason()));
                sender.sendMessage(_("&e>> "));
                if (!rb.getName().equals("")) {
                    if (!rb.getReason().equals("")) {
                        if (!rb.getPattern().equals("")) {
                            RegexBlock.getPlugin().getManager().addRegex(rb.getName(), rb.getReason(), rb.getPattern());
                            RegexBlock.getPlugin().getCommandHandler().getBuildMap().remove(sender.getName());
                            sender.sendMessage(_("&aNew Regex Pattern added to filter!"));
                        } else {
                            sender.sendMessage(_("&cPlease add a pattern using &e/regexblock add pattern &9<pattern>"));
                        }
                    } else {
                        sender.sendMessage(_("&cPlease add a denial reason using &e/regexblock add reason &9<reason>"));
                    }
                } else {
                    sender.sendMessage(_("&cPlease add a regex name using &e/regexblock add name &9<regex-name>"));
                }
                return true;
            case 2:
                return false;
            default:
                StringBuilder sb = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                if (args[1].equalsIgnoreCase("pattern")) {
                    rbm.get(sender.getName()).setPattern(sb.substring(0, sb.length() - 1));
                    sender.sendMessage(_("&aNew regex pattern set"));
                    return true;
                } else if (args[1].equalsIgnoreCase("name")) {
                    rbm.get(sender.getName()).setName(sb.substring(0, sb.length() - 1));
                    sender.sendMessage(_("&aNew regex name set"));
                    return true;
                } else if (args[1].equalsIgnoreCase("reason")) {
                    rbm.get(sender.getName()).setReason(sb.substring(0, sb.length() - 1));
                    sender.sendMessage(_("&aNew regex reason set"));
                    return true;
                }
                return false;
        }
    }

    public String getName() {
        return "add";
    }

    public String[] getHelp() {
        return new String[]{
            "/regexblock add <pattern|name|reason> <content>",
            "Command for adding new regexes to the filter"
        };
    }
}
