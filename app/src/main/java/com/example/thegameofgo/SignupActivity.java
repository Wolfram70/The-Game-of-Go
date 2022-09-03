package com.example.thegameofgo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

public class SignupActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void customExitDialog() {
        // creating custom dialog
        final Dialog dialog = new Dialog(SignupActivity.this);

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

    @Override
    public void onBackPressed() {
        customExitDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText email = (EditText) findViewById(R.id.email);
        EditText name = (EditText) findViewById(R.id.full_name);
        EditText password = (EditText) findViewById(R.id.signpass);
        EditText cpassword = (EditText) findViewById(R.id.signRePass);
        TextView warning = (TextView) findViewById(R.id.warning);

        Button signup = (Button) findViewById(R.id.button);
        Button alreadysignup = (Button) findViewById(R.id.alreadysigned);

        alreadysignup.setOnClickListener(v -> {
            finish();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        });

        signup.setOnClickListener(v -> {
            signup.setBackgroundResource(R.drawable.login_btn_bg_pressed);

            String sname = name.getText().toString();
            String semail = email.getText().toString();
            String spassword = password.getText().toString();
            String scpassword = cpassword.getText().toString();

            if(spassword.equals(scpassword)){
                warning.setText(R.string.empty);

                DocumentReference newPlayerRef = db.collection("players").document(sname);
                newPlayerRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                warning.setText(R.string.useralreadyexists);
                            } else {
                                player p = new player(sname, semail, spassword, 0, 0);
                                newPlayerRef.set(p);
                                finish();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                            }
                        } else {
                            warning.setText("get failed");
                        }
                    }
                });
            }
            else{
                warning.setText(R.string.nomatch);
            }

            signup.setBackgroundResource(R.drawable.login_btn_bg);
        });
    }

    public class player{
        private String username;
        private String email;
        private String password;
        private int wins;
        private int losses;

        public player(String sname, String semail, String spassword, int swins, int slosses){
            username = sname;
            email = semail;
            password = spassword;
            wins = swins;
            losses = slosses;
        }
        public String getUsername() {
            return username;
        }
        public String getEmail() {
            return email;
        }
        public String getPassword() {
            return password;
        }
        public int getWins(){
            return wins;
        }
        public int getLosses(){
            return losses;
        }


    }
}