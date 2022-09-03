package com.example.thegameofgo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

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

    BiMap buttons = HashBiMap.create();
    BiMap revButtons = HashBiMap.create();
    static boolean whiteturn = false;
    int board[] = new int[49];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_gameplay);

        ImageView menu_btn = (ImageView) findViewById(R.id.menu_btn);

        menu_btn.setOnClickListener(view -> {
            PauseDialog();
        });

        buttons.put(R.id.button0, 0);
        buttons.put(R.id.button1, 1);
        buttons.put(R.id.button2, 2);
        buttons.put(R.id.button3, 3);
        buttons.put(R.id.button4, 4);
        buttons.put(R.id.button5, 5);
        buttons.put(R.id.button6, 6);

        buttons.put(R.id.button7, 7);
        buttons.put(R.id.button8, 8);
        buttons.put(R.id.button9, 9);
        buttons.put(R.id.button10, 10);
        buttons.put(R.id.button11, 11);
        buttons.put(R.id.button12, 12);
        buttons.put(R.id.button13, 13);

        buttons.put(R.id.button14, 14);
        buttons.put(R.id.button15, 15);
        buttons.put(R.id.button16, 16);
        buttons.put(R.id.button17, 17);
        buttons.put(R.id.button18, 18);
        buttons.put(R.id.button19, 19);
        buttons.put(R.id.button20, 20);

        buttons.put(R.id.button21, 21);
        buttons.put(R.id.button22, 22);
        buttons.put(R.id.button23, 23);
        buttons.put(R.id.button24, 24);
        buttons.put(R.id.button25, 25);
        buttons.put(R.id.button26, 26);
        buttons.put(R.id.button27, 27);

        buttons.put(R.id.button28, 28);
        buttons.put(R.id.button29, 29);
        buttons.put(R.id.button30, 30);
        buttons.put(R.id.button31, 31);
        buttons.put(R.id.button32, 32);
        buttons.put(R.id.button33, 33);
        buttons.put(R.id.button34, 34);

        buttons.put(R.id.button35, 35);
        buttons.put(R.id.button36, 36);
        buttons.put(R.id.button37, 37);
        buttons.put(R.id.button38, 38);
        buttons.put(R.id.button39, 39);
        buttons.put(R.id.button40, 40);
        buttons.put(R.id.button41, 41);

        buttons.put(R.id.button42, 42);
        buttons.put(R.id.button43, 43);
        buttons.put(R.id.button44, 44);
        buttons.put(R.id.button45, 45);
        buttons.put(R.id.button46, 46);
        buttons.put(R.id.button47, 47);
        buttons.put(R.id.button48, 48);

        revButtons = buttons.inverse();
    }

    public void onClick(View v){
        int id = v.getId();

        move(id);
    }

    public void move(int id){
        int position = (int) buttons.get(id);

        if(board[position] != 0){
            return;
        }

        if(whiteturn){
            board[position] = 1;
        }
        else{
            board[position] = 2;
        }

        if(set(position)){
            whiteturn = !whiteturn;
        }
    }

    public boolean set(int change){
        int newColor;
        removeCaptures(7);
        newColor = board[change];

        if(newColor == 0)
        {
            return false;
        }
        int color, id;

        for(int i = 0; i < 49; i++){
            id = (int) revButtons.get(i);
            color = board[i];
            Button b = (Button) findViewById(id);
            switch(color){
                case 0: b.setBackgroundResource(R.drawable.gplay_panel);
                break;
                case 1: b.setBackgroundResource(R.drawable.white);
                break;
                case 2: b.setBackgroundResource(R.drawable.black);
                break;
                default: break;
            }
        }

        return true;
    }

    public void removeCaptures(int boardSize)
    {
        int toKeep[] = new int[boardSize * boardSize];
        boolean changed = true;
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                toKeep[boardSize * i + j] = 0;
            }
        }
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                if(board[boardSize * i + j] == 0)
                {
                    toKeep[boardSize * i + j] = 1;
                }
            }
        }

        int temp;

        while(changed)
        {
            changed = false;
            for(int i = 0; i < boardSize; i++)
            {
                for(int j = 0; j < boardSize; j++)
                {
                    if(toKeep[boardSize * i + j] != 0)
                    {
                        if(board[boardSize * i + j] == 0)
                        {
                            if((i - 1) >= 0)
                            {
                                if(toKeep[boardSize * (i - 1) + j] == 0) {
                                    changed = true;
                                }
                                toKeep[boardSize * (i - 1) + j] = 1;
                            }
                            if((j - 1) >= 0)
                            {
                                if(toKeep[boardSize * i + j - 1] == 0) {
                                    changed = true;
                                }
                                toKeep[boardSize * i + j - 1] = 1;
                            }
                            if((i + 1) < boardSize)
                            {
                                if(toKeep[boardSize * (i + 1) + j] == 0) {
                                    changed = true;
                                }
                                toKeep[boardSize * (i + 1) + j] = 1;
                            }
                            if((j + 1) < boardSize)
                            {
                                if(toKeep[boardSize * i + j + 1] == 0) {
                                    changed = true;
                                }
                                toKeep[boardSize * i + j + 1] = 1;
                            }
                        }
                        else
                        {
                            temp = board[boardSize * i + j];
                            if(((i - 1) >= 0) && (board[boardSize * (i - 1) + j] == temp))
                            {
                                if(toKeep[boardSize * (i - 1) + j] == 0) {
                                    changed = true;
                                }
                                toKeep[boardSize * (i - 1) + j] = 1;
                            }
                            if(((j - 1) >= 0) && (board[boardSize * i + j - 1] == temp))
                            {
                                if(toKeep[boardSize * i + j - 1] == 0) changed = true;
                                toKeep[boardSize * i + j - 1] = 1;
                            }
                            if(((i + 1) < boardSize) && (board[boardSize * (i + 1) + j] == temp))
                            {
                                if(toKeep[boardSize * (i + 1) + j] == 0) changed = true;
                                toKeep[boardSize * (i + 1) + j] = 1;
                            }
                            if(((j + 1) < boardSize) && (board[boardSize * i + j + 1] == temp))
                            {
                                if(toKeep[boardSize * i + j + 1] == 0) changed = true;
                                toKeep[boardSize * i + j + 1] = 1;
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                if(toKeep[boardSize * i + j] == 0)
                {
                    board[boardSize * i + j] = 0;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        PauseDialog();
    }
}