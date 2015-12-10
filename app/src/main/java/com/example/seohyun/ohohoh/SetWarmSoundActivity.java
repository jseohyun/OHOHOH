package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

/**
 * Created by SeoHyun on 15. 11. 27..
 */
public class SetWarmSoundActivity extends AppCompatActivity {
    SetVariable sv = SetVariable.getInstance();
    ImageButton settingBtn;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.warm_sounds);
        settingBtn = (ImageButton)findViewById(R.id.settingBtn);
        RadioGroup group = (RadioGroup)findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getId()==R.id.radioGroup){
                    switch (checkedId){
                        case R.id.warmSound1:
                            sv.setWarmSound("warm1");
                            break;
                        case R.id.warmSound2:
                            sv.setWarmSound("warm2");
                            break;
                        case R.id.warmSound3:
                            sv.setWarmSound("warm3");
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
