package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean isGameActive = true;

    // Indications: 0-player X, 1-player O, 2-otherwise (tile not pressed yet)

    int activePlayer = 0;

    int[] tilesState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winSituations= {{0, 1, 2},
                            {0, 3, 6},
                            {0, 4, 8},
                            {1, 4, 7},
                            {2, 4, 6},
                            {2, 5, 8},
                            {3, 4, 5},
                            {6, 7, 8}};

    int[] winImageRes = {R.drawable.mark6,
                         R.drawable.mark3,
                         R.drawable.mark1,
                         R.drawable.mark4,
                         R.drawable.mark2,
                         R.drawable.mark5,
                         R.drawable.mark7,
                         R.drawable.mark8};


    public static int tapsCounter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        ImageView gameStatus = findViewById(R.id.gameStatus);

        // When a player wins or all the boxes are tapped
        if (!isGameActive) {
            gameReset(view);
        }

        // When an empty tile is tapped
        if (tilesState[tappedImage] == 2) {
            tapsCounter++;

            // If it is the last empty tile left- inactive the game
            if (tapsCounter == 9) {
                isGameActive = false;
            }

            tilesState[tappedImage] = activePlayer;

            // Handle the next player
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                gameStatus.setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                gameStatus.setImageResource(R.drawable.xplay);
            }
        }

        boolean isWin = false;

        ImageView winMark = findViewById(R.id.winMark);

        for (int i = 0; i <= winSituations.length; i++) {
            // When a player tapped on all of a win situation's tiles
            if (tilesState[winSituations[i][0]] == tilesState[winSituations[i][1]] &&
                tilesState[winSituations[i][1]] == tilesState[winSituations[i][2]] &&
                tilesState[winSituations[i][0]] != 2) {
                isWin = true;
                isGameActive = false;

                winMark.setImageResource(winImageRes[i]);

                if (tilesState[winSituations[i][0]] == 0) {
                    gameStatus.setImageResource(R.drawable.xwin);
                } else {
                    gameStatus.setImageResource(R.drawable.owin);
                }
            }
        }

        // Match
        if (tapsCounter == 9 && !isWin) {
            gameStatus.setImageResource(R.drawable.nowin);
        }
    }

    public void gameReset(View view) {
        isGameActive = true;
        activePlayer = 0;

        for (int i = 0; i < tilesState.length; i++) {
            tilesState[i] = 2;
        }

        ((ImageView) findViewById(R.id.tile0)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile1)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile2)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile3)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile4)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile5)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile6)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile7)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile8)).setImageResource(R.drawable.empty);

        ImageView gameStatus = findViewById(R.id.gameStatus);
        gameStatus.setImageResource(R.drawable.xplay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}