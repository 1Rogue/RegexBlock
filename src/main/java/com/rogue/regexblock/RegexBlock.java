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
package com.rogue.regexblock;

import com.rogue.regexblock.command.CommandHandler;
import com.rogue.regexblock.listener.RBListener;
import com.rogue.regexblock.metrics.Metrics;
import com.rogue.regexblock.regex.RegexManager;
import com.rogue.regexblock.runnable.UpdateRunnable;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @since 1.0
 * @author 1Rogue
 * @version 1.0
 * 
 */
public class RegexBlock extends JavaPlugin {

    protected RBListener listener;
    protected File config;
    protected CommandHandler commands;
    protected RegexManager regexMan;

    /**
     * Loads configuration
     *
     * @since 1.0
     * @version 1.0
     */
    @Override
    public void onLoad() {
        
        this.getLogger().log(Level.INFO, "Loading config...");
        config = new File(getDataFolder() + File.separator + "config.yml");

        try {
            Metrics metrics = new Metrics(this);
            getLogger().info("Enabling Metrics...");
            metrics.start();
        } catch (IOException ex) {
            Logger.getLogger(RegexBlock.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!config.exists()) {
            this.getLogger().info("Generating first time config.yml...");
            this.getConfig().addDefault("update-check", true);
            this.getConfig().addDefault("regexes.ips.regex", ".*[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+.*");
            this.getConfig().addDefault("regexes.ips.reason", "&cYou are not allowed to use IPs in chat");
            this.getConfig().addDefault("regexes.urls.regex", ".*[0-9a-zA-Z]+\\.[0-9a-zA-Z]+\\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}).*");
            this.getConfig().addDefault("regexes.urls.reason", "&cYou are not allowed to use URLs in chat");
            this.getConfig().options().copyDefaults(true);
            this.saveConfig();
        }
        
        this.getLogger().log(Level.INFO, "{0} is loaded!", this.getName());
    }

    /**
     * Enables the regex manager, command system, listener, and plugin metrics.
     *
     * @since 1.0
     * @version 1.0
     */
    @Override
    public void onEnable() {
        try {
            Metrics metrics = new Metrics(this);
            getLogger().info("Enabling Metrics...");
            metrics.start();
        } catch (IOException ex) {
            Logger.getLogger(RegexBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (this.getConfig().getBoolean("update-check")) {
            Bukkit.getScheduler().runTaskLater(this, new UpdateRunnable(this), 1);
        }
        
        
        this.getLogger().log(Level.INFO, "Enabling commands...");
        commands = new CommandHandler(this);
        getCommand("regexblock").setExecutor(commands);
        this.getLogger().log(Level.INFO, "Enabling regex manager...");
        regexMan = new RegexManager(this);
        regexMan.loadFromConfig();
        this.getLogger().log(Level.INFO, "Enabling listener...");
        listener = new RBListener(this);
        Bukkit.getPluginManager().registerEvents(listener, this);
        this.getLogger().log(Level.INFO, "{0} is enabled!", this.getName());
    }

    /**
     * No use yet.
     *
     * @since 1.0
     * @version 1.0
     */
    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "{0} is disabled!", this.getName());
    }

    /**
     * Gets the instance of the plugin in its entirety.
     *
     * @since 1.0
     * @version 1.0
     *
     * @return The plugin instance
     */
    public static RegexBlock getPlugin() {
        return (RegexBlock) Bukkit.getServer().getPluginManager().getPlugin("RegexBlock");
    }

    /**
     * Gets the plugin's listener
     *
     * @since 1.0
     * @version 1.0
     *
     * @return The plugin's listener
     */
    public RBListener getListener() {
        return listener;
    }
    
    /**
     * Gets the plugin's regex manager
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @return The plugin's regex manager
     */
    public RegexManager getManager() {
        return regexMan;
    }
    
    /**
     * Gets the handler for commands of the plugin
     * 
     * @since 1.0
     * @version 1.0
     * 
     * @return The command handler
     */
    public CommandHandler getCommandHandler() {
        return commands;
    }
    
    /**
     * Converts pre-made strings to have chat colors in them
     * 
     * @param encoded String with unconverted color codes
     * @return string with correct chat colors included
     */
    public static String _(String encoded) {
        return ChatColor.translateAlternateColorCodes('&', encoded);
    }
}
