package  com.wakaguzi.kagua.dataliberation;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.wakaguzi.kagua.parsers.JsonParser;

public class JsonImportTask extends AsyncTask< String, Integer, JSONObject > { 

    JSONObject jArray,JSON;
	
   @Override
    protected void onPreExecute() {
        super.onPreExecute();            

    }
	@Override
	protected JSONObject doInBackground(String... params) {
		jArray = JsonParser.getJSONfromURL(params[0]);
		 //Check for some validation stuff
		// clear DB and install new Tv Shows and  stuff
			
		return jArray;
		
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress) {
		
		
	}
	
	
	
	
	
}
	



