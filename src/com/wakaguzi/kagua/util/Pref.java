package com.wakaguzi.kagua.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
	
	private static SharedPreferences settings;

    private static SharedPreferences.Editor editor;
    
    public static int selectedtheme = -1;
    
    public static final String PREFS_NAME = "Kagua";
    
    
    
    
    public static void loadSettings(Context context) {
        final SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        selectedtheme =settings.getInt("AutoLogin", selectedtheme);
        
    }
    public static void saveSettings(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        editor.putInt("SelectedTheme", selectedtheme); 
        editor.commit();
    }

}
