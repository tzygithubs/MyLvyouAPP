package com.dlxy.RiLi;

/**
 * Created by T on 2017/7/20.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by Administrator on 2017/7/18.
 */

public class NewCalendar extends LinearLayout {

    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView textData;
    private GridView gridView;

    //系统控件查询
    private Calendar curData = Calendar.getInstance();
    private String displayFormat;

    public NewCalendarListener listener;

    public NewCalendar(Context context) {
        super(context);
    }

    public NewCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initContol(context,attrs);
    }

    public NewCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initContol(context,attrs);
    }

    private void initContol(Context context, AttributeSet attrs)
    {
        bindContol(context);
        bindContolEvent();

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.NewCalendar);
        try {
            //读取字段
            String format = ta.getString(R.styleable.NewCalendar_dateFormat);
            displayFormat = format;
            if (displayFormat == null)
            {
                displayFormat = "MMM yyyy";
            }
        }
        finally {
            ta.recycle();
        }
        renderCalender();
    }

    //xml文件绑定到类文件
    private void bindContol(Context context)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.claendr_view,this);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        textData = findViewById(R.id.textDate);
        gridView = findViewById(R.id.calendar_grid);
    }

    //绑定按钮点击事件
    private void bindContolEvent()
    {
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //向前翻一个月
                curData.add(Calendar.MONTH,1);
                renderCalender();
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //向后翻一个月
                curData.add(Calendar.MONTH,-1);
                renderCalender();
            }
        });
    }
    //渲染的部分
    private void renderCalender()
    {
        //格式化
        SimpleDateFormat sdf = new SimpleDateFormat(displayFormat);
        textData.setText(sdf.format(curData.getTime()));

        ArrayList<Date> cells = new ArrayList<>();
        //在类头部做一个克隆
        Calendar calendar = (Calendar) curData.clone();

        //月份至于当月第一天
        calendar.set(Calendar.DAY_OF_MONTH,1);
        //假设当月天数为一时get返回 1 ,1 - 1=0; 就是上个月没有剩下来的天数
        final int preyDays =calendar.get(Calendar.DAY_OF_WEEK)-1;
        //将当前的日期天数移到了我们汇至的第一天
        calendar.add(Calendar.DAY_OF_MONTH,-preyDays);

        int maxCellCount = 6*7;
        while (cells.size() < maxCellCount)
        {
            //填入一个对象
            cells.add(calendar.getTime());
            //每一次get的话calendar需要我们前进一天
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

        gridView.setAdapter(new CalendarAdapter(getContext(),cells));
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (listener == null)
                {
                    return false;
                }
                else
                {
                    listener.onItemLongPress((Date) adapterView.getItemAtPosition(position));
                    return true;
                }
            }
        });
    }

    private class  CalendarAdapter extends ArrayAdapter<Date>
    {
        LayoutInflater inflater;
        public CalendarAdapter(Context context, ArrayList<Date> days) {
            super(context, R.layout.calendar_text_day,days);
            inflater =LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Date data = getItem(position);

            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.calendar_text_day,parent,false);
            }
            //获取天数
            int day =data.getDate();
            ((TextView)convertView).setText(String.valueOf(day));

            //获取当前日期
            Date now = new Date();
            Boolean isTheSameMonth = false;
            if (data.getMonth() == now.getMonth())
            {
                isTheSameMonth =true;
            }

            if (isTheSameMonth)
            {
                ((TextView)convertView).setTextColor(Color.parseColor("#000000"));
            }
            else
            {
                ((TextView)convertView).setTextColor(Color.parseColor("#c0c0c0"));
            }
            //放到颜色后面不然有冲突
            if (now.getDate() == data.getDate() && now.getMonth() == now.getMonth() && now.getYear() == data.getYear())
            {
                ((TextView)convertView).setTextColor(Color.parseColor("#ff0000"));
                ((Calendar_day_textView)convertView).isToday=true;
            }
            return convertView;

        }
    }

    public interface NewCalendarListener
    {
        void onItemLongPress(Date day);
    }
}
