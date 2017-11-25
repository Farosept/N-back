package com.example.android.n_back;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMatrixActivity extends AppCompatActivity {
    TextView mTextView;
    TextView mTextTimer;
    TextView mTextResult;
    TextView mTextBad;
    TextView mTextAll;
    GameLogic game;
    infoResults results;
    ArrayList<ImageView> imageViewList;
    AsyncTask<Void, Integer, Void> task;
    boolean buttonPressed;
    boolean index=true;
    boolean key=true;
    int randBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_matrix);

        mTextAll = (TextView) findViewById(R.id.text_all);
        mTextBad = (TextView) findViewById(R.id.miss_text);
        mTextResult = (TextView) findViewById(R.id.good_text);

        imageViewList = new ArrayList<>();
        imageViewList.add((ImageView)findViewById(R.id.image_table_1));
        imageViewList.add((ImageView)findViewById(R.id.image_table_2));
        imageViewList.add((ImageView)findViewById(R.id.image_table_3));
        imageViewList.add((ImageView)findViewById(R.id.image_table_4));
        imageViewList.add((ImageView)findViewById(R.id.image_table_5));
        imageViewList.add((ImageView)findViewById(R.id.image_table_6));
        imageViewList.add((ImageView)findViewById(R.id.image_table_7));
        imageViewList.add((ImageView)findViewById(R.id.image_table_8));
        imageViewList.add((ImageView)findViewById(R.id.image_table_9));
        game = new GameLogic(2);
        task = new ProgressTask().execute();
    }
    public void ClickOnTable(View view){
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
                int rand = random.nextInt(8);
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
            imageViewList.get(randBack).setImageResource(R.drawable.box);
            if(index){
                mTextAll.setText(String.valueOf(item[1]));
            imageViewList.get(item[0]).setImageResource(R.drawable.p42);
            randBack = item[0];
            }
        }
    }
}
