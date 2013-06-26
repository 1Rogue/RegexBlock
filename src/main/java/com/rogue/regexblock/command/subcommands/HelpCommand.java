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
import java.util.Arrays;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import static com.rogue.regexblock.RegexBlock._;

/**
 *
 * @since 1.0
 * @author 1Rogue
 * @version 1.0
 */
public class HelpCommand implements SubCommand {
    
    @Override
    public boolean execute(CommandSender cs, String[] args) {
        if (args.length == 1) {
            args = new String[]{"help", "1"};
        }
        int page = this.getInt(args[1]);
        Map commands = RegexBlock.getPlugin().getCommandHandler().getCommandList();
        cs.sendMessage(_(getPage(page, commands)));
        return true;
    }

    @Override
    public String getName() {
        return "help";
    }

    public String[] getHelp() {
        return new String[]{
            "/regexblock help",
            "Displays help information"
        };
    }

    private String getPage(int page, Map<String, Object> map) {
        int factor = 5;
        int index = (page - 1) * factor;
        int listSize = map.size();
        if (index > listSize) {
            return "";
        }
        int upper = index + factor;
        if (upper >= listSize) {
            upper = listSize;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&e").append(this.formatTitle(RegexBlock.getPlugin().getName(), ChatColor.YELLOW, ChatColor.RED)).append("\n");
        sb.append("&ePage &9").append(page).append(" &eof &9").append((int) Math.ceil((double) listSize / (double) factor)).append("\n").append(ChatColor.RESET);
        String[] list = map.keySet().toArray(new String[listSize]);
        Arrays.sort(list);
        for (int i = index; i < upper; i++) {
            Object test = map.get(list[i]);
            if (test != null) {
                if (test instanceof SubCommand) {
                    SubCommand db = (SubCommand) map.get(list[i]);
                    sb.append("&e").append(db.getHelp()[0]).append(" &9- &f").append(db.getHelp()[1]);
                }
                if (i != upper - 1) {
                    sb.append("\n");
                }
            }
        }
        sb.append('\n').append("&eUse &9/regexblock <command> help &efor help with a command");
        return sb.toString();
    }

    private int getInt(String test) {
        int page;
        try {
            page = Integer.parseInt(test);
            if (page < 1) {
                page = 1;
            }
        } catch (NumberFormatException e) {
            page = 1;
        }
        return page;
    }
    
    private String formatTitle(String title, ChatColor barcolor, ChatColor titlecolor) {
        String BAR = "--------------------------------------------------------";
        return StringUtils.center("[" + titlecolor + title + barcolor + "]", BAR.length(), '-');
    }
}