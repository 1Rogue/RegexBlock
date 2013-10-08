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

import java.util.regex.Pattern;

/**
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class BlockRegex {
    
    private final Pattern blockPattern;
    private final String reason;
    private final String name;
    
    public BlockRegex(String regexName, String blocked, String pattern) {
        reason = blocked;
        name = regexName;
        blockPattern = Pattern.compile(pattern);
    }
    
    public Pattern getPattern() {
        return blockPattern;
    }
    
    public String getName() {
        return name;
    }
    
    public String getReason() {
        return reason;
    }

}
