package com.itcast.yb.packelves.fragment;

import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itcast.yb.packelves.MainActivity;
import com.itcast.yb.packelves.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yb on 2017/10/29.
 */

public class HotFragment extends BaseFragment{

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_search) TextView tvSearch;

    @Override
    public void initData() {

    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_hot, null);
        ButterKnife.bind(this,view);

        tvTitle.setText("热门游戏");
        tvSearch.setVisibility(View.GONE);

        return view;
    }

    @OnClick(R.id.iv_menu)
    public void opneMenu() {
        MainActivity mainActivity = (MainActivity) this.mActivity;
        mainActivity.getDrawerLayout().openDrawer(GravityCompat.START);
    }
}
