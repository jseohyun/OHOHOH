package com.example.seohyun.ohohoh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by SeoHyun on 15. 11. 25..
 */
public class Rain extends View {
    float dx = 0.0f;
    float dy = 1.0f;
    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    int ranValuesX[] = new int[10];
    int ranValuesY[] = new int[10];

    public Rain(Context context){
        super(context);


    }
    public void makeRain(){

        for(int i = 0; i<10; i++){

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.waterdrop);
            bitmap = Bitmap.createScaledBitmap(bitmap,100, 100, true);

            System.out.println(bitmap.getConfig());

            bitmaps.add(bitmap);
            ranValuesX[i] = (int)(Math.random() * 800 + 50);
            ranValuesY[i] = (int)(Math.random() * 800 + 50);

        }
    }
    public void draw(Canvas canvas){

        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.waterdrop);
       // bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        //Bitmap.createScaledBitmap((bitmap.getBitmap() , 가로크기, 세로크기 , true);
        //canvas.drawBitmap(bitmap,100,100,null);

        //makeRain();
        canvas.translate(dx, dy);
        for(int i = 0; i<10; i++) {

            canvas.drawBitmap(bitmaps.get(i), ranValuesX[i], ranValuesY[i], null);
        }
        dy++;
        //invalidate();


    }

}
