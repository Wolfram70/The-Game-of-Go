package com.example.thegameofgo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public void customPlayOfflineDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.offline_options);
        dialog.getWindow().setBackgroundDrawableResource(R.color.trans);

        TextView player_1 = dialog.findViewById(R.id.player_1);
        TextView player_2 = dialog.findViewById(R.id.player_2);

        Button go = (Button) dialog.findViewById(R.id.play);

        go.setOnClickListener(view -> {
            String name_1  = player_1.getText().toString();
            String name_2 = player_2.getText().toString();

            dialog.dismiss();

            SharedPreferences preference = getSharedPreferences("OfflinePlayers", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preference.edit();

            editor.putString("player1", name_1);
            editor.putString("player2", name_2);
            editor.commit();

            Intent i = new Intent(getApplicationContext(), OfflineGameplayActivity.class);
            finish();
            startActivity(i);

        });

        dialog.show();
    }





    public void customLogoutDialog() {
        // creating custom dialog
        final Dialog dialog = new Dialog(MainActivity.this);

        // setting content view to dialog
        dialog.setContentView(R.layout.logout_dialog);

        // getting reference of TextView
        TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = (TextView) dialog.findViewById(R.id.textViewNo);

        // click listener for No
        dialogButtonNo.setOnClickListener(v -> {
            // dismiss the dialog
            dialog.dismiss();

        });
        // click listener for Yes
        dialogButtonYes.setOnClickListener(v -> {
            // dismiss the dialog and exit the exit
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

        // getting reference of TextView
        TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = (TextView) dialog.findViewById(R.id.textViewNo);

        // click listener for No
        dialogButtonNo.setOnClickListener(v -> {
            // dismiss the dialog
            dialog.dismiss();

        });
        // click listener for Yes
        dialogButtonYes.setOnClickListener(v -> {
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

        TextView create_btn = (TextView) dialog.findViewById(R.id.create);
        TextView join_btn = (TextView) dialog.findViewById(R.id.join);

        create_btn.setOnClickListener(v -> {
            dialog.dismiss();
            Dialog dialog1 = new Dialog(MainActivity.this);
            dialog1.setContentView(R.layout.game_id_dialog);
            dialog1.show();
        });

        join_btn.setOnClickListener(v -> {
            dialog.dismiss();
            Dialog dialog1 = new Dialog(MainActivity.this);
            dialog1.setContentView(R.layout.join_game_dialog);
            dialog1.show();
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
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
        online_play.setOnClickListener(view -> onlinePlayDialog());

        offline_play.setOnClickListener(view -> {
            customPlayOfflineDialog();
        });

        TextView logInfo = (TextView) findViewById(R.id.LoginInfo);
        logInfo.setText(logUsername);

        set_btn.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        });

        //for online play, check isLoggedIn, if false, redirect to signup

        exit.setOnClickListener(view -> customLogoutDialog());
    }
}
