package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by SeoHyun on 15. 11. 19..
 */
public class AddActivity extends AppCompatActivity {
    public static final int DEFAULT_ALARM_REQUEST = 800;

    private TimePicker timePicker;
    private ImageButton settingBtn;
    private int hour;
    private int min;
    public String Hour;
    public String Min;



    private AlarmManager alarmManager;
    GregorianCalendar currentCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
        settingBtn = (ImageButton)findViewById(R.id.settingBtn);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        //Intent intent = getIntent();
        alarmManager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setTimeZone("GMT");
        //Intent intent = new Intent(this,GameActivity.class);




        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                hour = hourOfDay;
                if(hour>12)
                    Hour = String.format("%d",hourOfDay-12);
                else
                    Hour = String.format("%d",hourOfDay);
                min = minute;
                Min = String.format("%d",minute);
                Log.d("Hour" , Hour);
                Log.d("Min",Min);

            }
        });
    }
    //        m_arr.add(new AlarmData("PM","10",":","45","@drawable/on_button"));

    public void mainActivity(View view){
        ArrayList<AlarmData> list = AlarmList.getAlarmList();
        String Day;
        Intent intent = new Intent(this,MainActivity.class);
        int reqCode = DEFAULT_ALARM_REQUEST+list.size();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        int currentYY = currentCalendar.get(Calendar.YEAR);
        int currentMM = currentCalendar.get(Calendar.MONTH);
        int currentDD = currentCalendar.get(Calendar.DAY_OF_MONTH);


        gregorianCalendar.set(currentYY, currentMM, currentDD, hour, min,00);

        if(gregorianCalendar.getTimeInMillis() < currentCalendar.getTimeInMillis()){
            gregorianCalendar.set(currentYY, currentMM, currentDD+1, hour, min,00);
            Log.i("TAG",gregorianCalendar.getTimeInMillis()+":");
        }




        if(hour>=12)
            Day = "PM";
        else
            Day = "AM";
        AlarmData alarmData = new AlarmData(Day, Hour,":",Min,"@drawable/on_button");
        alarmData.hour = hour;
        alarmData.min = min;
        alarmData.reqCode = reqCode;
        list.add(alarmData);

        Intent gIntent = new Intent(this,GameActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,reqCode, gIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, gregorianCalendar.getTimeInMillis() ,pi);
        System.out.println(gregorianCalendar.getTimeInMillis());
        finish();
        startActivity(intent);

    }
}
