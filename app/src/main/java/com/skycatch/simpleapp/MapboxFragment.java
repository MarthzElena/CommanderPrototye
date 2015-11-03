package main.java.com.skycatch.simpleapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.annotations.SpriteFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;
import com.skycatch.Coordinate2D;
import com.skycatch.GeoJsonPolygonFeature;
import com.skycatch.MissionPlanner;
import com.skycatch.MissionPlannerResult;
import com.skycatch.Route;
import com.skycatch.Waypoint;
import com.skycatch.simpleapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marthaelena on 11/3/15.
 */
public class MapboxFragment  extends Fragment {

    final static double DEFAULT_LAT = 20.6737777;
    final static double DEFAULT_LNG = -103.4054535;
    final static int BLACK = Color.argb(255, 0, 0, 0);
    final static int GRAY = Color.argb(255, 109, 103, 103);
    final static int WHITE = Color.argb(255, 255, 255, 255);
    final static int YELLOW_ALPHA = Color.argb(100, 228, 169, 59);
    final static int YELLOW = Color.argb(255, 228, 169, 59);

    private MapView mapView;
    private MarkerOptions uav;
    private ArrayList<Coordinate2D> polygonPoints = new ArrayList<>();
    private PolylineOptions drawing = new PolylineOptions();
    private PolylineOptions finalLine = new PolylineOptions();
    private SpriteFactory spriteFactory;
    private List<Marker> mapMarkers;

