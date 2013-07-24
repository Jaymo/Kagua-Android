package com.wakaguzi.kagua.dataliberation;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.wakaguzi.kagua.KaguaApplication;


public class JsonExportTask {
	
	public static JSONObject JSONexport(){
		
		JSONObject json = new JSONObject();
		JSONArray valuesarray = new JSONArray();
		List<String[]> values =null ;
		
        values = KaguaApplication.mDb.selectAll();
     
        for (String[] name : values) {
        	JSONObject valuesJson = new JSONObject();
    		try{
    		valuesJson.put("id",name[0]); Log.v("id+name[0]", name[0]);
    		valuesJson.put("name",name[1]); Log.v("name+name[1]", name[1]);
    		valuesJson.put("tel",name[2]);  Log.v("tel+name[2]", name[2]);
    		valuesJson.put("lastvisit",name[3]); Log.v("lastvisit+name[3]", name[3]);
    		valuesJson.put("image_url",name[4]); Log.v("image_url+name[4]", name[4]);
    		
    		valuesarray.put(valuesJson); 
    		}
    		catch(JSONException e){
                Log.e("", "Error parsing data "+e.toString());
            }
        }
		
		try{

		json.put("PAYLOAD",valuesarray);

		}
		catch(JSONException e){
            Log.e("", "Error parsing data "+e.toString());
        }
		

		
		return json;
		
	}
}
