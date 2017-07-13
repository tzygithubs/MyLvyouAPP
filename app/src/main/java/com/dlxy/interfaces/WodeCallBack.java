package com.dlxy.interfaces;

import com.dlxy.domain.Customer;

import java.util.List;

/**
 * Created by T on 2017/7/13.
 */

public interface WodeCallBack {
    void success(List<Customer> json);
    void errr(String error);
}
