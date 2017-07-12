package com.dlxy.interfaces;

import com.android.volley.VolleyError;
import com.dlxy.domain.Customer;

import java.util.List;

/**
 * Created by T on 2017/7/12.
 */

public interface MyCallBack {
    void success(List<Customer> customers);
    void error(VolleyError error);
}
