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


        sv.setRainSound("raiin2");
        Log.d("비소리", sv.getRainSound());


    }
    /*
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        if(menuNum==1)
            inflater.inflate(R.menu.raincheck,menu);
        else if(menuNum==2)
            inflater.inflate(R.menu.snowcheck,menu);
        else if(menuNum==3)
            inflater.inflate(R.menu.warmcheck,menu);
        else if(menuNum==4)
            inflater.inflate(R.menu.coldcheck,menu);

        return true;
    }
    */

    public void mOnClick(View view){
        switch (view.getId()){
            case R.id.rainBtn:

                break;
            case R.id.snowBtn:
                menuNum = 2;

            case R.id.warmBtn:
                setWarmSoundActivity();
                break;
            case R.id.coldBtn:

                break;
        }
    }
    public void setWarmSoundActivity(){
        Intent intent = new Intent(this,SetWarmSoundActivity.class);
        finish();
        startActivity(intent);
    }
}
