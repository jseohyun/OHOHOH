package com.example.seohyun.ohohoh;

/**
 * Created by SeoHyun on 15. 11. 18..
 */
public class AlarmData {

    public String Day;
    public String Hour;
    public int hour;
    public String Del = ":";
    public String Min ;
    public int min;
    public String ImgButton;
    public int reqCode;
    private boolean OFF_FLAG;

    public AlarmData(String day, String hour, String del, String min, String onButton){
        this.Day = day;
        Hour = hour;
        Del = del;
        Min = min;
        OFF_FLAG = false;
        ImgButton = onButton;
    }

    public boolean isOFF(){
        return OFF_FLAG;
    }
    public void setOff(Boolean flag){
        OFF_FLAG=flag;
    }
}
