package com.itcast.yb.packelves.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itcast.yb.packelves.BaseActivity;
import com.itcast.yb.packelves.R;
import com.itcast.yb.packelves.bean.GiftDetailsBean;
import com.itcast.yb.packelves.bean.GiftInfoBean;
import com.itcast.yb.packelves.utils.FastBlurUtil;
import com.itcast.yb.packelves.utils.HttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftDetailsActivity extends BaseActivity {
    GiftInfoBean.EntityInfo entityInfo;
    @BindView(R.id.tv_details_title) TextView tvDetailsTitle;//标题
    @BindView(R.id.iv_details_share) ImageView ivDetailsShare;//分享
    @BindView(R.id.tv_download_day) TextView tv_download_day;//礼包有效期
    @BindView(R.id.tv_download_gift_count) TextView tv_gift_num;//礼包个数
    @BindView(R.id.iv_logo) CircleImageView circleImageView;//圆形图片
    @BindView(R.id.iv_background_drable) ImageView iv_background;//背景虚化
    @BindView(R.id.tv_GiftNote_des) TextView tv_GiftNote_des;//礼包说明内容
    @BindView(R.id.tv_Exchange_Way_des) TextView tv_tv_Exchange_Way_des;//礼包兑换内容
    @BindView(R.id.ll_root) LinearLayout llRoot;
    @BindView(R.id.rl_root) RelativeLayout rlRoot;
    @BindView(R.id.btn_download) Button btnDownload;//立即领取
    @BindView(R.id.ib_gift_content) ImageButton ib_gift_content;//已经获取的礼包。
    private String mTitle;
    private int mAppid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        entityInfo= (GiftInfoBean.EntityInfo) getIntent().getSerializableExtra("details");
        //初始化基本信息
        initBasic();
        initData();

    }

    private void initBasic() {
        ButterKnife.bind(this);
        //离线应该加载的数据
        mTitle=entityInfo.gname+"-"+entityInfo.giftname;
        tv_download_day.setText("有效期:"+entityInfo.addtime);
        tv_gift_num.setText(entityInfo.number+"");
        mAppid=entityInfo.id;
        llRoot.setVisibility(View.INVISIBLE);
        ivDetailsShare.setVisibility(View.GONE);
        tvDetailsTitle.setText(mTitle);
        //模糊处理
        Resources res = getResources();
        Bitmap scaledBitmap = BitmapFactory.decodeResource(res, R.drawable.def_head);
        //scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
        Bitmap blurBitmap = FastBlurUtil.toBlur(scaledBitmap, 2);
        iv_background.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv_background.setImageBitmap(blurBitmap);

    }
//   http://www.1688wan.com/majax.action?method=getGiftInfo
    private void initData() {
        getDataForService(mAppid);
    }


    private void getDataForService(int mAppid) {
        HttpUtils.creat().querGiftDetails(mAppid).enqueue(new Callback<GiftDetailsBean>() {
            @Override
            public void onResponse(Call<GiftDetailsBean> call, Response<GiftDetailsBean> response) {
                rlRoot.setVisibility(View.GONE);
                llRoot.setVisibility(View.VISIBLE);
                //解析数据
                parase(response.body());
            }

            @Override
            public void onFailure(Call<GiftDetailsBean> call, Throwable t) {

            }
        });

    }


    private void parase(GiftDetailsBean body) {
        GiftDetailsBean.InfoEntity infoentity=body.getInfo();
        //联网数据请求
        Glide.with(this).load(HttpUtils.BASE_URL+infoentity.getIconurl())
                .into(circleImageView);
        tv_GiftNote_des.setText(infoentity.getExplains());
        tv_tv_Exchange_Way_des.setText(infoentity.getDescs());
    }

    //返回按钮
    @OnClick(R.id.iv_details_back)
    public void backPreActivity() {
        finish();
    }

}
