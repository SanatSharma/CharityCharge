package com.example.sanat.charitycharge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by sanat on 1/27/2018.
 */


public class ImageBackground extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage = null;
    private boolean setOnFinish = false;
    private Bitmap finalImage = null;

    public ImageBackground(boolean setOnFinish) {
        this.setOnFinish = setOnFinish;
    }

    public ImageBackground(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public ImageBackground(ImageView bmImage, boolean setOnFinish) {
        this.setOnFinish = setOnFinish;
        this.bmImage = bmImage;
    }

    public void setImageView(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public void setOnFinish(boolean bool) {
        this.setOnFinish = bool;
    }

    public void update() {
        try {
            update(finalImage);
        } catch (NullPointerException e) {
            Log.e("ImageBackground", "Image probably has not been set yet.");
        }
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        finalImage = result;
        if (setOnFinish) {
            update(finalImage);
        }
        else {
            Log.v("ImageBackground", "Finished processing image, but have not set image yet.");
        }
    }

    protected void update(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}