    MapView.OnMapClickListener clickListener = new MapView.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng point) {
            startDrawing(point);
        }
    };

    MapView.OnMarkerClickListener markerListener = new MapView.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            return finishDrawing(marker.getPosition());
        }
    };

    public MapboxFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapbox, container, false);

        mapView = (MapView) view.findViewById(R.id.mapbox_view);
        mapView.setAccessToken("pk.eyJ1IjoibWFydGhhbG9lcmEiLCJhIjoiY2lmYTZibmtiMG1vd3I3bHh3d2NuendzeSJ9.4p68TnK-kAOq5bSAmcRMZQ");
        mapView.onCreate(savedInstanceState);
        mapView.setMyLocationEnabled(true);
        mapView.setRotateEnabled(false);

        //config map
        mapView.setStyleUrl(Style.SATELLITE);
        Location myLocation = mapView.getMyLocation();
        if (myLocation != null) {
            mapView.setCenterCoordinate(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
        } else {
            mapView.setCenterCoordinate(new LatLng(DEFAULT_LAT, DEFAULT_LNG));
        }
        mapView.setZoomLevel(12);

        mapView.setOnMapClickListener(clickListener);
        mapView.setOnMarkerClickListener(markerListener);

        spriteFactory = new SpriteFactory(mapView);

        Toast.makeText(getContext(), "Tap on the map to start drawing", Toast.LENGTH_SHORT).show();

        return view;
    }

    public void moveMaptoZone(LatLng center){
        mapView.setZoomLevel(14);
        mapView.setCenterCoordinate(center);
    }

    public void drawMission(CommanderMission mission) {
        clearMission();
        moveMaptoZone(new LatLng(mission.location.getLatitude(), mission.location.getLongitude()));

        ArrayList<MarkerOptions> markersList= new ArrayList<>();
        int markerCount = 0;

        /** Paint the zones **/
        for (CommanderZone zone : mission.zones) {
            PolygonOptions zonepolygoneFill = new PolygonOptions();
            zonepolygoneFill.fillColor(Color.argb(80, 0, 0, 0));
            PolylineOptions zonepolygoneStroke = new PolylineOptions();
            zonepolygoneStroke.width(5);
            zonepolygoneStroke.color(Color.argb(255, 0, 0, 0));

            GeoJsonPolygonFeature currentZone = zone.polygonData;
            for (Coordinate2D coordinate : currentZone.coordinates()) {
                LatLng point = new LatLng(coordinate.getLatitude(), coordinate.getLongitude());
                zonepolygoneFill.add(point);
                zonepolygoneStroke.add(point);
            }
            mapView.addPolygon(zonepolygoneFill);
            mapView.addPolyline(zonepolygoneStroke);


            /** Paint base data **/
            CommanderZone.ZoneBase currentBase = zone.base;
            LatLng baseLocation = new LatLng(currentBase.point.coordinate().getLatitude(), currentBase.point.coordinate().getLongitude());
            MarkerOptions baseMarker = new MarkerOptions();
            baseMarker.icon(spriteFactory.fromDrawable(getResources().getDrawable(R.drawable.baseicon)));
            baseMarker.position(baseLocation);
            mapView.addMarker(baseMarker);

            /** Paint the routes **/
            int color = WHITE;
            for (Route route : zone.routes) {
                PolylineOptions routeLine = new PolylineOptions();
                routeLine.width(3);

                ArrayList<Waypoint> currentWaypoints = route.waypoints();
                for (Waypoint waypoint : currentWaypoints) {
                    LatLng point = new LatLng(waypoint.coordinate().getLatitude(), waypoint.coordinate().getLongitude());
                    routeLine.add(point);

                    MarkerOptions marker = new MarkerOptions();
                    marker.position(point);
                    if (waypoint.equals(currentWaypoints.get(0)) || waypoint.equals(currentWaypoints.get(currentWaypoints.size()-1))) {
                        marker.icon(spriteFactory.fromDrawable(customizedMarker(R.drawable.green_marker, String.valueOf(markerCount))));
                    } else {
                        marker.icon(spriteFactory.fromDrawable(customizedMarker(R.drawable.gray_circle, String.valueOf(markerCount))));
                    }
                    markerCount++;
                    markersList.add(marker);
                }
                routeLine.color(color);
                color = color == WHITE ? GRAY : WHITE;

                mapView.addPolyline(routeLine);
                mapMarkers = mapView.addMarkers(markersList);
            }

        }

        /** Paint the obstacles **/
        for (CommanderObstacle obstacle : mission.obstacles) {
            PolygonOptions obstacleFill = new PolygonOptions();
            obstacleFill.fillColor(YELLOW_ALPHA);
            PolylineOptions obstacleStroke = new PolylineOptions();
            obstacleStroke.width(5);
            obstacleStroke.color(YELLOW);

            GeoJsonPolygonFeature currentObstacle = obstacle.polygone;
            for (Coordinate2D coordinate : currentObstacle.coordinates()) {
                LatLng point = new LatLng(coordinate.getLatitude(), coordinate.getLongitude());
                obstacleFill.add(point);
                obstacleStroke.add(point);
            }
            mapView.addPolygon(obstacleFill);
            mapView.addPolyline(obstacleStroke);
        }
    }

    public void clearMission() {
        //remove previous missions
        mapView.removeAllAnnotations();
        polygonPoints = new ArrayList<>();
        drawing = new PolylineOptions();
        finalLine = new PolylineOptions();
        if (mapMarkers != null) {
            mapView.removeAnnotations(mapMarkers);
        }
    }

    public Drawable customizedMarker(int markerImageId, String number) {
        Drawable markerImage = getResources().getDrawable(markerImageId);
        if (markerImage != null) {
            Bitmap canvasBitmap = Bitmap.createBitmap(markerImage.getIntrinsicWidth(), markerImage.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas imageCanvas = new Canvas(canvasBitmap);

            Paint imagePaint = new Paint();
            imagePaint.setTextAlign(Paint.Align.CENTER);
            imagePaint.setTextSize(12f);

            markerImage.draw(imageCanvas);
            imageCanvas.drawText(number, markerImage.getIntrinsicWidth() / 2, markerImage.getMinimumHeight() / 2, imagePaint);

            return new LayerDrawable(new Drawable[]{markerImage, new BitmapDrawable(canvasBitmap)});
        } else {
            return null;
        }
    }

    public void startDrawing(LatLng point) {
        if (drawing.getPoints().isEmpty()) {
            Toast.makeText(getContext(), "Tap on the first marker to finish drawing", Toast.LENGTH_SHORT).show();
        }

        Coordinate2D coordinate2D = new Coordinate2D(point.getLatitude(), point.getLongitude());
        polygonPoints.add(coordinate2D);

        MarkerOptions marker = new MarkerOptions()
                .position(point)
                .icon(spriteFactory.fromDrawable(getResources().getDrawable(R.drawable.green_marker)));
        mapView.addMarker(marker);

        drawing.add(point);
        drawing.width(5);
        mapView.addPolyline(drawing);
    }

    public boolean finishDrawing(LatLng lastMarkerPosition) {
        if (lastMarkerPosition.getLatitude() == polygonPoints.get(0).getLatitude()
                && lastMarkerPosition.getLongitude() == polygonPoints.get(0).getLongitude()) {

            finalLine = new PolylineOptions();
            finalLine.width(5);
            finalLine.add(drawing.getPoints().get(drawing.getPoints().size()-1));
            finalLine.add(lastMarkerPosition);
            mapView.addPolyline(finalLine);
            mapView.refreshDrawableState();

            GeoJsonPolygonFeature bounds = GeoJsonPolygonFeature.createWithCoordinates(polygonPoints);
            Waypoint takeoff = Waypoint.createWithCoordinate(polygonPoints.get(1), 60.0, 0.0, "");
            double legAngle = 360.0;
            double overlap = 60.0;
            String resolution = "5 cm";
            boolean terrain = false;
            String uavId = "skc-bd-sky1";

            MissionPlanner planner = MissionPlanner.create();
            MissionPlannerResult result = planner.plan(bounds, legAngle, overlap, resolution, takeoff, terrain, uavId);
            int color = WHITE;
            if (result.errorCode() == MissionPlannerResult.ERROR_CODE_SUCCESS) {
                for (Route route : result.routes()) {
                    PolylineOptions routeLine = new PolylineOptions();

                    for (Waypoint waypoint : route.waypoints() ) {
                        LatLng point = new LatLng(waypoint.coordinate().getLatitude(), waypoint.coordinate().getLongitude());
                        routeLine.add(point);
                    }
                    routeLine.width(3);
                    routeLine.color(color);
                    color = color == WHITE ? GRAY : WHITE;
                    mapView.addPolyline(routeLine);
                }
            } else {
                Log.v("ERROR", "!!!");
            }

            polygonPoints = new ArrayList<>();
            drawing = new PolylineOptions();
            finalLine = new PolylineOptions();
            return true;
        } else {
            return false;
        }
    }

}
