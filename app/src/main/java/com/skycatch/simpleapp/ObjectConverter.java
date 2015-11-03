package main.java.com.skycatch.simpleapp;

import com.cocoahero.android.geojson.Point;
import com.cocoahero.android.geojson.Polygon;
import com.cocoahero.android.geojson.Position;
import com.skycatch.Coordinate2D;
import com.skycatch.GeoJsonPointFeature;
import com.skycatch.GeoJsonPolygonFeature;
import com.skycatch.Route;
import com.skycatch.Waypoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marthaelena on 11/3/15.
 */
public class ObjectConverter {

    /**
     * This methods create the converted object as it handles JSONObject
     * returns the converted object if everything went smooth and null if exception occurred
     */
    public static CommanderMission createObjectConverter(String missionJSON) {
        CommanderMission commanderMission = new CommanderMission();


        try {
            JSONObject jsonObject = new JSONObject(missionJSON);
            JSONObject JSONmission = jsonObject.getJSONObject("mission");
            JSONArray JSONzones = JSONmission.getJSONArray("zones");
            JSONArray JSONObstacles = JSONmission.getJSONArray("obstacles");

            /** set mission properties **/
            commanderMission.id = JSONmission.getString("id");
            commanderMission.name = JSONmission.getString("name");
            JSONObject JSONlocation = JSONmission.getJSONObject("location");
            commanderMission.location = new Coordinate2D(
                    JSONlocation.getDouble("lat"),
                    JSONlocation.getDouble("lng")
            );
            commanderMission.resolution = JSONmission.getString("resolution");
            commanderMission.altAboveGrnd = JSONmission.getDouble("altAboveGrnd");

            /** Get zondes data **/
            commanderMission.zones = getZones(JSONzones);

            /** Get obstacles data **/
            commanderMission.obstacles = getObstacles(JSONObstacles);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return commanderMission;
    }

    public static ArrayList<CommanderObstacle> getObstacles(JSONArray obstaclesArray) {
        ArrayList<CommanderObstacle> obstaclesList = new ArrayList<>();
        try {
            ArrayList<CommanderObstacle> obstacles = new ArrayList<>();
            for (int i=0; i<obstaclesArray.length(); i++) {
                JSONObject currentObstacle = obstaclesArray.getJSONObject(i);

                Polygon obstaclePolygon = new Polygon(currentObstacle.getJSONObject("data").getJSONObject("geometry"));
                ArrayList<Coordinate2D> obstacleSKYcoordinates = new ArrayList<>();
                List<Position> obstacleCoordinates = obstaclePolygon.getRings().get(0).getPositions();
                for (Position position : obstacleCoordinates) {
                    obstacleSKYcoordinates.add(new Coordinate2D(position.getLatitude(), position.getLongitude()));
                }
                GeoJsonPolygonFeature skyObstacle = GeoJsonPolygonFeature.createWithCoordinates(obstacleSKYcoordinates);
                obstacles.add(CommanderObstacle.createObstacle(skyObstacle, currentObstacle.getInt("height")));
            }
            obstaclesList = obstacles;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obstaclesList;
    }

    public static ArrayList<CommanderZone> getZones(JSONArray zones){
        GeoJsonPolygonFeature basePolygon;
        ArrayList<CommanderZone> zonesList = new ArrayList<>();

        try {
            for (int i=0; i<zones.length(); i++) {
                ArrayList<Route> skyRoutes = new ArrayList<>();
                /** Going through the ZONES **/
                JSONObject currentZone = zones.getJSONObject(i);
                JSONArray JSONroutes = currentZone.getJSONArray("routes");

                for (int j=0; j<JSONroutes.length(); j++) {
                    /** Going through the ROUTES **/
                    ArrayList<com.skycatch.Waypoint> skyWaypoints = new ArrayList<>();
                    JSONArray JSONwaypoints = JSONroutes.getJSONObject(j).getJSONArray("waypoints");

                    for (int k=0; k<JSONwaypoints.length(); k++) {
                        /** Going through the WAYPOINTS **/
                        JSONObject current = JSONwaypoints.getJSONObject(k);
                        Point waypointPoint = new Point(current.getJSONObject("data").getJSONObject("geometry"));

                        Waypoint currentSkyWaypoint = Waypoint.createWithCoordinate(
                                new Coordinate2D(waypointPoint.getPosition().getLatitude(), waypointPoint.getPosition().getLongitude()),
                                current.getDouble("altitude"),
                                current.getDouble("elevation"),
                                current.getString("id")
                        );

                        skyWaypoints.add(currentSkyWaypoint);
                    }
                    Route route = Route.createWithWaypoints(skyWaypoints);
                    skyRoutes.add(route);
                }

                /** Get polygon data **/
                basePolygon = getBasePolygon(currentZone);

                /** Get base coordinates **/
                Point basePoint = new Point(currentZone.getJSONObject("base").getJSONObject("data").getJSONObject("geometry"));

                CommanderZone zone = CommanderZone.createZone(
                        skyRoutes,
                        basePolygon,
                        CommanderZone.ZoneBase.createZoneBase(
                                currentZone.getJSONObject("base").getDouble("altitude"),
                                GeoJsonPointFeature.createWithCoordinate(
                                        new Coordinate2D(
                                                basePoint.getPosition().getLatitude(),
                                                basePoint.getPosition().getLongitude()
                                        )
                                )
                        )
                );
                zonesList.add(zone);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return zonesList;
    }

    public static GeoJsonPolygonFeature getBasePolygon(JSONObject currentZone) {
        try {
            Polygon polygon = new Polygon(currentZone.getJSONObject("data").getJSONObject("geometry"));
            ArrayList<Coordinate2D> polygonSKYcoordinates = new ArrayList<>();
            List<Position> polygonCoordinates = polygon.getRings().get(0).getPositions();
            for (Position position : polygonCoordinates) {
                polygonSKYcoordinates.add(new Coordinate2D(position.getLatitude(), position.getLongitude()));
            }
            return GeoJsonPolygonFeature.createWithCoordinates(polygonSKYcoordinates);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}

