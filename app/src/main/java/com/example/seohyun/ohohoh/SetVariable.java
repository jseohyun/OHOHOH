package com.example.seohyun.ohohoh;

import java.util.ArrayList;

/**
 * Created by SeoHyun on 15. 11. 26..
 */
public class SetVariable {
    private String phNum = new String("");
    private String rainSound = new String("rain1");
    private String snowSound = new String("snow1");
   private String warmSound = new String("warm1");
   private String coldSound = new String("cold1");

    private int yTemp=20;
    private static SetVariable sv = new SetVariable();
    private SetVariable(){}
    public static SetVariable getInstance(){
    return sv;
    }
    public void setPhNum(String PhNum){
        sv.phNum = PhNum;
    }

    public String getPhNum(){
        return sv.phNum;
    }

    public void setRainSound(String rainSound) {
        sv.rainSound = rainSound;
    }
    public String getRainSound(){
        return sv.rainSound;
    }
    public void setSnowSound(String snowSound) {
        sv.snowSound = snowSound;
    }
    public String getSnowSound(){
        return sv.snowSound;
    }
    public void setWarmSound(String warmSound) {
        sv.warmSound = warmSound;
    }
    public String getWarmSound(){
        return sv.warmSound;
    }
    public void setColdSound(String coldSound) {
        sv.coldSound = coldSound;
    }
    public String getcoldSound(){
        return sv.coldSound;
    }
    public void setyTemp(int yTemp){sv.yTemp = yTemp;};
    public int getyTemp(){return sv.yTemp;};
}

