package com.example.tumusime.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.IntProperty;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // yellow = 0 , or Red = 1;
    int activePlayer=0;
    boolean gameIsActive = true;
    // 2 means uplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6} };

    public void dropin(View view){
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] ==2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            counter.animate().rotation(360).translationYBy(1000f).setDuration(300);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]== gameState[winningPosition[2]] && gameState[winningPosition[2]] !=2){

                    // someone has one
                    gameIsActive = false;
                    String winner = "Red";
                    if(gameState[winningPosition[0]]==0){
                        winner = "yellow";
                    }

                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    TextView textView = (TextView) findViewById(R.id.winnerMessage);

                    textView.setText(winner + "has Won !!");

                    linearLayout.setVisibility(view.VISIBLE);

                }
            }
        }else{
            boolean gameIsOver = true;
            for(int counterState : gameState){
                if(counterState == 2) gameIsOver = false;
            }
            if(gameIsOver){

                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                TextView textView = (TextView) findViewById(R.id.winnerMessage);

                textView.setText("It's a draw !!");

                linearLayout.setVisibility(view.VISIBLE);

            }
        }
    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(view.INVISIBLE);

        activePlayer=0;

        for (int i=0; i< gameState.length; i++){
            gameState[i]=2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i< gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(0)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
