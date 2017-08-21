package com.dlxy.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dlxy.Utils.ImageUpdateUtil;
import com.dlxy.Utils.VolleyImageUtils;
import com.dlxy.Utils.VolleyUtil;
import com.dlxy.contents.UserContents;
import com.dlxy.interfaces.WodeCallBack;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.dlxy.com.Activity.DengRu;
import main.dlxy.com.Activity.QQdengru;
import main.dlxy.com.Activity.SheZhi;
import main.dlxy.com.mylvyouapp.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by T on 2017/7/11.
 */

public class WoDeFragment extends Fragment implements View.OnClickListener {
    private static final int RESULT_LOCAL_IMAGE = 0;
    private static final int RESULT_CAMERA_IMAGE = 1;
    private  static final String TAG="WoDeFragment";
    private String mCurrentPhotoPath;
    private ImageView imgsz ,imgtx , saomas;
    private TextView tv;
    SharedPreferences sp = null;
    public  View view;
    String url = "http://172.16.8.253:8080/file/";
    String url2 ;
    private LinearLayout linearLayout,dengrulinarLaout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null){
                    view = inflater.inflate(R.layout.wode_layout,container, false);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        //
        imgsz  =view.findViewById(R.id.shezhi);
        imgtx = view.findViewById(R.id.touxiang);
//        VolleyImageUtils.loadImage(UserContents.imageUrl, imgtx);
        imgtx.setOnClickListener(this);
        imgsz.setOnClickListener(this);
        tv = view.findViewById(R.id.tv1);
        view.findViewById(R.id.wode_btn_dianjidengru).setOnClickListener(this);
        linearLayout = view.findViewById(R.id.lv_123);
        dengrulinarLaout = view.findViewById(R.id.lv_321);
        saomas = view.findViewById(R.id.saoma);

        try {
            sp = this.getActivity().getSharedPreferences("sp_demo", QQdengru.MODE_PRIVATE);
            String name =sp.getString("name",null);
            boolean b= sp.getBoolean("boolean",false);
            String urltouxiang  = sp.getString("url",null);
            if (b==true){
                linearLayout.setVisibility(linearLayout.VISIBLE);
                dengrulinarLaout.setVisibility(dengrulinarLaout.GONE);
                VolleyImageUtils.loadImage(urltouxiang,imgtx);
            }else if (b==false){
                linearLayout.setVisibility(linearLayout.GONE);
                dengrulinarLaout.setVisibility(dengrulinarLaout.VISIBLE);
            }
//            VolleyUtil.getInstance().wode(name, new WodeCallBack() {
//                @Override
//                public void success(String json) {
//                    JSONObject jsonObject = JSON.parseObject(json);
//
//                    String name=  jsonObject.get("name").toString();
//                    tv.setText(name);
//                    url2 = jsonObject.getString("avator").toString();
////                    VolleyImageUtils.loadImage(url+url2, imgtx);
//                    VolleyImageUtils.loadImage("http://q.qlogo.cn/qqapp/100424468/DCCC78DD980CF083525BBF8373BAF538/100",imgtx);
//                    Log.i(TAG,"...............json:"+jsonObject+"..........22:"+url2);
//                }
//
//                @Override
//                public void errr(String error) {
//                    Toast.makeText(WoDeFragment.this.getActivity(),"..."+error,Toast.LENGTH_SHORT).show();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shezhi:

                Intent intent = new Intent(WoDeFragment.this.getActivity(),SheZhi.class);
                startActivity(intent);
                break;

            case R.id.touxiang:
                showWindow();
                break;

            case R.id.wode_btn_dianjidengru:
                Intent intent1 = new Intent(WoDeFragment.this.getActivity(),QQdengru.class);
                startActivity(intent1);
                break;
        }
    }

    private void showWindow() {
        View popView =View.inflate(this.getActivity(),R.layout.window_layout,null);
        Button fromCameraBtn =popView.findViewById(R.id.window_paizhao);
        Button fromLocalBtn = popView.findViewById(R.id.window_xiangce);
        Button cancelBtn =popView.findViewById(R.id.window_quxiao);

        int width =getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*2/5;

        final PopupWindow popupWindow = new PopupWindow(popView,width,height);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);//点击外部pop消失

        WindowManager.LayoutParams lp =getActivity().getWindow().getAttributes();
        lp.alpha=0.5f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);

        fromCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeCamera(RESULT_CAMERA_IMAGE);
                popupWindow.dismiss();
            }
        });
        fromLocalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOCAL_IMAGE);
                popupWindow.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }
    private void takeCamera(int resultCameraImage) {
            //获得环境的外部存储状态，等于环境.媒体安装
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File phpotoFile = null;
                        //创建文件图像方法
            phpotoFile = createImageFile();
            //打开媒体商店.动作捕捉图像（相机） 意图
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //得到可见提示方法
            Uri uri = getUserVisibleHint(this.getActivity(),phpotoFile);
            intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE,getUserVisibleHint(this.getActivity(),phpotoFile));
            //开始活动的结果
            startActivityForResult(intent,resultCameraImage);
        }else{
            Toast.makeText(getActivity(),"....",Toast.LENGTH_SHORT).show();
        }
    }

    private Uri getUserVisibleHint(Context  context, File phpotoFile) {
        if(context == null || phpotoFile==null){
            //如果没有得到参数则抛出空指针异常
            throw new NullPointerException();
        }
        Uri uri;
        //sdk版本判断
        if(Build.VERSION.SDK_INT>=24){
                //文件提供者.活动文件的uri   上下文. 让应用申请背景               权限提供者                          文件
            uri = FileProvider.getUriForFile(context.getApplicationContext(),"main.dlxy.com.mylvyouapp.myProvider",phpotoFile);

        }else {
            uri = Uri.fromFile(phpotoFile);
        }

        return uri;
    }

    private File createImageFile() {
        //                文件提供者  . 获得外部存储公共目录               （目录）
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = null;

        try {
            //          创建临时文件
            image = File.createTempFile(
                    generateFileName(),//生成的文件名
                    ".jpg",             //生产的文件后缀名
                    storageDir         //存储目录
            );
        }

    catch (Exception e) {
            e.printStackTrace();
        }
        mCurrentPhotoPath = image.getAbsolutePath();
        return  image;
    }
    private String generateFileName() {
        //获得时间
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((ViewGroup)view.getParent()).removeView(view);
    }

    //回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG,"....onActivityResult" + "...requestCode:" + requestCode + "...resultCode:" + resultCode + "...data:" + data);

        if(resultCode == RESULT_OK){
            if(requestCode == RESULT_LOCAL_IMAGE && data!=null){
                Log.i(TAG,"....onActivityResult" + "...requestCode:" + requestCode + "...resultCode:" + resultCode + "...data:" + data);
                Uri selectedImage =data.getData();
                String[] filePathColumn ={MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn,
                        null,null,null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                final String picturePath =cursor.getString(columnIndex);

                Map<String ,String> map = new HashMap<String ,String>();
                map.put("name","tzy");
                ImageUpdateUtil.getInstance().uploadFile(picturePath, "tzy", UserContents.imageGetUrl, map);
//                try {
//                    sp = this.getActivity().getSharedPreferences("sp_demo", DengRu.MODE_PRIVATE);
//                    String name =sp.getString("name",null);

                    VolleyUtil.getInstance().wode("tzy", new WodeCallBack() {
                        @Override
                        public void success(String json) {
                            JSONObject jsonObject = JSON.parseObject(json);

                           String url3 = jsonObject.getString("avator").toString();
                            Log.i(TAG,"...............json:"+jsonObject+".......333:"+url3);
                            VolleyImageUtils.loadImage(url+url3, imgtx);
                        }

                        @Override
                        public void errr(String error) {
                            Toast.makeText(WoDeFragment.this.getActivity(),"..."+error,Toast.LENGTH_SHORT).show();
                        }
                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }



            }else if (requestCode == RESULT_CAMERA_IMAGE ){
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "tzy");

                ImageUpdateUtil.getInstance().uploadFile(mCurrentPhotoPath, "tzy", UserContents.imageGetUrl, map);
            }


        }


    }

}
