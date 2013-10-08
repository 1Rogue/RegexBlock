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
package com.rogue.regexblock.command.subcommands;

import org.bukkit.command.CommandSender;

/**
 * Adapted from TotalPermissions
 * http://dev.bukkit.org/server-mods/totalpermissions
 * 
 * @since 1.0.0
 * @author Lord_Ralex
 * @version 1.0.0
 */
public interface SubCommand {

    /**
     * Executes the command. Only the args and sender are needed.
     *
     * @return Success of the command
     */
    public abstract boolean execute(CommandSender sender, String[] args);

    /**
     * Returns the command's name. When used, it is the /ttp [name].
     *
     * @return Name of the command
     */
    public abstract String getName();

    /**
     * Returns a String array of help statements. Index 0 is the command, and
     * index 1 is what the command does.
     *
     * @return Help information
     */
    public abstract String[] getHelp();
}
