package com.example.thegameofgo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OfflineGameplayActivity extends AppCompatActivity {

    public void PauseDialog() {
        final Dialog dialog = new Dialog(OfflineGameplayActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(R.color.trans);

        dialog.setContentView(R.layout.pause_dialog);

        TextView main_menu = (TextView) dialog.findViewById(R.id.main_menu);
        TextView exit_game = (TextView) dialog.findViewById(R.id.exit_game);
        TextView back = (TextView) dialog.findViewById(R.id.back);


        main_menu.setOnClickListener(v -> {
            dialog.dismiss();
            Dialog dialog1= new Dialog(OfflineGameplayActivity.this);
            dialog1.getWindow().setBackgroundDrawableResource(R.color.trans);
            dialog1.setContentView(R.layout.main_menu_confirm);
            dialog1.show();

            TextView no = (TextView) dialog1.findViewById(R.id.no);
            TextView yes = (TextView) dialog1.findViewById(R.id.yes);

            no.setOnClickListener(view -> {
                dialog1.dismiss();
            });

            yes.setOnClickListener(view -> {
                dialog1.dismiss();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(i);
            });

        });

        exit_game.setOnClickListener(v -> {
            dialog.dismiss();

            Dialog dialog1 = new Dialog(OfflineGameplayActivity.this);
            dialog1.getWindow().setBackgroundDrawableResource(R.color.trans);
            dialog1.setContentView(R.layout.exit_dialog);

            dialog1.show();

        });

        back.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_gameplay);

        ImageView menu_btn = (ImageView) findViewById(R.id.menu_btn);

        menu_btn.setOnClickListener(view -> {
            PauseDialog();
        });
    }
}