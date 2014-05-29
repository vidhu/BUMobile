package com.vidhucraft.android.bumobile.apps.bus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vidhucraft.android.bumobile.R;

public class DisplayBusStops extends AsyncTask<Boolean, Void, JSONArray> {

	private GoogleMap map;

	public DisplayBusStops(GoogleMap map) {
		this.map = map;
	}

	@Override
	protected JSONArray doInBackground(Boolean... args) {
		Boolean daytime = args[0];
		JSONArray jArray = null;
		
		try {
			String data = "";
			URL url;
			if (daytime) {
				url = new URL("http://www.bu.edu/maps/ajax/get-display-items.php?type=bus");
			} else {
				url = new URL("http://www.bu.edu/maps/ajax/get-display-items.php?type=bus_night");
			}

			/* Download JSON Data */
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				data += str;
			}
			in.close();
			jArray = new JSONObject(data).getJSONArray("markers");	
		} catch (Exception ex) {Log.d("Exception", ex.toString());}
		return jArray;
	}
	
	protected void onPostExecute(JSONArray result) {
		/* Place markers */
		try{
			for(int i=0; i < result.length(); i++){
				addBusStop(result.getJSONObject(i));
			}
		}catch(Exception ex){
			Log.d("Exception", ex.toString());
		}
    }
	
	private void addBusStop(JSONObject marker) throws Exception{
		map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
				.fromResource(R.drawable.map_bus_stop))
				.anchor(0.0f,  1.0f)
				.title(marker.getString("title"))
				.position(new LatLng(marker.getDouble("lat"), marker.getDouble("lng"))));
	}

}
