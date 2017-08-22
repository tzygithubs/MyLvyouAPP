package main.dlxy.com.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dlxy.Utils.Receiver;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/8/18.
 */

public class QQdengru extends Activity {
    private static final String TAG = "QQdengru";
    private ProgressDialog dialog;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    SharedPreferences sp = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qqdengru);
        dialog = new ProgressDialog(QQdengru.this);
        Config.DEBUG = true;
        adds();
        final boolean isauth = UMShareAPI.get(QQdengru.this).isAuthorize(QQdengru.this, platforms.get(0).mPlatform);
        Toast.makeText(QQdengru.this,"........boolean...:"+isauth,Toast.LENGTH_SHORT).show();
        findViewById(R.id.qqdengru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isauth) {
                    UMShareAPI.get(QQdengru.this).deleteOauth(QQdengru.this, platforms.get(0).mPlatform, authListener);
                } else {
                    UMShareAPI.get(QQdengru.this).doOauthVerify(QQdengru.this, platforms.get(0).mPlatform, authListener);

                }
            }
        });
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(QQdengru.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(QQdengru.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(QQdengru.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    UMAuthListener authListeners = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            String temp = "";
            String url=null;
            String name = null;
            String yonghuming = null;
            for (String key : data.keySet()) {
                temp = temp + key + " : " + data.get(key) + "\n";
                if (key.equals("profile_image_url")){
                    url = data.get(key);

                    Log.i(TAG,"........temp....:"+data.get(key));
                }
                if (key.equals("uid")){
                    name = data.get(key);

                    Log.i(TAG,"........temp....:"+data.get(key));
                }
                if (key.equals("screen_name")){
                    yonghuming = data.get(key);
                }
            }
            Log.i(TAG,"........temp:"+temp);
            sp = getSharedPreferences("sp_demo", QQdengru.MODE_PRIVATE);


            Log.i(TAG,".........................names"+name);
            if ( name != null){

                sp.edit().putString("name",name)
                        .putBoolean("boolean",true)
                        .putString("yonghuming",yonghuming)
                        .putString("url",url).commit();
            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {

        }
    };
    private void adds() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(QQdengru.this).getPlatformInfo(QQdengru.this, SHARE_MEDIA.QQ, authListeners);

        iten();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    public void iten(){

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(QQdengru.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putString("name","shezhi");

                intent.putExtras(b);
                startActivity(intent);

            }
        };
        timer.schedule(task, 2000);
    }
}
