package edu.mypayments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by Александр on 07.10.2016.
 */
public class PictureUtils {
    public static Bitmap getScaledBitmap (String path, int destWidth, int destHeight){
        //Чтение размеров изображения с диска
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeidth = options.outHeight;

        //Вычисление степени маштабирования
        int inSamplSize = 1;
        if (srcHeidth > destHeight || srcWidth > destWidth) {
            if (srcWidth > srcHeidth) {
                inSamplSize = Math.round(srcHeidth / srcWidth);
            } else {
                inSamplSize = Math.round(srcWidth / srcHeidth);
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSamplSize;

        //Чтение данных и создание готовых изображений
        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
}
