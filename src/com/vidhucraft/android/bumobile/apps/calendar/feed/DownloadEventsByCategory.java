package com.vidhucraft.android.bumobile.apps.calendar.feed;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadEventsByCategory extends AsyncTask<Void, Void, JSONArray> {

	private String category;
	private com.vidhucraft.android.bumobile.apps.calendar.Main context;
	private ProgressDialog progress;
	
	public DownloadEventsByCategory(Context context, String category, String friendlyName){
		this.context = (com.vidhucraft.android.bumobile.apps.calendar.Main) context;
		this.category = category;
		this.progress = new ProgressDialog(context);
		this.progress.setMessage("Loading events from " + friendlyName);
	}
	
	@Override
	protected void onPreExecute() {
		this.progress.show();
	}
	
	@Override
	protected JSONArray doInBackground(Void... params) {
		String result = "";
		JSONArray jArray = null;
		
		try{
			URL url = new URL("http://www.bu.edu/phpbin/calendar/client/rpc/events.json.php?category=" + this.category);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = null;
		    while ((s = reader.readLine()) != null)
		        result += s;
			jArray = new JSONObject(result.trim()).getJSONObject("ResultSet").getJSONArray("Result");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return jArray;
	}
	
	@Override
	protected void onPostExecute(JSONArray feed) {
		this.context.loadEvents(feed);
		this.progress.dismiss();
	}

}
