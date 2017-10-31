package com.itcast.yb.packelves.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.adapter.FeatureQuickAdapter;
import com.itcast.yb.packelves.bean.FeatureInfoBean;
import com.itcast.yb.packelves.network.RequestNetwork;
import com.itcast.yb.packelves.utils.UIUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yb on 2017/10/30.
 */

public class KaiCeFragment extends BaseFragment {

    @Override
    public View initView() {

        return null;
    }

    @Override
    public void initData() {

    }
}
