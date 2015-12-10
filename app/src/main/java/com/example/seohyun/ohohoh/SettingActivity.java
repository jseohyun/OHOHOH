package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;



/**
 * Created by SeoHyun on 15. 11. 26..
 */
public class SettingActivity extends AppCompatActivity {
    SetVariable sv = SetVariable.getInstance();
    ImageButton soundBtn;
    ImageButton setNumBtn;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        soundBtn = (ImageButton)findViewById(R.id.soundButton);
        setNumBtn = (ImageButton)findViewById(R.id.setNumButton);


    }
    public void setSoundActivity(View view){
        Intent intent = new Intent(this,SetSoundActivity.class);
        finish();
        startActivity(intent);
    }
    public void setPhNumActivity(View view){
        Intent intent = new Intent(this,SetPhNumActivity.class);
        finish();
        startActivity(intent);
    }
    public void mainActivity(View view){
        Intent intent = new Intent(this,MainActivity.class);
        finish();;
        startActivity(intent);
    }
}
