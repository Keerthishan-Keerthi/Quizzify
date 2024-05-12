package com.example.qizzify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @SuppressLint("NonConstantResourceId")
    public void main_btn(View view){
        switch (view.getId()){
            case R.id.btn_play:
                startActivity(new Intent(MainActivity.this , playActivity.class));
                break;
            case R.id.btn_scores:
                startActivity(new Intent(MainActivity.this , resultsActivity.class));
                break;
            case R.id.btn_exit:
                this.finishAffinity();
                break;
        }
    }
}