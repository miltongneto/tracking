package com.system.tracking.trackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.system.tracking.trackingsystem.activities.ChangeDistanceActivity;
import com.system.tracking.trackingsystem.activities.ChangePasswordActivity;
import com.system.tracking.trackingsystem.activities.ChangePhoneInfoActivity;

public class MainActivity extends AppCompatActivity {
    private Button changePassword;
    private Button changeDistance;
    private Button changePhoneInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();

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

    private void initializeVariables() {
        changePassword = (Button) findViewById(R.id.change_password);
        changeDistance = (Button) findViewById(R.id.change_distance);
        changePhoneInfo = (Button) findViewById(R.id.change_phone_info);
    }
}
