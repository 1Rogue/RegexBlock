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
    
    /**
     * Creates a new RegexManager, then creates and adds a BlockRegex object
     * from the provided data
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @param p The RegexBlock plugin instance
     * @param name Name of the BlockRegex
     * @param reason The denial reason for when the regex is triggered
     * @param regex The pattern to match against for the regex
     */
    public RegexManager(RegexBlock p, String name, String reason, String regex) {
        plugin = p;
        regexes.put(name, new BlockRegex(name, reason, regex));
    }
    
    /**
     * Creates a new RegexManager, and adds any provided BlockRegex objects
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @param p The RegexBlock plugin instance
     * @param regex Any BlockRegex objects
     */
    public RegexManager(RegexBlock p, BlockRegex... regex) {
        plugin = p;
        for (BlockRegex temp : regex) {
            regexes.put(temp.getName(), temp);
        }
    }
    
    /**
     * Adds provided BlockRegex(es) to the regex Map
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @param regex Any BlockRegex objects
     * 
     * @return The updated map of regexes
     */
    public Map<String, BlockRegex> addRegex(BlockRegex... regex) {
        for (BlockRegex temp : regex) {
            regexes.put(temp.getName(), temp);
        }
        return regexes;
    }
    
    /**
     * Creates a new BlockRegex object and adds it to the current regexes
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @param name Name of the BlockRegex
     * @param reason The denial reason for when the regex is triggered
     * @param regex The pattern to match against for the regex
     * 
     * @return The updated map of regexes
     */
    public Map<String, BlockRegex> addRegex(String name, String reason, String regex) {
        regexes.put(name, new BlockRegex(name, reason, regex));
        return regexes;
    }
    
    /** 
     * Returns the full Map of all the regexes
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @return The Map of regexes
     */
    public Map<String, BlockRegex> getRegexes() {
        return regexes;
    }
    
    /**
     * Gets a BlockRegex by name
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @param name The name of the BlockRegex
     * @return The BlockRegex, null if provided name does not exist
     */
    public BlockRegex getRegex(String name) {
        return regexes.get(name);
    }
    
    /**
     * Removes a BlockRegex by its designated name
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @param name The regex's name
     * @return The updated map of regexes
     */
    public Map<String, BlockRegex> remRegex(String name) {
        regexes.remove(name);
        return regexes;
    }
    
    /**
     * Loads any possible regex configurations from the config file
     * 
     * @since 1.0
     * @version 1.0
     */
    public void loadFromConfig() {
        FileConfiguration regexFile = plugin.getConfig();
        ConfigurationSection stuff = regexFile.getConfigurationSection("regexes");
        Set<String> allregexes = stuff.getKeys(false);
        for (String name : allregexes) {
            this.addRegex(name, regexFile.getString("regexes." + name + ".reason"), regexFile.getString("regexes." + name + ".regex"));
        }
    }
}
