package main.dlxy.com.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlxy.Dataorigin.Data;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.Map;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/17.
 */

public class SheZhi extends Activity implements View.OnClickListener {
    private ListView lv;
    private TextView text;
    SharedPreferences sp = null;
    ArrayAdapter<String> arrayAdapter;
    String[] shezhi = Data.shezhiData;
    private ProgressDialog dialog;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi_layout);
        initView();

    }

    private void initView() {
        lv= findViewById(R.id.shezhi_lv);
        text = findViewById(R.id.shezhi_text);
        text.setOnClickListener(this);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.kefu_shipei_layout,shezhi);
        lv.setAdapter(arrayAdapter);
        dialog = new ProgressDialog(SheZhi.this);
        adds();

    }

    @Override
    public void onClick(View view) {
        Config.DEBUG = true;
        sp = getSharedPreferences("sp_demo", QQdengru.MODE_PRIVATE);
        sp.edit().putBoolean("boolean",false).commit();
        final boolean isauth = UMShareAPI.get(SheZhi.this).isAuthorize(SheZhi.this, platforms.get(0).mPlatform);
        Toast.makeText(SheZhi.this,"..."+isauth,Toast.LENGTH_SHORT).show();
        if (isauth) {
            UMShareAPI.get(SheZhi.this).deleteOauth(SheZhi.this, platforms.get(0).mPlatform, authListener);
        } else {
//            UMShareAPI.get(SheZhi.this).doOauthVerify(SheZhi.this, platforms.get(0).mPlatform, authListener);

        }
        Intent intent = new Intent(SheZhi.this,MainActivity.class);
        Bundle b = new Bundle();
        b.putString("name","shezhi");

        intent.putExtras(b);
        startActivity(intent);

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
//            Toast.makeText(SheZhi.this, "成功了", Toast.LENGTH_LONG).show();

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
//            Toast.makeText(SheZhi.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(SheZhi.this, "取消了", Toast.LENGTH_LONG).show();
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
}
