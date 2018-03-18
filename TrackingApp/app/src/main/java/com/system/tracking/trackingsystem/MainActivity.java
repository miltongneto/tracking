package com.system.tracking.trackingsystem;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.system.tracking.trackingsystem.activities.ChangeDistanceActivity;
import com.system.tracking.trackingsystem.activities.ChangePasswordActivity;
import com.system.tracking.trackingsystem.activities.ChangePhoneInfoActivity;
import com.system.tracking.trackingsystem.services.SafeService;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private Button changePassword;
    private Button changeDistance;
    private Button changePhoneInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();

        //Intent intent = new Intent(this, NotifyAssaultService.class);
        //startService(intent);
        Intent restartService = new Intent();
        restartService.setAction("RestartService");
        sendBroadcast(restartService);
//        Intent intent = new Intent(this, SafeService.class);
//        startService(intent);

        verifyPermission();

        try {
            deviceConnectionTest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));
            }
        });

        changeDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChangeDistanceActivity.class));
            }
        });

        changePhoneInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChangePhoneInfoActivity.class));
            }
        });
    }

    private void deviceConnectionTest() throws IOException {
        URL url = null;
        url = new URL("http://192.168.43.186/SIGNAL");
        //new LoadHtml().execute(url);
    }

    private void initializeVariables() {
        changePassword = (Button) findViewById(R.id.change_password);
        changeDistance = (Button) findViewById(R.id.change_distance);
        changePhoneInfo = (Button) findViewById(R.id.change_phone_info);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.distance_config_file), Context.MODE_PRIVATE);
        int defaultValue = 10;
        int currentProgress = sharedPreferences.getInt(getString(R.string.distance_config_key), defaultValue);
        if(currentProgress == 10) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.distance_config_key), currentProgress);
            editor.commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i("MainActivity", "permissoes");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void verifyPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        } else {
            Toast.makeText(this, "" + Manifest.permission.ACCESS_FINE_LOCATION + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

}
