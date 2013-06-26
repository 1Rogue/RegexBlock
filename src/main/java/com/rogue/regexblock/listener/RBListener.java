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
package com.rogue.regexblock.listener;

import com.rogue.regexblock.RegexBlock;
import com.rogue.regexblock.regex.BlockRegex;
import java.util.regex.Pattern;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @since 1.0
 * @author 1Rogue
 * @version 1.0
 */
public class RBListener implements Listener {

    protected final RegexBlock plugin;

    public RBListener(RegexBlock p) {
        plugin = p;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("regexblock.bypass.*.chat")) {
            for (BlockRegex temp : plugin.getManager().getRegexes().values()) {
                if (temp.getPattern().matcher(event.getMessage()).matches()
                && (!(event.getPlayer().hasPermission("regexblock.bypass." + temp.getName() + "chat")
                || event.getPlayer().hasPermission("regexblock.bypass." + temp.getName())))) {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', temp.getReason()));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocessPermCheck(PlayerCommandPreprocessEvent event) {
        if (!event.getPlayer().hasPermission("regexblock.bypass.*.command")) {
            for (BlockRegex temp : plugin.getManager().getRegexes().values()) {
                if (temp.getPattern().matcher(event.getMessage()).matches()
                && (!(event.getPlayer().hasPermission("regexblock.bypass." + temp.getName() + "command")
                || event.getPlayer().hasPermission("regexblock.bypass." + temp.getName())))) {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', temp.getReason()));
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    
}