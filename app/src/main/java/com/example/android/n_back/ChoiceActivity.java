package com.example.android.n_back;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }
    public void ClickChooseMatrix(View view){
        Intent intent = new Intent(this, GameMatrixActivity.class);
        startActivity(intent);
    }
    public void ClickChooseOneItem(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
