package com.flyvr.chess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by android studio
 * author:chen
 */
public final class ChessBitmapUtil {
    private ChessBitmapUtil(){}

    public static Bitmap getBitmap(String assetsName){
        try {
            InputStream is =  MyApp.getInstance().getAssets().open(assetsName);
            return BitmapFactory.decodeStream(is);
        } catch (IOException pE) {
            pE.printStackTrace();
        }
        return null;
    }
}
