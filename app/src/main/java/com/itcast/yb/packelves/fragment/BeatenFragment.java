package com.itcast.yb.packelves.fragment;

import android.view.View;

import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.utils.UIUtils;

/**
 * Created by yb on 2017/10/30.
 */

public class BeatenFragment extends BaseFragment{


    @Override
    public void initData() {

    }

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.fragment_beaten);
        return view;
    }
}
