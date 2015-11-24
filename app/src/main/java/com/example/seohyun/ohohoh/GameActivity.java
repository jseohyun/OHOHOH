package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.app.AlarmManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by SeoHyun on 15. 11. 21..
 */
public class GameActivity extends Activity implements CompleteRequest {
    AlarmManager alarmManager;

    public void complete(String a, String b){
        Log.d(a, a);
        Log.d(b, b);
    }
    private void get(){
        new SHRequest().execute(this, null, null);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        get();


        Log.d("아", "ㅋㅋ");
//        finish();
    }
}
