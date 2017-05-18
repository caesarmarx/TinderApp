package jxgi.com.util;

import android.content.Context;
import android.content.SharedPreferences;

import jxgi.com.consts.CommonConsts;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "TenderWatchPreference";
    private static final String USER_TYPE = "usertype";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean saveUserType(int type) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_TYPE, type);
        editor.apply();
        return true;
    }

    public int getUserMessageUpdateTime() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(USER_TYPE, CommonConsts.USER_TYPE_CLIENT);
    }
}