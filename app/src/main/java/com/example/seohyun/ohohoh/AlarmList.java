package com.example.seohyun.ohohoh;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by SeoHyun on 15. 11. 21..
 */
public class AlarmList {
//싱글톤 디자인 패턴
    public File listFile;
    private static ArrayList<AlarmData> list = new ArrayList();
    private static AlarmList alarmList = new AlarmList();
    public static ArrayList<AlarmData> getAlarmList(){
        return list;
    }

    public void addAlarm(AlarmData alarmData){

        list.add(alarmData);
    }



/*
    public void delAlarm(){

    }
*/

}
