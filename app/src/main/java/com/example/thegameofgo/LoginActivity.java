package com.example.thegameofgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public AlphaAnimation clickAnimation() {
        return new AlphaAnimation(1F, 0.4F); // Change "0.4F" as per your recruitment.
    }

    @Override
    public void onBackPressed() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
        mp.start();
        finish();
        Intent i = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.username_login);
        EditText password = (EditText) findViewById(R.id.password);
        TextView warning = (TextView) findViewById(R.id.warning);

        Button login = (Button) findViewById(R.id.loginBtn);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);

        login.setOnClickListener(v -> {
            login.setAnimation(clickAnimation());
            mp.start();
            String susername = username.getText().toString();
            String spassword = password.getText().toString();
            if(susername.isEmpty() || spassword.isEmpty()){
                warning.setText("All the fields must be filled before logging in");
            }
            else {
                login.setAnimation(clickAnimation());
                DocumentReference newPlayerRef = db.collection("players").document(susername);
                newPlayerRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                //player p = document.toObject(player.class);
                                String comparePassword = document.get("password").toString();
                                if(spassword.equals(comparePassword)){
                                    SharedPreferences preferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("username", susername);
                                    editor.putBoolean("isloggedin", true);
                                    editor.commit();

                                    finish();
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                } else {
                                    warning.setText(R.string.wrongdetails);
                                }
                            } else {
                                warning.setText(R.string.wrongdetails);
                            }
                        } else {
                            warning.setText("get failed");
                        }
                    }
                });
            }

            login.setBackgroundResource(R.drawable.login_btn_bg);
        });
    }
}