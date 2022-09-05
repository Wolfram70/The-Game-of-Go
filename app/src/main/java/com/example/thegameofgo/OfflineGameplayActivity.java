package com.example.thegameofgo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

public class OfflineGameplayActivity extends AppCompatActivity {

    public AlphaAnimation clickAnimation() {
        return new AlphaAnimation(1F, 0.4F); // Change "0.4F" as per your recruitment.
    }

    public void PauseDialog() {
        final Dialog dialog = new Dialog(OfflineGameplayActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(R.color.trans);

        dialog.setContentView(R.layout.pause_dialog);

        TextView main_menu = (TextView) dialog.findViewById(R.id.main_menu);
        TextView exit_game = (TextView) dialog.findViewById(R.id.exit_game);
        TextView back = (TextView) dialog.findViewById(R.id.back);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.short_click);

        main_menu.setOnClickListener(v -> {
            main_menu.startAnimation(clickAnimation());
            mp.start();
            dialog.dismiss();
            Dialog dialog1= new Dialog(OfflineGameplayActivity.this);
            dialog1.getWindow().setBackgroundDrawableResource(R.color.trans);
            dialog1.setContentView(R.layout.main_menu_confirm);
            dialog1.show();

            TextView no = (TextView) dialog1.findViewById(R.id.no);
            TextView yes = (TextView) dialog1.findViewById(R.id.yes);

            no.setOnClickListener(view -> {
                no.startAnimation(clickAnimation());
                mp.start();
                dialog1.dismiss();
            });

            yes.setOnClickListener(view -> {
                yes.startAnimation(clickAnimation());
                mp.start();
                dialog1.dismiss();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(i);
            });

        });

        exit_game.setOnClickListener(v -> {
            exit_game.startAnimation(clickAnimation());
            mp.start();
            dialog.dismiss();

            Dialog dialog1 = new Dialog(OfflineGameplayActivity.this);
            dialog1.getWindow().setBackgroundDrawableResource(R.color.trans);
            dialog1.setContentView(R.layout.exit_dialog);
            dialog1.show();


            TextView no = (TextView) dialog1.findViewById(R.id.textViewNo);
            TextView yes = (TextView) dialog1.findViewById(R.id.textViewYes);

            no.setOnClickListener(vi -> {
                no.startAnimation(clickAnimation());
                mp.start();
                dialog1.dismiss();
            });

            yes.setOnClickListener(view -> {
                yes.startAnimation(clickAnimation());
                mp.start();
                dialog1.dismiss();
                finish();
            });
        });

        back.setOnClickListener(v -> {
            back.startAnimation(clickAnimation());
            final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.click);
            mp2.start();
            dialog.dismiss();
        });

