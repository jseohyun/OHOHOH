package com.example.seohyun.ohohoh;

import android.app.Activity;
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

import java.util.ArrayList;

/**
 * Created by SeoHyun on 15. 11. 18..
 */
public class List_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<alarmData> arr;
    public List_Adapter(Activity act, ArrayList<alarmData> arr_item) {
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
        ImageButton sButton = (ImageButton)convertView.findViewById(R.id.sButton);
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

  /*  버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.
       *   Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들
       *   이벤트처리를 할 수 있습니다.
   */
        layout_view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                GoIntent(position);
            }
        });
        return convertView;
    }
    public void GoIntent(int a){
        Intent intent = new Intent(m_activity, NothingActivity.class); //MainActivity 아니라 가고싶은 클래스 써야함
        //putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.
        intent.putExtra("Hour", arr.get(a).Hour);
        intent.putExtra("Minute", arr.get(a).Min);
        m_activity.startActivity(intent);

    }

}
