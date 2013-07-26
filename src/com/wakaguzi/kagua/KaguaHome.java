package com.wakaguzi.kagua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.WazaBe.HoloEverywhere.ThemeManager;
import com.WazaBe.HoloEverywhere.app.ProgressDialog;
import com.WazaBe.HoloEverywhere.sherlock.SActivity;
import com.WazaBe.HoloEverywhere.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.wakaguzi.kagua.adapters.ListViewAdapter;
import com.wakaguzi.kagua.fragments.TestGridFragment;
import com.wakaguzi.kagua.fragments.TestListFavFragmant;
import com.wakaguzi.kagua.fragments.TestListFragment;
import com.wakaguzi.kagua.models.ListViewItemModel;
import com.wakaguzi.kagua.util.Pref;

public class KaguaHome extends SActivity {
	public static  String SENDER_ID = "";
	private DrawerLayout drawerLayout = null;
	private ListView navList = null;
	private ActionBarDrawerToggle drawerToggle = null;
	ProgressDialog dialog;
	private String[] menuItemsCategories;
	private String[] menuItemsApplication;
	FragmentManager AfragmentManager,BfragmentManager,CfragmentManager;
	FragmentTransaction AfragmentTransaction,BfragmentTransaction,CfragmentTransaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setForceThemeApply(true);
		super.onCreate(savedInstanceState);
		final ActionBar actionbar = getSupportActionBar();
		setContentView(R.layout.layout_kagua_home);
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setHomeButtonEnabled(true);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		navList = (ListView) findViewById(R.id.drawer);
		
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
		
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open,R.string.drawer_closed )
           {
            public void onDrawerClosed(View view) {
            	actionbar.setTitle(R.string.drawer_closed);
                ActivityCompat.invalidateOptionsMenu(KaguaHome.this);
               
            }

            public void onDrawerOpened(View drawerView) {
                actionbar.setTitle(R.string.drawer_open);
                ActivityCompat.invalidateOptionsMenu(KaguaHome.this); 
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        
        
		
		_initListMenu();
		navList.setOnItemClickListener(new DrawerItemClickListener());
		if (savedInstanceState == null) {
			navList.setItemChecked(1, true);
        }
		
		
		if (savedInstanceState == null) {
            selectItem(1);
        }
		
		Pref.loadSettings(KaguaHome.this);
		
		
	}
	
	public void setDarkTheme(View v) {
		ThemeManager.restartWithTheme(this, ThemeManager.DARK
				| ThemeManager.FULLSCREEN);
	}

	public void setLightTheme(View v) {
		ThemeManager.restartWithTheme(this, ThemeManager.LIGHT
				| ThemeManager.FULLSCREEN);
	}
	public void setLightDarkActionBarTheme(View v) {
		ThemeManager.restartWithTheme(this, ThemeManager.LIGHT_WITH_DARK_ACTION_BAR
				| ThemeManager.FULLSCREEN);
	}
	

	private void _initListMenu() {
		ListViewAdapter mAdapter = new ListViewAdapter(this);
		
		mAdapter.addHeader(R.string.categories_menu);
		menuItemsCategories = getResources().getStringArray(R.array.categories_menu_data); 
		String[] menuCategoriesIcons = getResources().getStringArray(R.array.categories_menu_icons);
		int CategoriesIcons = 0;
		for (String item : menuItemsCategories) {
			
			int id_category_title = getResources().getIdentifier(item, "string",this.getPackageName());
			
			int id_category_icon = getResources().getIdentifier(menuCategoriesIcons[CategoriesIcons], "drawable",this.getPackageName());
					
							

			ListViewItemModel mItem = new ListViewItemModel(id_category_title,id_category_icon);
					
			mAdapter.addItem(mItem);
			CategoriesIcons++;
		}
		
		mAdapter.addHeader(R.string.application_menu);
		menuItemsApplication = getResources().getStringArray(R.array.application_menu_data); 
		String[] menuApplicationIcons = getResources().getStringArray(R.array.application_menu_icons);
		int ApplicationIcons = 0;
		for (String item : menuItemsApplication) {
			
			int id_application_title = getResources().getIdentifier(item, "string",this.getPackageName());
			
			int id_application_icon = getResources().getIdentifier(menuApplicationIcons[ApplicationIcons], "drawable",this.getPackageName());
					
							

			ListViewItemModel mItem = new ListViewItemModel(id_application_title,id_application_icon);
					
			mAdapter.addItem(mItem);
			ApplicationIcons++;
		}
		
		if (navList != null)
			navList.setAdapter(mAdapter);
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

	
	
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == android.R.id.home) {
			if (drawerLayout.isDrawerOpen(navList)) {
				drawerLayout.closeDrawer(navList);
			} else {
				drawerLayout.openDrawer(navList);
			}
		}
		return super.onOptionsItemSelected(item);
	};
	
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	selectItem(position);
        	
        }
    }
    
    private void selectItem(int position) {
    	
    	switch (position){
    	case 1:
    		 AfragmentManager = getSupportFragmentManager();
    		 AfragmentTransaction = AfragmentManager.beginTransaction();
    		 TestGridFragment Afragment = new TestGridFragment();
    		 AfragmentTransaction.replace(R.id.main, Afragment);
    		 AfragmentTransaction.addToBackStack(null);
    		 AfragmentTransaction.commit();
             
    	break;
    	case 2:
    		 BfragmentManager = getSupportFragmentManager();
    		 BfragmentTransaction = BfragmentManager.beginTransaction();
    		 TestListFragment  Bfragment = new TestListFragment ();
    		 BfragmentTransaction.replace(R.id.main, Bfragment);
    		 BfragmentTransaction.addToBackStack(null);
    		 BfragmentTransaction.commit();
        	break;
    	case 3:
    		 CfragmentManager = getSupportFragmentManager();
    		 CfragmentTransaction = CfragmentManager.beginTransaction();
    		 TestListFavFragmant  Cfragment = new TestListFavFragmant ();
    		 CfragmentTransaction.replace(R.id.main, Cfragment);
    		 CfragmentTransaction.addToBackStack(null);
    		 CfragmentTransaction.commit();
        	break;
    	
    	case 5:
    		Toast.makeText(KaguaHome.this, "Display Settings Fragment"+position, Toast.LENGTH_LONG).show();
        	break;
    	case 6:
    		ThemeDialog();
        	break;
    	case 7:
    		Toast.makeText(KaguaHome.this, "Display About Fragment"+position, Toast.LENGTH_LONG).show();
        	break;
    	case 8:
    		Toast.makeText(KaguaHome.this, "Display FeedBack Fragment"+position, Toast.LENGTH_LONG).show();
        	break;
        	
    	
    	}
    	
    	navList.setItemChecked(position, true);
    	drawerLayout.closeDrawer(navList);
    }
    
    private void ThemeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(KaguaHome.this);
        builder.setTitle("Pick a Theme");
        builder.setSingleChoiceItems(R.array.themes, Pref.selectedtheme, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
            	Pref.loadSettings(KaguaHome.this);
            	Pref.selectedtheme=which;
            	Pref.saveSettings(KaguaHome.this);
            	
            	switch (which){
            	case 0:		
            		setDarkTheme(drawerLayout); 
            		dialog.cancel();
            		break;
            	case 1:
            		setLightTheme(drawerLayout);
            		dialog.cancel();
            		break;
            	case 2:
            		setLightDarkActionBarTheme(drawerLayout);  
            		dialog.cancel();
            		break;            	
            	
            	}
            	
            }
        });
        builder.create();
        builder.show();
    }

}
