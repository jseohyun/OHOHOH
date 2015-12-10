package com.example.seohyun.ohohoh;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<AlarmData> m_arr;
    private List_Adapter adapter;
    private ImageButton addBtn;
    private ImageButton setBtn,callBtn;
    public Context mContext;
    SetVariable sv = SetVariable.getInstance();
    private String phNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        SoundPool sound = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        mSound = sound.load(this,R.raw.obliviate,1);
        sound.play(mSound, 1, 1, 1, -1, 1);
*/
   // writeData();

        mContext = getApplicationContext();
        setList();
        listUpdate();
        addBtn = (ImageButton)findViewById(R.id.addButton);
        setBtn = (ImageButton)findViewById(R.id.settingButton);
        callBtn = (ImageButton)findViewById(R.id.callBtn);


    }
    public void call(View view){
        phNum = sv.getPhNum();
        Log.d("Main_phNum", phNum);

        try {

            Intent callIntent = new Intent(Intent.ACTION_DIAL);

            callIntent.setData(Uri.parse("tel:" + phNum));

            startActivity(callIntent);

        } catch (ActivityNotFoundException e) {

            Log.e("전화걸기", "전화걸기에 실패했습니다", e);

        }
    }

    public void addActivity(View view){
       // Intent intent = new Intent(this,AddActivity.class);
        Intent intent = new Intent(this,AddActivity.class);
//
        finish();
        startActivity(intent);

    }
    public void settingActivity(View view){
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }

    private void setList(){

        m_arr = AlarmList.getAlarmList();
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new List_Adapter(MainActivity.this, m_arr,mContext);
            listView.setAdapter(adapter);


    }
    public void listUpdate(){
        adapter.notifyDataSetChanged(); //​리스트뷰 값들의 변화가 있을때 아이템들을 다시 배치 할 때 사용되는 메소드입니다.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
