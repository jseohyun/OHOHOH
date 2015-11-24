package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by SeoHyun on 15. 11. 18..
 */
public class List_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<AlarmData> arr;
    public Context mContext;
    public AlarmManager alarmManager;
    GregorianCalendar currentCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    public List_Adapter(Activity act, ArrayList<AlarmData> arr_item,Context context) {
        mContext = context;
        this.m_activity = act;
        arr = arr_item;
        mInflater = (LayoutInflater) m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public int getCount() {
        return arr.size();
    }
    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }
    public long getItemId(int position){
        return position;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            int res = 0;
            res = R.layout.list_item;
            convertView = mInflater.inflate(res, parent, false);
        }


        final ImageButton sButton = (ImageButton)convertView.findViewById(R.id.sButton);
        TextView dayView = (TextView)convertView.findViewById(R.id.dayView);
        TextView hourView = (TextView)convertView.findViewById(R.id.hourView);
        TextView delView = (TextView)convertView.findViewById(R.id.delView);
        TextView minView = (TextView)convertView.findViewById(R.id.minView);
        LinearLayout layout_view =  (LinearLayout)convertView.findViewById(R.id.view);

        dayView.setText(arr.get(position).Day);
        hourView.setText(arr.get(position).Hour);
        delView.setText(arr.get(position).Del);
        minView.setText(arr.get(position).Min);
        int resId=  m_activity.getResources().getIdentifier(arr.get(position).ImgButton,"drawable", m_activity.getPackageName());
        sButton.setImageResource(resId);
        //android:padding="0sp"
       // android:scaleType="centerCrop"
        //sButton.setPadding(0,0,0,0);
        sButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        sButton.setBackgroundColor(0000);
        alarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        sButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(arr.get(position).isOFF()==false){
                    sButton.setImageResource(R.drawable.off_button);
                    arr.get(position).ImgButton = "off_button";
                    arr.get(position).setOff(true);
                    Intent gIntent = new Intent(mContext,GameActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(mContext,arr.get(position).reqCode, gIntent,PendingIntent.FLAG_CANCEL_CURRENT);
                    alarmManager.cancel(pi);

            }

                else{
                    GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    int currentYY = currentCalendar.get(Calendar.YEAR);
                    int currentMM = currentCalendar.get(Calendar.MONTH);
                    int currentDD = currentCalendar.get(Calendar.DAY_OF_MONTH);

                    gregorianCalendar.set(currentYY, currentMM, currentDD, arr.get(position).hour, arr.get(position).min,00);

                    if(gregorianCalendar.getTimeInMillis() < currentCalendar.getTimeInMillis()){
                        gregorianCalendar.set(currentYY, currentMM, currentDD+1, arr.get(position).hour, arr.get(position).min,00);
                        Log.i("TAG", gregorianCalendar.getTimeInMillis() + ":");
                    }
                    sButton.setImageResource(R.drawable.on_button);
                    arr.get(position).ImgButton = "on_button";
                    arr.get(position).setOff(false);
                    Intent gIntent = new Intent(mContext,GameActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(mContext,arr.get(position).reqCode, gIntent,PendingIntent.FLAG_CANCEL_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, gregorianCalendar.getTimeInMillis() ,pi);
                }
            }
        });


        return convertView;
    }

}
