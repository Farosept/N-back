package com.example.android.n_back;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageButton mButtonPlay;

    Intent intent;

    private HelpFragment helpFragment;
    private HomeFragment homeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.container,homeFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_help:
                    transaction.replace(R.id.container,helpFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_statistic:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, GameMatrixActivity.class);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mButtonPlay = (ImageButton) findViewById(R.id.PlayButton);


        helpFragment = new HelpFragment();
        homeFragment = new HomeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,homeFragment);
        transaction.commit();
    }
    public void ClickPlay(View view){
        startActivity(intent);
    }

}
