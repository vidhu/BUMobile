package com.vidhucraft.android.bumobile.apps.calendar.feed;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vidhucraft.android.bumobile.apps.calendar.Main;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class DownloadEventsDetail extends AsyncTask<Void, Void, JSONArray> {

	private Main context;
	private String eventUID;
	private ProgressDialog progress;
	
	
	public DownloadEventsDetail(Main context, String eventUID){
		this.context = context;
		this.eventUID = eventUID;
	}
	
	@Override
	protected void onPreExecute() {
		this.progress.show();
	}
	
	@Override
	protected JSONArray doInBackground(Void... arg0) {
		String result = "";
		JSONArray jArray = null;
		
		try{
			URL url = new URL("http://www.bu.edu/phpbin/calendar/client/rpc/event.json.php?uid=" + this.eventUID);
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
		this.progress.dismiss();
	}

}
