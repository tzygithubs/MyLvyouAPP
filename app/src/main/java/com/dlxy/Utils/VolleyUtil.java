package com.dlxy.Utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dlxy.contents.UserContents;
import com.dlxy.domain.Customer;
import com.dlxy.interfaces.LoginCallBack;
import com.dlxy.interfaces.MyCallBack;
import com.dlxy.interfaces.RegistCallBack;
import com.dlxy.myApplication.MyApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by T on 2017/7/12.
 */

public class VolleyUtil {
    private static final String TAG = "VolleyUtil";
    private static final String VOLLEY_TAG = "VolleyTag";
    private static VolleyUtil instance;

    public static VolleyUtil getInstance() {
        if(instance == null) {
            instance = new VolleyUtil();
        }
        return instance;
    }


    public void getVolleyDataGet(String url , final MyCallBack callBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Customer> customer = JSON.parseArray(response ,Customer.class);
                callBack.success(customer);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,".....error"+error);
            }
        });

        stringRequest.setTag(VOLLEY_TAG);
        MyApplication.getRequestQueue().add(stringRequest);
    }
    public void login(final String name , final String password, final LoginCallBack loginCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UserContents.loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"...response:" + response);
                if (response.equals(UserContents.ok)){
                        loginCallBack.success(UserContents.ok);
                }else {
                    loginCallBack.success(UserContents.error);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginCallBack.success(UserContents.error);
            }
        }){
            protected Map<String ,String> getParams(){
                Map<String, String> map = new HashMap<>();
                map.put("name",name);
                map.put("password", password);
                return map;
            }
        };
            stringRequest.setTag(VOLLEY_TAG);
            MyApplication.getRequestQueue().add(stringRequest);
    }

    public  void regist(final String name , final String password , final String age, final String gender, final RegistCallBack raCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UserContents.registUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                raCallBack.registSuccess(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                raCallBack.registError(error.toString());
            }
        }){
            protected Map<String , String> getParams(){
                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("password", password);
                map.put("age", age);
                map.put("gender", gender);
                return map;
            }
        };
    }
}
