package com.skycatch.simpleapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import main.java.com.skycatch.simpleapp.CommanderMission;
import main.java.com.skycatch.simpleapp.MapboxFragment;
import main.java.com.skycatch.simpleapp.ObjectConverter;

public class MainActivity extends FragmentActivity {


    private Button importMission;

    private CharSequence[] chars;
    private String path = "";
    private CommanderMission commanderMission;

    MapboxFragment mapboxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importMission = (Button) findViewById(R.id.upload_btn);

        //Create app folder
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "No SDCard", Toast.LENGTH_LONG).show();
        } else {
            File directory = new File(Environment.getExternalStorageDirectory(), "Commander");
            path = directory.getPath();
            if (!directory.exists()) {
                boolean create = directory.mkdirs();
                Toast.makeText(getApplicationContext(), String.valueOf(create), Toast.LENGTH_LONG).show();
            }
            File[] filesArray = directory.listFiles();
            List<String> filesList = new ArrayList<>();
            if (filesArray != null) {
                for (int i=0; i < filesArray.length; i++) {
                    String current = filesArray[i].getName();
                    String[] fileName = current.split("\\.");
                    if (fileName[fileName.length-1].equals("cmdr")) {
                        filesList.add(current);
                    }
                }
            }
            chars = new String[filesList.size()];
            filesList.toArray(chars);
        }

        mapboxFragment = new MapboxFragment();
        getSupportFragmentManager().popBackStack();
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.add(R.id.fragment_container, mapboxFragment).commit();


        importMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Select file to upload:");
                if (chars != null) {
                    dialog.setItems(chars, dialoglistener);
                }
                AlertDialog box = dialog.create();
                box.show();
            }
        });

    }

    DialogInterface.OnClickListener dialoglistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String file = getCommanderJsonFile(path + "/" + chars[which]);
            commanderMission = ObjectConverter.createObjectConverter(file);

            if (commanderMission != null) {
                mapboxFragment.drawMission(commanderMission);
            }

        }
    };

    public String getCommanderJsonFile(String filename) {
        String json = "";
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    static {
        System.loadLibrary("skycatch_jni");
    }

}
