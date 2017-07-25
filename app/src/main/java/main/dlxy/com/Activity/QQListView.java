package main.dlxy.com.Activity;

import android.content.Context;
import android.widget.ListView;

/**
 * Created by T on 2017/7/24.
 */

public class QQListView extends ListView {

    private DelButtonClickListener mListener;

    public QQListView(Context context) {
        super(context);
    }
    public void setDelButtonClickListener(DelButtonClickListener listener){
        mListener = listener;
    }interface DelButtonClickListener
    {
        public void clickHappend(int position);
    }
}
