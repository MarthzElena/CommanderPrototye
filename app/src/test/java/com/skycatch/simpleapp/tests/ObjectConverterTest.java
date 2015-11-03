package com.skycatch.simpleapp.tests;

import android.test.AndroidTestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import main.java.com.skycatch.simpleapp.CommanderObstacle;
import main.java.com.skycatch.simpleapp.CommanderZone;
import main.java.com.skycatch.simpleapp.ObjectConverter;

/**
 * Created by marthaelena on 11/3/15.
 */
public class ObjectConverterTest extends AndroidTestCase {

    final static String OBSTACLE_STRING = "{\"obstacles\": [{\"data\": {\"geometry\": {\"type\": \"Polygon\",\"coordinates\": [[[-122.01204657554626,37.336248013042045],[-122.01205730438232,37.33583855322181],[-122.01143503189085,37.33583855322181],[-122.01145648956299,37.33622242186866],[-122.01204657554626,37.336248013042045]]]}},\"height\": 0}]}";
    final static double MIN_DELTA = 1e-9;

    public void testObjectConverter() {
        JSONArray zonesArray = new JSONArray();
        JSONArray obstaclesArray = new JSONArray();

        try {
            JSONObject zonesObject = new JSONObject(ZONE_STRING);
            zonesArray = zonesObject.getJSONArray("zones");

            JSONObject obstacleObject = new JSONObject(OBSTACLE_STRING);
            obstaclesArray = obstacleObject.getJSONArray("obstacles");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<CommanderZone> zonesList = ObjectConverter.getZones(zonesArray);
        ArrayList<CommanderObstacle> obstaclesList = ObjectConverter.getObstacles(obstaclesArray);

        /** TEST **/
        double waypointAltitude1 = 40;
        assertEquals(zonesList.get(0).routes.get(0).waypoints().get(0).altitude(), waypointAltitude1, MIN_DELTA);
        double obstacleLatitude1 = 37.336248013042045;
        assertEquals(obstaclesList.get(0).polygone.coordinates().get(0).getLatitude(), obstacleLatitude1, MIN_DELTA);
    }

    static {
        System.loadLibrary("skycatch_jni");
    }

    final static String ZONE_STRING = "{\"zones\": [\n" +
            "      {\n" +
            "        \"routes\": [\n" +
            "          {\n" +
            "            \"waypointTree\": null,\n" +
            "            \"zoneId\": \"4d678445-c8f1-61f1-32d6-76e608d0ca16\",\n" +
            "            \"waypoints\": [\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"1\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9627595907035054,\n" +
            "                      6.443010516477293\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"e5892a16-589a-39c0-68e0-6023fde48a6a\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"2\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9627595907035054,\n" +
            "                      6.443610940308766\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"bb65497a-21fa-09a3-bf63-5130374740d8\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"3\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.962547950603255,\n" +
            "                      6.443773925052485\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"fc05b9fc-179d-46a1-65ad-11c4563173f1\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"4\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.962547950603255,\n" +
            "                      6.442843771635591\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"cf44c71d-b57b-d3a1-271b-69d3356c9985\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"5\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9623363105030043,\n" +
            "                      6.443159226508613\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"63d8210c-96a0-81d7-bdd2-1ff4a7117818\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"6\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9623363105030043,\n" +
            "                      6.443936909796204\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"5846331e-432d-3966-a04f-8bbc9ee2378d\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"7\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9621246704027535,\n" +
            "                      6.444099894539922\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"984c47f0-b306-a45e-85a4-71b01e64863f\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"8\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9621246704027535,\n" +
            "                      6.443474681381636\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"f16ef632-b7e7-72fd-c94e-f32615b514ab\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"9\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9619130303025036,\n" +
            "                      6.443790136254657\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"a9734719-915c-b117-89df-688acd3a27ae\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"10\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9619130303025036,\n" +
            "                      6.444262879283641\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"d93a3dd6-51b7-37a5-1d61-34fdfacb53cd\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"11\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.961701390202253,\n" +
            "                      6.44442586402736\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"55156414-9000-fb88-6c0d-40c9ea183822\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"12\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.961701390202253,\n" +
            "                      6.443265985638135\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"ab035cbb-2966-c757-7cc5-b17d940942b2\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"13\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9614897501020025,\n" +
            "                      6.443245956753942\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"7d36a38d-f708-826e-633a-a2ce63d0f02a\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"14\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9614897501020025,\n" +
            "                      6.444588848771078\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"c9811d80-e470-d03d-670e-554e17de0cdc\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"15\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.961278110001752,\n" +
            "                      6.444751833514797\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"b519aa47-c654-5ef0-a1b0-b3be5c1f40c5\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"16\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.961278110001752,\n" +
            "                      6.443225927869749\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"cbfe2cf5-8765-d7c4-7377-627ee5e854c8\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"17\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.961066469901501,\n" +
            "                      6.443205898985555\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"5d3041f3-e20d-c1ba-581c-b954b2551182\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"18\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.961066469901501,\n" +
            "                      6.4448328139348945\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"42ec4e39-44e7-dac7-7e76-d6e4d0c2fa7a\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"19\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9608548298012507,\n" +
            "                      6.444846649630741\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"35da0495-c4d7-5fa4-269f-2ca2843c460f\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"20\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9608548298012507,\n" +
            "                      6.443185870101362\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"58d3eff0-d18f-6774-657f-7308b95ced2d\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"21\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9608287811279244,\n" +
            "                      6.443183404945369\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"6a54e958-7110-3512-d8d7-04fb0182333b\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"22\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9608548298012507,\n" +
            "                      6.442707136307212\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"25c4e050-4cc3-8b80-061e-fb45d754c338\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"23\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9608548298012507,\n" +
            "                      6.442204663045307\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"6d4d3acb-e34c-82d4-6da0-febe87cc67bd\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"24\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960643189701,\n" +
            "                      6.442220595142744\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"66921b46-f84c-8028-457f-b9d4e4360653\",\n" +
            "                \"routeIndex\": 1\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"25\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960643189701,\n" +
            "                      6.443279082617828\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"a873d527-6f19-cd62-2aac-966962db857b\",\n" +
            "                \"obstacleId\": null,\n" +
            "                \"height\": 0,\n" +
            "                \"isInsideObstacle\": false,\n" +
            "                \"routeIndex\": 1\n" +
            "              }\n" +
            "            ],\n" +
            "            \"id\": \"180cc1e1-db4b-f190-6834-40cd02c106c2\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"waypointTree\": null,\n" +
            "            \"zoneId\": \"4d678445-c8f1-61f1-32d6-76e608d0ca16\",\n" +
            "            \"waypoints\": [\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"26\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960643189701,\n" +
            "                      6.443279082617828\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"f41e1abf-fb3b-1cb5-18e4-11de683d7d20\",\n" +
            "                \"obstacleId\": null,\n" +
            "                \"height\": 0,\n" +
            "                \"isInsideObstacle\": false,\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"27\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960643189701,\n" +
            "                      6.444860485326588\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"a06ddf42-267e-3b90-0277-a98535d3014d\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"28\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960431549600749,\n" +
            "                      6.444874321022435\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"abe57ac1-b438-e1d5-791a-634dda671d93\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"29\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960431549600749,\n" +
            "                      6.442236527240181\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"2340ada3-37bc-8a8d-25eb-b3188ebe7ae9\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"30\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960219909500499,\n" +
            "                      6.442252459337618\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"ad63b45a-4a5f-2070-a496-c5603c87224f\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"31\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960219909500499,\n" +
            "                      6.444888156718282\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"6899d12e-928a-ecd8-fd20-c4e85153d9d6\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"32\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960008269400248,\n" +
            "                      6.4449019924141275\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"34536998-fdb5-9ed2-f008-7f21e837f49b\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"33\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.960008269400248,\n" +
            "                      6.442268391435055\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"bc7c2893-d505-f14f-848c-7e8b863131de\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"34\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9597966292999973,\n" +
            "                      6.442284323532492\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"464fddaa-ce63-fd85-85ed-9f9574de174b\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"35\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9597966292999973,\n" +
            "                      6.444915828109974\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"a4612e16-fead-c903-775a-1bf93bd4b4ca\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"36\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.959584989199746,\n" +
            "                      6.444929663805821\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"fb41edaa-e02b-55dc-4a34-31f7168c05e7\",\n" +
            "                \"routeIndex\": 2\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"37\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9595849891997457,\n" +
            "                      6.4425977130771805\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"b976f653-bcaf-30f6-819d-acee85ec42fb\",\n" +
            "                \"obstacleId\": null,\n" +
            "                \"height\": 0,\n" +
            "                \"isInsideObstacle\": false,\n" +
            "                \"routeIndex\": 2\n" +
            "              }\n" +
            "            ],\n" +
            "            \"id\": \"8eb20ee9-4560-135a-ffc2-f6aed9204f9d\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"waypointTree\": null,\n" +
            "            \"zoneId\": \"4d678445-c8f1-61f1-32d6-76e608d0ca16\",\n" +
            "            \"waypoints\": [\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"38\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9595849891997457,\n" +
            "                      6.4425977130771805\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"5f32a963-f4ba-3285-567a-9252630658d5\",\n" +
            "                \"obstacleId\": null,\n" +
            "                \"height\": 0,\n" +
            "                \"isInsideObstacle\": false,\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"39\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.959584989199746,\n" +
            "                      6.442300255629929\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"84c4bca8-24b6-be1b-1bb3-0d37bf41f790\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"40\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.959373349099496,\n" +
            "                      6.442293147268576\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"f47d7fd1-5511-5678-0ef7-0fec63866bf5\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"41\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.959373349099496,\n" +
            "                      6.444943499501668\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"7ca75775-b491-545d-dfa3-c13f8098067a\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"42\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.959161708999245,\n" +
            "                      6.444957335197515\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"47cde9de-4524-cdcf-1bf7-c6c1349f4d00\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"43\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.959161708999245,\n" +
            "                      6.4422565727121555\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"247a1d9b-24a3-582f-c846-4a56ac0d9009\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"44\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9589500688989943,\n" +
            "                      6.4422199981557355\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"89330d8a-b30b-f473-acab-36ba8f6d7de4\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"45\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9589500688989943,\n" +
            "                      6.4449711708933615\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"976d0178-9b39-48e5-1b1a-d53a82607fe9\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"46\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9587384287987444,\n" +
            "                      6.444985006589207\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"cffc6978-5ac8-cef4-bdd1-eebd776ea6f2\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"47\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9587384287987444,\n" +
            "                      6.442183423599315\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"08d7c450-3cce-aa86-fa4e-106d21b3716f\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"48\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9585267886984936,\n" +
            "                      6.442146849042894\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"20f7d357-a273-798e-3bb5-4737b40f8c09\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"49\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9585267886984936,\n" +
            "                      6.444998842285054\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"d36726c8-9419-8369-55dc-30afdcd11532\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"50\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9583151485982433,\n" +
            "                      6.445012677980901\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"043c3f9d-44a0-4e40-3261-413590a2838c\",\n" +
            "                \"routeIndex\": 3\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"51\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9583151485982433,\n" +
            "                      6.444840310823174\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"f5f2fe2c-8291-063b-f346-adca2bb98f4e\",\n" +
            "                \"obstacleId\": null,\n" +
            "                \"height\": 0,\n" +
            "                \"isInsideObstacle\": false,\n" +
            "                \"routeIndex\": 3\n" +
            "              }\n" +
            "            ],\n" +
            "            \"id\": \"f38bcf26-a954-f315-5f9b-b97a28b94885\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"waypointTree\": null,\n" +
            "            \"zoneId\": \"4d678445-c8f1-61f1-32d6-76e608d0ca16\",\n" +
            "            \"waypoints\": [\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"52\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9583151485982433,\n" +
            "                      6.444840310823174\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"925a929f-a295-c25c-d842-2dd4e4978f7d\",\n" +
            "                \"obstacleId\": null,\n" +
            "                \"height\": 0,\n" +
            "                \"isInsideObstacle\": false,\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"53\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9583151485982433,\n" +
            "                      6.442130858100675\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"1499d738-cfba-5921-8b54-ee9d9bca6e71\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"54\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9581035084979925,\n" +
            "                      6.442120843636696\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"856a6dee-74f8-30ce-6cb2-6de90e058263\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"55\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9581035084979925,\n" +
            "                      6.445026513676748\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"1c65f545-4975-d1c0-b4b5-5d7ab823cac1\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"56\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9578918683977418,\n" +
            "                      6.44499136308893\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"17965e3c-ec45-7d92-21bc-09540360b5d1\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"57\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9578918683977418,\n" +
            "                      6.442831706998123\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"46bbb85f-4647-cf3b-40af-de350a916d70\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"58\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9576802282974914,\n" +
            "                      6.442863252511943\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"a908ef8b-1e3a-1ad3-40fb-953b533ac7fa\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"59\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9576802282974914,\n" +
            "                      6.443356522433279\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"6051bfd2-f2bb-a31f-d419-259e983e68fd\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"60\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9577066898345894,\n" +
            "                      6.443375304145547\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"7498c632-f58f-d3f8-281e-ab3519cfe00c\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"61\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9576802282974914,\n" +
            "                      6.44339553058791\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"9441b791-af17-b862-3647-ef3c42b6b005\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"62\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9576802282974914,\n" +
            "                      6.444652541928805\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"1280236d-0bed-f795-234d-7d33c7a0a931\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"63\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9574685881972407,\n" +
            "                      6.44431372076868\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"4ff9a18a-3b4c-6f19-a30e-0294c0746a8d\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"64\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9574685881972407,\n" +
            "                      6.443557302229278\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"8b8841c0-e6e3-59a0-8245-f04eb95a82c8\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"65\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.95725694809699,\n" +
            "                      6.443719073870646\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"14ee363f-8ed4-6604-1503-f55b54e1422e\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"66\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.95725694809699,\n" +
            "                      6.443974899608554\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"1b1eed19-be33-ed24-f1cb-a8772fa7521b\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"67\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9577066898345894,\n" +
            "                      6.443375304145547\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"b6b65cca-4d10-9667-df75-2ba662ced486\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"68\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9574685881972407,\n" +
            "                      6.443206305782887\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"10c279d4-55b4-2cdc-861c-545f5fb741d8\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"69\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9574685881972407,\n" +
            "                      6.442894798025763\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"75258603-8784-7796-8bbc-93ae2f35ef67\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"70\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.95725694809699,\n" +
            "                      6.442926343539583\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"153e5681-7746-7be0-9b10-22f4e781c90b\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"71\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.95725694809699,\n" +
            "                      6.443056089132494\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"a46ad8db-3d55-d42f-1711-c9ae431cb584\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"72\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9579641819000195,\n" +
            "                      6.442820928480334\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"292e77b4-b2ac-eed6-551b-354f995c1c32\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"73\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9578918683977418,\n" +
            "                      6.442685198882999\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"f3ad0a17-e2ec-0c2e-0e32-c8d6d3db215a\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"74\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9578918683977418,\n" +
            "                      6.442110829172718\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"2af57c73-f73b-7f96-1a78-52aa0854a2d6\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"75\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9576802282974914,\n" +
            "                      6.4421008147087395\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"7e3e5f70-c4db-925b-fb42-2a9f0f72dab6\",\n" +
            "                \"routeIndex\": 4\n" +
            "              },\n" +
            "              {\n" +
            "                \"type\": \"waypoint\",\n" +
            "                \"altitude\": 40,\n" +
            "                \"elevation\": 0,\n" +
            "                \"data\": {\n" +
            "                  \"type\": \"Feature\",\n" +
            "                  \"properties\": {\n" +
            "                    \"marker\": {\n" +
            "                      \"icon\": \"76\"\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"geometry\": {\n" +
            "                    \"type\": \"Point\",\n" +
            "                    \"coordinates\": [\n" +
            "                      3.9576802282974914,\n" +
            "                      6.442287958745515\n" +
            "                    ]\n" +
            "                  }\n" +
            "                },\n" +
            "                \"id\": \"c2733b95-8d85-3d8a-543b-6448de1c339e\",\n" +
            "                \"routeIndex\": 4\n" +
            "              }\n" +
            "            ],\n" +
            "            \"id\": \"9eff19c5-1c64-920f-e441-021cced4d7ac\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"data\": {\n" +
            "          \"type\": \"Feature\",\n" +
            "          \"properties\": {\n" +
            "            \"style\": {\n" +
            "              \"color\": \"black\"\n" +
            "            },\n" +
            "            \"circumference\": 2127.4155198547587,\n" +
            "            \"area\": 140528.65267103986\n" +
            "          },\n" +
            "          \"geometry\": {\n" +
            "            \"type\": \"Polygon\",\n" +
            "            \"coordinates\": [\n" +
            "              [\n" +
            "                [\n" +
            "                  3.9579212665557812,\n" +
            "                  6.44503842750631\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9571487903594917,\n" +
            "                  6.443801746552719\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9577066898345894,\n" +
            "                  6.443375304145547\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9571058750152535,\n" +
            "                  6.442948861379927\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9579641819000195,\n" +
            "                  6.442820928480334\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9575779438018746,\n" +
            "                  6.442095974773417\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.958479166030879,\n" +
            "                  6.442138619137779\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9594662189483594,\n" +
            "                  6.442309196559439\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.960882425308227,\n" +
            "                  6.4422025856776175\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9608287811279244,\n" +
            "                  6.443183404945369\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9617300033569283,\n" +
            "                  6.443268693487738\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9618051052093457,\n" +
            "                  6.44395100131056\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.962663412094111,\n" +
            "                  6.442671673390061\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9628994464874214,\n" +
            "                  6.443503236905336\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.96118283271789,\n" +
            "                  6.444825206867361\n" +
            "                ],\n" +
            "                [\n" +
            "                  3.9579212665557812,\n" +
            "                  6.44503842750631\n" +
            "                ]\n" +
            "              ]\n" +
            "            ]\n" +
            "          }\n" +
            "        },\n" +
            "        \"area\": 136884.9651175423,\n" +
            "        \"routesDistance\": 6.887024351484767,\n" +
            "        \"base\": {\n" +
            "          \"id\": \"75075365-9398-1241-c689-c9e30828a8bb\",\n" +
            "          \"type\": \"base\",\n" +
            "          \"zoneId\": \"4d678445-c8f1-61f1-32d6-76e608d0ca16\",\n" +
            "          \"altitude\": 40,\n" +
            "          \"data\": {\n" +
            "            \"type\": \"Feature\",\n" +
            "            \"properties\": {},\n" +
            "            \"geometry\": {\n" +
            "              \"type\": \"Point\",\n" +
            "              \"coordinates\": [\n" +
            "                3.9628019340998093,\n" +
            "                6.444015636935137\n" +
            "              ]\n" +
            "            }\n" +
            "          },\n" +
            "          \"$$layerUID\": \"layer327\"\n" +
            "        },\n" +
            "        \"$$layerUID\": \"layer326\",\n" +
            "        \"circumference\": 2127.4155198547587\n" +
            "      }\n" +
            "    ]}";
}

