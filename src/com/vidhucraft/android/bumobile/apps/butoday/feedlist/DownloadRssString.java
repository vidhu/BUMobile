package com.vidhucraft.android.bumobile.apps.butoday.feedlist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;

import com.vidhucraft.android.bumobile.apps.butoday.Main;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

public class DownloadRssString extends AsyncTask<String, Integer, JSONArray> {

	private Context context;
	private Main activity;
	private ProgressDialog progress;
	
	public DownloadRssString(Main activity) {
		this.context = activity;
		this.activity = activity;
	}

	@Override
	protected void onPreExecute(){
		progress = ProgressDialog.show(context, "Loading News", "Please wait while news feeds are being downloaded from BU Today");
	}
	
	@Override
	protected JSONArray doInBackground(String... params) {
		
		String result = "";
		JSONArray jArray = null;
		
		try{
			URL url = new URL("http://butoday.vidhucraft.com/");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = null;
			
		    while ((s = reader.readLine()) != null)
		        result += s;
		    jArray = new JSONArray(result.trim());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return jArray;
	}

	protected void onPostExecute(JSONArray feed) {
		if(feed != null && isNetworkAvailable() != false){
			activity.setRssData(feed);
		}else{
			Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();
		}		
		progress.dismiss();
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
