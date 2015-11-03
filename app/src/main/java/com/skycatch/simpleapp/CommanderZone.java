package main.java.com.skycatch.simpleapp;

import com.skycatch.GeoJsonPointFeature;
import com.skycatch.GeoJsonPolygonFeature;
import com.skycatch.Route;

import java.util.ArrayList;

/**
 * Created by marthaelena on 11/3/15.
 */
public class CommanderZone {

    public ArrayList<Route> routes;
    public GeoJsonPolygonFeature polygonData;
    public ZoneBase base;

    public CommanderZone() {}

    public static CommanderZone createZone(ArrayList<Route> routes, GeoJsonPolygonFeature polygon, ZoneBase home) {
        CommanderZone zone = new CommanderZone();
        zone.routes = routes;
        zone.polygonData = polygon;
        zone.base = home;

        return zone;
    }


    public static class ZoneBase {

        public ZoneBase() {}

        public static ZoneBase createZoneBase(double altitude, GeoJsonPointFeature data) {
            ZoneBase base = new ZoneBase();
            base.altitude = altitude;
            base.point = data;

            return base;
        }

        public double altitude;
        public GeoJsonPointFeature point;
    }

}
