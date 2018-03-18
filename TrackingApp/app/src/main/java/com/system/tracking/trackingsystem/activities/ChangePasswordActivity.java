package com.system.tracking.trackingsystem.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.system.tracking.trackingsystem.R;

/**
 * Created by TMB on 18/03/2018.
 */

public class ChangePasswordActivity extends AppCompatActivity {
    private Button save;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initializeVariables();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.password_config_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.password_config_key), pass);
                editor.commit();

                finish();
            }
        });
    }

    private void initializeVariables() {
        save = (Button) findViewById(R.id.change_password_save);
        password = (EditText) findViewById(R.id.change_password_text);
    }
}
