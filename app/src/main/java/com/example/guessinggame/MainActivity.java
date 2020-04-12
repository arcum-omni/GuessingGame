package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button leftBtn;
    private Button rightBtn;
    private TextView scoreView;
    private Random rand;
    private int leftNumber;
    private int rightNumber;
    private int score;
    private int streak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftBtn = findViewById(R.id.leftButton);
        rightBtn = findViewById(R.id.rightButton);
        scoreView = findViewById((R.id.scoreView));

        score = 0;
        streak = 0;

        rand = new Random();
        chooseNumbers();
    }

    public void onClick(View v){
        if(v == leftBtn){
            if(leftNumber > rightNumber){
                keepScore(true);
                showMessages(true);
            }
            else{
                keepScore(false);
                showMessages(false);
            }
        }
        else{
            if(rightNumber > leftNumber){
                keepScore(true);
                showMessages(true);
            }
            else{
                keepScore(false);
                showMessages(false);
            }
        }
        scoreView.setText("Score: " + score);
        chooseNumbers();
    }

    private void chooseNumbers(){
        leftNumber = rand.nextInt(10);
        rightNumber = rand.nextInt(10);
        while(leftNumber == rightNumber){
            rightNumber = rand.nextInt(10);
        }

        leftBtn.setText("" + leftNumber);
        rightBtn.setText("" + rightNumber);
    }

    private void keepScore(boolean b){
        if(b == true){
            score++;
            streak++;
        }
        else{
            score--;
            streak = 0;
        }
    }

    private void showMessages(boolean b){
        int congratStreak = 10; // The number of correct guesses in a row to get congratulated

        if(b == true){
            if(streak >= 2 * congratStreak){
                Toast.makeText(this, "Wow, you are really on a roll.  You've guessed correct " + streak + " times in a row!", Toast.LENGTH_LONG).show();
            }
            else if(streak == congratStreak){
                Toast.makeText(this, "Great Job, you've guessed correct " + streak + " times in a row!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Great job, keep it up!", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "Wrong, try again.", Toast.LENGTH_LONG).show();
        }
    }
}
