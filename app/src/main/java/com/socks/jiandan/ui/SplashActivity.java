package com.socks.jiandan.ui;

import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.socks.jiandan.R;
import com.socks.jiandan.base.BaseActivity;
import com.socks.jiandan.base.ConstantString;
import com.socks.jiandan.utils.logger.Logger;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by wang on 17/4/18.
 */

public class SplashActivity extends BaseActivity implements SplashADListener {
    @InjectView(R.id.rl_splash)
    RelativeLayout splashLayout;

    private SplashAD splashAD;
    public boolean canJump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (canJump) {
            next();
        }
        canJump = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
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
        splashAD = new SplashAD(this, splashLayout, ConstantString.GDT_APPID, ConstantString.GDT_SPLASHPOSID, this, 3000);
    }

    @Override
    public void onADDismissed() {
        next();
    }

    @Override
    public void onNoAD(int i) {
        Logger.d("code:" + i);
        this.startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    public void onADPresent() {

    }

    @Override
    public void onADClicked() {

    }

    @Override
    public void onADTick(long l) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void next() {
        if (canJump) {
            this.startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } else {
            canJump = true;
        }
    }
}
