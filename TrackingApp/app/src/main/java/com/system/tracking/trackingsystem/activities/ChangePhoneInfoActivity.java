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

public class ChangePhoneInfoActivity extends AppCompatActivity {
    private Button save;
    private EditText phoneNumber;
    private EditText phoneBrand;
    private EditText phoneValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_info);
        initializeVariables();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pNumber = phoneNumber.getText().toString();
                String pBrand = phoneBrand.getText().toString();
                String pValue = phoneValue.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.phone_info_config_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.phone_number_config_key), pNumber);
                editor.putString(getString(R.string.phone_brand_config_key), pBrand);
                editor.putString(getString(R.string.phone_value_config_key), pValue);
                editor.commit();

                finish();
            }
        });
    }

    private void initializeVariables() {
        save = (Button) findViewById(R.id.change_phone_info_save);
        phoneNumber = (EditText) findViewById(R.id.change_phone_number);
        phoneBrand = (EditText) findViewById(R.id.change_phone_brand);
        phoneValue = (EditText) findViewById(R.id.change_phone_value);
    }
}
