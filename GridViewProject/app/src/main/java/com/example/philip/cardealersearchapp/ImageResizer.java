/*
 * Copyright (c) 2017.
 * Author: Philip Joseph Thomas
 */

package com.example.philip.cardealersearchapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to resize the images and hold a single reference
 * to the list of resized images. This approach reuses the static list
 * as resizing task is expensive and can cause delay for a large list
 * of images
 */

public class ImageResizer {

    // width for resized image
    public static final int RESIZED_WIDTH = 1280;
    //height for resized image
    public static final int RESIZED_HEIGHT = 720;
    // static field to hold the list of resized images
    public static List<Bitmap> resizedBmsList = new ArrayList<>();

    // this method resizes the images
    public static void resizeImages(Context mContext, ArrayList<Integer> mCarsHDImgIds) {
        // get bitmap factory options
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // set inSampleSize=4 to get the decoded image size scaled down by one-fourth
        opts.inSampleSize = 4;

        for (Integer resId : mCarsHDImgIds) {
            // convert resource id to bitmap for downsizing image for thumbnails
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), resId, opts);
            // resize the decoded image to 1280 x 720
            Bitmap resizedBm = Bitmap.createScaledBitmap(bm, RESIZED_WIDTH, RESIZED_HEIGHT, true);
            resizedBmsList.add(resizedBm);

        }
    }
}
