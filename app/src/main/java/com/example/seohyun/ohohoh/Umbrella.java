package com.example.seohyun.ohohoh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by SeoHyun on 15. 11. 25..
 */
public class Umbrella extends View {

    public Umbrella(Context context){
        super(context);

    }

    public void draw(Canvas canvas){



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.umbrella);
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);


        //bitmap.setConfig(Bitmap.Config.ARGB_4444);
       // System.out.println(bitmap.getConfig());
        //Bitmap.createScaledBitmap((bitmap.getBitmap() , 가로크기, 세로크기 , true);
        canvas.drawBitmap(bitmap, 100, 800, null);



    }

    public static Bitmap loadBitmap(Context context){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.drawable.umbrella);
        BitmapFactory.decodeFile("/Users/SeoHyun/AndroidStudioProjects/OHOHOH/app/src/main/res/drawable/umbrella.png", options);

        return BitmapFactory.decodeFile("/Users/SeoHyun/AndroidStudioProjects/OHOHOH/app/src/main/res/drawable/umbrella.png", options);
    }

}
