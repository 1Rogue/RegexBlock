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

/**
 * Used as a temporary class in building a regex via commands.
 * 
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class RegexBuild {
    
    private String blockPattern = "";
    private String reason = "";
    private String name = "";
    
    public String setPattern(String pattern) {
        blockPattern = pattern;
        return blockPattern;
    }
    
    public String setName(String regexName) {
        name = regexName;
        return name;
    }
    
    public String setReason(String regexReason) {
        reason = regexReason;
        return reason;
    }
            
    public String getPattern() {
        return blockPattern;
    }
    
    public String getName() {
        return name;
    }
    
    public String getReason() {
        return reason;
    }

}
