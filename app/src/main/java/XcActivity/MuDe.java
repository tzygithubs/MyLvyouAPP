package XcActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dlxy.MyAdapter.XingChengAdapter1;

import main.dlxy.com.mylvyouapp.R;

/**
 * Created by T on 2017/7/14.
 */

public class MuDe extends Activity {
    private ListView listView;
    private String name[] = {"01  序 | 童年幻想"};
    private String[] name1 = {"阿萨德撒多撒多，阿萨德撒多撒多我去，大沙发沙发沙发看见丘尔金前卫科技区我开阿萨德撒多撒多撒多撒大所多撒多了加强网络科技拉是的撒旦弃我去破日期我，去偶尔去哦IE健康垃圾袋",};
    private String[] name2 ={"”敖德萨多撒大所多“"};
    private int img[] = {R.mipmap.ic_launcher};
    private XingChengAdapter1 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mude_layout);
        listView = findViewById(R.id.lv_mu_layout);
        adapter = new XingChengAdapter1(name,name1,name2,img,this);
        listView.setAdapter(adapter);
    }
}