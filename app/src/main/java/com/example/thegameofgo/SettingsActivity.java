package com.example.thegameofgo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button back = (Button) findViewById(R.id.back);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);

        back.setOnClickListener(view -> {
            mp.start();
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
        mp.start();
        finish();
    }
}