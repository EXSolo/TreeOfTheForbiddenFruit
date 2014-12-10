package com.exsoloscript.treeoftheforbiddenfruit.util;

import org.bukkit.Location;

import java.util.Random;

/**
 * Created by Stefan on 05.12.2014.
 */
public class LocationUtils {

    public static Location randomLocationRelativeTo(Location old, int radius) {
        if (old != null) {
            Location newLoc = old.clone();
            Random r = new Random();

            // get random values
            int x = r.nextInt(radius);
            int z = r.nextInt(radius);

            // randomly negate values
            if (r.nextBoolean())
                x = x * -1;

            if (r.nextBoolean())
                z = z * -1;

            // Append new values
            newLoc.setX(newLoc.getX() + x);
            newLoc.setZ(newLoc.getZ() + z);

            newLoc.setY(newLoc.getWorld().getHighestBlockYAt(newLoc) + 1);

            return newLoc;
        }

        return null;
    }

    public static String toString(Location l) {
        if (l != null) {
            return "x: " + (int) l.getX() + " y: " + (int) l.getY() + " z: " + (int) + l.getZ() + " ";
        } else return null;
    }
}
