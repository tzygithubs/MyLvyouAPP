package com.dlxy.RiLi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by T on 2017/7/20.
 */

public class Calendar_day_textView extends TextView {
    public boolean isToday = false;
    private Paint paint = new Paint();

    public Calendar_day_textView(Context context) {
        super(context);
    }

    public Calendar_day_textView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContol();
    }

    public Calendar_day_textView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContol();
    }

    private void initContol()
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#ff0000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isToday)
        {
            canvas.translate(getWidth()/2,getHeight()/2);
            canvas.drawCircle(0,0,getWidth()/2,paint);
        }
    }
}
