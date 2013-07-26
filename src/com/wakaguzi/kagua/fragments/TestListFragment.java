
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.wakaguzi.kagua.R;
import com.wakaguzi.kagua.adapters.GridAdapter;
import com.wakaguzi.kagua.parsers.JsonParser;

public class TestListFragment  extends ListFragment {
	
	public static final String KEY_NAME = "name"; 
	public static final String KEY_PHONE = "tel"; 
	public static final String KEY_LAST_VISIT = "lastvisit";
	public static final String KEY_IMAGE_URL = "image_url";
	
	
	HttpPost httpPost;
	JSONObject json;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	InputStream inputstream;
	ArrayList<NameValuePair> namevaluepairs;
	StringBuilder urlBuilder;
	String mNAME,res="",mRes="";
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
	}
	
	public class loadListView extends AsyncTask<Integer, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
