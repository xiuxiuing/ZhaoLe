package com.socks.jiandan.ui;

import com.socks.jiandan.R;
import com.socks.jiandan.base.BaseActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

import net.youmi.android.AdManager;
import net.youmi.android.normal.common.ErrorCode;
import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

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
        AdManager.getInstance(getApplicationContext()).init("290de6ec4fe8f2aa", "1c4305da4432ab04", true);

        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 开屏展示界面的 onDestroy() 回调方法中调用
        SpotManager.getInstance(mContext).onDestroy();
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
        // 对开屏进行设置
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        // // 设置是否展示失败自动跳转，默认自动跳转
        // splashViewSettings.setAutoJumpToTargetWhenShowFailed(false);
        // 设置跳转的窗口类
        splashViewSettings.setTargetClass(MainActivity.class);
        // 设置开屏的容器
        splashViewSettings.setSplashViewContainer(splashLayout);

        // 展示开屏广告
        SpotManager.getInstance(mContext).showSplash(mContext, splashViewSettings, new SpotListener() {

            @Override
            public void onShowSuccess() {
                logInfo("开屏展示成功");
            }

            @Override
            public void onShowFailed(int errorCode) {
                logError("开屏展示失败");
                switch (errorCode) {
                    case ErrorCode.NON_NETWORK:
                        logError("网络异常");
                        break;
                    case ErrorCode.NON_AD:
                        logError("暂无开屏广告");
                        break;
                    case ErrorCode.RESOURCE_NOT_READY:
                        logError("开屏资源还没准备好");
                        break;
                    case ErrorCode.SHOW_INTERVAL_LIMITED:
                        logError("开屏展示间隔限制");
                        break;
                    case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
                        logError("开屏控件处在不可见状态");
                        break;
                    default:
                        logError("errorCode: %d", errorCode);
                        break;
                }
            }

            @Override
            public void onSpotClosed() {
                logDebug("开屏被关闭");
            }

            @Override
            public void onSpotClicked(boolean isWebPage) {
                logDebug("开屏被点击");
                logInfo("是否是网页广告？%s", isWebPage ? "是" : "不是");
            }
        });
    }
}
