package com.dlxy.Utils;

import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.dlxy.myApplication.MyApplication;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/20.
 */

public class VolleyImageUtils {
    public static void loadImage(String url, View view) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener((ImageView)view, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        MyApplication.getImageLoader().get(url, listener);
    }
}
