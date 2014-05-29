package com.vidhucraft.android.bumobile.apps.bus;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vidhucraft.android.bumobile.R;

import android.os.AsyncTask;
import android.util.Log;

public class DisplayBus extends AsyncTask<Void, Void, ArrayList<LatLng>> {

	private GoogleMap map;
	private ArrayList<Marker> buses_marker;
	
	public DisplayBus(GoogleMap map, ArrayList<Marker> marker){
		this.map = map;
		this.buses_marker = marker;
	}
	
	@Override
	protected ArrayList<LatLng> doInBackground(Void... arg0) {
		ArrayList<LatLng> coordinates = new ArrayList<LatLng>();
		
		try {
			String data = "";
			URL url = new URL("http://www.bu.edu/maps/livebus/livebus-bus-data.kml");

			/* Download XML Data */
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				data += str;
			}
			in.close();
			
			
			/* Parse XML */
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(data.getBytes("utf-8"))));
			doc.normalize();
			NodeList nList = doc.getElementsByTagName("Point");
			for(int i=0; i < nList.getLength(); i++){
				Node nNode = nList.item(i);
				Element element = (Element) nNode;
				
				String[] sCod = element.getElementsByTagName("coordinates").item(0).getTextContent().split(",");
				coordinates.add(new LatLng(Double.parseDouble(sCod[1]), Double.parseDouble(sCod[0])));
			}
			
		} catch (Exception ex) {
			Log.d("Exception", ex.toString());
		}

		return coordinates;
	}

	protected void onPostExecute(ArrayList<LatLng> result) {
		/* Remove Buses */
		for(Marker marker : buses_marker){
			marker.remove();
		}
		
		buses_marker.clear();
		
		/* Add buses */
		for (LatLng latLng : result) {
			Marker bus_marker = map.addMarker(new MarkerOptions()
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_bus_pointer))
				.position(latLng)
				.flat(true)
			);
			buses_marker.add(bus_marker);
		}
    }
}
