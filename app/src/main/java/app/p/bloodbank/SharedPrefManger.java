package app.p.bloodbank;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import app.p.bloodbank.data.model.User.Data;

public class SharedPrefManger {

    final static String SHARED_PREF_NAME = "shared_pref";
    final static String USER_DATA = "shared_pref";
    final static String LOGIN_STATUS = "login_status";

    Context mContext;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    public SharedPrefManger(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public Boolean getLoginStatus() {
        Boolean value = mSharedPreferences.getBoolean(LOGIN_STATUS, false);
        return value;
    }

    public void setLoginStatus(Boolean status) {
        mEditor.putBoolean(LOGIN_STATUS, status);
        mEditor.commit();
    }

    public Data getUserData() {
        String user = mSharedPreferences.getString(USER_DATA, null);
        return new Gson().fromJson(user, Data.class);
    }

    public void setUserData(Data userData) {
        String user = new Gson().toJson(userData);
        mEditor.putString(USER_DATA, user);
        mEditor.commit();
    }




}
