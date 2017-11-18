package com.example.android.n_back;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameMatrixActivity extends AppCompatActivity {
    ImageAdapter imageAdapter;
    TextView mTextView;
    TextView mTextTimer;
    TextView mTextResult;
    TextView mTextBad;
    TextView mTextAll;
    GameLogic game;
    infoResults results;
    AsyncTask<Void, Integer, Void> task;
    GridView gridview;
    boolean buttonPressed;
    boolean index=true;
    boolean key=true;
    int randBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_matrix);

        gridview = (GridView) findViewById(R.id.game_gridview);
        gridview.setNumColumns(3);
        imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GameMatrixActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                ClickInGameCheck(v);
            }
        });

        game = new GameLogic(2);
        new ProgressTask().execute();
    }
    public void ClickInGameCheck(View view){
        if(key){
            buttonPressed = true;
            key=false;
            results = game.CheckOnButtonClick(game.getLastIndex());
          //  mTextResult.setText(String.valueOf(results.getRights()));
        //    mTextBad.setText(String.valueOf(results.getBads()));
            buttonPressed=false;
            new ProgressTask().execute();
        }
    }
    private class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
            for(int i=0;!buttonPressed;i++){
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
            imageAdapter.mThumbIds[randBack] = R.drawable.box;
            if(index){
            imageAdapter.mThumbIds[item[0]] = R.drawable.p42;
            randBack = item[0];
            }
            gridview.setAdapter(imageAdapter);
        }
    }
}
