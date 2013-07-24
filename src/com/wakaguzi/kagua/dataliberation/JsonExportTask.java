package com.wakaguzi.kagua.dataliberation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JsonExportTask extends AsyncTask< String, Integer, JSONObject > {
	
	@Override
    protected void onPreExecute() {
        super.onPreExecute();            

    }
	
	@Override
	protected JSONObject doInBackground(String... params) {
		//Create main json object
		JSONObject json = new JSONObject();
		JSONArray valuesarray = new JSONArray();
		

		for(int i=0;i<=4;i++){
		 // Create values jsonObject
		JSONObject valuesJson = new JSONObject();
		try{
		valuesJson.put("front","front_value");
		valuesJson.put("back","back_value");
		valuesJson.put("color","color_value");
		
		valuesarray.put(valuesJson); 
		}
		catch(JSONException e){
            Log.e("", "Error parsing data "+e.toString());
        }

		}
		try{

		json.put("value",valuesarray);

		}
		catch(JSONException e){
            Log.e("", "Error parsing data "+e.toString());
        }
			
		return null;
		
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress) {
		
		
	}

}
