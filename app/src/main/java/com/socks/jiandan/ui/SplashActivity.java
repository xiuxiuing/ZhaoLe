package com.socks.jiandan.ui;

import com.socks.jiandan.R;
import com.socks.jiandan.base.BaseActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by wang on 17/4/18.
 */

public class SplashActivity extends BaseActivity {
    @InjectView(R.id.rl_splash)
    RelativeLayout splashLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);

    }

    @Override
    protected void initData() {
        setupSplashAd();
    }

    /**
     * 设置开屏广告
     */
    private void setupSplashAd() {

    }
}
