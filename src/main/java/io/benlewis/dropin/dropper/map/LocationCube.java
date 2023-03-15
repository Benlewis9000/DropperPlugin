package io.benlewis.dropin.dropper.map;

import org.bukkit.Location;

public class LocationCube {

    // TODO Use 3 point vectors? Are locations expensive? What about worlds?
    private final Location point1;
    private final Location point2;

    public LocationCube(Location point1, Location point2){
        this.point1 = point1;
        this.point2 = point2;
    }

    public boolean isInBounds(Location location){
        double x1 = point1.getX();
        double y1 = point1.getY();
        double z1 = point1.getZ();

        double x2 = point2.getX();
        double y2 = point2.getY();
        double z2 = point2.getZ();

        double xLoc = location.getX();
        double yLoc = location.getY();
        double zLoc = location.getZ();

        // TODO timings test where this is broken down into parts that return false
        return ((x1 < xLoc && x2 > xLoc ) || (x1 > xLoc && x2 < xLoc))
                && ((y1 < yLoc && y2 > yLoc ) || (y1 > yLoc && y2 < yLoc))
                && ((z1 < zLoc && z2 > zLoc ) || (z1 > zLoc && z2 < zLoc));
    }

}
