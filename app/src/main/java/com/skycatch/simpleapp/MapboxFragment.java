package main.java.com.skycatch.simpleapp;

import android.content.Context;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.annotations.SpriteFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;
import com.o3dr.services.android.lib.coordinate.LatLong;
import com.skycatch.Coordinate2D;
import com.skycatch.GeoJsonPolygonFeature;
import com.skycatch.MissionPlanner;
import com.skycatch.MissionPlannerResult;
import com.skycatch.Point2D;
import com.skycatch.Route;
import com.skycatch.Simplify;
import com.skycatch.Waypoint;
import com.skycatch.simpleapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marthaelena on 11/3/15.
 */
public class MapboxFragment  extends Fragment implements GestureOverlayView.OnGestureListener{

    final static double DEFAULT_LAT = 20.6737777;
    final static double DEFAULT_LNG = -103.4054535;
    final static int BLACK = Color.argb(255, 0, 0, 0);
    final static int GRAY = Color.argb(255, 109, 103, 103);
    final static int WHITE = Color.argb(255, 255, 255, 255);
    final static int YELLOW_ALPHA = Color.argb(100, 228, 169, 59);
    final static int YELLOW = Color.argb(255, 228, 169, 59);

    private MapView mapView;
    private MarkerOptions uav;
    private SpriteFactory spriteFactory;

    private GestureOverlayView mGestureOverlayView;

//    MapView.OnMarkerClickListener markerListener = new MapView.OnMarkerClickListener() {
//        @Override
//        public boolean onMarkerClick(Marker marker) {
//            return finishDrawing(marker.getPosition());
//        }
//    };

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

//        mapView.setOnMarkerClickListener(markerListener);

        spriteFactory = new SpriteFactory(mapView);

        mGestureOverlayView = (GestureOverlayView) view.findViewById(R.id.gesture_overlay);
        mGestureOverlayView.addOnGestureListener(this);
        mGestureOverlayView.setEnabled(false);

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
                    mapView.addMarker(marker);
                }
                routeLine.color(color);
                color = color == WHITE ? GRAY : WHITE;

                mapView.addPolyline(routeLine);
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

    public void enableDrawingMode(boolean value) {
        Toast.makeText(getContext(), "Drawing mode: ON", Toast.LENGTH_SHORT).show();
        mGestureOverlayView.setEnabled(value);
    }

    @Override
    public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public void onGesture(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
        mGestureOverlayView.setEnabled(false);

        ArrayList<Point2D> path = new ArrayList<>();
        ArrayList<Coordinate2D> polygonPoints = new ArrayList<>();
        PolylineOptions figure = new PolylineOptions();
        figure.color(Color.argb(255, 95, 166, 189));
        figure.width(5);

        float[] points = mGestureOverlayView.getGesture().getStrokes().get(0).points;
        for (int i = 0; i < points.length; i += 2) {
            path.add(new Point2D(points[i], points[i + 1]));
        }
        ArrayList<Point2D> simplifiedPoints = Simplify.simplifyPoints(path, 15.0f);
        for (Point2D point : simplifiedPoints) {
            PointF pointF = new PointF((float) point.getX(), (float) point.getY());
            LatLng coordinate = mapView.fromScreenLocation(pointF);
            figure.add(coordinate);
            polygonPoints.add(new Coordinate2D(coordinate.getLatitude(), coordinate.getLongitude()));
        }
        figure.add(figure.getPoints().get(0));
        mapView.addPolyline(figure);

        GeoJsonPolygonFeature bounds = GeoJsonPolygonFeature.createWithCoordinates(polygonPoints);
        Waypoint takeoff = Waypoint.createWithCoordinate(polygonPoints.get(0), 60.0, 0.0, "");
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
            MarkerOptions baseMarker = new MarkerOptions();
            baseMarker.icon(spriteFactory.fromDrawable(getResources().getDrawable(R.drawable.baseicon)));
            baseMarker.position(new LatLng(takeoff.coordinate().getLatitude(), takeoff.coordinate().getLongitude()));
            mapView.addMarker(baseMarker);
        } else {
            Log.v("ERROR", "!!!");
        }

    }

    @Override
    public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {

    }
}
