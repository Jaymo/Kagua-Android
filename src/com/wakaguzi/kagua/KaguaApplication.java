
package com.wakaguzi.kagua;

import com.WazaBe.HoloEverywhere.Settings;
import com.WazaBe.HoloEverywhere.ThemeManager;
import com.WazaBe.HoloEverywhere.app.Application;
import com.wakaguzi.kagua.data.KaguaDB;


public class KaguaApplication extends Application {
	 public static KaguaDB mDb;
	 
	public KaguaApplication() {
		Settings.setUseThemeManager(true);
		ThemeManager.THEME_DEFAULT |= ThemeManager.FULLSCREEN;
	}
	
	 @Override
	   public void onCreate() {
	       super.onCreate();

	       mDb = new KaguaDB(this);
	       mDb.open();
	   }
		@Override
	   public void onTerminate() {
	       mDb.close();
	      super.onTerminate();
	   }

}
