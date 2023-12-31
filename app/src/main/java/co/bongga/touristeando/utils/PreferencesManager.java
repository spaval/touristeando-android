package co.bongga.touristeando.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import co.bongga.touristeando.models.Coordinate;
import co.bongga.touristeando.models.User;

/**
 * Created by bongga on 12/13/16.
 * Base on Lincoln on 05/05/16.
 */

public class PreferencesManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "touristeando-welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";
    private static final String CURRENT_LOCATION = "currentLocation";
    private static final String DEFAULT_DISTANCE = "defaultDistance";
    private static final String HELP_MESSAGE = "helpMessage";
    private static final String LOGGED_USER = "userData";

    private Gson gson;

    public PreferencesManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        gson = new Gson();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setCurrentLocation(Coordinate location){
        editor.putString(CURRENT_LOCATION, gson.toJson(location));
        editor.commit();
    }

    public Coordinate getCurrentLocation(){
        String location = pref.getString(CURRENT_LOCATION, null);
        return gson.fromJson(location, Coordinate.class);
    }

    public void setDefaultDistance(int distance) {
        editor.putInt(DEFAULT_DISTANCE, distance);
        editor.commit();
    }

    public int getDistance() {
        return pref.getInt(DEFAULT_DISTANCE, 0);
    }

    public void setHelpMessage(boolean shown){
        editor.putBoolean(HELP_MESSAGE, shown);
        editor.commit();
    }

    public boolean isShownHelpMessage(){
        return pref.getBoolean(HELP_MESSAGE, true);
    }

    public void setLoggedUser(User user){
        editor.putString(LOGGED_USER, gson.toJson(user));
        editor.commit();
    }

    public User getLoggedUser(){
        String user = pref.getString(LOGGED_USER, null);
        return gson.fromJson(user, User.class);
    }
}
