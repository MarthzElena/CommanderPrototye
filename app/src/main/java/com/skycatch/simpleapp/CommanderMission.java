package main.java.com.skycatch.simpleapp;

import com.o3dr.services.android.lib.drone.mission.item.MissionItem;
import com.skycatch.Coordinate2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marthaelena on 11/3/15.
 */
public class CommanderMission {

    public String id;
    public String name;
    public ArrayList<CommanderZone> zones;
    public ArrayList<CommanderObstacle> obstacles;
    public Coordinate2D location;
    public String resolution;
    public double altAboveGrnd;
    public List<MissionItem> missionItems;
    //TODO: public UAVProfile uavProfile;

    public CommanderMission() {
        this.zones = new ArrayList<>();
    }


}
