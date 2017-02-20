package com.socks.jiandan.ui.fragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.socks.jiandan.R;
import com.socks.jiandan.utils.AppInfoUtil;
import com.socks.jiandan.utils.FileUtil;
import com.socks.jiandan.utils.ShowToast;

import java.io.File;
import java.text.DecimalFormat;

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    public static final String CLEAR_CACHE = "clear_cache";
    public static final String APP_VERSION = "app_version";
    public static final String ENABLE_SISTER = "enable_sister";
    public static final String ENABLE_FRESH_BIG = "enable_fresh_big";

    private Preference clearCache;
    private Preference appVersion;
    private CheckBoxPreference enableSister;
    private CheckBoxPreference enableBig;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        getActivity().setTitle("设置");
        clearCache = findPreference(CLEAR_CACHE);
        appVersion = findPreference(APP_VERSION);
        enableSister = (CheckBoxPreference) findPreference(ENABLE_SISTER);
        enableBig = (CheckBoxPreference) findPreference(ENABLE_FRESH_BIG);

        appVersion.setTitle(AppInfoUtil.getVersionName(getActivity()));
        File cacheFile = ImageLoader.getInstance().getDiskCache().getDirectory();
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        clearCache.setSummary("缓存大小：" + decimalFormat.format(FileUtil.getDirSize(cacheFile)) + "M");

        enableSister.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                ShowToast.Short(((Boolean) newValue) ? "已解锁隐藏属性->妹子图" : "已关闭隐藏属性->妹子图");
                return true;
            }
        });

        enableBig.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                ShowToast.Short(((Boolean) newValue) ? "已开启大图模式" : "已关闭大图模式");
                return true;
            }
        });

        clearCache.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        String key = preference.getKey();

        if (key.equals(CLEAR_CACHE)) {
            ImageLoader.getInstance().clearDiskCache();
            ShowToast.Short("清除缓存成功");
            clearCache.setSummary("缓存大小：0.00M");
        }
        return true;
    }


}
