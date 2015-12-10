package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by SeoHyun on 15. 11. 26..
 */
public class SetPhNumActivity extends AppCompatActivity {
    private ImageButton setBtn,saveBtn;
    private EditText editText;
    SetVariable sv = SetVariable.getInstance();

    private String phNum;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_phnum);
        setBtn = (ImageButton)findViewById(R.id.settingBtn);
        saveBtn = (ImageButton)findViewById(R.id.saveBtn);
        editText = (EditText)findViewById(R.id.editText);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhNum();
            }
        });
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhNum();
            }
        });
    }
    public void savePhNum(){
        phNum = editText.getText().toString();
        sv.setPhNum(phNum);
        Log.d("phNum", sv.getPhNum());
        finish();
    }

}
