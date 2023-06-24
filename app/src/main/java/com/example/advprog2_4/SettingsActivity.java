package com.example.advprog2_4;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {
    Button btnSetServerAddress;
    FloatingActionButton btnBack;
    Global global;
    EditText etServerAddress;
    Switch themeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnSetServerAddress = findViewById(R.id.btnSetServerAddress);
        etServerAddress = findViewById(R.id.etServerAddress);
        btnSetServerAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = etServerAddress.getText().toString();
                etServerAddress.setText("");
                if (addr.length() > 0 && addr.startsWith("http://")) {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            global.getInstance().setServerAddress(addr);
                            Looper.prepare();
                            Toast.makeText(SettingsActivity.this, "Server address set to:\n" + addr, Toast.LENGTH_SHORT).show();
                        }
                    };
                    new Thread(r).start();
                    //global.getInstance().setServerAddress(addr);
                    //Toast.makeText(SettingsActivity.this, "Server address set successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "Failed setting server address", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        themeSwitch = findViewById(R.id.themeSwitch);
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        themeSwitch.setChecked(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);
    }
}
