package com.example.seohyun.ohohoh;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SeoHyun on 15. 11. 21..
 */
public class GameActivity extends AppCompatActivity implements CompleteRequest {

    private Rain rain;
    private Umbrella umbrella;
    private String weather = "Rainy";
    private String temp;
    private GraphicsView graphicsView;
    private LinearLayout.LayoutParams param;
    private LinearLayout layout;
    private ImageButton btn1,btn2,btn3,btn4,btn5;
    private ImageView bar1,bar2,bar3,bar4,mom,cloud,tempView;
    private int i=0;
    private int Temp;
    private int yTemp ;



    SetVariable sv = SetVariable.getInstance();


    AlarmManager alarmManager;
    MediaPlayer mSound = new MediaPlayer();



    public void complete(String weather, String temp){
        Log.d(weather, weather);
        Log.d(temp, temp);
        this.weather = weather;
        //this.weather = "Rainy";
        this.temp = temp;
        this.Temp = Integer.parseInt(this.temp);
        this.yTemp = sv.getyTemp();
        //this.yTemp = Temp + 1;
        playSound(yTemp, Temp, weather);


    }
    private void get(){
       new SHRequest().execute(this, null, null);
    }
    public void playSound(int yTemp, int Temp, String weather){
        if(weather.contains("Rain")){
            if(sv.getRainSound().equals("rain1")){
                mSound = MediaPlayer.create(this,R.raw.rain1);
                Log.d("game rain","rain1");

            }
            else if(sv.getRainSound().contains("rain2")){
                mSound = MediaPlayer.create(this,R.raw.warm2);
                Log.d("game rain","rain2");

            }else if(sv.getRainSound().equals("rain3")){
                mSound = MediaPlayer.create(this,R.raw.rain3);
                Log.d("game rain","rain3");

            }

        }

        else if(weather.contains("Snow")){
            if(sv.getSnowSound().equals("snow1")){
                mSound = MediaPlayer.create(this,R.raw.snow1);
                Log.d("game snow","snow1");

            }
            else if(sv.getSnowSound().contains("snow2")){
                mSound = MediaPlayer.create(this,R.raw.snow2);
                Log.d("game snow","snow2");

            }else if(sv.getSnowSound().equals("snow3")){
                mSound = MediaPlayer.create(this,R.raw.snow3);
                Log.d("game snow","snow3");

            }
        }


       else if(yTemp <= Temp){           //warmer than yesterday
            if(sv.getWarmSound().equals("warm1")){
                mSound = MediaPlayer.create(this,R.raw.warm1);
                Log.d("game warm","warm1");

            }
            else if(sv.getWarmSound().contains("warm2")){
                mSound = MediaPlayer.create(this,R.raw.warm2);
                Log.d("game warm","warm2");

            }else if(sv.getWarmSound().equals("warm3")){
                mSound = MediaPlayer.create(this,R.raw.warm3);
                Log.d("game warm","warm3");

            }
        }
        else{
            if(sv.getColdSound().equals("cold1")){
                mSound = MediaPlayer.create(this,R.raw.cold1);
                Log.d("game cold","cold1");

            }
            else if(sv.getColdSound().contains("cold2")){
                mSound = MediaPlayer.create(this,R.raw.cold2);
                Log.d("game cold","cold2");

            }else if(sv.getColdSound().equals("cold3")){
                mSound = MediaPlayer.create(this,R.raw.cold3);
                Log.d("game cold","cold3");

            }
        }
        //mSound = MediaPlayer.create(this,R.raw.obliviate);
        mSound.start();


    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        get();
        mSound.pause();
        setContentView(R.layout.game);
        btn1 = (ImageButton)findViewById(R.id.btn1);
        btn2 = (ImageButton)findViewById(R.id.btn2);
        btn3 = (ImageButton)findViewById(R.id.btn3);
        btn4 = (ImageButton)findViewById(R.id.btn4);
        btn5 = (ImageButton)findViewById(R.id.btn5);
        mom = (ImageView)findViewById(R.id.img1);
        bar1 = (ImageView)findViewById(R.id.img2);
        bar2 = (ImageView)findViewById(R.id.img3);
        bar3 = (ImageView)findViewById(R.id.img4);
        bar4 = (ImageView)findViewById(R.id.img5);
        cloud = (ImageView)findViewById(R.id.cloud);
        tempView = (ImageView)findViewById(R.id.tempView);




        graphicsView = new GraphicsView(this);
        param = new LinearLayout.LayoutParams(1000, 1200);
        param.gravity= Gravity.TOP;
        layout = (LinearLayout) findViewById(R.id.graphicLayout);
        layout.addView(graphicsView, 0, param);


        //MediaPlayer mSound;


        Log.d("아", "ㅋㅋ");
//        finish();
    }
    protected void onPause(){
        super.onPause();
        if(i!=5) {
            mSound.pause();
            finish();
            Intent intent = new Intent("com.android.alrammanager.ACTION_ALARM");
            Intent gIntent = new Intent(this, GameActivity.class);
            PendingIntent sender = PendingIntent.getActivity(this, 0, gIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 10);

            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }

    }
    protected void onStop(){
        super.onStop();
        if(i!=5) {
            mSound.pause();
            finish();
            Intent intent = new Intent("com.android.alrammanager.ACTION_ALARM");
            Intent gIntent = new Intent(this, GameActivity.class);
            PendingIntent sender = PendingIntent.getActivity(this, 0, gIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 10);

            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }

    }
    public void mOnClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                btn1.setClickable(false);
                btn1.setVisibility(View.INVISIBLE);
                i++;
                break;
            case R.id.btn2:
                btn2.setClickable(false);
                btn2.setVisibility(View.INVISIBLE);
                i++;
                break;
            case R.id.btn3:
                btn3.setClickable(false);
                btn3.setVisibility(View.INVISIBLE);
                i++;
                break;
            case R.id.btn4:
                btn4.setClickable(false);
                btn4.setVisibility(View.INVISIBLE);
                i++;
                break;
            case R.id.btn5:
                btn5.setClickable(false);
                btn5.setVisibility(View.INVISIBLE);
                i++;
                break;
        }
    }

    public class GraphicsView extends View {



        public GraphicsView(Context context) {
            super(context);
            rain = new Rain(context);
            rain.makeRain();
            umbrella = new Umbrella(context);

        }
        protected void onDraw(Canvas canvas){

            if(weather.contains("Snow")){
                snowGame(canvas);
            }
            else if(weather.contains("Rain")){
                rainGame(canvas);
            }
            else if(yTemp<Temp)
                warmGame(canvas);
            else coldGame(canvas);
            if(i == 1)
                bar1.setImageResource(R.drawable.a_img);
            else if(i==2)
                bar2.setImageResource(R.drawable.a_img);
            else if(i==3)
                bar3.setImageResource(R.drawable.a_img);
            else if(i==4)
                mom.setImageResource(R.drawable.settint_icon);
            else if(i==5) {
                bar4.setImageResource(R.drawable.a_img);
                mSound.pause();
                sv.setyTemp(Temp);
                finish();
            }






            invalidate();


        }

        public void coldGame(Canvas canvas){
            Paint paint = new Paint(Color.BLACK);
            Bitmap fire = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
            Bitmap fire1,fire2,fire3,fire4,fire5;
            fire1 = Bitmap.createScaledBitmap(fire, 100, 100, true);
            fire2 = Bitmap.createScaledBitmap(fire, 100, 100, true);
            fire3 = Bitmap.createScaledBitmap(fire, 100, 100, true);
            fire4 = Bitmap.createScaledBitmap(fire, 100, 100, true);
            fire5 = Bitmap.createScaledBitmap(fire, 100, 100, true);


            Rect fireBox = new Rect(400,200,700,220);

            if(i==1) {
                canvas.drawBitmap(fire1, 600, 100, null);//left,top

            }else if(i==2){
                canvas.drawBitmap(fire1, 600, 100, null);//left,top
                canvas.drawBitmap(fire2, 550, 50, null);//left,top

            }else if(i==3){
                canvas.drawBitmap(fire1, 600, 100, null);//left,top
                canvas.drawBitmap(fire2, 550, 50, null);//left,toㅔ
                canvas.drawBitmap(fire3, 500, 100, null);//left,top


            }else if(i==4){
                canvas.drawBitmap(fire1, 600, 100, null);//left,top
                canvas.drawBitmap(fire2, 550, 50, null);//left,top
                canvas.drawBitmap(fire3, 500, 100, null);//left,top
                canvas.drawBitmap(fire4, 450, 50, null);//left,top



            }else if(i==5){
                canvas.drawBitmap(fire1, 600, 100, null);//left,top
                canvas.drawBitmap(fire2, 550, 50, null);//left,top
                canvas.drawBitmap(fire3, 500, 100, null);//left,top
                canvas.drawBitmap(fire4, 450, 50, null);//left,top
                canvas.drawBitmap(fire5, 400, 100, null);//left,top



            }




            //left,top,right,bottom
            canvas.drawRect(fireBox, paint);



        }
        public void warmGame(Canvas canvas){

            Paint paint = new Paint(Color.BLACK);
            Bitmap ice = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_ice);
            Bitmap ice1,ice2,ice3,ice4,ice5;
            ice1 = Bitmap.createScaledBitmap(ice, 100, 100, true);
            ice2 = Bitmap.createScaledBitmap(ice, 100, 100, true);
            ice3 = Bitmap.createScaledBitmap(ice, 100, 100, true);
            ice4 = Bitmap.createScaledBitmap(ice, 100, 100, true);
            ice5 = Bitmap.createScaledBitmap(ice, 100, 100, true);

            btn1.setImageResource(R.drawable.rsz_ice);
            btn2.setImageResource(R.drawable.rsz_ice);
            btn3.setImageResource(R.drawable.rsz_ice);
            btn4.setImageResource(R.drawable.rsz_ice);
            btn5.setImageResource(R.drawable.rsz_ice);
            cloud.setImageResource(R.drawable.rsz_sunny);
            tempView.setImageResource(R.drawable.rsz_hot);


            Rect fireBox = new Rect(400,200,700,220);

            if(i==1) {
                canvas.drawBitmap(ice1, 600, 100, null);//left,top

            }else if(i==2){
                canvas.drawBitmap(ice1, 600, 100, null);//left,top
                canvas.drawBitmap(ice2, 550, 50, null);//left,top

            }else if(i==3){
                canvas.drawBitmap(ice1, 600, 100, null);//left,top
                canvas.drawBitmap(ice2, 550, 50, null);//left,toㅔ
                canvas.drawBitmap(ice3, 500, 100, null);//left,top


            }else if(i==4){
                canvas.drawBitmap(ice1, 600, 100, null);//left,top
                canvas.drawBitmap(ice2, 550, 50, null);//left,top
                canvas.drawBitmap(ice3, 500, 100, null);//left,top
                canvas.drawBitmap(ice4, 450, 50, null);//left,top



            }else if(i==5){
                canvas.drawBitmap(ice1, 600, 100, null);//left,top
                canvas.drawBitmap(ice2, 550, 50, null);//left,top
                canvas.drawBitmap(ice3, 500, 100, null);//left,top
                canvas.drawBitmap(ice4, 450, 50, null);//left,top
                canvas.drawBitmap(ice5, 400, 100, null);//left,top




            }




            //left,top,right,bottom
            canvas.drawRect(fireBox, paint);



        }
        public void snowGame(Canvas canvas){

            Paint paint = new Paint(Color.BLACK);
            Bitmap umbrella = BitmapFactory.decodeResource(getResources(), R.drawable.umbrella);
            Bitmap umbrella1,umbrella2,umbrella3,umbrella4,umbrella5;
            umbrella1 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella2 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella3 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella4 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella5 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);

            btn1.setImageResource(R.drawable.umbrella);
            btn2.setImageResource(R.drawable.umbrella);
            btn3.setImageResource(R.drawable.umbrella);
            btn4.setImageResource(R.drawable.umbrella);
            btn5.setImageResource(R.drawable.umbrella);
            cloud.setImageResource(R.drawable.snowy);
            tempView.setImageResource(R.drawable.rsz_cold);


            Rect fireBox = new Rect(400,200,700,220);

            if(i==1) {
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top

            }else if(i==2){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,top

            }else if(i==3){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,toㅔ
                canvas.drawBitmap(umbrella3, 500, 100, null);//left,top


            }else if(i==4){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,top
                canvas.drawBitmap(umbrella3, 500, 100, null);//left,top
                canvas.drawBitmap(umbrella4, 450, 50, null);//left,top



            }else if(i==5){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,top
                canvas.drawBitmap(umbrella3, 500, 100, null);//left,top
                canvas.drawBitmap(umbrella4, 450, 50, null);//left,top
                canvas.drawBitmap(umbrella5, 400, 100, null);//left,top


            }

            //left,top,right,bottom
            canvas.drawRect(fireBox, paint);

        }

        public void rainGame(Canvas canvas){

            Paint paint = new Paint(Color.BLACK);
            Bitmap umbrella = BitmapFactory.decodeResource(getResources(), R.drawable.umbrella);
            Bitmap umbrella1,umbrella2,umbrella3,umbrella4,umbrella5;
            umbrella1 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella2 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella3 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella4 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);
            umbrella5 = Bitmap.createScaledBitmap(umbrella, 100, 100, true);

            btn1.setImageResource(R.drawable.umbrella);
            btn2.setImageResource(R.drawable.umbrella);
            btn3.setImageResource(R.drawable.umbrella);
            btn4.setImageResource(R.drawable.umbrella);
            btn5.setImageResource(R.drawable.umbrella);
            cloud.setImageResource(R.drawable.rainy);
            tempView.setImageResource(R.drawable.rsz_hot);


            Rect fireBox = new Rect(400,200,700,220);

            if(i==1) {
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top

            }else if(i==2){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,top

            }else if(i==3){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,toㅔ
                canvas.drawBitmap(umbrella3, 500, 100, null);//left,top


            }else if(i==4){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,top
                canvas.drawBitmap(umbrella3, 500, 100, null);//left,top
                canvas.drawBitmap(umbrella4, 450, 50, null);//left,top



            }else if(i==5){
                canvas.drawBitmap(umbrella1, 600, 100, null);//left,top
                canvas.drawBitmap(umbrella2, 550, 50, null);//left,top
                canvas.drawBitmap(umbrella3, 500, 100, null);//left,top
                canvas.drawBitmap(umbrella4, 450, 50, null);//left,top
                canvas.drawBitmap(umbrella5, 400, 100, null);//left,top


            }

            //left,top,right,bottom
            canvas.drawRect(fireBox, paint);

        }



    }

}

