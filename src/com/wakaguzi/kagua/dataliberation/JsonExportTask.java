package com.wakaguzi.kagua.dataliberation;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.wakaguzi.kagua.KaguaApplication;

public class JsonExportTask {

	public static JSONObject JSONexport() {

		JSONObject json = new JSONObject();
		JSONArray valuesarray = new JSONArray();
		List<String[]> values = null;

		values = KaguaApplication.mDb.selectAll();

		for (String[] entries : values) {
			JSONObject valuesJson = new JSONObject();
			try {
				valuesJson.put("id", entries[0]);
				valuesJson.put("name", entries[1]);
				valuesJson.put("tel", entries[2]);
				valuesJson.put("lastvisit", entries[3]);
				valuesJson.put("image_url", entries[4]);

				valuesarray.put(valuesJson);
			} catch (JSONException e) {
				Log.e("JsonExportTask", "Error parsing data " + e.toString());
			}
		}

		try {

			json.put("PAYLOAD", valuesarray);

		} catch (JSONException e) {
			Log.e("JsonExportTask", "Error parsing data " + e.toString());
		}

		return json;

	}
}
