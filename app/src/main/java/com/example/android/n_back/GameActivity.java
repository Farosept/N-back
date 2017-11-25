package com.example.android.n_back;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView mTextView;
    TextView mTextTimer;
    TextView mTextResult;
    TextView mTextBad;
    TextView mTextAll;
    GameLogic game;
    infoResults results;
    AsyncTask<Void, Integer, Void> task;
    boolean buttonPressed;
    boolean index=true;
    boolean key=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextView = (TextView) findViewById(R.id.textview);
        mTextTimer = (TextView) findViewById(R.id.timerview);
        mTextResult = (TextView) findViewById(R.id.text_yes);
        mTextBad = (TextView) findViewById(R.id.text_bad);
        mTextAll = (TextView) findViewById(R.id.text_all);

        game = new GameLogic(2);
       task = new ProgressTask().execute();
        buttonPressed = false;
    }
    public void ClickInGameCheck(View view){
        if(key){
        buttonPressed = true;
        key=false;
        results = game.CheckOnButtonClick(game.getLastIndex());
        mTextResult.setText(String.valueOf(results.getRights()));
        mTextBad.setText(String.valueOf(results.getBads()));
        buttonPressed=false;
        task = new ProgressTask().execute();
        }
    }
    @Override
    public void onBackPressed() {
        task.cancel(false);
        buttonPressed = true;
        super.onBackPressed();
    }
    private class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
            while (!buttonPressed){
                Random random = new Random();
                int rand = random.nextInt(5)+1;
                game.SetRandomElement(rand);
                int all = game.getAll();
                index=true;
                publishProgress(rand,all);
                SystemClock.sleep(3000);
                index=false;
                publishProgress(rand,all);
                SystemClock.sleep(3000);
                key=true;
            }
            return(null);
        }
        @Override
        protected void onProgressUpdate(Integer... item) {
            if(item[1]>1 || !index)
                mTextView.setVisibility(View.INVISIBLE);
            if(index){
                mTextView.setVisibility(View.VISIBLE);
                mTextView.setText(String.valueOf(item[0]));
                mTextAll.setText(String.valueOf(item[1]));
            }


        }
    }
}
