package edu.colorado.fantasticfour.ship;

import edu.colorado.fantasticfour.location.Location;

public interface CaptainsQuartersBehavior {
    void setCaptainsQuarters(Location location);
    Location getCaptainsQuarters();
    boolean checkWithCaptainsQuarters(Location location);
}


