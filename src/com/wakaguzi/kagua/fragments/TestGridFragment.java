package com.wakaguzi.kagua.fragments;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.WazaBe.HoloEverywhere.app.ProgressDialog;
import com.wakaguzi.kagua.KaguaApplication;
import com.wakaguzi.kagua.R;
import com.wakaguzi.kagua.adapters.GridAdapter;
import com.wakaguzi.kagua.data.KaguaDB;
import com.wakaguzi.kagua.parsers.JSONfunctions;



public class TestGridFragment  extends Fragment {
	    private GridView    mGridView;
	    public static final String KEY_NAME = "name"; 
		public static final String KEY_NAME_ID = "did"; 
		public static final String KEY_PHONE = "tel"; 
		public static final String KEY_LAST_VISIT = "lastvisit";
		public static final String KEY_LAT = "lat";
		public static final String KEY_LON = "long"; 
		public static final String KEY_CONTACT_PERSON = "cname";
		public static final String KEY_CONTACT_ID = "cid";
		public static final String KEY_BLOCK_ID = "bid";
		public static final String KEY_BLOCK_NAME = "bname"; 
		public static final String KEY_IMAGE_URL = "image_url";
		public static final String KEY_ACCOUNT_STATUS = "active"; 
		GridAdapter adaptr;
	 
		ProgressDialog dialog;
		HttpPost httpPost;JSONObject json;
		StringBuffer buffer;
		HttpResponse response;
		HttpClient httpclient;
		InputStream inputstream;
		SharedPreferences app_preferences;
		ArrayList<NameValuePair> namevaluepairs;
		StringBuilder urlBuilder;
		String mNAME,res="",mRes="",mDID,mBid,mContact,mCid,mPHONE,mVisit,mURL;
		ArrayList<HashMap<String, String>> displaylist;
		ProgressDialog progressDialog;
		
	public TestGridFragment() {
		// Empty constructor required for fragment subclasses
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			
		View view = inflater.inflate(R.layout.layout_grid_view,container, false);
				
		mGridView = (GridView) view.findViewById(R.id.grid_view);

		return view;
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        
        new loadListView().execute();
        Log.v("Reached Here","BS");
    }
	 public class loadListView extends AsyncTask<Integer, String, String> {

	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            dialog = new ProgressDialog(getActivity()); 
				dialog.setIndeterminate(true);
				dialog.setMessage("Contacting a Ninja....");
				dialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_dialog_icon_drawable_animation));
				dialog.show();
	        }

	        @Override
	        protected String doInBackground(Integer... args) {
	        	urlBuilder = new StringBuilder("http://197.254.42.106:85/crown/ws/bootstrap.php");
	        	urlBuilder.append("?t=data");
	        	urlBuilder.append("&uid=62");
	        	
	              displaylist = new ArrayList<HashMap<String, String>>();
	              JSONObject json = JSONfunctions.getJSONfromURL("http://197.254.42.106:85/crown/ws/bootstrap.php?t=data&uid=62");
				  Log.i("SENDING url", urlBuilder.toString());
				  try{
			      	
			      	JSONArray  mot = json.getJSONArray("PAYLOAD");
				        for(int i=0;i<mot.length();i++){						
				        	HashMap<String, String> map = new HashMap<String, String>();		
							JSONObject e = mot.getJSONObject(i);
							map.put(KEY_NAME_ID, e.getString(KEY_NAME_ID));
							map.put(KEY_NAME, e.getString(KEY_NAME));
							map.put(KEY_PHONE, e.getString(KEY_PHONE));
							map.put(KEY_LAST_VISIT, e.getString(KEY_LAST_VISIT));
							map.put(KEY_LAT, e.getString(KEY_LAT));
							map.put(KEY_LON, e.getString(KEY_LON));
							map.put(KEY_CONTACT_PERSON, e.getString(KEY_CONTACT_PERSON));
							map.put(KEY_CONTACT_ID , e.getString(KEY_CONTACT_ID));
							map.put(KEY_BLOCK_ID , e.getString(KEY_BLOCK_ID));
							map.put(KEY_BLOCK_NAME , e.getString(KEY_BLOCK_NAME));
						    map.put(KEY_IMAGE_URL, e.getString(KEY_IMAGE_URL));
						    map.put(KEY_ACCOUNT_STATUS, e.getString(KEY_ACCOUNT_STATUS));
						    displaylist.add(map);
						}		
			      }catch(JSONException e)      {

			      }
				  try{ 
						Thread.sleep(1000); 
						}
					catch(InterruptedException e)
					{ 
						
					}
	                return null;
	            } 
	        


	        @Override
	        protected void onPostExecute(String args) {
	        	      dialog.dismiss();
	        	      adaptr=new GridAdapter (getActivity(), displaylist);
				      mGridView.setAdapter(adaptr);
				      
				        mGridView.setOnItemClickListener(new OnItemClickListener() {
				        	public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				        		@SuppressWarnings("unchecked")
								HashMap<String, String> o = (HashMap<String, String>) mGridView.getAdapter().getItem(position);
				        		 
				        		
				        		
				        		 mNAME =o.get(KEY_NAME);
								 mPHONE =o.get(KEY_PHONE);
								 mVisit=o.get(KEY_LAST_VISIT);
								 mURL=o.get(KEY_IMAGE_URL);
								 
								 //Log.v("mName", mNAME);
								 
								//Insert Logic
				        		ContentValues values = new ContentValues();
			                	values.put(KaguaDB.KEY_NAME, mNAME);Log.v("KEY_NAME", mNAME);
			                	values.put(KaguaDB.KEY_PHONE, mPHONE+"10001");Log.v("KEY_PHONE", mPHONE+"10001");
			                	values.put(KaguaDB.KEY_LAST_VISIT, mVisit);Log.v("KEY_LAST_VISIT", mVisit);
			                	values.put(KaguaDB.KEY_IMAGE_URL, mURL);Log.v("KEY_IMAGE_URL", mURL);
			                	KaguaApplication.mDb.populate_home_table(values);
			                	
			                	
			                	
				    	  } 
				      });
		        	
				        	
	        }	  
	      	  
	              
	    }
	       

}
