package com.example.seohyun.ohohoh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

/**
 * Created by SeoHyun on 15. 11. 27..
 */
public class SetSnowySoundActivity extends AppCompatActivity {
    SetVariable sv = SetVariable.getInstance();
    ImageButton settingBtn;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.snow_sounds);
        settingBtn = (ImageButton)findViewById(R.id.settingBtn);
        RadioGroup group = (RadioGroup)findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getId()==R.id.radioGroup){
                    switch (checkedId){
                        case R.id.snowSound1:
                            sv.setSnowSound("snow1");
                            break;
                        case R.id.snowSound2:
                            sv.setSnowSound("snow2");
                            break;
                        case R.id.snowSound3:
                            sv.setSnowSound("snow3");
                            break;

                    }
                }
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingActivity();
            }
        });


    }
    public void settingActivity(){
        Intent intent = new Intent(this,SettingActivity.class);
        finish();
        startActivity(intent);
    }
}
