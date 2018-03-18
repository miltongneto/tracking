package com.system.tracking.trackingsystem.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.system.tracking.trackingsystem.R;

/**
 * Created by TMB on 17/03/2018.
 */

public class ChangeDistanceActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_distance);
        initializeVariables();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.distance_config_file), Context.MODE_PRIVATE);
        int defaultValue = 0;
        int currentProgress = sharedPreferences.getInt(getString(R.string.distance_config_key), defaultValue);
        seekBar.setProgress(currentProgress);

        textView.setText("Distancia: " + seekBar.getProgress() + " m");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("Distancia: " + progress + " m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.distance_config_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.distance_config_key), seekBar.getProgress());
                editor.commit();

                finish();
            }
        });
    }

    private void initializeVariables() {
        seekBar = (SeekBar) findViewById(R.id.distance_bar);
        textView = (TextView) findViewById(R.id.distance_text);
        save = (Button) findViewById(R.id.change_distance_save);
    }
}
