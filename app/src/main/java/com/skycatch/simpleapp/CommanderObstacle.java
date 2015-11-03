package main.java.com.skycatch.simpleapp;

import com.skycatch.GeoJsonPolygonFeature;

/**
 * Created by marthaelena on 11/3/15.
 */
public class CommanderObstacle {

    public GeoJsonPolygonFeature polygone;
    public int height;

    public CommanderObstacle() {}

    public static CommanderObstacle createObstacle(GeoJsonPolygonFeature figure, int height) {
        CommanderObstacle obstacle = new CommanderObstacle();
        obstacle.polygone = figure;
        obstacle.height = height;

        return obstacle;
    }

}
