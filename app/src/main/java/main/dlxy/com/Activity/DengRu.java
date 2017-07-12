package main.dlxy.com.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/12.
 */

public class DengRu extends Activity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.dengru_layout);

    }
}