        dialog.show();
    }

    BiMap buttons = HashBiMap.create();
    BiMap revButtons = HashBiMap.create();
    static boolean whiteturn = false;
    int board[] = new int[49];

    TextView noSu ;
    View playerPanel1;
    View playerPanel2;
    Boolean alreadyPassed = false;
    Button Pass1, Pass2;
    int whiteScore = 0;
    int blackScore = 0;
    int blackCaptures = 0;
    int whiteCaptures = 0;
    String player_1;
    String player_2;
    TextView blackCapturesText;
    TextView whiteCapturesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_gameplay);

        playerPanel1 = findViewById(R.id.player1panel);
        playerPanel2 = findViewById(R.id.player2panel);

        Pass1 = findViewById(R.id.user_pass);
        Pass2 = findViewById(R.id.opp_pass);

        blackCapturesText = findViewById(R.id.user_cptr);
        whiteCapturesText = findViewById(R.id.opp_cptr);

        blackCapturesText.setText(blackCaptures + " Captures");
        whiteCapturesText.setText(whiteCaptures + " Captures");

        SharedPreferences preferences = getSharedPreferences("OfflinePlayers", Context.MODE_PRIVATE);
        player_1 = preferences.getString("player1", "Player 1");
        player_2 = preferences.getString("player2", "Player 2");

        Button play1 = (Button) findViewById(R.id.player1_name);
        Button play2 = (Button) findViewById(R.id.player2_name);

        play1.setText(player_1);
        play2.setText(player_2);

        noSu = (TextView) findViewById(R.id.no_su);
        ImageView menu_btn = (ImageView) findViewById(R.id.menu_btn);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.short_click);
        menu_btn.setOnClickListener(view -> {
            menu_btn.startAnimation(clickAnimation());
            mp.start();
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

        blackCapturesText.setText(blackCaptures + " Captures");
        whiteCapturesText.setText(whiteCaptures + " Captures");
    }

    public void move(int id){
        if(id == Pass1.getId()) {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
            if(whiteturn) {
                return;
            }
            mp.start();
            whiteturn = !whiteturn;
            playerPanel2.setBackgroundResource(R.drawable.gplay_panel_focus);
            playerPanel1.setBackgroundResource(R.drawable.gplay_panel);
            //change opacity of Pass1 to dull, Pass2 to bright
            if(alreadyPassed) {
                endGame();
            }
            else {
                alreadyPassed = true;
            }
            Pass1.setText("PASSED");
            Pass2.setText("PASS");
            return;
        }
        if(id == Pass2.getId()) {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
            if(!whiteturn){
                return;
            }
            mp.start();
            whiteturn = !whiteturn;
            playerPanel1.setBackgroundResource(R.drawable.gplay_panel_focus);
            playerPanel2.setBackgroundResource(R.drawable.gplay_panel);
            //change opacity of Pass2 to dull, Pass1 to bright
            if(alreadyPassed) {
                endGame();
            }
            else {
                alreadyPassed = true;
            }
            Pass2.setText("PASSED");
            Pass1.setText("PASS");
            return;
        }
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.wood2);
        int position = (int) buttons.get(id);

        if(board[position] != 0){
            return;
        }

        if(whiteturn){
            mp.start();
            board[position] = 1;
        }
        else{
            mp.start();
            board[position] = 2;
        }

        if(set(position)){
            whiteturn = !whiteturn;
            alreadyPassed = false;
            if(whiteturn) {
                playerPanel2.setBackgroundResource(R.drawable.gplay_panel_focus);
                playerPanel1.setBackgroundResource(R.drawable.gplay_panel);
            }
            else {
                playerPanel1.setBackgroundResource(R.drawable.gplay_panel_focus);
                playerPanel2.setBackgroundResource(R.drawable.gplay_panel);
            }
            Pass2.setText("PASS");
            Pass1.setText("PASS");
            noSu.setText("");
        }
        else {
            noSu.setText("Suicidal move unallowed.");
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
        int captures = 0;
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
                    if(board[boardSize * i + j] != 0) {
                        captures ++;
                    }
                    board[boardSize * i + j] = 0;
                }
            }
        }

        if(whiteturn) {
            whiteCaptures += captures;
        }
        else {
            blackCaptures += captures;
        }
    }

    public void endGame() {
        whiteScore = calculateScore(7, 1);
        blackScore = calculateScore(7, 2);

        //endgame dialog here
        final Dialog dialog = new Dialog(OfflineGameplayActivity.this);

        dialog.setContentView(R.layout.endgame_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.trans);
        TextView white_score = (TextView) dialog.findViewById(R.id.white_score);
        TextView black_score = (TextView) dialog.findViewById(R.id.black_score);
        TextView player1 = (TextView) dialog.findViewById(R.id.black_player);
        TextView player2 = (TextView) dialog.findViewById(R.id.white_player);
        TextView gameResult = (TextView) dialog.findViewById(R.id.game_result);
        TextView result = (TextView) dialog.findViewById(R.id.result);
        Button mainMenu = (Button) dialog.findViewById(R.id.back);

        white_score.setText(Integer.toString(whiteScore));
        black_score.setText(Integer.toString(blackScore));

        player1.setText(player_1);
        player2.setText(player_2);

        if (whiteScore > blackScore) {
            int diff = whiteScore - blackScore;
            result.setText("White Wins !");
            gameResult.setText(player_2 + " wins by " + diff + " points.");
        }
        else if (blackScore > whiteScore) {
            int diff = blackScore - whiteScore;
            result.setText("Black Wins !");
            gameResult.setText(player_1 + " wins by " + diff + " points.");
        }
        else {
            result.setText(" Draw ! ");
            gameResult.setText("Draw !");
        }
        dialog.setCancelable(false);
        dialog.show();

        mainMenu.setOnClickListener(view ->{

            Dialog dialog1= new Dialog(OfflineGameplayActivity.this);
            dialog1.getWindow().setBackgroundDrawableResource(R.color.trans);
            dialog1.setContentView(R.layout.main_menu_confirm);
            dialog1.show();

            TextView no = (TextView) dialog1.findViewById(R.id.no);
            TextView yes = (TextView) dialog1.findViewById(R.id.yes);

            no.setOnClickListener(view1 -> {
                dialog1.dismiss();
            });

            yes.setOnClickListener(v -> {
                dialog1.dismiss();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(i);
            });
        });

    }

    public int calculateScore(int boardSize, int color) {
        int score = 0, opp = 1, temp;
        Boolean skip = false;

        if(color == opp) opp = 2;

        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                if(board[boardSize * i + j] == color)
                {
                    score++;
                }
                else if(board[boardSize * i + j] == 0)
                {
                    skip = false;

                    temp = i;
                    while(temp >= 0)
                    {
                        if(board[boardSize * temp + j] == color)
                        {
                            break;
                        }
                        else if(board[boardSize * temp + j] == opp)
                        {
                            skip = true;
                            break;
                        }

                        temp--;
                    }
                    if(skip) continue;

                    temp = i;
                    while(temp < boardSize)
                    {
                        if(board[boardSize * temp + j] == color)
                        {
                            break;
                        }
                        else if(board[boardSize * temp + j] == opp)
                        {
                            skip = true;
                            break;
                        }

                        temp++;
                    }
                    if(skip) continue;

                    temp = j;
                    while(temp >= 0)
                    {
                        if(board[boardSize * i + temp] == color)
                        {
                            break;
                        }
                        else if(board[boardSize * i + temp] == opp)
                        {
                            skip = true;
                            break;
                        }

                        temp--;
                    }
                    if(skip) continue;

                    temp = j;
                    while(temp < boardSize)
                    {
                        if(board[boardSize * i + temp] == color)
                        {
                            break;
                        }
                        else if(board[boardSize * i + temp] == opp)
                        {
                            skip = true;
                            break;
                        }

                        temp++;
                    }
                    if(skip) continue;

                    score++;
                }
            }
        }

        return score;
    }

    @Override
    public void onBackPressed() {
        PauseDialog();
    }
}