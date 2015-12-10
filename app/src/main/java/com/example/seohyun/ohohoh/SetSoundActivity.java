package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by SeoHyun on 15. 11. 26..
 */
public class SetSoundActivity extends AppCompatActivity {
    SetVariable sv = SetVariable.getInstance();
    ImageButton rainBtn;
    ImageButton snowBtn;
    ImageButton warmBtn;
    ImageButton coldBtn;
    int menuNum=0;//1=rain, 2= snow, 3=warm, 4= cold

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_sound);
        rainBtn = (ImageButton)findViewById(R.id.rainBtn);
        snowBtn = (ImageButton)findViewById(R.id.snowBtn);
        warmBtn = (ImageButton)findViewById(R.id.warmBtn);
        coldBtn = (ImageButton)findViewById(R.id.coldBtn);




    }


    public void mOnClick(View view){
        switch (view.getId()){
            case R.id.rainBtn:
                setRainySoundActivity();
                break;
            case R.id.snowBtn:
                setSnowySoundActivity();
                break;

            case R.id.warmBtn:
                setWarmSoundActivity();
                break;
            case R.id.coldBtn:
                setColdSoundActivity();
                break;
        }
    }
   public void setWarmSoundActivity(){
        Intent intent = new Intent(this,SetWarmSoundActivity.class);
        finish();
        startActivity(intent);
    }
    public void setColdSoundActivity(){
        Intent intent = new Intent(this,SetColdSoundActivity.class);
        finish();
        startActivity(intent);
    }
    public void setSnowySoundActivity(){
        Intent intent = new Intent(this,SetSnowySoundActivity.class);
        finish();
        startActivity(intent);
    }
    public void setRainySoundActivity(){
        Intent intent = new Intent(this,SetRainySoundActivity.class);
        finish();
        startActivity(intent);
    }




}
