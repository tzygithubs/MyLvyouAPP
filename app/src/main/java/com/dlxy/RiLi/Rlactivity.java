package com.dlxy.RiLi;

/**
 * Created by T on 2017/7/20.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class Rlactivity extends Activity implements NewCalendar.NewCalendarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rili);

        NewCalendar calendar = findViewById(R.id.newCalendar);
        calendar.listener =this;
    }

    @Override
    public void onItemLongPress(Date day) {
        DateFormat df = SimpleDateFormat.getDateInstance();
        Toast.makeText(this,df.format(day),Toast.LENGTH_LONG).show();
    }
}
