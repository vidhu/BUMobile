package com.vidhucraft.android.bumobile.apps.calendar.feed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vidhucraft.android.bumobile.R;
import com.vidhucraft.android.bumobile.apps.calendar.Main;

public class EventItemListAdapter extends BaseAdapter{

	Main context;
	JSONArray events;

	private LayoutInflater inflater;
	
	public EventItemListAdapter(Context context, JSONArray jArray) {
		this.context = (Main) context;
		this.events = jArray;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	
	@Override
	public int getCount() {
		return events.length();
	}

	@Override
	public JSONObject getItem(int position) {
		try {
			return events.getJSONObject(position);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.app_calender_event_row, null);
			
			holder = new ViewHolder();
			holder.tv_title = (TextView) convertView.findViewById(R.id.app_calender_event_row_title);
			holder.tv_StartTime = (TextView) convertView.findViewById(R.id.app_calender_event_row_time);
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		try{
			//Set UID
			holder.uid = events.getJSONObject(position).getString("uid");
			
			//Set Event Title
			String title = events.getJSONObject(position).getString("SUMMARY");
			holder.tv_title.setText(Html.fromHtml(title));
			holder.title = title;
			
			//Set Start Time
			String startTime = getEventTime(events.getJSONObject(position));
			holder.tv_StartTime.setText(startTime);
			holder.startTime = startTime;
		}catch(Exception ex){
			
		}
		
		return convertView;
	}
	
	private String getEventTime(JSONObject jObject) throws JSONException{
		if(!jObject.getString("DTEND").equals("false")){
			String friendlyTime = "";
			String period = "AM";
			JSONObject time = jObject.getJSONObject("DTEND");
			int hour = Integer.parseInt(time.getString("hour"));
			int min = Integer.parseInt(time.getString("min"));
			if(hour > 12){
				hour -= 12;
				period = "PM";
			}
			
			if(hour >= 10){
				friendlyTime = String.valueOf(hour);
			}else{
				friendlyTime = "0" + String.valueOf(hour);
			}
			if(min == 0){
				friendlyTime += ":00 " + period;
			}else{
				friendlyTime += ": " + String.valueOf(min) + period;
			}
			return friendlyTime;
		}
		return "All Day";
	}
	
	public class ViewHolder {
		int position;
		
		TextView tv_title;
		TextView tv_description;
		TextView tv_StartTime;
		
		String uid; 
		String title;
		String description;
		String startTime;
	}

}
