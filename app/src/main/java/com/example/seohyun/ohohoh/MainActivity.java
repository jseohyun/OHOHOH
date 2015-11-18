package com.example.seohyun.ohohoh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<alarmData> m_arr;
    private List_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setList();
        listUpdate();

    }
    //alarmData(String day, String hour, String del, String min, String onButton)
    private void setList(){
        m_arr = new ArrayList<alarmData>();
        ListView listView = (ListView)findViewById(R.id.listView);
        m_arr.add(new alarmData("PM","10",":","45","@drawable/on_button"));
        m_arr.add(new alarmData("PM","11",":","45","@drawable/off_button"));
        m_arr.add(new alarmData("AM","10",":","45","@drawable/off_button"));



        adapter = new List_Adapter(MainActivity.this, m_arr);
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
