package com.example.thegameofgo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public AlphaAnimation clickAnimation() {
        return new AlphaAnimation(1F, 0.4F); // Change "0.4F" as per your recruitment.
    }

    public void customPlayOfflineDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.offline_options);
        dialog.getWindow().setBackgroundDrawableResource(R.color.trans);

        TextView player_1 = dialog.findViewById(R.id.player_1);
        TextView player_2 = dialog.findViewById(R.id.player_2);

        Button go = (Button) dialog.findViewById(R.id.play);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
        go.setOnClickListener(view -> {
            go.startAnimation(clickAnimation());
            mp.start();
            String name_1  = player_1.getText().toString();
            String name_2 = player_2.getText().toString();

            SharedPreferences preference = getSharedPreferences("OfflinePlayers", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preference.edit();

            editor.putString("player1", name_1);
            editor.putString("player2", name_2);
            editor.commit();

            if (name_1.isEmpty() || name_2.isEmpty()) {
                return;
            }
            else {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), OfflineGameplayActivity.class);
                finish();
                startActivity(i);
            }
        });

        dialog.show();
    }




    public void customLogoutDialog() {
        // creating custom dialog
        final Dialog dialog = new Dialog(MainActivity.this);

        // setting content view to dialog
        dialog.setContentView(R.layout.logout_dialog);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
        // getting reference of TextView
        TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = (TextView) dialog.findViewById(R.id.textViewNo);

        // click listener for No
        dialogButtonNo.setOnClickListener(v -> {
            dialogButtonNo.startAnimation(clickAnimation());
            mp.start();
            // dismiss the dialog
            dialog.dismiss();

        });
        // click listener for Yes
        dialogButtonYes.setOnClickListener(v -> {
            // dismiss the dialog and exit the exit
            dialogButtonYes.startAnimation(clickAnimation());
            mp.start();
            SharedPreferences preferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", "Guest");
            editor.putBoolean("isloggedin", false);
            editor.commit();
            this.recreate();
            dialog.dismiss();
        });

        // show the logout dialog
        dialog.show();
    }

    public void customExitDialog() {
        // creating custom dialog
        final Dialog dialog = new Dialog(MainActivity.this);

        // setting content view to dialog
        dialog.setContentView(R.layout.exit_dialog);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
        // getting reference of TextView
        TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = (TextView) dialog.findViewById(R.id.textViewNo);

        // click listener for No
        dialogButtonNo.setOnClickListener(v -> {
            dialogButtonNo.startAnimation(clickAnimation());
            mp.start();
            // dismiss the dialog
            dialog.dismiss();

        });
        // click listener for Yes
        dialogButtonYes.setOnClickListener(v -> {
            dialogButtonYes.startAnimation(clickAnimation());
            mp.start();
            // dismiss the dialog and exit the exit
            dialog.dismiss();
            finish();
        });

        // show the logout dialog
        dialog.show();
    }
    public void onlinePlayDialog() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.online_play_dialog);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
        TextView create_btn = (TextView) dialog.findViewById(R.id.create);
        TextView join_btn = (TextView) dialog.findViewById(R.id.join);

        create_btn.setOnClickListener(v -> {
            create_btn.startAnimation(clickAnimation());
            mp.start();
            dialog.dismiss();
            Dialog dialog1 = new Dialog(MainActivity.this);
            dialog1.setContentView(R.layout.game_id_dialog);
            dialog1.show();
        });

        join_btn.setOnClickListener(v -> {
            join_btn.startAnimation(clickAnimation());
            mp.start();
            dialog.dismiss();
            Dialog dialog1 = new Dialog(MainActivity.this);
            dialog1.setContentView(R.layout.join_game_dialog);
            dialog1.show();
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);
        mp.start();
        customExitDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        Boolean isLoggedIn = preferences.getBoolean("isloggedin", false);
        String logUsername = preferences.getString("username", "Guest");

        if(!isLoggedIn){
            finish();
            Intent i = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(i);
        }

        Button set_btn = (Button) findViewById(R.id.options);
        Button exit = (Button) findViewById(R.id.Exit);
        Button online_play = (Button) findViewById(R.id.online_play);
        Button offline_play = (Button) findViewById(R.id.offline_play);
        Button howToPlay = (Button) findViewById(R.id.how_to_play);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
        howToPlay.setOnClickListener(view2 -> {
            mp.start();
            howToPlay.startAnimation(clickAnimation());
            Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.britgo.org/intro/intro2.html"));
            startActivity(viewIntent);
        });

        offline_play.setOnClickListener(view -> {
            mp.start();
            offline_play.startAnimation(clickAnimation());
            customPlayOfflineDialog();
        });

        TextView logInfo = (TextView) findViewById(R.id.LoginInfo);
        logInfo.setText(logUsername);

        set_btn.setOnClickListener(v -> {
            mp.start();
            set_btn.startAnimation(clickAnimation());
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        });

        //for online play, check isLoggedIn, if false, redirect to signup
        online_play.setOnClickListener(view -> {
            mp.start();
            online_play.startAnimation(clickAnimation());
            onlinePlayDialog();
        });

        exit.setOnClickListener(view -> {
            mp.start();
                exit.startAnimation(clickAnimation());
                customLogoutDialog();
        });
    }
}
