package com.example.seohyun.ohohoh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   // writeData();

        mContext = getApplicationContext();
        setList();
        listUpdate();
        addBtn = (ImageButton)findViewById(R.id.addButton);


    }
    protected void onStop(){
        super.onPause();
    }
    public void addActivity(View view){
        Intent intent = new Intent(this,AddActivity.class);
//
        finish();
        startActivity(intent);

    }
    /*
    public void writeData() {

        try{
            FileWriter listFile = new FileWriter("ListAlarm.txt");
            listFile.write("ejijwat");
            listFile.write("35");
            System.out.println("된댜ㅏ!");

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    */

    //alarmData(String day, String hour, String del, String min, String onButton)
    private void setList(){
        //m_arr = new ArrayList<AlarmData>();
        m_arr = AlarmList.getAlarmList();
        ListView listView = (ListView)findViewById(R.id.listView);
        /*
        m_arr.add(new AlarmData("PM","10",":","45","@drawable/on_button"));
        m_arr.add(new AlarmData("PM","11",":","45","@drawable/off_button"));
        m_arr.add(new AlarmData("AM","10",":","45","@drawable/off_button"));
        */



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
