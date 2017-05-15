package com.juventudrd.hsantana.vozciudadana.infraestructure.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.juventudrd.hsantana.vozciudadana.R;

import java.lang.ref.WeakReference;

/**
 * Created by hsantana on 1/12/2017.
 */

public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
    private WeakReference<ImageView> imageViewReference = null;
    private Resources _resources;
    private int data = 0;
    private LruCache<String, Bitmap> mMemoryCache;

    private BitmapWorkerTask(Resources resources, ImageView imageView, LruCache<String, Bitmap> memoryCache) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        if (imageView!=null)imageView.setTag("working");
        imageViewReference = new WeakReference<>(imageView);
        _resources = resources;
        mMemoryCache = memoryCache;
    }

    public BitmapWorkerTask(LruCache<String, Bitmap> memoryCache){
        mMemoryCache = memoryCache;
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(Integer... params) {
        data = params[0];
        Bitmap bitmap = decodeSampledBitmapFromResource(_resources, data);
        addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
        return bitmap;
//        return decodeSampledBitmapFromResource(_resources, data, 60, 60);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()){
            bitmap = null;
        }
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
            if (this == bitmapWorkerTask) {
                imageView.setImageBitmap(bitmap);
                imageView.notify();
                imageView.setTag("busy");
            }
        }
    }

    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 2;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public void loadBitmap(Resources res, int resId, ImageView imageView, LruCache<String, Bitmap> memoryCache, boolean questionmark) {
        if (cancelPotentialWork(resId, imageView)) {
            final String imageKey = String.valueOf(resId);
            final Bitmap bitmap = getBitmapFromMemCache(imageKey);
            if (bitmap!=null && imageView!=null){
                imageView.setImageBitmap(bitmap);
            }
            else {
                final BitmapWorkerTask task = new BitmapWorkerTask(res, imageView, memoryCache);
//                final AsyncDrawable asyncDrawable = new AsyncDrawable(res, mPlaceHolderBitmap, task);
//                imageView.setImageDrawable(asyncDrawable);
                if (imageView!=null && questionmark)imageView.setImageResource(R.drawable.question_60);
                task.execute(resId);
            }
        }

//        BitmapWorkerTask task = new BitmapWorkerTask(res, imageView);
//        task.execute(resId);
    }

    private static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == 0 || bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null && mMemoryCache!=null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String key) {
        if (mMemoryCache!=null){
            return mMemoryCache.get(key);
        }
        else {
            return null;
        }
    }
}