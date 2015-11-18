package com.example.seohyun.ohohoh;

/**
 * Created by SeoHyun on 15. 11. 18..
 */
public class alarmData {
    public String Day;
    public String Hour;
    public String Del = ":";
    public String Min ;
    public String ImgButton;

    public alarmData(String day, String hour, String del, String min, String onButton){
        Day = day;
        Hour = hour;
        Del = ":";
        Min = min;
        ImgButton = onButton;
    }
}
