package com.vidhucraft.android.bumobile.apps.bus;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.vidhucraft.android.bumobile.R;

public class Main extends Activity {
	
	private TimerTask doAsynchronousTask;
	private GoogleMap map;
	private ArrayList<Marker> buses_marker = new ArrayList<Marker>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_bus_activity_bus);
		
		MapsInitializer.initialize(this);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.bus_map)).getMap();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.348189, -71.099612), 14));
		
	}
	
	private void setUpMaps(){
		new DisplayBusStops(map).execute(true);
		new DisplayBusRoute(map).execute();
		
		final Handler handler = new Handler();
		Timer timer = new Timer();
		doAsynchronousTask = new TimerTask() {
			
			@Override
			public void run() {
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						try{
							new DisplayBus(map, buses_marker).execute();
						}catch(Exception e){
							
						}
					}
				});
			}
		};
		timer.schedule(doAsynchronousTask, 0, 3000);
	}
	
	@Override
	public void onPause() {
	    super.onPause();  // Always call the superclass method first
	    doAsynchronousTask.cancel();
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	    setUpMaps();
	}
}
