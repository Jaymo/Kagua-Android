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

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.wakaguzi.kagua.parsers.JsonParser;



public class TestGridFragment  extends Fragment {
	    
	    public static final String KEY_NAME = "name"; 
		public static final String KEY_PHONE = "tel"; 
		public static final String KEY_LAST_VISIT = "lastvisit"; 
		public static final String KEY_IMAGE_URL = "image_url";
		
	 
		JSONObject json;
		HttpPost httpPost;
		StringBuffer buffer;
		HttpResponse response;
		HttpClient httpclient;
		GridAdapter adaptr;
		InputStream inputstream;
		ProgressDialog dialog;
		private GridView mGridView;
		ArrayList<NameValuePair> namevaluepairs;
		StringBuilder urlBuilder;
		String mNAME,res="",mRes="",mPHONE,mVisit,mURL;
		
		ArrayList<HashMap<String, String>> displaylist;
		ProgressDialog progressDialog;
		
	public TestGridFragment() {
		
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
        
        new loadListView().execute();
 
    }
	 public class loadListView extends AsyncTask<Integer, String, String> {

	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            dialog = new ProgressDialog(getActivity()); 
				dialog.setIndeterminate(true);
				dialog.setMessage(getResources().getString(R.string.progress_dialog));
				dialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_dialog_icon_drawable_animation));
				dialog.show();
	        }

	        @Override
	        protected String doInBackground(Integer... args) {
	        	urlBuilder = new StringBuilder(getResources().getString(R.string.URL));
	        	urlBuilder.append("?t=data");
	        	urlBuilder.append("&uid=62");
	        	
	            displaylist = new ArrayList<HashMap<String, String>>();
	            JSONObject json = JsonParser.getJSONfromURL(urlBuilder.toString());
	            
				  try{
			      	
			      	JSONArray  mot = json.getJSONArray("PAYLOAD");
				        for(int i=0;i<mot.length();i++){						
				        	HashMap<String, String> map = new HashMap<String, String>();		
							JSONObject e = mot.getJSONObject(i);
							map.put(KEY_NAME, e.getString(KEY_NAME));
							map.put(KEY_PHONE, e.getString(KEY_PHONE));
							map.put(KEY_LAST_VISIT, e.getString(KEY_LAST_VISIT));
						    map.put(KEY_IMAGE_URL, e.getString(KEY_IMAGE_URL));
						    displaylist.add(map);
						}		
			      }
				  catch(JSONException e) 
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
				        		 mNAME =o.get(KEY_NAME);mPHONE =o.get(KEY_PHONE);
								 mVisit=o.get(KEY_LAST_VISIT);mURL=o.get(KEY_IMAGE_URL);
								 
								ContentValues values = new ContentValues();
			                	values.put(KaguaDB.KEY_NAME, mNAME);
			                	values.put(KaguaDB.KEY_PHONE, mPHONE+"10001");
			                	values.put(KaguaDB.KEY_LAST_VISIT, mVisit);
			                	values.put(KaguaDB.KEY_IMAGE_URL, mURL);
			                	KaguaApplication.mDb.populate_home_table(values);
			                	
			                	
			                	
				    	  } 
				      });
		        	
				        	
	        }	  
	      	  
	              
	    }
	       

}
