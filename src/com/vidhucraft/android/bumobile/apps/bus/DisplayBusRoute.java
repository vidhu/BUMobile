package com.vidhucraft.android.bumobile.apps.bus;

import org.json.JSONArray;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import android.graphics.Color;
import android.os.AsyncTask;

public class DisplayBusRoute extends AsyncTask<Void, Void, JSONArray> {

	private GoogleMap map;
	
	public DisplayBusRoute(GoogleMap map){
		this.map = map;
	}
	
	@Override
	protected JSONArray doInBackground(Void... params) {
		
		return null;
	}

	protected void onPostExecute(JSONArray result) {
		PolylineOptions route = new PolylineOptions().geodesic(true);
		
		/** Set Route Style **/
		route.width(5);
		route.color(Color.parseColor("#CC0000"));
		
		/** Hard Coded Lines **/
		route.add(new LatLng(42.352514, -71.118344));
		route.add(new LatLng(42.352514, -71.118344));
		route.add(new LatLng(42.353600, -71.118092));
		route.add(new LatLng(42.353687, -71.117968));
		route.add(new LatLng(42.353683, -71.117775));
		route.add(new LatLng(42.353190, -71.116724));
		route.add(new LatLng(42.352641, -71.115673));
		route.add(new LatLng(42.352482, -71.115479));
		route.add(new LatLng(42.351745, -71.115656));
		route.add(new LatLng(42.351007, -71.115801));
		route.add(new LatLng(42.350774, -71.113768));
		route.add(new LatLng(42.350520, -71.111773));
		route.add(new LatLng(42.349834, -71.106006));
		route.add(new LatLng(42.348882, -71.098013));
		route.add(new LatLng(42.348764, -71.096972));
		route.add(new LatLng(42.348851, -71.092836));
		route.add(new LatLng(42.348545, -71.092772));
		route.add(new LatLng(42.347915, -71.092455));
		route.add(new LatLng(42.347808, -71.092359));
		route.add(new LatLng(42.347245, -71.092407));
		route.add(new LatLng(42.346948, -71.092380));
		route.add(new LatLng(42.346813, -71.092284));
		route.add(new LatLng(42.346757, -71.092166));
		route.add(new LatLng(42.346444, -71.091141));
		route.add(new LatLng(42.346301, -71.090884));
		route.add(new LatLng(42.346083, -71.090739));
		route.add(new LatLng(42.345865, -71.090669));
		route.add(new LatLng(42.345493, -71.090594));
		route.add(new LatLng(42.344831, -71.090744));
		route.add(new LatLng(42.344595, -71.090776));
		route.add(new LatLng(42.344359, -71.090744));
		route.add(new LatLng(42.344244, -71.090661));
		route.add(new LatLng(42.344109, -71.090444));
		route.add(new LatLng(42.343984, -71.089990));
		route.add(new LatLng(42.343831, -71.089124));
		route.add(new LatLng(42.343324, -71.085798));
		route.add(new LatLng(42.342773, -71.084913));
		route.add(new LatLng(42.342265, -71.084189));
		route.add(new LatLng(42.342150, -71.084124));
		route.add(new LatLng(42.341337, -71.082923));
		route.add(new LatLng(42.340572, -71.081802));
		route.add(new LatLng(42.340255, -71.081367));
		route.add(new LatLng(42.339801, -71.080916));
		route.add(new LatLng(42.339208, -71.080348));
		route.add(new LatLng(42.338391, -71.079409));
		route.add(new LatLng(42.337325, -71.078068));
		route.add(new LatLng(42.336448, -71.077027));
		route.add(new LatLng(42.335752, -71.077942));
		route.add(new LatLng(42.335048, -71.078792));
		route.add(new LatLng(42.334128, -71.079929));
		route.add(new LatLng(42.334077, -71.080058));
		route.add(new LatLng(42.333764, -71.080460));
		route.add(new LatLng(42.333010, -71.081045));
		route.add(new LatLng(42.332903, -71.081120));
		route.add(new LatLng(42.332792, -71.080943));
		route.add(new LatLng(42.332221, -71.079876));
		route.add(new LatLng(42.331931, -71.079221));
		route.add(new LatLng(42.331575, -71.078309));
		route.add(new LatLng(42.331190, -71.076995));
		route.add(new LatLng(42.331527, -71.076459));
		route.add(new LatLng(42.331916, -71.075810));
		route.add(new LatLng(42.331963, -71.075616));
		route.add(new LatLng(42.332165, -71.075316));
		route.add(new LatLng(42.332344, -71.074962));
		route.add(new LatLng(42.332633, -71.074533));
		route.add(new LatLng(42.332899, -71.074232));
		route.add(new LatLng(42.333195, -71.073865));
		route.add(new LatLng(42.333478, -71.073444));
		route.add(new LatLng(42.333569, -71.073304));
		route.add(new LatLng(42.333676, -71.073278));
		route.add(new LatLng(42.334406, -71.072205));
		route.add(new LatLng(42.334937, -71.071443));
		route.add(new LatLng(42.334985, -71.071432));
		route.add(new LatLng(42.335469, -71.070719));
		route.add(new LatLng(42.335933, -71.070080));
		route.add(new LatLng(42.336016, -71.070182));
		route.add(new LatLng(42.337495, -71.071958));
		route.add(new LatLng(42.338871, -71.073589));
		route.add(new LatLng(42.338013, -71.074844));
		route.add(new LatLng(42.336520, -71.076915));
		route.add(new LatLng(42.337892, -71.078572));
		route.add(new LatLng(42.338605, -71.079468));
		route.add(new LatLng(42.339367, -71.080353));
		route.add(new LatLng(42.340015, -71.080955));
		route.add(new LatLng(42.340564, -71.081574));
		route.add(new LatLng(42.340640, -71.081705));
		route.add(new LatLng(42.341825, -71.083459));
		route.add(new LatLng(42.342220, -71.084028));
		route.add(new LatLng(42.342265, -71.084167));
		route.add(new LatLng(42.342997, -71.085262));
		route.add(new LatLng(42.343332, -71.085787));
		route.add(new LatLng(42.345187, -71.086694));
		route.add(new LatLng(42.347975, -71.088083));
		route.add(new LatLng(42.349763, -71.088952));
		route.add(new LatLng(42.350849, -71.089489));
		route.add(new LatLng(42.350359, -71.091259));
		route.add(new LatLng(42.350115, -71.092214));
		route.add(new LatLng(42.349945, -71.092874));
		route.add(new LatLng(42.349878, -71.093308));
		route.add(new LatLng(42.349755, -71.093571));
		route.add(new LatLng(42.349204, -71.095459));
		route.add(new LatLng(42.349073, -71.096033));
		route.add(new LatLng(42.348982, -71.096522));
		route.add(new LatLng(42.349009, -71.097149));
		route.add(new LatLng(42.348997, -71.097358));
		route.add(new LatLng(42.349973, -71.105319));
		route.add(new LatLng(42.350472, -71.109428));
		route.add(new LatLng(42.350472, -71.109729));
		route.add(new LatLng(42.351543, -71.118548));
		route.add(new LatLng(42.352514, -71.118344));
		
		map.addPolyline(route);
    }
}
