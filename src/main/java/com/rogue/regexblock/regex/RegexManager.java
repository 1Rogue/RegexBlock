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
package com.rogue.regexblock.regex;

import com.rogue.regexblock.RegexBlock;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @since 1.0
 * @author 1Rogue
 * @version 1.0
 */
public class RegexManager {
    
    private RegexBlock plugin;
    private Map<String, BlockRegex> regexes = new HashMap();
    
    public RegexManager(RegexBlock p, String name, String reason, String regex) {
        plugin = p;
        regexes.put(name, new BlockRegex(name, reason, regex));
    }
    
    public RegexManager(RegexBlock p, BlockRegex... regex) {
        plugin = p;
        for (BlockRegex temp : regex) {
            regexes.put(temp.getName(), temp);
        }
    }
    
    public Map<String, BlockRegex> addRegex(BlockRegex... regex) {
        for (BlockRegex temp : regex) {
            regexes.put(temp.getName(), temp);
        }
        return regexes;
    }
    
    public Map<String, BlockRegex> addRegex(String name, String reason, String regex) {
        regexes.put(name, new BlockRegex(name, reason, regex));
        return regexes;
    }
    
    public Map<String, BlockRegex> getRegexes() {
        return regexes;
    }
    
    public BlockRegex getRegex(String name) {
        return regexes.get(name);
    }
    
    public Map<String, BlockRegex> remRegex(String name) {
        regexes.remove(name);
        return regexes;
    }

    public void loadFromConfig() {
        FileConfiguration regexFile = plugin.getConfig();
        ConfigurationSection stuff = regexFile.getConfigurationSection("regexes");
        Set<String> allregexes = stuff.getKeys(false);
        for (String name : allregexes) {
            this.addRegex(name, regexFile.getString("regexes." + name + ".reason"), regexFile.getString("regexes." + name + ".regex"));
        }
    }
}
