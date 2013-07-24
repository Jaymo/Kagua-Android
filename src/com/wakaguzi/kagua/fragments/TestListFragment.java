
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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.wakaguzi.kagua.adapters.GridAdapter;
import com.wakaguzi.kagua.parsers.JSONfunctions;

public class TestListFragment  extends ListFragment {
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
	public static String vid ;
	public static String c_eid ;
	
	HttpPost httpPost;JSONObject json;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	InputStream inputstream;
	SharedPreferences app_preferences;
	ArrayList<NameValuePair> namevaluepairs;
	StringBuilder urlBuilder;
	String mNAME,res="",mRes="",mDID,mBid,mContact,mCid;
	ArrayList<HashMap<String, String>> displaylist;
	ProgressDialog progressDialog;

	
	ListView list;
	Context appContext;
	String[] values;
	GridAdapter adaptr;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	 }
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
	  super.onActivityCreated(savedInstanceState);
	  
	  new loadListView().execute();
      Log.v("Reached Here","BS");
	}
	
	public class loadListView extends AsyncTask<Integer, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... args) {  	
        	
              displaylist = new ArrayList<HashMap<String, String>>();
			  JSONObject json = JSONfunctions.getJSONfromURL("http://197.254.42.106:85/crown/ws/bootstrap.php?t=data&uid=62");
			  
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
                return null;
            } 
        


        @Override
        protected void onPostExecute(String args) {

        		  list = getListView();
        		  adaptr=new GridAdapter (getActivity(), displaylist);
			      list.setAdapter(adaptr);
			      setListShown(true);
			      
			      list.setOnItemClickListener(new OnItemClickListener() {
			        	public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
			        		
			    	  } 
			      });
	        	
			        	
        }	  
	}  	  

}
